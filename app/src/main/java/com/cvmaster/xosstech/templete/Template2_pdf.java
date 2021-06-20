package com.cvmaster.xosstech.templete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.multidex.BuildConfig;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.itextpdf.text.BaseColor.BLACK;

public class Template2_pdf extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener {


    /*String pdfFileName = "";
    PDFView pdfView;
    String TAG = "PDF";
    Integer pageNumber = 0;
    int position = -1;
    private Button button_SendMail;
    PdfPCell pdfimgemail, mobileimg, locationimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template4_pdf);

        button_SendMail = (Button) findViewById(R.id.button_SendMail);
        button_SendMail.setOnClickListener(this);

        BuildResumePDF();
    }

    public void BuildResumePDF()
    {
        pdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(System.currentTimeMillis());

        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory() + "/ResumeApp");

        if (!ResumeAppFolder.exists()) {
            ResumeAppFolder.mkdir();
        }

        //PDF file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/ResumeApp/" + pdfFileName + ".pdf";

        try {
            //create object of iText Document class
            Document document = new Document();
            //create instance of PdfWriter class
            PdfWriter.getInstance(document, new FileOutputStream(mFilePath));
            //open the document for writing
            document.open();

            document.setPageSize(PageSize.A4);

            //add author of the document (optional)
            document.addAuthor("Mah Dian Drovo");

            BaseColor columnColour = new BaseColor(0, 0, 0);

            BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf", "UTF-8", BaseFont.EMBEDDED);
            Font nameFont = new Font(fontName, 16.0f, Font.NORMAL, BaseColor.BLACK);

            headInfo(document,nameFont);


        }

        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        }

    }

    private void headInfo(Document document, Font fontName) throws IOException, DocumentException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        String path = ResumeProfilePart1.getImagePath();

        Image image = Image.getInstance(path);

        image.scaleAbsoluteHeight(55);
        image.scaleAbsoluteWidth(45);

        PdfPTable pdfPTable = new PdfPTable(new float[]{25, 75});
        pdfPTable.setWidthPercentage(100);

        Chunk profileHead = new Chunk("Profile",fontName);
        PdfPCell profileHeadCell = new PdfPCell(new Paragraph(profileHead));
        table.addCell(profileHeadCell);

        Chunk chunk1 = new Chunk(ResumeProfilePart2.career_objective, fontName);
        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
        table.addCell(pdfPCell1);
        document.add(table);

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
      *//*  PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");*//*

    }

    @Override
    public void onClick(View view) {
        if (view == button_SendMail) {
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename", pdfFileName + ".pdf");
            startActivity(intent);
        }
    }*/

    String pdfFileName = "";

    PDFView pdfView;
    String TAG = "PDF";
    Integer pageNumber = 0;
    int position = -1;
    private Button button_SendMail;
    Image imageemail;
    PdfPCell pdfimgemail,mobileimg,locationimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template2_pdf);

        button_SendMail = (Button) findViewById(R.id.button_SendMail);
        button_SendMail.setOnClickListener(this);

        BuildResumePDF();

        init();
        editDataInputted();
    }

    public void editDataInputted() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("resume_count").document("template2");

        FirebaseFirestore.getInstance()
                .collection("resume_count")
                .document("template2")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int cv_num = Integer.parseInt(documentSnapshot.getString("template2_cv_num"));
                        Log.d("HELLO", "Number: " + cv_num);
                        cv_num++;

                        Map<String, Object> data = new HashMap<>();
                        String numb = Integer.toString(cv_num);
                        data.put("template2_cv_num", numb);

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

    private void BuildResumePDF() {
        pdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(System.currentTimeMillis());
        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory() + "/ResumeApp");

        if (!ResumeAppFolder.exists()) {
            ResumeAppFolder.mkdir();
        }
        //PDF file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/ResumeApp/" + pdfFileName + ".pdf";

        try {

            //create object of iText Document class
            Document document = new Document();
            //create instance of PdfWriter class
            PdfWriter.getInstance(document, new FileOutputStream(mFilePath));
            //open the document for writing
            document.open();

            document.setPageSize(PageSize.A4);

            //add author of the document (optional)
            document.addAuthor("Mah Dian Drovo");

            //Font Settings
            //BaseColor colorAccent = new BaseColor(0,153,204,255);
            BaseColor colorAccent = new BaseColor(0, 0, 0);
            BaseColor topicColor = new BaseColor(192, 192, 192);
            BaseColor headColor = new BaseColor(0, 138, 211);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //Custom font
            BaseFont robotofont = BaseFont.createFont("assets/fonts/Roboto-Thin.ttf", "UTF-8", BaseFont.EMBEDDED);
            BaseFont robotolight = BaseFont.createFont("assets/fonts/Roboto-Light.ttf", "UTF-8", BaseFont.EMBEDDED);

            BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf", "UTF-8", BaseFont.EMBEDDED);
            //Create Title of the document
            Font nameFont = new Font(fontName, 16.0f, Font.NORMAL, BaseColor.WHITE);
            Font normalFont = new Font(robotolight, 8.5f, Font.NORMAL, BaseColor.WHITE);
            Font topicFont = new Font(fontName, 9.0f, Font.NORMAL, BLACK);

            // Font font1 = new Font(fontName, 7.0f, Font.FontFamily.HELVETICA, BLACK);

            Font font1 = new Font(robotolight, 8.0f, Font.NORMAL, BLACK);
            Font font2 = new Font(fontName, 12.0f, Font.UNDERLINE, headColor);
            Font font3 = new Font(fontName, 11.0f, Font.NORMAL, headColor);
            Font font4 = new Font(robotolight, 10.0f, Font.NORMAL, BLACK);
            Font font5 = new Font(fontName, 8.5f, Font.NORMAL, headColor);

            Font topicName = new Font(fontName, 16.0f, Font.UNDERLINE, headColor);

            headInfo(document, nameFont, normalFont, headColor);
            addLineSpace(document);
            bodayInfo(document, font1, font2, font3, font4, font5);

            //show message that file is saved
            Log.d("Filepath", pdfFileName + ".pdf IS SAVED TO " + mFilePath);

            //close the document
            document.close();


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        }
    }

    private void headInfo(Document document, Font nameFont, Font normalFont, BaseColor headColor) throws IOException, DocumentException {

        //String path = "/storage/emulated/0/Pictures/Messenger/received_508364883426271.jpeg";
        String path = ResumeProfilePart1.getImagePath();

        Image image = Image.getInstance(path);


        //image.setAbsolutePosition(450f,10f);
        //image.setAbsolutePosition(12,300);
        //image.setWidthPercentage(120);

        float abs_width = image.getAbsoluteX();
        float abs_height = image.getAbsoluteY();
        float plain_height = image.getPlainHeight();
        float plain_width = image.getPlainWidth();

        Log.d("SIZE_","ABS: Width: "+abs_width+" Height: "+abs_height);
        Log.d("SIZE_","ABS: Width: "+plain_width+" Height: "+plain_height);


        image.scaleAbsoluteHeight(55);
        image.scaleAbsoluteWidth(45);
        //image.setAbsolutePosition(0,0);


        PdfPTable table = new PdfPTable(new float[]{25, 75});
        //PdfPTable _table = new PdfPTable(new float[]{15,85});
        table.setWidthPercentage(100);

        Chunk chunk1 = new Chunk(ResumeProfilePart2.career_objective, normalFont);
        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(chunk1));
        pdfPCell1.setBorderColor(headColor);
        pdfPCell1.setBackgroundColor(headColor);

        PdfPCell pdfPCell2 = new PdfPCell(image);
        pdfPCell2.setRowspan(2);
        pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell2.setBorderColor(BaseColor.WHITE);

        Chunk chunk3 = new Chunk(ResumeProfilePart1.getName(), nameFont);
        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(chunk3));
        pdfPCell3.setBackgroundColor(headColor);
        pdfPCell3.setBorderColor(headColor);

        table.addCell(pdfPCell2);
        table.addCell(pdfPCell3);
        table.addCell(pdfPCell1);

        document.add(table);

    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(" "));
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private void bodayInfo(Document document, Font font1, Font font2, Font font3, Font font4, Font font5) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(new float[]{25, 75});
        table.setWidthPercentage(100);


        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);


        try {

            Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.send_mail_image,null);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image1 = Image.getInstance(stream.toByteArray());
            image1.scaleAbsoluteHeight(15);
            image1.scaleAbsoluteWidth(15);
            pdfimgemail = new PdfPCell(image1);
            //pdfimgemail.setRowspan(2);
            pdfimgemail.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfimgemail.setBorderColor(BaseColor.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.mobileimage,null);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image1 = Image.getInstance(stream.toByteArray());
            image1.scaleAbsoluteHeight(15);
            image1.scaleAbsoluteWidth(15);
            mobileimg = new PdfPCell(image1);
           // mobileimg.setRowspan(2);
            mobileimg.setHorizontalAlignment(Element.ALIGN_LEFT);
            mobileimg.setBorderColor(BaseColor.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }  try {

            Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.location,null);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image1 = Image.getInstance(stream.toByteArray());
            image1.scaleAbsoluteHeight(15.0f);
            image1.scaleAbsoluteWidth(15.0f);
            locationimg = new PdfPCell(image1);
            //locationimg.setRowspan(2);
            locationimg.setHorizontalAlignment(Element.ALIGN_LEFT);
            locationimg.setBorderColor(BaseColor.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_email_24);
        BitMapToString(myBitmap);
        //  Uri path = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID +  "/drawable/ic_baseline_email_24.xml");
        Image image = Image.getInstance( BitMapToString(myBitmap));
        image.scaleAbsoluteHeight(15);
        image.scaleAbsoluteWidth(15);
        pdfimgemail = new PdfPCell(image);
        pdfimgemail.setRowspan(2);
        pdfimgemail.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfimgemail.setBorderColor(BaseColor.WHITE);


        Chunk chunk10 = new Chunk(ResumeProfilePart1.getEmail(), font1);
        PdfPCell pdfPCell10 = new PdfPCell(new Paragraph(chunk10));
        pdfPCell10.setBorderColor(BaseColor.WHITE);

        Chunk chunk11 = new Chunk(ResumeProfilePart1.getContact_number(), font1);
        PdfPCell pdfPCell11 = new PdfPCell(new Paragraph(chunk11));
        pdfPCell11.setBorderColor(BaseColor.WHITE);

        Chunk chunk12 = new Chunk(ResumeProfilePart1.getPresent_address(), font1);
        PdfPCell pdfPCell12 = new PdfPCell(new Paragraph(chunk12));
        pdfPCell12.setBorderColor(BaseColor.WHITE);

        //SKILLS Start
        Chunk blankChunkSkillbefore = new Chunk(" ", font1);
        PdfPCell blankSkillCellbefore = new PdfPCell(new Paragraph(blankChunkSkillbefore));
        blankSkillCellbefore.setBorderColor(BaseColor.WHITE);

        Chunk skillsHeadchunk = new Chunk("Skills:", font2);
        PdfPCell skillsHeadcell = new PdfPCell(new Paragraph(skillsHeadchunk));
        skillsHeadcell.setBorderColor(BaseColor.WHITE);

        Chunk blankChunkSkill = new Chunk(" ", font1);
        PdfPCell blankSkillCell = new PdfPCell(new Paragraph(blankChunkSkill));
        blankSkillCell.setBorderColor(BaseColor.WHITE);


        String specialQualities;
        Skills_Model skills_model;
        skills_model = ResumeProfilePart3.skills.get(0);
        specialQualities = skills_model.getSkill().toString();
        if (ResumeProfilePart3.skills.size() > 1) {
            for (int i = 1; i < ResumeProfilePart3.skills.size(); i++) {
                skills_model = ResumeProfilePart3.skills.get(i);
                specialQualities = specialQualities.concat("\n" + skills_model.getSkill());
            }
        }

        Chunk skillchunk = new Chunk(specialQualities, font1);
        PdfPCell skillcell = new PdfPCell(new Paragraph(skillchunk));
        skillcell.setBorderColor(BaseColor.WHITE);

        //LANGUAGE Start
        Chunk blankChunkLanguagebefore = new Chunk(" ", font1);
        PdfPCell blanklanguageCellbefore = new PdfPCell(new Paragraph(blankChunkLanguagebefore));
        blanklanguageCellbefore.setBorderColor(BaseColor.WHITE);

        Chunk languageHeadchunk = new Chunk("Language:", font2);
        PdfPCell languageHeadcell = new PdfPCell(new Paragraph(languageHeadchunk));
        languageHeadcell.setBorderColor(BaseColor.WHITE);

        Chunk blankChunkLanguage = new Chunk(" ", font1);
        PdfPCell blankLanguageCell = new PdfPCell(new Paragraph(blankChunkLanguage));
        blankLanguageCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaHeadchunk = new Chunk("Bangla:", font1);
        PdfPCell banglaCell = new PdfPCell(new Paragraph(banglaHeadchunk));
        banglaCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaValuechunk = new Chunk(ResumeProfilePart3.bangla_skill_level, font5);
        PdfPCell banglaValueCell = new PdfPCell(new Paragraph(banglaValuechunk));
        banglaValueCell.setBorderColor(BaseColor.WHITE);

        Chunk englishHeadchunk = new Chunk("English:", font1);
        PdfPCell englishCell = new PdfPCell(new Paragraph(englishHeadchunk));
        englishCell.setBorderColor(BaseColor.WHITE);

        Chunk englishValuechunk = new Chunk(ResumeProfilePart3.english_skill_level, font5);
        PdfPCell englishValueCell = new PdfPCell(new Paragraph(englishValuechunk));
        englishValueCell.setBorderColor(BaseColor.WHITE);


        table1.addCell(pdfimgemail);
        table1.addCell(pdfPCell10);
        table1.addCell(mobileimg);
        table1.addCell(pdfPCell11);
        table1.addCell(locationimg);
        table1.addCell(pdfPCell12);
        table1.addCell(blankSkillCellbefore);
        table1.addCell(skillsHeadcell);
        table1.addCell(blankSkillCell);
        table1.addCell(skillcell);
        table1.addCell(blanklanguageCellbefore);
        table1.addCell(languageHeadcell);
        table1.addCell(blankLanguageCell);
        table1.addCell(banglaCell);
        table1.addCell(banglaValueCell);
        table1.addCell(englishCell);
        table1.addCell(englishValueCell);

        //REFERENCE Start
        Chunk blankChunkreferencebefore = new Chunk(" ", font1);
        PdfPCell blankreferenceCellbefore = new PdfPCell(new Paragraph(blankChunkreferencebefore));
        blankreferenceCellbefore.setBorderColor(BaseColor.WHITE);

        table1.addCell(blankreferenceCellbefore);

        if (ResumeProfilePart5.reference.size() > 0) {
            Chunk referenceHeadchunk = new Chunk("Reference:", font2);
            PdfPCell referenceHeadcell = new PdfPCell(new Paragraph(referenceHeadchunk));
            referenceHeadcell.setBorderColor(BaseColor.WHITE);
            table1.addCell(referenceHeadcell);

            Chunk blankChunkReference = new Chunk(" ", font1);
            PdfPCell blankReferenceCell = new PdfPCell(new Paragraph(blankChunkReference));
            blankReferenceCell.setBorderColor(BaseColor.WHITE);

            table1.addCell(blankReferenceCell);

            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(0);
            Chunk reference1Namechunk = new Chunk(reference_model.getName(), font5);
            PdfPCell reference1Namecell = new PdfPCell(new Paragraph(reference1Namechunk));
            reference1Namecell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference1Namecell);

            Chunk reference1Detailschunk = new Chunk(reference_model.getDesignation() + "\n" + reference_model.getOrganization_name() + "\n" + reference_model.getEmail() + "\n" + reference_model.getMobile_number(), font1);
            PdfPCell reference1Detailscell = new PdfPCell(new Paragraph(reference1Detailschunk));
            reference1Detailscell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference1Detailscell);
        }

        if (ResumeProfilePart5.reference.size() > 1) {
            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(1);
            Chunk reference2Namechunk = new Chunk(reference_model.getName(), font5);
            PdfPCell reference2Namecell = new PdfPCell(new Paragraph(reference2Namechunk));
            reference2Namecell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference2Namecell);

            Chunk reference2Detailschunk = new Chunk(reference_model.getDesignation() + "\n" + reference_model.getOrganization_name() + "\n" + reference_model.getEmail() + "\n" + reference_model.getMobile_number(), font1);
            PdfPCell reference2Detailscell = new PdfPCell(new Paragraph(reference2Detailschunk));
            reference2Detailscell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference2Detailscell);
        }

        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);

        Chunk chunk20 = new Chunk("Mah Dian Drovo",font1);
        PdfPCell pdfPCell20 = new PdfPCell(new Paragraph(chunk20));
        pdfPCell20.setBorderColor(BaseColor.BLACK);

        Chunk chunk21 = new Chunk("Dhaka",font1);
        PdfPCell pdfPCell21 = new PdfPCell(new Paragraph(chunk21));
        pdfPCell21.setBorderColor(BaseColor.BLACK);

        table2.addCell(pdfPCell20);
        table2.addCell(pdfPCell21);


        //EDUCATION START
        Chunk educationHeadchunk = new Chunk("Education Qualification:", font2);
        PdfPCell educationHeadcell = new PdfPCell(new Paragraph(educationHeadchunk));
        educationHeadcell.setBorderColor(BaseColor.WHITE);

        table2.addCell(educationHeadcell);

        Chunk blankChunk1 = new Chunk(" ", font1);
        PdfPCell blankCell1 = new PdfPCell(new Paragraph(blankChunk1));
        blankCell1.setBorderColor(BaseColor.WHITE);

        table2.addCell(blankCell1);

        EducationQualification_Model educationQualification_model;
        for (int i = 0; i < ResumeProfilePart2.educationQualification.size(); i++) {
            educationQualification_model = ResumeProfilePart2.educationQualification.get(i);

            Chunk qualificationNamechunk = new Chunk(educationQualification_model.getQualification_name(), font3);
            PdfPCell qualificationNamecell = new PdfPCell(new Paragraph(qualificationNamechunk));
            qualificationNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(qualificationNamecell);

            Chunk instituteNamechunk = new Chunk(educationQualification_model.getInstitute_name(), font4);
            PdfPCell instituteNamecell = new PdfPCell(new Paragraph(instituteNamechunk));
            instituteNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(instituteNamecell);

            Chunk detailsChunk = new Chunk("Passing Year: " + educationQualification_model.getPassing_year() + "\n" + "Board: " + educationQualification_model.getBoard_name() + "\nResult: " + educationQualification_model.getResult(), font1);
            PdfPCell detailsCell = new PdfPCell(new Paragraph(detailsChunk));
            detailsCell.setBorderColor(BaseColor.WHITE);

            table2.addCell(detailsCell);

            Chunk blankChunk2 = new Chunk(" ", font1);
            PdfPCell blankCell2 = new PdfPCell(new Paragraph(blankChunk2));
            blankCell2.setBorderColor(BaseColor.WHITE);

            table2.addCell(blankCell2);

        }


        if (ResumeProfilePart6.workExperience.size() > 0) {
            //Professional Experience START
            Chunk experienceHeadchunk = new Chunk("Professional Experience:", font2);
            PdfPCell experienceHeadcell = new PdfPCell(new Paragraph(experienceHeadchunk));
            experienceHeadcell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experienceHeadcell);

            Chunk blankChunk3 = new Chunk(" ", font1);
            PdfPCell blankCell3 = new PdfPCell(new Paragraph(blankChunk3));
            blankCell3.setBorderColor(BaseColor.WHITE);

            table2.addCell(blankCell3);

            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(0);

            Chunk experience1Namechunk = new Chunk(workExperience_model.getDesignationName(), font3);
            PdfPCell experience1Namecell = new PdfPCell(new Paragraph(experience1Namechunk));
            experience1Namecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1Namecell);

            Chunk experience1CompanyNamechunk = new Chunk(workExperience_model.getOgganizationAddress(), font4);
            PdfPCell experience1CompanyNamecell = new PdfPCell(new Paragraph(experience1CompanyNamechunk));
            experience1CompanyNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1CompanyNamecell);

            Chunk experience1detailsChunk = new Chunk("Address : " + workExperience_model.getOgganizationAddress() + "\nDuration: " + workExperience_model.getDurationTime(), font4);
            PdfPCell experience1detailscell = new PdfPCell(new Paragraph(experience1detailsChunk));
            experience1detailscell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1detailscell);

        }

        if (ResumeProfilePart6.workExperience.size() > 1) {
            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(1);

            Chunk experience1Namechunk = new Chunk(workExperience_model.getDesignationName(), font3);
            PdfPCell experience1Namecell = new PdfPCell(new Paragraph(experience1Namechunk));
            experience1Namecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1Namecell);

            Chunk experience1CompanyNamechunk = new Chunk(workExperience_model.getOgganizationAddress(), font4);
            PdfPCell experience1CompanyNamecell = new PdfPCell(new Paragraph(experience1CompanyNamechunk));
            experience1CompanyNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1CompanyNamecell);

            Chunk experience1detailsChunk = new Chunk("Address: " + workExperience_model.getOgganizationAddress() + "\nDuration: " + workExperience_model.getDurationTime(), font4);
            PdfPCell experience1detailscell = new PdfPCell(new Paragraph(experience1detailsChunk));
            experience1detailscell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1detailscell);
        }


        PdfPCell cell1 = new PdfPCell();
        cell1.setBorderColor(BaseColor.WHITE);
        cell1.addElement(table1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setBorderColor(BaseColor.WHITE);
        cell2.addElement(table2);

        table.addCell(cell1);
        table.addCell(cell2);

        document.add(table);
    }

    private void init() {
        pdfView = (PDFView) findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position", -1);
        displayFromSdcard();
    }

    private void displayFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory() + "/ResumeApp/" + pdfFileName + ".pdf");
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
    public void onClick(View view) {
        if (view == button_SendMail) {
            finish();
            Intent intent = new Intent(this, SendMail.class);
            intent.putExtra("filename", pdfFileName + ".pdf");
            startActivity(intent);
        }
    }

}
