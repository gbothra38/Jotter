package classes;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBFunctions {
	Connection connection; 
	public UserDBFunctions(Connection connection){
		super();
		this.connection=connection;
	}
	
	boolean addUser(User user) {
		String sql="insert into user(username,email,password)"+" values(?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,user.getUsernameString());
			statement.setString(2,user.getEmailString());
			statement.setString(3,user.getPassworString());
			int rows=statement.executeUpdate();
			if(rows>0) {
				System.out.println("Successfully inserted:"+user.getUsernameString());
				return true;
			}
			else {
				System.out.println("Not added");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}
	
	public User getUser(User user) {
		String sql="select * from user where username=? and password=? ";
		User current_user=null;
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,user.getUsernameString());
			statement.setString(2, user.getPassworString());
			
			ResultSet rows=statement.executeQuery();
			if(rows.next()) {
				System.out.println("Updated");
				current_user=new User();
				current_user.setUser_id(rows.getInt("user_id"));
				current_user.setUsernameString(rows.getString("username"));
				current_user.setEmailString(rows.getString("email"));
				current_user.setPassworString(rows.getString("password"));		
			}
			else {
				System.out.println("Not Updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_user;
		
	}
	
	public boolean deleteUser(int id){
		
		String sql="delete from user where user_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			int rows=statement.executeUpdate();
			if(rows==1) {
				System.out.println("Deleted");
				return true;
			}
			else {
				System.out.println("Not Deleted");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	boolean editUser(User user) {
		String sql="update user set username=?,email=?, password=? where user_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,user.getUsernameString());
			statement.setString(2,user.getEmailString());
			statement.setString(3, user.getPassworString());
			statement.setInt(4,user.getUser_id());
			
			int rows=statement.executeUpdate();
			if(rows>0) {
				System.out.println("Successfully Updated");
				return true;
			}
			else {
				System.out.println("Error");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}
	
	public User getUserByEmail(String emailString) {
		String sql="select * from user where email=? ";
		User current_user=null;
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,emailString);
			
			ResultSet rows=statement.executeQuery();
			if(rows.next()) {
				current_user=new User();
				current_user.setUser_id(rows.getInt("user_id"));
				current_user.setUsernameString(rows.getString("username"));
				current_user.setEmailString(rows.getString("email"));
				current_user.setPassworString(rows.getString("password"));		
			}
			else {
				System.out.println("Enter Correct email id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_user;
		
	}
	
	public boolean setToken(String email, String otp) {
		String sqlString="update user set token=? where email=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sqlString);
			if(otp=="")	otp=null;
			statement.setString(1, otp);
			statement.setString(2, email);
			int row=statement.executeUpdate();
			if(row>0) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkAndUpdatePassword(String email, String otp, String password) {
		// TODO Auto-generated method stub
		String sql="update user set password=? where email=? and token=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, password);
			statement.setString(2, email);
			statement.setString(3, otp);
			int rows=statement.executeUpdate();
			if(rows>0) {
				setToken(email,"");
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

}
