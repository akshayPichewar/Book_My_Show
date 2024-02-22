//package com.BookMyShowApp.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class ShowTime {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer showTimeId;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private Integer availableSeats;
//
//    @OneToOne
//    private Booking booking;
//
//    @ManyToOne
//    private Movies movies;
//
//    @ManyToOne
//    private CinemaHall cinemaHall;
//
//
//}
