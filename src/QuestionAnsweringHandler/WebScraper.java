package QuestionAnsweringHandler;

import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraper {
	WebScraper() {
	}

	String[] parser(String text, String regex) {

		Pattern pattern = Pattern.compile(regex);
		return pattern.split(text);
	}

	private void randomTimeRequests() {
		SecureRandom random_timer = new SecureRandom();
		long sleep_time = random_timer.nextInt(20000) + 10000;

		try {
			Thread.sleep(sleep_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}/////////// randomTimeRequests

	private void fileWriter(String[] file_row, BufferedWriter writer) {
		final int length = file_row.length;

		try {
			for (int i = 0; i < length - 1; ++i) {
				if (file_row[i] != null)
					writer.write(file_row[i].trim());
				else
					writer.write("NaN");
				writer.write(",");
			}
			writer.write(file_row[length - 1].trim());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}///////////// fileWriter

	/// Sadece bir kitabýn bilgilerini almak için.
	void bookInfoScraper(String url, String file_name) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_name), "utf-8"));
		bookInfoScraper(url, writer);
		writer.close();
	}

	private void bookInfoScraper(String url, BufferedWriter writer) throws IOException {

		String[] file_row = new String[8];///// Dosyaya yazýlacak bir satýrdaki,her bir attribute için
		Document inner_document = Jsoup.connect(url).get();

		for (Element inner_element : inner_document.select("div.product-info-list li")) {

			if (inner_element.hasText()) {
				String[] attr = parser(inner_element.text(), ":");
				String value = attr[1].trim();

				switch (attr[0]) {

				case "Kitap Adý":
					file_row[0] = value;
					break;
				case "Yazar":
					file_row[2] = value;
					break;
				case "Yayýnevi":
					file_row[3] = value;
					break;
				case "Sayfa Sayýsý":
					file_row[4] = value;
					break;
				case "Ýlk Baský Yýlý":
					file_row[5] = value;
					break;
				case "Baský Sayýsý":
					file_row[6] = value;
					break;

				}// switch
			} // if
		} ///// for//////

		file_row[1] = inner_document.select("div.breadcrumb.bg-dirty-white").select("li.lastElement a").text(); //// Türü
		file_row[7] = inner_document.select("div.current-price").text().replace(",", ".");//// Kitabýn fiyatý
		fileWriter(file_row, writer);
	}////////// boookInfoScraper

	void pageScraper(String url, String file_name) throws IOException {

		final String MAIN_URL = "https://www.idefix.com";
		Document document = Jsoup.connect(url).get();

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_name), "utf-8"));

		////////// Sayfadaki bütün kitaplarýn linkleri alýnýr.
		for (Element element : document.select("div.item.product-box-alternative")) {

			final String book_url = element.select("div.box-title a").attr("href");

			bookInfoScraper(MAIN_URL + book_url, writer);
			randomTimeRequests();
		} //// for
		writer.close();
	}
}
