package QuestionAnsweringHandler;

public class Answer {
	static private InformationSource source;

	public Answer(InformationSource source) {
		super();
		this.source = source;
	}

	static String getAnswer(String key, String subject) {

		return source.getAnswer(key.trim(), subject);// answer

	}
}
