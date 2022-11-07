package com.cvmaster.xosstech;


import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CoverLatterActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean allowSave = true;
    private int PERMISSION_REQUEST = 0;

    private EditText etHeader,etSubject,etBody,etFooter;
    private String header,subject,body,footer;
    private FloatingActionButton fabCvMake ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_latter);

        fabCvMake = findViewById(R.id.fabCoverLatterMake);
        fabCvMake.setOnClickListener(this);
        etHeader = findViewById(R.id.etCoverLatterHeader);
        etSubject = findViewById(R.id.etCoverLatterSubject);
        etBody = findViewById(R.id.etCoverLatterBody);
        etFooter = findViewById(R.id.etCoverLatterFooter);

        checkPermission();
    }

    private void checkPermission()
    {
        if (!allowSave)
            return;
        allowSave = false;

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {

        }

        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

    }

    @Override
    public void onClick(View view) {
        if(view == fabCvMake)
        {
            header = etHeader.getText().toString();
            subject = etSubject.getText().toString();
            body = etBody.getText().toString();
            footer = etFooter.getText().toString();

            CreatePdf createPdf = new CreatePdf();
            try {
                createPdf.createPdf(header,subject,body,footer);
                Toast.makeText(getApplicationContext(), "Cover Latter Saved In Download Folder", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}