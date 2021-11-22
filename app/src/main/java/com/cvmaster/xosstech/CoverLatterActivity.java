package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CoverLatterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etHeader,etSubject,etBody,etFooter;
    private String header,subject,body,footer;
    private FloatingActionButton fabCvMake ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_latter);

        fabCvMake = findViewById(R.id.fabCoverLatterMake);
        etHeader = findViewById(R.id.etCoverLatterHeader);
        etSubject = findViewById(R.id.etCoverLatterSubject);
        etBody = findViewById(R.id.etCoverLatterBody);
        etFooter = findViewById(R.id.etCoverLatterFooter);
    }

    private void makeCoverLatter()
    {
        header = etHeader.getText().toString();
        subject = etSubject.getText().toString();
        body = etBody.getText().toString();
        footer = etFooter.getText().toString();

    }

    @Override
    public void onClick(View view) {
        if(view == fabCvMake)
        {

        }
    }
}