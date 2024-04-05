title UpdateFine
java -cp "D:\Work_Directory\GIT_Upload\BookingLocker\db\lib\hsqldb.jar;D:\Work_Directory\GIT_Upload\BookingLocker\db\lib\sqltool.jar" org.hsqldb.util.sqltool --inlineRc="url=jdbc:hsqldb:file:database/Locker,user=ROOT,password=minic123" -d org.hsqldb.jdbc.JDBCDriver -sql "update bookings set fine = fine + 5000 where status=1 and end_date < CURRENT_DATE;"
pause
