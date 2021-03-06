package com.cashsale.bean;

import org.elasticsearch.common.text.Text;

import java.util.Map;

/**
 * @author 肥宅快乐码
 * @date 2018/12/2 - 17:30
 */
public class HightLightDTO {
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品标签
     */
    private String label;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 交易方式
     */
    private int tradeMethod;
    /**
     * 是否议价
     */
    private int isBargain;
    /**
     * 商品描述
     */
    private String pdDescription;
    /**
     * 商品图片地址
     */
    private String imageUrl;
    /**
     * 上传的用户名
     */
    private String username;
    /**
     * id
     */
    private int productId;
    /**
     * 高亮字段
     */
    private String highLight;

    public HightLightDTO(Map<String, Object> map){
        this.title = (String) map.get("title");
        this.label = (String)map.get("label");
        this.price = Double.parseDouble(map.get("price").toString());
        this.tradeMethod = Integer.parseInt(map.get("trade_method").toString());
        this.isBargain = Integer.parseInt(map.get("is_bargain").toString());
        this.pdDescription = (String)map.get("product_description");
        this.imageUrl = (String)map.get("image_url");
        this.username = (String)map.get("user_name");
        this.productId = Integer.parseInt(map.get("product_id").toString());
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String  getHighLight() {
        return highLight;
    }

    public void setHighLight(String highLight) {
        this.highLight = highLight;
    }
}
