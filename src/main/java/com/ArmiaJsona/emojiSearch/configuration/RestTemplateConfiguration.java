package com.ArmiaJsona.emojiSearch.configuration;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
}
