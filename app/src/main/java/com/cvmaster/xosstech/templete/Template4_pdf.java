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
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Skills_Model;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Template4_pdf extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener {


    String pdfFileName = "";

    PDFView pdfView;
    String TAG = "PDF";
    Integer pageNumber = 0;
    int position = -1;
    private Button button_SendMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template4_pdf);


        button_SendMail = (Button) findViewById(R.id.button_SendMail);
        button_SendMail.setOnClickListener(this);

        BuildResumePDF();

        init();
        editDataInputted();
    }


    public void editDataInputted() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("resume_count").document("template4");

        FirebaseFirestore.getInstance()
                .collection("resume_count")
                .document("template4")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int cv_num = Integer.parseInt(documentSnapshot.getString("template4_cv_num"));
                        Log.d("HELLO", "Number: " + cv_num);
                        cv_num++;

                        Map<String, Object> data = new HashMap<>();
                        String numb = Integer.toString(cv_num);
                        data.put("template4_cv_num", numb);

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

        File ResumeAppFolder = new File(Environment.getExternalStorageDirectory()
                + "/ResumeApp");

        if (!ResumeAppFolder.exists()) {
            ResumeAppFolder.mkdir();
        }

        String mFilePath = Environment.getExternalStorageDirectory()
                + "/ResumeApp/" + pdfFileName + ".pdf";

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(mFilePath));
            document.open();
            document.setPageSize(PageSize.A4);
            document.addAuthor("Fahamin");

            BaseColor colorAccent = new BaseColor(0, 0, 0);
            BaseColor topicColor = new BaseColor(192, 192, 192);
            BaseColor headColor = new BaseColor(0, 138, 211);
            BaseColor sideColoumn = new BaseColor(0,0,0);


            //Custom font
            BaseFont fontName = BaseFont.createFont("assets/fonts/FiraSans-Bold.otf", "UTF-8", BaseFont.EMBEDDED);
            //Create Title of the document
            Font nameFont = new Font(fontName, 16.0f, Font.NORMAL, BaseColor.WHITE);
            Font normalFont = new Font(fontName, 8.5f, Font.NORMAL, BaseColor.WHITE);
            Font topicFont = new Font(fontName, 9.0f, Font.NORMAL, BaseColor.BLACK);

            Font font1 = new Font(fontName, 8.5f, Font.NORMAL, BaseColor.BLACK);
            Font font2 = new Font(fontName, 12.0f, Font.UNDERLINE, headColor);
            Font font3 = new Font(fontName, 11.0f, Font.NORMAL, headColor);
            Font font4 = new Font(fontName, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font font5 = new Font(fontName, 8.5f, Font.NORMAL, headColor);


            headInfo(document, nameFont, normalFont, headColor);
            addLineSpace(document);
            bodayInfo(document, font1, font2, font3, font4, font5);

            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void headInfo(Document document, Font nameFont, Font normalFont, BaseColor headColor) throws IOException, DocumentException {

        String path = ResumeProfilePart1.getImagePath();
        Image image = Image.getInstance(path);
        image.scaleAbsoluteHeight(55);
        image.scaleAbsoluteWidth(45);

        PdfPTable table = new PdfPTable(new float[]{25, 75});
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

    private void bodayInfo(Document document, Font font1, Font font2, Font font3, Font font4, Font font5) throws IOException, DocumentException {

        PdfPTable table = new PdfPTable(new  float[]{25,75});
        table.setWidthPercentage(100);

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);

        Chunk chunk10 = new Chunk(ResumeProfilePart1.getEmail(),font1);
        PdfPCell pdfPCell10 = new PdfPCell(new Paragraph(chunk10));
        pdfPCell10.setBorderColor(BaseColor.WHITE);

        Chunk chunk11 = new Chunk(ResumeProfilePart1.getContact_number(),font1);
        PdfPCell pdfPCell11 = new PdfPCell(new Paragraph(chunk11));
        pdfPCell11.setBorderColor(BaseColor.WHITE);

        Chunk chunk12 = new Chunk(ResumeProfilePart1.getPresent_address(),font1);
        PdfPCell pdfPCell12 = new PdfPCell(new Paragraph(chunk12));
        pdfPCell12.setBorderColor(BaseColor.WHITE);

        Chunk blankChunkSkillbefore = new Chunk("",font1);
        PdfPCell blankSkillCellbefore = new PdfPCell(new Paragraph(blankChunkSkillbefore));
        blankSkillCellbefore.setBorderColor(BaseColor.WHITE);

        Chunk skillsHeadchunk = new Chunk("Skills:",font2);
        PdfPCell skillsHeadcell = new PdfPCell(new Paragraph(skillsHeadchunk));
        skillsHeadcell.setBorderColor(BaseColor.WHITE);

        Chunk blankChunkSkill = new Chunk(" ",font1);
        PdfPCell blankSkillCell = new PdfPCell(new Paragraph(blankChunkSkill));
        blankSkillCell.setBorderColor(BaseColor.WHITE);

        Skills_Model skills_model = ResumeProfilePart3.skills.get(0);
        String specialQualities = skills_model.getSkill().toString();

        if(ResumeProfilePart3.skills.size()>1)
        {
            for (int i = 0; i <ResumeProfilePart3.skills.size() ; i++) {
                skills_model = ResumeProfilePart3.skills.get(i);
                specialQualities = specialQualities.concat("\n" + skills_model.getSkill());
            }
        }

        Chunk skillchunk = new Chunk(specialQualities, font1);
        PdfPCell skillcell = new PdfPCell(new Paragraph(skillchunk));
        skillcell.setBorderColor(BaseColor.WHITE);




        //LANGUAGE Start
        Chunk blankChunkLanguagebefore = new Chunk(" ",font1);
        PdfPCell blanklanguageCellbefore = new PdfPCell(new Paragraph(blankChunkLanguagebefore));
        blanklanguageCellbefore.setBorderColor(BaseColor.WHITE);

        Chunk languageHeadchunk = new Chunk("Language:",font2);
        PdfPCell languageHeadcell = new PdfPCell(new Paragraph(languageHeadchunk));
        languageHeadcell.setBorderColor(BaseColor.WHITE);

        Chunk blankChunkLanguage = new Chunk(" ",font1);
        PdfPCell blankLanguageCell = new PdfPCell(new Paragraph(blankChunkLanguage));
        blankLanguageCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaHeadchunk = new Chunk("Bangla:",font1);
        PdfPCell banglaCell = new PdfPCell(new Paragraph(banglaHeadchunk));
        banglaCell.setBorderColor(BaseColor.WHITE);

        Chunk banglaValuechunk = new Chunk(ResumeProfilePart3.bangla_skill_level,font5);
        PdfPCell banglaValueCell = new PdfPCell(new Paragraph(banglaValuechunk));
        banglaValueCell.setBorderColor(BaseColor.WHITE);

        Chunk englishHeadchunk = new Chunk("English:",font1);
        PdfPCell englishCell = new PdfPCell(new Paragraph(englishHeadchunk));
        englishCell.setBorderColor(BaseColor.WHITE);

        Chunk englishValuechunk = new Chunk(ResumeProfilePart3.english_skill_level,font5);
        PdfPCell englishValueCell = new PdfPCell(new Paragraph(englishValuechunk));
        englishValueCell.setBorderColor(BaseColor.WHITE);



        table1.addCell(pdfPCell10);
        table1.addCell(pdfPCell11);
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
        Chunk blankChunkreferencebefore = new Chunk(" ",font1);
        PdfPCell blankreferenceCellbefore = new PdfPCell(new Paragraph(blankChunkreferencebefore));
        blankreferenceCellbefore.setBorderColor(BaseColor.WHITE);

        table1.addCell(blankreferenceCellbefore);

        if (ResumeProfilePart5.reference.size()>0){
            Chunk referenceHeadchunk = new Chunk("Reference:",font2);
            PdfPCell referenceHeadcell = new PdfPCell(new Paragraph(referenceHeadchunk));
            referenceHeadcell.setBorderColor(BaseColor.WHITE);
            table1.addCell(referenceHeadcell);

            Chunk blankChunkReference = new Chunk(" ",font1);
            PdfPCell blankReferenceCell = new PdfPCell(new Paragraph(blankChunkReference));
            blankReferenceCell.setBorderColor(BaseColor.WHITE);

            table1.addCell(blankReferenceCell);

            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(0);
            Chunk reference1Namechunk = new Chunk(reference_model.getName(),font5);
            PdfPCell reference1Namecell = new PdfPCell(new Paragraph(reference1Namechunk));
            reference1Namecell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference1Namecell);

            Chunk reference1Detailschunk = new Chunk(reference_model.getDesignation()+"\n"+reference_model.getOrganization_name()+"\n"+reference_model.getEmail()+"\n"+reference_model.getMobile_number(),font1);
            PdfPCell reference1Detailscell = new PdfPCell(new Paragraph(reference1Detailschunk));
            reference1Detailscell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference1Detailscell);
        }

        if (ResumeProfilePart5.reference.size()>1){
            Reference_Model reference_model;
            reference_model = ResumeProfilePart5.reference.get(1);
            Chunk reference2Namechunk = new Chunk(reference_model.getName(),font5);
            PdfPCell reference2Namecell = new PdfPCell(new Paragraph(reference2Namechunk));
            reference2Namecell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference2Namecell);

            Chunk reference2Detailschunk = new Chunk(reference_model.getDesignation()+"\n"+reference_model.getOrganization_name()+"\n"+reference_model.getEmail()+"\n"+reference_model.getMobile_number(),font1);
            PdfPCell reference2Detailscell = new PdfPCell(new Paragraph(reference2Detailschunk));
            reference2Detailscell.setBorderColor(BaseColor.WHITE);
            table1.addCell(reference2Detailscell);
        }

        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);
        /*
        Chunk chunk20 = new Chunk("Mah Dian Drovo",font1);
        PdfPCell pdfPCell20 = new PdfPCell(new Paragraph(chunk20));
        pdfPCell20.setBorderColor(BaseColor.BLACK);

        Chunk chunk21 = new Chunk("Dhaka",font1);
        PdfPCell pdfPCell21 = new PdfPCell(new Paragraph(chunk21));
        pdfPCell21.setBorderColor(BaseColor.BLACK);

        table2.addCell(pdfPCell20);
        table2.addCell(pdfPCell21);
        */

        //EDUCATION START
        Chunk educationHeadchunk = new Chunk("Education Qualification:",font2);
        PdfPCell educationHeadcell = new PdfPCell(new Paragraph(educationHeadchunk));
        educationHeadcell.setBorderColor(BaseColor.WHITE);

        table2.addCell(educationHeadcell);

        Chunk blankChunk1 = new Chunk(" ",font1);
        PdfPCell blankCell1 = new PdfPCell(new Paragraph(blankChunk1));
        blankCell1.setBorderColor(BaseColor.WHITE);

        table2.addCell(blankCell1);

        EducationQualification_Model educationQualification_model;
        for (int i=0;i<ResumeProfilePart2.educationQualification.size();i++){
            educationQualification_model = ResumeProfilePart2.educationQualification.get(i);

            Chunk qualificationNamechunk = new Chunk(educationQualification_model.getQualification_name(),font3);
            PdfPCell qualificationNamecell = new PdfPCell(new Paragraph(qualificationNamechunk));
            qualificationNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(qualificationNamecell);

            Chunk instituteNamechunk = new Chunk(educationQualification_model.getInstitute_name(),font4);
            PdfPCell instituteNamecell = new PdfPCell(new Paragraph(instituteNamechunk));
            instituteNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(instituteNamecell);

            Chunk detailsChunk = new Chunk("Passing Year: "+educationQualification_model.getPassing_year()+"\n"+"Board: "+educationQualification_model.getBoard_name()+"\nPassing Year: "+educationQualification_model.getPassing_year(),font1);
            PdfPCell detailsCell = new PdfPCell(new Paragraph(detailsChunk));
            detailsCell.setBorderColor(BaseColor.WHITE);

            table2.addCell(detailsCell);

            Chunk blankChunk2 = new Chunk(" ",font1);
            PdfPCell blankCell2 = new PdfPCell(new Paragraph(blankChunk2));
            blankCell2.setBorderColor(BaseColor.WHITE);

            table2.addCell(blankCell2);

        }


        if (ResumeProfilePart6.workExperience.size()>0){
            //Professional Experience START
            Chunk experienceHeadchunk = new Chunk("Professional Experience:",font2);
            PdfPCell experienceHeadcell = new PdfPCell(new Paragraph(experienceHeadchunk));
            experienceHeadcell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experienceHeadcell);

            Chunk blankChunk3 = new Chunk(" ",font1);
            PdfPCell blankCell3 = new PdfPCell(new Paragraph(blankChunk3));
            blankCell3.setBorderColor(BaseColor.WHITE);

            table2.addCell(blankCell3);

            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(0);

            Chunk experience1Namechunk = new Chunk(workExperience_model.getDesignationName(),font3);
            PdfPCell experience1Namecell = new PdfPCell(new Paragraph(experience1Namechunk));
            experience1Namecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1Namecell);

            Chunk experience1CompanyNamechunk = new Chunk(workExperience_model.getOgganizationAddress(),font4);
            PdfPCell experience1CompanyNamecell = new PdfPCell(new Paragraph(experience1CompanyNamechunk));
            experience1CompanyNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1CompanyNamecell);

            Chunk experience1detailsChunk = new Chunk("Address: "+workExperience_model.getOgganizationAddress()+"\nDuration: "+workExperience_model.getDurationTime(),font4);
            PdfPCell experience1detailscell = new PdfPCell(new Paragraph(experience1detailsChunk));
            experience1detailscell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1detailscell);

        }

        if (ResumeProfilePart6.workExperience.size()>1){
            WorkExperience_Model workExperience_model;
            workExperience_model = ResumeProfilePart6.workExperience.get(1);

            Chunk experience1Namechunk = new Chunk(workExperience_model.getDesignationName(),font3);
            PdfPCell experience1Namecell = new PdfPCell(new Paragraph(experience1Namechunk));
            experience1Namecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1Namecell);

            Chunk experience1CompanyNamechunk = new Chunk(workExperience_model.getOgganizationAddress(),font4);
            PdfPCell experience1CompanyNamecell = new PdfPCell(new Paragraph(experience1CompanyNamechunk));
            experience1CompanyNamecell.setBorderColor(BaseColor.WHITE);

            table2.addCell(experience1CompanyNamecell);

            Chunk experience1detailsChunk = new Chunk("Address: "+workExperience_model.getOgganizationAddress()+"\nDuration: "+workExperience_model.getDurationTime(),font4);
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
