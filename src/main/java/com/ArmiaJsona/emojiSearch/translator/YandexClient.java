package com.ArmiaJsona.emojiSearch.translator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class YandexClient implements TranslatorClient
{

    @Value("${Yandex.URL}")
    private String yandexUrl;

    private final static String API_KEY = "trnsl.1.1.20180420T194921Z.533bf209aa1802ee.c7debeea7ad734d254f9de0f2a6f21f5260d9c93";
    private final static String LANG = "en-pl";

    private RestTemplate yandexRestTemplate;

    public YandexClient(RestTemplate yandexRestTemplate)
    {
        this.yandexRestTemplate = yandexRestTemplate;
    }

    private HttpEntity getUserAgent()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Arrays.asList("Mozilla/5.0 (Windows NT 6.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36"));
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }

    @Override
    public String getTranslation(String word)
    {
        String url = yandexUrl + "?lang=" + LANG + "&key=" + API_KEY;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("text", word);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = yandexRestTemplate.postForEntity(url, request, String.class);
        String body = response.getBody();

        return body.substring(body.indexOf("<text>") + 6, body.indexOf("</text>"));
    }
}
