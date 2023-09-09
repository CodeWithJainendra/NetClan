package com.shivamsingh.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamsingh.netclan.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MerchantActivity extends AppCompatActivity {

    private EditText productNameEditText;
    private EditText productDescriptionEditText;
    private TextView priceEditText;
    private EditText penaltyAmountEditText; // New field for penalty input
    private CheckBox penaltyCheckBox;
    private RadioButton morningRadioButton;
    private RadioGroup shiftRadioGroup;

    private RadioButton eveningRadioButton;
    private RadioButton nightRadioButton;
    private Button submitButton;

    private Spinner productTypeSpinner;
    private String selectedProductType = "";
    private ArrayAdapter<String> spinnerAdapter;

    // Define an array of product types and their prices for the Spinner
    private String[] productTypes = {"Car", "Bus", "Train", "Aeroplane"};
    private double[] productPrices = {90.0, 40.0, 60.0, 100.0};

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        productTypeSpinner = findViewById(R.id.productTypeSpinner);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productTypes);
        productTypeSpinner.setAdapter(spinnerAdapter);
        shiftRadioGroup = findViewById(R.id.shiftRadioGroup);


        morningRadioButton = findViewById(R.id.morningRadioButton);
        eveningRadioButton = findViewById(R.id.eveningRadioButton);
        nightRadioButton = findViewById(R.id.nightRadioButton);

        // Initialize UI elements
        productNameEditText = findViewById(R.id.productNameEditText);
        productDescriptionEditText = findViewById(R.id.productDescriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        penaltyAmountEditText = findViewById(R.id.penaltyAmountEditText);
        penaltyCheckBox = findViewById(R.id.penaltyCheckBox);
        submitButton = findViewById(R.id.submitButton);

        productTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection here
                selectedProductType = spinnerAdapter.getItem(position);
                // Calculate and set the price based on the selected product type
                calculateAndSetPrice(selectedProductType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected (if needed)
            }
        });

        // Set an onClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }
        });

        // Set an onCheckedChangeListener for the penalty checkbox to toggle visibility
        penaltyCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            penaltyAmountEditText.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
    }

    private void calculateAndSetPrice(String selectedProductType) {
        // Find the corresponding price for the selected product type
        double price = 0.0;
        for (int i = 0; i < productTypes.length; i++) {
            if (productTypes[i].equals(selectedProductType)) {
                price = productPrices[i];
                break;
            }
        }

        // Set the calculated price in the priceEditText
        priceEditText.setText(String.valueOf(price));
    }

    private void saveProduct() {
        // Get input values
        String productName = productNameEditText.getText().toString().trim();
        String productDescription = productDescriptionEditText.getText().toString().trim();
        String priceStr = priceEditText.getText().toString().trim();
        String penaltyAmountStr = penaltyAmountEditText.getText().toString().trim();

        // Validate inputs
        if (productName.isEmpty() || productDescription.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        double penaltyAmount = penaltyCheckBox.isChecked() ? Double.parseDouble(penaltyAmountStr) : 0.0;

        // Calculate total price (considering fare and penalty)
        double total = price + penaltyAmount;
        String selectedShift = getSelectedShift();


        // Create a HashMap to store the product data
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("productName", productName);
        productMap.put("productDescription", productDescription);
        productMap.put("Type", selectedProductType);
        productMap.put("price", price);
        productMap.put("fare", price);
        productMap.put("shift", selectedShift);
        productMap.put("penaltyEnabled", penaltyCheckBox.isChecked());
        productMap.put("penaltyAmount", penaltyAmount);
        productMap.put("totalPrice", total);

        // Generate a unique key for the product
        String productId = databaseReference.push().getKey();

        // Save the product to Firebase
        if (productId != null) {
            databaseReference.child(productId).setValue(productMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MerchantActivity.this, "Product saved successfully", Toast.LENGTH_SHORT).show();
                                clearForm();
                            } else {
                                Toast.makeText(MerchantActivity.this, "Failed to save product", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private String getSelectedShift() {
        int selectedRadioButtonId = shiftRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == morningRadioButton.getId()) {
            return "Morning Shift";
        } else if (selectedRadioButtonId == eveningRadioButton.getId()) {
            return "Evening Shift";
        } else if (selectedRadioButtonId == nightRadioButton.getId()) {
            return "Night Shift";
        } else {
            return "No Shift Selected";
        }
    }


    private void clearForm() {
        productNameEditText.setText("");
        productDescriptionEditText.setText("");
        priceEditText.setText("");
        penaltyCheckBox.setChecked(false);
        shiftRadioGroup.clearCheck();
        penaltyAmountEditText.setText("");
        penaltyAmountEditText.setVisibility(View.GONE);
    }
}
