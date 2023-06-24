package com.svs.room.service;

import java.util.List;

import com.svs.room.domain.Room;
import com.svs.room.repository.projection.DefaultRoomProjection.RoomProjection;

public interface RoomService {

	List<Room> findRoom(Long id);

	RoomProjection findByRoomName(String room);



}
