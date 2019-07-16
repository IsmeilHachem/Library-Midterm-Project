
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

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
		List<Book> books = LibraryInventoryUtil.readFile(); //collect list of books
		List<Book> goodReturns = new ArrayList<>(); //collect array of users choices from search menu
		// ArrayList<Book> books = new ArrayList<>();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Grand Circus Library!");

		boolean Valid = true;// test whether to continue
		int userChoice = 0;// collect user choice for numbered menu
		String input = "";// accept input for String choices in menu
		int serialNumb = 0;// collect serial number

		do {
			System.out.println("Are you here to\n 1. Get a book?\n 2. Return a book?\n 3. Nahhhh save dem trees");
			userChoice = scnr.nextInt();

			if (userChoice == 1) {
				// Display list of book objects
				displayBooks(books);

				// Prompt user for search choice
				System.out.println(
						"Select how you'd like to retrieve book:\n 1. Search by Title\n 2. Search by Author\n 3. Search by Genre");
				userChoice = scnr.nextInt();// collect user input
				scnr.nextLine();// clear scanner object to collect string in scanner

				// Applying for user input
				if (userChoice == 1) {
					System.out.println("Enter Title name: ");
					input = scnr.nextLine();					
					goodReturns = matchSearch(input.toLowerCase(), "title", books);	
					displayBooks(goodReturns);
				} else if (userChoice == 2) {
					System.out.println("Enter Author name: ");
					input = scnr.nextLine();
					goodReturns = matchSearch(input.toLowerCase(), "author", books);
					displayBooks(goodReturns);
			} 
//					else {
//					System.out.println("Enter Genre: ");
//					input = scnr.nextLine();
//					goodReturns = matchSearch(input.toLowerCase(), "genre", books);
//					displayBooks(goodReturns);
//				}

			} else if (userChoice == 2) {
				System.out.println("Enter the serial number please.");
				serialNumb = scnr.nextInt();

//				// Testing
//				Book book = new Book("Silent Lambs", "Hannibal", Status.onShelf.toString(), 7);
//				// LocalDate test = LocalDate.of(2019, 07, 07);
//				// System.out.println(test + "Main");
//				book.makeDueDate();
//				System.out.println(book.getDueDate() + "Due Date");
//				book.isExpired();
//				// Testing

			}

		} while (!Valid);

	}

	// Method to display Books from IO File
	public static void displayBooks(List<Book> bookList) {

		// Print out headers for the columns
		System.out.printf("%-40s\t%-20s\t%-15s\t%14s\n", "Title", "Author", "Status", "Serial Number");

		// Collect book items from IO File and print on single lines
		List<Book> books = bookList;
		for (Book b : books) {
			System.out.printf("%-40s\t%-20s\t%-15s\t%2d\n", b.getTitle(), b.getAuthor(), b.getStatus(),
					b.getSerialNum());
		}

	}

	// Method to match search parameters
	public static List<Book> matchSearch(String choice, String type ,List<Book> books) {
		List<Book> bookMatches = new ArrayList<>();
		
		// type is defining which BookMethod to call to search for what the user wants
		switch (type.toLowerCase()) {
		case "title": {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getTitle().toLowerCase().contains(choice)) {
					bookMatches.add(books.get(i));
				}
			}
		}
		case "author": {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getAuthor().toLowerCase().contains(choice)) {
					bookMatches.add(books.get(i));
				}
			}
		}
//		case "genre": {
//			for (int i = 0; i < books.size(); i++) {
//				if (books.get(i).getGenre().toLowerCase().equals(choice)) {
//					bookMatches.add(books.get(i));
//				}
//			}
//		}

		}

		return bookMatches;
	}
	
	public void checkOutBook() {}
}
