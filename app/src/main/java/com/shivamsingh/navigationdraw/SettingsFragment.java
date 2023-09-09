package com.shivamsingh.navigationdraw;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.shivamsingh.Activities.BuisenessActivity;
import com.shivamsingh.Activities.Creativepreneur;
import com.shivamsingh.Activities.EnterringOTP;
import com.shivamsingh.Activities.MerchantActivity;
import com.shivamsingh.InsideActivity;
import com.shivamsingh.SellerRegister;
import com.shivamsingh.netclan.R;
import com.shivamsingh.netclan.SplashScreen;
import com.shivamsingh.netclan.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();

        binding.BuyerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MerchantActivity.class));
            }
        });

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertMessageLogout();
            }
        });

        binding.notificationsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Creativepreneur.class));
            }
        });



        return binding.getRoot();
    }

    private void AlertMessageLogout() {

        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(getActivity());
        dialog.setTitle("Task Status")
                .setIcon(R.drawable.ic_warning)
                .setMessage("Do You want Logout")
                .setPositiveButton("Ok", (dialog1, which) -> LogOut())
                .setNegativeButton("Cancel", (dialoginterface, i) -> dialoginterface.cancel()).show();

    }

    private void LogOut() {
        mAuth.signOut();
        startActivity(new Intent(getActivity(), SplashScreen.class));
        getActivity().finish();
    }


   }