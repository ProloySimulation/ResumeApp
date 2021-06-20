package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.model.Skills_Model;

import java.util.Calendar;

public class BuildResumePart4 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editText_FullName;
    private EditText editText_FatherName;
    private EditText editText_MotherName;

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



    private Button button_Next;
    private Button button_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part4);

        clearResumeProfilePart4Memory();

        button_Data = (Button) findViewById(R.id.button_BuildResumePart4_Data);
        button_Data.setOnClickListener(this);

        editText_FullName = (EditText) findViewById(R.id.editText_BuildResumePart4_FullName);
        editText_FatherName = (EditText) findViewById(R.id.editText_BuildResumePart4_FatherName);
        editText_MotherName = (EditText) findViewById(R.id.editText_BuildResumePart4_MotherName);

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
        String fullname = null;
        String fathername = null;
        String mothername = null;
        String gender = null;
        String birthdate = null;
        String maritalstatus = null;
        String nationality = null;
        String religion = null;
        String presentaddress = null;
        String permanentaddress = null;

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

        GoToNextIntent();

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

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT!");
        builder.setMessage("Do You Want To Exit From Make Resume?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToHomeIntent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
