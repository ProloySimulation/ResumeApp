package com.cvmaster.xosstech.InputActivities.experience;

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

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Experience;

public class Dialog extends DialogFragment {

    private EditText etDesignation,etDescription,etStartDate,etEndDate,etOrganizationAddress,etOrganizationName;
    private Button btnSubmit;
    private String designation,description,startDate,endDate,organizationAddress,organizationName;
    private LinearLayout layoutEditDelete;
    private String token;
    private int id;
    private UpdateStatus updateStatus;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_experience, null);

        token = "Bearer 564|rjnvymqohGMP6PoBWZn8Sw6pa3BZKUHeFKG4SCuu";

        etDesignation = v.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_Designation);
        etDescription = v.findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_1);
        etStartDate =  v.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_start);
        etEndDate = v.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_end);
        btnSubmit = v.findViewById(R.id.btnUpdate);
        layoutEditDelete = v.findViewById(R.id.layoutExperienceEditDelete);
        etOrganizationName = v.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationName);
        etOrganizationAddress = v.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationAddress1);

        Bundle bundle = getArguments();
        designation = bundle.getString("DESIGNATION", "");
        description = bundle.getString("DESCRIPTION", "");;
        startDate = bundle.getString("STARTDATE", "");
        endDate = bundle.getString("ENDDATE", "");
        organizationAddress = bundle.getString("ADDRESS", "");
        organizationName = bundle.getString("ORAGANIZATIONNAME", "");
        id = bundle.getInt("ID", 1);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExperience();
            }
        });

        setData();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateStatus = (UpdateStatus)context;
    }

    public interface UpdateStatus{
        void updateStatus(Experience experience,int id);
    }

    private void setData()
    {
        layoutEditDelete.setVisibility(View.GONE);
        etDesignation.setText(designation);
        etDescription.setText(description);
        etStartDate.setText(startDate);
        etEndDate.setText(endDate);
        etOrganizationAddress.setText(organizationAddress);
        etOrganizationName.setText(organizationName);

        btnSubmit.setVisibility(View.VISIBLE);
    }

    public void updateExperience() {

        designation = etDesignation.getText().toString();
        description = etDescription.getText().toString();
        startDate = etStartDate.getText().toString();
        endDate = etEndDate.getText().toString();
        organizationAddress = etOrganizationAddress.getText().toString();
        organizationName = etOrganizationName.getText().toString();

        Experience experience = new Experience(organizationName,designation,startDate,
                endDate,description,organizationAddress,1);

        updateStatus.updateStatus(experience,id);

        dismiss();
    }
}
