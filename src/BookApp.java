
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		Path path = Paths.get("inventory.txt");
		
		if (Files.notExists(path)) {// catch IOExceptions which always may occur when dealing with files.
			try {
				Files.createFile(path);
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Grand Circus Library!");
		
		boolean Valid = true;
		int userChoice = 0;
		String input = "";
		int serialNumb = 0;
		do {
			System.out.println("Are you here to\n 1. Get a book?\n 2. Return a book?\n 3. Nahhhh save dem trees");
			userChoice = scnr.nextInt();
			
			if (userChoice == 1) {
				System.out.println(" 1. Search by Author\n 2. Search by Title\n 3. Search by Genre");
				userChoice = scnr.nextInt();
				scnr.nextLine();
					if(userChoice == 1) {
						System.out.println("Enter Author name: ");
						input = scnr.nextLine();
						//call to check list
					}else if (userChoice == 2) {
						System.out.println("Enter Title name: ");
						input = scnr.nextLine();
						//call to check list
					}else {
						System.out.println("Enter Genre: ");
						input = scnr.nextLine();
						//call to check list
					}
						
					
			} else if (userChoice == 2) {
				System.out.println("Enter the serial number please.");
				serialNumb = scnr.nextInt();
				
				//Testing
				Book book = new Book("Silent Lambs", "Hannibal", Status.onShelf.toString(), 7);
				//LocalDate test = LocalDate.of(2019, 07, 07);				
				//System.out.println(test + "Main");
				book.makeDueDate();	
				System.out.println(book.getDueDate() + "Due Date");
				book.isExpired();
				//Testing
				
			}
			
		}while(!Valid);
		
		
		
		
		
	}
	
//	public static void getToday(String format) {
//		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date));
//	}

}
