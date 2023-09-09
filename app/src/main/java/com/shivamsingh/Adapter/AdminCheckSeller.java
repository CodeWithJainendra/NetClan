package com.shivamsingh.Adapter;

public class AdminCheckSeller {
    private String productId;
    private String productName;
    private String productDescription;
    private String productType;
    private String productCategory;
    private double productPrice;
    private boolean hasPenalty;
    private double penaltyAmount;

    private String is_approval;
    private String Shop_Status;
    private String Uid;
    private String address;
    private String city;
    private String email;
    private String mobile;
    private String password;
    private String shopName;
    private String timestamp;
    private String userType;
    private String username;
    private String key;
    private boolean selected;

    public AdminCheckSeller() {
        // Default constructor required for Firebase
    }

    public AdminCheckSeller(String productId, String productName, String productDescription, String productType, String productCategory, double productPrice, boolean hasPenalty, double penaltyAmount, String is_approval, String Shop_Status, String Uid, String address, String city, String email, String mobile, String password, String shopName, String timestamp, String userType, String username, String key, boolean selected) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productType = productType;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.hasPenalty = hasPenalty;
        this.penaltyAmount = penaltyAmount;
        this.is_approval = is_approval;
        this.Shop_Status = Shop_Status;
        this.Uid = Uid;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.shopName = shopName;
        this.timestamp = timestamp;
        this.userType = userType;
        this.username = username;
        this.key = key;
        this.selected = selected;
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

    public String getIs_approval() {
        return is_approval;
    }

    public void setIs_approval(String is_approval) {
        this.is_approval = is_approval;
    }

    public String getShop_Status() {
        return Shop_Status;
    }

    public void setShop_Status(String shop_Status) {
        Shop_Status = shop_Status;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
