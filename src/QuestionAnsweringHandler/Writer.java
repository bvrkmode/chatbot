package QuestionAnsweringHandler;

import java.util.ArrayList;
import java.util.List;

public class Writer {

	private List<String> books;

	public Writer(String name, String book_name) {
		super();
		books = new ArrayList<String>();
		books.add(book_name);
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

	List<String> getSubject(String subject) {

		List<String> writers_books = null;

		/// Þu an bir attribute var writer nesnesinde ancak ilerde çok sayýda attribute
		/// olacaðý için switch case kullanýldý
		switch (subject) {

		case "books":
			writers_books = books;
			break;
		}
		return writers_books;
	}
}
