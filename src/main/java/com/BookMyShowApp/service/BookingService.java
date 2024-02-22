package com.BookMyShowApp.service;

import com.BookMyShowApp.dto.*;

import java.util.List;

public interface BookingService {

    public BookingDto createBooking(BookingDto bookingDto);

    List<BookingDto> getAllBooking();

    List<BookingDto> getBookingByEmail(String email);


}
