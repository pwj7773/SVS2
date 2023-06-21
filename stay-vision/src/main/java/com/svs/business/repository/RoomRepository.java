package com.svs.business.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svs.business.domain.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findBybusinessNum(Long id);



	
}
