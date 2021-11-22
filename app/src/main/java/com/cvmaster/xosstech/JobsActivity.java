package com.cvmaster.xosstech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.Ads.Interstetial;
import com.cvmaster.xosstech.adapter.CvAdapter;
import com.cvmaster.xosstech.adapter.JobsAdapter;
import com.cvmaster.xosstech.model.Cv_Model;
import com.cvmaster.xosstech.model.Jobs;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobsActivity extends AppCompatActivity {

    private RecyclerView rvJobList ;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Jobs> jobsList;
    private RecyclerView.Adapter adapter;

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    private String jobUrl = "http://xosstech.com/cvm/api/public/api/jobs";
    private static final String TAG = "MyActivity";

    private InterstitialAd interstitialAd;
    private String id,jobTittle,companyName,category,location,apply,pdf,endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        rvJobList = findViewById(R.id.rvJobs);

        loadAd();

        jobsList = new ArrayList<>();
        adapter = new JobsAdapter(getApplicationContext(),jobsList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(rvJobList.getContext(), linearLayoutManager.getOrientation());

        rvJobList.setHasFixedSize(true);
        rvJobList.setLayoutManager(linearLayoutManager);
        rvJobList.addItemDecoration(dividerItemDecoration);
        rvJobList.setAdapter(adapter);
        showCv();
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
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        JobsActivity.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        Toast.makeText(JobsActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        showInterstitial();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        JobsActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        JobsActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
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
                        Toast.makeText(
                                JobsActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

   /* @Override
    public void onResume() {
        // Start or resume the game.
        super.onResume();

        if (gameIsInProgress) {
            resumeGame(timerMilliseconds);
        }
    }*/

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCv() {

        StringRequest request = new StringRequest(Request.Method.POST, jobUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                JSONArray cvArray = jsonObject.getJSONArray("data");

                                for(int j=0;j<cvArray.length();j++)
                                {
                                    JSONObject commentobj = cvArray.getJSONObject(j);
                                    id = commentobj.getString("id");
                                    jobTittle = commentobj.getString("job_title");
                                    companyName = commentobj.getString("company_name");
                                    location = commentobj.getString("location");
                                    apply = commentobj.getString("apply_link");
                                    pdf = commentobj.getString("pdf_link");
                                    endDate = commentobj.getString("end_date");

                                    Jobs jobs = new Jobs(id,jobTittle,companyName,endDate,apply,pdf);
                                    jobsList.add(jobs);

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(JobsActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobsActivity.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}