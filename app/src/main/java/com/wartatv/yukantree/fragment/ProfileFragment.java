package com.wartatv.yukantree.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.ImagePickerActivity;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.util.Preferences;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.pm.PackageManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created wartatv on 24-Sept-2019.
 * www.wartatv.com
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static Button updateBtn;

    private static EditText fullname,  mobileNumber, address, city, gender, idKtp, dateOfBirth, blood;
    private static TextView userEmail;
    private static String emailId ;
    private static String getFullName,getPhone,getAddres,getCity,getGender,getBlood,getKtp,getdateOfBirth;
    private static View view;
    ImageView imgProfilePic, imgUploadPic, imgplus;
    ProgressDialog dialog;

    public static final int REQUEST_IMAGE = 100;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgUploadPic = view.findViewById(R.id.imgProfile);
        imgplus = view.findViewById(R.id.img_plus);
        imgProfilePic = view.findViewById(R.id.imgProfile);
        fullname = view.findViewById(R.id.fullName);
        userEmail = view.findViewById(R.id.userEmail);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        address = view.findViewById(R.id.user_address);
        city = view.findViewById(R.id.city);
        gender = view.findViewById(R.id.gender);
        idKtp = view.findViewById(R.id.idKtp);
        dateOfBirth = view.findViewById(R.id.dateOfBirth);
        blood = view.findViewById(R.id.blood);
        updateBtn = view.findViewById(R.id.updateBtn);

        getProfile();
        ImagePickerActivity.clearCache(getActivity());
        setListeners();
        return view;
    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);
        Picasso.get().load(url).error(R.drawable.no_image).into(imgProfilePic, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                Log.d("debug : ", "berhasil");
            }

            @Override
            public void onError(Exception e) {
                Log.d("Error : ", e.getMessage());
            }
        });

//        GlideApp.with(this).load(url)
//                .into(imgProfilePic);
//        imgProfilePic.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.transparent));
    }

//    private void loadProfileDefault() {
//        GlideApp.with(this).load(R.drawable.baseline_account_circle_black_48)
//                .into(imgProfilePic);
//        imgProfilePic.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
//    }

    // Set Listeners
    private void setListeners() {
        updateBtn.setOnClickListener(this);
        imgUploadPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                onProfileImageClick();
            }
        });
    }

    private void getProfile() {
        emailId = Preferences.getRegisteredUser(getActivity());
        final String image = "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png";
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelUser> call = apiService.getUserProfile(emailId);

        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (response.isSuccessful()){
                    ModelUser databody = response.body();
                    Log.i("debug", "onResponse: Profile Found "+databody.getResult().get(0).getName());

                    fullname.setText(databody.getResult().get(0).getName());
                    userEmail.setText(databody.getResult().get(0).getEmail());
                    mobileNumber.setText(databody.getResult().get(0).getPhone());
                    address.setText(databody.getResult().get(0).getAddress());
                    city.setText(databody.getResult().get(0).getCity());
                    gender.setText(databody.getResult().get(0).getGender());
                    idKtp.setText(databody.getResult().get(0).getIdKtp());
                    dateOfBirth.setText(databody.getResult().get(0).getDateOfBirth());
                    blood.setText(databody.getResult().get(0).getBlood());
                    Picasso.get().load(databody.getResult().get(0).getPhoto()).error(R.drawable.no_image).into(imgProfilePic, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("debug : ", "berhasil");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Error : ", e.getMessage());
                        }
                    });

                }else{
                    Log.i("debug", "onResponse: Profile Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profile");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateBtn:
                updateProfile();
                break;
        }
    }


//    private void cameraIntent() {
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        startActivity(intent);
//
//    }


    private void updateProfile() {
        emailId = Preferences.getRegisteredUser(getActivity());

        getFullName = fullname.getText().toString();
        getPhone = mobileNumber.getText().toString();
        getAddres = address.getText().toString();
        getCity = city.getText().toString();
        getGender = gender.getText().toString();
        getBlood = blood.getText().toString();
        getKtp = idKtp.getText().toString();
        getdateOfBirth = dateOfBirth.getText().toString();

        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
            Call<ModelUser> call = apiService.updateProfile(
                    emailId,
                    getFullName,
                    getdateOfBirth,
                    getPhone,
                    getAddres,
                    getCity,
                    getGender,
                    getBlood,
                    getKtp
                    );

        call.enqueue(new Callback<ModelUser>() {
                @Override
                public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                    if (response.isSuccessful()){
//
                        Preferences.setLoggedInUser(getActivity(),getFullName);
//                        new CustomToast().Show_Toast(getActivity(), view, "Profile Updated");
                        /**
                         * Progress Dialog for User Interaction
                         */
                        dialog = new ProgressDialog(getActivity());
//                        dialog.setTitle("Profile");
                        dialog.setMessage("Profile Updated");
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 1000); // 5000 milliseconds
                        Log.i("debug", "onResponse: BERHASIL");
                    } else {
                        Log.i("debug", "onResponse: GA BERHASIL");
                        }
                }

                @Override
                public void onFailure(Call<ModelUser> call, Throwable throwable) {
                    Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
                }
            });
    }

    void onProfileImageClick() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getActivity(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", String.valueOf(getActivity()), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

}