package vijay.poc;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQQueue;

import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueSender;
import com.ibm.mq.jms.MQQueueSession;
import com.ibm.msg.client.wmq.WMQConstants;

/**
 * Java Class to send Test Message to IBM Websphere MQ.
 * @author Vijay (AM.Vijay@gmail.com)
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			MQQueueConnectionFactory cf = new MQQueueConnectionFactory();
			cf.setHostName("hostname.domain.com");
			cf.setPort(portNumber);
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);

			cf.setQueueManager("MQManagerName");
			cf.setChannel("MQ Client Channel Name");
//			MQQueueConnection connection = (MQQueueConnection) cf.createQueueConnection();

			MQQueueConnection connection = (MQQueueConnection) cf.createQueueConnection("userid","password");

			MQQueueSession session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			MQQueue queue = (MQQueue) session.createQueue("queue:///MQNAME");

			MQQueueSender sender = (MQQueueSender) session.createSender(queue);

			long uniqueNumber = System.currentTimeMillis() % 1000;

			TextMessage message = (TextMessage) session.createTextMessage("Basic Queue Test " + uniqueNumber);

			// Start the connection
			connection.start();
			sender.send(message);

			System.out.println("Sent message to Queue MyTestQueue: " + message.getText());
			sender.close();
			session.close();
			connection.close();
			System.out.println("Message Sent OK.\n");
		} catch (JMSException jmsex) {
			System.out.println(jmsex);
			System.out.println("Message Send Failure\n");
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("Message Send Failure\n");
		}
	}
}
