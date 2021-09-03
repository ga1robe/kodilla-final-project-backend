package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.HikingTrailConnectionWithWeather;
import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.HikingTrailRequest;
import com.crud.finalbackend.domain.TrailOffer;
import com.crud.finalbackend.domain.dto.HikingTrailDto;
import com.crud.finalbackend.domain.dto.LocationDto;
import com.crud.finalbackend.except.UnableToGetWeatherForecastException;
import com.crud.finalbackend.external.api.hereApi.HereApiFacade;
import com.crud.finalbackend.domain.HikingTrail;
import com.crud.finalbackend.external.api.weather.WeatherClientFacade;
import com.crud.finalbackend.external.api.weather.domain.DailyWeatherForecast;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class SeasonalTrailOffersService {
    private final TrailSearchRequestService requestService;
    private final PreferenceService notificationPreferenceService;
    private final HereApiFacade hereApiFacade;
    private final WeatherClientFacade weatherClientFacade;

    private LocalDate getNextFriday() {
        return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }
    private LocalDate getSundayAfterDeparture() { return getNextFriday().plusDays(2); }

    /**
     * Method creates a set of all trails appearing in customers' preferences. To ensure equality of strings and
     * avoid duplicating trails, every trail name gets changed to lower case
     *
     * @param allPreferences a list of customers' preferences to work on
     * @return
     */
    private Set<String> getAllTrailsFromPreferences(List<Preference> allPreferences) {
        Set<String> trails = new HashSet<>();
        allPreferences.forEach(e -> {
            trails.add( e.getTrailBegin() );
            trails.add( e.getTrailEnd() );
        });
        return trails;
    }

    /**
     * Having set of trails appearing in customers' preferences method checks via API points available for every trail
     * and maps with trails. Key is the name of the trail and value is a list of points available in the trail.
     *
     * @param trails
     * @return
     */
    private Map<String, List<Double>> getPointGeocodeForPreferredTrails(Set<String> trails) {
        Map<String, List<Double>> trailsAndPoints = new HashMap<>();
        trails.forEach(
                e -> trailsAndPoints.put(e, hereApiFacade.getPointGeocode(e))
        );
        return trailsAndPoints;
    }

    /**
     * Creates a reversed map where Key value is point code and value is the name of there trail where point is.
     *
     * @param trailsAndPoints
     * @return
     */
    private Map<List<Double>, String> createReverseMappingForTrailsAndPoints(Map<String, List<Double>> trailsAndPoints) {
        Map<List<Double>, String> result = new HashMap<>();
        trailsAndPoints.entrySet().stream()
                .forEach(entry -> {
                    result.put(List.of(entry.getValue().get(0),entry.getValue().get(1)), entry.getKey());

                });

        return result;
    }

    /**
     * Knowing points available in every trail method takes customer's preference as @param and creates a set of possible
     * connections for both going there and returning.
     *
     * @param preference
     * @param trailsAndPoints
     * @return
     */
    private Set<HikingTrailRequest> getSearchRequestsForPreference (Preference preference, Map<String, List<Double>> trailsAndPoints) {
        Set<HikingTrailRequest> requestsForPreference = new HashSet<>();
        List<Double> departurePoints = trailsAndPoints.get( preference.getTrailBegin() );
        List<Double> destinationPoints = trailsAndPoints.get( preference.getTrailEnd() );
        Map<List<Double>, String> reversedTrailsAndPoints = createReverseMappingForTrailsAndPoints(trailsAndPoints);

        /**
         * For every available in the trail point create a pair with every destination point to create search request.
         * Created request adds to the set and creates a return request with swapped points.
         */
        for(int i = 0; i < departurePoints.size() && i < destinationPoints.size(); i += 2) {
            Double departureLatitude = departurePoints.get(i);
            Double departureLongtitude = departurePoints.get(i+1);
            Double destinationLatitude = destinationPoints.get(i);
            Double destinationLongtitude = destinationPoints.get(i+1);
            /**
             * Hiking Trail search when going there
             */
            HikingTrailRequest thereRequest = HikingTrailRequest.builder()
                    .trailBegin( reversedTrailsAndPoints.get( List.of(departureLatitude, departureLongtitude) ) )
                    .trailEnd( reversedTrailsAndPoints.get( List.of(destinationLatitude , destinationLongtitude) ) )
                    .distance(
                            hereApiFacade.getLengthRoute(
                                    new HikingTrailDto(
                                            new LocationDto(null,null,null,departureLatitude, departureLongtitude),
                                            new LocationDto(null,null,null,destinationLatitude , destinationLongtitude),
                                            null, null, null)
                            )
                    )
                    .hikingDay( getNextFriday() )
                    .build();
            requestsForPreference.add(thereRequest);
            requestService.addSearchRequest(thereRequest);
        }

        return requestsForPreference;
    }

    public Set<HikingTrailRequest> getAllSearchRequests() {
        Set<HikingTrailRequest> uniqueSearchRequestsForPreferenfces = new HashSet<>();

        List<Preference> allPreferences = notificationPreferenceService.getAllPreferences();
        Set<String> trails = getAllTrailsFromPreferences(allPreferences);
        Map<String, List<Double>> trailsAndPoints = getPointGeocodeForPreferredTrails(trails);

        allPreferences.forEach(e -> {
            uniqueSearchRequestsForPreferenfces.addAll( getSearchRequestsForPreference(e, trailsAndPoints) );
        });

        return uniqueSearchRequestsForPreferenfces;
    }

    /**
     * Tells whether provided as @param LocalDate belongs to the upcoming weekend, meaning:
     * from departure day ("after day before") to sunday
     *
     * @param date
     * @return
     */
    private boolean isNextWeekendDay(LocalDate date) {
        return (date.isAfter(getNextFriday().minusDays(1)) && date.isBefore(getSundayAfterDeparture().plusDays(1)));
    }

    private Double getWeekendAverageTemperature(String trail) {
        return weatherClientFacade.getWeatherForecast(trail).getDailyForecasts().stream()
                .filter(e -> isNextWeekendDay( e.getDate() ))
                .mapToDouble(DailyWeatherForecast::getMaxTemperature )
                .average().orElseThrow(UnableToGetWeatherForecastException::new);
    }

    /**
     * Counts average temperature for destination trails during upcoming weekend. Returns it as a Map where Key is
     * trail name and value is the expected average temperature
     *
     * @return
     */
    public Map<String, Double> getWeatherForDestinationTrails() {
        Map<String, Double> averageTemperaturesForDestinationTrails = new HashMap<>();
        getAllTrailsFromPreferences( notificationPreferenceService.getAllPreferences() ).forEach(
                e -> averageTemperaturesForDestinationTrails.put(e, getWeekendAverageTemperature(e))
        );
        return averageTemperaturesForDestinationTrails;
    }

    private List<Double> getPointGeocodeForRequest(String query) {
        return hereApiFacade.getPointGeocode(query);
    }

    public List<HikingTrail> getAllPreferences() {
        List<HikingTrail> result = new ArrayList<>();
        Set<HikingTrailRequest> requests = this.getAllSearchRequests();
        log.info("Hiking Trail search requests number: " + requests.size());

        for(HikingTrailRequest request : requests) {
            log.info("Processing request: " + request);
            List<Double> searchResult = this.getPointGeocodeForRequest(request.getTrailBegin().concat(", ").concat(request.getCountryCode()));
            log.info("Found " + searchResult.size() + " hiking trail(s) for the request");
            
            log.info("Total result size: " + result.size());
        }

        return result;
    }

    public List<HikingTrailConnectionWithWeather> getAllHikingTrailOffersWithExpectedWeather() {
        List<HikingTrailConnectionWithWeather> result = new ArrayList<>();

        List<HikingTrail> hikingTrails = this.getAllPreferences();
        Map<String, Double> weather = this.getWeatherForDestinationTrails();

        for(HikingTrail hikingTrail : hikingTrails) {
            BigDecimal temperature =  BigDecimal.valueOf(weather.get(hikingTrail.getDestinationPoint())).setScale(2, RoundingMode.HALF_EVEN);
            result.add(
                    new HikingTrailConnectionWithWeather(hikingTrail,  temperature)
            );
        }

        return result;
    }

    /**
     * "Ultimate" method of the class, returning a complete set of connections matching preference requirements.
     *
     * @return
     */
    public Map<Preference, TrailOffer> getPreferencesAndOffers() {
        log.info("Matching preferences with avaiable connections...");
        Map<Preference, TrailOffer> preferencesAndOffers = new HashMap<>();
        List<Preference> preferences = notificationPreferenceService.getAllPreferences();
        List<HikingTrailConnectionWithWeather> offers = getAllHikingTrailOffersWithExpectedWeather();

        for(Preference preference : preferences) {
            log.info("Processing preference: " + preference);
            Optional<HikingTrailConnectionWithWeather> cheapestThereHikingTrail = offers.stream()
                    .filter(offer -> offer.getTrail().getDestinationPoint().equals( preference.getTrailEnd().toLowerCase() )  )
                    .filter(offer -> offer.getTrail().getDeparturePoint().equals( preference.getTrailBegin().toLowerCase() ))
                    .filter(offer -> offer.getExpectedTemperature().intValue() >= preference.getMinTemperature() )
                    .min( HikingTrailConnectionWithWeather::compareTo );

            log.info("Chepest there connection is: " + cheapestThereHikingTrail);

            Optional<HikingTrailConnectionWithWeather> cheapestReturnHikingTrail = offers.stream()
                    .filter(offer -> offer.getTrail().getDestinationPoint().equals( preference.getTrailEnd().toLowerCase() )  )
                    .filter(offer -> offer.getTrail().getDeparturePoint().equals( preference.getTrailBegin().toLowerCase() ))
                    .min( HikingTrailConnectionWithWeather::compareTo );
            log.info("Chepest return connection is: " + cheapestReturnHikingTrail);

            if( cheapestReturnHikingTrail.isPresent() && cheapestThereHikingTrail.isPresent() ) {
                HikingTrailConnectionWithWeather thereHikingTrail = cheapestThereHikingTrail.get();
                HikingTrailConnectionWithWeather returnHikingTrail = cheapestReturnHikingTrail.get();
                BigInteger totalDistance = thereHikingTrail.getTrail().getDistance().add(returnHikingTrail.getTrail().getDistance());

                /**
                 * If total cost of both hiking trails (there and return) is lower or equal to max price declared in preference
                 * then create offer of those hiking trails and add to the offer map
                 */
                if( totalDistance.compareTo( preference.getDistance() ) < 1 ) {
                    log.info("Total price is acceptable, adding to offers");
                    TrailOffer offer = new TrailOffer(thereHikingTrail, returnHikingTrail, totalDistance);
                    preferencesAndOffers.put(preference, offer);
                }
            }
        }

        return preferencesAndOffers;
    }
}
