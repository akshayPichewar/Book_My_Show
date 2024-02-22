package com.BookMyShowApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movies
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private String moviePoster;
    private String category;
    private LocalDate releaseDate;
    private String genre;
    private String rating;
    private String country;
    @JsonIgnore
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Booking> booking=new ArrayList<>();
//
//    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<BookedSeats> bookedSeats=new ArrayList<>();

//    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<ShowTime> showTimes=new ArrayList<>();

}
