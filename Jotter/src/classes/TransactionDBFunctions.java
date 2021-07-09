package classes;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDBFunctions {

	Connection connection;
	public TransactionDBFunctions(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection=connection;
	}

	

	public boolean addTransaction(int sender_id, int receiver_id, int notes_id) {
		// TODO Auto-generated method stub
		String sqlString="insert into transaction(sender_id,receiver_id,notes_id) values(?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, sender_id);
			preparedStatement.setInt(2, receiver_id);
			preparedStatement.setInt(3, notes_id);
			int flag=preparedStatement.executeUpdate();
			if(flag>0) {
				System.out.println("Successfully Sent");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Jotter> searchList(int id, String queryString){
		List<Jotter> listJotters=new ArrayList<Jotter>();
		Jotter jotter=null;
		String sql="select * from jotter where title like ? or content like ? and  notes_id=(select notes_id from transaction where receiver_id=?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,"%"+ queryString + "%");
			statement.setString(2, "%"+ queryString + "%");
			statement.setInt(3, id);
			
			ResultSet rows=statement.executeQuery();
			while(rows.next()) {
				jotter=new Jotter();
				jotter.setJotter_id(rows.getInt("notes_id"));
				jotter.setUser_id(rows.getInt("user_id"));
				jotter.setJotter_id(rows.getInt("notes_id"));
				jotter.setTitleString(rows.getString("title"));
				jotter.setContentString(rows.getString("content"));
				jotter.setpDate(rows.getTimestamp("date"));
				Blob blob= rows.getBlob("file");
				InputStream inputStream = blob.getBinaryStream();
				jotter.setFileBlob(inputStream);
				//jotter.setFileBlob(rows.getBlob("file"));			
				listJotters.add(jotter);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJotters;
	}
	
	
	public List<Jotter> getNotes(int id){
		List<Jotter> listJotters=new ArrayList<Jotter>();
		Jotter jotter=null;
					 
		String sql="select notes_id from transaction where receiver_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet rows=statement.executeQuery();
			while(rows.next()) {
				DBConnector dbConnector=new DBConnector();
				Connection connection=dbConnector.makeConnection();
				JotterDBFunctions jotterDBFunction=new JotterDBFunctions(connection);
				jotter=jotterDBFunction.getNoteById(rows.getInt(1));
				
//	
//				jotter=new Jotter();
////				jotter.setJotter_id(rows.getInt("notes_id"));
//				jotter.setUser_id(rows.getInt("user_id"));
//				jotter.setJotter_id(rows.getInt("notes_id"));
//				jotter.setTitleString(rows.getString("title"));
//				jotter.setContentString(rows.getString("content"));
//				jotter.setpDate(rows.getTimestamp("date"));
//				Blob blob= rows.getBlob("file");
//				InputStream inputStream = blob.getBinaryStream();
//				jotter.setFileBlob(inputStream);			
				listJotters.add(jotter);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJotters;
	}
	
	
	public boolean deleteNote(int notesid,int receiverid){
		
		String sql="delete from transaction where notes_id=? and receiver_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, notesid);
			statement.setInt(2, receiverid);
			
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

}
