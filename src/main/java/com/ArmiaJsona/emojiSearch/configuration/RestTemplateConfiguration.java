package com.ArmiaJsona.emojiSearch.configuration;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate emojipediaRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("https://emojipedia.org/emojipedia-api/")
                .setConnectTimeout(2000)
                .setReadTimeout(5000)
                .build();
    }

    @Bean
    public RestTemplate allegroRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("https://allegroapi.io/")
                .setConnectTimeout(2000)
                .setReadTimeout(1000)
                .build();
    }

    @Bean
    public RestTemplate yandexRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("https://translate.yandex.net/api/v1.5/tr/translate")
                .setConnectTimeout(2000)
                .setReadTimeout(1000)
                .build();
    }

    @Bean
    public RestTemplate googleTranslateRestTemplate(
    @Value("${googleTranslate.connectTimeout}") int connectionTimeout,
    @Value("${googleTranslate.connectionRequestTimeout}") int connectionRequestTimeout,
    @Value("${googleTranslate.readTimeout}") int readTimeout) {
        RestTemplate restTemplate = new RestTemplate(httpFactory(
                connectionTimeout,
                connectionRequestTimeout,
                readTimeout));
        return restTemplate;
    }

    private HttpComponentsClientHttpRequestFactory httpFactory(int connectionTimeout,
                                                               int connectionRequestTimeout,
                                                               int readTimeout) {
        HttpComponentsClientHttpRequestFactory requestConfig = new HttpComponentsClientHttpRequestFactory();
        requestConfig.setConnectTimeout(connectionTimeout);
        requestConfig.setConnectionRequestTimeout(connectionRequestTimeout);
        requestConfig.setReadTimeout(readTimeout);
        requestConfig.setHttpClient(httpClient());
        return requestConfig;
    }

    private HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setMaxConnTotal(10)
                .setMaxConnPerRoute(5)
                .build();
    }
}