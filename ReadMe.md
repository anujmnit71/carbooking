#Car Booking App

##Pre-requisite  
java17 , postgres:11-alpine

##How to run
1) compile  
```mvn clean compile```

2) start postgres  
   if postgres not running then run postgres using docker   
```docker run --rm  --name pg-docker --env POSTGRES_USER="root" --env POSTGRES_PASSWORD="root" -d -p 5432:5432 -v ~/anydir:/var/lib/postgresql/data  postgres:11-alpine```

default username password : root/root   
(if different configure same in src/main/resources/application.yml)

start application from  
```src/main/java/com/app/carbooking/CarBookingApplication.java```

Use postman collection  
```data/car-booking.postman_collection.json```

to call REST apis

Can use fakeData in
```data/sample-data.csv```
Copy data to fakeData.csv and use copy-data.sql from psql to inject data. 

##Design
