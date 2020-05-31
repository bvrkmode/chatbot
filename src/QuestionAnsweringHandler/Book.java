package QuestionAnsweringHandler;

public class Book {

	private String genre, number_of_pages, first_edition_year, number_of_editions, price;
	private String author, publisher;

	public Book(String[] attributes) {

		super();
		this.genre = attributes[1];
		this.number_of_pages = attributes[4];
		this.first_edition_year = attributes[5];
		this.number_of_editions = attributes[6];
		this.price = attributes[7];
		this.author = attributes[2];
		this.publisher = attributes[3];

	}

	String getSubject(String subject) {

		switch (subject) {

		case "writer":
			subject = this.author;
			break;
		case "genre":
			subject = this.genre;
			break;
		case "price":
			subject = this.price;
			break;
		case "page":
			subject = this.number_of_pages;
			break;
		case "publisher":
			subject = this.publisher;
			break;
		case "edition":
			subject = this.number_of_editions;
			break;
		case "first":
			subject = this.first_edition_year;
			break;

		}

		return subject;

	}///// getSubject

}/////////////////////// class Book