package com.ArmiaJsona.emojiSearch.allegro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDetail {

    private String id;
    private String name;

    @Override
    public String toString() {
        return "OfferDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
