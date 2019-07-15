import java.util.Calendar;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Grand Circus Library!");
		
		boolean Valid = true;
		int userChoice = 0;
		int serialNumb = 0;
		do {
			System.out.println("Are you here to\n 1. Get a book?\n 2. Return a book?\n 3. Nahhhh save dem trees");
			userChoice = scnr.nextInt();
			
			if (userChoice == 1) {
				System.out.println(" 1. Search by Author\n 2. Search by Title\n 3. Search by Genre");
				userChoice = scnr.nextInt();
			} else if (userChoice == 2) {
				System.out.println("Enter the serial number please.");
				serialNumb = scnr.nextInt();
				Book book = new Book("Silent Lambs", "Hannibal", Status.onShelf, 7);
				Calendar test = null;
				test.set(2017, 06, 07);
				System.out.println(test);
				book.setDueDate(test);
				
				book.isExpired();

			}
			
		}while(!Valid);
		
		
		
		
		
	}
	
//	public static void getToday(String format) {
//		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date));
//	}

}
