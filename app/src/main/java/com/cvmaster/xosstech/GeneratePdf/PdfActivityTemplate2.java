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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.BuildResumePDF_Part1;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SendMail;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.ResumeTemplate;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.viewmodel.Templete2ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PdfActivityTemplate2 extends AppCompatActivity implements View.OnClickListener {


    private Templete2ViewModel mViewModel;
    private String pdfFileName="";

    private ViewFlipper viewFlipper;
    private Button btnPrint,btnSendMail;
    private Bitmap bitmap;
    private LinearLayout llPdf2;

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

    private LinearLayout ll_prexp3;
    private TextView tv_pr_exp3_position;
    private TextView tv_pr_exp3_company;
    private TextView tv_pr_exp3_company_address;
    private TextView tv_pr_exp3_service_time;

    private LinearLayout ll_prexp4;
    private TextView tv_pr_exp4_position;
    private TextView tv_pr_exp4_company;
    private TextView tv_pr_exp4_company_address;
    private TextView tv_pr_exp4_service_time;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_template2);

        File file = new File(ResumeProfilePart1.getImagePath());
        List<EducationQualification_Model> educationQualification_models = ResumeProfilePart2.educationQualification;

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        btnPrint = findViewById(R.id.btnTemplate2);
        btnSendMail = findViewById(R.id.button_SendMail_pdfShow2);
        llPdf2 = findViewById(R.id.llpdf2);

        btnPrint.setOnClickListener(this);
        btnSendMail.setOnClickListener(this);

        imageView2 = findViewById(R.id.image2);
        imageView2.setImageURI(Uri.fromFile(file));

        name2 = findViewById(R.id.tv_heading_name);
        career_objective2 = findViewById(R.id.tv_CareerObjective_Container);
        email2 = findViewById(R.id.tv_email2);
        location2 = findViewById(R.id.tv_number);
        contact2 = findViewById(R.id.tv_address);

        name2.setText(ResumeProfilePart1.getName());
        career_objective2.setText(ResumeProfilePart2.career_objective);
        email2.setText(ResumeProfilePart1.getEmail());
        location2.setText(ResumeProfilePart1.getPresent_address());
        contact2.setText(ResumeProfilePart1.getContact_number());

        skills2 = findViewById(R.id.tv_skills_msword);

        btnSendMail.setVisibility(View.GONE);

        String _skills2 = "";

        for (int i = 0; i < ResumeProfilePart3.skills.size() - 1; i++) {
            Skills_Model skills_mod = ResumeProfilePart3.skills.get(i);
            _skills2 = _skills2 + skills_mod.getSkill() + "\n";
        }
        Skills_Model skills_mode = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size() - 1);
        _skills2 = _skills2 + skills_mode.getSkill();
        skills2.setText(_skills2);

        bangla_skill2 = findViewById(R.id.tv_language_bangla_pr);
        english_skill2 = findViewById(R.id.tv_language_english_pr);

        bangla_skill2.setText(ResumeProfilePart3.bangla_skill_level);
        english_skill2.setText(ResumeProfilePart3.english_skill_level);

        linearLayout_Head2 = findViewById(R.id.linearLayout_Head_References_2);

        linearLayout_layout1 = findViewById(R.id.linearLayout_Reference2_1);
        tv_ref1_name = findViewById(R.id.tv_ref1_name);
        tv_ref1_designation = findViewById(R.id.tv_ref1_designation);
        tv_ref1_company = findViewById(R.id.tv_ref1_company);
        tv_ref1_email = findViewById(R.id.tv_ref1_email);
        tv_ref1_contactnumber = findViewById(R.id.tv_ref1_contactnumber);

        linearLayout_layout2 = findViewById(R.id.linearLayout_Reference2_2);
        tv_ref2_name = findViewById(R.id.tv_ref2_name);
        tv_ref2_designation = findViewById(R.id.tv_ref2_designation);
        tv_ref2_company = findViewById(R.id.tv_ref2_company);
        tv_ref2_email = findViewById(R.id.tv_ref2_email);
        tv_ref2_contactnumber = findViewById(R.id.tv_ref2_contactnumber);

        List<Reference_Model> reference_model = ResumeProfilePart5.reference;

        if (ResumeProfilePart5.reference.size() > 0) {
            linearLayout_Head2.setVisibility(View.VISIBLE);

            linearLayout_layout1.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(0);

            tv_ref1_name.setText(reference_mod.getName());
            tv_ref1_designation.setText(reference_mod.getDesignation());
            tv_ref1_company.setText(reference_mod.getDesignation());
            tv_ref1_email.setText(reference_mod.getEmail());
            tv_ref1_contactnumber.setText(reference_mod.getMobile_number());
        }

        if (ResumeProfilePart5.reference.size() > 1) {
            linearLayout_layout2.setVisibility(View.VISIBLE);

            Reference_Model reference_mod = reference_model.get(1);

            tv_ref2_name.setText(reference_mod.getName());
            tv_ref2_designation.setText(reference_mod.getDesignation());
            tv_ref2_company.setText(reference_mod.getDesignation());
            tv_ref2_email.setText(reference_mod.getEmail());
            tv_ref2_contactnumber.setText(reference_mod.getMobile_number());
        }

        linearLayout_ProfessionalExp_Head = findViewById(R.id.linearLayout_ProfessionalExp_Head);

        ll_prexp1 = findViewById(R.id.ll_prexp1);
        tv_pr_exp1_position = findViewById(R.id.tv_pr_exp1_position);
        tv_pr_exp1_company = findViewById(R.id.tv_pr_exp1_company);
        tv_pr_exp1_company_address = findViewById(R.id.tv_pr_exp1_company_address);
        tv_pr_exp1_service_time = findViewById(R.id.tv_pr_exp1_service_time);

        ll_prexp2 = findViewById(R.id.ll_prexp2);
        tv_pr_exp2_position = findViewById(R.id.tv_pr_exp2_position);
        tv_pr_exp2_company = findViewById(R.id.tv_pr_exp2_company);
        tv_pr_exp2_company_address = findViewById(R.id.tv_pr_exp2_company_address);
        tv_pr_exp2_service_time = findViewById(R.id.tv_pr_exp2_service_time);

        ll_prexp3 = findViewById(R.id.ll_prexp3);
        tv_pr_exp3_position = findViewById(R.id.tv_pr_exp3_position);
        tv_pr_exp3_company = findViewById(R.id.tv_pr_exp3_company);
        tv_pr_exp3_company_address = findViewById(R.id.tv_pr_exp3_company_address);
        tv_pr_exp3_service_time = findViewById(R.id.tv_pr_exp3_service_time);

        ll_prexp4 = findViewById(R.id.ll_prexp4);
        tv_pr_exp4_position = findViewById(R.id.tv_pr_exp4_position);
        tv_pr_exp4_company = findViewById(R.id.tv_pr_exp4_company);
        tv_pr_exp4_company_address = findViewById(R.id.tv_pr_exp4_company_address);
        tv_pr_exp4_service_time = findViewById(R.id.tv_pr_exp4_service_time);


        List<WorkExperience_Model> workExperience_model = ResumeProfilePart6.workExperience;
        if (ResumeProfilePart6.workExperience.size() > 0) {
            linearLayout_ProfessionalExp_Head.setVisibility(View.VISIBLE);

            ll_prexp1.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(0);
            tv_pr_exp1_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp1_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp1_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp1_service_time.setText(workExperience_mod.getDurationTime());

        }

        if (ResumeProfilePart6.workExperience.size() > 1) {

            ll_prexp2.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp2_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp2_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp2_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp2_service_time.setText(workExperience_mod.getDurationTime());

        }

        if (ResumeProfilePart6.workExperience.size() > 2) {

            ll_prexp3.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp3_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp3_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp3_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp3_service_time.setText(workExperience_mod.getDurationTime());

        }

        if (ResumeProfilePart6.workExperience.size() > 3) {

            ll_prexp4.setVisibility(View.VISIBLE);

            WorkExperience_Model workExperience_mod = workExperience_model.get(1);
            tv_pr_exp4_position.setText(workExperience_mod.getDesignationName());
            tv_pr_exp4_company.setText(workExperience_mod.getOrganizationName());
            tv_pr_exp4_company_address.setText(workExperience_mod.getOgganizationAddress());
            tv_pr_exp4_service_time.setText(workExperience_mod.getDurationTime());

        }

        linearlayout_EducationalQualification_1 = findViewById(R.id.linearlayout_EducationalQualification_1);
        tv_educationQualification_1 = findViewById(R.id.tv_educationQualification_1);
        tv_instituteName_1 = findViewById(R.id.tv_instituteName_1);
        tv_passingYear_1 = findViewById(R.id.tv_passingYear_1);
        tv_Result_1 = findViewById(R.id.tv_Result_1);
        tv_boardName_1 = findViewById(R.id.tv_boardName_1);

        linearlayout_EducationalQualification_2 = findViewById(R.id.linearlayout_EducationalQualification_2);
        tv_educationQualification_2 = findViewById(R.id.tv_educationQualification_2);
        tv_instituteName_2 = findViewById(R.id.tv_instituteName_2);
        tv_passingYear_2 = findViewById(R.id.tv_passingYear_2);
        tv_Result_2 = findViewById(R.id.tv_Result_2);
        tv_boardName_2 = findViewById(R.id.tv_boardName_2);

        linearlayout_EducationalQualification_3 = findViewById(R.id.linearlayout_EducationalQualification_3);
        tv_educationQualification_3 = findViewById(R.id.tv_educationQualification_3);
        tv_instituteName_3 = findViewById(R.id.tv_instituteName_3);
        tv_passingYear_3 = findViewById(R.id.tv_passingYear_3);
        tv_Result_3 = findViewById(R.id.tv_Result_3);
        tv_boardName_3 = findViewById(R.id.tv_boardName_3);

        linearlayout_EducationalQualification_4 = findViewById(R.id.linearlayout_EducationalQualification_4);
        tv_educationQualification_4 = findViewById(R.id.tv_educationQualification_4);
        tv_instituteName_4 = findViewById(R.id.tv_instituteName_4);
        tv_passingYear_4 = findViewById(R.id.tv_passingYear_4);
        tv_Result_4 = findViewById(R.id.tv_Result_4);
        tv_boardName_4 = findViewById(R.id.tv_boardName_4);

        linearlayout_EducationalQualification_5 = findViewById(R.id.linearlayout_EducationalQualification_5);
        tv_educationQualification_5 = findViewById(R.id.tv_educationQualification_5);
        tv_instituteName_5 = findViewById(R.id.tv_instituteName_5);
        tv_passingYear_5 = findViewById(R.id.tv_passingYear_5);
        tv_Result_5 = findViewById(R.id.tv_Result_5);
        tv_boardName_5 = findViewById(R.id.tv_boardName_5);


        if (ResumeProfilePart2.educationQualification.size() > 0) {
            linearlayout_EducationalQualification_1.setVisibility(View.VISIBLE);

            EducationQualification_Model model = educationQualification_models.get(0);
            tv_educationQualification_1.setText(model.getQualification_name());
            tv_instituteName_1.setText(model.getInstitute_name());
            tv_passingYear_1.setText(model.getPassing_year());
            tv_Result_5.setText(model.getResult());
            tv_boardName_5.setText(model.getBoard_name());
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

    @Override
    public void onClick(View v) {

        if (v == btnPrint){

            btnPrint.setVisibility(View.GONE);
            bitmap = loadBitmapFromView(llPdf2, llPdf2.getWidth(), llPdf2.getHeight());
            createPdf();
            final LoadingDialog loadingDialog = new LoadingDialog(PdfActivityTemplate2.this) ;

            btnSendMail.setVisibility(View.VISIBLE);
            loadingDialog.startloadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dissmissDialog();
                }
            },2000);

        }

        if (v == btnSendMail){
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename",pdfFileName+".pdf");
            startActivity(intent);
        }
    }
}