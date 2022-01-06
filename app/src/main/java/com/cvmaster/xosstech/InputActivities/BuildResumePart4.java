package com.cvmaster.xosstech.InputActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.PermissionsUtil;
import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BuildResumePart4 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editText_FullName,editText_MotherName,editText_FatherName,etMobile,etEmail,etSummary,etCurrenJobTittle;
    private SpinKitView progrssBar ;

    private TextView textView_Gender;
    private Spinner spinner_Gender;

    private TextView textView_BD;
    private TextView textView_BirthDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private TextView textView_MaritalStatus;
    private Spinner spinner_MaritalStatus;

    private TextView textView_Nationality;
    private TextView tvDataSave ;
    private Spinner spinner_Nationality;

    private TextView textView_Religion;
    private Spinner spinner_Religion;

    private EditText editText_PresentAddress;
    private EditText editText_PermanentAddress;

    private Button btn_gallery;

    private String infoUrl = "http://xosstech.com/cvm/api/public/api/info";
    private String updateInfoUrl = "http://xosstech.com/cvm/api/public/api/info/update/";

    private String fullname = null;
    private String fathername = null;
    private String mothername = null;
    private String email = null;
    private String mobile = null;
    private String gender = "Select";
    private String birthdate = null;
    private String maritalstatus = "Select";
    private String fatherName = null;
    private String motherName = null;
    private String nationality = "Select";
    private String religion = "Select";
    private String presentaddress = null;
    private String permanentaddress = null;
    private String encodeImageString = null;
    private String profileSummary = null;
    private String currentJobTittle = null;
    private String id = null;
    private String currentPhotoPath,token;
    private ImageView imvProfile ;
    private Bitmap bitmap;
    private RewardedAd mRewardedAd;

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;
    private final int GALLERY_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part4);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        progrssBar = findViewById(R.id.spin_kit);
        btn_gallery = findViewById(R.id.button_BuildResumePart4_SelectFromGallery);
        btn_gallery.setOnClickListener(this);

        editText_FullName = (EditText) findViewById(R.id.editText_BuildResumePart4_FullName);
        editText_FatherName = (EditText) findViewById(R.id.editText_BuildResumePart4_FatherName);
        editText_MotherName = (EditText) findViewById(R.id.editText_BuildResumePart4_MotherName);
        etMobile = findViewById(R.id.editText_BuildResumePart4_mobile);
        etEmail = findViewById(R.id.editText_BuildResumePart4_email);
        imvProfile = findViewById(R.id.imageView_BuildResumePart1_Image);
        etSummary = findViewById(R.id.editText_BuildResumePart4_Summary);
        etCurrenJobTittle = findViewById(R.id.editText_BuildResumePart4_Tittle);

        tvDataSave = findViewById(R.id.tvPersonalDataSave);
        tvDataSave.setOnClickListener(this);

        textView_Gender = (TextView) findViewById(R.id.textView_BuildResumePart4_Gender);
        spinner_Gender = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Gender);
        ArrayAdapter<CharSequence> arrayAdapterGender = ArrayAdapter.createFromResource(this,R.array.gender_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Gender.setAdapter(arrayAdapterGender);

        textView_BD = (TextView) findViewById(R.id.textView_BuildResumePart4_BD);
        textView_BirthDate = (TextView) findViewById(R.id.textView_BuildResumePart4_BirthDate);
        textView_BirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BuildResumePart4.this,
                        android.R.style.Theme,
                        onDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"-"+month+"-"+year;
                textView_BirthDate.setText(date);
            }
        };



        textView_MaritalStatus = (TextView) findViewById(R.id.textView_BuildResumePart4_MaritialStatus);
        spinner_MaritalStatus = (Spinner) findViewById(R.id.spinner_BuildResumePart4_MaritialStatus);
        ArrayAdapter<CharSequence> arrayAdapterMaritalstatus = ArrayAdapter.createFromResource(this,R.array.marital_Status_Names,android.R.layout.simple_spinner_item);
        arrayAdapterMaritalstatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_MaritalStatus.setAdapter(arrayAdapterMaritalstatus);
       /* int initialMaritalPosition=spinner_MaritalStatus.getSelectedItemPosition();
        spinner_MaritalStatus.setSelection(initialMaritalPosition, false);*/


        textView_Nationality = (TextView) findViewById(R.id.textView_BuildResumePart4_Nationality);
        spinner_Nationality = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Nationality);
        ArrayAdapter<CharSequence> arrayAdapterNationality = ArrayAdapter.createFromResource(this,R.array.nationality_Names,android.R.layout.simple_spinner_item);
        arrayAdapterNationality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Nationality.setAdapter(arrayAdapterNationality);
/*        int initialNationalityPosition=spinner_Nationality.getSelectedItemPosition();
        spinner_Nationality.setSelection(initialNationalityPosition, false);*/


        textView_Religion = (TextView) findViewById(R.id.textView_BuildResumePart4_Religion);
        spinner_Religion = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Religion);
        ArrayAdapter<CharSequence> arrayAdapterReligion = ArrayAdapter.createFromResource(this,R.array.religion_Names,android.R.layout.simple_spinner_item);
        arrayAdapterReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Religion.setAdapter(arrayAdapterReligion);
      /*  int initiaReligionPosition=spinner_Religion.getSelectedItemPosition();
        spinner_Religion.setSelection(initiaReligionPosition, false);*/


        editText_PresentAddress = (EditText) findViewById(R.id.editText_BuildResumePart4_PresentAddress);
        editText_PermanentAddress = (EditText) findViewById(R.id.editText_BuildResumePart4_PermanentAddress);

        if(ResumeProfilePart4.getName()!=null)
        {
            id = ResumeProfilePart4.getId();
            editText_FullName.setText(ResumeProfilePart4.getName());
            editText_FatherName.setText(ResumeProfilePart4.getFather_name());
            editText_MotherName.setText(ResumeProfilePart4.getMother_name());
            etMobile.setText(ResumeProfilePart4.getMobile());
            etEmail.setText(ResumeProfilePart4.getEmail());
            textView_BD.setText(ResumeProfilePart4.getBirth_date());
            textView_BirthDate.setText(ResumeProfilePart4.getBirth_date());
            textView_MaritalStatus.setText(ResumeProfilePart4.getMarital_status());
            textView_Religion.setText(ResumeProfilePart4.getReligion());
            textView_Gender.setText(ResumeProfilePart4.getGender());
            textView_Nationality.setText(ResumeProfilePart4.getNationality());
            etCurrenJobTittle.setText(ResumeProfilePart4.getJobTitle());
            etSummary.setText(ResumeProfilePart4.getProfileSummary());
            editText_PermanentAddress.setText(ResumeProfilePart4.getPermanent_address());
            editText_PresentAddress.setText(ResumeProfilePart4.getPresent_address());
            Picasso.with(getApplicationContext()).load(ResumeProfilePart4.getImage()).into(imvProfile);

            gender = ResumeProfilePart4.getGender();
            nationality = ResumeProfilePart4.getNationality();
            maritalstatus = ResumeProfilePart4.getMarital_status();
            religion = ResumeProfilePart4.getReligion();
            encodeImageString = ResumeProfilePart4.getImage();
        }



        /*int initialGenderPosition=spinner_Gender.getSelectedItemPosition();
        spinner_Gender.setSelection(initialGenderPosition, false);*/

        int genderPosition = arrayAdapterGender.getPosition(gender);
        spinner_Gender.setSelection(genderPosition);
        spinner_Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = spinner_Gender.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        int maritalPosition = arrayAdapterMaritalstatus.getPosition(maritalstatus);
        spinner_MaritalStatus.setSelection(maritalPosition);
        spinner_MaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maritalstatus = spinner_MaritalStatus.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        int religionPosition = arrayAdapterReligion.getPosition(religion);
        spinner_Religion.setSelection(religionPosition);
        spinner_Religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                religion = spinner_Religion.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                religion = ResumeProfilePart4.getReligion();
                Toast.makeText(getApplicationContext(), religion, Toast.LENGTH_SHORT).show();
            }
        });

        int nationalityPosition = arrayAdapterNationality.getPosition(nationality);
        spinner_Nationality.setSelection(nationalityPosition);
        spinner_Nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nationality = spinner_Nationality.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adLoad();
    }

    private void CheckValidity(){

        fullname = editText_FullName.getText().toString().trim();
        fathername = editText_FatherName.getText().toString().trim();
        mothername = editText_MotherName.getText().toString().trim();
        presentaddress = editText_PresentAddress.getText().toString().trim();
        permanentaddress = editText_PermanentAddress.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        motherName = editText_MotherName.getText().toString().trim();
        fatherName = editText_FatherName.getText().toString().trim();
        profileSummary = etSummary.getText().toString().trim();
        currentJobTittle = etCurrenJobTittle.getText().toString().trim();
        birthdate = textView_BirthDate.getText().toString();

        if (fullname.isEmpty()){
            editText_FullName.setError("ENTER FULL NAME");
            editText_FullName.requestFocus();
            return;
        }
        if (fathername.isEmpty()){
            editText_FatherName.setError("ENTER FATHER NAME");
            editText_FatherName.requestFocus();
            return;
        }
        if (mothername.isEmpty()){
            editText_MotherName.setError("ENTER MOTHER NAME");
            editText_MotherName.requestFocus();
            return;
        }
        if (gender.compareTo("Select") == 0){
            textView_Gender.setError("SELECT A GENDER!");
            textView_Gender.requestFocus();
            return;
        }
        if (birthdate.compareTo("Select Date") == 0){
            textView_BD.setError("SELECT A BIRTHDATE");
            textView_BD.requestFocus();
            return;
        }
        if (maritalstatus.compareTo("Select") == 0){
            textView_MaritalStatus.setError("SELECT A MARITAL STATUS");
            textView_MaritalStatus.requestFocus();
            return;
        }
        if (nationality.compareTo("Select") == 0){
            textView_Nationality.setError("SELECT A NATIONALITY!");
            textView_Nationality.requestFocus();
            return;
        }

        if (religion.compareTo("Select") == 0){
            textView_Religion.setError("SELECT A RELIGION!");
            textView_Religion.requestFocus();
            return;
        }
        if (presentaddress.isEmpty()){
            editText_PresentAddress.setError("ENTER PRESENT ADDRESS!");
            editText_PresentAddress.requestFocus();
            return;
        }
        if (permanentaddress.isEmpty()){
            editText_PermanentAddress.setError("ENTER PERMANENT ADDRESS");
            editText_PermanentAddress.requestFocus();
            return;
        }

        if(id != null)
        {
            updateInfoStore();
        }
        else {
            infoStore();
        }
//        GoToNextIntent();
    }

    private void SaveData(String name, String father_name, String mother_name, String gender, String birth_date, String marital_status, String nationality, String religion, String present_address, String permanent_address){
        ResumeProfilePart4.setName(name);
        ResumeProfilePart4.setFather_name(father_name);
        ResumeProfilePart4.setMother_name(mother_name);
        ResumeProfilePart4.setGender(gender);
        ResumeProfilePart4.setBirth_date(birth_date);
        ResumeProfilePart4.setMarital_status(marital_status);
        ResumeProfilePart4.setNationality(nationality);
        ResumeProfilePart4.setReligion(religion);
        ResumeProfilePart4.setPresent_address(present_address);
        ResumeProfilePart4.setPermanent_address(permanent_address);
    }

    @Override
    public void onClick(View view) {

        if (view == btn_gallery){
            openGallery();
        }
        if(view == tvDataSave)
        {
            CheckValidity();
        }
    }

    private void clearResumeProfilePart4Memory(){
        ResumeProfilePart4.setName("");
        ResumeProfilePart4.setFather_name("");
        ResumeProfilePart4.setMother_name("");
        ResumeProfilePart4.setGender("");
        ResumeProfilePart4.setBirth_date("");
        ResumeProfilePart4.setMarital_status("");
        ResumeProfilePart4.setNationality("");
        ResumeProfilePart4.setReligion("");
        ResumeProfilePart4.setPresent_address("");
        ResumeProfilePart4.setPermanent_address("");
    }


    public void infoStore()
    {
        progrssBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, infoUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true"))
                            {
                                progrssBar.setVisibility(View.GONE);
//                                adShow();
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            progrssBar.setVisibility(View.GONE);
                            Toast.makeText(BuildResumePart4.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart4.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name",fullname);
                params.put("image",encodeImageString);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("present_address",presentaddress);
                params.put("permanent_address",permanentaddress);
                params.put("job_title",currentJobTittle);
                params.put("marital_status",maritalstatus);
                params.put("religion",religion);
                params.put("nationality",nationality);
                params.put("gender",gender);
                params.put("dob",birthdate);
                params.put("father_name",fatherName);
                params.put("mother_name",motherName);
                params.put("profile_summary",profileSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void updateInfoStore()
    {
        progrssBar.setVisibility(View.VISIBLE);
//        Toast.makeText(getApplicationContext(), religion, Toast.LENGTH_SHORT).show();

        StringRequest request = new StringRequest(Request.Method.POST, updateInfoUrl+id,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true"))
                            {
                                progrssBar.setVisibility(View.GONE);
//                                adShow();
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            progrssBar.setVisibility(View.GONE);
                            Toast.makeText(BuildResumePart4.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart4.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name",fullname);
                params.put("image",encodeImageString);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("present_address",presentaddress);
                params.put("permanent_address",permanentaddress);
                params.put("job_title",currentJobTittle);
                params.put("marital_status",maritalstatus);
                params.put("religion",religion);
                params.put("nationality",nationality);
                params.put("gender",gender);
                params.put("dob",birthdate);
                params.put("father_name",fatherName);
                params.put("mother_name",motherName);
                params.put("profile_summary",profileSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void openGallery() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionExplanation();
            } else if (!PermissionsUtil.getInstance(this).checkReadExternalStoragePermissionPreference()) {
                PermissionsUtil.getInstance(this).updateReadExternalStoragePermissionPreference();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
            } else {
                Toast.makeText(this, "Please Allow Read External Storage Permission", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        } else {
            takeImageFromGallery();
        }

    }

    private void takeImageFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
    }

    private void showPermissionExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Read External Storage Permission Needed");
        builder.setMessage("CV Master needs to access your Gallery to pick a photo of yours. So please give permission to Read External Storage.");
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(BuildResumePart4.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, filePath, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String myPath = cursor.getString(columnIndex);
                cursor.close();


                String path = ResumeProfilePart1.getImagePath();
                //added part start

                float abs_width = 0.0f;
                float abs_height = 0.0f;
                try {

                    Image image = Image.getInstance(myPath);
                    abs_width = image.getPlainWidth();
                    abs_height = image.getPlainHeight();
                    //Toast.makeText(this,"Height: "+abs_height+" Width: "+abs_width,Toast.LENGTH_LONG).show();

                } catch (BadElementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(this,"Height: "+abs_height+" Width: "+abs_width,Toast.LENGTH_LONG).show();
                //abs_height > 600 || abs_width > 500
                if (abs_height > 6000 || abs_width > 5000) {
                    Toast.makeText(this, "Please choose Passport Size Photo!", Toast.LENGTH_LONG).show();
                    return;
                }

                //added part end

                ResumeProfilePart1.setUri(uri);
                currentPhotoPath = myPath;
                File file = new File(currentPhotoPath);
                imvProfile.setImageURI(Uri.fromFile(file));

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    encodeBitmapImage(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    // Admob


    private void adLoad()
    {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-7854798461578735/4913309588",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        progrssBar.setVisibility(View.GONE);
//                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }

    private void adShow()
    {
        if (mRewardedAd != null) {
            Activity activityContext = BuildResumePart4.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    /*int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();*/

                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        } else {
//            Toast.makeText(getApplicationContext(), "Please wait, your connection is slow", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }
}
