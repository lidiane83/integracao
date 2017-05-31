package br.com.lidiane.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.jms.MessageListener;
import org.apache.activemq.Message;

public class ClienteConsumidor {
	//Recebe as mensagens
	
	public static void main(String[] args) throws Exception{

        InitialContext context = new InitialContext(); 

        //imports do package javax.jms
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
    
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	Destination fila = (Destination) context.lookup("reserva");
        	MessageConsumer consumer = session.createConsumer(fila);
        
        	consumer.setMessageListener(new MessageListener(){

        	    public void onMessage(Message message){
        	        System.out.println(message);
        	    }

				@Override
				public void onMessage(javax.jms.Message arg0) {
					// TODO Auto-generated method stub
					
				}

        	});
        	
        	Message message = (Message) consumer.receive();
        	

        	session.close();
        	//fecha conexões
        	
        new Scanner(System.in).nextLine(); //parar o programa para testar a conexao

        connection.close();
        context.close();
	}

}
