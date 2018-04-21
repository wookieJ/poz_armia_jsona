package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.controller.OfferDetail;

public interface AllegroClientInterface {

    String getOffersByPhrase(String phrase);

    OfferDetail getOfferById(String id);
}
