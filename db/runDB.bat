title HSQLDBServer
:: change diretory where place of file hsqld.jar is stored base on your environment
java  -cp "D:\Work_Directory\GIT_Upload\BookingLocker\db\lib\hsqldb.jar" org.hsqldb.server.Server --database.0 file:database\Locker --dbname.0 locker
pause
