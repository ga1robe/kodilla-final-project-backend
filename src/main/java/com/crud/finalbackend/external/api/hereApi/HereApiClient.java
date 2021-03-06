package com.crud.finalbackend.external.api.hereApi;

import com.crud.finalbackend.domain.dto.HikingTrailDto;
import com.crud.finalbackend.config.HereConfig;
import com.crud.finalbackend.external.api.hereApi.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

@Component
public class HereApiClient {
    @Autowired
    private HereConfig hereConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Double> getPointGeocode(String query) {
        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getGeocodeEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("q", query)
                .build().encode().toUri();
        try {
            HereItem geocodeResponse = restTemplate.getForObject(url, HereItem.class);
            Double latitude = Objects.requireNonNull(geocodeResponse).getLocations().get(0).getPosition().getLatitude();
            Double longitude = geocodeResponse.getLocations().get(0).getPosition().getLongitude();
            List<Double> position = new ArrayList<>();
            position.add(latitude);
            position.add(longitude);
            return position;
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<HereApiLocation> searchLocations(Double lat, Double lng, String query, String countryCode) {

        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getSearchEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("at", lat.toString() + "," + lng.toString())
                .queryParam("q", query)
                .queryParam("countryCode", countryCode)
                .build().encode().toUri();
        try {
            HereItem locationResponse = restTemplate.getForObject(url, HereItem.class);
            List<HereApiLocation> locationList;
            assert locationResponse != null;
            locationList = ofNullable(locationResponse.getLocations()).orElse(new HereItem().getLocations());
            return locationList;
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public Integer searchRouteLength(HikingTrailDto request) {
        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getRoutingEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("origin", request.getDeparturePoint().getLatitude() + "," + request.getDeparturePoint().getLongitude())
                .queryParam("destination", request.getDestinationPoint().getLatitude() + "," + request.getDestinationPoint().getLongitude())
                .queryParam("routingMode", "fast")
                .queryParam("transportMode", "pedestrian")
                .queryParam("return", "summary")
                .build().encode().toUri();
        try {
            HereApiRoutesExample routeResponse = restTemplate.getForObject(url, HereApiRoutesExample.class);
            assert routeResponse != null;
            HereApiRoute route = routeResponse.getRoutes().get(0);
            List<HereApiRouteSections> sections = ofNullable(route.getSections()).orElse(new ArrayList<>());
            Integer length = 0;
            for(HereApiRouteSections section : sections) {
                length += section.getSummary().getLength();
            }
            return length;
        } catch (RestClientException e) {
            System.out.println(e);
        }
        return -1;
    }
}
