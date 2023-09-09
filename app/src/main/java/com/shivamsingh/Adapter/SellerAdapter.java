package com.shivamsingh.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamsingh.netclan.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.HolderSeller> {

    private Context context;
    private ArrayList<AdminCheckSeller> adminCheckSellersList;


    public SellerAdapter(Context context, ArrayList<AdminCheckSeller> adminCheckSellersList) {
        this.context = context;
        this.adminCheckSellersList = adminCheckSellersList;
    }

    @NonNull
    @Override
    public HolderSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_seller, parent, false);
        return new HolderSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSeller holder, int position) {

        AdminCheckSeller adminCheckSeller = adminCheckSellersList.get(position);

        String shopeName = adminCheckSeller.getShopName();
        String mobile = adminCheckSeller.getMobile();
        String address = adminCheckSeller.getAddress();
        String city = adminCheckSeller.getCity();
        String email = adminCheckSeller.getEmail();
        String accountCreatedate = adminCheckSeller.getTimestamp();
        String sellerId = adminCheckSeller.getUid();
        String key = adminCheckSeller.getKey();


        String productname=adminCheckSeller.getProductName();

        //change timestamp to proper format
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(accountCreatedate));
        String formatDate = DateFormat.format("dd/MM/yyyy",calendar).toString();



        holder.shop_name.setText(shopeName);
        holder.phone.setText(mobile);
        holder.address.setText(address);
        holder.city.setText(city);
        holder.email.setText(email);
        holder.date.setText(formatDate);
        holder.product_name.setText(productname);

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateStatus(sellerId,key);
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDeclineStatus(sellerId,key);
            }
        });
    }

    private void UpdateDeclineStatus(String sellerId, String key) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(sellerId).child("Is_approval").setValue("notApproved").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                RemoveSellerStatusFromAdmin(sellerId,key);
                Toast.makeText(context,"Seller Approved",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RemoveSellerStatusFromAdmin(String sellerId, String key) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminChecking");
        databaseReference.child(key).child("Is_approval").setValue("notApproved");
    }

    private void UpdateStatus(String sellerId, String key) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(sellerId).child("Is_approval").setValue("approved").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

             //   String message = "Account is " + "approved";
                RemoveSellerFromAdmin(sellerId,key);
             //   prepareNotificationMessage(sellerId,message);
                Toast.makeText(context,"Seller Approved",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void RemoveSellerFromAdmin(String sellerId, String key) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminChecking");
        databaseReference.child(key).child("Is_approval").setValue("approved");
    }

    @Override
    public int getItemCount() {
        return adminCheckSellersList.size();
    }

    public class HolderSeller extends RecyclerView.ViewHolder {

        TextView shop_name,city,phone,address,email,date;
        EditText product_name, product_desc, category, quantity, price;
        String discountPrice,discount_desc,shippingFee;
        ImageView acceptBtn,removeBtn;

        public HolderSeller(@NonNull View itemView) {
            super(itemView);

            shop_name = itemView.findViewById(R.id.shop_name);
            city = itemView.findViewById(R.id.city);
            product_name=itemView.findViewById(R.id.product_name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            acceptBtn = itemView.findViewById(R.id.acceptBtn);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            email = itemView.findViewById(R.id.email);
            date = itemView.findViewById(R.id.date);
        }
    }
}
