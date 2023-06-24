package com.svs.room.repository.projection;

import javax.persistence.Column;

public record DefaultRoomProjection() {
	
	public record RoomProjection(
			@Column(name="MAX_PERSON")
			String maxPerson, 
			@Column(name="ROOM_INFO")
			String roomInfo
			) {}

}
