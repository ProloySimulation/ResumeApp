package com.cvmaster.xosstech.templete;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.SendMail;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import java.util.Locale;

public class Template4_new_pdf extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener {

    String pdfFileName = "";

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
      /*  PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");*/

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
