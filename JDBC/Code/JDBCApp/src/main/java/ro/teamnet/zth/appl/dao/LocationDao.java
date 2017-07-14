package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Gianina.Carp on 7/14/2017.
 */
public class LocationDao {
    private EntityManagerImpl entityManager = new EntityManagerImpl();

    public Location findLocationById(Long id) throws SQLException {
        return this.entityManager.findById(Location.class, id);
    }

    public long getNextItValOfLocations() throws SQLException {
        return this.entityManager.getNextIdVal("locations", "location_id");
    }

    public Location insertLocation(Location location) throws SQLException {
        return (Location) this.entityManager.insert(location);
    }

    public List<Location> findAllLocations() throws SQLException {
        return this.entityManager.findAll(Location.class);
    }

    public Location updateLocation(Location location) throws SQLException {
        return this.entityManager.update(location);
    }

    public void deleteLocation(Location location) throws SQLException {
        this.entityManager.delete(location);
    }

    public List<Location> findLocationsByParams(Map<String, Object> params) throws SQLException {
        return this.entityManager.findByParams(Location.class, params);
    }
}
