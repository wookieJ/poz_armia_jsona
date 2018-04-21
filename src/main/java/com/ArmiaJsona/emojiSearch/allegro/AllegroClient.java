package com.ArmiaJsona.emojiSearch.allegro;

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
    @Value("${Allegro.Token")
    private String allegroToken;

    private final RestTemplate allegroRestTemplate;

    public AllegroClient(RestTemplate allegroRestTemplate) {
        this.allegroRestTemplate = allegroRestTemplate;
    }

    @Override
    public String getOffersByPhrase(String phrase) {
        return allegroRestTemplate.exchange(allegroApiUrl + "offers?phrase=" + phrase,
                HttpMethod.GET,
                buildHttpHeadersForAllegro(),
                String.class).getBody();
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
        headers.put(HttpHeaders.AUTHORIZATION, Arrays.asList("Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1MjQzMjE1NjAsImp0aSI6IjcyY2I0ZDBjLWEzMzItNGQ3ZS1hMTdjLTQ4MDU5MzVkMjgyNSIsImNsaWVudF9pZCI6ImE0MWY1YjJhLThlODctNGI4Yi1iNmZlLTc0Y2M3NjM3MjBkNyJ9.PZ_lPj6ta_zsrRPPQX2J4RXfZ8ChYK7VCof2wg-b67Zkr71YdcRdIDJSRyzWhJqcQv3JRA6PTR7HhA7H5on2pQ37Z80g39G0iezdvDngdj3ecuUqy0M7aK0xGbldgYAFtioAOqCDTMJ0oQ5IVYHg2ncXe5BvVXOLkYhiRlDnSiNwvBGriW24rEnzdDySI1nfv2bwgt7OxoB3-dWfS2Ge9FlFizZcFIuMY0Gx8yVsG_lXIx7qaxx63aj2VPmOnaq4c-7eDaTvcooEGvqqAaEaXVcwVliNG1ExHm6Jyd86YScxdrQmg3rwlg6atiWEy4N6-fZfEEwJ6JpIycjs5trKzw"));
        headers.put(HttpHeaders.ACCEPT, Collections.singletonList("application/vnd.allegro.public.v1+json"));
        return new HttpEntity(headers);
    }
}
