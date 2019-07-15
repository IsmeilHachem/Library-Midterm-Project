import java.util.Calendar;

public class Book {
	private String title;
	private String author;
	protected Status status;
	protected Calendar dueDate;
	private int serialNum;
	
	public Book() {}
	
	public Book(String title, String author, Status status, int serialNum) {
		this.title = title;
		this.author = author;
		this.status = status;
		this.serialNum = serialNum;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Calendar getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public int getSerialNum() {
		return serialNum;
	}
	
	public boolean isExpired() {
		Calendar today = Calendar.getInstance();
		if(today.YEAR > dueDate.YEAR) {
			if(today.MONTH > dueDate.MONTH) {
				if(today.DAY_OF_MONTH > dueDate.DAY_OF_MONTH) {
					System.out.println("Book is late dumbo. Bring momma over here to pay for your lateness my sire");
				}
			}	
		} else 
		return false;
		return false;
		
	} 
	
}
