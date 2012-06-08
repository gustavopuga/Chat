package br.com.insite.chat.chat_room.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordFilter {

	private Map<String, String> wordsMap;

	public WordFilter() {
		wordsMap = new HashMap<String, String>();
	}

	public String process(String stringToBeProcessed) {

		if (stringToBeProcessed == null || stringToBeProcessed.trim().isEmpty())
			return "";

		String token;
		StringTokenizer stringTokenizer = new StringTokenizer(
				stringToBeProcessed, " ", true);
		StringBuilder newString = new StringBuilder();

		while (stringTokenizer.hasMoreTokens()) {
			token = stringTokenizer.nextToken();
			if (hasWordToBeFiltered(token))
				newString.append(wordsMap.get(token));
			else
				newString.append(token);
		}

		return newString.toString();
	}

	public boolean removeWordToBeFiltered(String wordToBeFiltered) {

		if (wordToBeFiltered == null || !wordsMap.containsKey(wordToBeFiltered))
			return false;

		wordsMap.remove(wordToBeFiltered);
		return true;

	}

	public boolean hasWordToBeFiltered(String wordToBeFiltered) {

		return wordsMap.containsKey(wordToBeFiltered);
	}

	public void addWordToBeFiltered(String wordToBeFiltered, String newWord) {

		if (wordToBeFiltered != null)
			wordsMap.put(wordToBeFiltered, newWord);
	}
}
