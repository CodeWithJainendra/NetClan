package com.shivamsingh.navigationdraw.ui.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shivamsingh.navigationdraw.ui.ui.dashboard.DashboardViewModel;
import com.shivamsingh.netclan.R;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private EditText productNameEditText, productDescriptionEditText;
    private Button saveButton;

    private FirebaseFirestore firestore;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize UI elements
        productNameEditText = view.findViewById(R.id.txtproduct_name);
        productDescriptionEditText = view.findViewById(R.id.txtdescription);
        saveButton = view.findViewById(R.id.btnaddproduct);

        // Set an OnClickListener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the product name and description from EditText fields
                String productName = productNameEditText.getText().toString().trim();
                String productDescription = productDescriptionEditText.getText().toString().trim();

                // Check if the fields are empty
                if (productName.isEmpty() || productDescription.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new product object
                Map<String, Object> product = new HashMap<>();
                product.put("productName", productName);
                product.put("productDescription", productDescription);

                // Add the product to Firestore
                firestore.collection("products")
                        .add(product)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                                    // Clear the input fields after successful addition
                                    productNameEditText.setText("");
                                    productDescriptionEditText.setText("");
                                } else {
                                    Toast.makeText(getContext(), "Error adding product", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
