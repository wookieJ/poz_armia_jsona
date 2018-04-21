package com.ArmiaJsona.emojiSearch.controller;

import com.ArmiaJsona.emojiSearch.utils.PhraseResolver;
import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.allegro.AllegroService;
import com.ArmiaJsona.emojiSearch.allegro.OfferDetail;
import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.model.Offer;
import com.ArmiaJsona.emojiSearch.model.OfferList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchEndpoint {

    private AllegroClient allegroClient;
    private PhraseResolver phraseResolver;
    private AllegroService allegroService;

    public SearchEndpoint(AllegroClient allegroClient, PhraseResolver phraseResolver,
                          AllegroService allegroService) {
        this.allegroClient = allegroClient;
        this.phraseResolver = phraseResolver;
        this.allegroService = allegroService;
    }

    @GetMapping("/offers")
    public List<Offer> getOffers(@RequestParam String name, @RequestParam(required = false) String sort) {
        String payload = phraseResolver.translatePhrasesWithEmojiToText (name);

        ObjectMapper mapper = new ObjectMapper();
        OfferList offerList = new OfferList();
        try {
            if(sort == null)
                sort = "";
            else {
                sort = "&sort=" + sort;
            }
            offerList = mapper.readValue(allegroClient.getOffersByPhrase(payload + sort), OfferList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return offerList.getOfferList();
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<OfferDetail> getOfferDetails(@PathVariable("offerId") String offerId) {
        return new ResponseEntity<>(allegroService.getOfferById(offerId), HttpStatus.OK);
    }
}
