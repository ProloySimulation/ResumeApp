package com.cvmaster.xosstech.edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cvmaster.xosstech.InputActivities.BuildResumePart7;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.ResumeProfilePart5;

public class EditResumePart5 extends AppCompatActivity implements View.OnClickListener {

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
    private Button button_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part5);

        linearLayout_Reference_1 = (LinearLayout) findViewById(R.id.linearlayout_EditResumePart5_Reference_1);
        editText_Name_1 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference1_Name);
        editText_OrganizationName_1 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference1_OrganizationName);
        editText_Designation_1 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference1_Designation);
        editText_MobileNumber_1 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference1_MobileNumber);
        editText_Email_1 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference1_Email);
        button_AddField_1 = (Button) findViewById(R.id.button_EditResumePart5_AddField_1);
        button_AddField_1.setOnClickListener(this);
        button_DeleteField_1 = (Button) findViewById(R.id.button_EditResumePart5_DeleteField_1);
        button_DeleteField_1.setOnClickListener(this);

        linearLayout_Reference_2 = (LinearLayout) findViewById(R.id.linearlayout_EditResumePart5_Reference_2);
        editText_Name_2 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference2_Name);
        editText_OrganizationName_2 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference2_OrganizationName);
        editText_Designation_2 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference2_Designation);
        editText_MobileNumber_2 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference2_MobileNumber);
        editText_Email_2 = (EditText) findViewById(R.id.editText_EditResumePart5_Reference2_Email);
        button_DeleteField_2 = (Button) findViewById(R.id.button_EditResumePart5_DeleteField_2);
        button_DeleteField_2.setOnClickListener(this);

        button_Skip = (Button) findViewById(R.id.button_EditResumePart5_Skip);
        button_Skip.setOnClickListener(this);

        button_Update = (Button) findViewById(R.id.button_EditResumePart5_Update);
        button_Update.setOnClickListener(this);

        if (ResumeProfilePart5.reference.size()>0){
            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(0);

            editText_Name_1.setText(reference_model.getName());
            editText_OrganizationName_1.setText(reference_model.getOrganization_name());
            editText_Designation_1.setText(reference_model.getDesignation());
            editText_MobileNumber_1.setText(reference_model.getMobile_number());
            editText_Email_1.setText(reference_model.getEmail());
        }
        if (ResumeProfilePart5.reference.size()>1){
            button_AddField_1.setVisibility(View.GONE);
            linearLayout_Reference_2.setVisibility(View.VISIBLE);

            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(1);

            editText_Name_2.setText(reference_model.getName());
            editText_OrganizationName_2.setText(reference_model.getOrganization_name());
            editText_Designation_2.setText(reference_model.getDesignation());
            editText_MobileNumber_2.setText(reference_model.getMobile_number());
            editText_Email_2.setText(reference_model.getEmail());

        }

    }

    @Override
    public void onClick(View view) {
        if (view == button_Update){
            clearResumeProfilePart5Memory();

            CheckValidity_Final();

        }
        if (view == button_Skip){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("SKIP!");
            builder.setMessage("If you skip you saved References will be discard. Are you agree?");
            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    clearResumeProfilePart5Memory();
                    GoToNextIntent();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
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

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart7.class);
        startActivity(intent);
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
