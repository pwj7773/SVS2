package com.svs.room.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.svs.support.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "ROOM", schema = "svs")
public class Room extends BaseEntity {

	@Column(name = "BUSINESS_NUM")
	Long businessNum;

    @Column(name = "ROOM_NAME")
    String roomName;
    
    @Column(name= "MAX_PERSON")
    String maxPerson;
    @Column(name = "ROOM_INFO")
    String roomInfo;
}
