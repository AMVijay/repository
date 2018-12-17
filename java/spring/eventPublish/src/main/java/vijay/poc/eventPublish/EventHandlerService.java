package vijay.poc.eventPublish;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandlerService {

	@EventListener
	public void handleEvent(CustomEvent event) {
		System.out.println("Handle Event" + event.getText());
	}
}
