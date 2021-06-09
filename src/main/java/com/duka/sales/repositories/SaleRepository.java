package com.duka.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duka.sales.models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findByInvId(int inv_id);
	
	List<Sale> findByUid(String uid);
}
