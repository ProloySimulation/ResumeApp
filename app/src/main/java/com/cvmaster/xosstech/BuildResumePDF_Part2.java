package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuildResumePDF_Part2 extends AppCompatActivity implements View.OnClickListener {
    private Button button_Done;
    private float CHARGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_pdf_part2);

        Intent data = getIntent();
        CHARGE = (float) 0.5;

        //Toast.makeText(this,"CHARGE "+CHARGE+" BDT",Toast.LENGTH_LONG).show();

        button_Done = (Button) findViewById(R.id.button_Done);
        button_Done.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == button_Done){
            finish();
            String msisdn = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();

        }
    }

}
