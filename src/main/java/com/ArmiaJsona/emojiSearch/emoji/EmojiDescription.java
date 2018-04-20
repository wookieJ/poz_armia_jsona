package com.ArmiaJsona.emojiSearch.emoji;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmojiDescription {

    @JsonProperty("code")
    private String unicode;

    @JsonProperty("name")
    private String emojiName;

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public String getUnicode() {

        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }
}
