package com.kunal.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.kunal.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessage();
//		System.out.println("Client Arrives...");
//		System.out.println("Enter the message: ");
//		Scanner scn = new Scanner(System.in);
//		String message = scn.nextLine();
//		
//		OutputStream out = socket.getOutputStream();
//		out.write(message.getBytes());
//		out.close();
//		scn.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}

	public void readMessage() {
		worker = new ClientWorker(in, textArea);
		worker.start();
	}
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Client client = new Client();
//
//	}

}
