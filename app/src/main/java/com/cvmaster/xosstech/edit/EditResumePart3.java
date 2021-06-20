package com.cvmaster.xosstech.edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.cvmaster.xosstech.InputActivities.BuildResumePart7;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.model.Skills_Model;

public class EditResumePart3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

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

    private Button button_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part3);

        textView_Skills = (TextView) findViewById(R.id.textView_EditResumePart3_Skills);

        checkBox_1 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_MicrosoftWord);
        checkBox_2 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_MicrosoftExcel);
        checkBox_3 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_MicrosoftPowerPoint);
        checkBox_4 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_InternetBrowsing);
        checkBox_5 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_SocialMedia);
        checkBox_6 = (CheckBox) findViewById(R.id.checkBox_EditResumePart3_Skills_Hardware);

        if (isThatSkillMarked("Microsoft Word")){
            checkBox_1.setChecked(true);
        }
        if (isThatSkillMarked("Microsoft Excel")){
            checkBox_2.setChecked(true);
        }
        if (isThatSkillMarked("Microsoft PowerPoint")){
            checkBox_3.setChecked(true);
        }
        if (isThatSkillMarked("Internet Browsing")){
            checkBox_4.setChecked(true);
        }
        if (isThatSkillMarked("Social Media")){
            checkBox_5.setChecked(true);
        }
        if (isThatSkillMarked("Computer Hardware")){
            checkBox_6.setChecked(true);
        }


        textView_LanguageProficiency_Bangla = (TextView) findViewById(R.id.textView_EditResumePart3_LanguageProficiency_Bangla);
        spinner_LanguageProficiency_1 = (Spinner) findViewById(R.id.spinner_EditResumePart3_LanguageProficiency_1);
        ArrayAdapter<CharSequence> arrayAdapterLanguageProficiency1 = ArrayAdapter.createFromResource(this,R.array.languageProficiency_Levels,android.R.layout.simple_spinner_item);
        arrayAdapterLanguageProficiency1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_LanguageProficiency_1.setAdapter(arrayAdapterLanguageProficiency1);
        spinner_LanguageProficiency_1.setOnItemSelectedListener(this);

        spinner_LanguageProficiency_1.setSelection(((ArrayAdapter<CharSequence>)spinner_LanguageProficiency_1.getAdapter()).getPosition(ResumeProfilePart3.bangla_skill_level));

        textView_LanguageProficiency_English = (TextView) findViewById(R.id.textView_EditResumePart3_LanguageProficiency_English);
        spinner_LanguageProficiency_2 = (Spinner) findViewById(R.id.spinner_EditResumePart3_LanguageProficiency_2);
        ArrayAdapter<CharSequence> arrayAdapterLanguageProficiency2 = ArrayAdapter.createFromResource(this,R.array.languageProficiency_Levels,android.R.layout.simple_spinner_item);
        arrayAdapterLanguageProficiency2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_LanguageProficiency_2.setAdapter(arrayAdapterLanguageProficiency2);
        spinner_LanguageProficiency_2.setOnItemSelectedListener(this);

        spinner_LanguageProficiency_2.setSelection(((ArrayAdapter<CharSequence>)spinner_LanguageProficiency_2.getAdapter()).getPosition(ResumeProfilePart3.english_skill_level));

        button_Update = (Button) findViewById(R.id.button_EditResumePart3_Update);
        button_Update.setOnClickListener(this);

    }

    private boolean isThatSkillMarked(String skill){

        for (int i = 0; i< ResumeProfilePart3.skills.size(); i++){
            Skills_Model skills_model = ResumeProfilePart3.skills.get(i);
            if (skill.equals(skills_model.getSkill())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view == button_Update){
            CheckValidity();
        }
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

        clearResumeProfilePart3Memory();
        SaveSkills();
        SavelanguageProficiency();
        GoToNextIntent();
    }

    private void clearResumeProfilePart3Memory(){
        ResumeProfilePart3.skills.clear();
        ResumeProfilePart3.bangla_skill_level = "";
        ResumeProfilePart3.english_skill_level = "";
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
        Intent intent = new Intent(getApplicationContext(),BuildResumePart7.class);
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
