package QuestionAnsweringHandler;

import java.util.HashMap;
import java.util.Map;

public class QuestionPatternCatalog {
	private Map<String, QuestionPattern> patterns;

	public QuestionPatternCatalog() {

		patterns = new HashMap<String, QuestionPattern>();

		patterns.put("yazar", new QuestionPattern(
				"writer kitab�n�n adli adl�  kitab�n kitabin kitabinin kitabi yazar yazari yazar�"));
		patterns.put("Turu", new QuestionPattern("genre kitab�n kitabin  Turu t�r� "));
		patterns.put("turunde", new QuestionPattern("genre  kitab�n�n kitabinin turu Turu t�r� tur�"));
		patterns.put("turu",
				new QuestionPattern("genre kitab�n�n adl� adli kitabin kitab�n kitabinin turu Turu t�r� tur�"));
		patterns.put("t�r�",
				new QuestionPattern("genre kitab�n�n kitabinin adl� kitab�n kitabin adli turu Turu t�r� tur�"));
		patterns.put("fiyat",
				new QuestionPattern("price  kitab�n�n kitabinin adl� adli kitab� kitab�n kitabin fiyat fiyat� ka� "));
		patterns.put("lira", new QuestionPattern("price kitab�n�n adl� kitap adli kitab� kitabin fiyat fiyat� ka� "));
		patterns.put("sayfa", new QuestionPattern(
				"page sayfa adli adl� kitab�n kitabin kitabi kitap  kitab� say�s� sayisi ka� sayfa"));
		patterns.put("yayinevi",
				new QuestionPattern("publisher adl� adli kitabinin kitab�n�n kitab�n kitabin  yay�nevi yay�n"));
		patterns.put("yay�nevi",
				new QuestionPattern("publisher adl� adli kitabin kitab�n kitab�n�n  kitabinin yay�nevi yay�n"));
		patterns.put("kitaplar�", new QuestionPattern("books kitaplar� nelerdir kitaplari"));
		patterns.put("bas�m", new QuestionPattern("edition kitabi kitab� ka��nc� bas�m"));
		patterns.put("basim", new QuestionPattern("edition kitabi kitab� kitabin kitab�n  ka��nc� bas�m"));
		patterns.put("ilk", new QuestionPattern(
				"first adl� kitabin�n kitab�n�n ka��nc� ilk �lk bas�ld��� y�l y�l� ne zaman bas�m"));

	}////// constructor.

	Map<String, QuestionPattern> getPatterns() {
		return patterns;

	}
}
