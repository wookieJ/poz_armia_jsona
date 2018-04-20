package com.ArmiaJsona.emojiSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferList
{
    @JsonProperty("offers")
    private List<Offer> offerList;

    public List<Offer> getOfferList()
    {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList)
    {
        this.offerList = offerList;
    }

    public OfferList(){}
}
