package com.cvmaster.xosstech.inputactivities.project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.inputactivities.project.viewModel.ProjectViewModel;

public class ProjectDialog extends DialogFragment {

    private ProjectViewModel mainViewModel;

    private EditText etProjectName, etProjectSummary,etStartDate,etEndDate;
    private Button btnSubmit;
    private String projectName,description,startDate,endDate;
    private LinearLayout layoutEditDelete;
    private String token;
    private int id;
    private ProjectDialog.UpdateStatus updateStatus;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_project, null);

        mainViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        token = "Bearer 564|rjnvymqohGMP6PoBWZn8Sw6pa3BZKUHeFKG4SCuu";

        etProjectName = v.findViewById(R.id.etItemProjectName);
        etProjectSummary = v.findViewById(R.id.etItemProjectSummary);
        etStartDate =  v.findViewById(R.id.etItemProjectStart);
        etEndDate = v.findViewById(R.id.etItemProjectEnd);
        btnSubmit = v.findViewById(R.id.button_BuildResumeProject_AddField_1);
        layoutEditDelete = v.findViewById(R.id.layoutProjectEditDelete);

        Bundle bundle = getArguments();
        projectName = bundle.getString("NAME", "");
        description = bundle.getString("SUMMARY", "");;
        startDate = bundle.getString("STARTDATE", "");
        endDate = bundle.getString("ENDDATE", "");

        id = bundle.getInt("ID", 1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProject();
            }
        });

        setData();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateStatus = (ProjectDialog.UpdateStatus)context;
    }

    public interface UpdateStatus{
        void updateStatus(Project project,int id);
    }

    private void setData()
    {
        layoutEditDelete.setVisibility(View.GONE);
        etProjectName.setText(projectName);
        etProjectSummary.setText(description);
        etStartDate.setText(startDate);
        etEndDate.setText(endDate);

        btnSubmit.setVisibility(View.VISIBLE);
    }

    public void updateProject() {

        projectName = etProjectName.getText().toString();
        description = etProjectSummary.getText().toString();
        startDate = etStartDate.getText().toString();
        endDate = etEndDate.getText().toString();

        Project project = new Project(projectName,startDate,endDate,description,1);

        updateStatus.updateStatus(project,id);
        dismiss();
    }
}
