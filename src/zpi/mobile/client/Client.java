package zpi.mobile.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private boolean quit;
	
	private ServerConnection sConn;
	private Socket ss;
	
	public Client(){
		
		quit = false;
		
//		Thread ui = new Thread(new UI(this));
//		ui.start();		
//		System.out.println("Client");
				
	}
	public void quit(){
		quit = true;
	}
	public void connect(String IP, int port){
		if(quit || port==0 || IP=="")
			return;
			
		try {
			ss = new Socket(IP, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sConn = new ServerConnection(this, ss);
		//Thread serverConnection = new Thread(sConn);
		//serverConnection.start();
	}
	public void disconnect(){
		sConn.close();
	}

	public void sendMsg(String message) {
		try {
			sConn.sendMsg(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
