package com.svs.room.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svs.room.domain.Room;
import com.svs.room.repository.projection.DefaultRoomProjection.RoomProjection;


public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findBybusinessNum(Long id);

	RoomProjection findByRoomName(String room);


}
