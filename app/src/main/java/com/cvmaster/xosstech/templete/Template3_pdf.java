package com.cvmaster.xosstech.templete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SendMail;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.UserProfileActivity;
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
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Template3_pdf extends AppCompatActivity implements View.OnClickListener , OnPageChangeListener, OnLoadCompleteListener {

    String pdfFileName="";

    PDFView pdfView;
    String TAG="PDF";
    Integer pageNumber = 0;
    int position=-1;
    private Button button_SendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template3_pdf);

        button_SendMail = (Button) findViewById(R.id.button_SendMail);
        button_SendMail.setOnClickListener(this);

        BuildReumePDF();
        init();

    }

    private void BuildReumePDF(){
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

            //add author of the document (optional)
            document.addAuthor("Mah Dian Drovo");

            //Font Settings
            //BaseColor colorAccent = new BaseColor(0,153,204,255);
            BaseColor colorWhite = new BaseColor(255,255,255);
            BaseColor topicColor = new BaseColor(192,192,192);
            BaseColor headColor = new BaseColor(0,138,211);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //Custom font
            BaseFont robotRegular = BaseFont.createFont("assets/fonts/Roboto-Regular.ttf", "UTF-8", BaseFont.EMBEDDED);

            BaseFont robotofont = BaseFont.createFont("assets/fonts/Roboto-Thin.ttf", "UTF-8", BaseFont.EMBEDDED);
            BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);

            BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf","UTF-8",BaseFont.EMBEDDED);
            BaseFont fontNormal = BaseFont.createFont("assets/fonts/OpenSans-Regular.ttf","UTF-8",BaseFont.EMBEDDED);
            //Create Title of the document
            Font nameFont = new Font(robotRegular,16.5f,Font.NORMAL,BaseColor.BLACK);
            Font normalFont = new Font(robotolight,8.0f,Font.NORMAL,BaseColor.BLACK);
            Font topicFont = new Font(robotolight,9.0f,Font.NORMAL,BaseColor.BLACK);

            Font font1 = new Font(robotolight, 8.0f, Font.NORMAL, BaseColor.BLACK);
            Font font2 = new Font(fontName, 12.0f, Font.UNDERLINE, headColor);
            Font font3 = new Font(fontName, 11.0f, Font.NORMAL, headColor);
            Font font4 = new Font(fontName, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font font5 = new Font(robotolight, 9.5f, Font.NORMAL, BaseColor.BLACK);

            addLineSpace(document);
            addLineSpace(document);
            headInfo(document,nameFont,normalFont,headColor, colorWhite, font1, font2, font3, font4, font5);
            addLineSpace(document);
            educationInfo(document,font1,font2,font3,font4,font5);

            if (ResumeProfilePart6.workExperience.size() > 0){
                addLineSpace(document);
                workExperience(document);
            }

            addLineSpace(document);
            skillsAndLanguage(document);

            if (ResumeProfilePart5.reference.size() > 0){
                addLineSpace(document);
                reference(document);
            }

            //close the document
            document.close();


        } catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        }
    }

    private void headInfo(Document document,Font nameFont,Font normalFont, BaseColor headColor, BaseColor colorWhite, Font font1, Font font2, Font font3, Font font4, Font font5) throws IOException, DocumentException {
        String path = ResumeProfilePart1.getImagePath();

        Image image = Image.getInstance(path);

        image.scaleAbsoluteHeight(55);
        image.scaleAbsoluteWidth(45);

        PdfPTable table = new PdfPTable(new float[]{15, 35, 50});
        table.setWidthPercentage(100);

        PdfPCell pdfPCell2 = new PdfPCell(image);
        pdfPCell2.setRowspan(4);
        pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell2.setBorderColor(BaseColor.WHITE);

        Chunk chunk1 = new Chunk(ResumeProfilePart2.career_objective,normalFont);
        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
        pdfPCell1.setBorderColor(colorWhite);

        Chunk chunk3 = new Chunk(ResumeProfilePart1.getName(),nameFont);
        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(chunk3));
        pdfPCell3.setRowspan(4);
        pdfPCell3.setBorderColor(colorWhite);

        Chunk chunk4 = new Chunk("Contact: "+ResumeProfilePart1.getContact_number(),normalFont);
        PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(chunk4));
        pdfPCell4.setBorderColor(colorWhite);

        Chunk chunk5 = new Chunk("Email: "+ResumeProfilePart1.getEmail(),normalFont);
        PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(chunk5));
        pdfPCell5.setBorderColor(colorWhite);

        Chunk chunk6 = new Chunk("Present Address: "+ResumeProfilePart1.getPresent_address(),normalFont);
        PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(chunk6));
        pdfPCell6.setBorderColor(colorWhite);

        table.addCell(pdfPCell2);
        table.addCell(pdfPCell3);
        table.addCell(pdfPCell1);
        table.addCell(pdfPCell4);
        table.addCell(pdfPCell5);
        table.addCell(pdfPCell6);

        document.add(table);

    }

    private void educationInfo(Document document,Font font1, Font font2, Font font3, Font font4,Font font5) throws IOException, DocumentException{
        //Custom font
        BaseFont robotRegular = BaseFont.createFont("assets/fonts/Roboto-Regular.ttf", "UTF-8", BaseFont.EMBEDDED);


        //Create Title of the document
        Font fontEduHead = new Font(robotRegular,12.f,Font.NORMAL,BaseColor.BLACK);

        BaseColor headColor = new BaseColor(236,236,236);

        PdfPTable educationHead = new PdfPTable(1);
        educationHead.setWidthPercentage(100);

        Chunk chunk1 = new Chunk("EDUCATION",fontEduHead);
        PdfPCell pdfPCellEduHead = new PdfPCell(new Paragraph(chunk1));
        pdfPCellEduHead.setBorderColor(BaseColor.WHITE);
        pdfPCellEduHead.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCellEduHead.setBackgroundColor(headColor);

        educationHead.addCell(pdfPCellEduHead);

        document.add(educationHead);

        //PdfPTable table = new PdfPTable(new float[]{50,50});
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);



        if (ResumeProfilePart2.educationQualification.size() > 0){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(0);
            Chunk chunkEduQualificationName1 = new Chunk(educationQualification_model.getQualification_name(), font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(educationQualification_model.getPassing_year(), font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" "+educationQualification_model.getInstitute_name(), font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setUseVariableBorders(true);
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            cellEduInstituteName1.setBorderColorTop(BaseColor.BLACK);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" Subject: "+educationQualification_model.getGroupsubject_name(), font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" Result: "+educationQualification_model.getResult(), font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);


        }

        if (ResumeProfilePart2.educationQualification.size() == 1){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(0);
            Chunk chunkEduQualificationName1 = new Chunk(" ", font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(" ", font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" ", font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" ", font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" ", font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() > 1){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(1);
            Chunk chunkEduQualificationName1 = new Chunk(educationQualification_model.getQualification_name(), font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(educationQualification_model.getPassing_year(), font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" "+educationQualification_model.getInstitute_name(), font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setUseVariableBorders(true);
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            cellEduInstituteName1.setBorderColorTop(BaseColor.BLACK);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" Subject: "+educationQualification_model.getGroupsubject_name(), font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" Result: "+educationQualification_model.getResult(), font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() > 2){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(2);
            Chunk chunkEduQualificationName1 = new Chunk(educationQualification_model.getQualification_name(), font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(educationQualification_model.getPassing_year(), font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" "+educationQualification_model.getInstitute_name(), font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setUseVariableBorders(true);
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            cellEduInstituteName1.setBorderColorTop(BaseColor.BLACK);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" Subject: "+educationQualification_model.getGroupsubject_name(), font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" Result: "+educationQualification_model.getResult(), font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() == 3){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(0);
            Chunk chunkEduQualificationName1 = new Chunk(" ", font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(" ", font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" ", font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" ", font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" ", font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() > 3){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(3);
            Chunk chunkEduQualificationName1 = new Chunk(educationQualification_model.getQualification_name(), font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(educationQualification_model.getPassing_year(), font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" "+educationQualification_model.getInstitute_name(), font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setUseVariableBorders(true);
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            cellEduInstituteName1.setBorderColorTop(BaseColor.BLACK);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" Subject: "+educationQualification_model.getGroupsubject_name(), font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" Result: "+educationQualification_model.getResult(), font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() > 4){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(4);
            Chunk chunkEduQualificationName1 = new Chunk(educationQualification_model.getQualification_name(), font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(educationQualification_model.getPassing_year(), font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" "+educationQualification_model.getInstitute_name(), font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setUseVariableBorders(true);
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            cellEduInstituteName1.setBorderColorTop(BaseColor.BLACK);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" Subject: "+educationQualification_model.getGroupsubject_name(), font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" Result: "+educationQualification_model.getResult(), font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        if (ResumeProfilePart2.educationQualification.size() == 5){
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(90);

            PdfPTable tableHeadEdu1 = new PdfPTable(2);
            tableHeadEdu1.setWidthPercentage(100);

            EducationQualification_Model educationQualification_model = ResumeProfilePart2.educationQualification.get(0);
            Chunk chunkEduQualificationName1 = new Chunk(" ", font5);
            PdfPCell cellEduQualificationName1 = new PdfPCell(new Paragraph(chunkEduQualificationName1));
            cellEduQualificationName1.setBorderColor(BaseColor.WHITE);


            Chunk chunkEduPassingYear1 = new Chunk(" ", font5);
            PdfPCell cellEduPassingYear1 = new PdfPCell(new Paragraph(chunkEduPassingYear1));
            cellEduPassingYear1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEduPassingYear1.setBorderColor(BaseColor.WHITE);

            tableHeadEdu1.addCell(cellEduQualificationName1);
            tableHeadEdu1.addCell(cellEduPassingYear1);

            PdfPCell pdfPCellEduHead1 = new PdfPCell();
            pdfPCellEduHead1.setBorderColor(BaseColor.WHITE);
            pdfPCellEduHead1.addElement(tableHeadEdu1);

            table1.addCell(pdfPCellEduHead1);

            Chunk chunkEduInstituteName1 = new Chunk(" ", font1);
            PdfPCell cellEduInstituteName1 = new PdfPCell(new Paragraph(chunkEduInstituteName1));
            cellEduInstituteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduInstituteName1);

            Chunk chunkSubjectName1 = new Chunk(" ", font1);
            PdfPCell cellEduSubjecteName1 = new PdfPCell(new Paragraph(chunkSubjectName1));
            cellEduSubjecteName1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellEduSubjecteName1);

            Chunk chunkResult1 = new Chunk(" ", font1);
            PdfPCell cellResult1 = new PdfPCell(new Paragraph(chunkResult1));
            cellResult1.setBorderColor(BaseColor.WHITE);
            table1.addCell(cellResult1);


            PdfPCell pdfPCellTable1 = new PdfPCell();
            pdfPCellTable1.setBorderColor(BaseColor.WHITE);
            pdfPCellTable1.addElement(table1);

            table1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(pdfPCellTable1);
        }

        document.add(table);
    }

    private void workExperience(Document document) throws IOException, DocumentException {
        BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);
        BaseFont robotRegular = BaseFont.createFont("assets/fonts/Roboto-Regular.ttf", "UTF-8", BaseFont.EMBEDDED);

        BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf","UTF-8",BaseFont.EMBEDDED);
        Font workExptitle = new Font(robotolight, 8.5f, Font.NORMAL, BaseColor.BLACK);
        Font workExpHead = new Font(robotolight, 10.5f, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        if (ResumeProfilePart6.workExperience.size() > 0){

            //Custom font
            //Create Title of the document
            Font fontWorkHead = new Font(robotRegular,12.f,Font.NORMAL,BaseColor.BLACK);

            BaseColor headColor = new BaseColor(236,236,236);

            PdfPTable workHeadTable = new PdfPTable(1);
            workHeadTable.setWidthPercentage(100);

            Chunk workHeadChunk = new Chunk("WORK EXPERIENCE", fontWorkHead);
            PdfPCell workHeadCell = new PdfPCell(new Paragraph(workHeadChunk));
            workHeadCell.setBorderColor(BaseColor.WHITE);
            workHeadCell.setBackgroundColor(headColor);
            workHeadTable.addCell(workHeadCell);

            document.add(workHeadTable);


            PdfPTable workTable1 = new PdfPTable(1);
            workTable1.setWidthPercentage(98);
            workTable1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPTable workHeadTable1 = new PdfPTable(2);
            workHeadTable1.setWidthPercentage(100);

            WorkExperience_Model workExperience_model = ResumeProfilePart6.workExperience.get(0);

            Chunk designationNameChunk1 = new Chunk(workExperience_model.getDesignationName(), workExpHead);
            PdfPCell designationNamePdfCell1 = new PdfPCell(new Paragraph(designationNameChunk1));
            designationNamePdfCell1.setBorderColor(BaseColor.WHITE);


            Chunk durationChunk1 = new Chunk(workExperience_model.getDurationTime(), workExpHead);
            PdfPCell durationCell1 = new PdfPCell(new Paragraph(durationChunk1));
            durationCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            durationCell1.setBorderColor(BaseColor.WHITE);

            Chunk organizationnameChunk1 = new Chunk(" "+workExperience_model.getOrganizationName(), workExptitle);
            PdfPCell organizationnameCell1 = new PdfPCell(new Paragraph(organizationnameChunk1));
            organizationnameCell1.setBorderColor(BaseColor.WHITE);
            organizationnameCell1.setUseVariableBorders(true);
            organizationnameCell1.setBorderColorTop(BaseColor.BLACK);

            Chunk organizationAddressChunk1 = new Chunk(" "+workExperience_model.getOgganizationAddress(), workExptitle);
            PdfPCell organizationAddressCell1 = new PdfPCell(new Paragraph(organizationAddressChunk1));
            organizationAddressCell1.setBorderColor(BaseColor.WHITE);

            workHeadTable1.addCell(designationNamePdfCell1);
            workHeadTable1.addCell(durationCell1);

            PdfPCell workHeadCell1 = new PdfPCell();
            workHeadCell1.setBorderColor(BaseColor.WHITE);
            workHeadCell1.addElement(workHeadTable1);

            workTable1.addCell(workHeadCell1);
            workTable1.addCell(organizationnameCell1);
            workTable1.addCell(organizationAddressCell1);

            PdfPCell workCell = new PdfPCell();
            workCell.setBorderColor(BaseColor.WHITE);
            workCell.addElement(workTable1);

            table.addCell(workCell);


        }

        if (ResumeProfilePart6.workExperience.size() == 1){



            PdfPTable workTable1 = new PdfPTable(1);
            workTable1.setWidthPercentage(98);
            workTable1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPTable workHeadTable1 = new PdfPTable(2);
            workHeadTable1.setWidthPercentage(100);

            WorkExperience_Model workExperience_model = ResumeProfilePart6.workExperience.get(0);

            Chunk designationNameChunk1 = new Chunk(" ", workExpHead);
            PdfPCell designationNamePdfCell1 = new PdfPCell(new Paragraph(designationNameChunk1));
            designationNamePdfCell1.setBorderColor(BaseColor.WHITE);


            Chunk durationChunk1 = new Chunk(" ", workExpHead);
            PdfPCell durationCell1 = new PdfPCell(new Paragraph(durationChunk1));
            durationCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            durationCell1.setBorderColor(BaseColor.WHITE);

            Chunk organizationnameChunk1 = new Chunk(" ", workExptitle);
            PdfPCell organizationnameCell1 = new PdfPCell(new Paragraph(organizationnameChunk1));
            organizationnameCell1.setBorderColor(BaseColor.WHITE);

            Chunk organizationAddressChunk1 = new Chunk(" ", workExptitle);
            PdfPCell organizationAddressCell1 = new PdfPCell(new Paragraph(organizationAddressChunk1));
            organizationAddressCell1.setBorderColor(BaseColor.WHITE);

            workHeadTable1.addCell(designationNamePdfCell1);
            workHeadTable1.addCell(durationCell1);

            PdfPCell workHeadCell1 = new PdfPCell();
            workHeadCell1.setBorderColor(BaseColor.WHITE);
            workHeadCell1.addElement(workHeadTable1);

            workTable1.addCell(workHeadCell1);
            workTable1.addCell(organizationnameCell1);
            workTable1.addCell(organizationAddressCell1);

            PdfPCell workCell = new PdfPCell();
            workCell.setBorderColor(BaseColor.WHITE);
            workCell.addElement(workTable1);

            table.addCell(workCell);

        }

        if (ResumeProfilePart6.workExperience.size() >1){

            PdfPTable workTable1 = new PdfPTable(1);
            workTable1.setWidthPercentage(98);
            workTable1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPTable workHeadTable1 = new PdfPTable(2);
            workHeadTable1.setWidthPercentage(100);

            WorkExperience_Model workExperience_model = ResumeProfilePart6.workExperience.get(1);

            Chunk designationNameChunk1 = new Chunk(workExperience_model.getDesignationName(), workExpHead);
            PdfPCell designationNamePdfCell1 = new PdfPCell(new Paragraph(designationNameChunk1));
            designationNamePdfCell1.setBorderColor(BaseColor.WHITE);


            Chunk durationChunk1 = new Chunk(workExperience_model.getDurationTime(), workExpHead);
            PdfPCell durationCell1 = new PdfPCell(new Paragraph(durationChunk1));
            durationCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            durationCell1.setBorderColor(BaseColor.WHITE);

            Chunk organizationnameChunk1 = new Chunk(" "+workExperience_model.getOrganizationName(), workExptitle);
            PdfPCell organizationnameCell1 = new PdfPCell(new Paragraph(organizationnameChunk1));
            organizationnameCell1.setBorderColor(BaseColor.WHITE);
            organizationnameCell1.setUseVariableBorders(true);
            organizationnameCell1.setBorderColorTop(BaseColor.BLACK);

            Chunk organizationAddressChunk1 = new Chunk(" "+workExperience_model.getOgganizationAddress(), workExptitle);
            PdfPCell organizationAddressCell1 = new PdfPCell(new Paragraph(organizationAddressChunk1));
            organizationAddressCell1.setBorderColor(BaseColor.WHITE);

            workHeadTable1.addCell(designationNamePdfCell1);
            workHeadTable1.addCell(durationCell1);

            PdfPCell workHeadCell1 = new PdfPCell();
            workHeadCell1.setBorderColor(BaseColor.WHITE);
            workHeadCell1.addElement(workHeadTable1);

            workTable1.addCell(workHeadCell1);
            workTable1.addCell(organizationnameCell1);
            workTable1.addCell(organizationAddressCell1);

            PdfPCell workCell = new PdfPCell();
            workCell.setBorderColor(BaseColor.WHITE);
            workCell.addElement(workTable1);

            table.addCell(workCell);
        }

        document.add(table);


    }

    private void skillsAndLanguage(Document document) throws IOException, DocumentException {
        BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);
        BaseFont robotRegular = BaseFont.createFont("assets/fonts/Roboto-Regular.ttf", "UTF-8", BaseFont.EMBEDDED);

        //Custom font
        //Create Title of the document
        Font fontSkillLang = new Font(robotRegular,12.f,Font.NORMAL,BaseColor.BLACK);

        BaseColor headColor = new BaseColor(236,236,236);

        Font skillsLanguageHead = new Font(robotolight, 8.5f, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPTable skillsHeadTable = new PdfPTable(1);
        skillsHeadTable.setWidthPercentage(100);

        PdfPTable languageHeadTable = new PdfPTable(1);
        languageHeadTable.setWidthPercentage(100);

        PdfPTable skillsTable = new PdfPTable(1);
        skillsTable.setWidthPercentage(98);
        skillsTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPTable languageTable = new PdfPTable(1);
        languageTable.setWidthPercentage(100);
        languageTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        Chunk skillHeadNameChunk = new Chunk("SKILLS", fontSkillLang);
        PdfPCell skillHeadNameCell = new PdfPCell(new Paragraph(skillHeadNameChunk));
        skillHeadNameCell.setBackgroundColor(headColor);
        skillHeadNameCell.setBorderColor(BaseColor.WHITE);

        Chunk languageHeadNameChunk = new Chunk("Language", fontSkillLang);
        PdfPCell languageHeadNameCell = new PdfPCell(new Paragraph(languageHeadNameChunk));
        languageHeadNameCell.setBackgroundColor(headColor);
        languageHeadNameCell.setBorderColor(BaseColor.WHITE);

        skillsHeadTable.addCell(skillHeadNameCell);
        languageHeadTable.addCell(languageHeadNameCell);

        PdfPCell skillHeadTableCell = new PdfPCell();
        skillHeadTableCell.setBorderColor(BaseColor.WHITE);
        skillHeadTableCell.addElement(skillsHeadTable);

        PdfPCell languageHeadTableCell = new PdfPCell();
        languageHeadTableCell.setBorderColor(BaseColor.WHITE);
        languageHeadTableCell.addElement(languageHeadTable);

        skillsTable.addCell(skillHeadTableCell);

        String specialQualities;
        Skills_Model skills_model;
        skills_model = ResumeProfilePart3.skills.get(0);
        specialQualities = skills_model.getSkill().toString();
        if (ResumeProfilePart3.skills.size()>1){
            for (int i=1;i<ResumeProfilePart3.skills.size();i++){
                skills_model = ResumeProfilePart3.skills.get(i);
                specialQualities = specialQualities.concat("\n"+skills_model.getSkill());
            }
        }

        Chunk skillchunk = new Chunk(specialQualities, skillsLanguageHead);
        PdfPCell skillcell = new PdfPCell(new Paragraph(skillchunk));
        skillcell.setBorderColor(BaseColor.WHITE);
        skillsTable.addCell(skillcell);


        languageTable.addCell(languageHeadTableCell);

        //Custom font
        //Create Title of the document
        Font fontLang = new Font(robotolight,10f,Font.ITALIC,BaseColor.BLACK);

        Chunk englishNameLanguageChunk = new Chunk("English", skillsLanguageHead);
        PdfPCell englishNameLanguageCell = new PdfPCell(new Paragraph(englishNameLanguageChunk));
        englishNameLanguageCell.setBorderColor(BaseColor.WHITE);

        Chunk englishSkillChunk = new Chunk(ResumeProfilePart3.english_skill_level, fontLang);
        PdfPCell englishSkillCell = new PdfPCell(new Paragraph(englishSkillChunk));
        englishSkillCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaNameLanguageChunk = new Chunk("Bangla", skillsLanguageHead);
        PdfPCell banglaNameLanguageCell = new PdfPCell(new Paragraph(banglaNameLanguageChunk));
        banglaNameLanguageCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaSkillChunk = new Chunk(ResumeProfilePart3.bangla_skill_level, fontLang);
        PdfPCell banglaSkillCell = new PdfPCell(new Paragraph(banglaSkillChunk));
        banglaSkillCell.setBorderColor(BaseColor.WHITE);

        languageTable.addCell(englishNameLanguageCell);
        languageTable.addCell(englishSkillCell);
        languageTable.addCell(banglaNameLanguageCell);
        languageTable.addCell(banglaSkillCell);

        PdfPCell skillsTableCell = new PdfPCell();
        skillsTableCell.setBorderColor(BaseColor.WHITE);
        skillsTableCell.addElement(skillsTable);

        PdfPCell languageTableCell = new PdfPCell();
        languageTableCell.setBorderColor(BaseColor.WHITE);
        languageTableCell.addElement(languageTable);

        table.addCell(skillsTableCell);
        table.addCell(languageTableCell);

        document.add(table);
    }

    private void reference(Document document) throws IOException, DocumentException {
        BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);

        Font refHead = new Font(robotolight, 8.5f, Font.NORMAL, BaseColor.BLACK);
        BaseFont robotRegular = BaseFont.createFont("assets/fonts/Roboto-Regular.ttf", "UTF-8", BaseFont.EMBEDDED);

        //Custom font
        //Create Title of the document
        Font fontRefHead = new Font(robotRegular,12.f,Font.NORMAL,BaseColor.BLACK);

        BaseColor headColor = new BaseColor(236,236,236);


        PdfPTable referenceHeadTable = new PdfPTable(1);
        referenceHeadTable.setWidthPercentage(100);

        Chunk refHeadChunk = new Chunk("REFERENCE", fontRefHead);
        PdfPCell refHeadCell = new PdfPCell(new Paragraph(refHeadChunk));
        refHeadCell.setBorderColor(BaseColor.WHITE);
        refHeadCell.setBackgroundColor(headColor);
        referenceHeadTable.addCell(refHeadCell);

        document.add(referenceHeadTable);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        if (ResumeProfilePart5.reference.size() > 0){
            PdfPTable referenceTabel = new PdfPTable(1);
            referenceTabel.setWidthPercentage(98);
            referenceTabel.setHorizontalAlignment(Element.ALIGN_LEFT);

            Reference_Model reference_model = ResumeProfilePart5.reference.get(0);

            Chunk nameChunk1 = new Chunk(reference_model.getName(), refHead);
            PdfPCell nameCell1 = new PdfPCell(new Paragraph(nameChunk1));
            nameCell1.setBorderColor(BaseColor.WHITE);

            Chunk designationChunk1 = new Chunk("Designation: "+reference_model.getDesignation(), refHead);
            PdfPCell designationCell1 = new PdfPCell(new Paragraph(designationChunk1));
            designationCell1.setUseVariableBorders(true);
            designationCell1.setBorderColor(BaseColor.WHITE);
            designationCell1.setBorderColorTop(BaseColor.BLACK);

            Chunk companyNameChunk1 = new Chunk("Organization: "+reference_model.getOrganization_name(), refHead);
            PdfPCell companyNameCell1 = new PdfPCell(new Paragraph(companyNameChunk1));
            companyNameCell1.setBorderColor(BaseColor.WHITE);

            Chunk emailChunk1 = new Chunk("Email: "+reference_model.getEmail(), refHead);
            PdfPCell emailCell1 = new PdfPCell(new Paragraph(emailChunk1));
            emailCell1.setBorderColor(BaseColor.WHITE);

            Chunk contactNumberChunk1 = new Chunk("Contact: "+reference_model.getMobile_number(), refHead);
            PdfPCell contactNumberCell1 = new PdfPCell(new Paragraph(contactNumberChunk1));
            contactNumberCell1.setBorderColor(BaseColor.WHITE);

            referenceTabel.addCell(nameCell1);
            referenceTabel.addCell(designationCell1);
            referenceTabel.addCell(companyNameCell1);
            referenceTabel.addCell(emailCell1);
            referenceTabel.addCell(contactNumberCell1);

            PdfPCell refCell1 = new PdfPCell();
            refCell1.setBorderColor(BaseColor.WHITE);
            refCell1.addElement(referenceTabel);

            table.addCell(refCell1);


        }

        if (ResumeProfilePart5.reference.size() == 1){

            PdfPTable referenceTabel = new PdfPTable(1);
            referenceTabel.setWidthPercentage(100);
            referenceTabel.setHorizontalAlignment(Element.ALIGN_LEFT);

            Reference_Model reference_model = ResumeProfilePart5.reference.get(0);

            Chunk nameChunk1 = new Chunk(" ", refHead);
            PdfPCell nameCell1 = new PdfPCell(new Paragraph(nameChunk1));
            nameCell1.setBorderColor(BaseColor.WHITE);

            Chunk designationChunk1 = new Chunk(" ", refHead);
            PdfPCell designationCell1 = new PdfPCell(new Paragraph(designationChunk1));
            designationCell1.setBorderColor(BaseColor.WHITE);

            Chunk companyNameChunk1 = new Chunk(" ", refHead);
            PdfPCell companyNameCell1 = new PdfPCell(new Paragraph(companyNameChunk1));
            companyNameCell1.setBorderColor(BaseColor.WHITE);

            Chunk emailChunk1 = new Chunk(" ", refHead);
            PdfPCell emailCell1 = new PdfPCell(new Paragraph(emailChunk1));
            emailCell1.setBorderColor(BaseColor.WHITE);

            Chunk contactNumberChunk1 = new Chunk(" ", refHead);
            PdfPCell contactNumberCell1 = new PdfPCell(new Paragraph(contactNumberChunk1));
            contactNumberCell1.setBorderColor(BaseColor.WHITE);

            referenceTabel.addCell(nameCell1);
            referenceTabel.addCell(designationCell1);
            referenceTabel.addCell(companyNameCell1);
            referenceTabel.addCell(emailCell1);
            referenceTabel.addCell(contactNumberCell1);

            PdfPCell refCell1 = new PdfPCell();
            refCell1.setBorderColor(BaseColor.WHITE);
            refCell1.addElement(referenceTabel);

            table.addCell(refCell1);

        }

        if (ResumeProfilePart5.reference.size() > 1){
            PdfPTable referenceTabel = new PdfPTable(1);
            referenceTabel.setWidthPercentage(100);
            referenceTabel.setHorizontalAlignment(Element.ALIGN_LEFT);

            Reference_Model reference_model = ResumeProfilePart5.reference.get(1);

            Chunk nameChunk1 = new Chunk(reference_model.getName(), refHead);
            PdfPCell nameCell1 = new PdfPCell(new Paragraph(nameChunk1));
            nameCell1.setBorderColor(BaseColor.WHITE);

            Chunk designationChunk1 = new Chunk("Designation: "+reference_model.getDesignation(), refHead);
            PdfPCell designationCell1 = new PdfPCell(new Paragraph(designationChunk1));
            designationCell1.setUseVariableBorders(true);
            designationCell1.setBorderColor(BaseColor.WHITE);
            designationCell1.setBorderColorTop(BaseColor.BLACK);

            Chunk companyNameChunk1 = new Chunk("Organization: "+reference_model.getOrganization_name(), refHead);
            PdfPCell companyNameCell1 = new PdfPCell(new Paragraph(companyNameChunk1));
            companyNameCell1.setBorderColor(BaseColor.WHITE);

            Chunk emailChunk1 = new Chunk("Email: "+reference_model.getEmail(), refHead);
            PdfPCell emailCell1 = new PdfPCell(new Paragraph(emailChunk1));
            emailCell1.setBorderColor(BaseColor.WHITE);

            Chunk contactNumberChunk1 = new Chunk("Contact: "+reference_model.getMobile_number(), refHead);
            PdfPCell contactNumberCell1 = new PdfPCell(new Paragraph(contactNumberChunk1));
            contactNumberCell1.setBorderColor(BaseColor.WHITE);

            referenceTabel.addCell(nameCell1);
            referenceTabel.addCell(designationCell1);
            referenceTabel.addCell(companyNameCell1);
            referenceTabel.addCell(emailCell1);
            referenceTabel.addCell(contactNumberCell1);

            PdfPCell refCell1 = new PdfPCell();
            refCell1.setBorderColor(BaseColor.WHITE);
            refCell1.addElement(referenceTabel);

            table.addCell(refCell1);

        }
        document.add(table);
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

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(" "));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);

    }

/*
    public void editDataInputted(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("resume_count").document("template3");

        FirebaseFirestore.getInstance()
                .collection("resume_count")
                .document("template3")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int cv_num = Integer.parseInt(documentSnapshot.getString("template3_cv_num"));
                        Log.d("HELLO", "Number: "+cv_num);
                        cv_num++;

                        Map<String, Object> data = new HashMap<>();
                        String numb = Integer.toString(cv_num);
                        data.put("template3_cv_num",numb);

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
*/
}
