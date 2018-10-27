package com.cashsale.bean;

import java.util.Map;

/**
 * @author 肥宅快乐码
 * @date 2018/10/17 - 22:52
 */
public class Product {
    private String title;
    private String label;
    private int price;
    private int tradeMethod;
    private int isBargain;
    private String pdDescription;
    private String imageUrl;

    public Product(Map<String, Object> map){
        this.title = (String) map.get("title");
        this.label = (String)map.get("label");
        this.price = (int)map.get("price");
        this.tradeMethod = (int)map.get("trade_method");
        this.isBargain = (int)map.get("is_bargain");
        this.pdDescription = (String)map.get("product_description");
        this.imageUrl = (String)map.get("image_url");
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(int tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public int getIsBargain() {
        return isBargain;
    }

    public void setIsBargain(int isBargain) {
        this.isBargain = isBargain;
    }

    public String getPdDescription() {
        return pdDescription;
    }

    public void setPdDescription(String pdDescription) {
        this.pdDescription = pdDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
