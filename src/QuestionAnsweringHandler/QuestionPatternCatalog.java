package QuestionAnsweringHandler;

import java.util.HashMap;
import java.util.Map;

public class QuestionPatternCatalog {
	private Map<String, QuestionPattern> patterns;

	public QuestionPatternCatalog() {

		patterns = new HashMap<String, QuestionPattern>();

		patterns.put("yazar", new QuestionPattern(
				"writer kitabının adli adlı  kitabın kitabin kitabinin kitabi yazar yazari yazarı"));
		patterns.put("Turu", new QuestionPattern("genre kitabın kitabin  Turu türü "));
		patterns.put("turunde", new QuestionPattern("genre  kitabının kitabinin turu Turu türü turü"));
		patterns.put("turu",
				new QuestionPattern("genre kitabının adlı adli kitabin kitabın kitabinin turu Turu türü turü"));
		patterns.put("türü",
				new QuestionPattern("genre kitabının kitabinin adlı kitabın kitabin adli turu Turu türü turü"));
		patterns.put("fiyat",
				new QuestionPattern("price  kitabının kitabinin adlı adli kitabı kitabın kitabin fiyat fiyatı kaç "));
		patterns.put("lira", new QuestionPattern("price kitabının adlı kitap adli kitabı kitabin fiyat fiyatı kaç "));
		patterns.put("sayfa", new QuestionPattern(
				"page sayfa adli adlı kitabın kitabin kitabi kitap  kitabı sayısı sayisi kaç sayfa"));
		patterns.put("yayinevi",
				new QuestionPattern("publisher adlı adli kitabinin kitabının kitabın kitabin  yayınevi yayın"));
		patterns.put("yayınevi",
				new QuestionPattern("publisher adlı adli kitabin kitabın kitabının  kitabinin yayınevi yayın"));
		patterns.put("kitapları", new QuestionPattern("books kitapları nelerdir kitaplari"));
		patterns.put("basım", new QuestionPattern("edition kitabi kitabı kaçıncı basım"));
		patterns.put("basim", new QuestionPattern("edition kitabi kitabı kitabin kitabın  kaçıncı basım"));
		patterns.put("ilk", new QuestionPattern(
				"first adlı kitabinın kitabının kaçıncı ilk ılk basıldığı yıl yılı ne zaman basım"));

	}////// constructor.

	Map<String, QuestionPattern> getPatterns() {
		return patterns;

	}
}
