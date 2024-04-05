package demo.bookinglocker.service;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bookinglocker.model.History;
import demo.bookinglocker.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository historyRepo;

    @Override
    public History addHistory(int bookingId,int userId ,int lockerNumber, Date startDate, Date endDate, BigInteger deposit,
            BigInteger fine, int status) {
        History history = new History();
        history.bookingId=bookingId;
        history.userId=userId;
        history.lockerNumber=lockerNumber;
        history.startDate=startDate;
        history.endDate=endDate;
        history.deposit=deposit;
        history.fine=fine;
        history.status=status;
        return historyRepo.save(history);

    }
    
}
