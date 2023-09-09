package com.shivamsingh.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shivamsingh.Login;
import com.shivamsingh.netclan.R;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class Enterotp extends AppCompatActivity {


    private EditText action1, action2, action3, action4, action5, action6;
    private Button dummy_button;
    String backendotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entering_otpp);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        TextView textMobile = findViewById(R.id.textmobilee);
        textMobile.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")
        ));


        backendotp = getIntent().getStringExtra("backendotp");


        action1 = findViewById(R.id.action1);
        action2 = findViewById(R.id.action2);
        action3 = findViewById(R.id.action3);
        action4 = findViewById(R.id.action4);
        action5 = findViewById(R.id.action5);
        action6 = findViewById(R.id.action6);
        dummy_button = findViewById(R.id.dummy_button);


        dummy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!action1.getText().toString().trim().isEmpty() && !action2.getText().toString().trim().isEmpty() && !action3.getText().toString().trim().isEmpty() && !action4.getText().toString().trim().isEmpty() && !action5.getText().toString().trim().isEmpty() && !action6.getText().toString().trim().isEmpty()) {
                    String entercodeotp = action1.getText().toString() +
                            action2.getText().toString() +
                            action3.getText().toString() +
                            action4.getText().toString() +
                            action5.getText().toString() +
                            action6.getText().toString();

                    if (backendotp != null) {


                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendotp, entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Enterotp.this, "Enter Correct Otp", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        Toast.makeText(Enterotp.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Enterotp.this, "Please Enter all Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setupotpinput();

        TextView resendlabel = findViewById(R.id.textResendotp);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, Enterotp.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(Enterotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                                backendotp = newbackendotp;
                                Toast.makeText(Enterotp.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }

    public void setupotpinput() {
        action1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    action2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        action2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    action3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        action3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    action4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        action4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    action5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        action5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    action6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}