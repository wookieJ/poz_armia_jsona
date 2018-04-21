package com.ArmiaJsona.emojiSearch.utils;

import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.translator.TranslatorClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhraseResolver {

    public static final int NUMBER_OF_WORDS = 2;
    private final TranslatorClient translatorClient;
    private final EmojiClient emojiClient;
    private final List<String> phrase;

    public PhraseResolver(TranslatorClient translatorClient, EmojiClient emojiClient) {
        this.translatorClient = translatorClient;
        this.emojiClient = emojiClient;
        phrase = new ArrayList<>();
    }

    public void changeEmojisToText(String phrase) {
        phrase = PhraseParser.splitPhraseWithEmojis(phrase);

        String[] splittedWordBySpace = phrase.split(" ");
        for (String word : splittedWordBySpace) {
            if (isWordEmoji(word)) {
                String emojiName = emojiClient.getEmojiNameByUnicode(word);
                this.phrase.add(emojiName);
            } else {
                this.phrase.add(word);
            }
        }
    }

    public String getPhraseInEnglish() {
        StringBuilder builder = new StringBuilder();
        for(String word : this.phrase) {
            if(word.length() > NUMBER_OF_WORDS) {
                String[] wordsInEmoji = word.split(" ");
                word = wordsInEmoji[0] + wordsInEmoji[1];
            }
            builder.append(word)
                    .append(" ");
        }
        return builder.toString().trim();
    }

    public String getPhraseInPolish() {
        StringBuilder builder = new StringBuilder();
        for(String word : this.phrase) {
            if(word.length() > NUMBER_OF_WORDS) {
                String[] wordsInEmoji = word.split(" ");
                word = wordsInEmoji[0] + wordsInEmoji[1];
                word = translatorClient.getTranslationFor(word);
            }
            builder.append(word)
                    .append(" ");
        }
        return builder.toString().trim();
    }

    private boolean isWordEmoji(String word) {
        return Character.isSurrogate(word.charAt(0));
    }
}
