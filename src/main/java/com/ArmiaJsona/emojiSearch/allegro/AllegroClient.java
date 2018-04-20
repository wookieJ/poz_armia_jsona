package com.ArmiaJsona.emojiSearch.allegro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AllegroClient implements AllegroClientInterface {

    @Value("${AllegroAPI.URL}")
    private String allegroApiUrl;
    private String API_KEY = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1MjQyOTEyMzMsImp0aSI6IjMyYmQzOTNjLWRhN2ItNDhiOS1hYzlkLTFjNjljZDg1OTBkNCIsImNsaWVudF9pZCI6ImE0MWY1YjJhLThlODctNGI4Yi1iNmZlLTc0Y2M3NjM3MjBkNyJ9.bVHSZCQ-_4DA_bR7UiIaXFXbZJKflePKkuUMYn0wSNcrrBGKZBaZDddxuyU5PF4nKKkuiGg8RBFY69tC4EjeNx2TExug6qwVQiAD5pfL-czDMwI9L1A5N7lc8-LIWbpffmTEXomyd6ezGWPehOpqRUYTGu3AImJJvptDgiXUmeBPgCs6zlm3gV856C--VuSO7AFtIoG6DbLtq_IOEPQt6Zdn-FFMxA-zu2BjeMDKUjuW_rFFEm0i5NGh72dYNPRmOObKa59Fvzbhl2uPwsBUPRgm5rFI8x8WeWdDzwY5feOPZuZJkYXQ6oeiLViOhnqIa6F763Zutk-hVeOr-PbN-Q";
    private RestTemplate allegroRestTemplate;

    public AllegroClient(RestTemplate allegroRestTemplate) {
        this.allegroRestTemplate = allegroRestTemplate;
    }

    @Override
    public String getOffersByPhrase(String phrase) {
       return allegroRestTemplate.exchange(allegroApiUrl + "offers?phrase=" + phrase, HttpMethod.GET, getUserAgent(), String.class ).getBody();
    }

    private HttpEntity getUserAgent() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Arrays.asList("Mozilla/5.0 (Client-Id 656cbe47-b17d-46c2-bae1-3222c8777d5b) Gecko/20100101 Firefox/59.0"));
        headers.put("Api-Key", Arrays.asList("eyJjbGllbnRJZCI6ImE0MWY1YjJhLThlODctNGI4Yi1iNmZlLTc0Y2M3NjM3MjBkNyJ9.ogVV_a9RUOMa1OWFZOTmgTkdk-U37vTliDCBUQ1YySU="));
        headers.put(HttpHeaders.ACCEPT, Arrays.asList("application/vnd.allegro.public.v1+json"));
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }
}
