package QuestionAnsweringHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {
	private List<String> fragments;
	private String pattern;
	private String answer;
	private String[] parsed_information;

	// static method kullanmamak için matcher,parser,infosource parametreleri
	// arguman alýnýr.
	Question(String question, Matcher_ matcher, Parser_ parser, InformationSource source) {
		parsed_information = new String[2];
		fragments = questionAnalyzer(question);
		pattern = matcher.getInfo(fragments);
		if (pattern != null) {
			parsed_information = parser.parse(fragments, pattern);

			answer = Answer.getAnswer(parsed_information[0], parsed_information[1]);

		} else
			answer = getErrorMessage();

		QuestionAnsweringHandler.transmit(answer);
	}

	List<String> questionAnalyzer(String question) {

		String regex_question = "\\b\\w+|[&'.,]\\b";
		Pattern pattern = Pattern.compile(regex_question, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(question);

		fragments = new ArrayList<String>();

		while (matcher.find()) {
			fragments.add(matcher.group());
		}

		return fragments;

	}

	String getErrorMessage() {

		return "Sonuc Bulunamadi :(";
	}

}
