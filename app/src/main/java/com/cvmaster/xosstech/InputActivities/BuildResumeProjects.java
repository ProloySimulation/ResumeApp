package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfileProjects;
import com.cvmaster.xosstech.model.Projects_model;
import com.cvmaster.xosstech.model.Reference_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildResumeProjects extends AppCompatActivity implements View.OnClickListener {

    private EditText etProjectName1 , etProjectName2 , etProjectName3;
    private EditText etStartDate1 , etStartDate2 , etStartDate3;
    private EditText etEndDate1 , etEndDate2 , etEndDate3 ;
    private EditText etSummary1 , etSummary2 , etSummary3 ;

    private LinearLayout layout1 , layout2 , layout3 ;

    private Button btnAddField1,btnAddField2;
    private TextView tvProjectsSave ;

    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/project";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/project/update/";
    private String id1 = null;
    private String id2 = null;
    private String id3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_projects);

        tvProjectsSave = findViewById(R.id.tvProjectsSave);
        tvProjectsSave.setOnClickListener(this);

        etProjectName1 = findViewById(R.id.editText_BuildResumeProjects_name_1);
        etProjectName2 = findViewById(R.id.editText_BuildResumeProjects_name_2);
        etProjectName3 = findViewById(R.id.editText_BuildResumeProjects_name_3);
        etStartDate1 = findViewById(R.id.editText_BuildResumeProjects_startDate_1);
        etStartDate2 = findViewById(R.id.editText_BuildResumeProjects_startDate_2);
        etStartDate3 = findViewById(R.id.editText_BuildResumeProjects_startDate_3);
        etEndDate1 = findViewById(R.id.editText_BuildResumeProjects_endDate_1);
        etEndDate2 = findViewById(R.id.editText_BuildResumeProjects_endDate_2);
        etEndDate3 = findViewById(R.id.editText_BuildResumeProjects_endDate_3);
        etSummary1 = findViewById(R.id.editText_BuildResumeProjects_Summary_1);
        etSummary2 = findViewById(R.id.editText_BuildResumeProjects_Summary_2);
        etSummary3 = findViewById(R.id.editText_BuildResumeProjects_Summary_3);

        layout1 = findViewById(R.id.project_Layout_1);
        layout2 = findViewById(R.id.project_Layout_2);
        layout3 = findViewById(R.id.project_Layout_3);

        btnAddField1 = findViewById(R.id.button_BuildResumeProject_AddField_1);
        btnAddField2 = findViewById(R.id.button_BuildResumeProject_AddField_2);

        btnAddField1.setOnClickListener(this);
        btnAddField2.setOnClickListener(this);

        List<Projects_model> projects_models = ResumeProfileProjects.projectsList;

        if(ResumeProfileProjects.projectsList.size()>0)
        {
            Projects_model projects_model = projects_models.get(0);
            etProjectName1.setText(projects_model.getProjectName());
            etStartDate1.setText(projects_model.getStartDate());
            etEndDate1.setText(projects_model.getEndDate());
            etSummary1.setText(projects_model.getProjectSummary());
            id1 = projects_model.getId();
        }

        if(ResumeProfileProjects.projectsList.size()>1)
        {
            layout2.setVisibility(View.VISIBLE);
            btnAddField1.setVisibility(View.GONE);
            Projects_model projects_model = projects_models.get(1);
            etProjectName2.setText(projects_model.getProjectName());
            etStartDate2.setText(projects_model.getStartDate());
            etEndDate2.setText(projects_model.getEndDate());
            etSummary2.setText(projects_model.getProjectSummary());
            id2 = projects_model.getId();
        }

        if(ResumeProfileProjects.projectsList.size()>2)
        {
            layout3.setVisibility(View.VISIBLE);
            btnAddField2.setVisibility(View.GONE);
            Projects_model projects_model = projects_models.get(2);
            etProjectName3.setText(projects_model.getProjectName());
            etStartDate3.setText(projects_model.getStartDate());
            etEndDate3.setText(projects_model.getEndDate());
            etSummary3.setText(projects_model.getProjectSummary());
            id3 = projects_model.getId();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnAddField1)
        {
            if(CheckValidity_Project_1())
            {
                layout2.setVisibility(View.VISIBLE);
                btnAddField1.setVisibility(View.GONE);
                btnAddField2.setVisibility(View.VISIBLE);
            }
        }
        if(view == btnAddField2)
        {
            if(CheckValidity_Project_1() && CheckValidity_Project_2())
            {
                layout3.setVisibility(View.VISIBLE);
                btnAddField2.setVisibility(View.GONE);
            }
        }

        if(view == tvProjectsSave)
        {
            CheckValidity_Final();
        }
    }

    private void saveData()
    {
        if(layout1.getVisibility() == View.VISIBLE) {

            String projectName,startDate,endDate,projectSummary ;

            projectName = etProjectName1.getText().toString();
            startDate = etStartDate1.getText().toString();
            endDate = etEndDate1.getText().toString();
            projectSummary = etSummary1.getText().toString();

            if(id1!=null)
            {
                UpdateProjects(id1,projectName,startDate,endDate,projectSummary);
            }
            else {
                UploadProjects(projectName,startDate,endDate,projectSummary);
            }

        }

        if(layout2.getVisibility() == View.VISIBLE) {

            String projectName,startDate,endDate,projectSummary ;

            projectName = etProjectName2.getText().toString();
            startDate = etStartDate2.getText().toString();
            endDate = etEndDate2.getText().toString();
            projectSummary = etSummary2.getText().toString();

            if(id2!=null)
            {
                UpdateProjects(id2,projectName,startDate,endDate,projectSummary);
            }
            else {
                UploadProjects(projectName,startDate,endDate,projectSummary);
            }
        }

        if(layout3.getVisibility() == View.VISIBLE) {

            String projectName,startDate,endDate,projectSummary ;

            projectName = etProjectName3.getText().toString();
            startDate = etStartDate3.getText().toString();
            endDate = etEndDate3.getText().toString();
            projectSummary = etSummary3.getText().toString();

            if(id3!=null)
            {
                UpdateProjects(id3,projectName,startDate,endDate,projectSummary);
            }
            else {
                UploadProjects(projectName,startDate,endDate,projectSummary);
            }
        }

    }

    private boolean CheckValidity_Final(){

        if (layout1.getVisibility() == View.VISIBLE && layout2.getVisibility() == View.GONE){
            if (CheckValidity_Project_1()){
                saveData();
//                GoToNextIntent();
                return true;
            }
        }

        if (layout1.getVisibility() == View.VISIBLE && layout2.getVisibility() == View.VISIBLE && layout3.getVisibility() == View.GONE){
            if (CheckValidity_Project_1() && CheckValidity_Project_2()){
                saveData();
//                GoToNextIntent();
                return true;
            }
        }

        if (layout1.getVisibility() == View.VISIBLE && layout1.getVisibility() == View.VISIBLE && layout1.getVisibility() == View.VISIBLE){
            if (CheckValidity_Project_1() && CheckValidity_Project_2() && CheckValidity_Project_3()){
                saveData();
//                GoToNextIntent();
                return true;
            }
        }

        return false;
    }

    private boolean CheckValidity_Project_1(){
        String projectName;
        String startDate;
        String endDate;
        String projectSummary;

        projectName = etProjectName1.getText().toString().trim();
        startDate = etStartDate1.getText().toString().trim();
        endDate = etEndDate1.getText().toString().trim();
        projectSummary = etSummary1.getText().toString().trim();

        if (projectName.isEmpty()){
            etProjectName1.setError("SELECT PROJECT NAME!");
            etProjectName1.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            etStartDate1.setError("ENTER START DATE!");
            etStartDate1.requestFocus();
            return false;
        }

        if (endDate.isEmpty()){
            etEndDate1.setError("ENTER END DATE!");
            etEndDate1.requestFocus();
            return false;
        }

        if (projectSummary.isEmpty()){
            etSummary1.setError("ENTER SUMMARY!");
            etSummary1.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            board_name = null;
        }*/


        return true;
    }

    private boolean CheckValidity_Project_2(){
        String projectName;
        String startDate;
        String endDate;
        String projectSummary;

        projectName = etProjectName2.getText().toString().trim();
        startDate = etStartDate2.getText().toString().trim();
        endDate = etEndDate2.getText().toString().trim();
        projectSummary = etSummary2.getText().toString().trim();

        if (projectName.isEmpty()){
            etProjectName2.setError("SELECT PROJECT NAME!");
            etProjectName2.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            etStartDate2.setError("ENTER START DATE!");
            etStartDate2.requestFocus();
            return false;
        }

        if (endDate.isEmpty()){
            etEndDate2.setError("ENTER END DATE!");
            etEndDate2.requestFocus();
            return false;
        }

        if (projectSummary.isEmpty()){
            etSummary2.setError("ENTER SUMMARY!");
            etSummary2.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            board_name = null;
        }*/


        return true;
    }

    private boolean CheckValidity_Project_3(){
        String projectName;
        String startDate;
        String endDate;
        String projectSummary;

        projectName = etProjectName3.getText().toString().trim();
        startDate = etStartDate3.getText().toString().trim();
        endDate = etEndDate3.getText().toString().trim();
        projectSummary = etSummary3.getText().toString().trim();

        if (projectName.isEmpty()){
            etProjectName3.setError("SELECT PROJECT NAME!");
            etProjectName3.requestFocus();
            return false;
        }
        if (startDate.isEmpty()){
            etStartDate3.setError("ENTER START DATE!");
            etStartDate3.requestFocus();
            return false;
        }

        if (endDate.isEmpty()){
            etEndDate3.setError("ENTER END DATE!");
            etEndDate3.requestFocus();
            return false;
        }

        if (projectSummary.isEmpty()){
            etSummary3.setError("ENTER SUMMARY!");
            etSummary3.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            board_name = null;
        }*/


        return true;
    }

    private void UploadProjects(String projectName,String startDate,String endDate,String projectSummary)
    {
        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumeProjects.this, "Data Input Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumeProjects.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeProjects.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"73|0zxBcVO1MOhwZO6KNYdy1drjK11aZMfyXT8naLhn");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("project_name", projectName);
                params.put("start", startDate);
                params.put("end",endDate);
                params.put("project_summary", projectSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void UpdateProjects(String id,String projectName,String startDate,String endDate,String projectSummary)
    {
        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumeProjects.this, "Data Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumeProjects.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeProjects.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"73|0zxBcVO1MOhwZO6KNYdy1drjK11aZMfyXT8naLhn");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("project_name", projectName);
                params.put("start", startDate);
                params.put("end",endDate);
                params.put("project_summary", projectSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}