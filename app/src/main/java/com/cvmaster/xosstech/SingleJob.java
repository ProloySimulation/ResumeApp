package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.model.Jobs;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SingleJob extends AppCompatActivity {

    private PDFView pdfJob;
    private String pdfUrl;
    private TextView tvJobTittle,tvJobOfficeName,tvJobApplyLink,tvJobEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_job);

        tvJobTittle = findViewById(R.id.tvJobDetailTitle);
        tvJobEndDate = findViewById(R.id.tvJobDetailEndDate);
        tvJobOfficeName = findViewById(R.id.tvJobDetailOfficeName);
        tvJobApplyLink = findViewById(R.id.tvJobDetailApplyLink);
        pdfJob = findViewById(R.id.pdfJob);

        pdfUrl = Jobs.getFile();
        tvJobTittle.setText(Jobs.getJobTitle());
        tvJobOfficeName.setText(Jobs.getOfficeName());
        tvJobApplyLink.setText(Jobs.getApplyLink());
        tvJobEndDate.setText(Jobs.getEndDate());

        Toast.makeText(getApplicationContext(), pdfUrl, Toast.LENGTH_SHORT).show();
        showPdf();

    }

    private void showPdf() {
        new RetrivePDFfromActivity().execute(pdfUrl);
    }

    class RetrivePDFfromActivity extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfJob.fromStream(inputStream).load();
        }
    }
}