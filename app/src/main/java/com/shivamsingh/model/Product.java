package com.shivamsingh.model;

public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private String productType;
    private String productCategory;
    private double productPrice;
    private boolean hasPenalty;
    private double penaltyAmount;

    // Default no-argument constructor required for Firebase
    public Product() {
    }

    public Product(String productId, String product_name, String product_desc, String productType, double productPrice, boolean hasPenalty, double penaltyAmount) {
        this.productId = productId;
        this.productName = product_name;
        this.productDescription = product_desc;
        this.productType = productType;
        this.productPrice = productPrice;
        this.hasPenalty = hasPenalty;
        this.penaltyAmount = penaltyAmount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isHasPenalty() {
        return hasPenalty;
    }

    public void setHasPenalty(boolean hasPenalty) {
        this.hasPenalty = hasPenalty;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
