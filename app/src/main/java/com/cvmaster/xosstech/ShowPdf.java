package com.cvmaster.xosstech;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PdfPrint;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.InputActivities.BuildResumePart2;
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

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPdf extends AppCompatActivity {

    private WebView mWebView;
    private RewardedAd mRewardedAd;
    private SpinKitView progrssBar ;

    private String mUrl = "";
    private String userMobile = null;
    private String cvPrice = null;
    private int PERMISSION_REQUEST = 0;
    private boolean allowSave = true;
    private Button btnPrint,btnAdShow;
    private CardView cardPaySim,cardPaybkash,cardPayNagad ;
    private String urlMatch = null;
    private WebSettings webSetting;
    private SharedPreferences sharedPreferences;

    private LinearLayout layoutPayment ;
    private String paymentSystem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);
        mWebView = findViewById(R.id.web_view);
        btnPrint = findViewById(R.id.btnSavePdf);
        btnAdShow = findViewById(R.id.btnShowAd);

        cardPaybkash = findViewById(R.id.cardViewPayBkash);
        cardPayNagad = findViewById(R.id.cardPayNagad);
        cardPaySim = findViewById(R.id.cardViewPaySim);
        progrssBar = findViewById(R.id.webview_spin_kit);
        layoutPayment = findViewById(R.id.linearLayoutPayment);

//        userMobile = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();

        btnPrint.setVisibility(View.GONE);
        btnAdShow.setVisibility(View.GONE);


        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.setInitialScale(1);

        webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);

        renderWebPage("https://xosstech.com/cvm/api/public/view_cv");
        adLoad();

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePdf();
            }
        });

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
                fragment = new FragmentPlayment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameContainerPayment, fragment).addToBackStack(null).commit();
            }
        });

        cardPayNagad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentSystem = "nagad";
                Fragment fragment = null;
                fragment = new FragmentPlayment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameContainerPayment, fragment).addToBackStack(null).commit();
            }
        });
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    @Override
    public void onBackPressed() {

        String paymentCheck = "0";

        sharedPreferences = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        paymentCheck = sharedPreferences.getString("payment",null);

        if(paymentCheck.equals("1"))
        {
            btnPrint.setVisibility(View.VISIBLE);
        }

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    protected void renderWebPage(String urlToRender){

        mWebView.setWebViewClient(new WebViewClient(){
            /*
                public void onPageStarted (WebView view, String url, Bitmap favicon)
                    Notify the host application that a page has started loading. This method is
                    called once for each main frame load so a page with iframes or framesets will
                    call onPageStarted one time for the main frame. This also means that
                    onPageStarted will not be called when the contents of an embedded frame changes,
                    i.e. clicking a link whose target is an iframe, it will also not be called for
                    fragment navigations (navigations to #fragment_id).

                Parameters
                    view : The WebView that is initiating the callback.
                    url : The url to be loaded.
                    favicon : The favicon for this page if it already exists in the database.

            */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started

                /*
                    public String getUrl ()
                        Gets the URL for the current page. This is not always the same as the URL
                        passed to WebViewClient.onPageStarted because although the load for that
                        URL has begun, the current page may not have changed.

                    Returns
                        the URL for the current page
                */
                // Only url is available in this stage
                progrssBar.setVisibility(View.VISIBLE);
                // Update the action bar
            }

            /*
                public void onPageFinished (WebView view, String url)
                    Notify the host application that a page has finished loading. This method is
                    called only for main frame. When onPageFinished() is called, the rendering
                    picture may not be updated yet. To get the notification for the new Picture,
                    use onNewPicture(WebView, Picture).

                Parameters
                    view : The WebView that is initiating the callback.
                    url : The url of the page.
            */
            @Override
            public void onPageFinished(WebView view, String url){

                progrssBar.setVisibility(View.GONE);
                mUrl = view.getUrl();
                cvPrice = view.getTitle();

                if(mUrl.contains("https://xosstech.com/cvm/api/public/resume"))
                {
                    if(cvPrice.equals("Free"))
                    {
                        btnAdShow.setVisibility(View.VISIBLE);
                    }

                    else {
                        layoutPayment.setVisibility(View.VISIBLE);
                    }
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
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-7854798461578735/4913309588",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
//                        Log.d(TAG, "Ad was loaded.");
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

        final EditText userInput = (EditText) promptsView.findViewById(R.id.etBdAppsNumber);

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
                Log.d("proloy",call.toString());
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
        Call<ModelResponses> call = service.charging(msisdn,"2.0");
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
                            btnAdShow.setVisibility(View.GONE);
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