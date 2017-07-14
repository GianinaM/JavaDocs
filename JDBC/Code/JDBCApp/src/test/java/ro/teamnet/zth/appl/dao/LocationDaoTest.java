package ro.teamnet.zth.appl.dao;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Gianina.Carp on 7/14/2017.
 */
public class LocationDaoTest {
    long nextVal = 0L;
    private LocationDao depDao = new LocationDao();

    @Test
    public void testFindLocationById() throws SQLException {
        Location myT = depDao.findLocationById((long) 1500);
        assertEquals("It should return 2011 Interiors Blvd",
                "2011 Interiors Blvd", myT.getStreetAddress());
    }

    @Test
    public void testGetNextItValOfLocations() throws SQLException {
        long myLong = depDao.getNextItValOfLocations();
        assertEquals("It should return 3201", 3201, myLong);
    }

    @Test
    public void testInsertLocation() throws SQLException {
//        this.nextVal = depDao.getNextItValOfLocations();
        Location location = new Location();
        location.setCity("Bucharest");
        location.setPostalCode("123123");
        location.setStateProvince("Muntenia");
        location.setStreetAddress("Camil Ressu");
        Location result = (Location) depDao.insertLocation(location);
        assertEquals("It should return Bucharest", "Bucharest", result.getCity());
        assertEquals("It should return Muntenia", "Muntenia", result.getStateProvince());
        assertEquals("It should return 123123", "123123", result.getPostalCode());
        assertEquals("It should return Camil Ressu", "Camil Ressu", result.getStreetAddress());
    }

    @Test
    public void testFindAllDepartments() throws SQLException {
        List<Location> allLocations = depDao.findAllLocations();
        assertEquals("It should return 23", 23, allLocations.size());
    }

    @Test
    public void testUpdateDepartment() throws SQLException {
        Location location = depDao.findLocationById(3200L);
        location.setStateProvince("Moldova");
        location.setCity("Iasi");
        Location result = depDao.updateLocation(location);
        assertEquals("It should return Iasi", "Iasi", result.getCity());
        assertEquals("It should return Moldova", "Moldova", result.getStateProvince());
    }

    @Test
    public void testDeleteDepartment() throws SQLException {
        this.nextVal = 2000L;
        Location location = depDao.findLocationById(nextVal);
        depDao.deleteLocation(location);
        assertNull(depDao.findLocationById(nextVal));
    }

    @Test
    public void testFindDepartmentsByParams() throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("city", "Bucharest");
        params.put("postal_code", new String("123123"));
        List<Location> departments = depDao.findLocationsByParams(params);
        assertEquals(3, departments.size());
    }
}
