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
import com.svs.predict.api.dto.PredictDto.*;
import com.svs.predict.domain.Reservation;
import com.svs.predict.service.PredictService;
import com.svs.room.domain.Room;
import com.svs.room.repository.projection.DefaultRoomProjection.*;
import com.svs.room.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PredictController {
	private final PredictService predictService;
	private final BusinessService businessService;
	private final RoomService roomService;
	
	@GetMapping("/predict")
	public String predict(Model model) {
		List<Business> business = businessService.findAllBusiness();
		model.addAttribute("business", business);
		return "predict";
	}
	
	@PostMapping("/predict")
	@ResponseBody
	public Map<String, Object> predict(ResrvationDto dto) throws JsonProcessingException {
		log.debug("{}",dto);
		RoomProjection room =roomService.findByRoomName(dto.room());
		Map<String, Object> result = null;
		Reservation reservation = 
				Reservation.builder()
						   .platform(dto.platform())
						   .name(dto.name())
						   .address(dto.address())
						   .date(dto.date())
						   .maxPerson(room.maxPerson())
						   .roomInfo(room.roomInfo())
						   .build();
		log.debug("{}",reservation);
		result = predictService.predict(reservation);
		
		return result;
	}

}
