package com.cvmaster.xosstech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.network.model.ModelResponses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignInPart2 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText editText_OtpCode;
    private Button button_VerifyOTP;
    private Button button_ResendCode;

    private String userMobileNumber,username,type;
    private String sentOTPVarificationId;
    private String password = "123456";
    private String id,token;
    private String regUrl = "http://xosstech.com/cvm/api/public/api/register";
    private String loginUrl = "http://xosstech.com/cvm/api/public/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in_part2);

        editText_OtpCode = (EditText) findViewById(R.id.otpCode_EditText_UserRegistrationPart2);
        editText_OtpCode.addTextChangedListener(editText_OtpCode_Watcher);

        button_VerifyOTP = (Button) findViewById(R.id.verifyOTP_Button_UserRegistrationPart2);
        button_VerifyOTP.setOnClickListener(this);
        button_VerifyOTP.setEnabled(false);

        button_ResendCode = (Button) findViewById(R.id.resendCode_Button_UserRegistrationPart2);
        button_ResendCode.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        sentOTPVarificationId = intent.getStringExtra("OTP_CODE_VARIFICATION_ID");
        userMobileNumber = intent.getStringExtra("MOBILE_NUMBER");
        username = intent.getStringExtra("USERNAME");
        type = intent.getStringExtra("TYPE");

        // Google Play Demo Account

        if(userMobileNumber.equals("01987982903"))
        {
            Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent1);
        }
    }

    private TextWatcher editText_OtpCode_Watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String otpCode = editText_OtpCode.getText().toString().trim();
            button_VerifyOTP.setEnabled(otpCode.length()>5);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void passToPhoneAuthCredential(String otpCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentOTPVarificationId, otpCode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"SUCCESS!",Toast.LENGTH_LONG).show();
                            if(type.equals("registration"))
                            {
                                registration();
                            }
                            else {
                                login();
                            }

//                            check_sub(userMobileNumber);
                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(),"FAILED! ENTER CORRECT OTP!",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == button_VerifyOTP){
            String otpCode = editText_OtpCode.getText().toString().trim();
            passToPhoneAuthCredential(otpCode);
        }

        if (view == button_ResendCode){
            finish();
            Intent intent = new Intent(this,UserSignInPart1.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(this, UserSignInPart1.class);
        startActivity(intent);
    }

    public void check_sub(final String msisdn){
        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                    if (responses.getResponse().equals("UNREGISTERED")) {
                        UnRegisteredDialog(msisdn);
                    }else{
                        finish();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(UserSignInPart2.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserSignInPart2.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void UnRegisteredDialog(final String msisdn){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Your are not subscribed yet. Do you want to subscribe ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        sub(msisdn);
                    }
                });

        builder1.setNegativeButton(
                "Later",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        startActivity(new Intent(UserSignInPart2.this, HomePage.class));
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void sub(String msisdn){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponses> call = service.subscription(msisdn);
        call.enqueue(new Callback<ModelResponses>() {
            @Override
            public void onResponse(Call<ModelResponses> call, Response<ModelResponses> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ModelResponses responses = response.body();
                    if (responses.getResponse().equals("ok")) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(UserSignInPart2.this);
                        builder1.setMessage("You will get a popup notification. Please type 1 and send.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                        startActivity(new Intent(UserSignInPart2.this, HomePage.class));
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }else{

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(UserSignInPart2.this, "Server error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelResponses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserSignInPart2.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void registration()
    {
        StringRequest request = new StringRequest(Request.Method.POST, regUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("user");
                            String status = jsonObject.getString("success");
                            token = jsonObject.getString("token");

                            if (status.equals("true"))
                            {
                                Toast.makeText(UserSignInPart2.this,"Register Scucessfully",Toast.LENGTH_SHORT).show();
                                id = object.getString("id");
                                SharedPreferenceManager.getInstance(UserSignInPart2.this).UserLoggedInfo(userMobileNumber,id,token);
                                Toast.makeText(UserSignInPart2.this, id, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(UserSignInPart2.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserSignInPart2.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name",username);
                params.put("mobile",userMobileNumber);
                params.put("password",password);
                params.put("password_confirmation",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void login()
    {
        StringRequest request = new StringRequest(Request.Method.POST, loginUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("user");
                            String status = jsonObject.getString("success");
                            token = jsonObject.getString("token");

                            if (status.equals("true"))
                            {
                                Toast.makeText(UserSignInPart2.this,"Login Scucessfully",Toast.LENGTH_SHORT).show();
                                id = object.getString("id");
                                SharedPreferenceManager.getInstance(UserSignInPart2.this).UserLoggedInfo(userMobileNumber,id,token);
                                Toast.makeText(UserSignInPart2.this, id, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(UserSignInPart2.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserSignInPart2.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("mobile",userMobileNumber);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
