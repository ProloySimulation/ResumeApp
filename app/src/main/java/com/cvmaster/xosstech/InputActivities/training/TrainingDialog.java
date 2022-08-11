package com.cvmaster.xosstech.InputActivities.training;

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
import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.InputActivities.training.viewModel.TrainingViewModel;

public class TrainingDialog extends DialogFragment {

    private TrainingViewModel mainViewModel;

    private EditText etTrainingName, etTrainingSummary,etEndDate;
    private Button btnSubmit;
    private String trainingName,trainingSummary,trainingEndDate;
    private LinearLayout layoutEditDelete;
    private String token;
    private int id;
    private UpdateStatus updateStatus;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_training, null);

        mainViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);

        token = "Bearer 564|rjnvymqohGMP6PoBWZn8Sw6pa3BZKUHeFKG4SCuu";

        etTrainingName = v.findViewById(R.id.editText_BuildResumeTraining_name_1);
        etTrainingSummary = v.findViewById(R.id.editText_BuildResumeTraining_Summary_1);
        etEndDate =  v.findViewById(R.id.editText_BuildResumeTraining_endDate_1);

        btnSubmit = v.findViewById(R.id.button_BuildResumeProject_Update_1);
        layoutEditDelete = v.findViewById(R.id.layoutProjectEditDelete);

        Bundle bundle = getArguments();
        trainingName = bundle.getString("NAME", "");
        trainingSummary = bundle.getString("SUMMARY", "");;
        trainingEndDate = bundle.getString("STARTDATE", "");
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
        updateStatus = (TrainingDialog.UpdateStatus)context;
    }

    public interface UpdateStatus{
        void updateStatus(Training training,int id);
    }

    private void setData()
    {
        layoutEditDelete.setVisibility(View.GONE);
        etTrainingName.setText(trainingName);
        etTrainingSummary.setText(trainingSummary);
        etEndDate.setText(trainingEndDate);

        btnSubmit.setVisibility(View.VISIBLE);
    }

    public void updateProject() {

        trainingName = etTrainingName.getText().toString();
        trainingSummary = etTrainingSummary.getText().toString();
        trainingEndDate = etEndDate.getText().toString();

        Training training = new Training(trainingName,trainingEndDate,trainingSummary,1);


        updateStatus.updateStatus(training,id);
        dismiss();
    }
}
