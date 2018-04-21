package com.ArmiaJsona.emojiSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prices {
    private Price buyNow;
    private Price withDelivery;
    private Price current;

    public Price getBuyNow() {
        return buyNow;
    }

    public void setBuyNow(Price buyNow) {
        this.buyNow = buyNow;
    }

    public Price getWithDelivery() {
        return withDelivery;
    }

    public void setWithDelivery(Price withDelivery) {
        this.withDelivery = withDelivery;
    }

    public Price getCurrent() {
        return current;
    }

    public void setCurrent(Price current) {
        this.current = current;
    }

    public Prices() {
    }
}
