package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cvmaster.xosstech.InputActivities.BuildResumePart5;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.model.WorkExperience_Model;

public class BuildResumePart6 extends AppCompatActivity implements View.OnClickListener {

    private Button button_AddWorkExperience;

    private LinearLayout linearLayout_AddWorkExperience_1;
    private EditText editText_Designation_1;
    private EditText editText_Duration_1;
    private EditText editText_Organization_1;
    private EditText etWorkDetail_1;

    private EditText editText_OrganizationAddress_1;
    private Button button_AddField_1;
    private Button button_DeleteField_1;

    private LinearLayout linearLayout_AddWorkExperience_2;
    private EditText editText_Designation_2;
    private EditText editText_Duration_2;
    private EditText editText_Organization_2;
    private EditText editText_OrganizationAddress_2;
    private EditText etWorkDetail_2;
    private Button button_AddField_2;
    private Button button_DeleteField_2;

    private LinearLayout linearLayout_AddWorkExperience_3;
    private EditText editText_Designation_3;
    private EditText editText_Duration_3;
    private EditText editText_Organization_3;
    private EditText editText_OrganizationAddress_3;
    private EditText etWorkDetail_3;
    private Button button_AddField_3;
    private Button button_DeleteField_3;

    private LinearLayout linearLayout_AddWorkExperience_4;
    private EditText editText_Designation_4;
    private EditText editText_Duration_4;
    private EditText editText_Organization_4;
    private EditText editText_OrganizationAddress_4;
    private EditText etWorkDetail_4;
    private Button button_AddField_4;
    private Button button_DeleteField_4;

    private Button button_Skip;
    private Button button_Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part6);

        clearResumeProfilePart6Memory();

        button_AddWorkExperience = (Button) findViewById(R.id.button_BuildResumePart6_AddWorkExperiece);
        button_AddWorkExperience.setOnClickListener(this);

        linearLayout_AddWorkExperience_1 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_1);
        editText_Designation_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_Designation);
        editText_Duration_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_Duration);
        editText_Organization_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationName);
        editText_OrganizationAddress_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationAddress);
        etWorkDetail_1 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_1);

        button_AddField_1 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_1);
        button_AddField_1.setOnClickListener(this);
        button_DeleteField_1 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_1);
        button_DeleteField_1.setOnClickListener(this);

        linearLayout_AddWorkExperience_2 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_2);
        editText_Designation_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_Designation);
        editText_Duration_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_Duration);
        editText_Organization_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_OrganizationName);
        editText_OrganizationAddress_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_OrganizationAddress);
        etWorkDetail_2 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_2);

        button_AddField_2 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_2);
        button_AddField_2.setOnClickListener(this);
        button_DeleteField_2 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_2);
        button_DeleteField_2.setOnClickListener(this);

        linearLayout_AddWorkExperience_3 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_3);
        editText_Designation_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_Designation);
        editText_Duration_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_Duration);
        editText_Organization_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_OrganizationName);
        editText_OrganizationAddress_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_OrganizationAddress);
        etWorkDetail_3 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_3);

        button_AddField_3 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_3);
        button_AddField_3.setOnClickListener(this);
        button_DeleteField_3 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_3);
        button_DeleteField_3.setOnClickListener(this);

        linearLayout_AddWorkExperience_4 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_4);
        editText_Designation_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_Designation);
        editText_Duration_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_Duration);
        editText_Organization_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_OrganizationName);
        editText_OrganizationAddress_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_OrganizationAddress);
        etWorkDetail_4 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_4);

        button_AddField_4 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_4);
        button_AddField_4.setOnClickListener(this);
        button_DeleteField_4 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_4);
        button_DeleteField_4.setOnClickListener(this);

        button_Skip = (Button) findViewById(R.id.button_BuildResumePart6_Skip);
        button_Skip.setOnClickListener(this);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart6_Next);
        button_Next.setOnClickListener(this);

    }

    public boolean CheckValidity_WorkExperience_1(){
        String designation;
        String duration;
        String organization;
        String organization_address;
        String work_Detail ;

        designation = editText_Designation_1.getText().toString().trim();
        duration = editText_Duration_1.getText().toString().trim();
        organization = editText_Organization_1.getText().toString().trim();
        organization_address = editText_OrganizationAddress_1.getText().toString().trim();
        work_Detail = etWorkDetail_1.getText().toString().trim();


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

        if (work_Detail.isEmpty()){
            etWorkDetail_1.setError("ENTER WORK DETAIL!");
            etWorkDetail_1.requestFocus();
            return false;
        }

        return true;
    }

    public boolean CheckValidity_WorkExperience_2(){
        String designation;
        String duration;
        String organization;
        String organization_address;
        String workDetail;

        designation = editText_Designation_2.getText().toString().trim();
        duration = editText_Duration_2.getText().toString().trim();
        organization = editText_Organization_2.getText().toString().trim();
        organization_address = editText_OrganizationAddress_2.getText().toString().trim();
        workDetail = etWorkDetail_2.getText().toString().trim();

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

        if (workDetail.isEmpty()){
            etWorkDetail_2.setError("ENTER WORK DETAIL!");
            etWorkDetail_2.requestFocus();
            return false;
        }

        return true;
    }

    public boolean CheckValidity_WorkExperience_3(){
        String designation;
        String duration;
        String organization;
        String organization_address;
        String workDetail;

        designation = editText_Designation_3.getText().toString().trim();
        duration = editText_Duration_3.getText().toString().trim();
        organization = editText_Organization_3.getText().toString().trim();
        organization_address = editText_OrganizationAddress_3.getText().toString().trim();
        workDetail = etWorkDetail_3.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_3.setError("ENTER DESIGNATION!");
            editText_Designation_3.requestFocus();
            return false;
        }
        if (duration.isEmpty()){
            editText_Duration_3.setError("ENTER DURATION!");
            editText_Duration_3.requestFocus();
            return false;
        }
        if (organization.isEmpty()){
            editText_Organization_3.setError("ENTER ORGANIZATION NAME!");
            editText_Organization_3.requestFocus();
            return false;
        }
        if (organization_address.isEmpty()){
            editText_OrganizationAddress_3.setError("ENTER ORGANIZATION NAME!");
            editText_OrganizationAddress_3.requestFocus();
            return false;
        }

        if (workDetail.isEmpty()){
            etWorkDetail_3.setError("ENTER WORK DETAIL!");
            etWorkDetail_3.requestFocus();
            return false;
        }

        return true;
    }

    public boolean CheckValidity_WorkExperience_4(){
        String designation;
        String duration;
        String organization;
        String organization_address;
        String workDetail;

        designation = editText_Designation_4.getText().toString().trim();
        duration = editText_Duration_4.getText().toString().trim();
        organization = editText_Organization_4.getText().toString().trim();
        organization_address = editText_OrganizationAddress_4.getText().toString().trim();
        workDetail = etWorkDetail_4.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_4.setError("ENTER DESIGNATION!");
            editText_Designation_4.requestFocus();
            return false;
        }
        if (duration.isEmpty()){
            editText_Duration_4.setError("ENTER DURATION!");
            editText_Duration_4.requestFocus();
            return false;
        }
        if (organization.isEmpty()){
            editText_Organization_4.setError("ENTER ORGANIZATION NAME!");
            editText_Organization_4.requestFocus();
            return false;
        }
        if (organization_address.isEmpty()){
            editText_OrganizationAddress_4.setError("ENTER ORGANIZATION NAME!");
            editText_OrganizationAddress_4.requestFocus();
            return false;
        }

        if (workDetail.isEmpty()){
            etWorkDetail_4.setError("ENTER WORK DETAIL!");
            etWorkDetail_4.requestFocus();
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
            String workDetail;

            designation = editText_Designation_1.getText().toString().trim();
            duration = editText_Duration_1.getText().toString().trim();
            organization = editText_Organization_1.getText().toString().trim();
            organization_address = editText_OrganizationAddress_1.getText().toString().trim();
            workDetail = etWorkDetail_1.getText().toString().trim();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);
            workExperience_model.setworkDetail(workDetail);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }
        if (linearLayout_AddWorkExperience_2.getVisibility() == View.VISIBLE){
            String designation;
            String duration;
            String organization;
            String organization_address;
            String workDetail;

            designation = editText_Designation_2.getText().toString().trim();
            duration = editText_Duration_2.getText().toString().trim();
            organization = editText_Organization_2.getText().toString().trim();
            organization_address = editText_OrganizationAddress_2.getText().toString().trim();
            workDetail = etWorkDetail_2.getText().toString();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);
            workExperience_model.setworkDetail(workDetail);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }

        if (linearLayout_AddWorkExperience_3.getVisibility() == View.VISIBLE){
            String designation;
            String duration;
            String organization;
            String organization_address;
            String workDetail;

            designation = editText_Designation_3.getText().toString().trim();
            duration = editText_Duration_3.getText().toString().trim();
            organization = editText_Organization_3.getText().toString().trim();
            organization_address = editText_OrganizationAddress_3.getText().toString().trim();
            workDetail = etWorkDetail_3.getText().toString();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);
            workExperience_model.setworkDetail(workDetail);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }

        if (linearLayout_AddWorkExperience_4.getVisibility() == View.VISIBLE){
            String designation;
            String duration;
            String organization;
            String organization_address;
            String workDetail;

            designation = editText_Designation_4.getText().toString().trim();
            duration = editText_Duration_4.getText().toString().trim();
            organization = editText_Organization_4.getText().toString().trim();
            organization_address = editText_OrganizationAddress_4.getText().toString().trim();
            workDetail = etWorkDetail_4.getText().toString();

            WorkExperience_Model workExperience_model = new WorkExperience_Model();
            workExperience_model.setDesignationName(designation);
            workExperience_model.setDurationTime(duration);
            workExperience_model.setOrganizationName(organization);
            workExperience_model.setOgganizationAddress(organization_address);
            workExperience_model.setworkDetail(workDetail);

            ResumeProfilePart6.workExperience.add(workExperience_model);
        }
    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart5.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        if (view == button_Skip){
            GoToNextIntent();
        }
        if (view == button_Next){
            CheckValidity_Final();
        }
        if (view == button_AddWorkExperience){
            button_AddWorkExperience.setVisibility(View.GONE);
            linearLayout_AddWorkExperience_1.setVisibility(View.VISIBLE);
        }
        if (view == button_DeleteField_1){
            linearLayout_AddWorkExperience_1.setVisibility(View.GONE);
            button_AddWorkExperience.setVisibility(View.VISIBLE);
        }
        if (view == button_AddField_1){
            if (CheckValidity_WorkExperience_1()){
                button_AddField_1.setVisibility(View.GONE);
                button_DeleteField_1.setVisibility(View.GONE);
                linearLayout_AddWorkExperience_2.setVisibility(View.VISIBLE);
            }
        }
        if (view == button_AddField_2){
            if (CheckValidity_WorkExperience_2()){
                button_AddField_2.setVisibility(View.GONE);
                button_DeleteField_2.setVisibility(View.GONE);
                linearLayout_AddWorkExperience_3.setVisibility(View.VISIBLE);
            }
        }
        if (view == button_DeleteField_2){
            linearLayout_AddWorkExperience_2.setVisibility(View.GONE);
            button_AddField_1.setVisibility(View.VISIBLE);
            button_DeleteField_1.setVisibility(View.GONE);
        }
        if (view == button_AddField_3){
            if (CheckValidity_WorkExperience_3()){
                button_AddField_3.setVisibility(View.GONE);
                button_DeleteField_3.setVisibility(View.GONE);
                linearLayout_AddWorkExperience_4.setVisibility(View.VISIBLE);
            }
        }
        if (view == button_DeleteField_3){
            linearLayout_AddWorkExperience_3.setVisibility(View.GONE);
            button_AddField_2.setVisibility(View.VISIBLE);
            button_DeleteField_2.setVisibility(View.GONE);
        }
        if (view == button_AddField_4){
            if (CheckValidity_WorkExperience_4()){
                button_AddField_4.setVisibility(View.GONE);
                button_DeleteField_4.setVisibility(View.GONE);
            }
        }
        if (view == button_DeleteField_4){
            linearLayout_AddWorkExperience_4.setVisibility(View.GONE);
            button_AddField_3.setVisibility(View.VISIBLE);
            button_DeleteField_3.setVisibility(View.GONE);
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

    private void clearResumeProfilePart6Memory(){
        ResumeProfilePart6.workExperience.clear();
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

    private void ShowData(){
        Log.d("BuildResumePart4_Data", ResumeProfilePart4.getName());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getFather_name());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getMother_name());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getGender());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getBirth_date());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getMarital_status());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getNationality());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getReligion());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getPresent_address());
        Log.d("BuildResumePart4_Data",ResumeProfilePart4.getPermanent_address());
    }
}
