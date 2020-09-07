package com.wartatv.yukantree.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.MainActivity;
import com.wartatv.yukantree.adapter.CategoryAdapter;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.helper.Data;
import com.wartatv.yukantree.model.Category;
import com.wartatv.yukantree.model.ModelCategory;
import com.wartatv.yukantree.util.CustomToast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by .
 * www.wartatv.com
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    Data data;
    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoryAdapter mAdapter;
    String[] id;
    String[] title;
    String[] image;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.category_rv);
//        data = new Data();
//        mAdapter = new CategoryAdapter(data.getCategoryList(), getContext(), "Category");
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);

        getCategoryList();

        return view;
    }

    public void getCategoryList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelCategory> call = apiService.getCategory();

        call.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                if (response.isSuccessful()){
                    ModelCategory databody = response.body();
                    List<Category> data = databody.getResult();

                    id = new String[data.size()];
                    title = new String[data.size()];
                    image = new String[data.size()];
                    int i = 0;
                    for (Category d: data) {
                        id[i] = d.getId();
                        title[i] = d.getTitle();
                        image[i] = d.getImage();

                        Category c = new Category(id[i],title[i],image[i]);
                        categoryList.add(c);

                        Log.i("debug", "onResponse: Category-Data " +title[i]);
                        i++;
                    }
                    updateCategory(categoryList);
                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }

    private void updateCategory(List<Category> categoryList) {
        mAdapter = new CategoryAdapter(categoryList, getContext(), "Category");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Category");
    }

}
