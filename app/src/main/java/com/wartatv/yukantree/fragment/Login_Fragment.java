package com.wartatv.yukantree.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.MainActivity;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.model.ResponseLogin;
import com.wartatv.yukantree.model.User;
import com.wartatv.yukantree.model.UserAddress;
import com.wartatv.yukantree.util.CustomToast;
import com.wartatv.yukantree.util.Preferences;
import com.wartatv.yukantree.util.Utils;
import com.wartatv.yukantree.util.localstorage.LocalStorage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wartatv.yukantree.util.localstorage.LocalStorage.USER_EMAIL;

/**
 * Created by .
 * www.wartatv.com
 */
public class Login_Fragment extends Fragment implements OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context _context;


    Animation frombottom, fromtop;
    ProgressDialog progressDialog;
    LocalStorage localStorage;
    String userString;
    UserAddress userAddress;
    Gson gson;

    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = view.findViewById(R.id.login_emailid);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.loginBtn);
        forgotPassword = view.findViewById(R.id.forgot_password);
        signUp = view.findViewById(R.id.createAccount);
        show_hide_password = view
                .findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);
        progressDialog = new ProgressDialog(getContext());
//        localStorage = new LocalStorage(getContext());
//        Gson gson = new Gson();
//        userString = localStorage.getUserLogin();
//        user = gson.fromJson(userString, User.class);
//        Log.d("User", userString);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
//        frombottom = AnimationUtils.loadAnimation(getActivity(),R.anim.frombottom);
//        fromtop = AnimationUtils.loadAnimation(getActivity(),R.anim.fromtop);
//        loginButton.startAnimation(frombottom);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getActivity())){
            startActivity(new Intent(getActivity(),MainActivity.class));
            getActivity().finish();
        }
    }
    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        final String getEmailId = emailid.getText().toString();
        final String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");
            vibrate(200);
        }
        // Check if email id is valid or not
        else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            vibrate(200);
            // Else do login and do your stuff
        } else {
            progressDialog.setMessage("Please Wait....");
            progressDialog.show();

            Handler mHand = new Handler();
            mHand.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (emailid != null) {
                        if (emailid == null || password == null) {
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Please Check Email or Password");
                        } else {

                            BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
                            Call<ResponseLogin> call = apiService.loginRequest(
                                    getEmailId,
                                    getPassword
                            );
                            call.enqueue(new Callback<ResponseLogin>() {
                                @Override
                                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                    if (response.isSuccessful()){
                                        ResponseLogin databody = response.body();

                                        String fullname = databody.getName();
//                                        Log.i("debug", "onResponse: BERHASIL "+fullname);
                                        Preferences.setUserid(getActivity(),databody.getUserid());
                                        Preferences.setRegisteredUser(getActivity(),getEmailId);
                                        Preferences.setLoggedInUser(getActivity(),fullname);
                                        Preferences.setLoggedInStatus(getActivity(),true);

                                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                                    getActivity().finish();
                                                    getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                        } else {
                                            Log.i("debug", "onResponse: GA BERHASIL");
                                            new CustomToast().Show_Toast(getActivity(), view,
                                                    "Login Gagal");
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                    Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                    new CustomToast().Show_Toast(getActivity(), view,
                                            "Gagal");
                                }

                            });


                        }
                    } else {
                        new CustomToast().Show_Toast(getActivity(), view,
                                "Please Register whith This Email");
                    }

                    progressDialog.dismiss();

                }
            }, 50);

        }
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }
}
