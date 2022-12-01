package com.cvmaster.xosstech.inputactivities.project;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.inputactivities.project.adapter.ProjectAdapter;
import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.inputactivities.project.viewModel.ProjectViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityProject extends AppCompatActivity implements ProjectDialog.UpdateStatus, DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerView;
    private ProjectViewModel mainViewModel;
    private ProjectAdapter projectAdapter;

    private SpinKitView progressBar ;
    private List<Project> projectList;
    private Button btnNewProject,btnSubmit;
    private ImageView imvBack;
    private LinearLayout newLayout;
    private String token ;
    private String state;

    private EditText etProjectName,etProjectSummary,etStartDate,etEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        init();

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNewProject.setVisibility(View.GONE);
                newLayout.setVisibility(View.VISIBLE);
            }
        });

        etStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    state = "startDate";
                    com.cvmaster.xosstech.DatePicker mDatePickerDialogFragment;
                    mDatePickerDialogFragment = new com.cvmaster.xosstech.DatePicker();
                    mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
                }
            }
        });

        etEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    state = "endDate";
                    com.cvmaster.xosstech.DatePicker mDatePickerDialogFragment;
                    mDatePickerDialogFragment = new com.cvmaster.xosstech.DatePicker();
                    mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
                }
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getProjectList();
    }

    private void init()
    {
        etProjectName = findViewById(R.id.etProjectName);
        etProjectSummary = findViewById(R.id.etProjectSummary);
        etStartDate = findViewById(R.id.etProjectStart);
        etEndDate = findViewById(R.id.etProjectEnd);
        imvBack = findViewById(R.id.imvProjectBack);

        recyclerView = findViewById(R.id.rvProject);
        btnNewProject = findViewById(R.id.btn_add_project);
        btnSubmit = findViewById(R.id.btn_submit_new_edu_field);
        newLayout = findViewById(R.id.add_new_project_layout);
        progressBar = findViewById(R.id.project_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        projectList = new ArrayList<>();
    }

    public void postProject(View view) {

        String projectName = etProjectName.getText().toString();
        String projectSummary = etProjectSummary.getText().toString();
        String projectStart = etStartDate.getText().toString();
        String projectEndD = etEndDate.getText().toString();

        if(projectName.isEmpty() || projectSummary.isEmpty() || projectStart.isEmpty() || projectEndD.isEmpty())
        {
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

        else
        {
            Toast.makeText(this, "Please Fill Up All Information", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String date = formatter.format(Date.parse(selectedDate));

        if(state.equals("startDate"))
        {
            etStartDate.setText(date);
        }
        else
        {
            etEndDate.setText(date);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}