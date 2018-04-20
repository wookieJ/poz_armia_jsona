package com.ArmiaJsona.emojiSearch;

import com.ArmiaJsona.emojiSearch.utils.PhraseSplitter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmojiSearchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmojiSearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String result = PhraseSplitter.splitPhraseWithEmojis("\uD83C\uDF47\uD83D\uDC3BHello");
    }
}
