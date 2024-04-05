package demo.bookinglocker.model;


import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking_ID", nullable = false)
    private int bookingId;

    // @ManyToOne
    // @JoinColumn(name = "User_ID", referencedColumnName = "ID")
    // public User user;

    // public User getUser() {
    //     return this.user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    @Column(name = "User_ID")
    public int userId;


    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

    @Column(name = "Locker_Number")
    public int lockerNumber;

    @Column(name = "Start_Date", nullable = false)
    public Date startDate;

    @Column(name = "End_Date", nullable = false)
    public Date endDate;

    @Column(name = "Deposit")
    public BigInteger deposit;

    @Column(name = "Fine")
    public BigInteger fine;

    @Column(name = "Status")
    public int status;


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
 

    // Getters and Setters
    // ...

    public int getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

   
    public int getLockerNumber() {
        return this.lockerNumber;
    }

    public void setLockerNumber(int lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public BigInteger getDeposit() {
        return this.deposit;
    }

    public void setDeposit(BigInteger deposit) {
        this.deposit = deposit;
    }

    public BigInteger getFine() {
        return this.fine;
    }

    public void setFine(BigInteger fine) {
        this.fine = fine;
    }
    
}