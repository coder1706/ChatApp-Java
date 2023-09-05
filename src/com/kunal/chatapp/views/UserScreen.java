package com.kunal.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kunal.chatapp.dao.UserDAO;
import com.kunal.chatapp.dto.UserDTO;
import com.kunal.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;
	private UserDAO userDAO = new UserDAO();

	public static void main(String[] args) {
		UserScreen window = new UserScreen();
	}
	
	private void login() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid, password);
		String message = "";
		try {
			if(userDAO.login(userDTO)) {
				setVisible(false);
				dispose();
				message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				DashBoard dashboard = new DashBoard(message);
				dashboard.setVisible(true);
				
			}else {
				message = "Invalid User ID or Password";
				JOptionPane.showMessageDialog(this, message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

	private void register() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid, password);
		
		try {
			int result = userDAO.add(userDTO);
			if(result == 0) {
				JOptionPane.showMessageDialog(this, "Record Failed!");
			}
			else  {
				JOptionPane.showMessageDialog(this, "Record added successfully!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error in DB Connection.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Generic exception...");
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(319, 40, 175, 75);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(409, 134, 301, 50);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel userIDLabel = new JLabel("User ID");
		userIDLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		userIDLabel.setBounds(196, 151, 107, 33);
		getContentPane().add(userIDLabel);
		
		JLabel pwdLabel = new JLabel("Password");
		pwdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdLabel.setBounds(196, 214, 107, 33);
		getContentPane().add(pwdLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(409, 219, 311, 26);
		getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginButton.setBounds(278, 324, 132, 41);
		getContentPane().add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		registerButton.setBounds(436, 324, 132, 41);
		getContentPane().add(registerButton);
		setTitle("LOGIN");
		setSize(833, 439);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
