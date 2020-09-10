package com.wartatv.yukantree.fragment;


import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.wartatv.yukantree.R;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.util.Preferences;

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
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static Button updateBtn;

    EditText fullname,  mobileNumber, address, city, gender, idKtp, dateOfBirth;
    TextView userEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        setListeners();

        return view;
    }

    private void setListeners() {
        updateBtn.setOnClickListener(this);
    }

    private void initViews() {
        fullname = view.findViewById(R.id.fullName);
        userEmail = view.findViewById(R.id.userEmail);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        address = view.findViewById(R.id.user_address);
        city = view.findViewById(R.id.city);
        gender = view.findViewById(R.id.gender);
        idKtp = view.findViewById(R.id.idKtp);
        dateOfBirth = view.findViewById(R.id.dateOfBirth);
        updateBtn = view.findViewById(R.id.updateBtn);
        getProfile();

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

//            login.setTextColor(csl);
//            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profile");
    }

    public void getProfile(){
        final String emailId = Preferences.getRegisteredUser(getActivity());

        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelUser> call = apiService.getUserProfile(emailId);

        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (response.isSuccessful()){
                    ModelUser databody = response.body();

                        fullname.setText(databody.getResult().get(0).getName());
                        userEmail.setText(databody.getResult().get(0).getEmail());
                        mobileNumber.setText(databody.getResult().get(0).getPhone());
                        address.setText(databody.getResult().get(0).getAddress());
                    city.setText(databody.getResult().get(0).getCity());
                    gender.setText(databody.getResult().get(0).getGender());
                    idKtp.setText(databody.getResult().get(0).getIdKtp());
                    dateOfBirth.setText(databody.getResult().get(0).getDateOfBirth());


                    Log.i("debug", "onResponse: Category Found "+databody.getResult().get(0).getName());


                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateBtn:
                // Call checkValidation method
                UpdateProfile();
                break;

        }
    }

    private void UpdateProfile() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
