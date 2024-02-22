package com.BookMyShowApp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedSeatsDto {

    private Integer id;
    private String seatNumber;
    @JsonBackReference
    private BookingDto booking;
}
