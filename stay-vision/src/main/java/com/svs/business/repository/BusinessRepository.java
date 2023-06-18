package com.svs.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svs.business.domain.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {

	List<Business> findByBusinessNameContains(String businessName);

	List<Business> findByBusinessAddressContains(String businessAddress);
	

	List<Business> findByMember_UserNameContains(String userName);


	Business findOneById(Long id);


	
}
