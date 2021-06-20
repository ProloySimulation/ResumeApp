package com.cvmaster.xosstech.GeneratePdf;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.cvmaster.xosstech.R;

public class LoadingDialog {

    Activity activity ;
    AlertDialog alertDialog ;

    LoadingDialog(Activity pdfActivity)
    {
        activity = pdfActivity;
    }

    public void startloadingDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_layout, null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dissmissDialog()
    {
        alertDialog.dismiss();
    }
}
