package com.cvmaster.xosstech;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.Fragment;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentPayment extends Fragment {

    private WebView webView ;
    private SpinKitView progressBar ;

    private String bkashUrl = "https://xosstech.com/Payment/bkash/payment.php?cv_id=&user_id=";

    private String nagadUrl = null;

    private SharedPreferences sharedPreferences ;
    private String paymentSystem = null;
    private String userId,amount ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ShowPdf activity = (ShowPdf) getActivity();
        paymentSystem = activity.getPaymentSystem();

        View rootView =  inflater.inflate(R.layout.fragment_playment, container, false);

        userId = SharedPreferenceManager.getInstance(getActivity()).GetUserId();
        amount = getArguments().getString("AMOUNT");

        webView = rootView.findViewById(R.id.webviewPayment);
        progressBar = rootView.findViewById(R.id.payment_progress);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setInitialScale(1);

        if(paymentSystem.equals("nagad"))
        {
            nagadCallBack();
        }

        if(paymentSystem.equals("bkash"))
        {
            renderWebPage(bkashUrl+userId+"&id=CVM"+amount+"ABC");
        }

        return rootView;
    }

    protected void renderWebPage(String urlToRender){

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){

                String mUrl = view.getUrl();

                if(mUrl.equals("https://xosstech.com/Payment/php/payment.php"))
                {
                    webView.loadUrl("javascript:clickPayButton()");
                }

                progressBar.setVisibility(View.GONE);

                sharedPreferences = getActivity().getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                if(mUrl.equals("https://xosstech.com/Payment/bkash/success.html") ||
                        mUrl.equals("https://xosstech.com/Payment/nagad/success.php"))
                {
                    editor.putString("payment","1");
                    editor.commit();
                    getActivity().onBackPressed();
                    getActivity().getFragmentManager().beginTransaction().remove(FragmentPayment.this).commit();
                }

                else
                {
                    editor.putString("payment","0");
                    editor.commit();
                }
            }

        });

        // Enable the javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        webView.loadUrl(urlToRender);
    }

    private void nagadCallBack()
    {
        StringRequest request = new StringRequest(Request.Method.POST, "https://xosstech.com/Payment/nagad/index.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responsee",response.toString());
                        String jsonRes = response.toString();
                        extractUrl(jsonRes);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("amount", amount);
                params.put("user_id",userId);
                params.put("cv_id","android");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void extractUrl(String string) {

        List<String> list = new ArrayList<>();
        String regexString = "(https://|www[.])[A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]*[out]*[=]";
        Pattern pattern = Pattern.compile(regexString,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            list.add(string.substring(matcher.start(0),matcher.end(0)));
        }
        for(String str:list)
        {
            nagadUrl = str;
        }
        renderWebPage(nagadUrl);
            Log.e("weburl",nagadUrl);
    }



}