package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class UserSignInPart1 extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_MobileNumber;
    private EditText etUserName;
    private Button button_SendOTP;
//    private LinearLayout linearLayout_SendingOTP;
    private static final Pattern BD_MOBILE_NUMBER = Pattern.compile("0?1[68][0-9]{8}\\b");
    private String mobileNumber,username ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_in_part);

        etUserName = findViewById(R.id.userName_EditText_UserRegistrationPart1);

        //comment the line
        //SharedPreferenceManager.getInstance(this).UserLoggedInfo("01676946820");

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
        button_SendOTP.setBackground(getResources().getDrawable(R.drawable.divider_shape_body));

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
        mobileNumber = textView_MobileNumber.getText().toString().trim();
        username = etUserName.getText().toString();
        Intent intent = new Intent(this,UserSignInPart2.class);
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
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
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
//            linearLayout_SendingOTP.setVisibility(View.VISIBLE);
            mobileNumber = textView_MobileNumber.getText().toString().trim();
            requestOTP(mobileNumber);
        }
    }
}
