package com.crud.finalbackend.mapper;

import com.crud.finalbackend.domain.HikingTrailRequest;
import com.crud.finalbackend.domain.Location;
import com.crud.finalbackend.domain.dto.HikingTrailDto;
import com.crud.finalbackend.domain.dto.LocationDto;
import com.crud.finalbackend.external.api.hereApi.HereApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TrailPointsResultMapper {
    private HereApiClient hereApiClient;

    public Location mapToPointGeocode(String query, List<Double> pointGeocode) {
        List<Double> position = pointGeocode;
        Double latitude = position.get(0);
        Double longitude = position.get(1);
        return new Location(null, query, latitude, longitude);
    }

    public Integer mapToLengthRoute(HikingTrailDto hikingTrailDto) {
        Location departure = new Location(null,
                hikingTrailDto.getDepartureName(),
                hikingTrailDto.getDeparturePoint().getLatitude(),
                hikingTrailDto.getDeparturePoint().getLongitude()
        );
        Location destination = new Location(null,
                hikingTrailDto.getDestinationName(),
                hikingTrailDto.getDestinationPoint().getLatitude(),
                hikingTrailDto.getDestinationPoint().getLongitude()
        );
        HikingTrailRequest hikingTrail = new HikingTrailRequest(null,
                departure.getLatitude(),
                departure.getLongitude(),
                destination.getLatitude(),
                destination.getLongitude(),
                departure.getLabel(),
                destination.getLabel(),
                null,
                null,
                null);
        return hereApiClient.searchRouteLength(
                mapperHikingTrailToDto(hikingTrail)
        );
    }

    private HikingTrailDto mapperHikingTrailToDto(HikingTrailRequest hikingTrail) {
        HikingTrailDto hikingTrailDto = new HikingTrailDto();
        return new HikingTrailDto(
                new LocationDto(null, hikingTrail.getTrailBegin(), hikingTrail.getCountryCode(), hikingTrail.getDeparturePointLatitude(), hikingTrail.getDeparturePointLongitude()),
                new LocationDto(null, hikingTrail.getTrailEnd(), hikingTrail.getCountryCode(), hikingTrail.getDestinationPointLatitude(), hikingTrail.getDestinationPointLongitude()),
                hikingTrail.getTrailBegin(),
                hikingTrail.getTrailEnd(),
                null
        );
    }
}
