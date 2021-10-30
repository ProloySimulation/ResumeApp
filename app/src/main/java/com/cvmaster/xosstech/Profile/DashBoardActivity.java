package com.cvmaster.xosstech.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.InputActivities.BuildResumeAddition;
import com.cvmaster.xosstech.InputActivities.BuildResumePart2;
import com.cvmaster.xosstech.InputActivities.BuildResumePart4;
import com.cvmaster.xosstech.InputActivities.BuildResumePart5;
import com.cvmaster.xosstech.InputActivities.BuildResumePart6;
import com.cvmaster.xosstech.InputActivities.BuildResumeProjects;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.ResumeProfileProjects;
import com.cvmaster.xosstech.ShowPdf;
import com.cvmaster.xosstech.model.Additional_Infos;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Projects_model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvPersonal,cvExp,cvEdu,cvReference,cvAddition,cvProjects;
    private FloatingActionButton fabCvMake ;

    private String profileUrl = "http://xosstech.com/cvm/api/public/api/profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        clearResumeProfilePart2Memory();
        clearResumeProfilePart5Memory();
        clearResumeProfilePart6Memory();
        clearResumeProjectsMemory();

        cvPersonal = findViewById(R.id.cvPersonal);
        cvExp = findViewById(R.id.cvExp);
        cvEdu = findViewById(R.id.cvEdu);
        cvReference = findViewById(R.id.cvRef);
        cvAddition = findViewById(R.id.cvAdditon);
        cvProjects = findViewById(R.id.cvProjects);
        fabCvMake = findViewById(R.id.fabCvMake);

        cvPersonal.setOnClickListener(this);
        cvExp.setOnClickListener(this);
        cvEdu.setOnClickListener(this);
        cvReference.setOnClickListener(this);
        cvAddition.setOnClickListener(this);
        cvProjects.setOnClickListener(this);
        fabCvMake.setOnClickListener(this);

        UploadInformation();
    }

    @Override
    public void onClick(View view) {

        if (view == cvPersonal)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumePart4.class);
            startActivity(intent);
        }
        if (view == cvExp)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumePart6.class);
            startActivity(intent);
        }
        if (view == cvEdu)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumePart2.class);
            startActivity(intent);
        }
        if (view == cvReference)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumePart5.class);
            startActivity(intent);
        }
        if (view == cvAddition)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumeAddition.class);
            startActivity(intent);
        }
        if (view == cvProjects)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumeProjects.class);
            startActivity(intent);
        }
        if(view == fabCvMake)
        {
            Intent intent = new Intent(getApplicationContext(), ShowPdf.class);
            startActivity(intent);
        }
    }

    private void UploadInformation() {

        StringRequest request = new StringRequest(Request.Method.POST, profileUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(DashBoardActivity.this, "Data Retrieve Successfully", Toast.LENGTH_SHORT).show();

                                JSONObject additionalObject = jsonObject.getJSONObject("additonalInfos data");
                                JSONObject educationalObject = jsonObject.getJSONObject("education data");
                                JSONObject experienceObject = jsonObject.getJSONObject("experiences data");
                                JSONObject personalObject = jsonObject.getJSONObject("personal_infos data");
                                JSONObject projectsObject = jsonObject.getJSONObject("projects data");
                                JSONObject referenceObject = jsonObject.getJSONObject("references data");

                                String referenceCount = referenceObject.getString("count");

                                if(referenceCount!=null)
                                {
                                    JSONArray infoArray = referenceObject.getJSONArray("references");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String id = infoObject.getString("id");
                                        String name = infoObject.getString("name");
                                        String designation = infoObject.getString("designation");
                                        String organization = infoObject.getString("organization");
                                        String email = infoObject.getString("email");
                                        String mobile = infoObject.getString("mobile");
                                        saveReferencedata(id,name,designation,organization,email,mobile);
                                    }
                                }

                                if(educationalObject!=null)
                                {
                                    JSONArray infoArray = educationalObject.getJSONArray("education");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String id = infoObject.getString("id");
                                        String institution = infoObject.getString("inst_name");
                                        String department = infoObject.getString("dept");
                                        String degree = infoObject.getString("degree");
                                        String passYear = infoObject.getString("pass_year");
                                        String result = infoObject.getString("result");
                                        Toast.makeText(getApplicationContext(), passYear, Toast.LENGTH_SHORT).show();
                                        saveEducationData(id,institution,degree,department,passYear,result);
                                    }
                                }

                                if(experienceObject!=null)
                                {
                                    JSONArray infoArray = experienceObject.getJSONArray("experiences");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String id = infoObject.getString("id");
                                        String orgranization = infoObject.getString("company_name");
                                        String designation = infoObject.getString("position");
                                        String startDate = infoObject.getString("start");
                                        String endDate = infoObject.getString("end");
                                        String workSummary = infoObject.getString("work_summary");
                                        String location = infoObject.getString("location");
                                        saveExperienceData(id,orgranization,designation,startDate,endDate,location,workSummary);
                                    }
                                }

                                if(additionalObject!=null)
                                {
                                    JSONArray infoArray = additionalObject.getJSONArray("additonalInfos");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String id = infoObject.getString("id");
                                        String skills = infoObject.getString("skills");
                                        String hobby = infoObject.getString("hobby");
                                        String linkedin = infoObject.getString("linkedin");
                                        String github = infoObject.getString("github");
                                        String twitter = infoObject.getString("twitter");
                                        String behance = infoObject.getString("behance");
                                        String language = infoObject.getString("language");

                                        saveAdditionalInfosData(id,skills,hobby,language,linkedin,github,twitter,behance);
                                    }
                                }

                                if(projectsObject!=null)
                                {
                                    JSONArray infoArray = projectsObject.getJSONArray("projects");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String id = infoObject.getString("id");
                                        String projectName = infoObject.getString("project_name");
                                        String startDate = infoObject.getString("start");
                                        String endDate = infoObject.getString("end");
                                        String summary = infoObject.getString("project_summary");

                                        saveProjectsData( id, projectName, startDate, endDate, summary);
                                    }
                                }

                                if(personalObject!=null)
                                {
                                    JSONArray infoArray = personalObject.getJSONArray("personal_infos");
                                    for(int j=0;j<infoArray.length();j++){

                                        JSONObject infoObject = infoArray.getJSONObject(j);
                                        String name = infoObject.getString("id");
                                        String image = infoObject.getString("project_name");
                                        String mobile = infoObject.getString("start");
                                        String presentAddress = infoObject.getString("end");
                                        String permanentAddress = infoObject.getString("project_summary");
                                        String jobTitle = infoObject.getString("project_summary");
                                        String maritalStatus = infoObject.getString("project_summary");
                                        String religion = infoObject.getString("project_summary");
                                        String nationality = infoObject.getString("project_summary");
                                        String gender = infoObject.getString("project_summary");
                                        String dob = infoObject.getString("project_summary");
                                        String profileSummary = infoObject.getString("project_summary");
                                        String fatherName = infoObject.getString("project_summary");
                                        String motherName = infoObject.getString("project_summary");

                                        savePersonalInfo(name,image,mobile,presentAddress,permanentAddress,jobTitle,maritalStatus,religion,nationality,gender,dob,profileSummary,fatherName,motherName);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(DashBoardActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DashBoardActivity.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"73|0zxBcVO1MOhwZO6KNYdy1drjK11aZMfyXT8naLhn");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void saveReferencedata(String id,String name,String designation,String organization,String email,String mobile)
    {
        Reference_Model reference_model = new Reference_Model();
        reference_model.setName(name);
        reference_model.setId(id);
        reference_model.setOrganization_name(organization);
        reference_model.setDesignation(designation);
        reference_model.setMobile_number(mobile);
        reference_model.setEmail(email);

        ResumeProfilePart5.reference.add(reference_model);
    }

    private void saveEducationData(String id,String institution,String degree,String department,String passYear,String result)
    {
        EducationQualification_Model education_model = new EducationQualification_Model();
        education_model.setInstitute_name(institution);
        education_model.setId(id);
        education_model.setQualification_name(degree);
        education_model.setGroupsubject_name(department);
        education_model.setPassing_year(passYear);
        education_model.setResult(result);
        ResumeProfilePart2.educationQualification.add(education_model);
    }

    private void saveExperienceData(String id,String orgranization,String designation,String startDate,String endDate,String location,String workSummary)
    {
        WorkExperience_Model experience_model = new WorkExperience_Model();
        experience_model.setDesignationName(designation);
        experience_model.setworkDetail(workSummary);
        experience_model.setOrganizationName(orgranization);
        experience_model.setId(id);
        experience_model.setStartDate(startDate);
        experience_model.setEndDate(endDate);
        experience_model.setOgganizationAddress(location);
        ResumeProfilePart6.workExperience.add(experience_model);
    }

    private void saveAdditionalInfosData(String id,String skills,String hobby,String language,String linkedin,String github,String twitter,String behance)
    {
        Additional_Infos.setId(id);
        Additional_Infos.setSkills(skills);
        Additional_Infos.setHobbies(hobby);
        Additional_Infos.setLanguages(language);
        Additional_Infos.setLinkedin(linkedin);
        Additional_Infos.setGithub(github);
        Additional_Infos.setTwitter(twitter);
        Additional_Infos.setBehance(behance);
    }

    private void saveProjectsData(String id,String projectName,String startDate,String endDate,String summary)
    {
        Projects_model projects_model = new Projects_model();
        projects_model.setId(id);
        projects_model.setProjectName(projectName);
        projects_model.setStartDate(startDate);
        projects_model.setEndDate(endDate);
        projects_model.setProjectSummary(summary);
        ResumeProfileProjects.projectsList.add(projects_model);
    }

    private void savePersonalInfo(String name,String image,String mobile,String presentAddress,String permanentAddress,String jobTitle,String maritalStatus,
                                  String religion,String nationality,String gender,String dob,String profileSummary,String fatherName,String motherName)
    {

    }

    private void clearResumeProfilePart2Memory(){
        ResumeProfilePart2.career_objective = "";
        ResumeProfilePart2.educationQualification.clear();
    }

    private void clearResumeProfilePart5Memory(){
        ResumeProfilePart5.reference.clear();
    }

    private void clearResumeProfilePart6Memory(){
        ResumeProfilePart6.workExperience.clear();
    }

    private void clearResumeProjectsMemory(){
        ResumeProfileProjects.projectsList.clear();
    }
}