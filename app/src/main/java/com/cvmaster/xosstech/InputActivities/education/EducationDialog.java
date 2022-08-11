package com.cvmaster.xosstech.InputActivities.education;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Education;
import com.cvmaster.xosstech.InputActivities.education.viewModel.EducationViewModel;

public class EducationDialog extends DialogFragment {

    private EducationViewModel mainViewModel;

    private EditText etDegree,etInstitution,etDept,etPassYear,etResult;
    private TextView tvBoard;
    private Spinner spinnerBoard ;

    private Button btnSubmit;
    private String degree,institution,board,dept,passYear,result;
    private LinearLayout layoutEditDelete;
    private String token;
    private int id;
    private EducationUpdateStatus updateStatus;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_education, null);

        mainViewModel = new ViewModelProvider(this).get(EducationViewModel.class);

        token = "Bearer 564|rjnvymqohGMP6PoBWZn8Sw6pa3BZKUHeFKG4SCuu";

        etDegree = v.findViewById(R.id.etItemDegreeName);
        etInstitution = v.findViewById(R.id.etItemInstituionName);
        tvBoard = v.findViewById(R.id.tvItemBoardName);
        etDept = v.findViewById(R.id.etItemGroupName);
        etPassYear = v.findViewById(R.id.etItemEducationPassYear);
        etResult = v.findViewById(R.id.etItemEducationResult);
        spinnerBoard = v.findViewById(R.id.spinnerItemBoardName);

        btnSubmit = v.findViewById(R.id.btnEducationUpdate);
        layoutEditDelete = v.findViewById(R.id.layoutEducationEditDelete);

        btnSubmit.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        degree = bundle.getString("DEGREE", "");
        institution = bundle.getString("INSTITUTION", "");;
        board = bundle.getString("BOARD", "");
        dept = bundle.getString("DEPARTMENT", "");
        passYear = bundle.getString("PASSYEAR", "");
        result = bundle.getString("RESULT", "");
        id = bundle.getInt("ID", 1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        ArrayAdapter<CharSequence> arrayAdapterBoardName1 = ArrayAdapter.createFromResource(getActivity(),R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBoard.setAdapter(arrayAdapterBoardName1);
        spinnerBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = String.valueOf(spinnerBoard.getAdapter().getItem(i));
                board = selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExperience();
            }
        });

        setData();

        return builder.create();
    }

    private void setData()
    {
        layoutEditDelete.setVisibility(View.GONE);
        etDegree.setText(degree);
        etDept.setText(dept);
        etPassYear.setText(passYear);
        etInstitution.setText(institution);
        etResult.setText(result);
        tvBoard.setText(board);

        btnSubmit.setVisibility(View.VISIBLE);
    }

    public void updateExperience() {

        degree = etDegree.getText().toString();
        result = etResult.getText().toString();
        institution = etInstitution.getText().toString();
        passYear = etPassYear.getText().toString();
        dept = etDept.getText().toString();

        Education education = new Education(institution,degree,dept,passYear,result,board,id);

        updateStatus.updateStatus(education,id);
        dismiss();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateStatus = (EducationUpdateStatus)context;
    }

    public interface EducationUpdateStatus{
        void updateStatus(Education education,int id);
    }
}
