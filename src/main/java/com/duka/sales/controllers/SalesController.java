package com.duka.sales.controllers;


import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.duka.sales.exceptions.ErrorException;
import com.duka.sales.exceptions.ResourceNotFoundException;
import com.duka.sales.models.Sale;
import com.duka.sales.pojo.Inventory;
import com.duka.sales.repositories.SaleRepository;
import org.springframework.amqp.core.Queue;


@RestController
@Component
public class SalesController {
	
	private Inventory inventory;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private SaleRepository salesrepo;
	
	@Value("${strings.sales_url}")
	private String sales_url;
	    
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
    private Queue queue;
	
	
	 @GetMapping("/sales")
	 public List<Sale> getAllSales() 
	 {
		logger("All Sales Records fetched"); 
	  	return salesrepo.findAll();        
	 }
	 
	 @GetMapping("/sales/{inv_id}")
	 public List<Sale> getAllSalesByInventory(@PathVariable String inv_id ) 
	 {
		 try {
			 
			 int id = new Integer(inv_id);
			 logger("Sales Records fetched for inventory id "+ inv_id);

		  	 return salesrepo.findByInvId(id);   
		 }
		 catch (Exception e)
		 {
			 throw new ErrorException("Invalid value given");
		 }
		       
	 }
	 
	 @GetMapping("/sales/user/{uid}")
	 public List<Sale> getAllSalesByUser(@PathVariable String uid ) 
	 {
		 String id = new String(uid);
		 logger("Sales Records fetched for user id "+ uid);

	  	 return salesrepo.findByUid(id);        
	 }
	 
	 @GetMapping("/sales/{inv_id}/{u_id}")
	 public List<Sale> getAllSalesByInvAndUser(@PathVariable String inv_id,@PathVariable String u_id ) 
	 {
		 String uid = new String(u_id);
		 String invid = new String(inv_id);
		 int id = Integer.parseInt(invid);
		 logger("Sales Records fetched for user id "+ uid);

	  	 return salesrepo.findByInvIdAndUid(id, uid);        
	 }
	 
	 @PostMapping("/sales")
	 public Sale createSale(@RequestBody Sale sale) 
	 { 			 
			 try {
				 String full_url = this.sales_url + "/inventories/" + sale.getInvId();
				 this.inventory = restTemplate.getForObject(full_url, Inventory.class);
				 Sale s = salesrepo.save(sale);
				 logger("Successfull Save Sale Record No.: " + s.getId());
				 return s;
			 
			 }catch (Exception e) {
				
				 logger("Failed Save Sale Record Inventory No.: " + sale.getInvId());
				 throw new ResourceNotFoundException("inventory with id " + sale.getInvId() + " not found");
			}
	 }
	 
	 public String logger(String message)
	 { 		 
		 amqpTemplate.convertAndSend(queue.getName(), new String(message));
		 return "successfully logged"; 
	 }
	 
	
}
