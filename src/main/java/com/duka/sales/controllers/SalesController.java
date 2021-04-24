package com.duka.sales.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
		logger("Browser", "All Sales Records fetched"); 
	  	return salesrepo.findAll();        
	 }
	 
	 @GetMapping("/sales/{inv_id}")
	 public List<Sale> getAllSalesByInventory(@PathVariable String inv_id ) 
	 {
		 int id = new Integer(inv_id);
		 logger("Browser", "Sales Records fetched for inventory id "+ inv_id);

	  	 return salesrepo.findByInvId(id);        
	 }
	 
	 @PostMapping("/sales")
	 public Sale createSale(@RequestBody Sale sale) 
	 { 
		
			 String full_url = this.sales_url + "/inventories/" + sale.getInvId();
			 
			 try {
				 this.inventory = restTemplate.getForObject(full_url, Inventory.class);
				 Sale s = salesrepo.save(sale);
				 logger("Browser", "Successfull Save Sale Record No.: " + s.getId());
				 return s;
			 
			 }catch (Exception e) {
				
				 logger("Browser", "Failed Save Sale Record Inventory No.: " + sale.getInvId());
				 throw new ResourceNotFoundException("inventory with id " + sale.getInvId() + " not found");
			}
			 		 

	 }
	 
	 public String logger(String useragent,String message)
	 {
		 StringBuffer mystring = new StringBuffer(); 
		 mystring.append(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		 mystring.append(",");
		 mystring.append(message);
		 mystring.append(",");
		 mystring.append(useragent);
		 		 
		 amqpTemplate.convertAndSend(queue.getName(), new String(mystring));
		 return "successfully logged"; 
	 }
	 
	
}
