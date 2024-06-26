BookingLocker
BookingLocker is a simple backend application that allows users to register, book lockers, and access lockers using passwords. 
Follow this step to run project :
- clone repo
- setup file runDB.bat base on your environment
- run command : mvn spring-boot : run 
- Import file BookingLocker postman_collection into your postman
- enjoy
   
Below are the working scenarios:

1. User Registration
Endpoint: POST /api/users/register

    - Registers a new user with the following information: 
        
        {
            "name": "Nabil",
            "phoneNumber": "082167425567",
            "idCard": "abc123",
            "email": "balita@example.com"
        }    
    
        If registration is successful will get JSON Respon :
        
        {
            "data": {
                "id": 3,
                "name": "Nabi",
                "phoneNumber": "082167425567",
                "idCard": "abc123",
                "email": "balita@example.com"
            },
            "message": "User registration successful",
            "status": 200
        }
      
    -    If the email is already registered will get JSON Respon :
    
        {
            "data": null,
            "message": "Email is already registered",
            "status": 200
        }
2. Booking Locker
Endpoint: POST /api/booking

    - Books a locker with the following information:
        {
            "userId": 2,
            "lockerNumber": 5
        }
     
    -  If booking is successful will get this JSON response :
        {
            "data": {
                "LOCKERNUMBER": 4,
                "EMAIL": "bayi@example.com",
                "USERID": 2,
                "PASSWORD": "tUXGRI8I"
            },
            "message": "Locker booking for Locker: 4 successful",
            "status": 200
        }
       
    - If the user has already booked 3 lockers will get this JSON Response:
        {
            "data": null,
            "message": "User has already booked 3 lockers",
            "status": 200
        }
3. Locker Password
Endpoint: POST /api/locker/{lockernmber}/{password}

    Accesses the locker with the provided locker number and password.
    
    The password can only be used 2 times. After that, the locker is considered rented out.
    If the wrong password is input 3 times, the user will be fined and the deposit will be lost.

4. Update Fine
Every day, the fine will be updated to 5000 (this purpose triger by file /db/UpdateFine.bat run by TaskScheduler in windows or cron jobs in linux)

With BookingLocker, users can easily register, book lockers, and access lockers effortlessly. This application is suitable for efficient locker rental management.
