package com.crud.finalbackend.service;

import com.crud.finalbackend.except.PointNotFoundException;
import com.crud.finalbackend.external.api.points.domain.Point;
import com.crud.finalbackend.repository.PointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    /**
     * Method checks if country object exists in database by its name.
     *
     * @param point point object which existence in database is checked.
     * @return true if @param point exists in database
     */
    private Boolean exists(Point point) {
        return pointRepository.existsByName(point.getName());
    }

    /**
     * Method checks if country object exists in database by its name.
     *
     * @param point point object which existence in database is checked.
     * @return true if @param point DOES NOT exist in database
     */
    private Boolean notExists(Point point) {
        return !this.exists(point);
    }

    /**
     * Checks if @param point exists already in database. If exists, returns new empty point.
     * If doesn't exist, saves in in the databse
     *
     * @param point point object to be added to the database
     * @return
     */
    @Transactional
    public Point addPoint(final Point point) {
        if( this.exists(point) ) {
            return new Point();
        }
        return pointRepository.save(point);
    }

    public List<Point> getAllPoints() {
        return pointRepository.findAll();
    }

    public Point getCountryById(final Long id) {
        return pointRepository.findById(id).orElseThrow(PointNotFoundException::new);
    }

    public Point getPointByName(final String name) {
        return pointRepository.findByName(name).orElseThrow(PointNotFoundException::new);
    }

    /**
     * Method used to update database with a list of points. Points not existing in the database will be added.
     * Despite checking point existence in addPoint(), method does not attempt it on every list element to avoid
     * returning too many objects.
     *
     * @param points list of countries to update database with
     */
    @Transactional
    public void updateDatabase(final List<Point> points) {
        points.stream()
                .filter(this::notExists)
                .forEach(this::addPoints);
    }

    private void addPoints(Point point) {
    }
}
