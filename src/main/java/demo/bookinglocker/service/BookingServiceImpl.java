package demo.bookinglocker.service;


import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bookinglocker.model.Booking;

import demo.bookinglocker.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }
    @Override
    public Booking addBooking(int userId, int lockerNumber, Date Stardate, Date Endate, BigInteger deposit,
           BigInteger fine,int status) {
        Booking booking = new Booking();
        booking.userId=userId;
        booking.lockerNumber=lockerNumber;
        booking.startDate=Stardate;
        booking.endDate=Endate;
        booking.deposit=deposit;
        booking.fine=fine;
        booking.status=status;
        return bookingRepository.save(booking);
    }

    

    @Override
    public Map<String, Object> findBookingInfo(int lockerNumber) {
        return bookingRepository.findBookingInfo(lockerNumber);
    }
 
    @Override
    public Booking updateAfter3FailAttempt(int lockerNumber) {
        Booking bookingUpdateFineAfterFailAttempt3 = bookingRepository.findBookingByLockerNumber(lockerNumber);
        bookingUpdateFineAfterFailAttempt3.setFine(BigInteger.valueOf(25000));
        bookingUpdateFineAfterFailAttempt3.setDeposit(BigInteger.valueOf(0));
        bookingUpdateFineAfterFailAttempt3.setStatus(2);
        //BigInteger saldo = deposit.subtract(fine);
        //bookingUpdateFineAfterFailAttempt3.setSaldo(BigInteger.valueOf(0).subtract(BigInteger.valueOf(25000)));
        return bookingRepository.save(bookingUpdateFineAfterFailAttempt3);
    }
    @Override
    public Booking Get(int lockerNumber) {
        return bookingRepository.findBookingByLockerNumber(lockerNumber);
    }
    @Override
    public Booking findBookingByLockerNumber(int lockerNumber) {
        return findBookingByLockerNumber(lockerNumber);
    }
    @Override
    public Booking updateAfterPassAttempt0(int lockerNumber) {
        Booking bookingUpdateAfterPassAttempt0 = bookingRepository.findBookingByLockerNumber(lockerNumber);
        bookingUpdateAfterPassAttempt0.setStatus(0);
        return bookingRepository.save(bookingUpdateAfterPassAttempt0);
    }
    @Override
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
        
    }
    
}
