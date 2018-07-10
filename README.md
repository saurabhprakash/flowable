#### Flowable Bootstrap Applications

###### Prerequisite: Java 1.8 and maven installed on system

#### Projects: 

- HolidayRequestFlowable: Command line flowable application
  - Link to doc of this project: https://www.flowable.org/docs/userguide/index.html#getting.started.command.line
- bootstrap: Springboot Flowable bootstrap application
  - Running this application: ```./mvnw spring-boot:run```
  - Build the JAR file: ```./mvnw clean package```

#### Some helpers:
 - For installing mvnw, run following command: ```mvn -N io.takari:maven:wrapper``` (More on mvnw can be read here: https://github.com/takari/maven-wrapper)
 
#### Current Sample Requests:
 - Starting loan application: curl -H "Content-Type: application/json" -d '{"name" : "saurabh prakash", "email": "saurabh@kuliza.com", "phoneNumber":"xxxxxxxxxxx"}' http://localhost:8090/get-loan-application
 - Get list of loan application taskid: curl http://localhost:8090/manage-application
 - Approve loan application: curl -H "Content-Type: application/json" -d '{"taskId": "7be0dfa1-8438-11e8-9c1e-86e776a6b7f2"}' http://localhost:8090/manage-application/approve
 
Flowable/Activiti tables explained: https://www.activiti.org/userguide/#database.tables.explained
