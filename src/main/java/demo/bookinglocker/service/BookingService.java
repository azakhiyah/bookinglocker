package demo.bookinglocker.service;


import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import demo.bookinglocker.model.Booking;

@Component
public interface BookingService {
    void addBooking(Booking booking);   
    void deleteBooking(int bookingId);
    Booking addBooking (int userId,int lockerNumber,Date Stardate,Date Endate,BigInteger deposit,BigInteger fine,int status);
    Booking updateAfter3FailAttempt (int lockerNumber);
    Booking updateAfterPassAttempt0 (int lockerNumber);
    Booking Get(int lockerNumber);
    Booking findBookingByLockerNumber(int lockerNumber);
    Map<String, Object> findBookingInfo(int lockerNumber);
}
