package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SendMail extends AppCompatActivity implements View.OnClickListener {

    EditText send_email_eAddress, send_email_Subject, send_email_Body;
    TextView textView_FileName;
    Button btn_Send_Email;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        filename = getIntent().getStringExtra("filename");

        send_email_eAddress = findViewById(R.id.send_email_eAddress);
        send_email_Subject = findViewById(R.id.send_email_Subject);
        send_email_Body = findViewById(R.id.send_email_Body);
        textView_FileName = (TextView) findViewById(R.id.textView_PDFFileName);
        btn_Send_Email = findViewById(R.id.btn_Send_Email);

        textView_FileName.setText(filename);

        btn_Send_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filename != null) {
                    send_email(filename);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    public void send_email(String pdffilename) {

        if (send_email_eAddress.getText().toString().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(send_email_eAddress.getText().toString()).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
        } else if (send_email_Subject.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter a subject", Toast.LENGTH_SHORT).show();
        } else if (send_email_Body.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter a body", Toast.LENGTH_SHORT).show();
        } else {
            String[] mailto = {send_email_eAddress.getText().toString()};
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/documents/" + pdffilename);
            Uri uri = FileProvider.getUriForFile(
                    SendMail.this,
                    "com.cvmaster.provider",
                    file
            );
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, send_email_Subject.getText().toString());
            emailIntent.putExtra(Intent.EXTRA_TEXT, send_email_Body.getText().toString());
            emailIntent.setType("application/pdf");
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(emailIntent, "Send email using:"));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);

    }

}
