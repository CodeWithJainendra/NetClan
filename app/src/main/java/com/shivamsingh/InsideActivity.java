package com.shivamsingh;

import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.shivamsingh.model.Product;
import com.shivamsingh.netclan.R;

public class InsideActivity extends AppCompatActivity {

    private EditText productNameEditText, productDescriptionEditText, initialPriceEditText, penaltyPriceEditText;
    private Switch penaltySwitch;
    private TextView totalPriceTextView;
    private Button imageChooserButton, submitButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        // Initialize Firebase Firestore
    }
}
