package com.ArmiaJsona.emojiSearch.controller;

import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.model.Offer;
import com.ArmiaJsona.emojiSearch.model.OfferList;
import com.ArmiaJsona.emojiSearch.translator.TranslatorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class SearchEndpoint
{
    private final EmojiClient emojipediaClient;
    private final AllegroClient allegroClient;
    private final TranslatorClient yandexClient;

    public SearchEndpoint(EmojiClient emojipediaClient, AllegroClient allegroClient, TranslatorClient yandexClient)
    {
        this.emojipediaClient = emojipediaClient;
        this.allegroClient = allegroClient;
        this.yandexClient = yandexClient;
    }

    @RequestMapping("/search")
    public List<Offer> getOffers()
    {
        List<Offer> offers = new ArrayList<>();
        String payload = null;
        String name = "\uD83C\uDF47";
        if (name != null && !name.equals(""))
        {
            payload = emojipediaClient.getEmojiNameByUnicode(name);
            payload = yandexClient.getTranslation(payload);
        }

        ObjectMapper mapper = new ObjectMapper();
        OfferList offerList = null;
        try
        {
            offerList = mapper.readValue(allegroClient.getOffersByPhrase(payload), OfferList.class);
            System.out.println(offerList.getOfferList().size());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return Objects.requireNonNull(offerList).getOfferList();
    }
}
