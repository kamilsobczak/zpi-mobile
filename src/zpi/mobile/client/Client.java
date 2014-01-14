package zpi.mobile.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import android.annotation.SuppressLint;
import android.os.StrictMode;

public class Client {
	private boolean quit;

	private ServerConnection sConn;
	private Socket ss;

	public Client() {

		quit = false;

		// Thread ui = new Thread(new UI(this));
		// ui.start();
		// System.out.println("Client");

	}

	public void quit() {
		quit = true;
	}

	public String connect(String IP, int port) throws IOException {
		if (quit || port == 0 || IP == "")
			return "Błąd połączenia";

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
		BufferedReader res = new BufferedReader(new InputStreamReader(
				ss.getInputStream()));
		return res.readLine();
		// Thread serverConnection = new Thread(sConn);
		// serverConnection.start();
	}

	public void disconnect() {
		sConn.close();
	}

	@SuppressLint("NewApi")
	public void sendMsg(String message) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		try {
			sConn.sendMsg(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
