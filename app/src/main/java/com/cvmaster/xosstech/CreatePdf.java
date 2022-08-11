package com.cvmaster.xosstech;

import android.os.Environment;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreatePdf {

    public void createPdf(String header,String subject,String body,String footer) throws FileNotFoundException
    {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"nooooo.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        com.itextpdf.layout.Document document = new Document(pdfDocument);

        Paragraph headerPara = new Paragraph(header);
        Paragraph subjectPara = new Paragraph(subject);
        Paragraph bodyPara = new Paragraph(body);
        Paragraph footerPara = new Paragraph(footer);
        document.add(headerPara);
        document.add(subjectPara);
        document.add(bodyPara);
        document.add(footerPara);

        document.close();
    }
}
