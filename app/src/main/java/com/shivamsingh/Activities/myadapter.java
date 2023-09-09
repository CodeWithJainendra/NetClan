package com.shivamsingh.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamsingh.Adapter.AdminCheckSeller;
import com.shivamsingh.netclan.R;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<AdminCheckSeller, myadapter.MyViewHolder> {

    private Context context;
    private DatabaseReference databaseReference;

    public myadapter(@NonNull FirebaseRecyclerOptions<AdminCheckSeller> options, Context context) {
        super(options);
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("products");
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull AdminCheckSeller model) {
        holder.name.setText(model.getProductName());
        holder.email.setText(model.getProductDescription());
        holder.penalty.setText(String.valueOf(model.getProductPrice()));
        holder.price.setText(model.getProductType());
        holder.editBtn.setOnClickListener(v -> {
            showEditDialog(model);
        });

        holder.removeBtn.setOnClickListener(v -> {
            removeProduct(model);
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_creative_edit, parent, false);
        return new MyViewHolder(view);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, email, type, price, penalty;
        ImageView editBtn, removeBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.shop_image);
            name = itemView.findViewById(R.id.shop_name);
            email = itemView.findViewById(R.id.email);
            type = itemView.findViewById(R.id.phone);
            price = itemView.findViewById(R.id.address);
            penalty = itemView.findViewById(R.id.city);
            editBtn = itemView.findViewById(R.id.acceptBtn);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }

    private void showEditDialog(AdminCheckSeller adminCheckSeller) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View editDialogView = LayoutInflater.from(context).inflate(R.layout.activity_edit_item_activvity, null);
        builder.setView(editDialogView);

        EditText editShopName = editDialogView.findViewById(R.id.edit_shop_name);
        EditText editEmail = editDialogView.findViewById(R.id.edit_email);

        editShopName.setText(adminCheckSeller.getProductName());
        editEmail.setText(adminCheckSeller.getProductDescription());

        builder.setPositiveButton("Save", (dialog, which) -> {
            String editedShopName = editShopName.getText().toString();
            String editedEmail = editEmail.getText().toString();

            try {
                // Create a HashMap to store the updates
                HashMap<String, Object> updates = new HashMap<>();
                updates.put("productName", editedShopName);
                updates.put("productDescription", editedEmail);

                // Update the database with the HashMap
                databaseReference.child(adminCheckSeller.getProductId()).updateChildren(updates);

                // Dismiss the dialog
                dialog.dismiss();

                Toast.makeText(context, "Product updated successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Failed to update product", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle canceling the edit
            dialog.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void removeProduct(AdminCheckSeller adminCheckSeller) {
        try {
            databaseReference.child(adminCheckSeller.getProductId()).removeValue(); // Remove the product from the database
            Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to delete product", Toast.LENGTH_SHORT).show();
        }
    }
}
