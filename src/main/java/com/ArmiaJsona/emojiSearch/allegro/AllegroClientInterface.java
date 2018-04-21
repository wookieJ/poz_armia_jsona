package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.model.AllOffersResponse;

public interface AllegroClientInterface {

    AllOffersResponse getAllOffersByPhrase(String phrase);

    AllOffersResponse getAllOffersByPhraseSortedBy(String phrase,String sort);

    OfferDetail getOfferById(String id);
}
