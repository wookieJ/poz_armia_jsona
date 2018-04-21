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
    private long views;
    private long endingTime;

    public String getDescription() {
        return offerDescription.getPlainText();
    }

    public long getViews() {
        return views;
    }

    public long getEndingTime() {
        return endingTime;
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
