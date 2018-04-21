package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.exception.OffersNotFoundException;
import com.ArmiaJsona.emojiSearch.model.Offer;
import com.ArmiaJsona.emojiSearch.utils.PhraseResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllegroService {

    private PhraseResolver phraseResolver;
    private AllegroClientInterface allegroClient;

    public AllegroService(AllegroClient allegroClient, PhraseResolver phraseResolver) {
        this.allegroClient = allegroClient;
        this.phraseResolver = phraseResolver;
    }

    public OfferDetail getOfferById(String offerId) {
        return allegroClient.getOfferById(offerId);
    }

    public List<Offer> getAllOffersByPhrase(String phrase, String sort) {
        phraseResolver.changeEmojisToText(phrase);
        String payload = phraseResolver.getPhraseInPolish();
        List<Offer> listOfOffers = allegroClient
                .getAllOffersByPhrase(payload, sort)
                .getOfferList();

        if (listOfOffers.isEmpty()) {
            payload = phraseResolver.getPhraseInEnglish();
            listOfOffers = allegroClient
                    .getAllOffersByPhrase(payload, sort)
                    .getOfferList();
        }

        if(listOfOffers.isEmpty()) {
            throw new OffersNotFoundException("We didn't get any offers from Allegro service");
        }
        return listOfOffers;
    }
}
