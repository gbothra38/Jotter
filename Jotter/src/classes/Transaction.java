package classes;

public class Transaction {
	private int transaction_id;
	private int sender_id;
	private int receiver_id;
	private int notes_id;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int transaction_id, int sender_id, int receiver_id, int notes_id) {
		super();
		this.transaction_id = transaction_id;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.notes_id = notes_id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public int getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}

}
