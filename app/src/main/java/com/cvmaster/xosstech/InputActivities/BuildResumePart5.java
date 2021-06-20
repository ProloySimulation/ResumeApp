package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;

public class BuildResumePart5 extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout_AddReference;
    private Button button_AddReference;

    private LinearLayout linearLayout_Reference_1;
    private EditText editText_Name_1;
    private EditText editText_Designation_1;
    private EditText editText_OrganizationName_1;
    private EditText editText_Email_1;
    private EditText editText_MobileNumber_1;
    private Button button_AddField_1;
    private Button button_DeleteField_1;

    private LinearLayout linearLayout_Reference_2;
    private EditText editText_Name_2;
    private EditText editText_Designation_2;
    private EditText editText_OrganizationName_2;
    private EditText editText_Email_2;
    private EditText editText_MobileNumber_2;
    private Button button_DeleteField_2;

    private Button button_Skip;
    private Button button_Next;

    private Button button_Data;


    private static final int STOREGE_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part5);

        clearResumeProfilePart5Memory();

        linearLayout_AddReference = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_5_AddReference);
        button_AddReference = (Button) findViewById(R.id.button_BuildResumePart5_AddReference);
        button_AddReference.setOnClickListener(this);

        linearLayout_Reference_1 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart5_Reference_1);
        editText_Name_1 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference1_Name);
        editText_OrganizationName_1 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference1_OrganizationName);
        editText_Designation_1 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference1_Designation);
        editText_MobileNumber_1 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference1_MobileNumber);
        editText_Email_1 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference1_Email);
        button_AddField_1 = (Button) findViewById(R.id.button_BuildResumePart5_AddField_1);
        button_AddField_1.setOnClickListener(this);
        button_DeleteField_1 = (Button) findViewById(R.id.button_BuildResumePart5_DeleteField_1);
        button_DeleteField_1.setOnClickListener(this);

        linearLayout_Reference_2 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart5_Reference_2);
        editText_Name_2 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference2_Name);
        editText_OrganizationName_2 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference2_OrganizationName);
        editText_Designation_2 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference2_Designation);
        editText_MobileNumber_2 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference2_MobileNumber);
        editText_Email_2 = (EditText) findViewById(R.id.editText_BuildResumePart5_Reference2_Email);
        button_DeleteField_2 = (Button) findViewById(R.id.button_BuildResumePart5_DeleteField_2);
        button_DeleteField_2.setOnClickListener(this);

        button_Skip = (Button) findViewById(R.id.button_BuildResumePart5_Skip);
        button_Skip.setOnClickListener(this);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart5_Next);
        button_Next.setOnClickListener(this);

        button_Data = (Button) findViewById(R.id.button_BuildResumePart5_Data);
        button_Data.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == button_AddReference){
            linearLayout_AddReference.setVisibility(View.GONE);
            linearLayout_Reference_1.setVisibility(View.VISIBLE);
        }
        if (view == button_DeleteField_1){
            linearLayout_Reference_1.setVisibility(View.GONE);
            linearLayout_AddReference.setVisibility(View.VISIBLE);
        }
        if (view == button_AddField_1){
            if (CheckValidity_Reference_1()){
                button_AddField_1.setVisibility(View.GONE);
                button_DeleteField_1.setVisibility(View.GONE);
                linearLayout_Reference_2.setVisibility(View.VISIBLE);
            }
        }
        if (view == button_DeleteField_2){
            linearLayout_Reference_2.setVisibility(View.GONE);
            button_AddField_1.setVisibility(View.VISIBLE);
            button_DeleteField_1.setVisibility(View.GONE);
        }
        if (view == button_Skip){
            GoToNextIntent();
        }
        if (view == button_Next){
            CheckValidity_Final();


        }
        if (view == button_Data){
            ShowData();
        }
    }

    private void CheckReadOrWriteStoragePermission(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            if(checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,STOREGE_CODE);
            }
            else {
                //permission already granted...write pdf method
                CheckValidity_Final();

            }
        }
        else {
            //permission already granted...write pdf method
            CheckValidity_Final();

        }
    }

    private boolean CheckValidity_Reference_1(){
        String name;
        String organization_name;
        String designation;
        String mobile_number;
        String email;

        name = editText_Name_1.getText().toString().trim();
        organization_name = editText_OrganizationName_1.getText().toString().trim();
        designation = editText_Designation_1.getText().toString().trim();
        mobile_number = editText_MobileNumber_1.getText().toString().trim();
        email = editText_Email_1.getText().toString().trim();

        if (name.isEmpty()){
            editText_Name_1.setError("ENTER NAME!");
            editText_Name_1.requestFocus();
            return false;
        }
        if (designation.isEmpty()){
            editText_Designation_1.setError("ENTER DESIGNATION!");
            editText_Designation_1.requestFocus();
            return false;
        }
        if (organization_name.isEmpty()){
            editText_OrganizationName_1.setError("ENTER THE ORGANIZATION NAME!");
            editText_OrganizationName_1.requestFocus();
            return false;
        }
        if (email.isEmpty()){
            editText_Email_1.setError("ENTER EMAIL ADDRESS!");
            editText_Email_1.requestFocus();
            return false;
        }
        if (mobile_number.isEmpty()){
            editText_MobileNumber_1.setError("ENTER MOBILE NUMBER!");
            editText_MobileNumber_1.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckValidity_Reference_2(){
        String name;
        String organization_name;
        String designation;
        String mobile_number;
        String email;

        name = editText_Name_2.getText().toString().trim();
        organization_name = editText_OrganizationName_2.getText().toString().trim();
        designation = editText_Designation_2.getText().toString().trim();
        mobile_number = editText_MobileNumber_2.getText().toString().trim();
        email = editText_Email_2.getText().toString().trim();

        if (name.isEmpty()){
            editText_Name_2.setError("ENTER NAME!");
            editText_Name_2.requestFocus();
            return false;
        }
        if (designation.isEmpty()){
            editText_Designation_2.setError("ENTER DESIGNATION!");
            editText_Designation_2.requestFocus();
            return false;
        }
        if (organization_name.isEmpty()){
            editText_OrganizationName_2.setError("ENTER THE ORGANIZATION NAME!");
            editText_OrganizationName_2.requestFocus();
            return false;
        }
        if (email.isEmpty()){
            editText_Email_2.setError("ENTER EMAIL ADDRESS!");
            editText_Email_2.requestFocus();
            return false;
        }
        if (mobile_number.isEmpty()){
            editText_MobileNumber_2.setError("ENTER MOBILE NUMBER!");
            editText_MobileNumber_2.requestFocus();
            return false;
        }
        return true;
    }

    private void SaveData(){
        if (linearLayout_Reference_1.getVisibility() == View.VISIBLE){
            String name;
            String organization_name;
            String designation;
            String mobile_number;
            String email;

            name = editText_Name_1.getText().toString().trim();
            organization_name = editText_OrganizationName_1.getText().toString().trim();
            designation = editText_Designation_1.getText().toString().trim();
            mobile_number = editText_MobileNumber_1.getText().toString().trim();
            email = editText_Email_1.getText().toString().trim();

            Reference_Model reference_model = new Reference_Model();
            reference_model.setName(name);
            reference_model.setOrganization_name(organization_name);
            reference_model.setDesignation(designation);
            reference_model.setMobile_number(mobile_number);
            reference_model.setEmail(email);
            ResumeProfilePart5.reference.add(reference_model);
        }
        if (linearLayout_Reference_2.getVisibility() == View.VISIBLE){
            String name;
            String organization_name;
            String designation;
            String mobile_number;
            String email;

            name = editText_Name_2.getText().toString().trim();
            organization_name = editText_OrganizationName_2.getText().toString().trim();
            designation = editText_Designation_2.getText().toString().trim();
            mobile_number = editText_MobileNumber_2.getText().toString().trim();
            email = editText_Email_2.getText().toString().trim();

            Reference_Model reference_model = new Reference_Model();
            reference_model.setName(name);
            reference_model.setOrganization_name(organization_name);
            reference_model.setDesignation(designation);
            reference_model.setMobile_number(mobile_number);
            reference_model.setEmail(email);
            ResumeProfilePart5.reference.add(reference_model);
        }
    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart7.class);
        startActivity(intent);
    }

    private void ShowData(){
        WorkExperience_Model workExperience_model;
        for (int i = 0; i< ResumeProfilePart6.workExperience.size(); i++){
            workExperience_model = ResumeProfilePart6.workExperience.get(i);
            Log.d("BuildResumePart6_Data: ",workExperience_model.getDesignationName());
            Log.d("BuildResumePart6_Data: ",workExperience_model.getDurationTime());
            Log.d("BuildResumePart6_Data: ",workExperience_model.getOrganizationName());
            Log.d("BuildResumePart6_Data: ",workExperience_model.getOgganizationAddress());
        }
    }

    private boolean CheckValidity_Final(){
        if (linearLayout_Reference_1.getVisibility() == View.GONE && linearLayout_Reference_2.getVisibility() == View.GONE){
            GoToNextIntent();
            return true;
        }
        if (linearLayout_Reference_1.getVisibility() == View.VISIBLE && linearLayout_Reference_2.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1()){
                SaveData();
                GoToNextIntent();
                return true;
            }
        }
        if (linearLayout_Reference_2.getVisibility() == View.VISIBLE && linearLayout_Reference_2.getVisibility() == View.VISIBLE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2()){
                SaveData();
                GoToNextIntent();
                return true;
            }
        }
        return false;
    }

    private void clearResumeProfilePart5Memory(){
        ResumeProfilePart5.reference.clear();
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

}
