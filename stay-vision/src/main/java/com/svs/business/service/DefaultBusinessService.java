package com.svs.business.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.svs.business.api.dto.BusinessRegisterDto.BusinessInsertRequestDto;
import com.svs.business.domain.Business;
import com.svs.business.repository.BusinessRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public final class DefaultBusinessService implements BusinessService {
	
	private final BusinessRepository businessRepository;
	
	@Override
	public List<Business> findAllBusiness(String category, String keyword) {
		List<Business> business = new ArrayList<>();
		if (keyword == null || keyword.equals("")) {
			business = businessRepository.findAll();
		}
		else if (category.equals("businessName")) {
			business = businessRepository.findByBusinessNameContains(keyword);
		}
		else if (category.equals("businessAddress")) {
			business = businessRepository.findByBusinessAddressContains(keyword);
		}
		else if (category.equals("userName")) {
			business = businessRepository.findByMember_UserNameContains(keyword);
		}
		
		return business;
	}

	@Override
	public void insert(BusinessInsertRequestDto dto, String userId) {
		Business business = Business.builder()
				.businessName(dto.businessName())
				.businessAddress(dto.businessAddress())
				.totalRoom(dto.totalRoom())
				.phone(dto.phone())
				.userId(userId)
				.build();
		businessRepository.save(business);

	}

	@Override
	public Business findById(Long id) {
		return businessRepository.findOneById(id);
	}

	@Override
	public void updateBusiness(BusinessInsertRequestDto dto) {

		Business business = businessRepository.findOneById(dto.id());
		business.setBusinessAddress(dto.businessAddress());
		business.setBusinessName(dto.businessName());
		business.setPhone(dto.phone());
		business.setTotalRoom(dto.totalRoom());
		businessRepository.save(business);
	}


}
