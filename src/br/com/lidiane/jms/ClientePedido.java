package br.com.lidiane.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;

import org.apache.activemq.Message;

public class ClientePedido {
	//Recebe as mensagens
	
	public static void main(String[] args) throws Exception{

        InitialContext context = new InitialContext(); 

       
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
    
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	Destination fila = (Destination) context.lookup("reserva");
        	        	
        	
        	
        	MessageProducer producer = session.createProducer(fila);
        	Message mensagem =  (Message) session.createTextMessage("<pedido> <reserva>1</reserva></pedido>");
        	
        //new Scanner(System.in).nextLine(); 

        connection.close();
        context.close();
	}

}
