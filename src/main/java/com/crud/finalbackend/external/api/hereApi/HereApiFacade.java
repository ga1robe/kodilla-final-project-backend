package com.crud.finalbackend.external.api.hereApi;

import com.crud.finalbackend.domain.dto.HikingTrailDto;
import com.crud.finalbackend.mapper.TrailPointsResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class HereApiFacade {
    private final TrailPointsResultMapper trailPointsResultMapper;
    private final HereApiClient hereApiClient;

    /**
     * API may respond to Geocode of Point as a List of Double according on query.
     *
     * @param query
     * @return
     */
    public List<Double> getPointGeocode(String query){
        List<Double> pointGeocode = new ArrayList<>();
        pointGeocode.add(trailPointsResultMapper.mapToPointGeocode(query, hereApiClient.getPointGeocode(query)).getLatitude());
        pointGeocode.add(trailPointsResultMapper.mapToPointGeocode(query, hereApiClient.getPointGeocode(query)).getLongitude());
        return pointGeocode;
    }

    /**
     * API may respond to length of the hiking trail with hiking Trail DTO
     *
     * @param hikingTrailDto
     * @return
     */
    public Integer getLengthRoute(HikingTrailDto hikingTrailDto){
        return trailPointsResultMapper.mapToLengthRoute(hikingTrailDto);
    }
}
