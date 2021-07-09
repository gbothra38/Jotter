package classes;


import java.io.InputStream;
import java.sql.Blob;
//import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JotterDBFunctions {
	Connection connection;
	public JotterDBFunctions(Connection connection) {
		super();
		this.connection=connection;
	}
	
	boolean addNotes(Jotter jotter) {
		String sql="insert into jotter(user_id,title,content,file)"+" values(?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,jotter.getUser_id());
			statement.setString(2,jotter.getTitleString());
			statement.setString(3,jotter.getContentString());
			statement.setBlob(4, jotter.getFileBlob());
			int rows=statement.executeUpdate();
			if(rows>0) {
				System.out.println("Successfully Added");
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
	
	
	public List<Jotter> getNotes(int id){
		List<Jotter> listJotters=new ArrayList<Jotter>();
		Jotter jotter=null;
		
		String sql="select * from jotter where user_id=? order by date desc";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			
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
	
	public Jotter getNoteById(int note_id) {
		String sql="select * from jotter where notes_id=? ";
		Jotter jotter=null;
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,note_id);
			
			ResultSet rows=statement.executeQuery();
			if(rows.next()) {
				jotter=new Jotter();
				jotter.setJotter_id(note_id);
				jotter.setTitleString(rows.getString("title")); 
				jotter.setContentString(rows.getString("content"));
				jotter.setpDate(rows.getTimestamp("date"));
				Blob blob= rows.getBlob("file");
				InputStream inputStream = blob.getBinaryStream();
				jotter.setFileBlob(inputStream);
			}
			else {
				System.out.println("Not Updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jotter;
	}
	
	boolean updateNote(Jotter jotter) {
		String sql="update jotter set title=?,content=? where notes_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,jotter.getTitleString());
			statement.setString(2,jotter.getContentString());
			statement.setInt(3,jotter.getJotter_id());
			//statement.setBlob(4, jotter.getFileBlob());
			int rows=statement.executeUpdate();
			if(rows>0) {
				System.out.println("Successfully Updated");
				return true;
			}
			else {
				System.out.println("Not Updated");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteNote(int id){
			
		String sql="delete from jotter where notes_id=?";
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
	
	public List<Jotter> searchList(int id, String queryString){
		List<Jotter> listJotters=new ArrayList<Jotter>();
		Jotter jotter=null;
		
		String sql="select * from jotter where user_id=? and (title like ? or content like ?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2,"%"+ queryString + "%");
			statement.setString(3, "%"+ queryString + "%");
			
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
	
}
