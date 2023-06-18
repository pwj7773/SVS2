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
    private int name ;
    private int person ;
    private int weight ;
    private int address ;
    private int isWeek ;
    private int Saturday ;
    private int days ;
    private int month;

}
