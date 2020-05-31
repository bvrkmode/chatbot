package QuestionAnsweringHandler;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationSource {
	private String answer;
	private Writer writer;
	private Book book;

	private Map<String, Book> booklist = new HashMap<String, Book>();
	private Map<String, Writer> writerlist = new HashMap<String, Writer>();

	InformationSource() {
	}

	String getAnswer(String key, String subject) {

		List<String> books = isAboutWriter(key, subject);
		if (books != null) {
			for (String book : books)
				System.out.println(book);
			return "\n\n------------------ Yazarýn kitaplarý listelenmiþtir-------------- \n\n";
		}

		book = booklist.get(key);//HashMap veri yapýsýnda tuttuk.
		if (book != null)
			return answer = book.getSubject(subject);/// answer

		return null;

	}

	List<String> isAboutWriter(String key, String subject) {
		writer = writerlist.get(key);//HashMap 

		if (writer != null)
			return writer.getSubject(subject);/// answer

		return null;

	}// isAboutWriter


	//// Start Up KOD
	
	private void createFiles(String url, WebScraper web_scraper, BufferedWriter writer) throws IOException {

		String[] url_parts = web_scraper.parser(url, "/");
		int length = url_parts.length;

		String file_name = url_parts[length - 2] + ".txt";

		writer.write(file_name);
		writer.newLine();

		web_scraper.pageScraper(url, file_name);
	}

	void initializeFileNames(WebScraper web_scraper) {

		try {
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("FileNames.txt"), "utf-8"));

			createFiles("https://www.idefix.com/CokSatanlar/Kitap", web_scraper, writer);
			createFiles("https://www.idefix.com/Yazar/fyodor-mihaylovic-dostoyevski/s=7112", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/Lev-Nikolayevic-Tolstoy/s=7113", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/George-Orwell/s=253774", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/Sabahattin-Ali/s=253771", web_scraper, writer);
			createFiles("https://www.idefix.com/Yazar/franz-kafka/s=173416", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/Johann-Wolfgang-Von-Goethe/s=244805", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/irvin-D--Yalom/s=214720", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/Jack-London/s=173342", web_scraper, writer);
			createFiles("https://www.idefix.com/yazar/Stefan-Zweig/s=125486", web_scraper, writer);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//////////// initializeFileNames

	void fileReader(WebScraper web_scraper) throws IOException {

		String file_name;

		BufferedReader outer_file_reader = new BufferedReader(new FileReader("FileNames.txt"));
		BufferedReader inner_file_reader;

		while ((file_name = outer_file_reader.readLine()) != null) {
			Reader reader = new InputStreamReader(new FileInputStream(file_name), "UTF-8");
			inner_file_reader = new BufferedReader(reader);
			rowParser(inner_file_reader, web_scraper);
		}
		outer_file_reader.close();
	}//////// fileReader

	private void rowParser(BufferedReader inner_file_reader, WebScraper web_scraper) throws IOException {
		final int LENGTH = 8;
		String file_row;
		String[] attributes_;

		//// Dosya baþý karakterini atlamak için
		inner_file_reader.mark(1);
		if (inner_file_reader.read() != 0xFEFF)
			inner_file_reader.reset();

		while ((file_row = inner_file_reader.readLine()) != null) {
			attributes_ = web_scraper.parser(file_row, ",");

			if (attributes_.length == LENGTH) {
				{
					booklist.put(attributes_[0], new Book(attributes_));
					if (!writerlist.containsKey(attributes_[2]))
						writerlist.put(attributes_[2], new Writer(attributes_[2], attributes_[0]));
					else
						writerlist.get(attributes_[2]).getBooks().add(attributes_[0]);

				}
			} /// outer_if
		} // while
	}////////////// rowParser

	////////// END(Start Up KOD)
	
	
	public Map<String, Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(Map<String, Book> booklist) {
		this.booklist = booklist;
	}

	public Map<String, Writer> getWriterlist() {
		return writerlist;
	}

	public void setWriterlist(Map<String, Writer> writerlist) {
		this.writerlist = writerlist;
	}

}
