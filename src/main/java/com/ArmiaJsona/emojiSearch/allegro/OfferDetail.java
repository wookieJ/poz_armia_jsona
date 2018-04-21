package com.ArmiaJsona.emojiSearch.allegro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDetail {

    @JsonProperty("id")
    private String offerId;

    @JsonProperty("name")
    private String offerName;

    @Override
    public String toString() {
        return "OfferDetail{" +
                "id='" + offerId + '\'' +
                ", name='" + offerName + '\'' +
                '}';
    }
}
