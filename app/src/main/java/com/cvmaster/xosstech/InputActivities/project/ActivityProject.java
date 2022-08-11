package com.cvmaster.xosstech.InputActivities.project;

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
import com.cvmaster.xosstech.InputActivities.project.adapter.ProjectAdapter;
import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.InputActivities.project.viewModel.ProjectViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class ActivityProject extends AppCompatActivity implements ProjectDialog.UpdateStatus {

    private RecyclerView recyclerView;
    private ProjectViewModel mainViewModel;
    private ProjectAdapter projectAdapter;

    private SpinKitView progressBar ;
    private List<Project> projectList;
    private Button btnNewProject,btnSubmit;
    private LinearLayout newLayout;
    private String token ;

    private EditText etProjectName,etProjectSummary,etStartDate,etEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        etProjectName = findViewById(R.id.etProjectName);
        etProjectSummary = findViewById(R.id.etProjectSummary);
        etStartDate = findViewById(R.id.etProjectStart);
        etEndDate = findViewById(R.id.etProjectEnd);

        recyclerView = findViewById(R.id.rvProject);
        btnNewProject = findViewById(R.id.btn_add_project);
        btnSubmit = findViewById(R.id.btn_submit_new_edu_field);
        newLayout = findViewById(R.id.add_new_project_layout);
        progressBar = findViewById(R.id.project_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        projectList = new ArrayList<>();

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNewProject.setVisibility(View.GONE);
                newLayout.setVisibility(View.VISIBLE);
            }
        });

        getProjectList();
    }

    public void postProject(View view) {

        String projectName = etProjectName.getText().toString();
        String projectSummary = etProjectSummary.getText().toString();
        String projectStart = etStartDate.getText().toString();
        String projectEndD = etEndDate.getText().toString();

        Project project = new Project(projectName,projectStart,projectEndD,projectSummary,1);

        mainViewModel.postProject(token,project).observe(this, new Observer<List<Project>>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onChanged(@Nullable List<Project> projectList) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                newLayout.setVisibility(View.GONE);
                btnNewProject.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                mainViewModel.getAllProjects(token).removeObserver(this);
            }
        });
    }

    public void getProjectList() {

        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.getAllProjects(token).observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projectList) {
                setRecyclerView(projectList);
            }
        });
    }

    public void deleteExperience(int id,int position) {

        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.deleteProject(token,id).observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projectList) {
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                projectAdapter.notifyItemRemoved(position);
                setRecyclerView(projectList);
                mainViewModel.getAllProjects(token).removeObserver(this);
            }
        });
    }

    private void updateProject(Project project, int id)
    {
        mainViewModel.updateProject(token,id,project).observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projectList) {
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(projectList);
                mainViewModel.getAllProjects(token).removeObserver(this);
            }
        });
    }

    private void setRecyclerView(List<Project> projectList) {

        progressBar.setVisibility(View.GONE);
        projectAdapter = new ProjectAdapter(projectList, new ProjectAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                ProjectDialog dialogFragment = new ProjectDialog();
                String projectName = projectList.get(position).getProject_name();
                String summary = projectList.get(position).getProject_summary();
                String startDate = projectList.get(position).getStart();
                String endDate = projectList.get(position).getEnd();
                int id = projectList.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("NAME",projectName);
                bundle.putString("SUMMARY",summary);
                bundle.putString("STARTDATE",startDate);
                bundle.putString("ENDDATE",endDate);
                bundle.putInt("ID",id);
                dialogFragment.setArguments(bundle);

                dialogFragment.show((ActivityProject.this).getSupportFragmentManager(),"Image Dialog");
            }

            @Override
            public void deleteItem(int position) {
                int id = projectList.get(position).getId();
                deleteExperience(id,position);
            }
        });
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(projectAdapter);
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateStatus(Project project,int id) {
        updateProject(project,id);
    }
}