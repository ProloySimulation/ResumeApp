package com.cvmaster.xosstech.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.ResumeProfileProjects;
import com.cvmaster.xosstech.ResumeProfileTrainings;
import com.cvmaster.xosstech.adapter.EducationAdapter;
import com.cvmaster.xosstech.adapter.ExperienceAdapter;
import com.cvmaster.xosstech.adapter.ProjectsAdapter;
import com.cvmaster.xosstech.adapter.ReferenceAdapter;
import com.cvmaster.xosstech.adapter.TrainingAdapter;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Projects_model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    private RecyclerView rvEdu,rvExp,rvRef,rvProjects,rvTrainings;
    private RecyclerView.Adapter adapterEdu,adapterRef,adapterExp,adapterProjetcs,adapterTrainings;
    private LinearLayoutManager linearLayoutManageredu,linearLayoutManagerExp,linearLayoutManagerRef,
            linearLayoutManagerProjects,linearLayoutManagerTrainings;
    private DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);

        rvEdu = findViewById(R.id.rvEdu);
        rvExp = findViewById(R.id.rvExp);
        rvRef = findViewById(R.id.rvRef);
        rvProjects = findViewById(R.id.rvProjects);
        rvTrainings = findViewById(R.id.rvTraining);

       /* items = new ArrayList<>();

        List<EducationQualification_Model> edu_model = ResumeProfilePart2.educationQualification;
        for(int i = 0;i < ResumeProfilePart2.educationQualification.size();i++)
        {
            EducationQualification_Model edu_mod = edu_model.get(i);
            items.add(new Item(0,edu_mod));
        }


        List<Reference_Model> reference_models = ResumeProfilePart5.reference;
        for(int i = 0;i < ResumeProfilePart5.reference.size();i++)
        {
            Reference_Model ref_mod = reference_models.get(i);
            items.add(new Item(1,ref_mod));
        }*/

        List<EducationQualification_Model> edu_model = ResumeProfilePart2.educationQualification;

        if(edu_model.size()>0)
        {
            adapterEdu = new EducationAdapter(getApplicationContext(),edu_model);

            linearLayoutManageredu = new LinearLayoutManager(getApplicationContext());
            linearLayoutManageredu.setOrientation(LinearLayoutManager.VERTICAL);
            dividerItemDecoration = new DividerItemDecoration(rvEdu.getContext(), linearLayoutManageredu.getOrientation());

            rvEdu.setHasFixedSize(true);
            rvEdu.setLayoutManager(linearLayoutManageredu);
            rvEdu.addItemDecoration(dividerItemDecoration);
            rvEdu.setAdapter(adapterEdu);
        }


        List<WorkExperience_Model> workExperienceModels = ResumeProfilePart6.workExperience;
        if(workExperienceModels.size()>0)
        {
            adapterExp = new ExperienceAdapter(getApplicationContext(),workExperienceModels);

            linearLayoutManagerExp = new LinearLayoutManager(getApplicationContext());
            linearLayoutManageredu.setOrientation(LinearLayoutManager.VERTICAL);
            dividerItemDecoration = new DividerItemDecoration(rvExp.getContext(), linearLayoutManagerExp.getOrientation());

            rvExp.setHasFixedSize(true);
            rvExp.setLayoutManager(linearLayoutManagerExp);
            rvExp.addItemDecoration(dividerItemDecoration);
            rvExp.setAdapter(adapterExp);
        }


        List<Reference_Model> reference_models = ResumeProfilePart5.reference;
        if(reference_models.size()>0)
        {
            adapterRef = new ReferenceAdapter(getApplicationContext(),reference_models);

            linearLayoutManagerRef = new LinearLayoutManager(getApplicationContext());
            linearLayoutManagerRef.setOrientation(LinearLayoutManager.VERTICAL);
            dividerItemDecoration = new DividerItemDecoration(rvRef.getContext(), linearLayoutManagerRef.getOrientation());

            rvRef.setHasFixedSize(true);
            rvRef.setLayoutManager(linearLayoutManagerRef);
            rvRef.addItemDecoration(dividerItemDecoration);
            rvRef.setAdapter(adapterRef);
        }


        List<Projects_model> projects_models = ResumeProfileProjects.projectsList;
        if(projects_models.size()>0)
        {
            adapterProjetcs = new ProjectsAdapter(getApplicationContext(),projects_models);

            linearLayoutManagerProjects = new LinearLayoutManager(getApplicationContext());
            linearLayoutManagerProjects.setOrientation(LinearLayoutManager.VERTICAL);
            dividerItemDecoration = new DividerItemDecoration(rvRef.getContext(), linearLayoutManagerProjects.getOrientation());

            rvProjects.setHasFixedSize(true);
            rvProjects.setLayoutManager(linearLayoutManagerProjects);
            rvProjects.addItemDecoration(dividerItemDecoration);
            rvProjects.setAdapter(adapterProjetcs);
        }


        List<Training> trainingList = ResumeProfileTrainings.trainingList;
        if(trainingList.size()>0)
        {
            adapterTrainings = new TrainingAdapter(getApplicationContext(),trainingList);

            linearLayoutManagerTrainings = new LinearLayoutManager(getApplicationContext());
            linearLayoutManagerTrainings.setOrientation(LinearLayoutManager.VERTICAL);
            dividerItemDecoration = new DividerItemDecoration(rvTrainings.getContext(), linearLayoutManagerTrainings.getOrientation());

            rvTrainings.setHasFixedSize(true);
            rvTrainings.setLayoutManager(linearLayoutManagerTrainings);
            rvTrainings.addItemDecoration(dividerItemDecoration);
            rvTrainings.setAdapter(adapterProjetcs);
        }

    }
}