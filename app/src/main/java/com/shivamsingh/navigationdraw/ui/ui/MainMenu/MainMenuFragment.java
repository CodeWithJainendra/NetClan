package com.shivamsingh.navigationdraw.ui.ui.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.shivamsingh.SellerRegister;
import com.shivamsingh.netclan.R;

public class MainMenuFragment extends Fragment {

    private MainMenuViewModel mainMenuViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainMenuViewModel =
                ViewModelProviders.of(this).get(MainMenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mainmenu, container, false);
        final TextView textView = root.findViewById(R.id.text_mainmenu);

        mainMenuViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        // Find the CardView and set an OnClickListener
        CardView cardView = root.findViewById(R.id.addProduct);
        CardView products=root.findViewById(R.id.showProducts);// Replace with your CardView's ID





        return root;

    }


}
