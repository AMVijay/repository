package vijay.poc.mqConnection;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * This is the JMS Sender class for the functions related to send messages to Queue
 * 
 * @author Vijay(AM.Vijay@gmail.com) 
 */
public class JmsSender {


	private JmsTemplate jmsQueueTemplate;
	private String queueName;

	public void sendMessage(final String message) throws JMSException {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}


	public JmsTemplate getJmsQueueTemplate() {
		return jmsQueueTemplate;
	}

	public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
		this.jmsQueueTemplate = jmsQueueTemplate;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
}
