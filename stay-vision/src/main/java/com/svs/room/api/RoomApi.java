package com.svs.room.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svs.business.domain.Business;
import com.svs.business.service.BusinessService;
import com.svs.room.domain.Room;
import com.svs.room.service.RoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoomApi {
	private final BusinessService businessService;
	private final RoomService roomservice;
	
	@PostMapping("/rooms")
	@ResponseBody
	public List<Room> rooms(String businessName){
		Business business = businessService.findByBusinessName(businessName);
		List<Room> rooms = roomservice.findRoom(business.getId()); 
		return rooms;
	}

}
