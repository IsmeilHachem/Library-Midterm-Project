import java.time.LocalDate;


public class Book extends Genre{
	private String title;
	private String author;
	protected String status;
	protected LocalDate dueDate;
	private int serialNum;
	
	public Book() {}
	
	public Book(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	//constructor to add values to variables
	public Book(String title, String author, String status, int serialNum) {
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public int getSerialNum() {
		return serialNum;
	}
	
	//Method to set the due date for a returned book
	//Still preventing users to manually set a due date
	public void returnBook() {
		dueDate = null;
	}
	
	//Method to create due date two weeks from today
	public void makeDueDate() {
		LocalDate today = LocalDate.now(); //Collect today's date
		
		dueDate = today.plusDays(14);
		System.out.println(dueDate); //Testing
		
	}
	
	//Method to determine if the book is being return on time or late of the due date
	public boolean isExpired() {
		LocalDate today = LocalDate.now(); //Collect today's date 
		
		//If statement to check due date to today's date
		if (today.isAfter(dueDate)) {
			System.out.println("Late"); //Testing
			return true;
		}else if(today.isBefore(dueDate)) {
			System.out.println("This book is early");
			return false;
		}else {
			System.out.println("On Time"); //Testing
			return false;
		}
		
	} 
	
}
