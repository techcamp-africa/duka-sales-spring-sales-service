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
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	  
}
