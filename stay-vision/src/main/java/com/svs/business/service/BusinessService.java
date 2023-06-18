package com.svs.business.service;

import java.util.List;

import com.svs.business.api.dto.BusinessRegisterDto.BusinessInsertRequestDto;
import com.svs.business.domain.Business;
import com.svs.business.repository.projection.DefaultBusinessProjection.BusinessFindProjection;

public interface BusinessService {

	List<Business> findAllBusiness(String category, String keyword);

	void insert(BusinessInsertRequestDto dto, String userId);

	Business findById(Long id);

	void updateBusiness(BusinessInsertRequestDto dto);

}
