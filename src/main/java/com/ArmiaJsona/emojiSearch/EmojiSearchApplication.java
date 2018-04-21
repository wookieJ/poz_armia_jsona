package com.ArmiaJsona.emojiSearch;

import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.allegro.AllegroService;
import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.translator.TranslatorClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmojiSearchApplication implements CommandLineRunner {

    private final EmojiClient emojipediaClient;
    private final AllegroClient allegroClient;
    private final TranslatorClient yandexClient;
    private final PhraseResolver phraseResolver;
    private final AllegroService allegroService;

    public EmojiSearchApplication(EmojiClient emojipediaClient, AllegroClient allegroClient,
                                  TranslatorClient yandexClient, PhraseResolver phraseResolver,  AllegroService allegroService) {
        this.emojipediaClient = emojipediaClient;
        this.allegroClient =  allegroClient;
        this.yandexClient = yandexClient;
        this.phraseResolver = phraseResolver;
        this.allegroService = allegroService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmojiSearchApplication.class, args);
    }

	@Override
    public void run(String... args) {
        System.out.print(emojipediaClient.getEmojiNameByUnicode("\uD83C\uDF47"));
        System.out.println(allegroClient.getOffersByPhrase("pilka nike"));
        System.out.println(allegroClient.getOfferById("7074249172"));
        System.out.println("phone(ENG)" + " = " + yandexClient.getTranslation("phone") + "(PL)");
        System.out.println(phraseResolver.translatePhrasesWithEmojiToText("\uD83C\uDF54\uD83C\uDF48Hello World"));
        System.out.println(allegroService.getOfferById("7074249172").toString());
    }
}