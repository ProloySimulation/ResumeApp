package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildResumePart6 extends AppCompatActivity implements View.OnClickListener {

    private Button button_AddWorkExperience;

    private LinearLayout linearLayout_AddWorkExperience_1;
    private EditText editText_Designation_1;
    private EditText editText_Organization_1;
    private EditText editText_startDate_1;
    private EditText editText_endDate_1;
    private EditText etWorkDetail_1;

    private EditText editText_OrganizationAddress_1;
    private Button button_AddField_1;
    private Button button_DeleteField_1;

    private LinearLayout linearLayout_AddWorkExperience_2;
    private EditText editText_Designation_2;
    private EditText editText_startDate_2;
    private EditText editText_endDate_2;
    private EditText editText_Organization_2;
    private EditText editText_OrganizationAddress_2;
    private EditText etWorkDetail_2;
    private Button button_AddField_2;
    private Button button_DeleteField_2;

    private LinearLayout linearLayout_AddWorkExperience_3;
    private EditText editText_Designation_3;
    private EditText editText_startDate_3;
    private EditText editText_endDate_3;
    private EditText editText_Organization_3;
    private EditText editText_OrganizationAddress_3;
    private EditText etWorkDetail_3;
    private Button button_AddField_3;
    private Button button_DeleteField_3;

    private LinearLayout linearLayout_AddWorkExperience_4;
    private EditText editText_Designation_4;
    private EditText editText_startDate_4;
    private EditText editText_endDate_4;
    private EditText editText_Organization_4;
    private EditText editText_OrganizationAddress_4;
    private EditText etWorkDetail_4;
    private Button button_DeleteField_4;

    private String token = null;
    private String updateId1 = null;
    private String updateId2 = null;
    private String updateId3 = null;
    private String updateId4 = null;
    private TextView tvWorkDataSave ;

    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/experience";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/experience/update/";
    private String deleteUrl = "http://xosstech.com/cvm/api/public/api/experience/delete/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part6);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        tvWorkDataSave = findViewById(R.id.tvExpDataSave);
        tvWorkDataSave.setOnClickListener(this);

        button_AddWorkExperience = (Button) findViewById(R.id.button_BuildResumePart6_AddWorkExperiece);
        button_AddWorkExperience.setOnClickListener(this);

        linearLayout_AddWorkExperience_1 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_1);
        editText_Designation_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_Designation);
        editText_Organization_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationName);
        editText_startDate_1 = findViewById(R.id.editText_BuildResumePart6_WorkExperience1_start);
        editText_endDate_1 = findViewById(R.id.editText_BuildResumePart6_WorkExperience1_end);
        editText_OrganizationAddress_1 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationAddress);
        etWorkDetail_1 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_1);

        button_AddField_1 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_1);
        button_AddField_1.setOnClickListener(this);

        button_DeleteField_1 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_1);
        button_DeleteField_1.setOnClickListener(this);

        linearLayout_AddWorkExperience_2 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_2);
        editText_Designation_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_Designation);
        editText_startDate_2 = findViewById(R.id.editText_BuildResumePart6_WorkExperience2_start);
        editText_endDate_2 = findViewById(R.id.editText_BuildResumePart6_WorkExperience2_end);
        editText_Organization_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_OrganizationName);
        editText_OrganizationAddress_2 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience2_OrganizationAddress);
        etWorkDetail_2 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_2);

        button_AddField_2 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_2);
        button_AddField_2.setOnClickListener(this);

        button_DeleteField_2 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_2);
        button_DeleteField_2.setOnClickListener(this);

        linearLayout_AddWorkExperience_3 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_3);
        editText_Designation_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_Designation);
        editText_startDate_3 = findViewById(R.id.editText_BuildResumePart6_WorkExperience3_start);
        editText_endDate_3 = findViewById(R.id.editText_BuildResumePart6_WorkExperience3_end);
        editText_Organization_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_OrganizationName);
        editText_OrganizationAddress_3 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience3_OrganizationAddress);
        etWorkDetail_3 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_3);

        button_AddField_3 = (Button) findViewById(R.id.button_BuildResumePart6_AddField_3);
        button_AddField_3.setOnClickListener(this);

        button_DeleteField_3 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_3);
        button_DeleteField_3.setOnClickListener(this);

        linearLayout_AddWorkExperience_4 = (LinearLayout) findViewById(R.id.linearlayout_BuildResumePart6_WorkExperience_4);
        editText_Designation_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_Designation);
        editText_startDate_4 = findViewById(R.id.editText_BuildResumePart6_WorkExperience4_start);
        editText_endDate_4 = findViewById(R.id.editText_BuildResumePart6_WorkExperience4_end);
        editText_Organization_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_OrganizationName);
        editText_OrganizationAddress_4 = (EditText) findViewById(R.id.editText_BuildResumePart6_WorkExperience4_OrganizationAddress);
        etWorkDetail_4 = findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_4);

        button_DeleteField_4 = (Button) findViewById(R.id.button_BuildResumePart6_DeleteField_4);
        button_DeleteField_4.setOnClickListener(this);

        List<WorkExperience_Model> exp_model = ResumeProfilePart6.workExperience;

        if (ResumeProfilePart6.workExperience.size() > 0){

            WorkExperience_Model experience_mod = exp_model.get(0);
            editText_Organization_1.setText(experience_mod.getOrganizationName());
            editText_Designation_1.setText(experience_mod.getDesignationName());
            editText_OrganizationAddress_1.setText(experience_mod.getOgganizationAddress());
            etWorkDetail_1.setText(experience_mod.getworkDetail());
            editText_startDate_1.setText(experience_mod.getStartDate());
            editText_endDate_1.setText(experience_mod.getEndDate());
            updateId1 = experience_mod.getId();
        }

        if (ResumeProfilePart6.workExperience.size() > 1){
            linearLayout_AddWorkExperience_2.setVisibility(View.VISIBLE);
            WorkExperience_Model experience_mod = exp_model.get(1);
            editText_Organization_2.setText(experience_mod.getOrganizationName());
            editText_Designation_2.setText(experience_mod.getDesignationName());
            etWorkDetail_2.setText(experience_mod.getworkDetail());
            editText_OrganizationAddress_2.setText(experience_mod.getOgganizationAddress());
            editText_startDate_2.setText(experience_mod.getStartDate());
            editText_endDate_2.setText(experience_mod.getEndDate());
            updateId2 = experience_mod.getId();
        }

        if (ResumeProfilePart6.workExperience.size() > 2){
            linearLayout_AddWorkExperience_3.setVisibility(View.VISIBLE);
            WorkExperience_Model experience_mod = exp_model.get(2);
            editText_Organization_3.setText(experience_mod.getOrganizationName());
            etWorkDetail_3.setText(experience_mod.getworkDetail());
            editText_Designation_3.setText(experience_mod.getDesignationName());
            editText_OrganizationAddress_3.setText(experience_mod.getOgganizationAddress());
            editText_startDate_3.setText(experience_mod.getStartDate());
            editText_endDate_3.setText(experience_mod.getEndDate());
            updateId3 = experience_mod.getId();
        }

        if (ResumeProfilePart6.workExperience.size() > 3){
            linearLayout_AddWorkExperience_4.setVisibility(View.VISIBLE);
            WorkExperience_Model experience_mod = exp_model.get(3);
            editText_Organization_4.setText(experience_mod.getOrganizationName());
            editText_Designation_4.setText(experience_mod.getDesignationName());
            etWorkDetail_4.setText(experience_mod.getworkDetail());
            editText_OrganizationAddress_4.setText(experience_mod.getOgganizationAddress());
            editText_startDate_4.setText(experience_mod.getStartDate());
            editText_endDate_4.setText(experience_mod.getEndDate());
            updateId4 = experience_mod.getId();
        }

    }

    public boolean CheckValidity_WorkExperience_1(){

        String designation = editText_Designation_1.getText().toString().trim();
        String startDate = editText_startDate_1.getText().toString().trim();
        String endDate = editText_endDate_1.getText().toString().trim();
        String organization = editText_Organization_1.getText().toString().trim();
        String organization_address = editText_OrganizationAddress_1.getText().toString().trim();
        String workDetail = etWorkDetail_1.getText().toString().trim();


        if (designation.isEmpty()){
            editText_Designation_1.setError("ENTER DESIGNATION!");
            editText_Designation_1.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            editText_startDate_1.setError("ENTER START DATE!");
            editText_startDate_1.requestFocus();
            return false;
        }
        if (endDate.isEmpty()){
            editText_endDate_1.setError("ENTER DURATION!");
            editText_endDate_1.requestFocus();
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

        if (workDetail.isEmpty()){
            etWorkDetail_1.setError("ENTER WORK DETAIL!");
            etWorkDetail_1.requestFocus();
            return false;
        }

        return true;
    }

    public boolean CheckValidity_WorkExperience_2(){

        String designation = editText_Designation_2.getText().toString().trim();
        String startDate = editText_startDate_2.getText().toString().trim();
        String endDate = editText_endDate_2.getText().toString().trim();
        String organization = editText_Organization_2.getText().toString().trim();
        String organization_address = editText_OrganizationAddress_2.getText().toString().trim();
        String workDetail = etWorkDetail_2.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_2.setError("ENTER DESIGNATION!");
            editText_Designation_2.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            editText_startDate_2.setError("ENTER START DATE!");
            editText_startDate_2.requestFocus();
            return false;
        }
        if (endDate.isEmpty()){
            editText_endDate_2.setError("ENTER END DATE!");
            editText_endDate_2.requestFocus();
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

        String designation = editText_Designation_3.getText().toString().trim();
        String startDate = editText_startDate_3.getText().toString().trim();
        String endDate = editText_endDate_3.getText().toString().trim();
        String organization = editText_Organization_3.getText().toString().trim();
        String organization_address = editText_OrganizationAddress_3.getText().toString().trim();
        String workDetail = etWorkDetail_3.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_3.setError("ENTER DESIGNATION!");
            editText_Designation_3.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            editText_startDate_3.setError("ENTER START DATE!");
            editText_startDate_3.requestFocus();
            return false;
        }
        if (endDate.isEmpty()){
            editText_endDate_3.setError("ENTER END DATE!");
            editText_endDate_3.requestFocus();
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

        String designation = editText_Designation_4.getText().toString().trim();
        String startDate = editText_startDate_4.getText().toString().trim();
        String endDate = editText_endDate_4.getText().toString().trim();
        String organization = editText_Organization_4.getText().toString().trim();
        String organization_address = editText_OrganizationAddress_4.getText().toString().trim();
        String workDetail = etWorkDetail_4.getText().toString().trim();

        if (designation.isEmpty()){
            editText_Designation_4.setError("ENTER DESIGNATION!");
            editText_Designation_4.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            editText_startDate_4.setError("ENTER START DATE!");
            editText_startDate_4.requestFocus();
            return false;
        }
        if (endDate.isEmpty()){
            editText_endDate_4.setError("ENTER END DATE!");
            editText_endDate_4.requestFocus();
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

            String designation = editText_Designation_1.getText().toString().trim();
            String startDate = editText_startDate_1.getText().toString().trim();
            String endDate = editText_endDate_1.getText().toString().trim();
            String organization = editText_Organization_1.getText().toString().trim();
            String organization_address = editText_OrganizationAddress_1.getText().toString().trim();
            String workDetail = etWorkDetail_1.getText().toString().trim();

            if (updateId1!=null)
            {
                updateInformation(organization,designation,startDate,endDate,workDetail,organization_address,updateId1);
            }

            else
            {
                UploadInformation(organization,designation,startDate,endDate,organization_address,workDetail);
            }
        }
        if (linearLayout_AddWorkExperience_2.getVisibility() == View.VISIBLE){

            String designation = editText_Designation_2.getText().toString().trim();
            String startDate = editText_startDate_2.getText().toString().trim();
            String endDate = editText_endDate_2.getText().toString().trim();
            String organization = editText_Organization_2.getText().toString().trim();
            String organization_address = editText_OrganizationAddress_2.getText().toString().trim();
            String workDetail = etWorkDetail_2.getText().toString();


            if (updateId2 != null)
            {
                updateInformation(organization,designation,startDate,endDate,workDetail,organization_address,updateId2);
            }

            else
            {
                UploadInformation(organization,designation,startDate,endDate,organization_address,workDetail);
            }
        }

        if (linearLayout_AddWorkExperience_3.getVisibility() == View.VISIBLE){

            String designation = editText_Designation_3.getText().toString().trim();
            String startDate = editText_startDate_3.getText().toString().trim();
            String endDate = editText_endDate_3.getText().toString().trim();
            String organization = editText_Organization_3.getText().toString().trim();
            String organization_address = editText_OrganizationAddress_3.getText().toString().trim();
            String workDetail = etWorkDetail_3.getText().toString();

            if (updateId3 != null)
            {
                updateInformation(organization,designation,startDate,endDate,workDetail,organization_address,updateId3);
            }

            else
            {
                UploadInformation(organization,designation,startDate,endDate,organization_address,workDetail);
            }

        }

        if (linearLayout_AddWorkExperience_4.getVisibility() == View.VISIBLE){

            String designation = editText_Designation_4.getText().toString().trim();
            String startDate = editText_startDate_4.getText().toString().trim();
            String endDate = editText_endDate_4.getText().toString().trim();
            String organization = editText_Organization_4.getText().toString().trim();
            String organization_address = editText_OrganizationAddress_4.getText().toString().trim();
            String workDetail = etWorkDetail_4.getText().toString();

            if (updateId4 != null)
            {
                updateInformation(organization,designation,startDate,endDate,workDetail,organization_address,updateId4);
            }

            else
            {
                UploadInformation(organization,designation,startDate,endDate,organization_address,workDetail);
            }
        }
    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart5.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {

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
            button_DeleteField_2.setVisibility(View.VISIBLE);
        }

        if (view == button_DeleteField_4){
            linearLayout_AddWorkExperience_4.setVisibility(View.GONE);
            button_AddField_3.setVisibility(View.VISIBLE);
            button_DeleteField_3.setVisibility(View.VISIBLE);
        }

        if(view == tvWorkDataSave)
        {
            CheckValidity_Final();
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
//                GoToNextIntent();
                return true;
            }
        }
        if (linearLayout_AddWorkExperience_1.getVisibility() == View.VISIBLE && linearLayout_AddWorkExperience_2.getVisibility() == View.VISIBLE){
            if (CheckValidity_WorkExperience_1() && CheckValidity_WorkExperience_2()){
                SaveData();
//                GoToNextIntent();
                return true;
            }
        }
        return false;
    }

    private void UploadInformation(String organization,String designation,String startDate,String endDate,String workAddress,String workDetail) {
        Toast.makeText(getApplicationContext(), organization, Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart6.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart6.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("company_name", organization);
                params.put("position", designation);
                params.put("start",startDate);
                params.put("end", endDate);
                params.put("address", workAddress);
                params.put("work_summary", workDetail);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void updateInformation(String organization,String designation,String startDate,String endDate,String workDetail,String wrokAddress,String updateId) {

        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+updateId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart6.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart6.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("company_name", organization);
                params.put("position", designation);
                params.put("start",startDate);
                params.put("end", endDate);
                params.put("location", wrokAddress);
                params.put("work_summary", workDetail);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void deleteInformation(String id) {

        StringRequest request = new StringRequest(Request.Method.POST, deleteUrl+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                startActivity(intent);                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart6.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart6.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

}
