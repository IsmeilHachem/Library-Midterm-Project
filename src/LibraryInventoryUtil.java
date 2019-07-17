
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryInventoryUtil {

	// Modify path to file here.
	private static FileLinesHelper linesHelper = new FileLinesHelper("inventory.txt");

	// Modify this method as necessary to convert a line of text from the file to a
	// new item instance
	private static Book convertLineToItem(String line) {
		String[] parts = line.split(",");
		Book books = new Book(stringLocalDate(parts[5]));
		books.setTitle(parts[0]);
		books.setAuthor(parts[1]);
		books.setStatus(parts[2]);
		books.setSerialNum(Integer.parseInt(parts[3]));
		books.setGenre(parts[4]);

		return books;
	}

	// Modify this method as necessary to convert an item instance to a line of text
	// in the file
	private static String convertItemToLine(Book books) {
		return String.format("%s,%s,%s,%d,%s,%s", books.getTitle(), books.getAuthor(), books.getStatus(),
				books.getSerialNum(), books.getGenre(), books.getDueDate());
		// string tab number
	}

	public static List<Book> readFile() {
		List<String> lines = linesHelper.readFile();
		List<Book> items = new ArrayList<>(lines.size());
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;

//		Or with streams...
//		return linesHelper.readFile().stream().map(PlayerFileUtil::convertLineToItem).collect(Collectors.toList());
	}

	public static void rewriteFile(List<Book> items) throws IOException {
		List<String> lines = new ArrayList<>(items.size());
		for (Book item : items) {
			lines.add(convertItemToLine(item));
		}
		linesHelper.rewriteFile(lines);
//		Or with streams...
//		linesHelper.rewriteFile(items.stream().map(PlayerFileUtil::convertItemToLine).collect(Collectors.toList()));
	}

	public static void appendToFile(Book item) throws IOException {
		String line = convertItemToLine(item);
		linesHelper.appendToFile(line);
	}

	public static LocalDate stringLocalDate(String str) {
		
		
		if(!str.equals("null")) {
			LocalDate myDate = LocalDate.parse(str);
			return myDate;
		} else {	
			return null;			
		}	
//		return null;
	}
}