package com.cvmaster.xosstech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.GeneratePdf.PdfActivityTemplate1;
import com.cvmaster.xosstech.GeneratePdf.PdfActivityTemplate2;
import com.cvmaster.xosstech.GeneratePdf.PdfActivityTemplate4;
import com.cvmaster.xosstech.GeneratePdf.PdfActivityTemplate5;
import com.cvmaster.xosstech.GeneratePdf.PdfActivityTemplate6;
import com.cvmaster.xosstech.model.ResumeTemplate;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.network.model.ModelResponses;
import com.cvmaster.xosstech.templete.Template3_pdf;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargingActivity extends AppCompatActivity {

    private Button button_Done;
    private float CHARGE;
    private TextView textView_chargeStatus;
    private DatabaseReference reference ;
    private String downloadCount;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_activity);
        textView_chargeStatus = (TextView) findViewById(R.id.chargeStatus);
        reference = FirebaseDatabase.getInstance().getReference().child("count");

        //Toast.makeText(this,ResumeTemplate.templateName,Toast.LENGTH_LONG).show();

        CHARGE = (float) 10.0;

        if (ResumeTemplate.templateName == "template1"){
            CHARGE = (float) 10.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 10(+VAT) To Complete Your Resume");
        } else if (ResumeTemplate.templateName == "template2"){
            CHARGE = (float) 20.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 20(+VAT) To Complete Your Resume");
        } else if (ResumeTemplate.templateName == "template3"){
            CHARGE = (float) 20.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 20(+VAT) To Complete Your Resume");
        }
        else if (ResumeTemplate.templateName == "template4"){
            CHARGE = (float) 10.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 10(+VAT) To Complete Your Resume");
        }
        else if (ResumeTemplate.templateName == "template5"){
            CHARGE = (float) 20.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 20(+VAT) To Complete Your Resume");
        }
        else if (ResumeTemplate.templateName == "template6"){
            CHARGE = (float) 20.0;
            textView_chargeStatus.setText("You Will Be Charged BDT 20(+VAT) To Complete Your Resume");
        }


        //CHARGE = (float) 1.0;

        //Toast.makeText(this,"CHARGE: "+CHARGE,Toast.LENGTH_LONG).show();

        button_Done = (Button) findViewById(R.id.button_Done);

        button_Done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             /*  String msisdn = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserMobileNumber();
                check_sub(msisdn);*/

                //for test
                intent_to_next();
            }
        });
        
    }

    public void check_sub(final String msisdn){

        final ProgressDialog progressDialog = new ProgressDialog(ChargingActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.check_subscription(msisdn);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                   progressDialog.dismiss();
                    ModelResponses responses = response.body();
//                    Toast.makeText(ChargingActivity.this, responses.getResponse().toString(), Toast.LENGTH_SHORT).show();
//                    Log.d("yo",responses.getResponse().toString());
                    if (responses.getResponse().equals("UNREGISTERED") || responses.getResponse().equals("null") ) {
                        UnRegisteredDialog(msisdn);
                    }else{
                        charging(msisdn);
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ChargingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChargingActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void UnRegisteredDialog(final String msisdn){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ChargingActivity.this);
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
        final ProgressDialog progressDialog = new ProgressDialog(ChargingActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.subscription(msisdn);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, final Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelResponses responses = response.body();
                    if (responses.getResponse().equals("ok")) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ChargingActivity.this);
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
                        Toast.makeText(ChargingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ChargingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChargingActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void charging(final String msisdn){
        final ProgressDialog progressDialog = new ProgressDialog(ChargingActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.charging(msisdn,String.valueOf(CHARGE));
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
                            intent_to_next();
                        }else if(responses.getResponse().equals("charged_unsuccessfull")){
                            Toast.makeText(ChargingActivity.this, "Please check your balance", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ChargingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChargingActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Here the PDF number will be check
    public void intent_to_next(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("You have been charged successfully");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Proceed",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        if (ResumeTemplate.templateName == "template1"){

                            startActivity(new Intent(ChargingActivity.this, PdfActivityTemplate1.class));
                            cheack("template1");

                        } else if(ResumeTemplate.templateName == "template2"){

                            startActivity(new Intent(ChargingActivity.this, PdfActivityTemplate2.class));
                            cheack("template2");

                        } else if (ResumeTemplate.templateName == "template3"){

                            startActivity(new Intent(ChargingActivity.this, Template3_pdf.class));
                            cheack("template3");

                        }
                        else if (ResumeTemplate.templateName == "template4"){

                            startActivity(new Intent(ChargingActivity.this, PdfActivityTemplate4.class));
                            cheack("template4");

                        }
                        else if (ResumeTemplate.templateName == "template5"){

                            startActivity(new Intent(ChargingActivity.this, PdfActivityTemplate5.class));
                            cheack("template5");

                        }

                        else if (ResumeTemplate.templateName == "template6"){

                            startActivity(new Intent(ChargingActivity.this, PdfActivityTemplate6.class));
                            cheack("template6");

                        }
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT!");
        builder.setMessage("Do You Want To Exit From Make Resume?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToHomeIntent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void goToHomeIntent(){
        finish();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void cheack(final String templateName) {

        reference.child(templateName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                downloadCount = dataSnapshot.child("count").getValue().toString();
                count = Integer.parseInt(downloadCount);
                count++ ;
                reference.child(templateName).child("count").setValue(count);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
