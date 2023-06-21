package com.svs.predict.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svs.predict.domain.Reservation;



@Service
public class PredictServiceImp implements PredictService {
	@Value("${svs.predict.server}")
	String url;

	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> predict(Reservation reservation) {
		
		Map<String, Object> result = new HashMap<>();   // 에러가 발생 시 반환
		Map<String, Object> map = null;					// 정상 반환시 
		
		try {
			Map<String, String> parameters = new HashMap<>();
			
			// 서버로 보낼 데이터(쿼리스트링) 준비
			String str = reservation.getDate();
			String[] date = str.split("~");
			parameters.put("platform", reservation.getPlatform());
			parameters.put("name", reservation.getName());
			parameters.put("address", reservation.getAddress());
			parameters.put("room", reservation.getRoom());
			parameters.put("checkIn", date[0]);
			parameters.put("checkOut", date[1]);
			
	
			// 헤더 준비
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			RestTemplate restTemplate = new RestTemplate();
			
			//post로 전송할 경우 postForEntity를 사용 (1:요청 URL, 2:요청바디, 3:응답바디) 
			ResponseEntity<String> response = restTemplate.postForEntity(url, parameters, String.class);
			
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			
			map = gson.fromJson(response.getBody(), Map.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(result);
		} catch(Exception e) {
			result.put("statusCode", "999");
			result.put("body", "exception 발생");
			System.out.println(result);
			return result;
		}
		
		return map;
	}

	

}
