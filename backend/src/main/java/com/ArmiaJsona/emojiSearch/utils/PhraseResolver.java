package com.ArmiaJsona.emojiSearch.utils;

import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.translator.TranslatorClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhraseResolver {

    private static final int NUMBER_OF_WORDS = 2;
    private final TranslatorClient translatorClient;
    private final EmojiClient emojiClient;

    public PhraseResolver(TranslatorClient translatorClient, EmojiClient emojiClient) {
        this.translatorClient = translatorClient;
        this.emojiClient = emojiClient;
    }

    public List<String> changeEmojisToText(String phrase) {
        phrase = PhraseParser.splitPhraseWithEmojis(phrase);
        List<String> phraseDividedIntoWords = new ArrayList<>();
        String[] splittedWordBySpace = phrase.split(" ");
        for (String word : splittedWordBySpace) {
            if (isWordEmoji(word)) {
                String emojiName = emojiClient.getEmojiNameByUnicode(word);
                phraseDividedIntoWords.add(emojiName);
            } else {
                phraseDividedIntoWords.add(word);
            }
        }
        return phraseDividedIntoWords;
    }

    public String getPhraseInEnglish(List<String> phraseDividedIntoWords) {
        StringBuilder builder = new StringBuilder();
        for (String word : phraseDividedIntoWords) {
            if (word.split(" ").length > NUMBER_OF_WORDS) {
                String[] wordsInEmoji = word.split(" ");
                word = wordsInEmoji[0] + " " + wordsInEmoji[1];
            }
            builder.append(word)
                    .append(" ");
        }
        return builder.toString().trim();
    }

    public String getPhraseInPolish(List<String> phraseDividedIntoWords) {
        StringBuilder builder = new StringBuilder();
        for (String word : phraseDividedIntoWords) {
            if (word.split(" ").length > NUMBER_OF_WORDS) {
                String[] wordsInEmoji = word.split(" ");
                word = wordsInEmoji[0] + " " + wordsInEmoji[1];
            }
            word = translatorClient.getTranslationFor(word);
            builder.append(word)
                    .append(" ");
        }
        return builder.toString().trim();
    }

    private boolean isWordEmoji(String word) {
        return Character.isSurrogate(word.charAt(0));
    }
}
