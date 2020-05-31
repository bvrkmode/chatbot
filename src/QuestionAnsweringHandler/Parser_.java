package QuestionAnsweringHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser_ {
	String[] parse(List<String> fragments, String pattern_text) {

		String regex_ = "\\b\\w+|[&'.,]\\b";
		Pattern pattern = Pattern.compile(regex_, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(pattern_text);

		ArrayList<String> property = new ArrayList<String>();
		String name = "";

		while (matcher.find()) {
			property.add(matcher.group());
		}

		for (String fragment : fragments) {
			if (property.contains(fragment))
				break;
			name += " " + fragment;
		} // for

		String return_value[] = new String[2];
		return_value[0] = name.trim();
		return_value[1]=  pattern_text.split(" ")[0];

		return return_value;

	}
}
