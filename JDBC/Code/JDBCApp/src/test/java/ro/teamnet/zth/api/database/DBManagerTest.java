package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection() throws SQLException {
        assertNotNull(DBManager.getConnection());
    }

    @Test
    public void testCheckConnection() throws SQLException {
        assertEquals("Result shoud be true", true, DBManager.checkConnection(DBManager.getConnection()));
    }
}
