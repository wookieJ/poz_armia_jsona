package com.ArmiaJsona.emojiSearch.allegro;

import com.ArmiaJsona.emojiSearch.model.AllOffersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class AllegroClient implements AllegroClientInterface {

    @Value("${AllegroAPI.URL}")
    private String allegroApiUrl;
    @Value("${AllegroIdAPI.URL}")
    private String allegroIdApiUrl;
    @Value("${Allegro.Token}")
    private String allegroToken;

    private final RestTemplate allegroRestTemplate;

    public AllegroClient(RestTemplate allegroRestTemplate) {
        this.allegroRestTemplate = allegroRestTemplate;
    }

    @Override
    public AllOffersResponse getAllOffersByPhrase(String phrase,String sort) {
        if(null != sort) {
            return getAllOffersByPhraseSortedBy(phrase, sort);
        }
        return allegroRestTemplate.exchange(allegroApiUrl + "offers?phrase=" + phrase,
                HttpMethod.GET,
                buildHttpHeadersForAllegro(),
                AllOffersResponse.class).getBody();
    }

    private AllOffersResponse getAllOffersByPhraseSortedBy(String phrase, String sort) {
        return allegroRestTemplate.exchange(allegroApiUrl + "offers?phrase=" + phrase + "&sort=" + sort,
                HttpMethod.GET,
                buildHttpHeadersForAllegro(),
                AllOffersResponse.class).getBody();
    }

    @Override
    public OfferDetail getOfferById(String id) {
        ResponseEntity<OfferDetail> offerDetail = allegroRestTemplate.exchange(
                allegroIdApiUrl + "v1/allegro/offers/" + id,
                HttpMethod.GET,
                buildHttpHeadersForAllegro(),
                OfferDetail.class);
        return offerDetail.getBody();
    }

    private HttpEntity buildHttpHeadersForAllegro() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Collections.singletonList("Mozilla/5.0 (Client-Id 656cbe47-b17d-46c2-bae1-3222c8777d5b) Gecko/20100101 Firefox/59.0"));
        headers.put(HttpHeaders.AUTHORIZATION, Arrays.asList("Bearer " + allegroToken));
        headers.put(HttpHeaders.ACCEPT, Collections.singletonList("application/vnd.allegro.public.v1+json"));
        return new HttpEntity(headers);
    }
}
