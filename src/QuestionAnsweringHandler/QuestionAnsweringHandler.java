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
			source.initializeFileNames(web_scraper);//////// veri çeker ,dosyaları oluşturur.
		}

		source.fileReader(web_scraper);/////// Verileri dosyadan okur
        
		new Answer(source);//Answer nesnesi question ile info source arasında katman görevi görür.
		QuestionPatternCatalog qp = new QuestionPatternCatalog();

		///// Çok Sayıda soru sorulması durumunda ya da başka bi durumdan dolayı birden
		///// fazla matcher ve parser olması gerekebilir ondan dolayı,
		///// singleton sınıfı kullanmadım.Soru sayısı bilgisi QuestionHandler'da olduğu için
		///// ve çok sayıda soru olursa başka parser ve matcher nesneleri oluşturmak
		///// gerekebilir diye başlangıç için gereken matcher ve parser nesnelerini de burada oluşturduk.
		///// Yine de bir matcher nesnesi birçok soru için kullanılabilir belki binlerce, aynı durum
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

		/////////// soru-cevap kısmı

		/*
		 * örnek olarak :
		 * 1984 adlı kitabın yazarı 
		 * 1984 kitabının yazarı
		 * 1984 yazarı 
		 * George Orwell kitapları 
		 * Ben Robot adlı kitabın türü nedir
		 * 1984 kaç lira 
		 * 1984 türü nedir vs.... 
		 * 7 farklı soru, farklı kombinasyonlarda sorulabilir.
		 * Yaklaşık 400 kitap ve 150 yazar
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
