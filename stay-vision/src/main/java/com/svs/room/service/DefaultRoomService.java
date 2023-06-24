package com.svs.room.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.svs.room.domain.Room;
import com.svs.room.repository.RoomRepository;
import com.svs.room.repository.projection.DefaultRoomProjection.RoomProjection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public final class DefaultRoomService implements RoomService {
	
	private final RoomRepository roomRepository;
	
	
	@Override
	public List<Room> findRoom(Long id) {
		return roomRepository.findBybusinessNum(id);
	}


	@Override
	public RoomProjection findByRoomName(String room) {
		return roomRepository.findByRoomName(room);
	}


}
