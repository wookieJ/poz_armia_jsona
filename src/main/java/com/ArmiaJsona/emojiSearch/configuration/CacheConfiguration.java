package com.ArmiaJsona.emojiSearch.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    @Bean
    public Cache<String,String> emojiCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(1,TimeUnit.DAYS)
                .maximumSize(1000)
                .build();
    }
}
