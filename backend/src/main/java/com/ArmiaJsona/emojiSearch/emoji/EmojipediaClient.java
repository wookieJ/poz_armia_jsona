package com.ArmiaJsona.emojiSearch.emoji;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class EmojipediaClient implements EmojiClient {

    private String emojipediaUrl;

    private final RestTemplate emojipediaRestTemplate;

    public EmojipediaClient(RestTemplate emojipediaRestTemplate,
                            @Value("${Emojipedia.URL}") String emojipediaUrl) {
        this.emojipediaRestTemplate = emojipediaRestTemplate;
        this.emojipediaUrl = emojipediaUrl;
    }

    @Override
    public String getEmojiNameByUnicode(String unicode) {
        String payload = emojipediaRestTemplate.exchange(emojipediaUrl + unicode,
                HttpMethod.GET,
                getUserAgent(),
                String.class).getBody();

        String jsonData = getEmojiDescriptionAsJson(payload);
        return getEmojiNameFromJson(jsonData);
    }

    private String getEmojiNameFromJson(String jsonData) {
        final String EMOJI_NAME = "name: ";
        int startIndexOfName = jsonData.indexOf(EMOJI_NAME) + EMOJI_NAME.length();
        int endIndexOfName = jsonData.indexOf("'", startIndexOfName + 1);
        return jsonData.substring(startIndexOfName + 1, endIndexOfName);
    }

    private String getEmojiDescriptionAsJson(String payload) {
        final String startDataPhrase = "window.emojiData = ";
        int indexOfStartData = payload.indexOf(startDataPhrase)
                + startDataPhrase.length();
        int indexOfEndData = payload.indexOf("};", indexOfStartData) + 2;
        return payload.substring(indexOfStartData, indexOfEndData - 1);
    }

    private HttpEntity getUserAgent() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Collections.singletonList("Mozilla/5.0 (Windows NT 6.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36"));
        return new HttpEntity(headers);
    }
}
