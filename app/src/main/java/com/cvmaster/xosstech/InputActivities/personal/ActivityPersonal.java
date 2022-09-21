package com.cvmaster.xosstech.InputActivities.personal;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.PermissionsUtil;
import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.PersonalInformation;
import com.cvmaster.xosstech.InputActivities.personal.viewModel.PersonalInfoViewModel;
import com.cvmaster.xosstech.model.Suggestion;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.github.ybq.android.spinkit.SpinKitView;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPersonal extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editText_FullName,editText_MotherName,editText_FatherName,etMobile,etEmail,etCurrenJobTittle;
    private AutoCompleteTextView etSummary;
    private SpinKitView progrssBar ;

    private TextView textView_Gender;
    private Spinner spinner_Gender;

    private TextView textView_BD;
    private TextView textView_BirthDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private TextView textView_MaritalStatus;
    private Spinner spinner_MaritalStatus;

    private TextView textView_Nationality;
    private Spinner spinner_Nationality;

    private TextView textView_Religion;
    private Spinner spinner_Religion;

    private EditText editText_PresentAddress;
    private EditText editText_PermanentAddress;

    private Button btn_gallery,btnSave,btnUpdate;

    private String fullname = null;
    private String fatherName = null;
    private String motherName = null;
    private String email = null;
    private String mobile = null;
    private String gender = "Select";
    private String birthdate = null;
    private String maritalstatus = "Select";
    private String nationality = "Select";
    private String religion = "Select";
    private String presentaddress = null;
    private String permanentaddress = null;
    private String encodeImageString = null;
    private String profileSummary = null;
    private String currentJobTittle = null;
    private int id;
    private String currentPhotoPath,token,userMobile,userId;
    private ImageView imvProfile,imvBack ;
    private Bitmap bitmap;
    private PersonalInfoViewModel mainViewModel;
    private final ArrayList<String> suggestions = new ArrayList<String>();

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;
    private final int GALLERY_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part4);

        init();

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityPersonal.this,
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
                birthdate = day+"-"+month+"-"+year;
                textView_BD.setText(birthdate);
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

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getPersonalInfos();
        getSuggestion();
    }

    private void init() {
        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        userMobile = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();
        encodeImageString = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserImage();
        userId = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserId();

        mainViewModel = new ViewModelProvider(this).get(PersonalInfoViewModel.class);

        progrssBar = findViewById(R.id.spin_kit);
        btn_gallery = findViewById(R.id.button_BuildResumePart4_SelectFromGallery);
        btn_gallery.setOnClickListener(this);
        btnSave = findViewById(R.id.personaInfoSave);
        btnUpdate = findViewById(R.id.personalInfoUpdate);
        imvBack = findViewById(R.id.imvPersonalBack);

        editText_FullName = (EditText) findViewById(R.id.editText_BuildResumePart4_FullName);
        editText_FatherName = (EditText) findViewById(R.id.editText_BuildResumePart4_FatherName);
        editText_MotherName = (EditText) findViewById(R.id.editText_BuildResumePart4_MotherName);
        etMobile = findViewById(R.id.editText_BuildResumePart4_mobile);
        etEmail = findViewById(R.id.editText_BuildResumePart4_email);
        imvProfile = findViewById(R.id.imageView_BuildResumePart1_Image);
        etSummary = findViewById(R.id.editText_BuildResumePart4_Summary);
        etCurrenJobTittle = findViewById(R.id.editText_BuildResumePart4_Tittle);
    }

    private boolean CheckValidity(){

        fullname = editText_FullName.getText().toString().trim();
        fatherName = editText_FatherName.getText().toString().trim();
        motherName = editText_MotherName.getText().toString().trim();
        presentaddress = editText_PresentAddress.getText().toString().trim();
        permanentaddress = editText_PermanentAddress.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        profileSummary = etSummary.getText().toString().trim();
        currentJobTittle = etCurrenJobTittle.getText().toString().trim();

        if (fullname.isEmpty()){
            editText_FullName.setError("ENTER FULL NAME");
            editText_FullName.requestFocus();
            return false;
        }
        if (fatherName.isEmpty()){
            editText_FatherName.setError("ENTER FATHER NAME");
            editText_FatherName.requestFocus();
            return false;
        }
        if (motherName.isEmpty()){
            editText_MotherName.setError("ENTER MOTHER NAME");
            editText_MotherName.requestFocus();
            return false;
        }
        if (gender.compareTo("Select") == 0){
            textView_Gender.setError("SELECT A GENDER!");
            textView_Gender.requestFocus();
            return false;
        }
        if (birthdate.isEmpty()){
            textView_BD.setError("SELECT A BIRTHDATE");
            textView_BD.requestFocus();
            return false;
        }
        if (maritalstatus.isEmpty()){
            textView_MaritalStatus.setError("SELECT A MARITAL STATUS");
            textView_MaritalStatus.requestFocus();
            return false;
        }
        if (nationality.isEmpty()){
            textView_Nationality.setError("SELECT A NATIONALITY!");
            textView_Nationality.requestFocus();
            return false;
        }

        if (religion.isEmpty()){
            textView_Religion.setError("SELECT A RELIGION!");
            textView_Religion.requestFocus();
            return false;
        }
        if (presentaddress.isEmpty()){
            editText_PresentAddress.setError("ENTER PRESENT ADDRESS!");
            editText_PresentAddress.requestFocus();
            return false;
        }
        if (permanentaddress.isEmpty()){
            editText_PermanentAddress.setError("ENTER PERMANENT ADDRESS");
            editText_PermanentAddress.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        if (view == btn_gallery){
            openGallery();
        }
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
                ActivityCompat.requestPermissions(ActivityPersonal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
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

                //added part start

                float abs_width = 0.0f;
                float abs_height = 0.0f;
                try {

                    com.itextpdf.text.Image image = Image.getInstance(myPath);
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

    public void postPersonalInfo(View view) {

        if(CheckValidity())
        {
            PersonalInformation information = new PersonalInformation(fullname,encodeImageString,mobile,email,
                    presentaddress,permanentaddress,currentJobTittle,maritalstatus,religion,nationality,gender,birthdate,
                    profileSummary,fatherName,motherName,1);

            SharedPreferenceManager.getInstance(ActivityPersonal.this).UserLoggedInfo(userMobile,userId,token,encodeImageString);

            mainViewModel.postAllInfo(token,information).observe(this, new Observer<List<PersonalInformation>>() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onChanged(@Nullable List<PersonalInformation> informationList) {
                    int position = 1;
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    mainViewModel.getAllInfos(token).removeObserver(this);
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    public void getPersonalInfos() {

        mainViewModel.getAllInfos(token).observe(this, new Observer<List<PersonalInformation>>() {
            @Override
            public void onChanged(@Nullable List<PersonalInformation> informationList) {

                if(informationList.size()<=0)
                {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    btnSave.setVisibility(View.GONE);
                    btnUpdate.setVisibility(View.VISIBLE);

                    fullname = informationList.get(0).getName();
                    motherName = informationList.get(0).getMother_name();
                    fatherName = informationList.get(0).getFather_name();
                    maritalstatus = informationList.get(0).getMarital_status();
                    birthdate = informationList.get(0).getDob();
                    profileSummary = informationList.get(0).getProfile_summary();
                    email = informationList.get(0).getEmail();
                    mobile = informationList.get(0).getMobile();
                    nationality = informationList.get(0).getNationality();
                    gender = informationList.get(0).getGender();
                    String imageUrl = informationList.get(0).getImage();
                    religion = informationList.get(0).getReligion();
                    presentaddress = informationList.get(0).getPresent_address();
                    permanentaddress = informationList.get(0).getPermanent_address();
                    currentJobTittle = informationList.get(0).getJob_title();

                    id = informationList.get(0).getId();
                    editText_FullName.setText(fullname);
                    editText_FatherName.setText(fatherName);
                    editText_MotherName.setText(motherName);
                    etMobile.setText(mobile);
                    etEmail.setText(email);
                    textView_BD.setText(birthdate);
//                textView_BirthDate.setText(birthdate);
                    textView_MaritalStatus.setText(maritalstatus);
                    textView_Religion.setText(religion);
                    textView_Gender.setText(gender);
                    textView_Nationality.setText(nationality);
                    etCurrenJobTittle.setText(currentJobTittle);
                    etSummary.setText(profileSummary);
                    editText_PermanentAddress.setText(permanentaddress);
                    editText_PresentAddress.setText(presentaddress);
                    Picasso.with(getApplicationContext()).load(imageUrl).into(imvProfile);
                }
//                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    public void updateInfo(View view)
    {

        if(CheckValidity())
        {

            PersonalInformation information = new PersonalInformation(fullname,encodeImageString,mobile,email,
                    presentaddress,permanentaddress,currentJobTittle,maritalstatus,religion,nationality,gender,birthdate,
                    profileSummary,fatherName,motherName,1);

            SharedPreferenceManager.getInstance(ActivityPersonal.this).UserLoggedInfo(userMobile,userId,token,encodeImageString);

            mainViewModel.updateInfo(token,id,information).observe(this, new Observer<List<PersonalInformation>>() {
                @Override
                public void onChanged(@Nullable List<PersonalInformation> informationList) {
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    private void getSuggestion()
    {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<Suggestion>> call = service.getEducationSuggestion("summary");
        call.enqueue(new Callback<List<Suggestion>>() {

            @Override
            public void onResponse(Call<List<Suggestion>> call, Response<List<Suggestion>> response) {

                List<Suggestion> suggestionList = response.body();
                if(suggestionList != null)
                {
                    for(int i = 0;i<suggestionList.size();i++)
                    {
                        String summary = suggestionList.get(i).getProfileSummary();
                        suggestions.add(summary);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_dropdown_item_1line,suggestions);
                    etSummary.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Suggestion>> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}