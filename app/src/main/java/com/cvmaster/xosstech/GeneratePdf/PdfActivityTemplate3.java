package com.cvmaster.xosstech.GeneratePdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SendMail;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.viewmodel.Templete3ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PdfActivityTemplate3 extends AppCompatActivity implements View.OnClickListener {

    private Bitmap bitmap;
    private LinearLayout llPdf2;
    private String pdfFileName="";

    Button btnPrint,btnSendEmail;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_template3);

        viewFlipper = findViewById(R.id.viewFlipper);
        imageView_Template3_image = findViewById(R.id.template3_image);
        File file3 = new File(ResumeProfilePart1.getImagePath());
        imageView_Template3_image.setImageURI(Uri.fromFile(file3));

        textView_Template3_name = findViewById(R.id.tv_heading_name3);
        textView_Template3_name.setText(ResumeProfilePart1.getName());

        textView_Template3_careerObjective = findViewById(R.id.tv_Career_Objective_Container3);
        textView_Template3_careerObjective.setText(ResumeProfilePart2.career_objective);

        textView_Template3_contactNumber = findViewById(R.id.tv_number3);
        textView_Template3_contactNumber.setText(ResumeProfilePart1.getContact_number());

        textView_Template3_email = findViewById(R.id.tv_email3);
        textView_Template3_email.setText(ResumeProfilePart1.getEmail());

        textView_Template3_curresntAddress = findViewById(R.id.tv_Address3);
        textView_Template3_curresntAddress.setText(ResumeProfilePart1.getPresent_address());
        llPdf2 = findViewById(R.id.llpdf3);


        //Education Start

        relativeLayout_Template3_RL_SSC = findViewById(R.id.RL_SSC);
        relativeLayout_Template3_RL_SSC.setVisibility(View.GONE);

        textView_Template3_tv_SSC_Heading = findViewById(R.id.tv_SSC_Heading);
        textView_Template3_SSC_Year = findViewById(R.id.SSC_Year);
        textView_Template3_tv_SSC_Institute_Name = findViewById(R.id.tv_SSC_Institute_Name);
        textView_Template3_tv_SSC_Subject_Name = findViewById(R.id.tv_SSC_Subject_Name);
        textView_Template3_tv_SSC_Result = findViewById(R.id.tv_SSC_Result);

        relativeLayout_Template3_RL_HSC = findViewById(R.id.RL_HSC);
        relativeLayout_Template3_RL_HSC.setVisibility(View.GONE);

        textView_Template3_tv_HSC_Heading = findViewById(R.id.tv_HSC_Heading);
        textView_Template3_HSC_Year = findViewById(R.id.HSC_Year);
        textView_Template3_tv_HSC_Institute_Name = findViewById(R.id.tv_HSC_Institute_Name);
        textView_Template3_tv_HSC_Subject_Name = findViewById(R.id.tv_HSC_Subject_Name);
        textView_Template3_tv_HSC_Result = findViewById(R.id.tv_HSC_Result);

        relativeLayout_Template3_RL_Univarsity_Name = findViewById(R.id.RL_Univarsity_Name);
        relativeLayout_Template3_RL_Univarsity_Name.setVisibility(View.GONE);

        textView_Template3_tv_Univarsity_Heading = findViewById(R.id.tv_Univarsity_Heading);
        textView_Template3_Honrs_Year = findViewById(R.id.Honrs_Year);
        textView_Template3_tv_Honrs_Institute_Name = findViewById(R.id.tv_Honrs_Institute_Name);
        textView_Template3_tv_Honrs_Subject_Name = findViewById(R.id.tv_Honrs_Subject_Name);
        textView_Template3_tv_Honrs_Result = findViewById(R.id.tv_Honrs_Result);

        relativeLayout_Template3_RL_Masters = findViewById(R.id.RL_Masters);
        relativeLayout_Template3_RL_Masters.setVisibility(View.GONE);

        textView_Template3_tv_Masters_Heading = findViewById(R.id.tv_Masters_Heading);
        textView_Template3_Master_Year = findViewById(R.id.Master_Year);
        textView_Template3_tv_Masters_Institute_Name = findViewById(R.id.tv_Masters_Institute_Name);
        textView_Template3_tv_Masters_Subject_Name = findViewById(R.id.tv_Masters_Subject_Name);
        textView_Template3_tv_Masters_Result = findViewById(R.id.tv_Masters_Result);



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

        textView_Template3_skill1 = findViewById(R.id.tv_Skills_Template3_1);
        textView_Template3_skill1.setVisibility(View.INVISIBLE);
        textView_Template3_skill2 = findViewById(R.id.tv_Skills_Template3_2);
        textView_Template3_skill2.setVisibility(View.INVISIBLE);
        textView_Template3_skill3 = findViewById(R.id.tv_Skills_Template3_3);
        textView_Template3_skill3.setVisibility(View.INVISIBLE);
        textView_Template3_skill4 = findViewById(R.id.tv_Skills_Template3_4);
        textView_Template3_skill4.setVisibility(View.INVISIBLE);
        textView_Template3_skill5 = findViewById(R.id.tv_Skills_Template3_5);
        textView_Template3_skill5.setVisibility(View.INVISIBLE);
        textView_Template3_skill6 = findViewById(R.id.tv_Skills_Template3_6);
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

        textView_Template3_skillEng = findViewById(R.id.tv_Language_English_Proficiency);
        textView_Template3_skillEng.setText(ResumeProfilePart3.english_skill_level);

        textView_Template3_skillBangla = findViewById(R.id.tv_Language_Bangla_Proficiency);
        textView_Template3_skillBangla.setText(ResumeProfilePart3.bangla_skill_level);

        //Work Exp
        relativeLayout_Template3_WorkExpHeading = findViewById(R.id.RL_Work_Exp_Heading);
        relativeLayout_Template3_WorkExpHeading.setVisibility(View.GONE);

        relativeLayout_Template3_WorkExp1 = findViewById(R.id.RL_Work_Exp_1);
        relativeLayout_Template3_WorkExp1.setVisibility(View.GONE);

        textView_Template3_WorkExp1Designation = findViewById(R.id.tv_Work_Exp_1_Heading);
        textView_Template3_WorkExp1Duration = findViewById(R.id.Work_Exp_1_Year);
        textView_Template3_WorkExp1OrgName = findViewById(R.id.tv_Work_Exp_1_Org_Name);
        textView_Template3_WorkExp1OrgAddress = findViewById(R.id.tv_Work_Exp_1_Org_Address);

        relativeLayout_Template3_WorkExp2 = findViewById(R.id.RL_Work_Exp_2);
        relativeLayout_Template3_WorkExp2.setVisibility(View.GONE);

        textView_Template3_WorkExp2Designation = findViewById(R.id.tv_Work_Exp_2_Heading);
        textView_Template3_WorkExp2Duration = findViewById(R.id.Work_Exp_2_Year);
        textView_Template3_WorkExp2OrgName = findViewById(R.id.tv_Work_Exp_2_Org_Name);
        textView_Template3_WorkExp2OrgAddress = findViewById(R.id.tv_Work_Exp_2_Org_Address);



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
        linearLayout_Template3_ReferenceHeading = findViewById(R.id.Linear_Reference_Heading);
        linearLayout_Template3_ReferenceHeading.setVisibility(View.GONE);

        relativeLayout_Template3_Ref1 = findViewById(R.id.RL_Reference_1);
        relativeLayout_Template3_Ref1.setVisibility(View.GONE);

        textView_Template3_Ref1Name = findViewById(R.id.tv_Reference_1_Name);
        textView_Template3_Ref1Designation = findViewById(R.id.tv_Reference_1_Designation);
        textView_Template3_Ref1OrgName = findViewById(R.id.tv_Reference_1_Company_Name);
        textView_Template3_Ref1Email = findViewById(R.id.tv_Reference_1_Email);
        textView_Template3_Ref1Mobile = findViewById(R.id.tv_Reference_1_Number);

        relativeLayout_Template3_Ref2 = findViewById(R.id.RL_Reference_2);
        relativeLayout_Template3_Ref2.setVisibility(View.GONE);
        textView_Template3_Ref2Name = findViewById(R.id.tv_Reference_2_Name);;
        textView_Template3_Ref2Designation = findViewById(R.id.tv_Reference_2_Designation);
        textView_Template3_Ref2OrgName = findViewById(R.id.tv_Reference_2_Company_Name);
        textView_Template3_Ref2Email= findViewById(R.id.tv_Reference_2_Email);
        textView_Template3_Ref2Mobile = findViewById(R.id.tv_Reference_2_Number);


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


        btnPrint = findViewById(R.id.btnTemplate3);
        btnPrint.setOnClickListener(this);
        btnSendEmail = findViewById(R.id.button_SendMail_pdfShow3);
        btnSendEmail.setOnClickListener(this);
        btnSendEmail.setVisibility(View.GONE);
    }

    private void createPdf(){


        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        pdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory()+"/ResumeApp");

        if (!ResumeAppFolder.exists()){
            ResumeAppFolder.mkdir();
        }

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, 595, 842, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/documents/" + pdfFileName + ".pdf";

        File filePath;
        filePath = new File(Environment.getExternalStorageDirectory(),targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @Override
    public void onClick(View v) {

        if (v == btnPrint){

            btnPrint.setVisibility(View.GONE);
            bitmap = loadBitmapFromView(llPdf2, llPdf2.getWidth(), llPdf2.getHeight());
            createPdf();
            final LoadingDialog loadingDialog = new LoadingDialog(PdfActivityTemplate3.this) ;

            btnSendEmail.setVisibility(View.VISIBLE);
            loadingDialog.startloadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dissmissDialog();
                }
            },2000);

        }

        if (v == btnSendEmail){
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename",pdfFileName+".pdf");
            startActivity(intent);
        }
    }
}