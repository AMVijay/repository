package vijay.poc.mqConnection;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Java Class to test IBM Websphere MQ Queue Message Posting.
 * 
 * @author Vijay(AM.Vijay@gmail.com)
 *
 */
public class MqConnectionApplication {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("jms-configuration.xml");

		JmsSender jmsSender = (JmsSender) context.getBean("jmsSender");

		try {
			long uniqueNumber = System.currentTimeMillis() % 1000;
			jmsSender.sendMessage("Test Message " + uniqueNumber);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
