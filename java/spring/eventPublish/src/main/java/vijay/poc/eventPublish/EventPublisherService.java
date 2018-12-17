package vijay.poc.eventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherService {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	public void publishCustomEvent() {
		System.out.println("publishCustomEvent");
		CustomEvent customEvent = new CustomEvent();
		customEvent.setText("Hello, World");
		publisher.publishEvent(customEvent);		
	}
}

