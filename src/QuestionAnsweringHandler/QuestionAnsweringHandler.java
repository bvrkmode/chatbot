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
			source.initializeFileNames(web_scraper);//////// veri �eker ,dosyalar� olu�turur.
		}

		source.fileReader(web_scraper);/////// Verileri dosyadan okur
        
		new Answer(source);//Answer nesnesi question ile info source aras�nda katman g�revi g�r�r.
		QuestionPatternCatalog qp = new QuestionPatternCatalog();

		///// �ok Say�da soru sorulmas� durumunda ya da ba�ka bi durumdan dolay� birden
		///// fazla matcher ve parser olmas� gerekebilir diye d���nd�k ondan dolay�,
		///// singleton s�n�f� kullanmad�k.Soru say�s� bilgisi QuestionHandler'da oldu�u i�in
		///// ve �ok say�da soru olursa ba�ka parser ve matcher nesneleri olu�turmak
		///// gerekebilir diye ba�lang�� i�in gereken matcher ve parser nesnelerini de burada olu�turduk.
		///// Yine de bir matcher nesnesi bir�ok soru i�in kullan�labilir belki binlerce, ayn� durum
		///// parser nesnesi i�in de ge�erli.
 
		
		Matcher_ matcher = new Matcher_(qp);
		Parser_ parser = new Parser_();

		//////////////

		/// kitaplar g�sterilir
		System.out.println("---------Books----------------------");
		for (String key : source.getBooklist().keySet())
			System.out.println(key);

		System.out.println("------------Books-------------------\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("-----------The Chatbot--------------");
		System.out.println("       (press enter for exit)");

		/////////// soru-cevap k�sm�

		/*
		 * �rnek olarak :
		 * 1984 adl� kitab�n yazar� 
		 * 1984 kitab�n�n yazar�
		 * 1984 yazar� 
		 * George Orwell kitaplar� 
		 * Ben Robot adl� kitab�n t�r� nedir
		 * 1984 ka� lira 
		 * 1984 t�r� nedir vs.... 
		 * 7 farkl� soru, farkl� kombinasyonlarda sorulabilir(Supplementary Spec 'da belirtilmi�tir).
		 * Yakla��k 400 kitap ve 150 yazar
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