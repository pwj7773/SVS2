package com.svs.predict.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class Reservation {
    String platform;
    String name;
    String address;
    String date;
    String maxPerson;
    String roomInfo;

}