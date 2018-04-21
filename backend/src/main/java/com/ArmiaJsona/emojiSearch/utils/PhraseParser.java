package com.ArmiaJsona.emojiSearch.utils;

class PhraseParser {
    public static String splitPhraseWithEmojis(String phrase) {
        StringBuilder splittedPhraseBuilder = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (isCharacterUnicode(phrase, i)) {
                splittedPhraseBuilder.append(phrase.charAt(i))
                        .append(phrase.charAt(++i))
                        .append(" ");
            } else {
                splittedPhraseBuilder.append(phrase.charAt(i));
            }
        }
        return splittedPhraseBuilder.toString().replaceAll("[ ]+"," ").trim();
    }

    private static boolean isCharacterUnicode(String phrase, int i) {
        return Character.isSurrogate(phrase.charAt(i));
    }
}
