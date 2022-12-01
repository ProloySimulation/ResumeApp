package com.cvmaster.xosstech.inputactivities.reference;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.inputactivities.reference.adapter.ReferenceAdapter;
import com.cvmaster.xosstech.model.Reference;
import com.cvmaster.xosstech.inputactivities.reference.viewModel.ReferenceViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class ActivityReference extends AppCompatActivity implements ReferenceDialog.UpdateStatus {

    private RecyclerView recyclerView;
    private ReferenceViewModel mainViewModel;
    private ReferenceAdapter referenceAdapter;

    private SpinKitView progressBar ;
    private List<Reference> referenceList;
    private Button btnNewExperience;
    private ImageView imvBack;
    private LinearLayout newLayout;
    private String token ;

    private EditText etRefName, etOrganization, etRefDesignation, etRefEmail, etRefMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        init();

        btnNewExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newLayout.setVisibility(View.VISIBLE);
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getReferences();
    }

    private void init() {
        etRefName = findViewById(R.id.etReferenceName);
        etOrganization = findViewById(R.id.etReferenceOrganization);
        etRefDesignation = findViewById(R.id.etReferenceDesignation);
        etRefEmail = findViewById(R.id.etReferenceEmail);
        etRefMobile = findViewById(R.id.etReferenceMobile);
        imvBack = findViewById(R.id.imvReferenceBack);

        recyclerView = findViewById(R.id.rvReference);
        btnNewExperience = findViewById(R.id.btn_add_reference);
        newLayout = findViewById(R.id.add_new_reference_layout);
        progressBar = findViewById(R.id.reference_spin_kit);
        mainViewModel = new ViewModelProvider(this).get(ReferenceViewModel.class);

        token = "Bearer "+SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        referenceList = new ArrayList<>();
    }

    public void postReference(View view) {

        String mobile = etRefMobile.getText().toString();
        String name = etRefName.getText().toString();
        String designation = etRefDesignation.getText().toString();
        String email = etRefEmail.getText().toString();
        String organization = etOrganization.getText().toString();

        if(mobile.isEmpty() || name.isEmpty() || designation.isEmpty() || email.isEmpty() || organization.isEmpty())
        {
            Reference reference = new Reference(name,designation,organization,email,mobile,1);

            mainViewModel.postAllReference(token,reference).observe(this, new Observer<List<Reference>>() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onChanged(@Nullable List<Reference> referenceList) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    newLayout.setVisibility(View.GONE);
                    mainViewModel.getAllReferences(token).removeObserver(this);
                    Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

        else
        {
            Toast.makeText(this, "Please Fill Up All Information", Toast.LENGTH_SHORT).show();
        }

    }

    public void getReferences() {

        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.getAllReferences(token).observe(this, new Observer<List<Reference>>() {
            @Override
            public void onChanged(@Nullable List<Reference> referenceList) {
                setRecyclerView(referenceList);
            }
        });
    }

    public void updateReference(Reference reference,int id)
    {
        mainViewModel.updateReference(token,id,reference).observe(this, new Observer<List<Reference>>() {
            @Override
            public void onChanged(@Nullable List<Reference> experienceList) {
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                setRecyclerView(referenceList);
                mainViewModel.getAllReferences(token).removeObserver(this);
            }
        });
    }

    public void deleteExperience(int id,int position) {

        progressBar.setVisibility(View.VISIBLE);

        mainViewModel.deleteReference(token,id).observe(this, new Observer<List<Reference>>() {
            @Override
            public void onChanged(@Nullable List<Reference> experienceList) {
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                referenceAdapter.notifyItemRemoved(position);
                setRecyclerView(referenceList);
                mainViewModel.getAllReferences(token).removeObserver(this);
            }
        });
    }

    private void setRecyclerView(List<Reference> referenceList) {

        progressBar.setVisibility(View.GONE);
        referenceAdapter = new ReferenceAdapter(referenceList, new ReferenceAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                ReferenceDialog dialogFragment = new ReferenceDialog();
                String name = referenceList.get(position).getName();
                String designation = referenceList.get(position).getDesignation();
                String organizationName = referenceList.get(position).getOrganization();
                String email = referenceList.get(position).getEmail();
                String mobile = referenceList.get(position).getMobile();
                int id = referenceList.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("DESIGNATION",designation);
                bundle.putString("NAME",name);
                bundle.putString("EMAIL",email);
                bundle.putString("MOBILE",mobile);
                bundle.putString("ORAGANIZATIONNAME",organizationName);
                bundle.putInt("ID",id);
                dialogFragment.setArguments(bundle);

                dialogFragment.show((ActivityReference.this).getSupportFragmentManager(),"Image Dialog");
            }

            @Override
            public void deleteItem(int position) {
                int id = referenceList.get(position).getId();
                deleteExperience(id,position);
            }
        });
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(referenceAdapter);
        referenceAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateStatus(Reference reference,int id) {
        updateReference(reference,id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}