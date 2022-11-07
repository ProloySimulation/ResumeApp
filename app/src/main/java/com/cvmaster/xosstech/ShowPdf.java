package com.cvmaster.xosstech;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PdfPrint;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.network.model.ModelResponses;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPdf extends AppCompatActivity {

    private WebView mWebView;
    private RewardedAd mRewardedAd;
    private SpinKitView progrssBar ;

    private String mUrl = "";
    public String userMobile,loginNumber,checkPaidOrFree;
    private int adCount;
    private String cvPrice = null;
    private int PERMISSION_REQUEST = 0;
    private boolean allowSave = true;
    private Button btnPrint,btnAdShow;
    private CardView cardPaySim,cardPaybkash,cardPayNagad ;
    private String urlMatch = null;
    private WebSettings webSetting;
    private PDFView cvView ;
    private SharedPreferences sharedPreferences;

    private LinearLayout layoutPayment,webviewLayout ;
    private String paymentSystem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_show_pdf);
        mWebView = findViewById(R.id.web_view);
        btnPrint = findViewById(R.id.btnSavePdf);
        btnAdShow = findViewById(R.id.btnShowAd);
        cvView = findViewById(R.id.cvPdfView);

        cardPaybkash = findViewById(R.id.cardViewPayBkash);
        cardPayNagad = findViewById(R.id.cardPayNagad);
        cardPaySim = findViewById(R.id.cardViewPaySim);
        progrssBar = findViewById(R.id.webview_spin_kit);
        layoutPayment = findViewById(R.id.linearLayoutPayment);

//        userMobile = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();
        adCount = SharedPreferenceManager.getInstance(getApplicationContext()).GetAdCount();
        loginNumber = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setClickable(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setInitialScale(1);

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.clearCache(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);


        renderWebPage("https://xosstech.com/cvm/api/public/view_cv-v2/"+"01987982903");

        btnAdShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adShow();
            }
        });

        cardPaySim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                check_sub("01866942451");
                alertDialogDemo();
            }
        });

        cardPaybkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentSystem = "bkash";
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("AMOUNT", cvPrice);
                fragment = new FragmentPayment();
                FragmentManager fragmentManager = getFragmentManager();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.frameContainerPayment, fragment).addToBackStack(null).commit();
            }
        });

        cardPayNagad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentSystem = "nagad";
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("AMOUNT", cvPrice);
                fragment = new FragmentPayment();
                FragmentManager fragmentManager = getFragmentManager();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.frameContainerPayment, fragment).addToBackStack(null).commit();
            }
        });

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCheck();
            }
        });
    }

    private void dateCheck()
    {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        int lastDay = SharedPreferenceManager.getInstance(getApplicationContext()).GetCurrentDate();

        if(checkPaidOrFree.equals("Free"))
        {
            if(lastDay!=currentDay)
            {
                if(adCount<2)
                {
                    btnPrint.setVisibility(View.GONE);
                    adLoad();
                    SharedPreferenceManager.getInstance(getApplicationContext()).SetAdCount(adCount++);
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferenceManager.getInstance(getApplicationContext()).SetAdCount(0);
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                    SharedPreferenceManager.getInstance(getApplicationContext()).SetCurrentDate(currentDay);
                    savePdf();
                }
            }
            else
            {
                btnPrint.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "YO", Toast.LENGTH_SHORT).show();
                savePdf();
            }
        }
        else
        {
            savePdf();
        }
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    @Override
    public void onBackPressed() {

        String paymentCheck = null;

        sharedPreferences = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        paymentCheck = sharedPreferences.getString("payment",null);

        if(paymentCheck!=null)
        {
            if(paymentCheck.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "Payment Has Done Successfully", Toast.LENGTH_SHORT).show();
                checkPaidOrFree = "Paid";
                btnPrint.setVisibility(View.VISIBLE);
            }

        }

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (mWebView.canGoBack())
            {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void renderWebPage(String urlToRender){

        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progrssBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url){

                progrssBar.setVisibility(View.GONE);
                mUrl = view.getUrl();
                cvPrice = view.getTitle();

                if(mUrl.contains("https://xosstech.com/cvm/api/public/resume"))
                {
                    if(cvPrice.equals("Free"))
                    {
                        btnPrint.setVisibility(View.VISIBLE);
                        checkPaidOrFree = "Free";
                    }

                    else {
                        btnPrint.setVisibility(View.GONE);
                        layoutPayment.setVisibility(View.VISIBLE);
                    }
                }
                else if (mUrl.contains("https://xosstech.com/cvm/api/public/view_cv"))
                {
                    layoutPayment.setVisibility(View.GONE);
                }

            }

        });

        // Enable the javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        mWebView.loadUrl(urlToRender);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void savePdf() {
        if (!allowSave)
            return;
        allowSave = false;
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {
            String fileName = String.format("%s.pdf", new SimpleDateFormat("dd_MM_yyyyHH_mm_ss", Locale.US).format(new Date()));
            final PrintDocumentAdapter printAdapter = mWebView.createPrintDocumentAdapter(fileName);
            PrintAttributes printAttributes = new PrintAttributes.Builder()
                    .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                    .setResolution(new PrintAttributes.Resolution("pdf", "pdf", 300, 72))
                    .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                    .build();
            String PATH = Environment.getExternalStorageDirectory() + "/download/";
            final File file = new File(PATH);
            new PdfPrint(printAttributes).print(
                    printAdapter,
                    file,
                    fileName,
                    new PdfPrint.CallbackPrint() {
                        @Override
                        public void onSuccess(String path) {
                            allowSave = true;
                            Toast.makeText(getApplicationContext(),
                                    String.format("Your file is saved in Download Folder", path),
                                    Toast.LENGTH_LONG).show();
                            btnPrint.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            allowSave = true;
                            Toast.makeText(getApplicationContext(),
                                    String.format("Exception while saving the file and the exception is %s", ex.getMessage()),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults[Arrays.asList(permissions).indexOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)] == PERMISSION_GRANTED) {
                savePdf();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void adLoad()
    {
        progrssBar.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-7854798461578735/2249839374",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
                        progrssBar.setVisibility(View.GONE);
                        /*btnAdShow.setVisibility(View.GONE);
                        btnPrint.setVisibility(View.VISIBLE);*/
                        savePdf();
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        progrssBar.setVisibility(View.GONE);
                        savePdf();
                    }
                });
    }

    private void adShow()
    {
        if (mRewardedAd != null) {
            Activity activityContext = ShowPdf.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    btnPrint.setVisibility(View.VISIBLE);
                    btnAdShow.setVisibility(View.GONE);
                    Log.e("rewardcheck",rewardItem.getType().toString());
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "The rewarded ad wasn't ready yet.", Toast.LENGTH_SHORT).show();
        }
    }



    // BDAPPS Payment  ////////////////////////////////


    void alertDialogDemo() {
        // get alert_dialog.xml view
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.alert_bd_apps, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ShowPdf.this);

        // set alert_dialog.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        EditText userInput = (EditText) promptsView.findViewById(R.id.etBdAppsNumber);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        // edit text
                        userMobile = userInput.getText().toString();
                        check_sub(userMobile);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void check_sub(final String msisdn){

        final ProgressDialog progressDialog = new ProgressDialog(ShowPdf.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.check_subscription(msisdn);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, retrofit2.Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelResponses responses = response.body();
//                    Toast.makeText(ChargingActivity.this, responses.getResponse().toString(), Toast.LENGTH_SHORT).show();
//                    Log.d("yo",responses.getResponse().toString());
                    if (responses.getResponse().equals("null") || responses.getResponse().equals("UNREGISTERED")) {
                        UnRegisteredDialog(msisdn);
                    }else{
                        charging(msisdn);
                    }
                } else {
                    progressDialog.dismiss();

                    Toast.makeText(ShowPdf.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ShowPdf.this, call.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void UnRegisteredDialog(final String msisdn){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowPdf.this);
        builder1.setMessage("Your are not subscribed yet. Do you want to subscribe ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        sub(msisdn);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //startActivity(new Intent(ChargingActivity.this,UserProfileActivity.class));
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void sub(String msisdn){
        final ProgressDialog progressDialog = new ProgressDialog(ShowPdf.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.subscription(msisdn);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, final retrofit2.Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelResponses responses = response.body();
                    if (responses.getResponse().equals("ok")) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowPdf.this);
                        builder1.setMessage("You will get a popup notification. Please type 1 and send.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        /*ModelResponses responses = response.body();
                                        Toast.makeText(ChargingActivity.this, responses.getResponse().toString(), Toast.LENGTH_SHORT).show();
                                        Log.d("response", responses.getResponse().toString());*/
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }else{
                        Toast.makeText(ShowPdf.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ShowPdf.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ShowPdf.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void charging(final String msisdn){
        final ProgressDialog progressDialog = new ProgressDialog(ShowPdf.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.charging(msisdn,cvPrice);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelResponses responses = response.body();
                    if (responses.getResponse().equals("not_subscribe")) {
                        UnRegisteredDialog(msisdn);
                    }else{
                        if(responses.getResponse().equals("charged_successfull")){
                            btnPrint.setVisibility(View.VISIBLE);
                            Toast.makeText(ShowPdf.this, "Charged Successfully", Toast.LENGTH_SHORT).show();


                        }else if(responses.getResponse().equals("charged_unsuccessfull")){
                            Toast.makeText(ShowPdf.this, "Please check your balance", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ShowPdf.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ShowPdf.this, call.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}