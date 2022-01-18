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
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Reference_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Button button_Data;
    private String updateId1 = null;
    private String updateId2 = null;

    private TextView tvRefSave ;

    private String token = null;
    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/reference";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/reference/update/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part5);

//        clearResumeProfilePart5Memory();
        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        tvRefSave = findViewById(R.id.tvRefDataSave);
        tvRefSave.setOnClickListener(this);

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

        button_Data = (Button) findViewById(R.id.button_BuildResumePart5_Data);
        button_Data.setOnClickListener(this);

        List<Reference_Model> reference_model = ResumeProfilePart5.reference;

        if (ResumeProfilePart5.reference.size() > 0){
            Reference_Model reference_mod = reference_model.get(0);
            editText_Name_1.setText(reference_mod.getName());
            editText_Designation_1.setText(reference_mod.getDesignation());
            editText_OrganizationName_1.setText(reference_mod.getOrganization_name());
            editText_Email_1.setText(reference_mod.getEmail());
            editText_MobileNumber_1.setText(reference_mod.getMobile_number());
            updateId1 = reference_mod.getId();
        }

        if (ResumeProfilePart5.reference.size() > 1){
            linearLayout_Reference_2.setVisibility(View.VISIBLE);
            Reference_Model reference_mod = reference_model.get(1);
            editText_Name_2.setText(reference_mod.getName());
            editText_Designation_2.setText(reference_mod.getDesignation());
            editText_OrganizationName_2.setText(reference_mod.getOrganization_name());
            editText_Email_2.setText(reference_mod.getEmail());
            editText_MobileNumber_2.setText(reference_mod.getMobile_number());
            updateId2 = reference_mod.getId();
        }

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

        if (view == button_Data){
        }

        if (view == tvRefSave)
        {
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

            if (updateId1!=null)
            {
                updateInformation(name,designation,organization_name,email,mobile_number,updateId1);
            }

            else  {
                UploadInformation(name,designation,organization_name,email,mobile_number);
            }

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

            if (updateId2 != null)
            {
                updateInformation(name,designation,organization_name,email,mobile_number,updateId2);
            }

            else  {
                UploadInformation(name,designation,organization_name,email,mobile_number);
            }
        }
    }

    private boolean CheckValidity_Final(){
        if (linearLayout_Reference_1.getVisibility() == View.GONE && linearLayout_Reference_2.getVisibility() == View.GONE){
//            GoToNextIntent();
            return true;
        }
        if (linearLayout_Reference_1.getVisibility() == View.VISIBLE && linearLayout_Reference_2.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1()){
                SaveData();
//                GoToNextIntent();
                return true;
            }
        }
        if (linearLayout_Reference_2.getVisibility() == View.VISIBLE && linearLayout_Reference_2.getVisibility() == View.VISIBLE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2()){
                SaveData();
//                GoToNextIntent();
                return true;
            }
        }
        return false;
    }

    private void UploadInformation(String name,String designation,String organization,String email,String mobile) {

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
                            Toast.makeText(BuildResumePart5.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart5.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("designation", designation);
                params.put("organization",organization);
                params.put("email", email);
                params.put("mobile", mobile);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void updateInformation(String name,String designation,String organization,String email,String mobile,String updateId)
    {
        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+updateId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumePart5.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart5.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart5.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("designation", designation);
                params.put("organization",organization);
                params.put("email", email);
                params.put("mobile", mobile);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

}