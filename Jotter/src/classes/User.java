package classes;

public class User {
	private int user_id;
	private String usernameString;
	private String emailString;
	private String passworString;
	private String tokenString;
	
	public User(String usernameString, String emailString, String passworString) {
		super();
		this.usernameString = usernameString;
		this.emailString = emailString;
		this.passworString = passworString;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsernameString() {
		return usernameString;
	}
	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	
	public String getPassworString() {
		return passworString;
	}
	public void setPassworString(String passworString) {
		this.passworString = passworString;
	}
	public void setTokenString(String tokenString) {
		this.tokenString=tokenString;
	}
	public String getTokenString() {
		return tokenString;
	}
	
	
}
