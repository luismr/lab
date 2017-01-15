package br.com.singularideas.labs.knowhub.client.aboriginal.gui.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleInstanceThread extends Thread {
	public static final int PORT = 57738;
	private static final int QUEUE = 1;

	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;

	public SingleInstanceThread() {
		super();
		this.setName(this.getClass().getSimpleName());
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(PORT, QUEUE);
			while (true) {
				clientSocket = serverSocket.accept();
				clientSocket.close();
			}
		} catch (IOException ioe) {
			System.out.println("Error in SingleInstanceThread: " + ioe);
		}
	}
	
	public static boolean hasAnInstancesRunning() {
		boolean running = false;
		
		try {
			@SuppressWarnings({"resource" })
			Socket clientSocket = new Socket("localhost", PORT);
			running = clientSocket.isConnected();
		} catch (Exception e) {
			Thread instance = new SingleInstanceThread();
			instance.start();
		}
		
		return running;
	}
	
}
