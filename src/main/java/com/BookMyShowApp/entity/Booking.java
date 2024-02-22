package com.BookMyShowApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookedSeats> bookedSeats;

    @ManyToOne
    private Movies movie;

    @ManyToOne
    private ATM atm;

//    @ManyToOne
//    CinemaHall cinemaHall;

//    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
//    ShowTime showTime;

    private String time;
    private String food;
    private String location;
    private String theaterName;
   // private Double price;
    private Double totalPrice;
//    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
//    private Map<String,Integer>  foods;
}
