## Pivotal Deployment POC using Spring Boot Web
This project is demonstrate how Spring Boot Web Application can be deployed in PCF (Pivotal Cloud Foundry).

## Technical Stack
- Java 8
- Spring Boot
- Spring Boot Web
- Spring Boot Thymeleaf
- Spring Boot Actuator

## Pre-requisite
Pivotal Account Setup
Installation of cf

## Steps to deploy in PCF
* Login to PCF from Project Folder `cf login -a api.run.pivotal.io`
  - Need to enter Email ID and Password
* Push the deployment JAR file to PCF using command `cf push firstExample -p target/firstExample-0.0.1-SNAPSHOT.jar` 
* Stop the application `cf stop firstExample`
* Logout from PCF `cf logout`

 