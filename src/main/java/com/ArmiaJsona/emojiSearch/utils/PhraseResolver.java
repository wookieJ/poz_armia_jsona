package com.ArmiaJsona.emojiSearch.utils;

import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.translator.TranslatorClient;
import com.ArmiaJsona.emojiSearch.utils.PhraseParser;
import org.springframework.stereotype.Service;

@Service
public class PhraseResolver {

    private final TranslatorClient translatorClient;
    private EmojiClient emojiClient;

    public PhraseResolver(TranslatorClient translatorClient, EmojiClient emojiClient) {
        this.translatorClient = translatorClient;
        this.emojiClient = emojiClient;
    }

    public String translatePhrasesWithEmojiToText(String phrase) {
        phrase = PhraseParser.splitPhraseWithEmojis(phrase);

        StringBuilder stringBuilder = new StringBuilder();
        String[] splittedWordBySpace = phrase.split(" ");
        for (String word : splittedWordBySpace) {
            if (isWordEmoji(word)) {
                String emojiName = emojiClient.getEmojiNameByUnicode(word);
                String emojiNameInPolish = translatorClient.getTranslation(emojiName);
                stringBuilder.append(emojiNameInPolish)
                        .append(" ");
            } else {
                stringBuilder.append(word)
                        .append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    private boolean isWordEmoji(String word) {
        return Character.isSurrogate(word.charAt(0));
    }
}
