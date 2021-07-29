package com.cvmaster.xosstech.GeneratePdf;

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

import androidx.appcompat.app.AppCompatActivity;

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
import com.cvmaster.xosstech.viewmodel.Template6ViewModel;
import com.cvmaster.xosstech.viewmodel.Templete4ViewModel;
import com.itextpdf.text.pdf.parser.Line;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PdfActivityTemplate6 extends AppCompatActivity implements View.OnClickListener {

    private String pdfFileName="";
    private Bitmap bitmap;
    private RelativeLayout llPdf;

    private Button btnPrint;
    private Button btnSendEmail;

    private CircularImageView imageView2;

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

    private LinearLayout ll_prexp3;
    private TextView tv_pr_exp3_position;
    private TextView tv_pr_exp3_company;
    private TextView tv_pr_exp3_company_address;
    private TextView tv_pr_exp3_service_time;
    private TextView tv_pr_exp3_service_detail;

    private LinearLayout ll_prexp4;
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

    private Template6ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_template6);

        File file = new File(ResumeProfilePart1.getImagePath());
        List<EducationQualification_Model> educationQualification_models = ResumeProfilePart2.educationQualification;

        llPdf = findViewById(R.id.rl_main_template6);
        btnPrint = findViewById(R.id.btnTemplate6);
        btnPrint.setOnClickListener(this);

        btnSendEmail = findViewById(R.id.button_SendMail_pdfShowTemplate6);
        btnSendEmail.setOnClickListener(this);
        btnSendEmail.setVisibility(View.GONE);

        imageView2 =  findViewById(R.id.image_person_template6);
        imageView2.setImageURI(Uri.fromFile(file));

//        tvFacebook = view.findViewById(R.id.contact_fb);
//        tvLinkedin = view.findViewById(R.id.tv_contact_linkedin5);

        name2 = findViewById(R.id.tv_person_name_template6);
        career_objective2 = findViewById(R.id.tv_profile_description_template6);
        email2 = findViewById(R.id.tv_email_template6);
        location2 = findViewById(R.id.tv_address_template6);
        contact2 = findViewById(R.id.tv_phone_template6);
        tvJobTitle = findViewById(R.id.job_title_main_template6);

        name2.setText(ResumeProfilePart1.getName());
        career_objective2.setText(ResumeProfilePart2.career_objective);
        email2.setText(ResumeProfilePart1.getEmail());
        location2.setText(ResumeProfilePart1.getPresent_address());
        contact2.setText(ResumeProfilePart1.getContact_number());
        tvJobTitle.setText(ResumeProfilePart1.getJobTitle());

//        tvFacebook.setText(ResumeProfilePart1.getfacebook_id());
//        tvLinkedin.setText(ResumeProfilePart1.getlinkedin_id());

        skills2 = findViewById(R.id.tv_skills_msword_template6);

        String _skills2 = "";

        for (int i = 0; i < ResumeProfilePart3.skills.size() - 1; i++) {
            Skills_Model skills_mod = ResumeProfilePart3.skills.get(i);
            _skills2 = _skills2 + skills_mod.getSkill() + "\n";
        }
        Skills_Model skills_mode = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size() - 1);
        _skills2 = _skills2 + skills_mode.getSkill();
        skills2.setText(_skills2);

        bangla_skill2 = findViewById(R.id.tv_lan_bangla_pr_template6);
        english_skill2 = findViewById(R.id.tv_lan_english_pr_template6);

        bangla_skill2.setText(ResumeProfilePart3.bangla_skill_level);
        english_skill2.setText(ResumeProfilePart3.english_skill_level);

        linearLayout_Head2 = findViewById(R.id.rl_references_template6);

//        linearLayout_layout1 = (LinearLayout) view.findViewById(R.id.linearLayout_Reference2_1);
        tv_ref1_name = findViewById(R.id.tv_ref1_name_template6);
        tv_ref1_designation = findViewById(R.id.tv_ref1_designation_template6);
        tv_ref1_company = findViewById(R.id.tv_ref1_company_template6);
        tv_ref1_email = findViewById(R.id.tv_ref1_email_template6);
        tv_ref1_contactnumber = findViewById(R.id.tv_ref1_contactnumber_template6);
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

        linearLayout_ProfessionalExp_Head = findViewById(R.id.ll_work_exp1_template6);

//        ll_prexp1 = (RelativeLayout) view.findViewById(R.id.rl_WorkExp_template4);
        tv_pr_exp1_position = findViewById(R.id.tv_wrk_exp1_job_title_template6);
        tv_pr_exp1_company = findViewById(R.id.tv_wrk_exp1_cname_template6);
        tv_pr_exp1_company_address = findViewById(R.id.tv_wrk_exp1_location_template6);
        tv_pr_exp1_service_time = findViewById(R.id.tv_wrk_exp1_duration_template6);
        tv_pr_exp1_service_detail = findViewById(R.id.tv_wrk_exp1_description_template6);

        ll_prexp2 = findViewById(R.id.ll_work_exp2_template6);
        tv_pr_exp2_position = findViewById(R.id.tv_wrk_exp2_job_title_template6);
        tv_pr_exp2_company = findViewById(R.id.tv_wrk_exp2_cname_template6);
        tv_pr_exp2_company_address = findViewById(R.id.tv_wrk_exp2_location_template6);
        tv_pr_exp2_service_time = findViewById(R.id.tv_wrk_exp2_duration_template6);
        tv_pr_exp2_service_detail = findViewById(R.id.tv_wrk_exp2_description_template6);

        ll_prexp3 = findViewById(R.id.ll_work_exp3_template6);
        tv_pr_exp3_position =  findViewById(R.id.tv_wrk_exp3_job_title_template6);
        tv_pr_exp3_company = findViewById(R.id.tv_wrk_exp3_cname_template6);
        tv_pr_exp3_company_address = findViewById(R.id.tv_wrk_exp3_location_template6);
        tv_pr_exp3_service_time = findViewById(R.id.tv_wrk_exp3_duration_template6);
        tv_pr_exp3_service_detail = findViewById(R.id.tv_wrk_exp3_description_template6);

        ll_prexp4 = findViewById(R.id.ll_work_exp4);
        tv_pr_exp4_position = findViewById(R.id.tv_wrk_exp4_job_title);
        tv_pr_exp4_company = findViewById(R.id.tv_wrk_exp4_cname);
        tv_pr_exp4_company_address = findViewById(R.id.tv_wrk_exp4_location);
        tv_pr_exp4_service_time = findViewById(R.id.tv_wrk_exp4_duration);
        tv_pr_exp4_service_detail = findViewById(R.id.tv_wrk_exp4_description);


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

        if (ResumeProfilePart6.workExperience.size() > 2) {

            ll_prexp3.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp3_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp3_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp3_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp3_service_time.setText(workExperience_mod.getDurationTime());
            tv_pr_exp3_service_detail.setText(workExperience_mod.getworkDetail());

        }

        if (ResumeProfilePart6.workExperience.size() > 3) {

            ll_prexp4.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp4_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp4_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp4_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp4_service_time.setText(workExperience_mod.getDurationTime());
            tv_pr_exp4_service_detail.setText(workExperience_mod.getworkDetail());

        }

        linearlayout_EducationalQualification_1 = findViewById(R.id.rl_education_template6);
        tv_educationQualification_1 = findViewById(R.id.tv_educationQualification_1_template6);
        tv_instituteName_1 = findViewById(R.id.tv_instituteName_1_template6);
        tv_passingYear_1 = findViewById(R.id.tv_passingYear_1_template6);
        tv_Result_1 = findViewById(R.id.tv_Result_1_template6); // Remove
        tv_boardName_1 = findViewById(R.id.tv_boardName_1_template6); // Remove

        linearlayout_EducationalQualification_2 = findViewById(R.id.linearlayout_EducationalQualification_2_template6);
        tv_educationQualification_2 = findViewById(R.id.tv_educationQualification_2_template6);
        tv_instituteName_2 = findViewById(R.id.tv_instituteName_2_template6);
        tv_passingYear_2 = findViewById(R.id.tv_passingYear_2_template6);
        tv_Result_2 = findViewById(R.id.tv_Result_2_template6); // Remove
        tv_boardName_2 = findViewById(R.id.tv_boardName_2_template6); // Remove

        linearlayout_EducationalQualification_3 = findViewById(R.id.linearlayout_EducationalQualification_3_template6);
        tv_educationQualification_3 = findViewById(R.id.tv_educationQualification_2_template6);
        tv_instituteName_3 = findViewById(R.id.tv_instituteName_2_template6);
        tv_passingYear_3 = findViewById(R.id.tv_passingYear_2_template6);
        tv_Result_3 = findViewById(R.id.tv_Result_2_template6); // Remove
        tv_boardName_3 = findViewById(R.id.tv_boardName_2_template6); // Remove

        linearlayout_EducationalQualification_4 = findViewById(R.id.linearlayout_EducationalQualification_4_template6);
        tv_educationQualification_4 = findViewById(R.id.tv_educationQualification_4_template6);
        tv_instituteName_4 = findViewById(R.id.tv_instituteName_4_template6);
        tv_passingYear_4 = findViewById(R.id.tv_passingYear_4_template6);
        tv_Result_4 = findViewById(R.id.tv_Result_4_template6);
        tv_boardName_4 = findViewById(R.id.tv_boardName_4_template6);

        linearlayout_EducationalQualification_5 = findViewById(R.id.linearlayout_EducationalQualification_5_template6);
        tv_educationQualification_5 = findViewById(R.id.tv_educationQualification_5_template6);
        tv_instituteName_5 = findViewById(R.id.tv_instituteName_5_template6);
        tv_passingYear_5 = findViewById(R.id.tv_passingYear_5_template6);
        tv_Result_5 = findViewById(R.id.tv_Result_5_template6);
        tv_boardName_5 = findViewById(R.id.tv_boardName_5_template6);


        if (ResumeProfilePart2.educationQualification.size() > 0) {
            linearlayout_EducationalQualification_1.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(0);
            tv_educationQualification_1.setText(model.getQualification_name());
            tv_instituteName_1.setText(model.getInstitute_name());
            tv_passingYear_1.setText(model.getPassing_year());
            tv_Result_1.setText(model.getResult());
            if(model.getBoard_name().equals("Select"))
            {
                tv_boardName_1.setVisibility(View.GONE);
            }
            else {
                tv_boardName_1.setText(model.getBoard_name());
            }
        }

        if (ResumeProfilePart2.educationQualification.size() > 1) {
            linearlayout_EducationalQualification_2.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(1);
            tv_educationQualification_2.setText(model.getQualification_name());
            tv_instituteName_2.setText(model.getInstitute_name());
            tv_passingYear_2.setText(model.getPassing_year());
            tv_Result_2.setText(model.getResult());
            if(model.getBoard_name().equals("Select"))
            {
                tv_boardName_2.setVisibility(View.GONE);
            }
            else {
                tv_boardName_2.setText(model.getBoard_name());
            }
        }

        if (ResumeProfilePart2.educationQualification.size() > 2) {
            linearlayout_EducationalQualification_3.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(2);
            tv_educationQualification_3.setText(model.getQualification_name());
            tv_instituteName_3.setText(model.getInstitute_name());
            tv_passingYear_3.setText(model.getPassing_year());
            tv_Result_3.setText(model.getResult());
            if(model.getBoard_name().equals("Select"))
            {
                tv_boardName_3.setVisibility(View.GONE);
            }
            else {
                tv_boardName_3.setText(model.getBoard_name());
            }
        }

        if (ResumeProfilePart2.educationQualification.size() > 3) {
            linearlayout_EducationalQualification_4.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(3);
            tv_educationQualification_4.setText(model.getQualification_name());
            tv_instituteName_4.setText(model.getInstitute_name());
            tv_passingYear_4.setText(model.getPassing_year());
            tv_Result_4.setText(model.getResult());
            if(model.getBoard_name().equals("Select"))
            {
                tv_boardName_4.setVisibility(View.GONE);
            }
            else {
                tv_boardName_4.setText(model.getBoard_name());
            }
        }

        if (ResumeProfilePart2.educationQualification.size() > 4) {
            linearlayout_EducationalQualification_5.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(4);
            tv_educationQualification_5.setText(model.getQualification_name());
            tv_instituteName_5.setText(model.getInstitute_name());
            tv_passingYear_5.setText(model.getPassing_year());
            tv_Result_5.setText(model.getResult());
            if(model.getBoard_name().equals("Select"))
            {
                tv_boardName_5.setVisibility(View.GONE);
            }
            else {
                tv_boardName_5.setText(model.getBoard_name());
            }
        }
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){

        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        pdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory()+"/ResumeApp");

        if (!ResumeAppFolder.exists()){
            ResumeAppFolder.mkdir();
        }

        String mFilePath = Environment.getExternalStorageDirectory() + "/ResumeApp/" + pdfFileName + ".pdf";


        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, 595, 842, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 2, 0 , null);
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

    @Override
    public void onClick(View v) {
        if ( v== btnPrint)
        {
            btnPrint.setVisibility(View.GONE);
            bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
            createPdf();
            final LoadingDialog loadingDialog = new LoadingDialog(PdfActivityTemplate6.this) ;

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

        if(v == btnSendEmail )
        {
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename",pdfFileName+".pdf");
            startActivity(intent);
        }
    }
}