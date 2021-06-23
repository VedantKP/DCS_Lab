/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pubsub;

/**
 *
 * @author vedan
 */
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.*;
public class Publisher {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public static void main(String[] args) throws JMSException {
		System.out.println(url);
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("StockQuote");
		MessageProducer producer = session.createProducer(topic);

		
		 TextMessage message = session.createTextMessage();
		 message.setText("Message from Vedant");

		
//		Here we are sending the message!
		producer.send(message);
		System.out.println("Sent message '" + message.getText() + "'");
		connection.close();
	
	}
}