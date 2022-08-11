package com.cvmaster.xosstech.InputActivities.experience;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.InputActivities.experience.adapter.ExperienceAdapter;
import com.cvmaster.xosstech.model.Experience;
import com.cvmaster.xosstech.InputActivities.experience.viewModel.ExperienceViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class ActivityExperience extends AppCompatActivity implements Dialog.UpdateStatus {

    private RecyclerView recyclerView;
    private ExperienceViewModel mainViewModel;
    private ExperienceAdapter experienceAdapter;

    private SpinKitView progressBar ;
    private List<Experience> experienceList;
    private Button btnNewExperience;
    private LinearLayout newLayout;
    private String token ;

    private EditText etDesignation,etWorkDetail,etStartDate,etEndDate,etCompanyName,etWorkAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        etDesignation = findViewById(R.id.etExperienceDesignation);
        etWorkDetail = findViewById(R.id.etExperienceWorkDetail);
        etStartDate = findViewById(R.id.etExperienceStartDate);
        etEndDate = findViewById(R.id.etExperienceEndDate);
        etCompanyName = findViewById(R.id.etExperienceCompanyName);
        etWorkAddress = findViewById(R.id.etExperienceWorkAddress);


        recyclerView = findViewById(R.id.rvExperience);
        btnNewExperience = findViewById(R.id.btn_add_expereiecne);
        newLayout = findViewById(R.id.add_new_experience_layout);
        progressBar = findViewById(R.id.experiecne_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(ExperienceViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        experienceList = new ArrayList<>();

        btnNewExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newLayout.setVisibility(View.VISIBLE);
                btnNewExperience.setVisibility(View.GONE);
            }
        });

        getExperienceList();
    }

    public void postExperienceList(View view) {

        String companyName = etCompanyName.getText().toString();
        String position = etDesignation.getText().toString();
        String startDate = etStartDate.getText().toString();
        String endDate = etEndDate.getText().toString();
        String workSummary = etWorkDetail.getText().toString();
        String location = etWorkAddress.getText().toString();

        Experience experience = new Experience(companyName,position,startDate,
                endDate,workSummary,location,1);

        mainViewModel.postAllUsers(token,experience).observe(this, new Observer<List<Experience>>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onChanged(@Nullable List<Experience> experienceList) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                newLayout.setVisibility(View.GONE);
                mainViewModel.getAllUsers(token).removeObserver(this);
                btnNewExperience.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void getExperienceList() {

        mainViewModel.getAllUsers(token).observe(this, new Observer<List<Experience>>() {
            @Override
            public void onChanged(@Nullable List<Experience> experienceList) {
                setRecyclerView(experienceList);
//                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    public void deleteExperience(int id,int position) {

        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.deleteExperience(token,id).observe(this, new Observer<List<Experience>>() {
            @Override
            public void onChanged(@Nullable List<Experience> experienceList) {
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(experienceList);
                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    private void updateExperience(Experience experience,int id)
    {
        mainViewModel.updateUsers(token,id,experience).observe(this, new Observer<List<Experience>>() {
            @Override
            public void onChanged(@Nullable List<Experience> experienceList) {
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(experienceList);
                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    private void setRecyclerView(List<Experience> experienceList) {

        progressBar.setVisibility(View.GONE);
        experienceAdapter = new ExperienceAdapter(experienceList, new ExperienceAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                com.cvmaster.xosstech.InputActivities.experience.Dialog dialogFragment = new com.cvmaster.xosstech.InputActivities.experience.Dialog();
                String designation = experienceList.get(position).getPosition();
                String description = experienceList.get(position).getWork_summary();
                String startDate = experienceList.get(position).getStart();
                String endDate = experienceList.get(position).getEnd();
                String organizationAddress = experienceList.get(position).getLocation();
                String organizationName = experienceList.get(position).getCompany_name();
                int id = experienceList.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("DESIGNATION",designation);
                bundle.putString("DESCRIPTION",description);
                bundle.putString("STARTDATE",startDate);
                bundle.putString("ENDDATE",endDate);
                bundle.putString("ADDRESS",organizationAddress);
                bundle.putString("ORAGANIZATIONNAME",organizationName);
                bundle.putInt("ID",id);
                dialogFragment.setArguments(bundle);

                dialogFragment.show((ActivityExperience.this).getSupportFragmentManager(),"Image Dialog");
            }

            @Override
            public void deleteItem(int position) {
                int id = experienceList.get(position).getId();
                deleteExperience(id,position);
            }
        });
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(experienceAdapter);
        experienceAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateStatus(Experience experience,int id) {
        updateExperience(experience,id);
    }
}