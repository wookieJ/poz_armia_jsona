package com.ArmiaJsona.emojiSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prices {
    private Price buyNow;
    private Price current;

    public void setBuyNow(Price buyNow) {
        this.buyNow = buyNow;
    }

    @JsonProperty("price")
    public Price getPrice() {
        if(current != null)
            return current;
        return buyNow;
    }

    public void setCurrent(Price current) {
        this.current = current;
    }

    public Prices() {
    }
}
