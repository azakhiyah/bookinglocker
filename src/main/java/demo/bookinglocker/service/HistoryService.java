package demo.bookinglocker.service;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.stereotype.Component;

import demo.bookinglocker.model.History;

@Component
public interface HistoryService {
    History addHistory(int bookingId,int userId,int lockerNumber, Date startDate, Date endDate, BigInteger deposit, BigInteger fine, int status) ;
    
    
}
