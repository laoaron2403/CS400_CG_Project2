default: run

run: prepareRun clean

prepareRun: compile
  java Fronend

test: compile testData BackEndDeveloperTests testFrontend clean

compile: prepareFrontend prepareBackEnd prepareData

# test

## test Data
testData: prepareData DataWranglerTests.java
  java -jar junit5.jar --class-path . --scan-classpath
  $(RM) DataWranglerTests.class

DataWranglerTests.class: DataWranglerTests.java
  javac -cp .:junit5.jar DataWranglerTests.java -Xlint
 
## test Backend
BackEndDeveloperTests: prepareBackend BackEndDeveloperTests.class
  java -jar junit5.jar --class-path . --scan-classpath
  $(RM) BackEndDeveloperTests.class

BackEndDeveloperTests.class: BackEndDeveloperTests.java
  javac -cp .:junit5.jar BackEndDeveloperTests.java -Xlint
  
## testFrontend
testFrontend: prepareFrontend FrontendDeveloperTests.class
  java -jar junit5.jar --class-path . --scan-classpath

testFrontend.class: FrontendDeveloperTests.java
  javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint

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
prepareData: RoomDataReader.class Room.class

RoomDataReader.class: RoomDataReader.java RoomDataReaderInterface.class
  javac RoomDataReader.java
  
RoomDataReaderInterface.class: RoomDataReaderInterface.java
  javac RoomDataReaderInterface.java

Room.class: Room.java RoomInterface.class
  javac Room.java
  
RoomInterface.class: RoomInterface.java
  javac RoomInterface.java

# Clean all class files
clean:
  $(RM) *.class
