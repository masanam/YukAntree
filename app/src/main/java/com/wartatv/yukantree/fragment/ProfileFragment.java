package com.wartatv.yukantree.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.BuildConfig;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.util.CustomToast;
import com.wartatv.yukantree.util.Preferences;
import com.wartatv.yukantree.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.pm.PackageManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    ImageView imgProfilePic, imgUploadPic;
    ProgressDialog dialog;

    private static final int REQUEST_CAMERA = 101;
    private static final int PICK_IMAGE_REQUEST = 102;
    private static final int REQUEST_CODE_CROP_IMAGE = 103;
    private Bitmap cameraBitmap;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgUploadPic = view.findViewById(R.id.imgProfile);
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
        setListeners();
        return view;
    }

    // Set Listeners
    private void setListeners() {

        updateBtn.setOnClickListener(this);
        imgUploadPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                selectImage();

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

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {

                    cameraIntent();

                } else if (items[item].equals("Choose from Library")) {

                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File out = Environment.getExternalStorageDirectory();
//        out = new File(out, "abc");
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
//        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivityForResult(cameraIntent, REQUEST_CAMERA);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                File mPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStorageDirectory();
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    private void galleryIntent() {

        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    private void performCrop(Uri picUri) {
        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, REQUEST_CODE_CROP_IMAGE);
        } catch (ActivityNotFoundException anfe) {
            String errorMessage = "your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            switch (requestCode) {

                case PICK_IMAGE_REQUEST:

                    Uri u = data.getData();
                    performCrop(u);

                    break;

                case REQUEST_CAMERA:

                    File file = new File(Environment.getExternalStorageDirectory()
                            + File.separator + "abc");
                    // Crop the captured image using an other intent
                    try {
                        /* the user's device may not support cropping */
                        performCrop(Uri.fromFile(file));
                    } catch (ActivityNotFoundException aNFE) {
                        // display an error message if user device doesn't support
                        String errorMessage = "Sorry - your device doesn't support the crop action!";
                        Toast toast = Toast.makeText(getActivity(), errorMessage,
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    break;

                case REQUEST_CODE_CROP_IMAGE:

                    if (resultCode == Activity.RESULT_OK) {
                        Bundle extras = data.getExtras();
                        cameraBitmap = extras.getParcelable("data");
                        imgProfilePic.setImageBitmap(cameraBitmap);
                    }
                    break;
            }
        }
    }

    private String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void updateData() {
//        final String name = edtUserName.getText().toString().trim();
        BitmapDrawable drawable = (BitmapDrawable) imgProfilePic.getDrawable();
        Bitmap profileBitmap = drawable.getBitmap();

        Bitmap bitmap = null;

        if (cameraBitmap == null) {
            bitmap = profileBitmap;
        } else {
            bitmap = cameraBitmap;
        }

        final String image = getStringImage(bitmap);


        final ProgressDialog dialog;
        /**
         * Progress Dialog for User Interaction
         */
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loding");
        dialog.setMessage("Please Wait...");
        dialog.show();

        //Creating an object of our api interface
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

                    //Dismiss Dialog
                    dialog.dismiss();
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    getActivity().startActivity(i);

                    String errorMessage = "Sorry - your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(getActivity(), errorMessage,
                            Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                dialog.dismiss();
            }

        });
    }

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

    }