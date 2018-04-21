package com.ArmiaJsona.emojiSearch.utils;

class PhraseSplitter {

    public static String splitPhraseWithEmojis(String phrase) {
        StringBuilder splittedPhraseBuilder = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isSurrogate(phrase.charAt(i))) {
                splittedPhraseBuilder.append(phrase.charAt(i))
                        .append(phrase.charAt(++i))
                        .append(" ");
            } else {
                splittedPhraseBuilder.append(phrase.charAt(i));
            }
        }
        return splittedPhraseBuilder.toString().trim();
    }

}
