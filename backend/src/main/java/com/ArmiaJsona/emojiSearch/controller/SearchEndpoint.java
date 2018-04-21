package com.ArmiaJsona.emojiSearch.controller;

import com.ArmiaJsona.emojiSearch.allegro.AllegroService;
import com.ArmiaJsona.emojiSearch.allegro.OfferDetail;
import com.ArmiaJsona.emojiSearch.model.Offer;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Offer>> getOffersSorted(@RequestParam String phrase, @RequestParam(required = false) String sort) {
        List<Offer> allOffersById = allegroService.getAllOffersByPhrase(phrase, sort);
        if (!allOffersById.isEmpty())
            return ResponseEntity.ok(allOffersById);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<OfferDetail> getOfferDetails(@PathVariable("offerId") String offerId) {
        OfferDetail offerById = allegroService.getOfferById(offerId);
        if (offerById != null)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
