package com.cvmaster.xosstech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.adapter.InterviewAdapter;
import com.cvmaster.xosstech.adapter.JobsAdapter;
import com.cvmaster.xosstech.model.Interview;
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

public class InterviewActivity extends AppCompatActivity {

    private RecyclerView rvTips;
    private LinearLayoutManager linearLayoutManager;
    private List<Interview> interviewList;
    private RecyclerView.Adapter adapter;
    private InterstitialAd interstitialAd;
    private String id,tips,token;
    private static final String AD_UNIT_ID = "ca-app-pub-7854798461578735/4559503678";
    private String interviewUrl = "https://xosstech.com/cvm/api/public/api/interviewtips";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        rvTips = findViewById(R.id.rvInterviewTips);

        interviewList = new ArrayList<>();
        adapter = new InterviewAdapter(getApplicationContext(),interviewList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        rvTips.setLayoutManager(linearLayoutManager);
        rvTips.setAdapter(adapter);

        loadAd();
        interviewTips();

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
                        InterviewActivity.this.interstitialAd = interstitialAd;
                        Toast.makeText(InterviewActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        showInterstitial();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {

                                        InterviewActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                                        InterviewActivity.this.interstitialAd = null;
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
                        interstitialAd = null;

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        /*Toast.makeText(
                                InterviewActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();*/
                    }
                });
    }

    private void showInterstitial() {

        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void interviewTips()
    {
        StringRequest request = new StringRequest(Request.Method.POST, interviewUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                JSONArray cvArray = jsonObject.getJSONArray("tips data");

                                for(int j=0;j<cvArray.length();j++)
                                {
                                    JSONObject commentobj = cvArray.getJSONObject(j);
                                    id = commentobj.getString("id");
                                    tips = commentobj.getString("tips");

                                    Interview interview = new Interview(id,tips);
                                    interviewList.add(interview);

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(InterviewActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InterviewActivity.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}