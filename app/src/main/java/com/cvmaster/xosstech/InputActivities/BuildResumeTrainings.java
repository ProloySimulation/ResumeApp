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
import com.cvmaster.xosstech.ResumeProfileProjects;
import com.cvmaster.xosstech.ResumeProfileTrainings;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Projects_model;
import com.cvmaster.xosstech.model.Training;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildResumeTrainings extends AppCompatActivity implements View.OnClickListener {

    private EditText etTrainingName1 , etTrainingName2 , etTrainingName3;
    private EditText etEndDate1 , etEndDate2 , etEndDate3 ;
    private EditText etSummary1 , etSummary2 , etSummary3 ;

    private LinearLayout layout1 , layout2 , layout3 ;

    private Button btnAddField1,btnAddField2,btnDeleteFiled2,btnDeleteField3;
    private TextView tvTrainingSave ;

    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/training";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/training/update/";
    private String id1 = null;
    private String id2 = null;
    private String id3 = null;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_trainings);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        tvTrainingSave = findViewById(R.id.tvTrainingSave);
        tvTrainingSave.setOnClickListener(this);

        etTrainingName1 = findViewById(R.id.editText_BuildResumeTraining_name_1);
        etTrainingName2 = findViewById(R.id.editText_BuildResumeTraining_name_2);
        etTrainingName3 = findViewById(R.id.editText_BuildResumeTraining_name_3);

        etEndDate1 = findViewById(R.id.editText_BuildResumeTraining_endDate_1);
        etEndDate2 = findViewById(R.id.editText_BuildResumeTraining_endDate_2);
        etEndDate3 = findViewById(R.id.editText_BuildResumeTraining_endDate_3);

        etSummary1 = findViewById(R.id.editText_BuildResumeTraining_Summary_1);
        etSummary2 = findViewById(R.id.editText_BuildResumeTraining_Summary_2);
        etSummary3 = findViewById(R.id.editText_BuildResumeTraining_Summary_3);

        layout1 = findViewById(R.id.training_Layout_1);
        layout2 = findViewById(R.id.training_Layout_2);
        layout3 = findViewById(R.id.training_Layout_3);

        btnAddField1 = findViewById(R.id.button_BuildResumeProject_AddField_1);
        btnAddField2 = findViewById(R.id.button_BuildResumeProject_AddField_2);
        btnDeleteField3 = findViewById(R.id.button_BuildResumeTraining_DeleteField_3);
        btnDeleteFiled2 = findViewById(R.id.button_BuildResumeTraining_DeleteField_2);

        btnDeleteFiled2.setOnClickListener(this);
        btnAddField1.setOnClickListener(this);
        btnAddField2.setOnClickListener(this);
        btnDeleteField3.setOnClickListener(this);

        List<Training> training_model = ResumeProfileTrainings.trainingList;

        if(ResumeProfileTrainings.trainingList.size()>0)
        {
            Training training = training_model.get(0);
            etTrainingName1.setText(training.getTrainingName());
            etEndDate1.setText(training.getEndDate());
            etSummary1.setText(training.getTrainingSummary());
            id1 = training.getId();
        }

        if(ResumeProfileTrainings.trainingList.size()>1)
        {
            layout2.setVisibility(View.VISIBLE);
            btnAddField1.setVisibility(View.GONE);
            Training training = training_model.get(1);
            etTrainingName1.setText(training.getTrainingName());
            etEndDate1.setText(training.getEndDate());
            etSummary1.setText(training.getTrainingSummary());
            id1 = training.getId();
        }

        if(ResumeProfileTrainings.trainingList.size()>2)
        {
            layout3.setVisibility(View.VISIBLE);
            btnAddField2.setVisibility(View.GONE);
            Training training = training_model.get(2);
            etTrainingName1.setText(training.getTrainingName());
            etEndDate1.setText(training.getEndDate());
            etSummary1.setText(training.getTrainingSummary());
            id1 = training.getId();
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
                btnDeleteField3.setVisibility(View.VISIBLE);
                btnAddField2.setVisibility(View.GONE);
            }
        }

        if(view == btnDeleteFiled2)
        {
            btnAddField1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }

        if(view == btnDeleteField3)
        {
            btnAddField2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.GONE);
        }

        if(view == tvTrainingSave)
        {
            CheckValidity_Final();
        }
    }

    private void saveData()
    {
        if(layout1.getVisibility() == View.VISIBLE) {

            String trainingName,endDate,trainingSummary ;

            trainingName = etTrainingName1.getText().toString();
            endDate = etEndDate1.getText().toString();
            trainingSummary = etSummary1.getText().toString();

            if(id1!=null)
            {
                UpdateProjects(id1,trainingName,endDate,trainingSummary);
            }
            else {
                UploadProjects(trainingName,endDate,trainingSummary);
            }

        }

        if(layout2.getVisibility() == View.VISIBLE) {

            String trainingName,endDate,trainingSummary ;

            trainingName = etTrainingName2.getText().toString();
            endDate = etEndDate2.getText().toString();
            trainingSummary = etSummary2.getText().toString();

            if(id2!=null)
            {
                UpdateProjects(id2,trainingName,endDate,trainingSummary);
            }
            else {
                UploadProjects(trainingName,endDate,trainingSummary);
            }
        }

        if(layout3.getVisibility() == View.VISIBLE) {

            String trainingName,endDate,trainingSummary ;

            trainingName = etTrainingName3.getText().toString();
            endDate = etEndDate3.getText().toString();
            trainingSummary = etSummary3.getText().toString();

            if(id3!=null)
            {
                UpdateProjects(id3,trainingName,endDate,trainingSummary);
            }
            else {
                UploadProjects(trainingName,endDate,trainingSummary);
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

        projectName = etTrainingName1.getText().toString().trim();
        endDate = etEndDate1.getText().toString().trim();
        projectSummary = etSummary1.getText().toString().trim();

        if (projectName.isEmpty()){
            etTrainingName1.setError("SELECT PROJECT NAME!");
            etTrainingName1.requestFocus();
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

        projectName = etTrainingName2.getText().toString().trim();
        endDate = etEndDate2.getText().toString().trim();
        projectSummary = etSummary2.getText().toString().trim();

        if (projectName.isEmpty()){
            etTrainingName2.setError("SELECT PROJECT NAME!");
            etTrainingName2.requestFocus();
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

        projectName = etTrainingName3.getText().toString().trim();
        endDate = etEndDate3.getText().toString().trim();
        projectSummary = etSummary3.getText().toString().trim();

        if (projectName.isEmpty()){
            etTrainingName3.setError("SELECT PROJECT NAME!");
            etTrainingName3.requestFocus();
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

    private void UploadProjects(String trainingName,String endDate,String projectSummary)
    {
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
                            Toast.makeText(BuildResumeTrainings.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeTrainings.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("training_name", trainingName);
                params.put("end",endDate);
                params.put("training_summary", projectSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void UpdateProjects(String id,String trainingName,String endDate,String trainingSummary)
    {
        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+id,
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
                            Toast.makeText(BuildResumeTrainings.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeTrainings.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("project_name", trainingName);
                params.put("end",endDate);
                params.put("project_summary", trainingSummary);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}