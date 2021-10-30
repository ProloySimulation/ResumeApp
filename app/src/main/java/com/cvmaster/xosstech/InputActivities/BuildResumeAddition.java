package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Additional_Infos;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BuildResumeAddition extends AppCompatActivity implements View.OnClickListener {

    private EditText etSkills,etHobbies,etLanguages,etLinkedin,etBehance,etGithub,etTwitter;
    private Button btnSubmit ;
    private String skills = null;
    private String hobbies = null;
    private String languages = null;
    private String linkedin = null;
    private String behance = null;
    private String github = null;
    private String twitter = null;
    private String id = null ;
    private String token = null;
    private int update = 0 ;
    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/addition";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/addition/update/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_addition);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        etSkills = findViewById(R.id.editText_BuildResumeAdditiona_Skills);
        etHobbies = findViewById(R.id.editText_BuildResumeAdditional_Hobbies);
        etLanguages = findViewById(R.id.editText_BuildResumeSkills_Language);
        etLinkedin = findViewById(R.id.editText_BuildResumeSkills_Linkedin);
        etBehance = findViewById(R.id.editText_BuildResumeSkills_Behance);
        etGithub = findViewById(R.id.editText_BuildResumeSkills_Github);
        etTwitter = findViewById(R.id.editText_BuildResumeSkills_Twitter);
        btnSubmit = findViewById(R.id.btn_Submit);

        if(Additional_Infos.getSkills().isEmpty())
        {
            update = 100;
        }

        etSkills.setText(Additional_Infos.getSkills());
        etHobbies.setText(Additional_Infos.getHobbies());
        etLanguages.setText(Additional_Infos.getLanguages());
        etGithub.setText(Additional_Infos.getGithub());
        etLinkedin.setText(Additional_Infos.getLinkedin());
        etTwitter.setText(Additional_Infos.getTwitter());
        etBehance.setText(Additional_Infos.getBehance());
        id = Additional_Infos.getId();
        Toast.makeText(getApplicationContext(), Additional_Infos.getSkills(), Toast.LENGTH_SHORT).show();

        btnSubmit.setOnClickListener(this);

    }

    private void CheckValidity(){

        skills = etSkills.getText().toString().trim();
        hobbies = etHobbies.getText().toString().trim();
        languages = etLanguages.getText().toString().trim();
        linkedin = etLinkedin.getText().toString().trim();
        behance = etBehance.getText().toString().trim();
        github = etGithub.getText().toString().trim();
        twitter = etTwitter.getText().toString().trim();


        if (skills.isEmpty()){
            etSkills.setError("ENTER SKILLS");
            etSkills.requestFocus();
            return;
        }
        if (hobbies.isEmpty()){
            etHobbies.setError("ENTER HOBBIES");
            etHobbies.requestFocus();
            return;
        }
        if (languages.isEmpty()){
            etLanguages.setError("ENTER LANGUAGE");
            etLanguages.requestFocus();
            return;
        }

        else {
            checkUpdateOrUpload();
        }

    }

    private void checkUpdateOrUpload()
    {
        if(update == 100)
        {
            UploadInformation();
        }

        else {
            UpdateInformation();
        }
    }

    private void UploadInformation() {

        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumeAddition.this, "Data Input Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumeAddition.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeAddition.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"74|wJmvBNnBScsOoD3uKXiNHn2ad9lSlyLLyOAdxaP6");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("skills", skills);
                params.put("hobby", hobbies);
                params.put("language",languages);
                params.put("linkedin", linkedin);
                params.put("github", github);
                params.put("twitter", twitter);
                params.put("behance", behance);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void UpdateInformation() {

        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumeAddition.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumeAddition.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumeAddition.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"74|wJmvBNnBScsOoD3uKXiNHn2ad9lSlyLLyOAdxaP6");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("skills", skills);
                params.put("hobby", hobbies);
                params.put("language",languages);
                params.put("linkedin", linkedin);
                params.put("github", github);
                params.put("twitter", twitter);
                params.put("behance", behance);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSubmit)
        {
            CheckValidity();
        }
    }
}