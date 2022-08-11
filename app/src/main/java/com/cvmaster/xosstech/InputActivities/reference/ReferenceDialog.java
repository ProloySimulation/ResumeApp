package com.cvmaster.xosstech.InputActivities.reference;

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
import com.cvmaster.xosstech.model.Reference;
import com.cvmaster.xosstech.InputActivities.reference.viewModel.ReferenceViewModel;

public class ReferenceDialog extends DialogFragment {

    private ReferenceViewModel mainViewModel;

    private EditText etRefName, etOrganization, etRefDesignation, etRefEmail, etRefMobile;
    private Button btnSubmit;
    private String name,organization,designation,email,mobile;
    private LinearLayout layoutEditDelete;
    private String token;
    private int id;
    private ReferenceDialog.UpdateStatus updateStatus;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_reference, null);

        mainViewModel = new ViewModelProvider(this).get(ReferenceViewModel.class);

        token = "Bearer 564|rjnvymqohGMP6PoBWZn8Sw6pa3BZKUHeFKG4SCuu";

        etRefName = v.findViewById(R.id.editText_BuildResumePart5_Reference1_Name);
        etRefDesignation = v.findViewById(R.id.editText_BuildResumePart5_Reference1_Designation);
        etOrganization =  v.findViewById(R.id.editText_BuildResumePart5_Reference1_OrganizationName);
        etRefEmail = v.findViewById(R.id.editText_BuildResumePart5_Reference1_Email);
        btnSubmit = v.findViewById(R.id.button_BuildResumePart5_AddField_1);
        layoutEditDelete = v.findViewById(R.id.layoutReferenceEditDelete);
        etRefMobile = v.findViewById(R.id.editText_BuildResumePart5_Reference1_MobileNumber);

        Bundle bundle = getArguments();
        name = bundle.getString("NAME", "");
        organization = bundle.getString("ORAGANIZATIONNAME", "");;
        designation = bundle.getString("DESIGNATION", "");
        email = bundle.getString("EMAIL", "");
        mobile = bundle.getString("MOBILE", "");
        id = bundle.getInt("ID", 1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExperience();
            }
        });

        setData();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateStatus = (ReferenceDialog.UpdateStatus)context;
    }

    public interface UpdateStatus{
        void updateStatus(Reference reference,int id);
    }

    private void setData()
    {
        layoutEditDelete.setVisibility(View.GONE);
        etOrganization.setText(organization);
        etRefName.setText(name);
        etRefDesignation.setText(designation);
        etRefEmail.setText(email);
        etRefMobile.setText(mobile);

        btnSubmit.setVisibility(View.VISIBLE);
    }

    public void updateExperience() {

        designation = etRefDesignation.getText().toString();
        name = etRefName.getText().toString();
        organization = etOrganization.getText().toString();
        email = etRefEmail.getText().toString();
        mobile = etRefMobile.getText().toString();

        Reference reference = new Reference(name,designation,organization,email,mobile,1);

        updateStatus.updateStatus(reference,id);
        dismiss();
    }
}
