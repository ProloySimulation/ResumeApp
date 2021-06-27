package com.cvmaster.xosstech.fragment_template;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
import com.cvmaster.xosstech.viewmodel.Template6ViewModel;
import com.cvmaster.xosstech.viewmodel.Templete4ViewModel;
import com.itextpdf.text.pdf.parser.Line;

import java.io.File;
import java.util.List;


public class Template6Fragment extends Fragment implements View.OnClickListener {

    private ViewFlipper viewFlipper;
    private Button button_Back2;
    private Button getButton_CompletePayment2;

    private ImageView imageView2;

    private TextView name2;
    private TextView career_objective2;
    private TextView email2;
    private TextView location2;
    private TextView contact2;
    private TextView tvJobTitle;

    private TextView skills2;

    private TextView bangla_skill2;
    private TextView english_skill2;

    private RelativeLayout linearLayout_Head2;

    private LinearLayout linearLayout_layout1;
    private TextView tv_ref1_name;
    private TextView tv_ref1_designation;
    private TextView tv_ref1_company;
    private TextView tv_ref1_email;
    private TextView tv_ref1_contactnumber;

    private LinearLayout linearLayout_layout2;
    private TextView tv_ref2_name;
    private TextView tv_ref2_designation;
    private TextView tv_ref2_company;
    private TextView tv_ref2_email;
    private TextView tv_ref2_contactnumber;

    private LinearLayout linearLayout_ProfessionalExp_Head;

    //    private RelativeLayout ll_prexp1;
    private TextView tv_pr_exp1_position;
    private TextView tv_pr_exp1_company;
    private TextView tv_pr_exp1_company_address;
    private TextView tv_pr_exp1_service_time;
    private TextView tv_pr_exp1_service_detail;

    private LinearLayout ll_prexp2;
    private TextView tv_pr_exp2_position;
    private TextView tv_pr_exp2_company;
    private TextView tv_pr_exp2_company_address;
    private TextView tv_pr_exp2_service_time;
    private TextView tv_pr_exp2_service_detail;

    private RelativeLayout ll_prexp3;
    private TextView tv_pr_exp3_position;
    private TextView tv_pr_exp3_company;
    private TextView tv_pr_exp3_company_address;
    private TextView tv_pr_exp3_service_time;
    private TextView tv_pr_exp3_service_detail;

    private RelativeLayout ll_prexp4;
    private TextView tv_pr_exp4_position;
    private TextView tv_pr_exp4_company;
    private TextView tv_pr_exp4_company_address;
    private TextView tv_pr_exp4_service_time;
    private TextView tv_pr_exp4_service_detail;

    private RelativeLayout linearlayout_EducationalQualification_1;
    private TextView tv_educationQualification_1;
    private TextView tv_instituteName_1;
    private TextView tv_passingYear_1;
    private TextView tv_Result_1;
    private TextView tv_boardName_1;

    private LinearLayout linearlayout_EducationalQualification_2;
    private TextView tv_educationQualification_2;
    private TextView tv_instituteName_2;
    private TextView tv_passingYear_2;
    private TextView tv_Result_2;
    private TextView tv_boardName_2;

    private LinearLayout linearlayout_EducationalQualification_3;
    private TextView tv_educationQualification_3;
    private TextView tv_instituteName_3;
    private TextView tv_passingYear_3;
    private TextView tv_Result_3;
    private TextView tv_boardName_3;

    private LinearLayout linearlayout_EducationalQualification_4;
    private TextView tv_educationQualification_4;
    private TextView tv_instituteName_4;
    private TextView tv_passingYear_4;
    private TextView tv_Result_4;
    private TextView tv_boardName_4;

    private LinearLayout linearlayout_EducationalQualification_5;
    private TextView tv_educationQualification_5;
    private TextView tv_instituteName_5;
    private TextView tv_passingYear_5;
    private TextView tv_Result_5;
    private TextView tv_boardName_5;

    private TextView tvFacebook;
    private TextView tvLinkedin;

    private Template6ViewModel mViewModel;

    public static Templete4Fragment newInstance() {
        return new Templete4Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_template6, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Template6ViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        File file = new File(ResumeProfilePart1.getImagePath());
        List<EducationQualification_Model> educationQualification_models = ResumeProfilePart2.educationQualification;

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);

        button_Back2 = (Button) view.findViewById(R.id.back2_template6);
        button_Back2.setOnClickListener(this);

        getButton_CompletePayment2 = (Button) view.findViewById(R.id.completePayment2_template6);
        getButton_CompletePayment2.setOnClickListener(this);


        imageView2 = (ImageView) view.findViewById(R.id.image_person_template6);
        imageView2.setImageURI(Uri.fromFile(file));

//        tvFacebook = view.findViewById(R.id.contact_fb);
//        tvLinkedin = view.findViewById(R.id.tv_contact_linkedin5);

        name2 = (TextView) view.findViewById(R.id.tv_person_name_template6);
        career_objective2 = (TextView) view.findViewById(R.id.tv_profile_description_template6);
        email2 = (TextView) view.findViewById(R.id.tv_email_template6);
        location2 = (TextView) view.findViewById(R.id.tv_address_template6);
        contact2 = (TextView) view.findViewById(R.id.tv_phone_template6);
        tvJobTitle = view.findViewById(R.id.job_title_main_template6);

        name2.setText(ResumeProfilePart1.getName());
        career_objective2.setText(ResumeProfilePart2.career_objective);
        email2.setText(ResumeProfilePart1.getEmail());
        location2.setText(ResumeProfilePart1.getPresent_address());
        contact2.setText(ResumeProfilePart1.getContact_number());
//        tvFacebook.setText(ResumeProfilePart1.getfacebook_id());
/*        tvLinkedin.setText(ResumeProfilePart1.getlinkedin_id());
        tvJobTitle.setText(ResumeProfilePart1.getJobTitle());*/

        skills2 = (TextView) view.findViewById(R.id.tv_skills_msword_template6);

        String _skills2 = "";

        for (int i = 0; i < ResumeProfilePart3.skills.size() - 1; i++) {
            Skills_Model skills_mod = ResumeProfilePart3.skills.get(i);
            _skills2 = _skills2 + skills_mod.getSkill() + "\n";
        }
        Skills_Model skills_mode = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size() - 1);
        _skills2 = _skills2 + skills_mode.getSkill();
        skills2.setText(_skills2);

        bangla_skill2 = (TextView) view.findViewById(R.id.tv_lan_bangla_pr_template6);
        english_skill2 = (TextView) view.findViewById(R.id.tv_lan_english_pr_template6);

        bangla_skill2.setText(ResumeProfilePart3.bangla_skill_level);
        english_skill2.setText(ResumeProfilePart3.english_skill_level);

        linearLayout_Head2 = (RelativeLayout) view.findViewById(R.id.rl_references_template6);

//        linearLayout_layout1 = (LinearLayout) view.findViewById(R.id.linearLayout_Reference2_1);
        tv_ref1_name = (TextView) view.findViewById(R.id.tv_ref1_name_template6);
        tv_ref1_designation = (TextView) view.findViewById(R.id.tv_ref1_designation_template6);
        tv_ref1_company = (TextView) view.findViewById(R.id.tv_ref1_company_template6);
        tv_ref1_email = (TextView) view.findViewById(R.id.tv_ref1_email_template6);
        tv_ref1_contactnumber = (TextView) view.findViewById(R.id.tv_ref1_contactnumber_template6);
/*
        linearLayout_layout2 = (LinearLayout) view.findViewById(R.id.linearLayout_Reference2_2);
        tv_ref2_name = (TextView) view.findViewById(R.id.tv_ref2_name);
        tv_ref2_designation = (TextView) view.findViewById(R.id.tv_ref2_designation);
        tv_ref2_company = (TextView) view.findViewById(R.id.tv_ref2_company);
        tv_ref2_email = (TextView) view.findViewById(R.id.tv_ref2_email);
        tv_ref2_contactnumber = (TextView) view.findViewById(R.id.tv_ref2_contactnumber);*/

        List<Reference_Model> reference_model = ResumeProfilePart5.reference;

        if (ResumeProfilePart5.reference.size() > 0) {
            linearLayout_Head2.setVisibility(View.VISIBLE);

//            linearLayout_layout1.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(0);

            tv_ref1_name.setText(reference_mod.getName());
            tv_ref1_designation.setText(reference_mod.getDesignation());
            tv_ref1_company.setText(reference_mod.getDesignation());
            tv_ref1_email.setText(reference_mod.getEmail());
            tv_ref1_contactnumber.setText(reference_mod.getMobile_number());
        }

     /*   if (ResumeProfilePart5.reference.size() > 1) {
            linearLayout_layout2.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(1);

            tv_ref2_name.setText(reference_mod.getName());
            tv_ref2_designation.setText(reference_mod.getDesignation());
            tv_ref2_company.setText(reference_mod.getDesignation());
            tv_ref2_email.setText(reference_mod.getEmail());
            tv_ref2_contactnumber.setText(reference_mod.getMobile_number());
        }*/

        linearLayout_ProfessionalExp_Head = (LinearLayout) view.findViewById(R.id.ll_work_exp1_template6);

//        ll_prexp1 = (RelativeLayout) view.findViewById(R.id.rl_WorkExp_template4);
        tv_pr_exp1_position = (TextView) view.findViewById(R.id.tv_wrk_exp1_job_title_template6);
        tv_pr_exp1_company = (TextView) view.findViewById(R.id.tv_wrk_exp1_cname_template6);
        tv_pr_exp1_company_address = (TextView) view.findViewById(R.id.tv_wrk_exp1_location_template6);
        tv_pr_exp1_service_time = (TextView) view.findViewById(R.id.tv_wrk_exp1_duration_template6);
        tv_pr_exp1_service_detail = view.findViewById(R.id.tv_wrk_exp1_description_template6);

        ll_prexp2 = (LinearLayout) view.findViewById(R.id.ll_work_exp2_template6);
        tv_pr_exp2_position = (TextView) view.findViewById(R.id.tv_wrk_exp2_job_title_template6);
        tv_pr_exp2_company = (TextView) view.findViewById(R.id.tv_wrk_exp2_cname_template6);
        tv_pr_exp2_company_address = (TextView) view.findViewById(R.id.tv_wrk_exp2_location_template6);
        tv_pr_exp2_service_time = (TextView) view.findViewById(R.id.tv_wrk_exp2_duration_template6);
        tv_pr_exp2_service_detail = view.findViewById(R.id.tv_wrk_exp2_description_template6);

        ll_prexp3 = (RelativeLayout) view.findViewById(R.id.rl_work_exp3_template5);
        tv_pr_exp3_position = (TextView) view.findViewById(R.id.tv_wrk_exp2_job_title5);
        tv_pr_exp3_company = (TextView) view.findViewById(R.id.tv_wrk_exp3_cname5);
        tv_pr_exp3_company_address = (TextView) view.findViewById(R.id.tv_wrk_exp3_location5);
        tv_pr_exp3_service_time = (TextView) view.findViewById(R.id.tv_wrk_exp3_duration5);
        tv_pr_exp3_service_detail = view.findViewById(R.id.tv_wrk_exp3_description5);

        ll_prexp4 = (RelativeLayout) view.findViewById(R.id.rl_work_exp4_template5);
        tv_pr_exp4_position = (TextView) view.findViewById(R.id.tv_wrk_exp3_job_title_template6);
        tv_pr_exp4_company = (TextView) view.findViewById(R.id.tv_wrk_exp3_cname_template6);
        tv_pr_exp4_company_address = (TextView) view.findViewById(R.id.tv_wrk_exp3_location_template6);
        tv_pr_exp4_service_time = (TextView) view.findViewById(R.id.tv_wrk_exp3_duration_template6);
        tv_pr_exp4_service_detail = view.findViewById(R.id.tv_wrk_exp3_description_template6);


        List<WorkExperience_Model> workExperience_model = ResumeProfilePart6.workExperience;
        if (ResumeProfilePart6.workExperience.size() > 0) {
            linearLayout_ProfessionalExp_Head.setVisibility(View.VISIBLE);

//            ll_prexp1.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(0);
            tv_pr_exp1_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp1_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp1_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp1_service_time.setText(workExperience_mod.getDurationTime());
            tv_pr_exp1_service_detail.setText(workExperience_mod.getworkDetail());

        }

        if (ResumeProfilePart6.workExperience.size() > 1) {

            ll_prexp2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp2_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp2_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp2_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp2_service_time.setText(workExperience_mod.getDurationTime());
            tv_pr_exp2_service_detail.setText(workExperience_mod.getworkDetail());

        }

        linearlayout_EducationalQualification_1 = (RelativeLayout) view.findViewById(R.id.rl_education_template6);
        tv_educationQualification_1 = (TextView) view.findViewById(R.id.tv_educationQualification_1_template6);
        tv_instituteName_1 = (TextView) view.findViewById(R.id.tv_instituteName_1_template6);
        tv_passingYear_1 = (TextView) view.findViewById(R.id.tv_passingYear_1_template6);
        tv_Result_1 = (TextView) view.findViewById(R.id.tv_Result_1_template6); // Remove
        tv_boardName_1 = (TextView) view.findViewById(R.id.tv_boardName_1_template6); // Remove

        linearlayout_EducationalQualification_2 = (LinearLayout) view.findViewById(R.id.linearlayout_EducationalQualification_2_template6);
        tv_educationQualification_2 = (TextView) view.findViewById(R.id.tv_educationQualification_2_template5);
        tv_instituteName_2 = (TextView) view.findViewById(R.id.tv_instituteName_2_template5);
        tv_passingYear_2 = (TextView) view.findViewById(R.id.tv_passingYear_2_template5);
        tv_Result_2 = (TextView) view.findViewById(R.id.tv_Result_2_template5); // Remove
        tv_boardName_2 = (TextView) view.findViewById(R.id.tv_boardName_2_template5); // Remove

        linearlayout_EducationalQualification_3 = (LinearLayout) view.findViewById(R.id.linearlayout_EducationalQualification_3_template5);
        tv_educationQualification_3 = (TextView) view.findViewById(R.id.tv_educationQualification_2_template6);
        tv_instituteName_3 = (TextView) view.findViewById(R.id.tv_instituteName_2_template6);
        tv_passingYear_3 = (TextView) view.findViewById(R.id.tv_passingYear_2_template6);
        tv_Result_3 = (TextView) view.findViewById(R.id.tv_Result_2_template6); // Remove
        tv_boardName_3 = (TextView) view.findViewById(R.id.tv_boardName_2_template6); // Remove

        linearlayout_EducationalQualification_4 = (LinearLayout) view.findViewById(R.id.linearlayout_EducationalQualification_3_template6);
        tv_educationQualification_4 = (TextView) view.findViewById(R.id.tv_educationQualification_3_template6);
        tv_instituteName_4 = (TextView) view.findViewById(R.id.tv_instituteName_3_template6);
        tv_passingYear_4 = (TextView) view.findViewById(R.id.tv_passingYear_3_template6);
        tv_Result_4 = (TextView) view.findViewById(R.id.tv_Result_3_template6);
        tv_boardName_4 = (TextView) view.findViewById(R.id.tv_boardName_3_template6);

        linearlayout_EducationalQualification_5 = (LinearLayout) view.findViewById(R.id.linearlayout_EducationalQualification_4_template6);
        tv_educationQualification_5 = (TextView) view.findViewById(R.id.tv_educationQualification_4_template6);
        tv_instituteName_5 = (TextView) view.findViewById(R.id.tv_instituteName_4_template6);
        tv_passingYear_5 = (TextView) view.findViewById(R.id.tv_passingYear_4_template6);
        tv_Result_5 = (TextView) view.findViewById(R.id.tv_Result_4_template6);
        tv_boardName_5 = (TextView) view.findViewById(R.id.tv_boardName_4_template6);


        if (ResumeProfilePart2.educationQualification.size() > 0) {
            linearlayout_EducationalQualification_1.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(0);
            tv_educationQualification_1.setText(model.getQualification_name());
            tv_instituteName_1.setText(model.getInstitute_name());
            tv_passingYear_1.setText(model.getPassing_year());
            tv_Result_1.setText(model.getResult());
            tv_boardName_1.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size() > 1) {
            linearlayout_EducationalQualification_2.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(1);
            tv_educationQualification_2.setText(model.getQualification_name());
            tv_instituteName_2.setText(model.getInstitute_name());
            tv_passingYear_2.setText(model.getPassing_year());
            tv_Result_2.setText(model.getResult());
            tv_boardName_2.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size() > 2) {
            linearlayout_EducationalQualification_3.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(2);
            tv_educationQualification_3.setText(model.getQualification_name());
            tv_instituteName_3.setText(model.getInstitute_name());
            tv_passingYear_3.setText(model.getPassing_year());
            tv_Result_3.setText(model.getResult());
            tv_boardName_3.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size() > 3) {
            linearlayout_EducationalQualification_4.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(3);
            tv_educationQualification_4.setText(model.getQualification_name());
            tv_instituteName_4.setText(model.getInstitute_name());
            tv_passingYear_4.setText(model.getPassing_year());
            tv_Result_4.setText(model.getResult());
            tv_boardName_4.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size() > 4) {
            linearlayout_EducationalQualification_5.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(4);
            tv_educationQualification_5.setText(model.getQualification_name());
            tv_instituteName_5.setText(model.getInstitute_name());
            tv_passingYear_5.setText(model.getPassing_year());
            tv_Result_5.setText(model.getResult());
            tv_boardName_5.setText(model.getBoard_name());
        }


    }

    @Override
    public void onClick(View v) {
        if (v == button_Back2){
            startActivity(new Intent(getContext(), BuildResumePDF_Part1.class));

          /*  viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
            viewFlipper.setDisplayedChild(1);*/
        }

        if (v == getButton_CompletePayment2){
            ResumeTemplate.templateName = "template5";
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