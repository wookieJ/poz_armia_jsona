package com.ArmiaJsona.emojiSearch.allegro;

public interface AllegroClientInterface {

    String getOffersByPhrase(String phrase);

    OfferDetail getOfferById(String id);
}
