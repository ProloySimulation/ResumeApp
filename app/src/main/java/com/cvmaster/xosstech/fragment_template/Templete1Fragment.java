package com.cvmaster.xosstech.fragment_template;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.BuildResumePDF_Part1;
import com.cvmaster.xosstech.ChargingActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.ResumeTemplate;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.viewmodel.Templete1ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Templete1Fragment extends Fragment implements View.OnClickListener{

    private Button button_Back;
    private Button button_CompletePayment;
    private String myValue = null ;
    private LinearLayout llPdf ;

    private Bitmap bitmap ;
    private String pdfFileName="";

    private ViewFlipper viewFlipper;

    private Templete1ViewModel mViewModel;
    //Part 1
    private TextView textView_tv_Heading_Name;
    private TextView textView_tv_Mobile;
    private TextView textView_tv_email;
    private TextView textView_tv_Address;
    private ImageView imageView_image;

    //Part 2
    private TextView textView_tv_Career_Objective_Container;

    private TableRow tableRow_row1;
    private TextView textView_title1;
    private TextView textView_concentration1;
    private TextView textView_institute1;
    private TextView textView_board1;
    private TextView textView_result1;
    private TextView textView_year1;

    private TableRow tableRow_row2;
    private TextView textView_title2;
    private TextView textView_concentration2;
    private TextView textView_institute2;
    private TextView textView_board2;
    private TextView textView_result2;
    private TextView textView_year2;

    private TableRow tableRow_row3;
    private TextView textView_title3;
    private TextView textView_concentration3;
    private TextView textView_institute3;
    private TextView textView_board3;
    private TextView textView_result3;
    private TextView textView_year3;

    private TableRow tableRow_row4;
    private TextView textView_title4;
    private TextView textView_concentration4;
    private TextView textView_institute4;
    private TextView textView_board4;
    private TextView textView_result4;
    private TextView textView_year4;


    private TableRow tableRow_row5;
    private TextView textView_title5;
    private TextView textView_concentration5;
    private TextView textView_institute5;
    private TextView textView_board5;
    private TextView textView_result5;
    private TextView textView_year5;

    //Part 3
    private TextView textView_tv_Special_Qualities;
    private TextView textView_bangla_Efficienty;
    private TextView textView_english_Efficienty;

    //Part 4
    private TextView textView_tv_pd_Name;
    private TextView textView_tv_pd_FName;
    private TextView textView_tv_pd_MName;
    private TextView textView_tv_pd_Gender;
    private TextView textView_tv_pd_DOB;
    private TextView textView_tv_pd_Status;
    private TextView textView_tv_pd_Nationality;
    private TextView textView_tv_pd_ReligionName;
    private TextView textView_tv_pd_PRAD;
    private TextView textView_tv_pd_PERAD;


    //Part5
    private RelativeLayout relativeLayout_rl_Reference1;

    private RelativeLayout relativeLayout_rl_ref_1;
    private TextView textView_tv_References_Name;
    private TextView textView_tv_References_Designation;
    private TextView textView_tv_References_COMName;
    private TextView textView_tv_References_Email;
    private TextView textView_tv_References_Contact;

    private RelativeLayout relativeLayout_rl_ref_2;
    private TextView textView_tv_References_Name2;
    private TextView textView_tv_References_Designation2;
    private TextView textView_tv_References_COMName2;
    private TextView textView_tv_References_Email2;
    private TextView textView_tv_References_Contact2;


    //Part 6
    private RelativeLayout relativeLayout_rl_WorkExp_Heading;

    private RelativeLayout relativeLayout_rl_WorkExp;
    private TextView textView_tv_WorkExp_DesigName;
    private TextView textView_tv_WorkExp_ComName;
    private TextView textView_tv_WorkExp_ComAddress;

    private RelativeLayout relativeLayout_rl_WorkExp2;
    private TextView textView_tv_WorkExp_DesigName2;
    private TextView textView_tv_WorkExp_ComName2;
    private TextView textView_tv_WorkExp_ComAddress2;


    public static Templete1Fragment newInstance() {
        return new Templete1Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.templete1_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Templete1ViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Templete 1
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        button_Back = (Button) view.findViewById(R.id.back_Button);
        llPdf = view.findViewById(R.id.llpdf);
        button_Back.setOnClickListener(this);

        button_CompletePayment = (Button) view.findViewById(R.id.completePayment_Button);
        button_CompletePayment.setOnClickListener(this);


        //Part 1
        textView_tv_Heading_Name = (TextView) view.findViewById(R.id.tv_Heading_Name);
        textView_tv_Mobile = (TextView) view.findViewById(R.id.tv_Mobile);
        textView_tv_email = (TextView) view.findViewById(R.id.tv_email);
        textView_tv_Address = (TextView) view.findViewById(R.id.tv_Address);
        imageView_image = (ImageView) view.findViewById(R.id.image);

        textView_tv_Heading_Name.setText(ResumeProfilePart1.getName());
        textView_tv_Mobile.setText(ResumeProfilePart1.getContact_number());
        textView_tv_email.setText(ResumeProfilePart1.getEmail());
        textView_tv_Address.setText(ResumeProfilePart1.getPresent_address());

    /*    File file = new File(ResumeProfilePart1.getImagePath());
        imageView_image.setImageURI(Uri.fromFile(file));*/

        //Part 2
        textView_tv_Career_Objective_Container = (TextView) view.findViewById(R.id.tv_Career_Objective_Container);
        textView_tv_Career_Objective_Container.setText(ResumeProfilePart2.career_objective);

        tableRow_row1 = (TableRow) view.findViewById(R.id.row1);
        textView_title1 = (TextView) view.findViewById(R.id.title1);
        textView_concentration1 = (TextView) view.findViewById(R.id.concentration1);
        textView_institute1 = (TextView) view.findViewById(R.id.institute1);
        textView_board1 = (TextView) view.findViewById(R.id.board1);
        textView_result1 = (TextView) view.findViewById(R.id.result1);
        textView_year1 = (TextView) view.findViewById(R.id.year1);

        tableRow_row2 = (TableRow) view.findViewById(R.id.row2);
        textView_title2 = (TextView) view.findViewById(R.id.title2);
        textView_concentration2 = (TextView) view.findViewById(R.id.concentration2);
        textView_institute2 = (TextView) view.findViewById(R.id.institute2);
        textView_board2 = (TextView) view.findViewById(R.id.board2);
        textView_result2 = (TextView) view.findViewById(R.id.result2);
        textView_year2 = (TextView) view.findViewById(R.id.year2);

        tableRow_row3 = (TableRow) view.findViewById(R.id.row3);
        textView_title3 = (TextView) view.findViewById(R.id.title3);
        textView_concentration3 = (TextView) view.findViewById(R.id.concentration3);
        textView_institute3 = (TextView) view.findViewById(R.id.institute3);
        textView_board3 = (TextView) view.findViewById(R.id.board3);
        textView_result3 = (TextView) view.findViewById(R.id.result3);
        textView_year3 = (TextView) view.findViewById(R.id.year3);

        tableRow_row4 = (TableRow) view.findViewById(R.id.row4);
        textView_title4 = (TextView) view.findViewById(R.id.title4);
        textView_concentration4 = (TextView) view.findViewById(R.id.concentration4);
        textView_institute4 = (TextView) view.findViewById(R.id.institute4);
        textView_board4 = (TextView) view.findViewById(R.id.board4);
        textView_result4 = (TextView) view.findViewById(R.id.result4);
        textView_year4 = (TextView) view.findViewById(R.id.year4);

        tableRow_row5 = (TableRow) view.findViewById(R.id.row5);
        textView_title5 = (TextView) view.findViewById(R.id.title5);
        textView_concentration5 = (TextView) view.findViewById(R.id.concentration5);
        textView_institute5 = (TextView) view.findViewById(R.id.institute5);
        textView_board5 = (TextView) view.findViewById(R.id.board5);
        textView_result5 = (TextView) view.findViewById(R.id.result5);
        textView_year5 = (TextView) view.findViewById(R.id.year5);

        List<EducationQualification_Model> educationQualification_models = ResumeProfilePart2.educationQualification;
        if (ResumeProfilePart2.educationQualification.size()>0){
            tableRow_row1.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(0);
            textView_title1.setText(model.getQualification_name());
            textView_concentration1.setText(model.getQualification_name());
            textView_institute1.setText(model.getInstitute_name());
            textView_board1.setText(model.getBoard_name());
            textView_result1.setText(model.getResult());
            textView_year1.setText(model.getPassing_year());
        }
        if (ResumeProfilePart2.educationQualification.size()>1){
            tableRow_row2.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(1);
            textView_title2.setText(model.getQualification_name());
            textView_concentration2.setText(model.getQualification_name());
            textView_institute2.setText(model.getInstitute_name());
            textView_board2.setText(model.getBoard_name());
            textView_result2.setText(model.getResult());
            textView_year2.setText(model.getPassing_year());
        }
        if (ResumeProfilePart2.educationQualification.size()>2){
            tableRow_row3.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(2);
            textView_title3.setText(model.getQualification_name());
            textView_concentration3.setText(model.getQualification_name());
            textView_institute3.setText(model.getInstitute_name());
            textView_board3.setText(model.getBoard_name());
            textView_result3.setText(model.getResult());
            textView_year3.setText(model.getPassing_year());
        }
        if (ResumeProfilePart2.educationQualification.size()>3){
            tableRow_row4.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(3);
            textView_title4.setText(model.getQualification_name());
            textView_concentration4.setText(model.getQualification_name());
            textView_institute4.setText(model.getInstitute_name());
            textView_board4.setText(model.getBoard_name());
            textView_result4.setText(model.getResult());
            textView_year4.setText(model.getPassing_year());
        }
        if (ResumeProfilePart2.educationQualification.size()>4){
            tableRow_row5.setVisibility(View.VISIBLE);
            EducationQualification_Model model = educationQualification_models.get(4);
            textView_title5.setText(model.getQualification_name());
            textView_concentration5.setText(model.getQualification_name());
            textView_institute5.setText(model.getInstitute_name());
            textView_board5.setText(model.getBoard_name());
            textView_result5.setText(model.getResult());
            textView_year5.setText(model.getPassing_year());
        }

        //Part 3
        textView_tv_Special_Qualities = (TextView) view.findViewById(R.id.tv_Special_Qualities);
        textView_bangla_Efficienty = (TextView) view.findViewById(R.id.bangla_Efficienty);
        textView_english_Efficienty = (TextView) view.findViewById(R.id.english_Efficienty);

        String skills = "";

        for (int i = 0; i< ResumeProfilePart3.skills.size()-1; i++){
            Skills_Model skills_model = ResumeProfilePart3.skills.get(i);
            skills = skills + skills_model.getSkill() + ", ";
        }
        Skills_Model skills_model = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size()-1);
        skills = skills+skills_model.getSkill();
        textView_tv_Special_Qualities.setText(skills);

        textView_bangla_Efficienty.setText(ResumeProfilePart3.bangla_skill_level);
        textView_english_Efficienty.setText(ResumeProfilePart3.english_skill_level);

        //Part 4
        textView_tv_pd_Name = (TextView) view.findViewById(R.id.tv_pd_Name);
        textView_tv_pd_FName = (TextView) view.findViewById(R.id.tv_pd_FName);
        textView_tv_pd_MName = (TextView) view.findViewById(R.id.tv_pd_MName);
        textView_tv_pd_Gender = (TextView) view.findViewById(R.id.tv_pd_Gender);
        textView_tv_pd_DOB = (TextView) view.findViewById(R.id.tv_pd_DOB);
        textView_tv_pd_Status = (TextView) view.findViewById(R.id.tv_pd_Status);
        textView_tv_pd_Nationality = (TextView) view.findViewById(R.id.tv_pd_Nationality);
        textView_tv_pd_ReligionName = (TextView) view.findViewById(R.id.tv_pd_ReligionName);
        textView_tv_pd_PRAD = (TextView) view.findViewById(R.id.tv_pd_PRAD);
        textView_tv_pd_PERAD = (TextView) view.findViewById(R.id.tv_pd_PERAD);

        textView_tv_pd_Name.setText(ResumeProfilePart4.getName());
        textView_tv_pd_FName.setText(ResumeProfilePart4.getFather_name());
        textView_tv_pd_MName.setText(ResumeProfilePart4.getMother_name());
        textView_tv_pd_Gender.setText(ResumeProfilePart4.getGender());
        textView_tv_pd_DOB.setText(ResumeProfilePart4.getBirth_date());
        textView_tv_pd_Status.setText(ResumeProfilePart4.getMarital_status());
        textView_tv_pd_Nationality.setText(ResumeProfilePart4.getNationality());
        textView_tv_pd_ReligionName.setText(ResumeProfilePart4.getReligion());
        textView_tv_pd_PRAD.setText(ResumeProfilePart4.getPresent_address());
        textView_tv_pd_PERAD.setText(ResumeProfilePart4.getPermanent_address());

        //Part 5
        relativeLayout_rl_Reference1 = (RelativeLayout) view.findViewById(R.id.rl_Reference1);

        relativeLayout_rl_ref_1 = (RelativeLayout) view.findViewById(R.id.rl_ref_1);
        textView_tv_References_Name = (TextView) view.findViewById(R.id.tv_References_Name);
        textView_tv_References_Designation = (TextView) view.findViewById(R.id.tv_References_Designation);
        textView_tv_References_COMName = (TextView) view.findViewById(R.id.tv_References_COMName);
        textView_tv_References_Email = (TextView) view.findViewById(R.id.tv_References_Email);
        textView_tv_References_Contact = (TextView) view.findViewById(R.id.tv_References_Contact);

        relativeLayout_rl_ref_2 = (RelativeLayout) view.findViewById(R.id.rl_ref_2);
        textView_tv_References_Name2 = (TextView) view.findViewById(R.id.tv_References_Name2);
        textView_tv_References_Designation2 = (TextView) view.findViewById(R.id.tv_References_Designation2);
        textView_tv_References_COMName2 = (TextView) view.findViewById(R.id.tv_References_COMName2);
        textView_tv_References_Email2 = (TextView) view.findViewById(R.id.tv_References_Email2);
        textView_tv_References_Contact2 = (TextView) view.findViewById(R.id.tv_References_Contact2);

        List<Reference_Model> reference_models = ResumeProfilePart5.reference;
        if (ResumeProfilePart5.reference.size()>0){
            relativeLayout_rl_Reference1.setVisibility(View.VISIBLE);
            relativeLayout_rl_ref_1.setVisibility(View.VISIBLE);

            Reference_Model reference_model = reference_models.get(0);
            textView_tv_References_Name.setText(reference_model.getName());
            textView_tv_References_Designation.setText(reference_model.getDesignation());
            textView_tv_References_COMName.setText(reference_model.getOrganization_name());
            textView_tv_References_Email.setText(reference_model.getEmail());
            textView_tv_References_Contact.setText(reference_model.getMobile_number());

        }
        if (ResumeProfilePart5.reference.size()>1){
            relativeLayout_rl_ref_2.setVisibility(View.VISIBLE);

            Reference_Model reference_model = reference_models.get(1);
            textView_tv_References_Name2.setText(reference_model.getName());
            textView_tv_References_Designation2.setText(reference_model.getDesignation());
            textView_tv_References_COMName2.setText(reference_model.getOrganization_name());
            textView_tv_References_Email2.setText(reference_model.getEmail());
            textView_tv_References_Contact2.setText(reference_model.getMobile_number());
        }

        //Part 6
        relativeLayout_rl_WorkExp_Heading = (RelativeLayout) view.findViewById(R.id.rl_WorkExp_Heading);

        relativeLayout_rl_WorkExp = (RelativeLayout) view.findViewById(R.id.rl_WorkExp);
        textView_tv_WorkExp_DesigName = (TextView) view.findViewById(R.id.tv_WorkExp_DesigName);
        textView_tv_WorkExp_ComName = (TextView) view.findViewById(R.id.tv_WorkExp_ComName);
        textView_tv_WorkExp_ComAddress = (TextView) view.findViewById(R.id.tv_WorkExp_ComAddress);

        relativeLayout_rl_WorkExp2 = (RelativeLayout) view.findViewById(R.id.rl_WorkExp2);
        textView_tv_WorkExp_DesigName2 = (TextView) view.findViewById(R.id.tv_WorkExp_DesigName2);
        textView_tv_WorkExp_ComName2 = (TextView) view.findViewById(R.id.tv_WorkExp_ComName2);
        textView_tv_WorkExp_ComAddress2 = (TextView) view.findViewById(R.id.tv_WorkExp_ComAddress2);

        List<WorkExperience_Model> workExperience_models = ResumeProfilePart6.workExperience;

        if (ResumeProfilePart6.workExperience.size() > 0){
            relativeLayout_rl_WorkExp_Heading.setVisibility(View.VISIBLE);
            relativeLayout_rl_WorkExp.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_model = workExperience_models.get(0);
            textView_tv_WorkExp_DesigName.setText(workExperience_model.getDesignationName()+" ["+workExperience_model.getDurationTime()+"]");
            textView_tv_WorkExp_ComName.setText(workExperience_model.getOrganizationName());
            textView_tv_WorkExp_ComAddress.setText(workExperience_model.getOgganizationAddress());
        }

        if (ResumeProfilePart6.workExperience.size() > 1){
            relativeLayout_rl_WorkExp2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_model = workExperience_models.get(1);
            textView_tv_WorkExp_DesigName2.setText(workExperience_model.getDesignationName()+" ["+workExperience_model.getDurationTime()+"]");
            textView_tv_WorkExp_ComName2.setText(workExperience_model.getOrganizationName());
            textView_tv_WorkExp_ComAddress2.setText(workExperience_model.getOgganizationAddress());

        }

        if (myValue == null)
        {

        }

        else {

        }
       /* myValue = this.getArguments().getString("new");

        if(myValue.equals("1"))
        {
            button_Back.setVisibility(View.GONE);
            button_CompletePayment.setVisibility(View.GONE);

            createPdf();
        }*/

    }

    @Override
    public void onClick(View v) {
          if (v == button_Back){
              startActivity(new Intent(getContext(), BuildResumePDF_Part1.class));
           /* viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
            viewFlipper.showNext();*/
        }
        if (v == button_CompletePayment){
            ResumeTemplate.templateName = "template1";
            verifyTheProcess();

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