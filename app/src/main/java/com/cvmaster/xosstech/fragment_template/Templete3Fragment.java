package com.cvmaster.xosstech.fragment_template;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.BuildResumePDF_Part1;
import com.cvmaster.xosstech.ChargingActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.ResumeTemplate;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.viewmodel.Templete3ViewModel;

import java.io.File;

public class Templete3Fragment extends Fragment implements View.OnClickListener{

    Button button_Back3;
    Button button_CompletePayment3;
    private ViewFlipper viewFlipper;
    private Templete3ViewModel mViewModel;
    //Template 3 Start
    ImageView imageView_Template3_image;
    TextView textView_Template3_name;
    TextView textView_Template3_careerObjective;
    TextView textView_Template3_contactNumber;
    TextView textView_Template3_email;
    TextView textView_Template3_curresntAddress;
    //Education
    RelativeLayout relativeLayout_Template3_RL_SSC;
    TextView textView_Template3_tv_SSC_Heading;
    TextView textView_Template3_SSC_Year;
    TextView textView_Template3_tv_SSC_Institute_Name;
    TextView textView_Template3_tv_SSC_Subject_Name;
    TextView textView_Template3_tv_SSC_Result;

    RelativeLayout relativeLayout_Template3_RL_HSC;
    TextView textView_Template3_tv_HSC_Heading;
    TextView textView_Template3_HSC_Year;
    TextView textView_Template3_tv_HSC_Institute_Name;
    TextView textView_Template3_tv_HSC_Subject_Name;
    TextView textView_Template3_tv_HSC_Result;

    RelativeLayout relativeLayout_Template3_RL_Univarsity_Name;
    TextView textView_Template3_tv_Univarsity_Heading;
    TextView textView_Template3_Honrs_Year;
    TextView textView_Template3_tv_Honrs_Institute_Name;
    TextView textView_Template3_tv_Honrs_Subject_Name;
    TextView textView_Template3_tv_Honrs_Result;

    RelativeLayout relativeLayout_Template3_RL_Masters;
    TextView textView_Template3_tv_Masters_Heading;
    TextView textView_Template3_Master_Year;
    TextView textView_Template3_tv_Masters_Institute_Name;
    TextView textView_Template3_tv_Masters_Subject_Name;
    TextView textView_Template3_tv_Masters_Result;

    //Education End

    TextView textView_Template3_skill1;
    TextView textView_Template3_skill2;
    TextView textView_Template3_skill3;
    TextView textView_Template3_skill4;
    TextView textView_Template3_skill5;
    TextView textView_Template3_skill6;

    TextView textView_Template3_skillEng;
    TextView textView_Template3_skillBangla;

    //Work Exp
    RelativeLayout relativeLayout_Template3_WorkExpHeading;

    RelativeLayout relativeLayout_Template3_WorkExp1;
    TextView textView_Template3_WorkExp1Designation;
    TextView textView_Template3_WorkExp1Duration;
    TextView textView_Template3_WorkExp1OrgName;
    TextView textView_Template3_WorkExp1OrgAddress;

    RelativeLayout relativeLayout_Template3_WorkExp2;
    TextView textView_Template3_WorkExp2Designation;
    TextView textView_Template3_WorkExp2Duration;
    TextView textView_Template3_WorkExp2OrgName;
    TextView textView_Template3_WorkExp2OrgAddress;

    //Reference
    LinearLayout linearLayout_Template3_ReferenceHeading;

    RelativeLayout relativeLayout_Template3_Ref1;
    TextView textView_Template3_Ref1Name;
    TextView textView_Template3_Ref1Designation;
    TextView textView_Template3_Ref1OrgName;
    TextView textView_Template3_Ref1Email;
    TextView textView_Template3_Ref1Mobile;

    RelativeLayout relativeLayout_Template3_Ref2;
    TextView textView_Template3_Ref2Name;
    TextView textView_Template3_Ref2Designation;
    TextView textView_Template3_Ref2OrgName;
    TextView textView_Template3_Ref2Email;
    TextView textView_Template3_Ref2Mobile;



    //Template 3 End


    public static Templete3Fragment newInstance() {
        return new Templete3Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.templete3_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Templete3ViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Template 3
        File file3 = new File(ResumeProfilePart1.getImagePath());

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        imageView_Template3_image = (ImageView) view.findViewById(R.id.template3_image);
        imageView_Template3_image.setImageURI(Uri.fromFile(file3));

        textView_Template3_name = (TextView) view.findViewById(R.id.tv_heading_name3);
        textView_Template3_name.setText(ResumeProfilePart1.getName());

        textView_Template3_careerObjective = (TextView) view.findViewById(R.id.tv_Career_Objective_Container3);
        textView_Template3_careerObjective.setText(ResumeProfilePart2.career_objective);

        textView_Template3_contactNumber = (TextView) view.findViewById(R.id.tv_number3);
        textView_Template3_contactNumber.setText(ResumeProfilePart1.getContact_number());

        textView_Template3_email = (TextView) view.findViewById(R.id.tv_email3);
        textView_Template3_email.setText(ResumeProfilePart1.getEmail());

        textView_Template3_curresntAddress = (TextView) view.findViewById(R.id.tv_Address3);
        textView_Template3_curresntAddress.setText(ResumeProfilePart1.getPresent_address());


        //Education Start

        relativeLayout_Template3_RL_SSC = (RelativeLayout) view.findViewById(R.id.RL_SSC);
        relativeLayout_Template3_RL_SSC.setVisibility(View.GONE);

        textView_Template3_tv_SSC_Heading = (TextView) view.findViewById(R.id.tv_SSC_Heading);
        textView_Template3_SSC_Year = (TextView) view.findViewById(R.id.SSC_Year);
        textView_Template3_tv_SSC_Institute_Name = (TextView) view.findViewById(R.id.tv_SSC_Institute_Name);
        textView_Template3_tv_SSC_Subject_Name = (TextView) view.findViewById(R.id.tv_SSC_Subject_Name);
        textView_Template3_tv_SSC_Result = (TextView) view.findViewById(R.id.tv_SSC_Result);

        relativeLayout_Template3_RL_HSC = (RelativeLayout) view.findViewById(R.id.RL_HSC);
        relativeLayout_Template3_RL_HSC.setVisibility(View.GONE);

        textView_Template3_tv_HSC_Heading = (TextView) view.findViewById(R.id.tv_HSC_Heading);
        textView_Template3_HSC_Year = (TextView) view.findViewById(R.id.HSC_Year);
        textView_Template3_tv_HSC_Institute_Name = (TextView) view.findViewById(R.id.tv_HSC_Institute_Name);
        textView_Template3_tv_HSC_Subject_Name = (TextView) view.findViewById(R.id.tv_HSC_Subject_Name);
        textView_Template3_tv_HSC_Result = (TextView) view.findViewById(R.id.tv_HSC_Result);

        relativeLayout_Template3_RL_Univarsity_Name = (RelativeLayout) view.findViewById(R.id.RL_Univarsity_Name);
        relativeLayout_Template3_RL_Univarsity_Name.setVisibility(View.GONE);

        textView_Template3_tv_Univarsity_Heading = (TextView) view.findViewById(R.id.tv_Univarsity_Heading);
        textView_Template3_Honrs_Year = (TextView) view.findViewById(R.id.Honrs_Year);
        textView_Template3_tv_Honrs_Institute_Name = (TextView) view.findViewById(R.id.tv_Honrs_Institute_Name);
        textView_Template3_tv_Honrs_Subject_Name = (TextView) view.findViewById(R.id.tv_Honrs_Subject_Name);
        textView_Template3_tv_Honrs_Result = (TextView) view.findViewById(R.id.tv_Honrs_Result);

        relativeLayout_Template3_RL_Masters = (RelativeLayout) view.findViewById(R.id.RL_Masters);
        relativeLayout_Template3_RL_Masters.setVisibility(View.GONE);

        textView_Template3_tv_Masters_Heading = (TextView) view.findViewById(R.id.tv_Masters_Heading);
        textView_Template3_Master_Year = (TextView) view.findViewById(R.id.Master_Year);
        textView_Template3_tv_Masters_Institute_Name = (TextView) view.findViewById(R.id.tv_Masters_Institute_Name);
        textView_Template3_tv_Masters_Subject_Name = (TextView) view.findViewById(R.id.tv_Masters_Subject_Name);
        textView_Template3_tv_Masters_Result = (TextView) view.findViewById(R.id.tv_Masters_Result);



        if (ResumeProfilePart2.educationQualification.size()>0){
            relativeLayout_Template3_RL_SSC.setVisibility(View.VISIBLE);
            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(0);

            textView_Template3_tv_SSC_Heading.setText(educationQualification_model.getQualification_name());
            textView_Template3_SSC_Year.setText(educationQualification_model.getPassing_year());
            textView_Template3_tv_SSC_Institute_Name.setText(educationQualification_model.getInstitute_name());
            textView_Template3_tv_SSC_Subject_Name.setText("Subject: "+educationQualification_model.getGroupsubject_name());
            textView_Template3_tv_SSC_Result.setText("Result: "+educationQualification_model.getResult());
        }

        if (ResumeProfilePart2.educationQualification.size()>1){
            relativeLayout_Template3_RL_HSC.setVisibility(View.VISIBLE);
            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(1);

            textView_Template3_tv_HSC_Heading.setText(educationQualification_model.getQualification_name());
            textView_Template3_HSC_Year.setText(educationQualification_model.getPassing_year());
            textView_Template3_tv_HSC_Institute_Name.setText(educationQualification_model.getInstitute_name());
            textView_Template3_tv_HSC_Subject_Name.setText("Subject: "+educationQualification_model.getGroupsubject_name());
            textView_Template3_tv_HSC_Result.setText("Result: "+educationQualification_model.getResult());
        }

        if (ResumeProfilePart2.educationQualification.size()>2){
            relativeLayout_Template3_RL_Univarsity_Name.setVisibility(View.VISIBLE);
            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(2);

            textView_Template3_tv_Univarsity_Heading.setText(educationQualification_model.getQualification_name());
            textView_Template3_Honrs_Year.setText(educationQualification_model.getPassing_year());
            textView_Template3_tv_Honrs_Institute_Name.setText(educationQualification_model.getInstitute_name());
            textView_Template3_tv_Honrs_Subject_Name.setText("Subject: "+educationQualification_model.getGroupsubject_name());
            textView_Template3_tv_Honrs_Result.setText("Result: "+educationQualification_model.getResult());
        }

        if (ResumeProfilePart2.educationQualification.size()>3){
            relativeLayout_Template3_RL_Masters.setVisibility(View.VISIBLE);
            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(3);

            textView_Template3_tv_Masters_Heading.setText(educationQualification_model.getQualification_name());
            textView_Template3_Master_Year.setText(educationQualification_model.getPassing_year());
            textView_Template3_tv_Masters_Institute_Name.setText(educationQualification_model.getInstitute_name());
            textView_Template3_tv_Masters_Subject_Name.setText("Subject: "+educationQualification_model.getGroupsubject_name());
            textView_Template3_tv_Masters_Result.setText("Result: "+educationQualification_model.getResult());
        }

        //Education End

        textView_Template3_skill1 = (TextView) view.findViewById(R.id.tv_Skills_Template3_1);
        textView_Template3_skill1.setVisibility(View.INVISIBLE);
        textView_Template3_skill2 = (TextView) view.findViewById(R.id.tv_Skills_Template3_2);
        textView_Template3_skill2.setVisibility(View.INVISIBLE);
        textView_Template3_skill3 = (TextView) view.findViewById(R.id.tv_Skills_Template3_3);
        textView_Template3_skill3.setVisibility(View.INVISIBLE);
        textView_Template3_skill4 = (TextView) view.findViewById(R.id.tv_Skills_Template3_4);
        textView_Template3_skill4.setVisibility(View.INVISIBLE);
        textView_Template3_skill5 = (TextView) view.findViewById(R.id.tv_Skills_Template3_5);
        textView_Template3_skill5.setVisibility(View.INVISIBLE);
        textView_Template3_skill6 = (TextView) view.findViewById(R.id.tv_Skills_Template3_6);
        textView_Template3_skill6.setVisibility(View.INVISIBLE);


        if (ResumeProfilePart3.skills.size()>0){
            textView_Template3_skill1.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(0);
            textView_Template3_skill1.setText(skills_model.getSkill());
        }

        if (ResumeProfilePart3.skills.size()>1){
            textView_Template3_skill2.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(1);
            textView_Template3_skill2.setText(skills_model.getSkill());
        }

        if (ResumeProfilePart3.skills.size()>2){
            textView_Template3_skill3.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(2);
            textView_Template3_skill3.setText(skills_model.getSkill());
        }

        if (ResumeProfilePart3.skills.size()>3){
            textView_Template3_skill4.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(3);
            textView_Template3_skill4.setText(skills_model.getSkill());
        }

        if (ResumeProfilePart3.skills.size()>4){
            textView_Template3_skill5.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(4);
            textView_Template3_skill5.setText(skills_model.getSkill());
        }

        if (ResumeProfilePart3.skills.size()>5){
            textView_Template3_skill6.setVisibility(View.VISIBLE);
            Skills_Model skills_model = ResumeProfilePart3.skills.get(5);
            textView_Template3_skill6.setText(skills_model.getSkill());
        }

        textView_Template3_skillEng = (TextView) view.findViewById(R.id.tv_Language_English_Proficiency);
        textView_Template3_skillEng.setText(ResumeProfilePart3.english_skill_level);

        textView_Template3_skillBangla = (TextView) view.findViewById(R.id.tv_Language_Bangla_Proficiency);
        textView_Template3_skillBangla.setText(ResumeProfilePart3.bangla_skill_level);

        //Work Exp
        relativeLayout_Template3_WorkExpHeading = (RelativeLayout) view.findViewById(R.id.RL_Work_Exp_Heading);
        relativeLayout_Template3_WorkExpHeading.setVisibility(View.GONE);

        relativeLayout_Template3_WorkExp1 = (RelativeLayout) view.findViewById(R.id.RL_Work_Exp_1);
        relativeLayout_Template3_WorkExp1.setVisibility(View.GONE);

        textView_Template3_WorkExp1Designation = (TextView) view.findViewById(R.id.tv_Work_Exp_1_Heading);
        textView_Template3_WorkExp1Duration = (TextView) view.findViewById(R.id.Work_Exp_1_Year);
        textView_Template3_WorkExp1OrgName = (TextView) view.findViewById(R.id.tv_Work_Exp_1_Org_Name);
        textView_Template3_WorkExp1OrgAddress = (TextView) view.findViewById(R.id.tv_Work_Exp_1_Org_Address);

        relativeLayout_Template3_WorkExp2 = (RelativeLayout) view.findViewById(R.id.RL_Work_Exp_2);
        relativeLayout_Template3_WorkExp2.setVisibility(View.GONE);

        textView_Template3_WorkExp2Designation = (TextView) view.findViewById(R.id.tv_Work_Exp_2_Heading);
        textView_Template3_WorkExp2Duration = (TextView) view.findViewById(R.id.Work_Exp_2_Year);
        textView_Template3_WorkExp2OrgName = (TextView) view.findViewById(R.id.tv_Work_Exp_2_Org_Name);
        textView_Template3_WorkExp2OrgAddress = (TextView) view.findViewById(R.id.tv_Work_Exp_2_Org_Address);



        if (ResumeProfilePart6.workExperience.size()>0){
            relativeLayout_Template3_WorkExpHeading.setVisibility(View.VISIBLE);
            relativeLayout_Template3_WorkExp1.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_model = ResumeProfilePart6.workExperience.get(0);
            textView_Template3_WorkExp1Designation.setText(workExperience_model.getDesignationName());
            textView_Template3_WorkExp1Duration.setText(workExperience_model.getDurationTime());
            textView_Template3_WorkExp1OrgName.setText(workExperience_model.getOrganizationName());
            textView_Template3_WorkExp1OrgAddress.setText(workExperience_model.getOgganizationAddress());
        }

        if (ResumeProfilePart6.workExperience.size()>1){
            relativeLayout_Template3_WorkExp2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_model = ResumeProfilePart6.workExperience.get(1);
            textView_Template3_WorkExp2Designation.setText(workExperience_model.getDesignationName());
            textView_Template3_WorkExp2Duration.setText(workExperience_model.getDurationTime());
            textView_Template3_WorkExp2OrgName.setText(workExperience_model.getOrganizationName());
            textView_Template3_WorkExp2OrgAddress.setText(workExperience_model.getOgganizationAddress());
        }


        //refrrence
        linearLayout_Template3_ReferenceHeading = (LinearLayout) view.findViewById(R.id.Linear_Reference_Heading);
        linearLayout_Template3_ReferenceHeading.setVisibility(View.GONE);

        relativeLayout_Template3_Ref1 = (RelativeLayout) view.findViewById(R.id.RL_Reference_1);
        relativeLayout_Template3_Ref1.setVisibility(View.GONE);

        textView_Template3_Ref1Name = (TextView) view.findViewById(R.id.tv_Reference_1_Name);
        textView_Template3_Ref1Designation = (TextView) view.findViewById(R.id.tv_Reference_1_Designation);
        textView_Template3_Ref1OrgName = (TextView) view.findViewById(R.id.tv_Reference_1_Company_Name);
        textView_Template3_Ref1Email = (TextView) view.findViewById(R.id.tv_Reference_1_Email);
        textView_Template3_Ref1Mobile = (TextView) view.findViewById(R.id.tv_Reference_1_Number);

        relativeLayout_Template3_Ref2 = (RelativeLayout) view.findViewById(R.id.RL_Reference_2);
        relativeLayout_Template3_Ref2.setVisibility(View.GONE);
        textView_Template3_Ref2Name = (TextView) view.findViewById(R.id.tv_Reference_2_Name);;
        textView_Template3_Ref2Designation = (TextView) view.findViewById(R.id.tv_Reference_2_Designation);
        textView_Template3_Ref2OrgName = (TextView) view.findViewById(R.id.tv_Reference_2_Company_Name);
        textView_Template3_Ref2Email= (TextView) view.findViewById(R.id.tv_Reference_2_Email);
        textView_Template3_Ref2Mobile = (TextView) view.findViewById(R.id.tv_Reference_2_Number);


        if (ResumeProfilePart5.reference.size()>0){
            linearLayout_Template3_ReferenceHeading.setVisibility(View.VISIBLE);
            relativeLayout_Template3_Ref1.setVisibility(View.VISIBLE);

            Reference_Model reference_model = ResumeProfilePart5.reference.get(0);
            textView_Template3_Ref1Name.setText(reference_model.getName());
            textView_Template3_Ref1Designation.setText(reference_model.getDesignation());
            textView_Template3_Ref1OrgName.setText(reference_model.getOrganization_name());
            textView_Template3_Ref1Email.setText("Email: "+reference_model.getEmail());
            textView_Template3_Ref1Mobile.setText("Contact: "+reference_model.getMobile_number());
        }

        if (ResumeProfilePart5.reference.size()>1){
            relativeLayout_Template3_Ref2.setVisibility(View.VISIBLE);

            Reference_Model reference_model = ResumeProfilePart5.reference.get(1);
            textView_Template3_Ref2Name.setText(reference_model.getName());
            textView_Template3_Ref2Designation.setText(reference_model.getDesignation());
            textView_Template3_Ref2OrgName.setText(reference_model.getOrganization_name());
            textView_Template3_Ref2Email.setText("Email: "+reference_model.getEmail());
            textView_Template3_Ref2Mobile.setText("Contact: "+reference_model.getMobile_number());
        }


        button_Back3 = (Button) view.findViewById(R.id.back3);
        button_Back3.setOnClickListener(this);
        button_CompletePayment3 = (Button) view.findViewById(R.id.completePayment3);
        button_CompletePayment3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == button_Back3){
            startActivity(new Intent(getContext(), BuildResumePDF_Part1.class));

          /*  viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
            viewFlipper.setDisplayedChild(1);*/
        }
        if (v == button_CompletePayment3){
            ResumeTemplate.templateName = "template3";
            verifyTheProcess();
            /*
            Intent intent = new Intent(getApplicationContext(), Template2_pdf.class);
            startActivity(intent);
            */
        }
    }

    private void verifyTheProcess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("WANT TO COMPLETE CHARGING FOR THE PDF?");
        builder.setMessage("Are You Sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GoToNextIntent();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void GoToNextIntent(){
       // finish();
        Intent intent = new Intent(getContext(), ChargingActivity.class);
        startActivity(intent);
    }
}