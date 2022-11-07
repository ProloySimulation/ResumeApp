package com.cvmaster.xosstech.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cvmaster.xosstech.inputactivities.additionalinfo.BuildResumeAddition;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.ShowPdf;
import com.cvmaster.xosstech.inputactivities.education.ActivityEducation;
import com.cvmaster.xosstech.inputactivities.experience.ActivityExperience;
import com.cvmaster.xosstech.inputactivities.personal.ActivityPersonal;
import com.cvmaster.xosstech.inputactivities.project.ActivityProject;
import com.cvmaster.xosstech.inputactivities.reference.ActivityReference;
import com.cvmaster.xosstech.inputactivities.training.ActivityTraining;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvPersonal,cvExp,cvEdu,cvReference,cvAddition,cvProjects,cvTraining;
    private ImageView imvBack;
    private LinearLayout layoutProfile ;
    private FloatingActionButton fabCvMake ;
    private SpinKitView progressBar ;

    private String token = null;
    private String mobile = "01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        init();

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //For Demo Account
/*
        Intent intent = getIntent();
        mobile = intent.getStringExtra("MOBILE_NUMBER");*/

        if(mobile.equals("01987982903"))
        {
            Toast.makeText(getApplicationContext(), "This is a testing account", Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        cvPersonal = findViewById(R.id.cvPersonal);
        cvExp = findViewById(R.id.cvExp);
        cvEdu = findViewById(R.id.cvEdu);
        cvReference = findViewById(R.id.cvRef);
        cvAddition = findViewById(R.id.cvAdditon);
        cvProjects = findViewById(R.id.cvProjects);
        cvTraining = findViewById(R.id.cvTraining);
        fabCvMake = findViewById(R.id.fabCvMake);
        imvBack = findViewById(R.id.imvProfileBack);
        layoutProfile = findViewById(R.id.bottomLinear);
        progressBar = findViewById(R.id.dashboard_spin_kit);

        cvPersonal.setOnClickListener(this);
        cvExp.setOnClickListener(this);
        cvEdu.setOnClickListener(this);
        cvReference.setOnClickListener(this);
        cvAddition.setOnClickListener(this);
        cvProjects.setOnClickListener(this);
        cvTraining.setOnClickListener(this);
        fabCvMake.setOnClickListener(this);
        layoutProfile.setOnClickListener(this);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
    }

    @Override
    public void onClick(View view) {

        if (view == cvPersonal)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityPersonal.class);
            startActivity(intent);
        }
        if (view == cvExp)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityExperience.class);
            startActivity(intent);
        }
        if (view == cvEdu)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityEducation.class);
            startActivity(intent);
        }
        if (view == cvReference)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityReference.class);
            startActivity(intent);
        }
        if (view == cvAddition)
        {
            Intent intent = new Intent(getApplicationContext(), BuildResumeAddition.class);
            startActivity(intent);
        }
        if (view == cvProjects)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityProject.class);
            startActivity(intent);
        }
        if (view == cvTraining)
        {
            Intent intent = new Intent(getApplicationContext(), ActivityTraining.class);
            startActivity(intent);
        }
        if(view == fabCvMake)
        {
            Intent intent = new Intent(getApplicationContext(), ShowPdf.class);
            startActivity(intent);
        }
        if(view == layoutProfile)
        {
            Intent intent = new Intent(getApplicationContext(), ShowPdf.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}