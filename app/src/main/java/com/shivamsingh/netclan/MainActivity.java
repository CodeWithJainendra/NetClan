package com.shivamsingh.netclan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.shivamsingh.Activities.Creativepreneur;
import com.shivamsingh.Activities.Enterotp;
import com.shivamsingh.Activities.EnterringOTP;
import com.shivamsingh.Activities.Innovative;
import com.shivamsingh.Activities.MainNavigation;
import com.shivamsingh.Activities.PersonalActivity;
import com.shivamsingh.AdminPanel.AdminHomeActivity;
import com.shivamsingh.Login;
import com.shivamsingh.Register;
import com.shivamsingh.SellerRegister;
import com.shivamsingh.netclan.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseAuth mAuth;
    FirebaseDatabase database;

    SharedPreferences onBordingScreen;

    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.menu, R.drawable.cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.homee)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.merchantt)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.community)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.creativepre)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.innovate)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {

                        switch (index) {
                            case 0: // Home
                                openHomeActivity();
                                break;
                            case 1: // Search
                                openSearchActivity();
                                break;
                            case 2: // Notification
                                openNotificationActivity();
                                break;
                            case 3: // Settings
                                openSettingsActivity();
                                break;
                            case 4: // Gps
                                openGpsActivity();
                                break;
                        }
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                    @Override
                    public void onMenuOpened() {
                    }

                    @Override
                    public void onMenuClosed() {
                    }

                });
    }

    private void openHomeActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), EnterringOTP.class);
                startActivity(intent);
            }
        }, 1600);
    }

    private void openSearchActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        }, 1600);
    }

    private void openNotificationActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String url = "https://www.example.com"; // Replace with your desired URL

                // Create an Intent to open a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Check if there's a web browser available to handle the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the web browser activity
                    startActivity(intent);
                } else {
                    // Handle the case where no web browser is available
                    Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection !", Toast.LENGTH_SHORT).show();

                    // You can display a message to the user or take other action
                }
            }
        }, 1600);

    }

    private void openSettingsActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                Intent intent = new Intent(MainActivity.this, Innovative.class);
                startActivity(intent);
            }
        }, 1600);
    }
    private void openGpsActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        }, 1600);
    }



    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        circleMenu.openMenu();
        return super.onMenuOpened(featureId, menu);
    }

    public void onBackPressed() {
        if (circleMenu.isOpened()) {
            // If the circle menu is open, close it
            circleMenu.closeMenu();
        } else {
            // If the circle menu is not open, show an exit confirmation dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to exit?");
            builder.setTitle("Alert !");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                // When the user clicks "Yes," the app will close
                finish();
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                // If the user clicks "No," the dialog box is canceled
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

}
