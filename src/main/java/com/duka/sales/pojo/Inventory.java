package com.duka.sales.pojo;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

private String title;
private String isbnNo;
private Integer buyingPrice;
private Integer sellingPrice;
private Integer id;
private String publicId;
private String createdAt;
private String updatedAt;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Inventory() {
}

/**
*
* @param createdAt
* @param sellingPrice
* @param buyingPrice
* @param isbnNo
* @param id
* @param title
* @param publicId
* @param updatedAt
*/
public Inventory(String title, String isbnNo, Integer buyingPrice, Integer sellingPrice, Integer id, String publicId, String createdAt, String updatedAt) {
super();
this.title = title;
this.isbnNo = isbnNo;
this.buyingPrice = buyingPrice;
this.sellingPrice = sellingPrice;
this.id = id;
this.publicId = publicId;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getIsbnNo() {
return isbnNo;
}

public void setIsbnNo(String isbnNo) {
this.isbnNo = isbnNo;
}

public Integer getBuyingPrice() {
return buyingPrice;
}

public void setBuyingPrice(Integer buyingPrice) {
this.buyingPrice = buyingPrice;
}

public Integer getSellingPrice() {
return sellingPrice;
}

public void setSellingPrice(Integer sellingPrice) {
this.sellingPrice = sellingPrice;
}

public Integer sale.getInvId()() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getPublicId() {
return publicId;
}

public void setPublicId(String publicId) {
this.publicId = publicId;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}