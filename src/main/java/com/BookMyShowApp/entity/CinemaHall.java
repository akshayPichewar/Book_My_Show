//package com.BookMyShowApp.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class CinemaHall {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer hallId;
//
//    private String hallName;
//
//    private String location;
//
//    private Integer seatingCapacity;
//
//    private Integer screens;
//
//    @OneToMany(mappedBy = "cinemaHall",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<BookedSeats> bookedSeats;
//
//    @OneToMany(mappedBy = "cinemaHall",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Booking> bookings;
//
//    @OneToMany(mappedBy = "cinemaHall",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<ShowTime> showTime;
//
//}
