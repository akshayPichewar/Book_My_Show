package com.BookMyShowApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Integer userId;
    private Integer theaterId;
    private Integer moviesId;
    private String time;
    private ATMDto atm;
}
