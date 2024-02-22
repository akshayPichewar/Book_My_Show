//package com.BookMyShowApp.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer ticketId;
//
//    private List<String> seatNumbers;
//
//    @ManyToOne
//    private User user;
//
//    @ManyToOne
//    private Movies movies;
//
//    @ManyToOne
//    private CinemaHall cinemaHall;
//
//    @OneToOne
//    private Booking booking;
//
//
//}
