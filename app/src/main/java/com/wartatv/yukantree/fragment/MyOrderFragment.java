package com.wartatv.yukantree.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.BaseActivity;
import com.wartatv.yukantree.adapter.OrderAdapter;
import com.wartatv.yukantree.model.Order;
import com.wartatv.yukantree.util.localstorage.LocalStorage;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by .
 * www.wartatv.com
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment {
    LocalStorage localStorage;
    LinearLayout linearLayout;
    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;

    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);

        recyclerView = view.findViewById(R.id.order_rv);
        orderList = ((BaseActivity) getActivity()).getOrderList();
        mAdapter = new OrderAdapter(orderList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        linearLayout = view.findViewById(R.id.no_order_ll);
        if (orderList.isEmpty()) {
            linearLayout.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("MyOrder");
    }
}
