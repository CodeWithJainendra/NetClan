package com.shivamsingh.netclan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.shivamsingh.onboarding.onboarding.IntroActivity;

public class SecondWelcome extends AppCompatActivity {
    Button button;

    String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = findViewById(R.id.btn_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity
                Intent mainActivity = new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

    }
    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}