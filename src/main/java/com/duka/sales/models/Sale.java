package com.duka.sales.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "sales")
public class Sale extends AuditModel {
	
	  @Id
	  @GeneratedValue(generator = "sale_generator")
	  @SequenceGenerator(
	            name = "sale_generator",
	            sequenceName = "sale_sequence",
	            initialValue = 1000
	    )
	   private Long id;
	  
	  @Column(name="inv_id",nullable=false)
	  private int invId;
	  
	  @Column(name="quantity",nullable=false)
	  private int quantity;
	  
	  @Column(name="uid",nullable=false)
	  private String uid;


	public Sale() {
		super();
	}


	public Sale(Long id, int invId, int quantity, String uid) {
		super();
		this.id = id;
		this.invId = invId;
		this.quantity = quantity;
		this.uid = uid;
	}

 
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getInvId() {
		return invId;
	}


	public void setInvId(int invId) {
		this.invId = invId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}

}
