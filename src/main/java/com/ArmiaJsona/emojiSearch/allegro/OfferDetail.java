package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.model.Description;
import com.ArmiaJsona.emojiSearch.model.MainImage;
import com.ArmiaJsona.emojiSearch.model.Quantities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDetail {
    @JsonProperty("id")
    private String offerId;

    @JsonProperty("name")
    private String offerName;
    @JsonProperty("description")
    private Description offerDescription;
    private String description;
    private int views;
    private int endingTime;
    private Quantities quantities;
    private MainImage mainImage;

    public String getDescription() {
        return offerDescription.getPlainText();
    }

    public int getViews() {
        return views;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public Quantities getQuantities() {
        return quantities;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public OfferDetail() {
    }

    @Override
    public String toString() {
        return "OfferDetail{" + "id='" + offerId + '\'' + ", name='" + offerName + '\'' + '}';
    }
}
