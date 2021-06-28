package com.cvmaster.xosstech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.activity.FragmentShowActivity;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.ResumeTemplate;

public class BuildResumePDF_Part1 extends AppCompatActivity implements View.OnClickListener {

    private ViewFlipper viewFlipper;
    private Button button_Data;
    private static final int STOREGE_CODE = 1000;

    private CardView button_Template1;
    private CardView button_Template2;
    private CardView button_Template3;
    private CardView button_Template4;
    private CardView button_Template5;
    private CardView button_template6;

    //Templete 1
  /*

    private Button button_Back;
    private Button button_CompletePayment;

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


*/

    //templete2 star

  /*  private Button button_Back2;
    private Button getButton_CompletePayment2;

    private ImageView imageView2;

    private TextView name2;
    private TextView career_objective2;
    private TextView email2;
    private TextView location2;
    private TextView contact2;

    private TextView skills2;

    private TextView bangla_skill2;
    private TextView english_skill2;

    private LinearLayout linearLayout_Head2;

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

    private LinearLayout ll_prexp1;
    private TextView tv_pr_exp1_position;
    private TextView tv_pr_exp1_company;
    private TextView tv_pr_exp1_company_address;
    private TextView tv_pr_exp1_service_time;

    private LinearLayout ll_prexp2;
    private TextView tv_pr_exp2_position;
    private TextView tv_pr_exp2_company;
    private TextView tv_pr_exp2_company_address;
    private TextView tv_pr_exp2_service_time;

    private LinearLayout linearlayout_EducationalQualification_1;
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
*/


    ////Template 3 Start

  /*
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

    Button button_Back3;
    Button button_CompletePayment3;

    //Template 3 End*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_pdf__part1);
/*        File file = new File(ResumeProfilePart1.getImagePath());
        List<EducationQualification_Model> educationQualification_model = ResumeProfilePart2.educationQualification;
        List<EducationQualification_Model> educationQualification_models = ResumeProfilePart2.educationQualification;*/

        //Template 3

       /* imageView_Template3_image = (ImageView) findViewById(R.id.template3_image);
        File file3 = new File(ResumeProfilePart1.getImagePath());
        imageView_Template3_image.setImageURI(Uri.fromFile(file3));

        textView_Template3_name = (TextView) findViewById(R.id.tv_heading_name3);
        textView_Template3_name.setText(ResumeProfilePart1.getName());

        textView_Template3_careerObjective = (TextView) findViewById(R.id.tv_Career_Objective_Container3);
        textView_Template3_careerObjective.setText(ResumeProfilePart2.career_objective);

        textView_Template3_contactNumber = (TextView) findViewById(R.id.tv_number3);
        textView_Template3_contactNumber.setText(ResumeProfilePart1.getContact_number());

        textView_Template3_email = (TextView) findViewById(R.id.tv_email3);
        textView_Template3_email.setText(ResumeProfilePart1.getEmail());

        textView_Template3_curresntAddress = (TextView) findViewById(R.id.tv_Address3);
        textView_Template3_curresntAddress.setText(ResumeProfilePart1.getPresent_address());


        //Education Start

        relativeLayout_Template3_RL_SSC = (RelativeLayout) findViewById(R.id.RL_SSC);
        relativeLayout_Template3_RL_SSC.setVisibility(View.GONE);

        textView_Template3_tv_SSC_Heading = (TextView) findViewById(R.id.tv_SSC_Heading);
        textView_Template3_SSC_Year = (TextView) findViewById(R.id.SSC_Year);
        textView_Template3_tv_SSC_Institute_Name = (TextView) findViewById(R.id.tv_SSC_Institute_Name);
        textView_Template3_tv_SSC_Subject_Name = (TextView) findViewById(R.id.tv_SSC_Subject_Name);
        textView_Template3_tv_SSC_Result = (TextView) findViewById(R.id.tv_SSC_Result);

        relativeLayout_Template3_RL_HSC = (RelativeLayout) findViewById(R.id.RL_HSC);
        relativeLayout_Template3_RL_HSC.setVisibility(View.GONE);

        textView_Template3_tv_HSC_Heading = (TextView) findViewById(R.id.tv_HSC_Heading);
        textView_Template3_HSC_Year = (TextView) findViewById(R.id.HSC_Year);
        textView_Template3_tv_HSC_Institute_Name = (TextView) findViewById(R.id.tv_HSC_Institute_Name);
        textView_Template3_tv_HSC_Subject_Name = (TextView) findViewById(R.id.tv_HSC_Subject_Name);
        textView_Template3_tv_HSC_Result = (TextView) findViewById(R.id.tv_HSC_Result);

        relativeLayout_Template3_RL_Univarsity_Name = (RelativeLayout) findViewById(R.id.RL_Univarsity_Name);
        relativeLayout_Template3_RL_Univarsity_Name.setVisibility(View.GONE);

        textView_Template3_tv_Univarsity_Heading = (TextView) findViewById(R.id.tv_Univarsity_Heading);
        textView_Template3_Honrs_Year = (TextView) findViewById(R.id.Honrs_Year);
        textView_Template3_tv_Honrs_Institute_Name = (TextView) findViewById(R.id.tv_Honrs_Institute_Name);
        textView_Template3_tv_Honrs_Subject_Name = (TextView) findViewById(R.id.tv_Honrs_Subject_Name);
        textView_Template3_tv_Honrs_Result = (TextView) findViewById(R.id.tv_Honrs_Result);

        relativeLayout_Template3_RL_Masters = (RelativeLayout) findViewById(R.id.RL_Masters);
        relativeLayout_Template3_RL_Masters.setVisibility(View.GONE);

        textView_Template3_tv_Masters_Heading = (TextView) findViewById(R.id.tv_Masters_Heading);
        textView_Template3_Master_Year = (TextView) findViewById(R.id.Master_Year);
        textView_Template3_tv_Masters_Institute_Name = (TextView) findViewById(R.id.tv_Masters_Institute_Name);
        textView_Template3_tv_Masters_Subject_Name = (TextView) findViewById(R.id.tv_Masters_Subject_Name);
        textView_Template3_tv_Masters_Result = (TextView) findViewById(R.id.tv_Masters_Result);



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

        textView_Template3_skill1 = (TextView) findViewById(R.id.tv_Skills_Template3_1);
        textView_Template3_skill1.setVisibility(View.INVISIBLE);
        textView_Template3_skill2 = (TextView) findViewById(R.id.tv_Skills_Template3_2);
        textView_Template3_skill2.setVisibility(View.INVISIBLE);
        textView_Template3_skill3 = (TextView) findViewById(R.id.tv_Skills_Template3_3);
        textView_Template3_skill3.setVisibility(View.INVISIBLE);
        textView_Template3_skill4 = (TextView) findViewById(R.id.tv_Skills_Template3_4);
        textView_Template3_skill4.setVisibility(View.INVISIBLE);
        textView_Template3_skill5 = (TextView) findViewById(R.id.tv_Skills_Template3_5);
        textView_Template3_skill5.setVisibility(View.INVISIBLE);
        textView_Template3_skill6 = (TextView) findViewById(R.id.tv_Skills_Template3_6);
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

        textView_Template3_skillEng = (TextView) findViewById(R.id.tv_Language_English_Proficiency);
        textView_Template3_skillEng.setText(ResumeProfilePart3.english_skill_level);

        textView_Template3_skillBangla = (TextView) findViewById(R.id.tv_Language_Bangla_Proficiency);
        textView_Template3_skillBangla.setText(ResumeProfilePart3.bangla_skill_level);

        //Work Exp
        relativeLayout_Template3_WorkExpHeading = (RelativeLayout) findViewById(R.id.RL_Work_Exp_Heading);
        relativeLayout_Template3_WorkExpHeading.setVisibility(View.GONE);

        relativeLayout_Template3_WorkExp1 = (RelativeLayout) findViewById(R.id.RL_Work_Exp_1);
        relativeLayout_Template3_WorkExp1.setVisibility(View.GONE);

        textView_Template3_WorkExp1Designation = (TextView) findViewById(R.id.tv_Work_Exp_1_Heading);
        textView_Template3_WorkExp1Duration = (TextView) findViewById(R.id.Work_Exp_1_Year);
        textView_Template3_WorkExp1OrgName = (TextView) findViewById(R.id.tv_Work_Exp_1_Org_Name);
        textView_Template3_WorkExp1OrgAddress = (TextView) findViewById(R.id.tv_Work_Exp_1_Org_Address);

        relativeLayout_Template3_WorkExp2 = (RelativeLayout) findViewById(R.id.RL_Work_Exp_2);
        relativeLayout_Template3_WorkExp2.setVisibility(View.GONE);

        textView_Template3_WorkExp2Designation = (TextView) findViewById(R.id.tv_Work_Exp_2_Heading);
        textView_Template3_WorkExp2Duration = (TextView) findViewById(R.id.Work_Exp_2_Year);
        textView_Template3_WorkExp2OrgName = (TextView) findViewById(R.id.tv_Work_Exp_2_Org_Name);
        textView_Template3_WorkExp2OrgAddress = (TextView) findViewById(R.id.tv_Work_Exp_2_Org_Address);



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
        linearLayout_Template3_ReferenceHeading = (LinearLayout) findViewById(R.id.Linear_Reference_Heading);
        linearLayout_Template3_ReferenceHeading.setVisibility(View.GONE);

        relativeLayout_Template3_Ref1 = (RelativeLayout) findViewById(R.id.RL_Reference_1);
        relativeLayout_Template3_Ref1.setVisibility(View.GONE);

        textView_Template3_Ref1Name = (TextView) findViewById(R.id.tv_Reference_1_Name);
        textView_Template3_Ref1Designation = (TextView) findViewById(R.id.tv_Reference_1_Designation);
        textView_Template3_Ref1OrgName = (TextView) findViewById(R.id.tv_Reference_1_Company_Name);
        textView_Template3_Ref1Email = (TextView) findViewById(R.id.tv_Reference_1_Email);
        textView_Template3_Ref1Mobile = (TextView) findViewById(R.id.tv_Reference_1_Number);

        relativeLayout_Template3_Ref2 = (RelativeLayout) findViewById(R.id.RL_Reference_2);
        relativeLayout_Template3_Ref2.setVisibility(View.GONE);
        textView_Template3_Ref2Name = (TextView) findViewById(R.id.tv_Reference_2_Name);;
        textView_Template3_Ref2Designation = (TextView) findViewById(R.id.tv_Reference_2_Designation);
        textView_Template3_Ref2OrgName = (TextView) findViewById(R.id.tv_Reference_2_Company_Name);
        textView_Template3_Ref2Email= (TextView) findViewById(R.id.tv_Reference_2_Email);
        textView_Template3_Ref2Mobile = (TextView) findViewById(R.id.tv_Reference_2_Number);


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


        button_Back3 = (Button) findViewById(R.id.back3);
        button_Back3.setOnClickListener(this);
        button_CompletePayment3 = (Button) findViewById(R.id.completePayment3);
        button_CompletePayment3.setOnClickListener(this);
*/
        //Template 3 End

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);


        button_Template1 = (CardView) findViewById(R.id.button_Template1);
        button_Template1.setOnClickListener(this);

        button_Template2 = (CardView) findViewById(R.id.button_Template2);
        button_Template2.setOnClickListener(this);

        button_Template3 = (CardView) findViewById(R.id.button_Template3);
        button_Template3.setOnClickListener(this);

        button_Template4 = (CardView) findViewById(R.id.button_Template4);
        button_Template4.setOnClickListener(this);

        button_Template5 = findViewById(R.id.button_Template5);
        button_Template5.setOnClickListener(this);

        button_template6 = findViewById(R.id.button_Template6);
        button_template6.setOnClickListener(this);

        button_Data = (Button) findViewById(R.id.button_BuildResumePDF_Part5_Data);
        button_Data.setOnClickListener(this);


        //Templete 1
      /*  //Part 1


        button_Back = (Button) findViewById(R.id.back_Button);
        button_Back.setOnClickListener(this);

        button_CompletePayment = (Button) findViewById(R.id.completePayment_Button);
        button_CompletePayment.setOnClickListener(this);


        textView_tv_Heading_Name = (TextView) findViewById(R.id.tv_Heading_Name);
        textView_tv_Mobile = (TextView) findViewById(R.id.tv_Mobile);
        textView_tv_email = (TextView) findViewById(R.id.tv_email);
        textView_tv_Address = (TextView) findViewById(R.id.tv_Address);
        imageView_image = (ImageView) findViewById(R.id.image);

        textView_tv_Heading_Name.setText(ResumeProfilePart1.getName());
        textView_tv_Mobile.setText(ResumeProfilePart1.getContact_number());
        textView_tv_email.setText(ResumeProfilePart1.getEmail());
        textView_tv_Address.setText(ResumeProfilePart1.getPresent_address());

        imageView_image.setImageURI(Uri.fromFile(file));

        //Part 2
        textView_tv_Career_Objective_Container = (TextView) findViewById(R.id.tv_Career_Objective_Container);
        textView_tv_Career_Objective_Container.setText(ResumeProfilePart2.career_objective);

        tableRow_row1 = (TableRow) findViewById(R.id.row1);
        textView_title1 = (TextView) findViewById(R.id.title1);
        textView_concentration1 = (TextView) findViewById(R.id.concentration1);
        textView_institute1 = (TextView) findViewById(R.id.institute1);
        textView_board1 = (TextView) findViewById(R.id.board1);
        textView_result1 = (TextView) findViewById(R.id.result1);
        textView_year1 = (TextView) findViewById(R.id.year1);

        tableRow_row2 = (TableRow) findViewById(R.id.row2);
        textView_title2 = (TextView) findViewById(R.id.title2);
        textView_concentration2 = (TextView) findViewById(R.id.concentration2);
        textView_institute2 = (TextView) findViewById(R.id.institute2);
        textView_board2 = (TextView) findViewById(R.id.board2);
        textView_result2 = (TextView) findViewById(R.id.result2);
        textView_year2 = (TextView) findViewById(R.id.year2);

        tableRow_row3 = (TableRow) findViewById(R.id.row3);
        textView_title3 = (TextView) findViewById(R.id.title3);
        textView_concentration3 = (TextView) findViewById(R.id.concentration3);
        textView_institute3 = (TextView) findViewById(R.id.institute3);
        textView_board3 = (TextView) findViewById(R.id.board3);
        textView_result3 = (TextView) findViewById(R.id.result3);
        textView_year3 = (TextView) findViewById(R.id.year3);

        tableRow_row4 = (TableRow) findViewById(R.id.row4);
        textView_title4 = (TextView) findViewById(R.id.title4);
        textView_concentration4 = (TextView) findViewById(R.id.concentration4);
        textView_institute4 = (TextView) findViewById(R.id.institute4);
        textView_board4 = (TextView) findViewById(R.id.board4);
        textView_result4 = (TextView) findViewById(R.id.result4);
        textView_year4 = (TextView) findViewById(R.id.year4);

        tableRow_row5 = (TableRow) findViewById(R.id.row5);
        textView_title5 = (TextView) findViewById(R.id.title5);
        textView_concentration5 = (TextView) findViewById(R.id.concentration5);
        textView_institute5 = (TextView) findViewById(R.id.institute5);
        textView_board5 = (TextView) findViewById(R.id.board5);
        textView_result5 = (TextView) findViewById(R.id.result5);
        textView_year5 = (TextView) findViewById(R.id.year5);


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
        textView_tv_Special_Qualities = (TextView) findViewById(R.id.tv_Special_Qualities);
        textView_bangla_Efficienty = (TextView) findViewById(R.id.bangla_Efficienty);
        textView_english_Efficienty = (TextView) findViewById(R.id.english_Efficienty);

        String skills = "";

        for (int i = 0;i<ResumeProfilePart3.skills.size()-1;i++){
            Skills_Model skills_model = ResumeProfilePart3.skills.get(i);
            skills = skills + skills_model.getSkill() + ", ";
        }
        Skills_Model skills_model = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size()-1);
        skills = skills+skills_model.getSkill();
        textView_tv_Special_Qualities.setText(skills);

        textView_bangla_Efficienty.setText(ResumeProfilePart3.bangla_skill_level);
        textView_english_Efficienty.setText(ResumeProfilePart3.english_skill_level);

        //Part 4
        textView_tv_pd_Name = (TextView) findViewById(R.id.tv_pd_Name);
        textView_tv_pd_FName = (TextView) findViewById(R.id.tv_pd_FName);
        textView_tv_pd_MName = (TextView) findViewById(R.id.tv_pd_MName);
        textView_tv_pd_Gender = (TextView) findViewById(R.id.tv_pd_Gender);
        textView_tv_pd_DOB = (TextView) findViewById(R.id.tv_pd_DOB);
        textView_tv_pd_Status = (TextView) findViewById(R.id.tv_pd_Status);
        textView_tv_pd_Nationality = (TextView) findViewById(R.id.tv_pd_Nationality);
        textView_tv_pd_ReligionName = (TextView) findViewById(R.id.tv_pd_ReligionName);
        textView_tv_pd_PRAD = (TextView) findViewById(R.id.tv_pd_PRAD);
        textView_tv_pd_PERAD = (TextView) findViewById(R.id.tv_pd_PERAD);

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
        relativeLayout_rl_Reference1 = (RelativeLayout) findViewById(R.id.rl_Reference1);

        relativeLayout_rl_ref_1 = (RelativeLayout) findViewById(R.id.rl_ref_1);
        textView_tv_References_Name = (TextView) findViewById(R.id.tv_References_Name);
        textView_tv_References_Designation = (TextView) findViewById(R.id.tv_References_Designation);
        textView_tv_References_COMName = (TextView) findViewById(R.id.tv_References_COMName);
        textView_tv_References_Email = (TextView) findViewById(R.id.tv_References_Email);
        textView_tv_References_Contact = (TextView) findViewById(R.id.tv_References_Contact);

        relativeLayout_rl_ref_2 = (RelativeLayout) findViewById(R.id.rl_ref_2);
        textView_tv_References_Name2 = (TextView) findViewById(R.id.tv_References_Name2);
        textView_tv_References_Designation2 = (TextView) findViewById(R.id.tv_References_Designation2);
        textView_tv_References_COMName2 = (TextView) findViewById(R.id.tv_References_COMName2);
        textView_tv_References_Email2 = (TextView) findViewById(R.id.tv_References_Email2);
        textView_tv_References_Contact2 = (TextView) findViewById(R.id.tv_References_Contact2);

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
        relativeLayout_rl_WorkExp_Heading = (RelativeLayout) findViewById(R.id.rl_WorkExp_Heading);

        relativeLayout_rl_WorkExp = (RelativeLayout) findViewById(R.id.rl_WorkExp);
        textView_tv_WorkExp_DesigName = (TextView) findViewById(R.id.tv_WorkExp_DesigName);
        textView_tv_WorkExp_ComName = (TextView) findViewById(R.id.tv_WorkExp_ComName);
        textView_tv_WorkExp_ComAddress = (TextView) findViewById(R.id.tv_WorkExp_ComAddress);

        relativeLayout_rl_WorkExp2 = (RelativeLayout) findViewById(R.id.rl_WorkExp2);
        textView_tv_WorkExp_DesigName2 = (TextView) findViewById(R.id.tv_WorkExp_DesigName2);
        textView_tv_WorkExp_ComName2 = (TextView) findViewById(R.id.tv_WorkExp_ComName2);
        textView_tv_WorkExp_ComAddress2 = (TextView) findViewById(R.id.tv_WorkExp_ComAddress2);

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

        }*/


        // templlet 1 end here




        //I have changed all the value as same
        //The serial is 0,2,3
        if (ResumeTemplate.templateName == "template1"){
            viewFlipper.setDisplayedChild(0);
        } else if (ResumeTemplate.templateName == "template2"){
            viewFlipper.setDisplayedChild(2);
        } else if (ResumeTemplate.templateName == "template3"){
            viewFlipper.setDisplayedChild(3);
        }else if (ResumeTemplate.templateName == "template4"){
            viewFlipper.setDisplayedChild(4);
        }

        // templete 2 star
      /*  button_Back2 = (Button) findViewById(R.id.back2);
        button_Back2.setOnClickListener(this);

        getButton_CompletePayment2 = (Button) findViewById(R.id.completePayment2);
        getButton_CompletePayment2.setOnClickListener(this);


        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView2.setImageURI(Uri.fromFile(file));

        name2 = (TextView) findViewById(R.id.tv_heading_name);
        career_objective2 = (TextView) findViewById(R.id.tv_CareerObjective_Container);
        email2 = (TextView) findViewById(R.id.tv_email2);
        location2 = (TextView) findViewById(R.id.tv_number);
        contact2 = (TextView) findViewById(R.id.tv_address);

        name2.setText(ResumeProfilePart1.getName());
        career_objective2.setText(ResumeProfilePart2.career_objective);
        email2.setText(ResumeProfilePart1.getEmail());
        location2.setText(ResumeProfilePart1.getPresent_address());
        contact2.setText(ResumeProfilePart1.getContact_number());

        skills2 = (TextView) findViewById(R.id.tv_skills_msword);

        String _skills2 = "";

        for (int i = 0;i<ResumeProfilePart3.skills.size()-1;i++){
            Skills_Model skills_mod = ResumeProfilePart3.skills.get(i);
            _skills2 = _skills2 + skills_mod.getSkill() + "\n";
        }
        Skills_Model skills_mode = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size()-1);
        _skills2 = _skills2+skills_mode.getSkill();
        skills2.setText(_skills2);

        bangla_skill2 = (TextView) findViewById(R.id.tv_language_bangla_pr);
        english_skill2 = (TextView) findViewById(R.id.tv_language_english_pr);

        bangla_skill2.setText(ResumeProfilePart3.bangla_skill_level);
        english_skill2.setText(ResumeProfilePart3.english_skill_level);

        linearLayout_Head2 = (LinearLayout) findViewById(R.id.linearLayout_Head_References_2);

        linearLayout_layout1 = (LinearLayout) findViewById(R.id.linearLayout_Reference2_1);
        tv_ref1_name = (TextView) findViewById(R.id.tv_ref1_name);
        tv_ref1_designation = (TextView) findViewById(R.id.tv_ref1_designation);
        tv_ref1_company = (TextView) findViewById(R.id.tv_ref1_company);
        tv_ref1_email = (TextView) findViewById(R.id.tv_ref1_email);
        tv_ref1_contactnumber = (TextView) findViewById(R.id.tv_ref1_contactnumber);

        linearLayout_layout2 = (LinearLayout) findViewById(R.id.linearLayout_Reference2_2);
        tv_ref2_name = (TextView) findViewById(R.id.tv_ref2_name);
        tv_ref2_designation = (TextView) findViewById(R.id.tv_ref2_designation);
        tv_ref2_company = (TextView) findViewById(R.id.tv_ref2_company);
        tv_ref2_email = (TextView) findViewById(R.id.tv_ref2_email);
        tv_ref2_contactnumber = (TextView) findViewById(R.id.tv_ref2_contactnumber);

        List<Reference_Model> reference_model = ResumeProfilePart5.reference;

        if (ResumeProfilePart5.reference.size()>0){
            linearLayout_Head2.setVisibility(View.VISIBLE);

            linearLayout_layout1.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(0);

            tv_ref1_name.setText(reference_mod.getName());
            tv_ref1_designation.setText(reference_mod.getDesignation());
            tv_ref1_company.setText(reference_mod.getDesignation());
            tv_ref1_email.setText(reference_mod.getEmail());
            tv_ref1_contactnumber.setText(reference_mod.getMobile_number());
        }

        if (ResumeProfilePart5.reference.size()>1){
            linearLayout_layout2.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(1);

            tv_ref2_name.setText(reference_mod.getName());
            tv_ref2_designation.setText(reference_mod.getDesignation());
            tv_ref2_company.setText(reference_mod.getDesignation());
            tv_ref2_email.setText(reference_mod.getEmail());
            tv_ref2_contactnumber.setText(reference_mod.getMobile_number());
        }

        linearLayout_ProfessionalExp_Head = (LinearLayout) findViewById(R.id.linearLayout_ProfessionalExp_Head);

        ll_prexp1 = (LinearLayout) findViewById(R.id.ll_prexp1);
        tv_pr_exp1_position = (TextView) findViewById(R.id.tv_pr_exp1_position);
        tv_pr_exp1_company = (TextView) findViewById(R.id.tv_pr_exp1_company);
        tv_pr_exp1_company_address = (TextView) findViewById(R.id.tv_pr_exp1_company_address);
        tv_pr_exp1_service_time = (TextView) findViewById(R.id.tv_pr_exp1_service_time);

        ll_prexp2 = (LinearLayout) findViewById(R.id.ll_prexp2);
        tv_pr_exp2_position = (TextView) findViewById(R.id.tv_pr_exp2_position);
        tv_pr_exp2_company = (TextView) findViewById(R.id.tv_pr_exp2_company);
        tv_pr_exp2_company_address = (TextView) findViewById(R.id.tv_pr_exp2_company_address);
        tv_pr_exp2_service_time = (TextView) findViewById(R.id.tv_pr_exp2_service_time);


        List<WorkExperience_Model> workExperience_model = ResumeProfilePart6.workExperience;
        if (ResumeProfilePart6.workExperience.size()>0){
            linearLayout_ProfessionalExp_Head.setVisibility(View.VISIBLE);

            ll_prexp1.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(0);
            tv_pr_exp1_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp1_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp1_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp1_service_time.setText(workExperience_mod.getDurationTime());

        }

        if (ResumeProfilePart6.workExperience.size()>1){

            ll_prexp2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp2_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp2_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp2_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp2_service_time.setText(workExperience_mod.getDurationTime());

        }

        linearlayout_EducationalQualification_1 = (LinearLayout) findViewById(R.id.linearlayout_EducationalQualification_1);
        tv_educationQualification_1 = (TextView) findViewById(R.id.tv_educationQualification_1);
        tv_instituteName_1 = (TextView) findViewById(R.id.tv_instituteName_1);
        tv_passingYear_1 = (TextView) findViewById(R.id.tv_passingYear_1);
        tv_Result_1 = (TextView) findViewById(R.id.tv_Result_1);
        tv_boardName_1 = (TextView) findViewById(R.id.tv_boardName_1);

        linearlayout_EducationalQualification_2 = (LinearLayout) findViewById(R.id.linearlayout_EducationalQualification_2);
        tv_educationQualification_2 = (TextView) findViewById(R.id.tv_educationQualification_2);
        tv_instituteName_2 = (TextView) findViewById(R.id.tv_instituteName_2);
        tv_passingYear_2 = (TextView) findViewById(R.id.tv_passingYear_2);
        tv_Result_2 = (TextView) findViewById(R.id.tv_Result_2);
        tv_boardName_2 = (TextView) findViewById(R.id.tv_boardName_2);

        linearlayout_EducationalQualification_3 = (LinearLayout) findViewById(R.id.linearlayout_EducationalQualification_3);
        tv_educationQualification_3 = (TextView) findViewById(R.id.tv_educationQualification_3);
        tv_instituteName_3 = (TextView) findViewById(R.id.tv_instituteName_3);
        tv_passingYear_3 = (TextView) findViewById(R.id.tv_passingYear_3);
        tv_Result_3 = (TextView) findViewById(R.id.tv_Result_3);
        tv_boardName_3 = (TextView) findViewById(R.id.tv_boardName_3);

        linearlayout_EducationalQualification_4 = (LinearLayout) findViewById(R.id.linearlayout_EducationalQualification_4);
        tv_educationQualification_4 = (TextView) findViewById(R.id.tv_educationQualification_4);
        tv_instituteName_4 = (TextView) findViewById(R.id.tv_instituteName_4);
        tv_passingYear_4 = (TextView) findViewById(R.id.tv_passingYear_4);
        tv_Result_4 = (TextView) findViewById(R.id.tv_Result_4);
        tv_boardName_4 = (TextView) findViewById(R.id.tv_boardName_4);

        linearlayout_EducationalQualification_5 = (LinearLayout) findViewById(R.id.linearlayout_EducationalQualification_5);
        tv_educationQualification_5 = (TextView) findViewById(R.id.tv_educationQualification_5);
        tv_instituteName_5 = (TextView) findViewById(R.id.tv_instituteName_5);
        tv_passingYear_5 = (TextView) findViewById(R.id.tv_passingYear_5);
        tv_Result_5 = (TextView) findViewById(R.id.tv_Result_5);
        tv_boardName_5 = (TextView) findViewById(R.id.tv_boardName_5);


        if (ResumeProfilePart2.educationQualification.size()>0){
            linearlayout_EducationalQualification_1.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(0);
            tv_educationQualification_1.setText(model.getQualification_name());
            tv_instituteName_1.setText(model.getInstitute_name());
            tv_passingYear_1.setText(model.getPassing_year());
            tv_Result_5.setText(model.getResult());
            tv_boardName_5.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size()>1){
            linearlayout_EducationalQualification_2.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(1);
            tv_educationQualification_2.setText(model.getQualification_name());
            tv_instituteName_2.setText(model.getInstitute_name());
            tv_passingYear_2.setText(model.getPassing_year());
            tv_Result_2.setText(model.getResult());
            tv_boardName_2.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size()>2){
            linearlayout_EducationalQualification_3.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(2);
            tv_educationQualification_3.setText(model.getQualification_name());
            tv_instituteName_3.setText(model.getInstitute_name());
            tv_passingYear_3.setText(model.getPassing_year());
            tv_Result_3.setText(model.getResult());
            tv_boardName_3.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size()>3){
            linearlayout_EducationalQualification_4.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(3);
            tv_educationQualification_4.setText(model.getQualification_name());
            tv_instituteName_4.setText(model.getInstitute_name());
            tv_passingYear_4.setText(model.getPassing_year());
            tv_Result_4.setText(model.getResult());
            tv_boardName_4.setText(model.getBoard_name());
        }

        if (ResumeProfilePart2.educationQualification.size()>4){
            linearlayout_EducationalQualification_5.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(4);
            tv_educationQualification_5.setText(model.getQualification_name());
            tv_instituteName_5.setText(model.getInstitute_name());
            tv_passingYear_5.setText(model.getPassing_year());
            tv_Result_5.setText(model.getResult());
            tv_boardName_5.setText(model.getBoard_name());
        }

*/


        // templete 2 end
    }

    @Override
    public void onClick(View view) {

        if (view == button_Template1){
            startActivity(new Intent(this, FragmentShowActivity.class).putExtra("t",1));

            //verifyTheProcess();
           /* viewFlipper.setInAnimation(this,R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
            //viewFlipper.showPrevious();
            viewFlipper.setDisplayedChild(0);*/
        }

        if (view == button_Template2){
            startActivity(new Intent(this, FragmentShowActivity.class).putExtra("t",2));

          /*  viewFlipper.setInAnimation(this,R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
            viewFlipper.setDisplayedChild(2);*/
        }

        if (view == button_Template3){
            startActivity(new Intent(this, FragmentShowActivity.class).putExtra("t",3));
           /* viewFlipper.setInAnimation(this,R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
            viewFlipper.setDisplayedChild(3);*/
        }
        if (view == button_Template4){
            startActivity(new Intent(this, FragmentShowActivity.class).putExtra("t",4));

           /* viewFlipper.setInAnimation(this,R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
            viewFlipper.setDisplayedChild(4);*/
        }

        if(view == button_Template5)
        {
            startActivity(new Intent(this, FragmentShowActivity.class).putExtra("t",5));

           /* viewFlipper.setInAnimation(this,R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
            viewFlipper.setDisplayedChild(4);*/
        }

        if(view == button_template6)
        {
            startActivity(new Intent(this,FragmentShowActivity.class).putExtra("t",6));
        }

       /* if (view == button_Back){
            viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
            viewFlipper.showNext();
        }
        if (view == button_CompletePayment){
            ResumeTemplate.templateName = "template1";
            verifyTheProcess();
        }
*/

       /* if (view == button_Back2){
            viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
            viewFlipper.setDisplayedChild(1);
        }
        if (view == getButton_CompletePayment2){
            ResumeTemplate.templateName = "template2";
            verifyTheProcess();

            *//*
            Intent intent = new Intent(getApplicationContext(), Template2_pdf.class);
            startActivity(intent);
            *//*
        }*/


       /* if (view == button_Back3){
            viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
            viewFlipper.setDisplayedChild(1);
        }

        if (view == button_CompletePayment3){
            ResumeTemplate.templateName = "template3";
            verifyTheProcess();

           *//* Intent intent = new Intent(getApplicationContext(), Template2_pdf.class);
            startActivity(intent);*//*

        }*/


        if (view == button_Data){
            ShowData();
        }
    }

    private void verifyTheProcess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        finish();
        Intent intent = new Intent(getApplicationContext(),ChargingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT!");
        builder.setMessage("Do You Want To Exit From Make Resume?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToHomeIntent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void goToHomeIntent(){
        finish();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void ShowData(){
        Toast.makeText(getApplicationContext(),"SIZE: "+ResumeProfilePart5.reference.size(),Toast.LENGTH_LONG).show();
        Reference_Model reference_model;
        for (int i=0;i<ResumeProfilePart5.reference.size();i++){
            reference_model = ResumeProfilePart5.reference.get(i);
            Log.d("BuildResumePart5_Data: ",reference_model.getName());
            Log.d("BuildResumePart5_Data: ",reference_model.getOrganization_name());
            Log.d("BuildResumePart5_Data: ",reference_model.getDesignation());
            Log.d("BuildResumePart5_Data: ",reference_model.getMobile_number());
            Log.d("BuildResumePart5_Data: ",reference_model.getEmail());

        }
    }
}
