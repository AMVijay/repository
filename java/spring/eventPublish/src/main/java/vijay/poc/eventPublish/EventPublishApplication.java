package vijay.poc.eventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventPublishApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EventPublishApplication.class, args);
	}
	
	@Autowired
	private EventPublisherService publisher;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running Boot Application");
		publisher.publishCustomEvent();
		
	}
}
