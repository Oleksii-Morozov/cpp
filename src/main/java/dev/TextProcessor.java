package dev;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

    public String extractUniqueWordsOfGivenLength(String text, int length) throws IllegalArgumentException {

        if(text == null || text.isEmpty() || length <= 0) {
            throw new IllegalArgumentException("Text cannot be null or empty and length cannot be less than 0");
        }


        Set<String> uniqueWords = getUniqueWordsOfGivenLength(text, length);
        StringBuilder result = new StringBuilder();
        for(String word : uniqueWords) {
            result.append(word).append("\n");
        }
        return result.toString();
    }

    private Set<String> getUniqueWordsOfGivenLength(String text, int length)  throws IllegalArgumentException {
        Set<String> uniqueWords = new HashSet<>();

        Pattern pattern = Pattern.compile("(?<=[.!?]\\s|\\A)[A-Z][^.!?]*\\?");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group();
            String[] words = sentence.split("\\W+");
            for (String word : words) {
                if (word.length() == length) {
                    uniqueWords.add(word);
                }
            }
        }

        return uniqueWords;
    }
}
