package com.cvmaster.xosstech.InputActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
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
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.UserSignInPart2;
import com.cvmaster.xosstech.model.Skills_Model;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

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

    private EditText editText_FullName,editText_MotherName,editText_FatherName,etMobile,etEmail;

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

    private EditText etMotherName, etFatherName ;

    private Button button_Next;
    private Button button_Data,btn_gallery;

    private String infoUrl = "http://xosstech.com/cvm/api/public/api/info";

    private String fullname = null;
    private String fathername = null;
    private String mothername = null;
    private String email = null;
    private String mobile = null;
    private String gender = null;
    private String birthdate = null;
    private String maritalstatus = null;
    private String fatherName = null;
    private String motherName = null;
    private String nationality = null;
    private String religion = null;
    private String presentaddress = null;
    private String permanentaddress = null;
    private String encodeImageString = null;
    private String currentPhotoPath,token;
    private ImageView imvProfile ;
    private Bitmap bitmap;

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;
    private final int GALLERY_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part4);

        clearResumeProfilePart4Memory();

        button_Data = (Button) findViewById(R.id.button_BuildResumePart4_Data);
        button_Data.setOnClickListener(this);

        btn_gallery = findViewById(R.id.button_BuildResumePart4_SelectFromGallery);
        btn_gallery.setOnClickListener(this);

        editText_FullName = (EditText) findViewById(R.id.editText_BuildResumePart4_FullName);
        editText_FatherName = (EditText) findViewById(R.id.editText_BuildResumePart4_FatherName);
        editText_MotherName = (EditText) findViewById(R.id.editText_BuildResumePart4_MotherName);
        etMobile = findViewById(R.id.editText_BuildResumePart4_mobile);
        etEmail = findViewById(R.id.editText_BuildResumePart4_email);
        imvProfile = findViewById(R.id.imageView_BuildResumePart1_Image);
        button_Data = findViewById(R.id.button_BuildResumePart1_SelectFromGallery);

        textView_Gender = (TextView) findViewById(R.id.textView_BuildResumePart4_Gender);
        spinner_Gender = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Gender);
        ArrayAdapter<CharSequence> arrayAdapterGender = ArrayAdapter.createFromResource(this,R.array.gender_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Gender.setAdapter(arrayAdapterGender);
        spinner_Gender.setOnItemSelectedListener(this);

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
        spinner_MaritalStatus.setOnItemSelectedListener(this);

        textView_Nationality = (TextView) findViewById(R.id.textView_BuildResumePart4_Nationality);
        spinner_Nationality = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Nationality);
        ArrayAdapter<CharSequence> arrayAdapterNationality = ArrayAdapter.createFromResource(this,R.array.nationality_Names,android.R.layout.simple_spinner_item);
        arrayAdapterNationality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Nationality.setAdapter(arrayAdapterNationality);
        spinner_Nationality.setOnItemSelectedListener(this);

        textView_Religion = (TextView) findViewById(R.id.textView_BuildResumePart4_Religion);
        spinner_Religion = (Spinner) findViewById(R.id.spinner_BuildResumePart4_Religion);
        ArrayAdapter<CharSequence> arrayAdapterReligion = ArrayAdapter.createFromResource(this,R.array.religion_Names,android.R.layout.simple_spinner_item);
        arrayAdapterReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Religion.setAdapter(arrayAdapterReligion);
        spinner_Religion.setOnItemSelectedListener(this);

        editText_PresentAddress = (EditText) findViewById(R.id.editText_BuildResumePart4_PresentAddress);
        editText_PermanentAddress = (EditText) findViewById(R.id.editText_BuildResumePart4_PermanentAddress);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart4_Next);
        button_Next.setOnClickListener(this);

        //Polulate Data
        /*
        editText_FullName.setText("Mah Dian Drovo");
        editText_FatherName.setText("Father Name");
        editText_MotherName.setText("Mother Name");
        spinner_Gender.setSelection(1);
        textView_BirthDate.setText("31.12.1996");
        spinner_MaritalStatus.setSelection(2);
        spinner_Religion.setSelection(1);
        editText_PresentAddress.setText("My Present Address");
        editText_PermanentAddress.setText("My Permanment Address");
        */



    }

    private void CheckValidity(){

        fullname = editText_FullName.getText().toString().trim();
        fathername = editText_FatherName.getText().toString().trim();
        mothername = editText_MotherName.getText().toString().trim();
        gender = spinner_Gender.getSelectedItem().toString().trim();
        birthdate = textView_BirthDate.getText().toString().trim();
        maritalstatus = spinner_MaritalStatus.getSelectedItem().toString().trim();
        nationality = spinner_Nationality.getSelectedItem().toString().trim();
        religion = spinner_Religion.getSelectedItem().toString().trim();
        presentaddress = editText_PresentAddress.getText().toString().trim();
        permanentaddress = editText_PermanentAddress.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        motherName = editText_MotherName.getText().toString().trim();
        fatherName = editText_FatherName.getText().toString().trim();
        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();


        if (fullname.isEmpty()){
            editText_FullName.setError("ENTER FULL NAME");
            editText_FullName.requestFocus();
            return;
        }
        if (fathername.isEmpty()){
            editText_FatherName.setError("ENTER FATHER NAMDE");
            editText_FatherName.requestFocus();
            return;
        }
        if (mothername.isEmpty()){
            editText_MotherName.setError("ENTER MOTHER NAME");
            editText_MotherName.requestFocus();
            return;
        }
        if (gender.compareTo("Select") == 0){
            textView_Gender.setError("SELECR A GENDER!");
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

        SaveData(fullname,fathername,mothername,gender,birthdate,maritalstatus,nationality,religion,presentaddress,permanentaddress);
        infoStore();
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

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart6.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == button_Next){
            CheckValidity();
        }
        if (view == button_Data){
            ShowData();
        }
        if (view == btn_gallery){
            openGallery();
        }
    }

    private void ShowData(){
        for (int i = 0; i< ResumeProfilePart3.skills.size(); i++){
            Skills_Model skills_model;
            skills_model = ResumeProfilePart3.skills.get(i);
            Log.d("BuildResumePart3_Data",skills_model.getSkill());
        }
        Log.d("BuildResumePart3_Data",ResumeProfilePart3.bangla_skill_level);
        Log.d("BuildResumePart3_Data",ResumeProfilePart3.english_skill_level);
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
        StringRequest request = new StringRequest(Request.Method.POST, infoUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true"))
                            {
                                Toast.makeText(BuildResumePart4.this,"Data Input Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
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
                params.put("Authorization", "Bearer "+"73|0zxBcVO1MOhwZO6KNYdy1drjK11aZMfyXT8naLhn");
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
                params.put("job_title","Software");
                params.put("marital_status",maritalstatus);
                params.put("religion",religion);
                params.put("nationality",nationality);
                params.put("gender",gender);
                params.put("dob",birthdate);
                params.put("father_name",fatherName);
                params.put("mother_name",motherName);
                params.put("profile_summary","Hello");

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

    private void goToHomeIntent(){
        finish();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
