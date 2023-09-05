package com.kunal.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kunal.chatapp.network.Client;
import com.kunal.chatapp.utils.UserInfo;

@SuppressWarnings("serial")
public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;
//	public static void main(String[] args) {
//		try {
//			ClientChatScreen frame = new ClientChatScreen();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	private void sendIt() {
		String message = textField.getText();
//		if(message.length() == 0) return;
		textField.setText("");
		try {
			client.sendMessage(UserInfo.USER_NAME+": "+message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		client = new Client(textArea);
		
		setTitle(UserInfo.USER_NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 685, 281);
		contentPane.add(scrollPane);
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 10, 691, 272);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(5, 312, 521, 64);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendBtn = new JButton("Send");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sendBtn.setBounds(536, 328, 123, 32);
		contentPane.add(sendBtn);
		setVisible(true);
	}
}
