package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class Sender {
	
	private static String url = "tcp://localhost:61616";
	private static String subject = "Thanh Tuong";

	
	public void senderMQ(String xml) throws JMSException{
		ConnectionFactory conFactory = new ActiveMQConnectionFactory();
		Connection con = conFactory.createConnection();
		con.start();
		
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);		
		Destination destination = session.createQueue(subject);
		
		MessageProducer mProducer = session.createProducer(destination);
		TextMessage tMessage = session.createTextMessage(xml);
		mProducer.send(tMessage);
		con.close();
	}
}
