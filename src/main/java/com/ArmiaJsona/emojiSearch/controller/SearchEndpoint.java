package com.ArmiaJsona.emojiSearch.controller;

import com.ArmiaJsona.emojiSearch.PhraseResolver;
import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.model.Offer;
import com.ArmiaJsona.emojiSearch.model.OfferList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class SearchEndpoint {
    private AllegroClient allegroClient;
    private PhraseResolver phraseResolver;

    public SearchEndpoint(AllegroClient allegroClient, PhraseResolver phraseResolver) {
        this.allegroClient = allegroClient;
        this.phraseResolver = phraseResolver;
    }

    @RequestMapping("/offers")
    public List<Offer> getOffers(@RequestParam(required = true) String name) {
        List<Offer> offers = new ArrayList<>();
        String payload = phraseResolver.translatePhrasesWithEmojiToText(name);

        ObjectMapper mapper = new ObjectMapper();
        OfferList offerList = new OfferList();
        try {
            offerList = mapper.readValue(allegroClient.getOffersByPhrase(payload), OfferList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return offerList.getOfferList();
    }
}
