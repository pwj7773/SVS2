package com.svs.predict.service;

import java.util.Map;

import com.svs.predict.domain.Reservation;


public interface PredictService {

	Map<String, Object> predict(Reservation reservation);



}
