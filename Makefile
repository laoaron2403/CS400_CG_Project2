default: run

run: prepareRun clean

prepareRun: compile
  java Fronend

test: compile testData testBackend testFrontend clean

compile: prepareFrontend prepareBackEnd prepareData

# test

## test Data
testData: prepareData testHotelAndHotelDataReader.class
  java -jar junit5.jar --class-path . --scan-classpath
  $(RM) testHotelAndHotelDataReader.class

testHotelAndHotelReader.class: testHotelAndHotelReader.java
  javac -cp .:junit5.jar testHotelAndHotelReader.java -Xlint
 
## test Backend
testBackend: prepareBackend testBackend.class
  java -jar junit5.jar --class-path . --scan-classpath
  $(RM) testBackend.class

testBackend.class: testBackend.java
  javac -cp .:junit5.jar testBackend.java -Xlint
  
## testFrontend
testFrontend: prepareFrontend testFrontend.class
  java -jar junit5.jar --class-path . --scan-classpath

testFrontend.class: testFrontend.java
  javac -cp .:junit5.jar testFrontend.java -Xlint

# Frontend
prepareFrontend: prepareBackend Frontend.class

Frontend.class: Frontend.java FrontendInterface.class
  javac Frontend.java

FrontendInterface.class: FrontendInterface.java
  javac FrontendInterface.java
  
# Backend
prepareBackend: prepareData Backend.class

Backend.class: Backend.java BackendInterface.java
  javac Backend.java
  
BackendInterface.class: BackendInterface.java
  javac BackendInterface.java
  
# DataWrangler
prepareData: HotelDataReader.class Hotel.class

HotelDataReader.class: HotelDataReader.java HotelDataReaderInterface.class
  javac HotelDataReader.java
  
HotelDataReaderInterface.class: HotelDataReaderInterface.java
  javac HotelDataReaderInterface.java

Hotel.class: Hotel.java HotelInterface.class
  javac Hotel.java
  
HotelInterface.class: HotelInterface.java
  javac HotelInterface.java

# Clean all class files
clean:
  $(RM) *.class
