package demo.bookinglocker.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.bookinglocker.model.Booking;
import demo.bookinglocker.model.Locker;
import demo.bookinglocker.repository.LockerRepository;
import demo.bookinglocker.service.BookingService;
import demo.bookinglocker.service.HistoryService;
import demo.bookinglocker.service.LockerService;
import demo.bookinglocker.utilities.responsehandler;
import demo.bookinglocker.utilities.RandomPasswordGenerator;

@RestController
@RequestMapping("/api")
public class LockerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockerController.class);
    @Autowired
    LockerService lockerService;
    @Autowired
    LockerRepository lockerRepo;
    @Autowired
    BookingService bookingService;
    @Autowired
    HistoryService historyService;
    
    @PostMapping("/locker")
    public ResponseEntity<Object> addLocker(@RequestBody Locker locker, BindingResult result) {
        String Alias = locker.getAlias();
        try {
            List<Locker> existLockerNumber = lockerRepo.findListLockerByAlias(Alias);
            if (existLockerNumber.isEmpty()||existLockerNumber.size()==0) {

                String passwd = RandomPasswordGenerator.generateRandomPassword();
                lockerService.addLocker(passwd, true, 3, Alias,2);
                LOGGER.info("Process register Locker :"+Alias+" berhasil");
                String Pesan = "Process register Locker :"+Alias+" berhasil";
                Locker registeredLockerNumber = lockerRepo.findLockerByAlias(Alias);
                return responsehandler.generateResponse(Pesan,HttpStatus.OK, registeredLockerNumber);
            } else {
                String Pesan = "Locker Number Sudah terdaftar";
                LOGGER.info("Block register Locker karena Locker: "+Alias+" sudah terdaftar");
                return responsehandler.generateResponse(Pesan,HttpStatus.OK, null);
            }
        } catch (Exception e) {
            return responsehandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
        //return  null;
    }    

    @PostMapping("/locker/{lockerNumber}/{password}")
    public ResponseEntity<Object> checkPassword (@PathVariable int lockerNumber, @PathVariable String password) {

        LOGGER.info("Process check password for locker : " + lockerNumber);
        List<Locker> availableLocker = lockerRepo.findListLockerByLockerNumber(lockerNumber);
        if (availableLocker.isEmpty()|| availableLocker.size()==0) {
            String Pesan = "Locker Number tidak ditemukan";
            LOGGER.info("Locker  : "+lockerNumber+" tidak ditemukan");
            return responsehandler.generateResponse(Pesan,HttpStatus.OK, null);
        } else {
            LOGGER.info("locker : "+lockerNumber+" sudah terdaftar, cek apakah available untuk di booking ?");
            Locker availabilityLocker = lockerService.Get(lockerNumber);
            boolean availability = availabilityLocker.isAvailability();
            if (availability == true) {
                String Pesan = "Locker: "+lockerNumber+" belum dibooking";
                LOGGER.info("Locker : " + lockerNumber + " belum di booking tidak perlu check password");
                return responsehandler.generateResponse(Pesan,HttpStatus.OK, null);
            } else {
             String registeredPass = availabilityLocker.getPassword();
             if (registeredPass.equals(password)) {
                int passPassword = availabilityLocker.getpassPasswordAttempts();
                int passPasswordCount = availabilityLocker.getpassPasswordAttempts()-1;
                    if (passPassword == 1) {
                        lockerService.updateAfterPassAttempt(lockerNumber, availability, passPasswordCount);
                        lockerService.updateAfterPassAttempt0(lockerNumber);
                        bookingService.updateAfterPassAttempt0(lockerNumber);
                        Booking BookingafterUpdatepassAttempt0 = bookingService.Get(lockerNumber);
                        int finBookingId = BookingafterUpdatepassAttempt0.getBookingId();
                        int finUserId = BookingafterUpdatepassAttempt0.getUserId();
                        int finLockerNumber = BookingafterUpdatepassAttempt0.getLockerNumber();
                        Date finStart_Date = BookingafterUpdatepassAttempt0.getStartDate();
                        Date finEnd_Date = BookingafterUpdatepassAttempt0.getEndDate();
                        int finStatus = BookingafterUpdatepassAttempt0.getStatus();
                        BigInteger finDeposit = BookingafterUpdatepassAttempt0.getDeposit();
                        BigInteger finFine = BookingafterUpdatepassAttempt0.getFine();
                        historyService.addHistory(finBookingId, finUserId, finLockerNumber, finStart_Date, finEnd_Date, finDeposit, finFine, finStatus);
                        bookingService.deleteBooking(finBookingId);
                        
                        //historyService.addHistory(passPassword, passPasswordCount, lockerNumber, null, null, null, null, lockerNumber)
                        String Pesan = "Sukses update availablitiy dan pass dari Locker: "+lockerNumber;
                        LOGGER.info(Pesan);
                        Locker afterUpdatepassAttempt0 = lockerService.Get(lockerNumber);
                        return responsehandler.generateResponse(Pesan,HttpStatus.OK, afterUpdatepassAttempt0);
                    } else {
                        lockerService.updateAfterPassAttempt(lockerNumber, availability, passPasswordCount);
                        String Pesan = "Sukses update pass attempt Locker: "+lockerNumber;
                        LOGGER.info(Pesan);
                        Locker afterUpdatepassAttempt = lockerService.Get(lockerNumber);
                        return responsehandler.generateResponse(Pesan,HttpStatus.OK, afterUpdatepassAttempt);
                    }
             } else {
                int failPassword = availabilityLocker.getfailurePasswordAttempts();
                int failPasswordCount = availabilityLocker.getfailurePasswordAttempts()-1;
                if (failPassword == 1) {
                   lockerService.updateAfterFailAttempt(lockerNumber, availability, failPasswordCount);
                   bookingService.updateAfter3FailAttempt(lockerNumber);
                   lockerService.updateAfterFailAttempt0(lockerNumber);
                   String Pesan = "Sukses update fine dan deposit setelah 3 kali gagal input password ";
                   LOGGER.info(Pesan);
                   Booking afterUpdateFineAndDeposit3FailPass = bookingService.Get(lockerNumber);
                   return responsehandler.generateResponse(Pesan,HttpStatus.OK, afterUpdateFineAndDeposit3FailPass);
                } else {
                    lockerService.updateAfterFailAttempt(lockerNumber, availability, failPasswordCount);
                    String Pesan = "Sukses update fail attempt Locker: "+lockerNumber;
                    LOGGER.info(Pesan);
                    Locker afterUpdatefailAttempt = lockerService.Get(lockerNumber);
                    return responsehandler.generateResponse(Pesan,HttpStatus.OK, afterUpdatefailAttempt);
                }

             }
            }
        }
        //return null;
    }
}
