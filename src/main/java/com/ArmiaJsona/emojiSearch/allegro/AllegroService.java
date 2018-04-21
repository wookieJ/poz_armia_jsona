package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.model.Offer;
import com.ArmiaJsona.emojiSearch.utils.PhraseResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllegroService {

    private PhraseResolver phraseResolver;
    private AllegroClientInterface allegroClient;

    public AllegroService(AllegroClient allegroClient,PhraseResolver phraseResolver) {
        this.allegroClient = allegroClient;
        this.phraseResolver = phraseResolver;
    }

    public OfferDetail getOfferById(String offerId) {
        return allegroClient.getOfferById(offerId);
    }

    public List<Offer> getAllOffersByPhrase(String phrase) {
        String payload = phraseResolver.translatePhrasesWithEmojiToText(phrase);
        return allegroClient.getAllOffersByPhrase(payload).getOfferList();
    }

    public List<Offer> getAllOffersByPhraseSortedBy(String phrase,String sort) {
        String payload = phraseResolver.translatePhrasesWithEmojiToText(phrase);
        return allegroClient.getAllOffersByPhraseSortedBy(payload, sort).getOfferList();
    }
}
