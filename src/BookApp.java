
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
		List<Book> books = LibraryInventoryUtil.readFile(); // collect list of books
		List<Book> goodReturns = new ArrayList<>(); // collect array of users choices from search menu
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Grand Circus Library!");

		boolean Valid = true;// test whether to continue
		int userChoice = 0;// collect user choice for numbered menu
		String input = "";// accept input for String choices in menu
		int serialNumb = 0;// collect serial number

		do {
			// Added Validator for user choice
			userChoice = Validator.getInt(scnr,
					"Are you here to\n 1. Get a book?\n 2. Return a book?\n 3. Exit");

			if (userChoice == 1) {
				// Display list of book objects
				displayBooks(books);

				// Prompt user for search choice
				// collect user input
				userChoice = Validator.getInt(scnr,
						"Select how you'd like to retrieve book:\n 1. Search by Title\n 2. Search by Author\n 3. Search by Genre");

				// Applying user input choice
				if (userChoice == 1) {
					// Validate user input
					input = Validator.getString(scnr, "Enter Title name: ");
					goodReturns = matchSearch(input.toLowerCase(), "title", books);
					displayBooks(goodReturns);
					books = checkOutBook(input.toLowerCase(), books);

				} else if (userChoice == 2) {
					// Validate user input
					input = Validator.getString(scnr, "Enter Author name: ");
					goodReturns = matchSearch(input.toLowerCase(), "author", books);

					if (goodReturns.isEmpty()) {
						System.out.println("Sorry not available. Select again");
					} else {
						displayBooks(goodReturns);
						if (goodReturns.size() > 1) {
							// Prompt user for title
							input = Validator.getString(scnr, "Which title would you like?");
							goodReturns = matchSearch(input.toLowerCase(), "title", goodReturns);
							//displayBooks(goodReturns);// testing
							books = checkOutBook(input.toLowerCase(), books);
						} else {
							for (int i = 0; i < books.size(); i++) {
								if (books.get(i).getAuthor().toLowerCase().contains(input.toLowerCase())) {
									input = books.get(i).getTitle();
									break;
								}
							}
							books = checkOutBook(input.toLowerCase(), books);
						
						}
					}

				} else {
					input = Validator.getString(scnr, "Enter Genre name: ");
					goodReturns = matchSearch(input.toLowerCase(), "genre", books);
					displayBooks(goodReturns);
					if (goodReturns.isEmpty()) {
						System.out.println("That genre is not available."); 
					} else {
						if (goodReturns.size() > 1) {
							input = Validator.getString(scnr, "Which title would you like?");
							goodReturns = matchSearch(input.toLowerCase(), "title", goodReturns);
							books = checkOutBook(input.toLowerCase(), books);
							if (goodReturns.size() > 1) {
								System.out.println("We actually have more than one book with that word. Please select an author for your choice to be more clear.");
								input = Validator.getString(scnr, "Which author would you like?");
								goodReturns = matchSearch(input.toLowerCase(), "author", books);
								for (int i = 0; i < books.size(); i++) {
									if (books.get(i).getAuthor().toLowerCase().contains(input.toLowerCase())) {
										input = books.get(i).getTitle();
										break;
									}
								}
							}
						} else {
							for (int i = 0; i < books.size(); i++) {
								if (books.get(i).getGenre().toLowerCase().contains(input.toLowerCase())) {
									input = books.get(i).getTitle();
									break;
								}
							}
						}
					
					}
	

				}

				//Return Book options
			} else if (userChoice == 2) {

				// use validator class to verify input
				serialNumb = Validator.getInt(scnr, "Enter the serial number please.");
				
				books = bookReturn(serialNumb, books);
				displayBooks(books);//testing

			}else {
				System.out.println("Thank you for visiting have a wonderful day!!");
				try {
					LibraryInventoryUtil.rewriteFile(books);
				} catch (IOException e) {
					System.out.println("Unable to write file");
				}
				Valid = false;
			}
			
			

		} while (Valid);

		scnr.close();// close scanner object

	}

	// Method to display Books from IO File
	public static void displayBooks(List<Book> bookList) {

		// Print out headers for the columns
		System.out.printf("%-40s\t%-20s\t%-15s\t%-14s\t%-15s\n", "Title", "Author", "Status", "Serial Number", "Genre");

		// Collect book items from IO File and print on single lines
		List<Book> books = bookList;
		for (Book b : books) {
			System.out.printf("%-40s\t%-20s\t%-15s\t%014d\t%-15s\n", b.getTitle(), b.getAuthor(), b.getStatus(),
					b.getSerialNum(), b.getGenre());
		}

	}

	// Method to match search parameters
	public static List<Book> matchSearch(String choice, String type, List<Book> books) {
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
		case "genre": {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getGenre().toLowerCase().equals(choice)) {
					bookMatches.add(books.get(i));
				}
			}
		}

		}

		return bookMatches;
	}

	public static List<Book> checkOutBook(String choice, List<Book> books) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().toLowerCase().contains(choice)) {
				if (books.get(i).getStatus().toLowerCase().equals(Status.onShelf.toString().toLowerCase())) {
					books.get(i).makeDueDate();
					System.out.println(
							"Successful transaction. Book is now checked out until " + books.get(i).getDueDate() + ".");
					books.get(i).setStatus(Status.checkedOut.toString());
				} else {
					System.out
							.println("Book is not available. It's checked out until "
									+ books.get(i).getDueDate());
				}
			}
		}
		return books;

	}

	public static List<Book> bookReturn(int serialNum, List<Book> books) {

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getSerialNum() == serialNum) {
				books.get(i).setStatus(Status.onShelf.toString());
				if (books.get(i).isExpired() == true) {
					System.out.println("You owe $10.00 for each day you're late!!");
				}
				books.get(i).returnBook();

			}
		}

		return books;
	}
}
