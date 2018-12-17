# POC for Event Publish and Listener using Spring
POC on Event Publish and Listening.

## Technical Stack
* Java 1.8
* Spring Boot 2.0.0.RELEASE

# Notes
* Use Command mvn spring-boot:run to run the Application and Test it.
* `EventPublishApplication` publishes an `CustomEvent` event using Service `EventPublisherService`.
* `EventHandlerService` handler service receive the event and process the Event.