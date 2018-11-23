## Energy Consumption Test

This is a spring micro service test case for the **legacy** implementation with a **RESTful** API . This project relies heavily on the use of Java 8 Stream API due to its parallelization, expressive and declarative style. Design patterns as Dry (Dont repeat your self) Soc (Separation of concern) and SOLID principles are also considered.
The project runs requires minimal configuration to run , To run project you need Java 8 Runtime Environment installed as well as maven. 
After a mvn clean package ,the exec command ['java','jar','energy-consumption-0.0.1.jar'] done in the target folder starts the application or a **mvn spring-boot:run** . The resources folder comes bundled with the files listed below.

- Sample CSV's
- applciation.properties
- data.sql

The project also contains code documentation and the swagger rest api documentation thatcan be accessed via [Swagger Home](https://localhost:8080/)
