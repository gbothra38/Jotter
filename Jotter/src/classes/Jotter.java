package classes;

import java.util.Date;
import java.io.InputStream;
public class Jotter {
	private int jotter_id;
	private int user_id ;
	private String titleString;
	private String contentString;
	private Date pDate;
	private InputStream fileBlob;

	
	 public Jotter(int user_id, String titleString, String contentString,InputStream sb) {
			super();
			this.user_id=user_id;
			this.titleString = titleString;
			this.contentString = contentString;
			this.fileBlob=sb;
		}
		 
	
	public InputStream getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(InputStream fileBlob) {
		this.fileBlob = fileBlob;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	


	public Jotter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getJotter_id() {
		return jotter_id;
	}

	public void setJotter_id(int jotter_id) {
		this.jotter_id = jotter_id;
	}


	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
}