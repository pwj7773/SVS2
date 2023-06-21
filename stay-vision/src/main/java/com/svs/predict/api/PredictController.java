package com.svs.predict.api;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.svs.business.domain.Business;
import com.svs.business.service.BusinessService;
import com.svs.predict.domain.Reservation;
import com.svs.predict.service.PredictService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PredictController {
	private final PredictService predictService;
	private final BusinessService businessService;
	
	@GetMapping("/predict")
	public String predict(Model model) {
		List<Business> business = businessService.findAllBusiness();
		model.addAttribute("business", business);
		return "predict";
	}
	
	@PostMapping("/predict")
	@ResponseBody
	public Map<String, Object> predict(Reservation reservation) throws JsonProcessingException {
		
		Map<String, Object> result = null;
		
		result = predictService.predict(reservation);
		
		return result;
	}

}
