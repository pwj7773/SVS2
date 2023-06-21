package com.svs.predict.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class Reservation {
    String platform;
    String name;
    String address;
    String room;
    String checkIn;
    String checkOut;

}
