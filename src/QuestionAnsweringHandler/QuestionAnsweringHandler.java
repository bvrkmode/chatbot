package QuestionAnsweringHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class QuestionAnsweringHandler {

	public QuestionAnsweringHandler() {

	}

	void askQuestion(String input_question, Matcher_ matcher, Parser_ parser, InformationSource source) {

		new Question(input_question, matcher, parser, source);
	}

	public static void main(String[] args) throws IOException {

		String input_question = "";
		Scanner scanner = new Scanner(System.in);

		///////////////// Start Up kod ///////////////////////////

		WebScraper web_scraper = new WebScraper();
		InformationSource source = new InformationSource();

		if (!Files.exists(Paths.get("FileNames.txt"))) {
			source.initializeFileNames(web_scraper);//////// veri çeker ,dosyalarý oluþturur.
		}

		source.fileReader(web_scraper);/////// Verileri dosyadan okur
        
		new Answer(source);//Answer nesnesi question ile info source arasýnda katman görevi görür.
		QuestionPatternCatalog qp = new QuestionPatternCatalog();

		///// Çok Sayýda soru sorulmasý durumunda ya da baþka bi durumdan dolayý birden
		///// fazla matcher ve parser olmasý gerekebilir diye düþündük ondan dolayý,
		///// singleton sýnýfý kullanmadýk.Soru sayýsý bilgisi QuestionHandler'da olduðu için
		///// ve çok sayýda soru olursa baþka parser ve matcher nesneleri oluþturmak
		///// gerekebilir diye baþlangýç için gereken matcher ve parser nesnelerini de burada oluþturduk.
		///// Yine de bir matcher nesnesi birçok soru için kullanýlabilir belki binlerce, ayný durum
		///// parser nesnesi için de geçerli.
 
		
		Matcher_ matcher = new Matcher_(qp);
		Parser_ parser = new Parser_();

		//////////////

		/// kitaplar gösterilir
		System.out.println("---------Books----------------------");
		for (String key : source.getBooklist().keySet())
			System.out.println(key);

		System.out.println("------------Books-------------------\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("-----------The Chatbot--------------");
		System.out.println("       (press enter for exit)");

		/////////// soru-cevap kýsmý

		/*
		 * örnek olarak :
		 * 1984 adlý kitabýn yazarý 
		 * 1984 kitabýnýn yazarý
		 * 1984 yazarý 
		 * George Orwell kitaplarý 
		 * Ben Robot adlý kitabýn türü nedir
		 * 1984 kaç lira 
		 * 1984 türü nedir vs.... 
		 * 7 farklý soru, farklý kombinasyonlarda sorulabilir(Supplementary Spec 'da belirtilmiþtir).
		 * Yaklaþýk 400 kitap ve 150 yazar
		 */

		QuestionAnsweringHandler qa_handler = new QuestionAnsweringHandler();

		while (!(input_question = scanner.nextLine()).trim().equals("")) {
			qa_handler.askQuestion(input_question, matcher, parser, source);
		}

		scanner.close();
	}///////// main func

	static void transmit(String answer) {

		System.out.println(answer);
	}

}