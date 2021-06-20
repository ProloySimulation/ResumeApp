package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Skills_Model;

public class BuildResumePart3 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private TextView textView_Skills;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;
    private CheckBox checkBox_6;

    private TextView textView_LanguageProficiency_Bangla;
    private Spinner spinner_LanguageProficiency_1;

    private TextView textView_LanguageProficiency_English;
    private Spinner spinner_LanguageProficiency_2;

    private Button button_Next;
    private Button button_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part3);

        clearResumeProfilePart3Memory();

        button_Data = (Button) findViewById(R.id.button_BuildResumePart3_Data);
        button_Data.setOnClickListener(this);

        textView_Skills = (TextView) findViewById(R.id.textView_BuildResumePart3_Skills);

        checkBox_1 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_MicrosoftWord);
        checkBox_2 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_MicrosoftExcel);
        checkBox_3 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_MicrosoftPowerPoint);
        checkBox_4 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_InternetBrowsing);
        checkBox_5 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_SocialMedia);
        checkBox_6 = (CheckBox) findViewById(R.id.checkBox_BuildResumePart3_Skills_Hardware);

        textView_LanguageProficiency_Bangla = (TextView) findViewById(R.id.textView_BuildResumePart3_LanguageProficiency_Bangla);
        spinner_LanguageProficiency_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_LanguageProficiency_1);
        ArrayAdapter<CharSequence> arrayAdapterLanguageProficiency1 = ArrayAdapter.createFromResource(this,R.array.languageProficiency_Levels,android.R.layout.simple_spinner_item);
        arrayAdapterLanguageProficiency1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_LanguageProficiency_1.setAdapter(arrayAdapterLanguageProficiency1);
        spinner_LanguageProficiency_1.setOnItemSelectedListener(this);

        textView_LanguageProficiency_English = (TextView) findViewById(R.id.textView_BuildResumePart3_LanguageProficiency_English);
        spinner_LanguageProficiency_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_LanguageProficiency_2);
        ArrayAdapter<CharSequence> arrayAdapterLanguageProficiency2 = ArrayAdapter.createFromResource(this,R.array.languageProficiency_Levels,android.R.layout.simple_spinner_item);
        arrayAdapterLanguageProficiency2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_LanguageProficiency_2.setAdapter(arrayAdapterLanguageProficiency2);
        spinner_LanguageProficiency_2.setOnItemSelectedListener(this);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart3_Next);
        button_Next.setOnClickListener(this);

        //Populate Data
        /*
        checkBox_1.setChecked(true);
        spinner_LanguageProficiency_1.setSelection(1);
        spinner_LanguageProficiency_2.setSelection(2);
        */

    }

    private void CheckValidity(){
        String languageProficiency_Bangla;
        String languageProficiency_English;

        languageProficiency_Bangla = spinner_LanguageProficiency_1.getSelectedItem().toString().trim();
        languageProficiency_English = spinner_LanguageProficiency_2.getSelectedItem().toString().trim();

        if (!checkBox_1.isChecked() && !checkBox_2.isChecked() && !checkBox_3.isChecked() && !checkBox_4.isChecked() && !checkBox_5.isChecked() && !checkBox_6.isChecked()){
            textView_Skills.setError("PLEASE CHECK ONE!");
            textView_Skills.requestFocus();
            return;
        }


        if (languageProficiency_Bangla.compareTo("Select") == 0){
            textView_LanguageProficiency_Bangla.setError("SELECT LEVEL");
            textView_LanguageProficiency_Bangla.requestFocus();
            return;
        }

        if (languageProficiency_English.compareTo("Select") == 0){
            textView_LanguageProficiency_English.setError("SELECT LEVEL");
            textView_LanguageProficiency_English.requestFocus();
            return;
        }

        SaveSkills();
        SavelanguageProficiency();
        GoToNextIntent();
    }

    private void SaveSkills(){
        if (checkBox_1.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Microsoft Word");
            ResumeProfilePart3.skills.add(skills_model);
        }
        if (checkBox_2.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Microsoft Excel");
            ResumeProfilePart3.skills.add(skills_model);
        }
        if (checkBox_3.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Microsoft PowerPoint");
            ResumeProfilePart3.skills.add(skills_model);
        }
        if (checkBox_4.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Internet Browsing");
            ResumeProfilePart3.skills.add(skills_model);
        }
        if (checkBox_5.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Social Media");
            ResumeProfilePart3.skills.add(skills_model);
        }
        if (checkBox_6.isChecked()){
            Skills_Model skills_model = new Skills_Model();
            skills_model.setSkill("Computer Hardware");
            ResumeProfilePart3.skills.add(skills_model);
        }

    }

    private void SavelanguageProficiency(){
        String bangla_skill_level;
        String english_skill_level;

        bangla_skill_level = spinner_LanguageProficiency_1.getSelectedItem().toString().trim();
        english_skill_level = spinner_LanguageProficiency_2.getSelectedItem().toString().trim();

        ResumeProfilePart3.bangla_skill_level = bangla_skill_level;
        ResumeProfilePart3.english_skill_level = english_skill_level;
    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart4.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == button_Next){
            CheckValidity();
        }
        if (view == button_Data){
            ShowData();
        }
    }

    private void ShowData(){
        Log.d("BuildResumePart2_Data", ResumeProfilePart2.career_objective);
        EducationQualification_Model educationQualification_model;
        for (int i=0;i<ResumeProfilePart2.educationQualification.size();i++){
            educationQualification_model = ResumeProfilePart2.educationQualification.get(i);
            Log.d("BuildResumePart2_Data",educationQualification_model.getQualification_name());
            Log.d("BuildResumePart2_Data",educationQualification_model.getInstitute_name());
            Log.d("BuildResumePart2_Data",educationQualification_model.getBoard_name());
            Log.d("BuildResumePart2_Data",educationQualification_model.getGroupsubject_name());
            Log.d("BuildResumePart2_Data",educationQualification_model.getPassing_year());
            Log.d("BuildResumePart2_Data",educationQualification_model.getResult());
        }
    }

    private void clearResumeProfilePart3Memory(){
        ResumeProfilePart3.skills.clear();
        ResumeProfilePart3.bangla_skill_level = "";
        ResumeProfilePart3.english_skill_level = "";
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
