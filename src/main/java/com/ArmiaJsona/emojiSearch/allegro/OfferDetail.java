package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.model.Description;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDetail {
    @JsonProperty("id")
    private String offerId;

    @JsonProperty("name")
    private String offerName;
    @JsonProperty("description")
    private Description offerDescription;
    private String description;

    public String getDescription() {
        return offerDescription.getPlainText();
    }

    public OfferDetail(){
    }

    @Override
    public String toString() {
        return "OfferDetail{" +
                "id='" + offerId + '\'' +
                ", name='" + offerName + '\'' +
                '}';
    }
}
