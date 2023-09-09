package com.shivamsingh.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shivamsingh.netclan.R;

import java.util.concurrent.TimeUnit;

public class EnterringOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterring_otp);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final EditText inputmobile =findViewById(R.id.inputmobile);
        Button requestotp =findViewById(R.id.requestotp);



        requestotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputmobile.getText().toString().trim().isEmpty()) {
                    if ((inputmobile.getText().toString().trim()).length() == 10) {


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + inputmobile.getText().toString(), 60, TimeUnit.SECONDS, EnterringOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        Toast.makeText(EnterringOTP.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        requestotp.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), Enterotp.class);
                                        Toast.makeText(EnterringOTP.this, "Code has been Sent !", Toast.LENGTH_SHORT).show();
                                        intent.putExtra("mobile", inputmobile.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                });


                    } else {
                        Toast.makeText(EnterringOTP.this, "Please Enter Correct Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    Toast.makeText(EnterringOTP.this,"Enter Mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}