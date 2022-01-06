package com.cvmaster.xosstech;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.model.Jobs;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
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

        adLoad();

    }

    public void downloadPdfContent(String urlToDownload){

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Circular Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_baseline_email_24);
        mBuilder.setProgress(100, 1, false);
        mNotifyManager.notify(0, mBuilder.build());

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
                adShow();
            }

            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }
    }

    private void adLoad()
    {
        progrssBar.setVisibility(View.VISIBLE);

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-7854798461578735/4913309588",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;

                        //Google Admob Limited

                        progrssBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        progrssBar.setVisibility(View.GONE);
//                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }

    private void adShow()
    {
        if (mRewardedAd != null) {
            Activity activityContext = SingleJob.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    downloadPdfContent(circular);
                }
            });
        } else {
//            Toast.makeText(getApplicationContext(), "Please wait, your connection is slow", Toast.LENGTH_SHORT).show();

            //Google Admob Limited

            downloadPdfContent(circular);
        }
    }
}