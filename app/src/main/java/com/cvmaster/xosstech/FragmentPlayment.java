package com.cvmaster.xosstech;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.Fragment;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;


public class FragmentPlayment extends Fragment {

    private WebView webView ;
    private SpinKitView progressBar ;

    private String bkashUrl = "https://xosstech.com/Payment/php/payment.php";
    private String nagadUrl = "https://xosstech.com/Payment/nagad";

    private SharedPreferences sharedPreferences ;
    private String paymentSystem = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ShowPdf activity = (ShowPdf) getActivity();
        paymentSystem = activity.getPaymentSystem();

        View rootView =  inflater.inflate(R.layout.fragment_playment, container, false);

        webView = rootView.findViewById(R.id.webviewPayment);
        progressBar = rootView.findViewById(R.id.payment_progress);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setInitialScale(1);

        if(paymentSystem.equals("nagad"))
        {
            renderWebPage(nagadUrl);
        }

        if(paymentSystem.equals("bkash"))
        {
            renderWebPage(bkashUrl);
        }

        return rootView;
    }

    protected void renderWebPage(String urlToRender){

        webView.setWebViewClient(new WebViewClient(){
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

                progressBar.setVisibility(View.VISIBLE);
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

                progressBar.setVisibility(View.GONE);

                sharedPreferences = getActivity().getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // https://xosstech.com/Payment/php/success.html

                String mUrl = view.getUrl();

                if(mUrl.equals("https://xosstech.com/Payment/php/success.html") ||
                        mUrl.equals("https://xosstech.com/Payment/nagad/success.php"))
                {
                    editor.putString("payment","1");
                    editor.commit();
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

}