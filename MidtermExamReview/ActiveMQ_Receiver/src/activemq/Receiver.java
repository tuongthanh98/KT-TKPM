package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class Receiver {
	private static String url = "tcp://localhost:61616";
	private static String subject = "Thanh Tuong";
	
	public String Receiver() throws JMSException{
		ConnectionFactory conFactory = new ActiveMQConnectionFactory();
		Connection con = conFactory.createConnection();
		con.start();
		
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);		
		Destination destination = session.createQueue(subject);
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		Message message = consumer.receive();
		if(message instanceof TextMessage) {
			TextMessage tMessage =(TextMessage) message;
			return tMessage.getText();
			
		}else if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			return objectMessage.toString();
		}
		return null; 
	}
	
	
}
