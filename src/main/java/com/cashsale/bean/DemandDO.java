package com.cashsale.bean;

import java.util.Map;

/**
 * @Description:
 * @Author: 8-0416
 * @Date: 2018/12/3
 */
public class DemandDO {
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
    private String demandDescription;
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
    private int demandId;
    /**
     * 评论数
     */
    private int commentsNumber;
    /**
     * 点赞数
     */
    private int likeNumber;
    /**
     * 分享数
     */
    private int shareNumber;

    public DemandDO() {
    }

    public DemandDO(Map<String, Object> map){
        this.title = (String) map.get("title");
        this.label = (String)map.get("label");
        this.price = Double.parseDouble(map.get("price").toString());
        this.tradeMethod = Integer.parseInt(map.get("trade_method").toString());
        this.isBargain = Integer.parseInt(map.get("is_bargain").toString());
        this.demandDescription = (String)map.get("demand_description");
        this.imageUrl = (String)map.get("image_url");
        this.username = (String)map.get("user_name");
        this.demandId = Integer.parseInt(map.get("demand_id").toString());
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

    public String getDemandDescription() {
        return demandDescription;
    }

    public void setDemandDescription(String demandDescription) {
        this.demandDescription = demandDescription;
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

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(int shareNumber) {
        this.shareNumber = shareNumber;
    }
}
