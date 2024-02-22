package com.BookMyShowApp.ServiceImpl;

import com.BookMyShowApp.dto.*;
import com.BookMyShowApp.entity.*;
import com.BookMyShowApp.exception.ResourceNotFound;
import com.BookMyShowApp.repository.*;
import com.BookMyShowApp.service.BookingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private BookedSetRepository bookedSetRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MoviesServiceImpl moviesService;
    @Autowired
    private ATMRepository atmRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MoviesRepository moviesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public BookingDto createBooking(BookingDto bookingDto) {

        User user = userRepository.findById(bookingDto.getUser().getId())
                .orElseThrow(() -> new ResourceNotFound("userId is not found -" + bookingDto.getUser().getId()));

        Movies movies = this.moviesRepository.findById(bookingDto.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFound("MovieId is not found -" + bookingDto.getMovie().getId()));


        ATMDto atmDto = bookingDto.getAtm();
        if (atmDto == null) {
            throw new ResourceNotFound("Payment information is required");
        }
        Booking booking = modelMapper.map(bookingDto, Booking.class);

        ATM atmEntity = modelMapper.map(atmDto, ATM.class);

        ATM atmSave = atmRepository.save(atmEntity);

        booking.setUser(user);
        booking.setMovie(movies);
        booking.setAtm(atmSave);

        //  checking seat Availability
        List<BookedSeatsDto> bookedSeatDtoList = bookingDto.getBookedSeats();
        List<BookedSeats> bookedSeatsList = bookedSeatDtoList.stream().map(bookedSeatsDto -> {
            BookedSeats bookedSeats = modelMapper.map(bookedSeatsDto, BookedSeats.class);
            bookedSeats.setBooking(booking);
            return bookedSeats;
        }).collect(Collectors.toList());

        boolean seatAvailable = checkSeatsAvailability(
                booking.getTheaterName(),
                booking.getLocation(),
                booking.getTime(),
                bookedSeatDtoList);


        if (!seatAvailable)
        {
            throw new ResourceNotFound("Seats are already Booked.");
        }

        int size = bookingDto.getBookedSeats().size();

        if(size>=5){
            throw new ResourceNotFound("Only Four seat will be booked by one user");
        }

        double ticketPrice = calculateSeatTypePrice( bookedSeatDtoList);

        String food = bookingDto.getFood();
        String[] split = food.split("=");
        double price = Double.parseDouble(split[1]);
        double TotalPrice =ticketPrice +price;
        booking.setTotalPrice(TotalPrice);


        List<BookedSeats> bookedSeats = bookedSetRepository.saveAll(bookedSeatsList);
            booking.setBookedSeats(bookedSeats);


        Booking savedBooking = bookingRepository.save(booking);

        return modelMapper.map(savedBooking,BookingDto.class);
    }





    public boolean checkSeatsAvailability(String theaterName, String location, String time,
                                         List<BookedSeatsDto> bookedSets)
    {
        List<Booking> alreadyBooked = bookingRepository.findByTheaterNameAndLocationAndTime(theaterName, location, time);

        if(alreadyBooked==null || alreadyBooked.isEmpty())
        {
            return true;
        }
        for(Booking checkBooking:alreadyBooked){
            List<BookedSeats> bookedSeats = checkBooking.getBookedSeats();

            for(BookedSeatsDto bookedSeatsDto:bookedSets)
            {
                for(BookedSeats bookedSeats1:bookedSeats){

                    if(bookedSeats1.getSeatNumber().equals(bookedSeatsDto.getSeatNumber())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<BookingDto> getAllBooking() {

        List<Booking> all = bookingRepository.findAll();

        List<BookingDto> collect = all.stream().map(booking -> modelMapper.map(booking,BookingDto.class)).collect(Collectors.toList());
        return collect;
    }
    @Override
    public List<BookingDto> getBookingByEmail(String email)
    {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));

        List<Booking> bookingList = user.getBookingList();
        return bookingList.stream().map(booking -> modelMapper.map(booking,BookingDto.class))
                .collect(Collectors.toList());
    }


    private double calculateSeatTypePrice(List<BookedSeatsDto> bookedSeats) {

        return bookedSeats.stream()
                .mapToDouble(seatDto -> {
                    char seatType = seatDto.getSeatNumber().charAt(0);
                    // pricing logic based on seat type
                    if (seatType == 'A') {
                        return 100.0;  // Set price for seat type A
                    } else if (seatType == 'B') {
                        return 200.0;   // Set price for seat type B
                    } else if (seatType == 'C') {
                        return 300.0;  // Set price for seat type C
                    } else {
                        return 0.0;   // Default price for other seat types
                    }
                })
                .sum();
    }
}
