package com.BookMyShowApp.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Integer id;
    private UserDto user;
    private MovieDto movie;
    private ATMDto atm;
    @JsonManagedReference
    private List<BookedSeatsDto> bookedSeats;
    private String time;
    private String location;
    private String theaterName;
   // private Double price;
    private String food;
    private Double totalPrice;
    //@JsonManagedReference
//    private Map<FoodDto,Integer> foods;
}
