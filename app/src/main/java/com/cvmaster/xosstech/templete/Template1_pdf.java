package com.cvmaster.xosstech.templete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SendMail;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.shockwave.pdfium.PdfDocument;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Template1_pdf extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener {

    final private int REQUEST_CODE_ASK_PERMISSION = 111;
    private File pdfFile;
    private static final int STOREGE_CODE = 1000;

    private Button button_SendMail;

    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName="";
    String TAG="PDF";
    int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template1_pdf);

        button_SendMail = (Button) findViewById(R.id.button_SendMail);
        button_SendMail.setOnClickListener(this);

        //Permission();
        BuildResumePDF();
        init();

        editDataInputted();
    }

    public void editDataInputted(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("resume_count").document("basic_cv");

        FirebaseFirestore.getInstance()
                .collection("resume_count")
                .document("basic_cv")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int cv_num = Integer.parseInt(documentSnapshot.getString("basic_cv_num"));
                        Log.d("HELLO", "Number: "+cv_num);
                        cv_num++;

                        Map<String, Object> data = new HashMap<>();
                        String numb = Integer.toString(cv_num);
                        data.put("basic_cv_num",numb);

                        documentReference.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    public void BuildResumePDF(){

        pdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory()+"/ResumeApp");

        if (!ResumeAppFolder.exists()){
            ResumeAppFolder.mkdir();
        }
        //PDF file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/ResumeApp/" + pdfFileName + ".pdf";

        try {
            //create object of iText Document class
            Document document = new Document();
            //create instance of PdfWriter class
            PdfWriter.getInstance(document,new FileOutputStream(mFilePath));
            //open the document for writing
            document.open();

            document.setPageSize(PageSize.A4);
            //get text from edit text
            String name = ResumeProfilePart1.getName();
            String email = ResumeProfilePart1.getEmail();
            String contact_number = ResumeProfilePart1.getContact_number();
            String address = ResumeProfilePart1.getPresent_address();
            //add author of the document (optional)
            document.addAuthor("Mah Dian Drovo");

            //Font Settings
            //BaseColor colorAccent = new BaseColor(0,153,204,255);
            BaseColor colorAccent = new BaseColor(0,0,0);
            BaseColor topicColor = new BaseColor(192,192,192);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //Custom font
            BaseFont robotofont = BaseFont.createFont("assets/fonts/Roboto-Thin.ttf", "UTF-8", BaseFont.EMBEDDED);
            BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);
            BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf","UTF-8",BaseFont.EMBEDDED);
            //Create Title of the document
            Font nameFont = new Font(fontName,16.0f,Font.NORMAL,BaseColor.BLUE);
            Font normalFont = new Font(robotolight,8.0f,Font.NORMAL,BaseColor.BLACK);
            Font topicFont = new Font(robotolight,10.0f,Font.NORMAL,BaseColor.BLACK);
            Font topicFont2 = new Font(robotolight,9.0f,Font.NORMAL,BaseColor.BLACK);

            //name(document,nameFont);
            //personalInformation(document,normalFont);
            personalInformations(document,nameFont,normalFont);
            careerObjective(document,topicFont,normalFont,topicColor);
            educationQualification(document,topicFont,topicFont2,normalFont,topicColor);
            skills(document,topicFont,normalFont,topicColor);
            languageProficiency(document,topicFont,topicFont2,normalFont,topicColor);
            personalInformation(document,topicFont,topicFont2,normalFont,topicColor);
            reference(document,topicFont,normalFont,topicColor);
            workExperience(document,topicFont,normalFont,topicColor);


            //close the document
            document.close();
            //show message that file is saved
            Log.d("Filepath", pdfFileName+".pdf IS SAVED TO "+mFilePath);
            Log.e("Filepath", pdfFileName+".pdf IS SAVED TO "+mFilePath);
            //Toast.makeText(getApplicationContext(),pdfFileName+".pdf IS SAVED TO "+mFilePath,Toast.LENGTH_LONG).show();
        }

        catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        }

    }

    private void addLineSeparator(Document document) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        document.add(new Chunk(lineSeparator));

    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(" "));
    }

    public void addNewItem(Document document,String text,int alignment, Font font) throws DocumentException {
        Chunk chunk = new Chunk(text,font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }
    public void  name(Document document,Font nameFont) throws DocumentException {
        addNewItem(document,ResumeProfilePart1.getName(),Element.ALIGN_LEFT,nameFont);
    }
    private void personalInformations(Document document,Font nameFont,Font normalFont) throws IOException, DocumentException {

        //String path = "/storage/emulated/0/Pictures/Messenger/received_508364883426271.jpeg";
        String path = ResumeProfilePart1.getImagePath();

        Image image = Image.getInstance(path);


        //image.setAbsolutePosition(450f,10f);
        //image.setAbsolutePosition(12,300);
        //image.setWidthPercentage(120);
        /*
        float abs_width = image.getAbsoluteX();
        float abs_height = image.getAbsoluteY();
        float plain_height = image.getPlainHeight();
        float plain_width = image.getPlainWidth();

        Log.d("SIZE_","ABS: Width: "+abs_width+" Height: "+abs_height);
        Log.d("SIZE_","ABS: Width: "+plain_width+" Height: "+plain_height);
        */
        image.scaleAbsoluteHeight(55);
        image.scaleAbsoluteWidth(45);
        //image.setAbsolutePosition(0,0);
        BaseColor headColor = new BaseColor(0, 138, 211);


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Chunk chunk1 = new Chunk(ResumeProfilePart1.getName(),nameFont);
        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
        pdfPCell1.setBorderColor(BaseColor.WHITE);

        PdfPCell pdfPCell2 = new PdfPCell(image);
        pdfPCell2.setRowspan(4);
        pdfPCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pdfPCell2.setBorderColor(BaseColor.WHITE);

        Chunk chunk3 = new Chunk("Contact: "+ResumeProfilePart1.getContact_number(),normalFont);
        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(chunk3));
        pdfPCell3.setBorderColor(BaseColor.WHITE);

        Chunk chunk4 = new Chunk("Email: "+ResumeProfilePart1.getEmail(),normalFont);
        PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(chunk4));
        pdfPCell4.setBorderColor(BaseColor.WHITE);

        Chunk chunk5 = new Chunk("Address: "+ResumeProfilePart1.getPresent_address(),normalFont);
        PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(chunk5));
        pdfPCell5.setBorderColor(BaseColor.WHITE);

        table.addCell(pdfPCell1);
        table.addCell(pdfPCell2);
        table.addCell(pdfPCell3);
        table.addCell(pdfPCell4);
        table.addCell(pdfPCell5);

        document.add(table);

    }
    public void careerObjective(Document document,Font topicFont,Font normalFont, BaseColor topicColor) throws DocumentException {
        addLineSpace(document);
        PdfPTable table = new PdfPTable(1);
        Chunk chunk = new Chunk("Career Objective",topicFont);
        PdfPCell cell = new PdfPCell(new Paragraph(chunk));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBackgroundColor(topicColor);
        cell.setBorderColor(BaseColor.WHITE);
        table.setWidthPercentage(100.0f);
        table.addCell(cell);
        document.add(table);
        addNewItem(document, ResumeProfilePart2.career_objective,Element.ALIGN_LEFT,normalFont);
    }
    public void educationQualification(Document document,Font topicFont,Font topicfont2,Font normalFont,BaseColor titleColor) throws DocumentException {

        addLineSpace(document);
        PdfPTable tableTitle = new PdfPTable(1);
        Chunk chunk = new Chunk("Educational Qualification",topicFont);
        PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
        cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
        cellTopic.setBackgroundColor(titleColor);
        cellTopic.setBorderColor(BaseColor.WHITE);
        tableTitle.setWidthPercentage(100.0f);
        tableTitle.addCell(cellTopic);
        tableTitle.setSpacingAfter(5.0f);
        document.add(tableTitle);


        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100.0f);
        Chunk chunk1 = new Chunk("Exam Ttile",topicfont2);
        PdfPCell cellTitle1 = new PdfPCell(new Paragraph(chunk1));
        cellTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk2 = new Chunk("Concentration",topicfont2);
        PdfPCell cellTitle2 = new PdfPCell(new Paragraph(chunk2));
        cellTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk3 = new Chunk("Institute",topicfont2);
        PdfPCell cellTitle3 = new PdfPCell(new Paragraph(chunk3));
        cellTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk4 = new Chunk("Board",topicfont2);
        PdfPCell cellTitle4 = new PdfPCell(new Paragraph(chunk4));
        cellTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk5 = new Chunk("Result",topicfont2);
        PdfPCell cellTitle5 = new PdfPCell(new Paragraph(chunk5));
        cellTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk6 = new Chunk("Passing Year",topicfont2);
        PdfPCell cellTitle6 = new PdfPCell(new Paragraph(chunk6));
        cellTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellTitle1);
        table.addCell(cellTitle2);
        table.addCell(cellTitle3);
        table.addCell(cellTitle4);
        table.addCell(cellTitle5);
        table.addCell(cellTitle6);


        EducationQualification_Model educationQualification_model;
        PdfPCell[] pdfPCellQualification = new PdfPCell[ResumeProfilePart2.educationQualification.size()];
        PdfPCell[] pdfPCellGroupSubject = new PdfPCell[ResumeProfilePart2.educationQualification.size()];
        PdfPCell[] pdfPCellInstitute = new PdfPCell[ResumeProfilePart2.educationQualification.size()];
        PdfPCell[] pdfPCellBoard = new PdfPCell[ResumeProfilePart2.educationQualification.size()];
        PdfPCell[] pdfPCellResult = new PdfPCell[ResumeProfilePart2.educationQualification.size()];
        PdfPCell[] pdfPCellPassingYear = new PdfPCell[ResumeProfilePart2.educationQualification.size()];

        for (int i=0;i<ResumeProfilePart2.educationQualification.size();i++){
            educationQualification_model = ResumeProfilePart2.educationQualification.get(i);
            Chunk chunk10 = new Chunk(educationQualification_model.getQualification_name(),normalFont);
            pdfPCellQualification[i] = new PdfPCell(new Paragraph(chunk10));
            pdfPCellQualification[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            Chunk chunk11 = new Chunk(educationQualification_model.getGroupsubject_name(),normalFont);
            pdfPCellGroupSubject[i] = new PdfPCell(new Paragraph(chunk11));
            pdfPCellGroupSubject[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            Chunk chunk12 = new Chunk(educationQualification_model.getInstitute_name(),normalFont);
            pdfPCellInstitute[i] = new PdfPCell(new Paragraph(chunk12));
            pdfPCellInstitute[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            Chunk chunk13 = new Chunk(educationQualification_model.getBoard_name(),normalFont);
            pdfPCellBoard[i] = new PdfPCell(new Paragraph(chunk13));
            pdfPCellBoard[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            Chunk chunk14 = new Chunk(educationQualification_model.getResult(),normalFont);
            pdfPCellResult[i] = new PdfPCell(new Paragraph(chunk14));
            pdfPCellResult[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            Chunk chunk15 = new Chunk(educationQualification_model.getPassing_year(),normalFont);
            pdfPCellPassingYear[i] = new PdfPCell(new Paragraph(chunk15));
            pdfPCellPassingYear[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(pdfPCellQualification[i]);
            table.addCell(pdfPCellGroupSubject[i]);
            table.addCell(pdfPCellInstitute[i]);
            table.addCell(pdfPCellBoard[i]);
            table.addCell(pdfPCellResult[i]);
            table.addCell(pdfPCellPassingYear[i]);


        }
        document.add(table);

    }
    public void skills(Document document,Font topicFont,Font normalFont, BaseColor topicColor) throws DocumentException {
        addLineSpace(document);
        PdfPTable tableTitle = new PdfPTable(1);
        Chunk chunk = new Chunk("Special Qualities",topicFont);
        PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
        cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
        cellTopic.setBackgroundColor(topicColor);
        cellTopic.setBorderColor(BaseColor.WHITE);
        tableTitle.setWidthPercentage(100.0f);
        tableTitle.addCell(cellTopic);
        document.add(tableTitle);

        String specialQualities;
        Skills_Model skills_model;
        skills_model = ResumeProfilePart3.skills.get(0);
        specialQualities = skills_model.getSkill().toString();
        if (ResumeProfilePart3.skills.size()>1){
            for (int i=1;i<ResumeProfilePart3.skills.size();i++){
                skills_model = ResumeProfilePart3.skills.get(i);
                specialQualities = specialQualities.concat(", "+skills_model.getSkill());
            }
        }

        addNewItem(document,specialQualities,Element.ALIGN_LEFT,normalFont);

    }
    public void languageProficiency(Document document,Font topicFont,Font topicfont2, Font normalFont, BaseColor topicColor) throws DocumentException {
        addLineSpace(document);
        PdfPTable tableTitle = new PdfPTable(1);
        Chunk chunk = new Chunk("Language Proficiency",topicFont);
        PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
        cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
        cellTopic.setBackgroundColor(topicColor);
        cellTopic.setBorderColor(BaseColor.WHITE);
        tableTitle.setWidthPercentage(100.0f);
        tableTitle.addCell(cellTopic);
        tableTitle.setSpacingAfter(5.0f);
        document.add(tableTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        Chunk chunk1 = new Chunk("Language Name",topicfont2);
        PdfPCell cellTitle1 = new PdfPCell(new Paragraph(chunk1));
        cellTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk2 = new Chunk("Efficiency",topicfont2);
        PdfPCell cellTitle2 = new PdfPCell(new Paragraph(chunk2));
        cellTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellTitle1);
        table.addCell(cellTitle2);

        Chunk chunk3 = new Chunk("Bangla",normalFont);
        PdfPCell cellBangla = new PdfPCell(new Paragraph(chunk3));
        cellBangla.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk4 = new Chunk(ResumeProfilePart3.bangla_skill_level,normalFont);
        PdfPCell cellBanglaEfficiency = new PdfPCell(new Paragraph(chunk4));
        cellBanglaEfficiency.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellBangla);
        table.addCell(cellBanglaEfficiency);

        Chunk chunk5 = new Chunk("English",normalFont);
        PdfPCell cellEnglish = new PdfPCell(new Paragraph(chunk5));
        cellEnglish.setHorizontalAlignment(Element.ALIGN_CENTER);
        Chunk chunk6 = new Chunk(ResumeProfilePart3.english_skill_level,normalFont);
        PdfPCell cellEnglishEfficiency = new PdfPCell(new Paragraph(chunk6));
        cellEnglishEfficiency.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellEnglish);
        table.addCell(cellEnglishEfficiency);

        document.add(table);

    }
    public void personalInformation(Document document,Font topicFont,Font topicfont2, Font normalFont, BaseColor topicColor) throws DocumentException {
        addLineSpace(document);
        PdfPTable tableTitle = new PdfPTable(1);
        Chunk chunk = new Chunk("Personal Details",topicFont);
        PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
        cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
        cellTopic.setBackgroundColor(topicColor);
        cellTopic.setBorderColor(BaseColor.WHITE);
        tableTitle.setWidthPercentage(100.0f);
        tableTitle.setSpacingAfter(5.0f);
        tableTitle.addCell(cellTopic);
        document.add(tableTitle);

        PdfPTable _table = new PdfPTable(new float[]{15,85});
        _table.setWidthPercentage(100.0f);

        Chunk chunkName = new Chunk("Name",topicfont2);
        PdfPCell cell1 = new PdfPCell(new Paragraph(chunkName));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorderColor(BaseColor.WHITE);
        Chunk _chunkName = new Chunk(": "+ ResumeProfilePart4.getName(),normalFont);
        PdfPCell cell2 = new PdfPCell(new Paragraph(_chunkName));
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell1);
        _table.addCell(cell2);

        Chunk chunkFatherName = new Chunk("Father's Name",topicfont2);
        PdfPCell cell3 = new PdfPCell(new Paragraph(chunkFatherName));
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setBorderColor(BaseColor.WHITE);
        Chunk _chunkFatherName = new Chunk(": "+ResumeProfilePart4.getFather_name(),normalFont);
        PdfPCell cell4 = new PdfPCell(new Paragraph(_chunkFatherName));
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell3);
        _table.addCell(cell4);

        Chunk chunkMotherName = new Chunk("Mother's Name",topicfont2);
        PdfPCell cell5 = new PdfPCell(new Paragraph(chunkMotherName));
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setBorderColor(BaseColor.WHITE);
        Chunk _chunkMotherName = new Chunk(": "+ResumeProfilePart4.getMother_name(),normalFont);
        PdfPCell cell6 = new PdfPCell(new Paragraph(_chunkMotherName));
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell5);
        _table.addCell(cell6);

        Chunk chunkGender = new Chunk("Gender",topicfont2);
        PdfPCell cell7 = new PdfPCell(new Paragraph(chunkGender));
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell7.setBorderColor(BaseColor.WHITE);
        Chunk _chunkGender = new Chunk(": "+ResumeProfilePart4.getGender(),normalFont);
        PdfPCell cell8 = new PdfPCell(new Paragraph(_chunkGender));
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell8.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell7);
        _table.addCell(cell8);

        Chunk chunkBirthDate = new Chunk("Birth Date",topicfont2);
        PdfPCell cell9 = new PdfPCell(new Paragraph(chunkBirthDate));
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell9.setBorderColor(BaseColor.WHITE);
        Chunk _chunkBirthDate = new Chunk(": "+ResumeProfilePart4.getBirth_date(),normalFont);
        PdfPCell cell10 = new PdfPCell(new Paragraph(_chunkBirthDate));
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell10.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell9);
        _table.addCell(cell10);

        Chunk chunkMaritalStatus = new Chunk("Marital Status",topicfont2);
        PdfPCell cell11 = new PdfPCell(new Paragraph(chunkMaritalStatus));
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell11.setBorderColor(BaseColor.WHITE);
        Chunk _chunkMaritalStatus = new Chunk(": "+ResumeProfilePart4.getMarital_status(),normalFont);
        PdfPCell cell12 = new PdfPCell(new Paragraph(_chunkMaritalStatus));
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell12.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell11);
        _table.addCell(cell12);

        Chunk chunkNationality = new Chunk("Nationality",topicfont2);
        PdfPCell cell13 = new PdfPCell(new Paragraph(chunkNationality));
        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell13.setBorderColor(BaseColor.WHITE);
        Chunk _chunkNationality = new Chunk(": "+ResumeProfilePart4.getNationality(),normalFont);
        PdfPCell cell14 = new PdfPCell(new Paragraph(_chunkNationality));
        cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell14.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell13);
        _table.addCell(cell14);

        Chunk chunkReligion = new Chunk("Religion",topicfont2);
        PdfPCell cell15 = new PdfPCell(new Paragraph(chunkReligion));
        cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell15.setBorderColor(BaseColor.WHITE);
        Chunk _chunkReligion = new Chunk(": "+ResumeProfilePart4.getReligion(),normalFont);
        PdfPCell cell16 = new PdfPCell(new Paragraph(_chunkReligion));
        cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell16.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell15);
        _table.addCell(cell16);


        Chunk chunkPresentAddress = new Chunk("Present Address",topicfont2);
        PdfPCell cell17 = new PdfPCell(new Paragraph(chunkPresentAddress));
        cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell17.setBorderColor(BaseColor.WHITE);
        Chunk _chunkPresentAddress = new Chunk(": "+ResumeProfilePart4.getPresent_address(),normalFont);
        PdfPCell cell18 = new PdfPCell(new Paragraph(_chunkPresentAddress));
        cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell18.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell17);
        _table.addCell(cell18);

        Chunk chunkPermanentAddress = new Chunk("Permanent Address",topicfont2);
        PdfPCell cell19 = new PdfPCell(new Paragraph(chunkPermanentAddress));
        cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell19.setBorderColor(BaseColor.WHITE);
        Chunk _chunkPermanentAddress = new Chunk(": "+ResumeProfilePart4.getPermanent_address(),normalFont);
        PdfPCell cell20 = new PdfPCell(new Paragraph(_chunkPermanentAddress));
        cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell20.setBorderColor(BaseColor.WHITE);
        _table.addCell(cell19);
        _table.addCell(cell20);

        document.add(_table);
    }
    public void reference(Document document,Font topicFont,Font normalFont,BaseColor topicColor) throws DocumentException {
        if (ResumeProfilePart5.reference.size()>0){
            addLineSpace(document);
            PdfPTable tableTitle = new PdfPTable(1);
            Chunk chunk = new Chunk("References",topicFont);
            PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
            cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
            cellTopic.setBackgroundColor(topicColor);
            cellTopic.setBorderColor(BaseColor.WHITE);
            tableTitle.setWidthPercentage(100.0f);
            tableTitle.addCell(cellTopic);
            tableTitle.setSpacingAfter(5.0f);
            document.add(tableTitle);

            PdfPTable table;

            if (ResumeProfilePart5.reference.size() == 1){
                table = new PdfPTable(1);
                table.setWidthPercentage(100.0f);
                Reference_Model reference_model;
                reference_model = ResumeProfilePart5.reference.get(0);
                Chunk chunk1 = new Chunk(reference_model.getName(),normalFont);
                PdfPCell pdfPCellName = new PdfPCell(new Paragraph(chunk1));
                pdfPCellName.setBorderColor(BaseColor.WHITE);
                pdfPCellName.setHorizontalAlignment(Element.ALIGN_LEFT);
                Chunk chunk2 = new Chunk(reference_model.getDesignation(),normalFont);
                PdfPCell pdfPCellDesignation = new PdfPCell(new Paragraph(chunk2));
                pdfPCellDesignation.setBorderColor(BaseColor.WHITE);
                pdfPCellDesignation.setHorizontalAlignment(Element.ALIGN_LEFT);
                Chunk chunk3 = new Chunk(reference_model.getOrganization_name(),normalFont);
                PdfPCell pdfPCellOrganization = new PdfPCell(new Paragraph(chunk3));
                pdfPCellOrganization.setBorderColor(BaseColor.WHITE);
                pdfPCellOrganization.setHorizontalAlignment(Element.ALIGN_LEFT);
                Chunk chunk4 = new Chunk("Email: "+reference_model.getEmail(),normalFont);
                PdfPCell pdfPCellEmail = new PdfPCell(new Paragraph(chunk4));
                pdfPCellEmail.setBorderColor(BaseColor.WHITE);
                pdfPCellEmail.setHorizontalAlignment(Element.ALIGN_LEFT);
                Chunk chunk5 = new Chunk("Contact: "+reference_model.getMobile_number(),normalFont);
                PdfPCell pdfPCellContact = new PdfPCell(new Paragraph(chunk5));
                pdfPCellContact.setBorderColor(BaseColor.WHITE);
                pdfPCellContact.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(pdfPCellName);
                table.addCell(pdfPCellDesignation);
                table.addCell(pdfPCellOrganization);
                table.addCell(pdfPCellEmail);
                table.addCell(pdfPCellContact);
                document.add(table);
            }
            else if (ResumeProfilePart5.reference.size() == 2){
                table = new PdfPTable(new float[]{30,70});
                table.setWidthPercentage(100.0f);
                Reference_Model reference_model;

                reference_model = ResumeProfilePart5.reference.get(0);
                Chunk chunkName1 = new Chunk(reference_model.getName(),topicFont);
                PdfPCell pdfPCellName1 = new PdfPCell(new Paragraph(chunkName1));
                pdfPCellName1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellName1.setBorderColor(BaseColor.WHITE);

                Chunk chunkOrganization1 = new Chunk(reference_model.getOrganization_name(),normalFont);
                PdfPCell pdfPCellOrganization1 = new PdfPCell(new Paragraph(chunkOrganization1));
                pdfPCellOrganization1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellOrganization1.setBorderColor(BaseColor.WHITE);

                Chunk chunkDesignation1 = new Chunk(reference_model.getDesignation(),topicFont);
                PdfPCell pdfPCellDesignation1 = new PdfPCell(new Paragraph(chunkDesignation1));
                pdfPCellDesignation1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellDesignation1.setBorderColor(BaseColor.WHITE);

                Chunk chunkEmail1 = new Chunk("Email: "+reference_model.getEmail(),topicFont);
                PdfPCell pdfPCellEmail1 = new PdfPCell(new Paragraph(chunkEmail1));
                pdfPCellEmail1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellEmail1.setBorderColor(BaseColor.WHITE);

                Chunk chunkContact1 = new Chunk("Contact: "+reference_model.getMobile_number(),topicFont);
                PdfPCell pdfPCellContact1 = new PdfPCell(new Paragraph(chunkContact1));
                pdfPCellContact1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellContact1.setBorderColor(BaseColor.WHITE);

                reference_model = ResumeProfilePart5.reference.get(1);
                Chunk chunkName2 = new Chunk(reference_model.getName(),topicFont);
                PdfPCell pdfPCellName2 = new PdfPCell(new Paragraph(chunkName2));
                pdfPCellName2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellName2.setBorderColor(BaseColor.WHITE);

                Chunk chunkOrganization2 = new Chunk(reference_model.getOrganization_name(),normalFont);
                PdfPCell pdfPCellOrganization2 = new PdfPCell(new Paragraph(chunkOrganization2));
                pdfPCellOrganization2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellOrganization2.setBorderColor(BaseColor.WHITE);

                Chunk chunkDesignation2 = new Chunk(reference_model.getDesignation(),topicFont);
                PdfPCell pdfPCellDesignation2 = new PdfPCell(new Paragraph(chunkDesignation2));
                pdfPCellDesignation2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellDesignation2.setBorderColor(BaseColor.WHITE);

                Chunk chunkEmail2 = new Chunk("Email: "+reference_model.getEmail(),topicFont);
                PdfPCell pdfPCellEmail2 = new PdfPCell(new Paragraph(chunkEmail2));
                pdfPCellEmail2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellEmail2.setBorderColor(BaseColor.WHITE);

                Chunk chunkContact2 = new Chunk("Contact: "+reference_model.getMobile_number(),topicFont);
                PdfPCell pdfPCellContact2 = new PdfPCell(new Paragraph(chunkContact2));
                pdfPCellContact2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCellContact2.setBorderColor(BaseColor.WHITE);

                table.addCell(pdfPCellName1);
                table.addCell(pdfPCellName2);
                table.addCell(pdfPCellOrganization1);
                table.addCell(pdfPCellOrganization2);
                table.addCell(pdfPCellDesignation1);
                table.addCell(pdfPCellDesignation2);
                table.addCell(pdfPCellEmail1);
                table.addCell(pdfPCellEmail2);
                table.addCell(pdfPCellContact1);
                table.addCell(pdfPCellContact2);

                document.add(table);
            }

        }

    }
    public void workExperience(Document document,Font topicFont,Font normalFont,BaseColor topicColor) throws DocumentException {

        if (ResumeProfilePart6.workExperience.size()>0){
            addLineSpace(document);
            PdfPTable tableTitle = new PdfPTable(1);
            Chunk chunk = new Chunk("Work Experience",topicFont);
            PdfPCell cellTopic = new PdfPCell(new Paragraph(chunk));
            cellTopic.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTopic.setVerticalAlignment(Element.ALIGN_TOP);
            cellTopic.setBackgroundColor(topicColor);
            cellTopic.setBorderColor(BaseColor.WHITE);
            tableTitle.setWidthPercentage(100.0f);
            tableTitle.addCell(cellTopic);
            tableTitle.setSpacingAfter(5.0f);
            document.add(tableTitle);

            PdfPTable table;
            if (ResumeProfilePart6.workExperience.size() == 1){
                table = new PdfPTable(1);
                table.setWidthPercentage(100.0f);

                WorkExperience_Model workExperience_model;
                workExperience_model = ResumeProfilePart6.workExperience.get(0);
                Chunk chunk1 = new Chunk(workExperience_model.getDesignationName()+" ["+workExperience_model.getDurationTime()+"]",topicFont);
                PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
                pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell1.setBorderColor(BaseColor.WHITE);
                Chunk chunk2 = new Chunk(workExperience_model.getOrganizationName(),topicFont);
                PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(chunk2));
                pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell2.setBorderColor(BaseColor.WHITE);
                Chunk chunk3 = new Chunk(workExperience_model.getOgganizationAddress(),topicFont);
                PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(chunk3));
                pdfPCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell3.setBorderColor(BaseColor.WHITE);

                table.addCell(pdfPCell1);
                table.addCell(pdfPCell2);
                table.addCell(pdfPCell3);

                document.add(table);
            }
            if (ResumeProfilePart6.workExperience.size() == 2){
                table = new PdfPTable(new float[]{30,70});
                table.setWidthPercentage(100.0f);

                WorkExperience_Model workExperience_model;
                workExperience_model = ResumeProfilePart6.workExperience.get(0);
                Chunk chunk1 = new Chunk(workExperience_model.getDesignationName()+" ["+workExperience_model.getDurationTime()+"]",topicFont);
                PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
                pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell1.setBorderColor(BaseColor.WHITE);
                Chunk chunk2 = new Chunk(workExperience_model.getOrganizationName(),topicFont);
                PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(chunk2));
                pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell2.setBorderColor(BaseColor.WHITE);
                Chunk chunk3 = new Chunk(workExperience_model.getOgganizationAddress(),topicFont);
                PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(chunk3));
                pdfPCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell3.setBorderColor(BaseColor.WHITE);



                workExperience_model = ResumeProfilePart6.workExperience.get(1);
                Chunk chunk4 = new Chunk(workExperience_model.getDesignationName()+" ["+workExperience_model.getDurationTime()+"]",topicFont);
                PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(chunk4));
                pdfPCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell4.setBorderColor(BaseColor.WHITE);
                Chunk chunk5 = new Chunk(workExperience_model.getOrganizationName(),topicFont);
                PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(chunk5));
                pdfPCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell5.setBorderColor(BaseColor.WHITE);
                Chunk chunk6 = new Chunk(workExperience_model.getOgganizationAddress(),topicFont);
                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(chunk6));
                pdfPCell6.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell6.setBorderColor(BaseColor.WHITE);

                table.addCell(pdfPCell1);
                table.addCell(pdfPCell4);
                table.addCell(pdfPCell2);
                table.addCell(pdfPCell5);
                table.addCell(pdfPCell3);
                table.addCell(pdfPCell6);

                document.add(table);
            }

        }





    }
    public void Permission(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){

            if(checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,STOREGE_CODE);
            }
            else {
                //permission already granted...write pdf method
                BuildResumePDF();

            }
        }
        else {
            Toast.makeText(getApplicationContext(),"abc",Toast.LENGTH_LONG).show();
            BuildResumePDF();

        }
    }

    @Override
    public void onClick(View view) {
        if (view == button_SendMail){
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename",pdfFileName+".pdf");
            startActivity(intent);
        }
    }

    private void init(){
        pdfView= (PDFView)findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position",-1);
        displayFromSdcard();
    }

    private void displayFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory()+"/ResumeApp/"+pdfFileName+".pdf");
        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
