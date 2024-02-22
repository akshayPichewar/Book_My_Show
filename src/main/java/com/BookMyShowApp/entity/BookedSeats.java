package com.BookMyShowApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNumber;

//    @ManyToOne
//    private User user;
//
//    @ManyToOne
//    private Movies movies;

//    @ManyToOne
//    private CinemaHall cinemaHall;

    @ManyToOne
    @JsonIgnore
    private Booking booking;
}
