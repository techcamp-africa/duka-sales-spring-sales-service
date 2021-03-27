package com.duka.sales.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.duka.sales.exceptions.ResourceNotFoundException;
import com.duka.sales.models.Sale;
import com.duka.sales.pojo.Inventory;
import com.duka.sales.repositories.SaleRepository;

import static java.util.Objects.isNull;

@RestController
@Component
public class SalesController {
	
	@Autowired
	private SaleRepository salesrepo;
	
	@Value("${strings.sales_url}")
	private String sales_url;
	    
	@Autowired
	RestTemplate restTemplate;
	
	
	 @GetMapping("/sales")
	 public List<Sale> getAllSales() 
	 {
	  	return salesrepo.findAll();        
	 }
	 
	 @GetMapping("/sales/{inv_id}")
	 public List<Sale> getAllSalesByInventory(@PathVariable Long inv_id ) 
	 {
	  	return salesrepo.findByInvId(inv_id);        
	 }
	 
	 @PostMapping("/sales")
	 public Sale createSale(@RequestBody Sale sale) 
	 { 
		 
			 String full_url = this.sales_url + "/inventories/" + sale.getInvId();
		
			 Inventory inventory = restTemplate.getForObject(full_url, Inventory.class);
			 
			 if(inventory.getId() > 0) 
			 {
				 return salesrepo.save(sale); 	
		     }	
			 
			 throw new ResourceNotFoundException("inventory with id " + inventory.getId() + " not found");
	 
	    	
		 
	 }
}
