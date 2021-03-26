package com.duka.sales.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duka.sales.models.Sale;
import com.duka.sales.repositories.SaleRepository;

@RestController
@Component
public class SalesController {
	
	@Autowired
	private SaleRepository salesrepo;
	
	 @GetMapping("/sales")
	 public List<Sale> getAllSales() 
	 {
	  	return salesrepo.findAll();        
	 }
	 
	 @GetMapping("/sales/{inv_id}")
	 public List<Sale> getAllSalesByInventory(@PathVariable Long inv_id ) 
	 {
	  	return salesrepo.findByInvId();        
	 }
	 
	 @PostMapping("/sales")
	 public Sale createSale(@Validated @RequestBody Sale sale) 
	 { 
    	return salesrepo.save(sale);
	 }
}
