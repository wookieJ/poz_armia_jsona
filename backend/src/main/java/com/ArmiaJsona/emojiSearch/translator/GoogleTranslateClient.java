package com.ArmiaJsona.emojiSearch.translator;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Primary
@Service
public class GoogleTranslateClient implements TranslatorClient {

    private RestTemplate googleTranslateRestTemplate;

    public GoogleTranslateClient(RestTemplate googleTranslateRestTemplate) {
        this.googleTranslateRestTemplate = googleTranslateRestTemplate;
    }

    @Override
    public String getTranslationFor(String word) {
        String data = googleTranslateRestTemplate.exchange(
                "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=pl&dt=t&q=" + word,
                HttpMethod.GET, getUserAgent(), String.class).getBody();
        return retrieveTranslationFromResponse(data);
    }

    private HttpEntity getUserAgent() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Collections.singletonList("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        return new HttpEntity(headers);
    }

    private String retrieveTranslationFromResponse(String serviceResponse) {
        int startIndexOfTranslation = serviceResponse.indexOf("\"") + 1;
        int endIndexOfTranslation = serviceResponse.indexOf("\"", startIndexOfTranslation);
        return serviceResponse.substring(startIndexOfTranslation, endIndexOfTranslation);
    }
}
