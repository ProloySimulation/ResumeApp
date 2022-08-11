package com.cvmaster.xosstech.InputActivities.additionalInfo;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Additional;
import com.cvmaster.xosstech.InputActivities.additionalInfo.viewModel.AdditionalViewModel;

import java.util.List;

public class BuildResumeAddition extends AppCompatActivity {

    private EditText etSkills,etHobbies,etLanguages,etLinkedin,etBehance,etGithub,etTwitter;
    private Button btnSubmit,btnUpdate ;
    private String skills = null;
    private String hobbies = null;
    private String languages = null;
    private String linkedin = null;
    private String behance = null;
    private String github = null;
    private String twitter = null;
    private int id ;
    private String token = null;
    private AdditionalViewModel mainViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_addition);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        mainViewModel = new ViewModelProvider(this).get(AdditionalViewModel.class);

        etSkills = findViewById(R.id.editText_BuildResumeAdditiona_Skills);
        etHobbies = findViewById(R.id.editText_BuildResumeAdditional_Hobbies);
        etLanguages = findViewById(R.id.editText_BuildResumeSkills_Language);
        etLinkedin = findViewById(R.id.editText_BuildResumeSkills_Linkedin);
        etBehance = findViewById(R.id.editText_BuildResumeSkills_Behance);
        etGithub = findViewById(R.id.editText_BuildResumeSkills_Github);
        etTwitter = findViewById(R.id.editText_BuildResumeSkills_Twitter);
        btnSubmit = findViewById(R.id.btn_addition_Submit);
        btnUpdate = findViewById(R.id.btn_addition_update);

        getAdditionInfo();

    }

    private boolean CheckValidity(){

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
            return false;
        }
        if (hobbies.isEmpty()){
            etHobbies.setError("ENTER HOBBIES");
            etHobbies.requestFocus();
            return false;
        }
        if (languages.isEmpty()){
            etLanguages.setError("ENTER LANGUAGE");
            etLanguages.requestFocus();
            return false;
        }

        else {
            return true;
        }

    }

    private void getAdditionInfo()
    {
        mainViewModel.getAllAdditional(token).observe(this, new Observer<List<Additional>>() {
            @Override
            public void onChanged(@Nullable List<Additional> informationList) {

                if(informationList.size()<=0)
                {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
                else {

                    btnSubmit.setVisibility(View.GONE);
                    btnUpdate.setVisibility(View.VISIBLE);

                    hobbies = informationList.get(0).getHobby();
                    skills = informationList.get(0).getSkills();
                    languages = informationList.get(0).getLanguage();
                    linkedin = informationList.get(0).getLinkedin();
                    behance = informationList.get(0).getBehance();
                    github = informationList.get(0).getGithub();
                    twitter = informationList.get(0).getTwitter();
                    id = informationList.get(0).getId();


                    etSkills.setText(skills);
                    etHobbies.setText(hobbies);
                    etLanguages.setText(languages);
                    etGithub.setText(github);
                    etLinkedin.setText(linkedin);
                    etTwitter.setText(twitter);
                    etBehance.setText(behance);
                }
            }
        });
    }


    public void UploadInformation(View view) {

        if(CheckValidity())
        {
            Additional additional = new Additional(skills,hobbies,languages,linkedin,github,twitter,behance,1);

            mainViewModel.newAdditional(token,additional).observe(this, new Observer<List<Additional>>() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onChanged(@Nullable List<Additional> experienceList) {
                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    mainViewModel.getAllAdditional(token).removeObserver(this);
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    public void UpdateInformation(View view) {

        if(CheckValidity())
        {
            Additional additional = new Additional(skills,hobbies,languages,linkedin,github,twitter,behance,1);

            mainViewModel.updateAdditional(token,id,additional).observe(this, new Observer<List<Additional>>() {
                @Override
                public void onChanged(@Nullable List<Additional> experienceList) {
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                    mainViewModel.getAllAdditional(token).removeObserver(this);
                }
            });
        }
    }
}