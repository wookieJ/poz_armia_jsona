package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.controller.OfferDetail;
import org.springframework.stereotype.Service;

@Service
public class AllegroService {

    private AllegroClientInterface allegroClient;

    public AllegroService(AllegroClient allegroClient) {
        this.allegroClient = allegroClient;
    }

    public OfferDetail getOfferById(String offerId) {
        return allegroClient.getOfferById(offerId);
    }

}
