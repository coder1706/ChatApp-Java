package com.kunal.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kunal.chatapp.dto.UserDTO;
import com.kunal.chatapp.utils.Encryption;

public class UserDAO {
	
	public boolean login(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String pass = Encryption.encryptPassword(new String(userDTO.getPassword()));
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			return rs.next();
		} finally {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
	}
	
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection con = null;
		Statement stmnt = null;
		
		try {
		// #1 Make Connection
		con = CommonDAO.createConnection();
		// #2 We do a Query
		stmnt = con.createStatement();
		//insert into users (userid, password) values('kunal', 'kunal123');
		int record = stmnt.executeUpdate("insert into users (userid, password) values('"+userDTO.getUserid()+"', '"+ Encryption.encryptPassword(new String(userDTO.getPassword())) +"');");
		return record;
		}
		finally {
			if(con!=null) con.close();
			if(stmnt!=null) stmnt.close();
		}
	}
}
