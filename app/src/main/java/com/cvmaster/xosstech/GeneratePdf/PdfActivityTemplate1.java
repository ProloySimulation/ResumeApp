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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SendMail;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.viewmodel.Templete1ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PdfActivityTemplate1 extends AppCompatActivity implements View.OnClickListener {


    private ViewFlipper viewFlipper;
    private String pdfFileName="";

    private Templete1ViewModel mViewModel;
    private Bitmap bitmap;
    private LinearLayout llPdf ;
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
    private Button btn,btnEmail ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_show);

        //Part 1
        textView_tv_Heading_Name = findViewById(R.id.tv_Heading_Name);
        btn = findViewById(R.id.btn);
        btnEmail = findViewById(R.id.button_SendMail_pdfShow);

        btn.setOnClickListener(this);
        btnEmail.setOnClickListener(this);

        btnEmail.setVisibility(View.GONE);
        textView_tv_Mobile = findViewById(R.id.tv_Mobile);
        textView_tv_email = findViewById(R.id.tv_email);
        textView_tv_Address = findViewById(R.id.tv_Address);
        imageView_image = findViewById(R.id.image);
        llPdf = findViewById(R.id.lldf);

        textView_tv_Heading_Name.setText(ResumeProfilePart1.getName());
        textView_tv_Mobile.setText(ResumeProfilePart1.getContact_number());
        textView_tv_email.setText(ResumeProfilePart1.getEmail());
        textView_tv_Address.setText(ResumeProfilePart1.getPresent_address());
        File file = new File(ResumeProfilePart1.getImagePath());
        imageView_image.setImageURI(Uri.fromFile(file));

        //Part 2
        textView_tv_Career_Objective_Container = findViewById(R.id.tv_Career_Objective_Container);
        textView_tv_Career_Objective_Container.setText(ResumeProfilePart2.career_objective);

        tableRow_row1 = findViewById(R.id.row1);
        textView_title1 = findViewById(R.id.title1);
        textView_concentration1 = findViewById(R.id.concentration1);
        textView_institute1 = findViewById(R.id.institute1);
        textView_board1 = findViewById(R.id.board1);
        textView_result1 = findViewById(R.id.result1);
        textView_year1 = findViewById(R.id.year1);

        tableRow_row2 = findViewById(R.id.row2);
        textView_title2 = findViewById(R.id.title2);
        textView_concentration2 = findViewById(R.id.concentration2);
        textView_institute2 = findViewById(R.id.institute2);
        textView_board2 = findViewById(R.id.board2);
        textView_result2 = findViewById(R.id.result2);
        textView_year2 = findViewById(R.id.year2);

        tableRow_row3 = findViewById(R.id.row3);
        textView_title3 = findViewById(R.id.title3);
        textView_concentration3 = findViewById(R.id.concentration3);
        textView_institute3 = findViewById(R.id.institute3);
        textView_board3 = findViewById(R.id.board3);
        textView_result3 = findViewById(R.id.result3);
        textView_year3 = findViewById(R.id.year3);

        tableRow_row4 = findViewById(R.id.row4);
        textView_title4 = findViewById(R.id.title4);
        textView_concentration4 = findViewById(R.id.concentration4);
        textView_institute4 = findViewById(R.id.institute4);
        textView_board4 = findViewById(R.id.board4);
        textView_result4 = findViewById(R.id.result4);
        textView_year4 = findViewById(R.id.year4);

        tableRow_row5 = findViewById(R.id.row5);
        textView_title5 = findViewById(R.id.title5);
        textView_concentration5 = findViewById(R.id.concentration5);
        textView_institute5 = findViewById(R.id.institute5);
        textView_board5 = findViewById(R.id.board5);
        textView_result5 = findViewById(R.id.result5);
        textView_year5 = findViewById(R.id.year5);

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
        textView_tv_Special_Qualities = findViewById(R.id.tv_Special_Qualities);
        textView_bangla_Efficienty = findViewById(R.id.bangla_Efficienty);
        textView_english_Efficienty = findViewById(R.id.english_Efficienty);

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
        textView_tv_pd_Name = findViewById(R.id.tv_pd_Name);
        textView_tv_pd_FName = findViewById(R.id.tv_pd_FName);
        textView_tv_pd_MName = findViewById(R.id.tv_pd_MName);
        textView_tv_pd_Gender = findViewById(R.id.tv_pd_Gender);
        textView_tv_pd_DOB = findViewById(R.id.tv_pd_DOB);
        textView_tv_pd_Status = findViewById(R.id.tv_pd_Status);
        textView_tv_pd_Nationality = findViewById(R.id.tv_pd_Nationality);
        textView_tv_pd_ReligionName = findViewById(R.id.tv_pd_ReligionName);
        textView_tv_pd_PRAD = findViewById(R.id.tv_pd_PRAD);
        textView_tv_pd_PERAD = findViewById(R.id.tv_pd_PERAD);

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
        relativeLayout_rl_Reference1 = findViewById(R.id.rl_Reference1);

        relativeLayout_rl_ref_1 = findViewById(R.id.rl_ref_1);
        textView_tv_References_Name = findViewById(R.id.tv_References_Name);
        textView_tv_References_Designation = findViewById(R.id.tv_References_Designation);
        textView_tv_References_COMName = findViewById(R.id.tv_References_COMName);
        textView_tv_References_Email = findViewById(R.id.tv_References_Email);
        textView_tv_References_Contact = findViewById(R.id.tv_References_Contact);

        relativeLayout_rl_ref_2 = findViewById(R.id.rl_ref_2);
        textView_tv_References_Name2 = findViewById(R.id.tv_References_Name2);
        textView_tv_References_Designation2 = findViewById(R.id.tv_References_Designation2);
        textView_tv_References_COMName2 = findViewById(R.id.tv_References_COMName2);
        textView_tv_References_Email2 = findViewById(R.id.tv_References_Email2);
        textView_tv_References_Contact2 = findViewById(R.id.tv_References_Contact2);

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
        relativeLayout_rl_WorkExp_Heading = findViewById(R.id.rl_WorkExp_Heading);

        relativeLayout_rl_WorkExp = findViewById(R.id.rl_WorkExp);
        textView_tv_WorkExp_DesigName = findViewById(R.id.tv_WorkExp_DesigName);
        textView_tv_WorkExp_ComName = findViewById(R.id.tv_WorkExp_ComName);
        textView_tv_WorkExp_ComAddress = findViewById(R.id.tv_WorkExp_ComAddress);

        relativeLayout_rl_WorkExp2 = findViewById(R.id.rl_WorkExp2);
        textView_tv_WorkExp_DesigName2 = findViewById(R.id.tv_WorkExp_DesigName2);
        textView_tv_WorkExp_ComName2 = findViewById(R.id.tv_WorkExp_ComName2);
        textView_tv_WorkExp_ComAddress2 = findViewById(R.id.tv_WorkExp_ComAddress2);

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
        if ( v== btn)
        {
            btn.setVisibility(View.GONE);
            bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
            createPdf();
            final LoadingDialog loadingDialog = new LoadingDialog(PdfActivityTemplate1.this) ;

            btnEmail.setVisibility(View.VISIBLE);
            loadingDialog.startloadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dissmissDialog();
                }
            },2000);
        }

        if(v == btnEmail )
        {
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename",pdfFileName+".pdf");
            startActivity(intent);
        }
    }

    /*private void displayFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory()+"/documents/"+pdfFileName+".pdf");
        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }*/
}
