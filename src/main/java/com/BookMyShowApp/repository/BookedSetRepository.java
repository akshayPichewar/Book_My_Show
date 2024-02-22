package com.BookMyShowApp.repository;

import com.BookMyShowApp.entity.BookedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedSetRepository extends JpaRepository<BookedSeats,Integer> {
}
