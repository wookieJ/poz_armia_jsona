package com.ArmiaJsona.emojiSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
    private String id;
    private String url;
    private String name;
    private Prices prices;
    private Price price;
    private List<Url> images;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice(){
        return prices.getPrice();
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    public String getImage() {
        return images.get(0).getUrl();
    }

    public void setImages(List<Url> images) {
        this.images = images;
    }

    public Offer() {
    }
}
