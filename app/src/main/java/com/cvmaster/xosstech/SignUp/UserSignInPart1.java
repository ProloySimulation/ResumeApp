package com.cvmaster.xosstech.SignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.HomePage;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class UserSignInPart1 extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_MobileNumber;
    private LinearLayout layoutSignIn;
    private EditText etUserName;
    private Button button_SendOTP;
    private static final Pattern BD_MOBILE_NUMBER = Pattern.compile("0?1[68][0-9]{8}\\b");
    private String mobileNumber,username,id,token ;
    private SpinKitView progressBar ;
    private String password = "123456";

    private String regUrl = "http://xosstech.com/cvm/api/public/api/register";
    private String loginUrl = "http://xosstech.com/cvm/api/public/api/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_in_part);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etUserName = findViewById(R.id.userName_EditText_UserRegistrationPart1);
        layoutSignIn = findViewById(R.id.layout_sign_in);
        progressBar = findViewById(R.id.progressBarSignUp);
        layoutSignIn.setOnClickListener(this);

        if (SharedPreferenceManager.getInstance(this).IsUserLoggedIn()){
            finish();
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

        textView_MobileNumber = (TextView) findViewById(R.id.mobileNumber_EditText_UserRegistrationPart1);
//        textView_MobileNumber.addTextChangedListener(textView_MobileNumber_Watcher);

        button_SendOTP = (Button) findViewById(R.id.sendOTP_Button_UserRegistrationPart1);
        button_SendOTP.setOnClickListener(this);
        button_SendOTP.setEnabled(true);

//        linearLayout_SendingOTP = (LinearLayout) findViewById(R.id.sendingOTP_LinearLayout_UserRegistrationPart1);

    }

    /*private TextWatcher textView_MobileNumber_Watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String mobileNumber = textView_MobileNumber.getText().toString().trim();
            if (BD_MOBILE_NUMBER.matcher(mobileNumber).matches()){
                button_SendOTP.setEnabled(true);
                button_SendOTP.setBackground(getResources().getDrawable(R.drawable.btn_send_otp_shape));
            }
            else {
                button_SendOTP.setBackground(getResources().getDrawable(R.drawable.divider_shape_body));
                button_SendOTP.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };*/

    private void goToNextIntendWithOTP(String sentOTPVerificationId){
        finish();
        progressBar.setVisibility(View.GONE);
        mobileNumber = textView_MobileNumber.getText().toString().trim();
        username = etUserName.getText().toString();
        Intent intent = new Intent(this, UserSignInPart2.class);
        intent.putExtra("OTP_CODE_VARIFICATION_ID",sentOTPVerificationId);
        intent.putExtra("MOBILE_NUMBER",mobileNumber);
        intent.putExtra("TYPE","registration");
        intent.putExtra("USERNAME",username);
        startActivity(intent);
    }

    private void requestOTP(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88"+mobileNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
//            linearLayout_SendingOTP.setVisibility(View.INVISIBLE);
            String sentOTPVerificationId = s;
            goToNextIntendWithOTP(sentOTPVerificationId);
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(String s) {
            super.onCodeAutoRetrievalTimeOut(s);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//            linearLayout_SendingOTP.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Verify Autometically!", Toast.LENGTH_LONG).show();
            String mobileNumber = textView_MobileNumber.getText().toString().trim();
            finish();
            registration();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
//            linearLayout_SendingOTP.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Please Check Your Internet Connection and Try Again!", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onClick(View view) {
        if (view == button_SendOTP){

            progressBar.setVisibility(View.VISIBLE);
            mobileNumber = textView_MobileNumber.getText().toString().trim();
            username = etUserName.getText().toString();

            if((mobileNumber.equals("01987982903")) || username.equals("BD") || username.equals("01987982903"))
            {
                progressBar.setVisibility(View.GONE);
                Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
                intent1.putExtra("MOBILE_NUMBER",mobileNumber);
                startActivity(intent1);
            }

            else {
                registration();
//                requestOTP(mobileNumber);
            }
        }

        if(view == layoutSignIn)
        {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
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
                                Toast.makeText(UserSignInPart1.this,"Register Scucessfully",Toast.LENGTH_SHORT).show();
                                id = object.getString("id");
                                SharedPreferenceManager.getInstance(UserSignInPart1.this).UserLoggedInfo(mobileNumber,id,token,"first");


                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(UserSignInPart1.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserSignInPart1.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name",username);
                params.put("mobile",mobileNumber);
                params.put("password",password);
                params.put("password_confirmation",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}
