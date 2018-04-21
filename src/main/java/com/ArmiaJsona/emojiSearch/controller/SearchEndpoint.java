package com.ArmiaJsona.emojiSearch.controller;

import com.ArmiaJsona.emojiSearch.allegro.AllegroService;
import com.ArmiaJsona.emojiSearch.allegro.OfferDetail;
import com.ArmiaJsona.emojiSearch.model.Offer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEndpoint {

    private AllegroService allegroService;

    public SearchEndpoint(AllegroService allegroService) {
        this.allegroService = allegroService;
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getOffers(@RequestParam String phrase) {
        return ResponseEntity.ok(allegroService.getAllOffersByPhrase(phrase));
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getOffers(@RequestParam String phrase,
                                                 @RequestParam String sort) {
        return ResponseEntity.ok(allegroService.getAllOffersByPhraseSortedBy(phrase, sort));
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<OfferDetail> getOfferDetails(@PathVariable("offerId") String offerId) {
        return ResponseEntity.ok(allegroService.getOfferById(offerId));
    }
}
