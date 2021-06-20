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
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.model.WorkExperience_Model;

public class EditResumePart6 extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout_AddWorkExperience_1;
    private EditText editText_Designation_1;
    private EditText editText_Duration_1;
    private EditText editText_Organization_1;
    private EditText editText_OrganizationAddress_1;
    private Button button_AddField_1;
    private Button button_DeleteField_1;

    private LinearLayout linearLayout_AddWorkExperience_2;
    private EditText editText_Designation_2;
    private EditText editText_Duration_2;
    private EditText editText_Organization_2;
    private EditText editText_OrganizationAddress_2;
    private Button button_DeleteField_2;

    private Button button_Skip;
    private Button button_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part6);

        linearLayout_AddWorkExperience_1 = (LinearLayout) findViewById(R.id.linearlayout_EditResumePart6_WorkExperience_1);
        editText_Designation_1 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience1_Designation);
        editText_Duration_1 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience1_Duration);
        editText_Organization_1 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience1_OrganizationName);
        editText_OrganizationAddress_1 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience1_OrganizationAddress);

        button_AddField_1 = (Button) findViewById(R.id.button_EditResumePart6_AddField_1);
        button_AddField_1.setOnClickListener(this);
        button_DeleteField_1 = (Button) findViewById(R.id.button_EditResumePart6_DeleteField_1);
        button_DeleteField_1.setOnClickListener(this);

        linearLayout_AddWorkExperience_2 = (LinearLayout) findViewById(R.id.linearlayout_EditResumePart6_WorkExperience_2);
        editText_Designation_2 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience2_Designation);
        editText_Duration_2 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience2_Duration);
        editText_Organization_2 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience2_OrganizationName);
        editText_OrganizationAddress_2 = (EditText) findViewById(R.id.editText_EditResumePart6_WorkExperience2_OrganizationAddress);

        button_DeleteField_2 = (Button) findViewById(R.id.button_EditResumePart6_DeleteField_2);
        button_DeleteField_2.setOnClickListener(this);

        button_Skip = (Button) findViewById(R.id.button_EditResumePart6_Skip);
        button_Skip.setOnClickListener(this);

        button_Update = (Button) findViewById(R.id.button_EditResumePart6_Update);
        button_Update.setOnClickListener(this);

        if (ResumeProfilePart6.workExperience.size()>0){

            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(0);

            editText_Designation_1.setText(workExperience_model.getDesignationName());
            editText_Duration_1.setText(workExperience_model.getDurationTime());
            editText_Organization_1.setText(workExperience_model.getOrganizationName());
            editText_OrganizationAddress_1.setText(workExperience_model.getOgganizationAddress());
        }

        if (ResumeProfilePart6.workExperience.size()>1){
            button_AddField_1.setVisibility(View.GONE);
            linearLayout_AddWorkExperience_2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(1);

            editText_Designation_2.setText(workExperience_model.getDesignationName());
            editText_Duration_2.setText(workExperience_model.getDurationTime());
            editText_Organization_2.setText(workExperience_model.getOrganizationName());
            editText_OrganizationAddress_2.setText(workExperience_model.getOgganizationAddress());
        }

    }

    @Override
    public void onClick(View view) {
        if (view == button_Update){
            clearResumeProfilePart6Memory();
            CheckValidity_Final();
        }
        if (view == button_Skip){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("SKIP!");
            builder.setMessage("If you skip you saved Work Experiece will be discard. Are you agree?");
            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    clearResumeProfilePart6Memory();
                    GoToNextIntent();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (view == button_AddField_1){
            if (CheckValidity_WorkExperience_1()){
                button_AddField_1.setVisibility(View.GONE);
                button_DeleteField_1.setVisibility(View.GONE);
                linearLayout_AddWorkExperience_2.setVisibility(View.VISIBLE);
            }
        }
        if (view == button_DeleteField_2){
            linearLayout_AddWorkExperience_2.setVisibility(View.GONE);
            button_AddField_1.setVisibility(View.VISIBLE);
        }
    }

    private void clearResumeProfilePart6Memory(){
        ResumeProfilePart6.workExperience.clear();
    }

    public boolean CheckValidity_WorkExperience_1(){
        String designation;
        String duration;
        String organization;
        String organization_address;

        designation = editText_Designation_1.getText().toString().trim();
        duration = editText_Duration_1.getText().toString().trim();
        organization = editText_Organization_1.getText().toString().trim();
        organization_address = editText_OrganizationAddress_1.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_1.setError("ENTER DESIGNATION!");
            editText_Designation_1.requestFocus();
            return false;
        }
        if (duration.isEmpty()){
            editText_Duration_1.setError("ENTER DURATION!");
            editText_Duration_1.requestFocus();
            return false;
        }
        if (organization.isEmpty()){
            editText_Organization_1.setError("ENTER ORGANIZATION NAME!");
            editText_Organization_1.requestFocus();
            return false;
        }
        if (organization_address.isEmpty()){
            editText_OrganizationAddress_1.setError("ENTER ORGANIZATION NAME!");
            editText_OrganizationAddress_1.requestFocus();
            return false;
        }
        return true;
    }

    public boolean CheckValidity_WorkExperience_2(){
        String designation;
        String duration;
        String organization;
        String organization_address;

        designation = editText_Designation_2.getText().toString().trim();
        duration = editText_Duration_2.getText().toString().trim();
        organization = editText_Organization_2.getText().toString().trim();
        organization_address = editText_OrganizationAddress_2.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_2.setError("ENTER DESIGNATION!");
            editText_Designation_2.requestFocus();
            return false;
        }
        if (duration.isEmpty()){
            editText_Duration_2.setError("ENTER DURATION!");
            editText_Duration_2.requestFocus();
            return false;
        }
        if (organization.isEmpty()){
            editText_Organization_2.setError("ENTER ORGANIZATION NAME!");
            editText_Organization_2.requestFocus();
            return false;
        }
        if (organization_address.isEmpty()){
            editText_OrganizationAddress_2.setError("ENTER ORGANIZATION NAME!");
            editText_OrganizationAddress_2.requestFocus();
            return false;
        }
        return true;
    }

    public void SaveData(){
        if (linearLayout_AddWorkExperience_1.getVisibility() == View.VISIBLE){
            String designation;
            String duration;
            String organization;
            String organization_address;

            designation = editText_Designation_1.getText().toString().trim();
            duration = editText_Duration_1.getText().toString().trim();
            organization = editText_Organization_1.getText().toString().trim();
            organization_address = editText_OrganizationAddress_1.getText().toString().trim();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }
        if (linearLayout_AddWorkExperience_2.getVisibility() == View.VISIBLE){
            String designation;
            String duration;
            String organization;
            String organization_address;

            designation = editText_Designation_2.getText().toString().trim();
            duration = editText_Duration_2.getText().toString().trim();
            organization = editText_Organization_2.getText().toString().trim();
            organization_address = editText_OrganizationAddress_2.getText().toString().trim();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }
    }

    private boolean CheckValidity_Final(){
        if (linearLayout_AddWorkExperience_1.getVisibility() == View.GONE && linearLayout_AddWorkExperience_2.getVisibility() == View.GONE){
            GoToNextIntent();
            return true;
        }
        if (linearLayout_AddWorkExperience_1.getVisibility() == View.VISIBLE && linearLayout_AddWorkExperience_2.getVisibility() == View.GONE){
            if (CheckValidity_WorkExperience_1()){
                SaveData();
                GoToNextIntent();
                return true;
            }
        }
        if (linearLayout_AddWorkExperience_1.getVisibility() == View.VISIBLE && linearLayout_AddWorkExperience_2.getVisibility() == View.VISIBLE){
            if (CheckValidity_WorkExperience_1() && CheckValidity_WorkExperience_2()){
                SaveData();
                GoToNextIntent();
                return true;
            }
        }
        return false;
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
