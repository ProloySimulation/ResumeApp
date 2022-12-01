package com.cvmaster.xosstech.inputactivities.training;

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
import com.cvmaster.xosstech.inputactivities.training.adapter.TrainingAdapter;
import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.inputactivities.training.viewModel.TrainingViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityTraining extends AppCompatActivity implements TrainingDialog.UpdateStatus, DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerView;
    private TrainingViewModel mainViewModel;
    private TrainingAdapter trainingAdapter;

    private SpinKitView progressBar ;
    private Button btnNewExperience;
    private ImageView imvBack;
    private LinearLayout newLayout;
    private String token ;

    private EditText etTrainingName, etTrainingSummary,etEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        init();

        btnNewExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newLayout.setVisibility(View.VISIBLE);
            }
        });

        etEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
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

        getTrainingList();
    }

    private void init()
    {
        etTrainingName = findViewById(R.id.etTrainingName);
        etTrainingSummary = findViewById(R.id.etTrainingDetail);
        etEndDate = findViewById(R.id.etTrainingEndDate);
        imvBack = findViewById(R.id.imvTrainingBack);

        recyclerView = findViewById(R.id.rvTrainings);
        btnNewExperience = findViewById(R.id.btn_add_training);
        newLayout = findViewById(R.id.training_Layout_1);
        progressBar = findViewById(R.id.training_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
    }

    public void postTraining(View view) {

        String trainingName = etTrainingName.getText().toString();
        String trainingSummary = etTrainingSummary.getText().toString();
        String trainingEndDate = etEndDate.getText().toString();

        if(trainingName.isEmpty() || trainingSummary.isEmpty() || trainingEndDate.isEmpty())
        {
            Training training = new Training(trainingName,trainingEndDate,trainingSummary,1);

            mainViewModel.postAllTraining(token,training).observe(this, new Observer<List<Training>>() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onChanged(@Nullable List<Training> trainingList) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    newLayout.setVisibility(View.GONE);
                    mainViewModel.getAllTrainings(token).removeObserver(this);
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    public void getTrainingList() {

        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.getAllTrainings(token).observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(@Nullable List<Training> trainingList) {
                setRecyclerView(trainingList);
//                mainViewModel.getAllUsers(token).removeObserver(this);
            }
        });
    }

    public void deleteTraining(int id,int position) {

        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.deleteTraining(token,id).observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(@Nullable List<Training> trainingList) {
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                trainingAdapter.notifyItemRemoved(position);
                setRecyclerView(trainingList);
                mainViewModel.getAllTrainings(token).removeObserver(this);
            }
        });
    }

    public void updateTraining(Training training,int id)
    {
        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.updateTraining(token,id,training).observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(@Nullable List<Training> trainingList) {
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(trainingList);
                progressBar.setVisibility(View.GONE);
                mainViewModel.getAllTrainings(token).removeObserver(this);
            }
        });
    }

    private void setRecyclerView(List<Training> trainingList) {

        progressBar.setVisibility(View.GONE);
        trainingAdapter = new TrainingAdapter(trainingList, new TrainingAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                TrainingDialog dialogFragment = new TrainingDialog();
                String trainingName = trainingList.get(position).getTraining_name();
                String trainingSummary = trainingList.get(position).getTraining_summary();
                String trainingDate = trainingList.get(position).getEnd();
                int id = trainingList.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("NAME",trainingName);
                bundle.putString("SUMMARY",trainingSummary);
                bundle.putString("STARTDATE",trainingDate);
                bundle.putInt("ID",id);
                dialogFragment.setArguments(bundle);

                dialogFragment.show((ActivityTraining.this).getSupportFragmentManager(),"Image Dialog");
            }

            @Override
            public void deleteItem(int position) {
                int id = trainingList.get(position).getId();
                deleteTraining(id,position);
            }
        });
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trainingAdapter);
        trainingAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateStatus(Training training,int id) {
        updateTraining(training,id);
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
        etEndDate.setText(date);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}