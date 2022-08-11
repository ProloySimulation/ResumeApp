package com.cvmaster.xosstech.InputActivities.education;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.InputActivities.education.adapter.EducationAdapter;
import com.cvmaster.xosstech.model.Education;
import com.cvmaster.xosstech.InputActivities.education.viewModel.EducationViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class ActivityEducation extends AppCompatActivity implements AdapterView.OnItemSelectedListener,EducationDialog.EducationUpdateStatus {

    private RecyclerView recyclerView;
    private EducationViewModel mainViewModel;
    private EducationAdapter educationAdapter;

    private SpinKitView progressBar ;
    private List<Education> educationList;
    private Button btnNewEducation,btnSubmit;
    private Spinner spinnerBoard ;
    private LinearLayout newLayout;
    private String token,board ;

    private EditText etDegree,etInstitution,etSubjectName,etPassYear,etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        etDegree = findViewById(R.id.etDegreeName);
        etInstitution = findViewById(R.id.etInstituionName);
        etSubjectName = findViewById(R.id.etGroupName);
        etPassYear = findViewById(R.id.etEducationPassYear);
        etResult = findViewById(R.id.etEducationResult);

        spinnerBoard = findViewById(R.id.spinnerBoardName);
        recyclerView = findViewById(R.id.rvEducation);
        btnNewEducation = findViewById(R.id.btn_add_education);
        btnSubmit = findViewById(R.id.btn_submit_new_edu_field);
        newLayout = findViewById(R.id.layout_education);
        progressBar = findViewById(R.id.experiecne_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(EducationViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        educationList = new ArrayList<>();

        ArrayAdapter<CharSequence> arrayAdapterBoardName1 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBoard.setAdapter(arrayAdapterBoardName1);
        spinnerBoard.setOnItemSelectedListener(this);

        btnNewEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newLayout.setVisibility(View.VISIBLE);
                btnNewEducation.setVisibility(View.GONE);
            }
        });

        getEducations();
    }

    public void postEducationList(View view) {

        String degree = etDegree.getText().toString();
        String result = etResult.getText().toString();
        String institution = etInstitution.getText().toString();
        String passYear = etPassYear.getText().toString();
        String dept = etSubjectName.getText().toString();

        Education education = new Education(institution,degree,dept,passYear,result,board,1);

        mainViewModel.postAllEducation(token,education).observe(this, new Observer<List<Education>>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onChanged(@Nullable List<Education> educationList) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                newLayout.setVisibility(View.GONE);
                mainViewModel.getAllEducation(token).removeObserver(this);
                btnNewEducation.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void getEducations() {

        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.getAllEducation(token).observe(this, new Observer<List<Education>>() {
            @Override
            public void onChanged(@Nullable List<Education> educationList) {
                setRecyclerView(educationList);
                progressBar.setVisibility(View.GONE);
//                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    public void deleteExperience(int id,int position) {

        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.deleteEducation(token,id).observe(this, new Observer<List<Education>>() {
            @Override
            public void onChanged(@Nullable List<Education> educationList) {
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(educationList);
                mainViewModel.getAllEducation(token).removeObserver(this);
            }
        });
    }

    private void updateEducation(Education education,int id)
    {
        mainViewModel.updateEducation(token,id,education).observe(this, new Observer<List<Education>>() {
            @Override
            public void onChanged(@Nullable List<Education> educationList) {
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(educationList);
                mainViewModel.getAllEducation(token).removeObserver(this);
            }
        });
    }

    private void setRecyclerView(List<Education> educationList) {

        educationAdapter = new EducationAdapter(educationList, new EducationAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                com.cvmaster.xosstech.InputActivities.education.EducationDialog dialogFragment = new com.cvmaster.xosstech.InputActivities.education.EducationDialog();
                String degree = educationList.get(position).getDegree();
                String institution = educationList.get(position).getInst_name();
                String board = educationList.get(position).getBoard();
                String dept = educationList.get(position).getDept();
                String passYear = educationList.get(position).getPass_year();
                String result = educationList.get(position).getResult();
                int id = educationList.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("DEGREE",degree);
                bundle.putString("INSTITUTION",institution);
                bundle.putString("BOARD",board);
                bundle.putString("DEPARTMENT",dept);
                bundle.putString("PASSYEAR",passYear);
                bundle.putString("RESULT",result);
                bundle.putInt("ID",id);
                dialogFragment.setArguments(bundle);

                dialogFragment.show((ActivityEducation.this).getSupportFragmentManager(),"Image Dialogg");
            }

            @Override
            public void deleteItem(int position) {
                int id = educationList.get(position).getId();
                deleteExperience(id,position);
            }
        });
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(educationAdapter);
        educationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = String.valueOf(spinnerBoard.getAdapter().getItem(i));
        board = selected;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void updateStatus(Education education,int id) {
        updateEducation(education,id);
    }
}