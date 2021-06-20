package com.cvmaster.xosstech.edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cvmaster.xosstech.InputActivities.BuildResumePart7;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart4;

import java.util.Calendar;

public class EditResumePart4 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

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



    private Button button_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part4);

        editText_FullName = (EditText) findViewById(R.id.editText_EditResumePart4_FullName);
        editText_FatherName = (EditText) findViewById(R.id.editText_EditResumePart4_FatherName);
        editText_MotherName = (EditText) findViewById(R.id.editText_EditResumePart4_MotherName);

        textView_Gender = (TextView) findViewById(R.id.textView_EditResumePart4_Gender);
        spinner_Gender = (Spinner) findViewById(R.id.spinner_EditResumePart4_Gender);
        ArrayAdapter<CharSequence> arrayAdapterGender = ArrayAdapter.createFromResource(this,R.array.gender_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Gender.setAdapter(arrayAdapterGender);
        spinner_Gender.setOnItemSelectedListener(this);

        textView_BD = (TextView) findViewById(R.id.textView_EditResumePart4_BD);
        textView_BirthDate = (TextView) findViewById(R.id.textView_EditResumePart4_BirthDate);
        textView_BirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditResumePart4.this,
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



        textView_MaritalStatus = (TextView) findViewById(R.id.textView_EditResumePart4_MaritialStatus);
        spinner_MaritalStatus = (Spinner) findViewById(R.id.spinner_EditResumePart4_MaritialStatus);
        ArrayAdapter<CharSequence> arrayAdapterMaritalstatus = ArrayAdapter.createFromResource(this,R.array.marital_Status_Names,android.R.layout.simple_spinner_item);
        arrayAdapterMaritalstatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_MaritalStatus.setAdapter(arrayAdapterMaritalstatus);
        spinner_MaritalStatus.setOnItemSelectedListener(this);

        textView_Nationality = (TextView) findViewById(R.id.textView_EditResumePart4_Nationality);
        spinner_Nationality = (Spinner) findViewById(R.id.spinner_EditResumePart4_Nationality);
        ArrayAdapter<CharSequence> arrayAdapterNationality = ArrayAdapter.createFromResource(this,R.array.nationality_Names,android.R.layout.simple_spinner_item);
        arrayAdapterNationality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Nationality.setAdapter(arrayAdapterNationality);
        spinner_Nationality.setOnItemSelectedListener(this);

        textView_Religion = (TextView) findViewById(R.id.textView_EditResumePart4_Religion);
        spinner_Religion = (Spinner) findViewById(R.id.spinner_EditResumePart4_Religion);
        ArrayAdapter<CharSequence> arrayAdapterReligion = ArrayAdapter.createFromResource(this,R.array.religion_Names,android.R.layout.simple_spinner_item);
        arrayAdapterReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Religion.setAdapter(arrayAdapterReligion);
        spinner_Religion.setOnItemSelectedListener(this);

        editText_PresentAddress = (EditText) findViewById(R.id.editText_EditResumePart4_PresentAddress);
        editText_PermanentAddress = (EditText) findViewById(R.id.editText_EditResumePart4_PermanentAddress);

        button_Update = (Button) findViewById(R.id.button_EditResumePart4_Next);
        button_Update.setOnClickListener(this);


        editText_FullName.setText(ResumeProfilePart4.getName());
        editText_FatherName.setText(ResumeProfilePart4.getFather_name());
        editText_MotherName.setText(ResumeProfilePart4.getMother_name());
        spinner_Gender.setSelection(((ArrayAdapter<CharSequence>)spinner_Gender.getAdapter()).getPosition(ResumeProfilePart4.getGender()));
        textView_BirthDate.setText(ResumeProfilePart4.getBirth_date());
        spinner_MaritalStatus.setSelection(((ArrayAdapter<CharSequence>)spinner_MaritalStatus.getAdapter()).getPosition(ResumeProfilePart4.getMarital_status()));
        spinner_Nationality.setSelection(((ArrayAdapter<CharSequence>)spinner_Nationality.getAdapter()).getPosition(ResumeProfilePart4.getNationality()));
        spinner_Religion.setSelection(((ArrayAdapter<CharSequence>)spinner_Religion.getAdapter()).getPosition(ResumeProfilePart4.getReligion()));
        editText_PresentAddress.setText(ResumeProfilePart4.getPresent_address());
        editText_PermanentAddress.setText(ResumeProfilePart4.getPermanent_address());

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
        clearResumeProfilePart4Memory();
        SaveData(fullname,fathername,mothername,gender,birthdate,maritalstatus,nationality,religion,presentaddress,permanentaddress);

        GoToNextIntent();

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
        Intent intent = new Intent(getApplicationContext(), BuildResumePart7.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == button_Update){
            CheckValidity();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GO BACK WITHOUT UPDATING DATA!");
        builder.setMessage("If you press Yes data will not be up updated. Are you agree?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToBuildResumePart7Intent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void goToBuildResumePart7Intent(){
        finish();
        Intent intent = new Intent(this, BuildResumePart7.class);
        startActivity(intent);
    }
}
