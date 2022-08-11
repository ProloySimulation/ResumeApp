package com.cvmaster.xosstech.JobSector;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.shockwave.pdfium.PdfDocument;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class SingleJob extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener {

    Integer pageNumber = 0;
    String pdfFileName="";
    String TAG="PDF";
    int position=-1;

    private String tittle,officeName,applyLink,jobEndDate,circular;
    private LinearLayout layoutJob;
    private int PERMISSION_REQUEST = 0;
    private boolean allowSave = true;
    private Button btnDownloadCircular;
    private SpinKitView progrssBar ;
    private String fileName = null;
    private RewardedAd mRewardedAd;
    private PDFView pdfView;
    private TextView tvJobTittle,tvJobOfficeName,tvJobApplyLink,tvJobEndDate;
    private File file,outputFile;

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;

    private static final String AD_UNIT_ID = "ca-app-pub-7854798461578735/5532470659";
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_job);

        tvJobTittle = findViewById(R.id.tvJobDetailTitle);
        tvJobEndDate = findViewById(R.id.tvJobDetailEndDate);
        tvJobOfficeName = findViewById(R.id.tvJobDetailOfficeName);
        tvJobApplyLink = findViewById(R.id.tvJobDetailApplyLink);
        btnDownloadCircular = findViewById(R.id.btnDownloadJobPdf);
        progrssBar = findViewById(R.id.job_spin_kit);
        pdfView = findViewById(R.id.circualrPdf);
        layoutJob = findViewById(R.id.linearJobDetail);
        btnDownloadCircular.setOnClickListener(this);

        tittle = getIntent().getStringExtra("jobtittle");
        officeName = getIntent().getStringExtra("joboffice");
        applyLink = getIntent().getStringExtra("applylink");
        jobEndDate = getIntent().getStringExtra("jobenddate");
        circular = getIntent().getStringExtra("circualrpath");

        tvJobTittle.setText(tittle);
        tvJobOfficeName.setText(officeName);
        tvJobEndDate.setText(jobEndDate);
        tvJobApplyLink.setText(applyLink);

        tvJobApplyLink.setMovementMethod(LinkMovementMethod.getInstance());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        progrssBar.setVisibility(View.VISIBLE);
        new RetrivePDFfromUrl().execute(circular);
        loadAd();

    }

    public void downloadPdfContent(String urlToDownload){

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Circular Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_baseline_email_24);
        mBuilder.setProgress(100, 1, false);
        mNotifyManager.notify(10, mBuilder.build());

        try {

            progrssBar.setVisibility(View.VISIBLE);

            fileName = String.format("%s.pdf", new SimpleDateFormat("dd_MM_yyyyHH_mm_ss", Locale.US).format(new Date()));

            URL url = new URL(urlToDownload);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String PATH = Environment.getExternalStorageDirectory() + "/download/";
            file = new File(PATH);
            file.mkdirs();
            outputFile = new File(file, fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            allowSave = true;

            mBuilder.setProgress(100, 100, false);
            mBuilder.setContentText("Download Successfully. Check Download Folder");

            mNotifyManager.notify(0, mBuilder.build());
            Toast.makeText(getApplicationContext(), "You File Save In Download Folder", Toast.LENGTH_LONG).show();
            progrssBar.setVisibility(View.GONE);
            displayFromSdcard();
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayFromSdcard() {
        layoutJob.setVisibility(View.GONE);
        pdfView.fromFile(outputFile)
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
        setTitle(String.format("%s %s / %s", fileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
//        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }


    @Override
    public void onClick(View view) {
        if(view == btnDownloadCircular)
        {
            if (!allowSave)
                return;
            allowSave = false;
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PERMISSION_GRANTED)
            {
                showInterstitial();
            }

            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }
    }

    public void loadAd() {

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        SingleJob.this.interstitialAd = interstitialAd;
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {

                                        SingleJob.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                                        SingleJob.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAd = null;

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                    }
                });
    }

    private void showInterstitial() {

        if (interstitialAd != null) {
            interstitialAd.show(this);
            downloadPdfContent(circular);
        } else {
            downloadPdfContent(circular);
        }
    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
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
            progrssBar.setVisibility(View.GONE);
            pdfView.fromStream(inputStream).load();
        }
    }
}