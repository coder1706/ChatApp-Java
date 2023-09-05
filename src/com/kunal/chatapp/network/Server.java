package com.kunal.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.kunal.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server started waiting for the Clients to Join...");
		handleClientRequest();
	}
	
	// Multiple Client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			// Per Client per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this);
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	
	/*
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server started and waiting for the Client connection...");
		Socket socket = serverSocket.accept();
		System.out.println("Client joins the Server");
		InputStream in = socket.getInputStream();
		byte[] b = in.readAllBytes();
		String str = new String(b);
		System.out.println("Message recieved from Client: " + str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		Server server = new Server();
	}

}
