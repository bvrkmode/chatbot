package QuestionAnsweringHandler;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matcher_ {
	private boolean info = false;
	private QuestionPatternCatalog qp_catalog;
	private Map<String, QuestionPattern> patterns;

	Matcher_(QuestionPatternCatalog qp_catalog) {

		this.qp_catalog = qp_catalog;
	}

	String getInfo(List<String> fragments) {

		patterns = qp_catalog.getPatterns();

		for (String key : patterns.keySet()) {
			info = match(fragments, key);
			if (info == true)
				return patterns.get(key).getPattern();// QuestionPattern nesnesinde bulunan String tipindeki pattern
														// döndürülür.
		}

		return null;
	}///////// getInfo

	static boolean match(List<String> fragments, String key) {
		Pattern pattern = Pattern.compile(key, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher_;
		for (String fragment : fragments) {
			matcher_ = pattern.matcher(fragment);
			if (matcher_.find())
				return true;
		}

		return false;

	}/// match

}
