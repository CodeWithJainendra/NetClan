package com.shivamsingh.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.shivamsingh.navigationdraw.CategoryFragment;
import com.shivamsingh.navigationdraw.SettingsFragment;
import com.shivamsingh.navigationdraw.ui.ui.MainMenu.MainMenuFragment;
import com.shivamsingh.netclan.R;
import com.shivamsingh.netclan.databinding.ActivityBuisenessBinding;

public class BuisenessActivity extends AppCompatActivity {

    ActivityBuisenessBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuisenessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(BuisenessActivity.this, "Created By Jainendra Singh", Toast.LENGTH_LONG).show();

        bottomMenu();

//        binding.BottomView.setOnNavigationItemSelectedListener(navListner);
        binding.BottomView.setItemSelected(R.id.nav_home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenuFragment()).commit();
    }

    private void bottomMenu() {
        binding.BottomView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment selectedFragment = null;
                if (i == R.id.nav_home) {
                    selectedFragment = new MainMenuFragment();
                } else if (i == R.id.nav_category) {
                    selectedFragment = new CategoryFragment();
                } else if (i == R.id.nav_settings) {
                    selectedFragment = new SettingsFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
        });
    }





    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(BuisenessActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}