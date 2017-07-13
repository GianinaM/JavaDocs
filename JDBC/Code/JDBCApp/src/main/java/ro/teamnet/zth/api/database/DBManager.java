package ro.teamnet.zth.api.database;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

import java.sql.*;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public class DBManager {
    private static final String CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager() throws UnsupportedOperationException{}

    private static void registerDriver() {
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        String USER = DBProperties.USER;
        String PASS = DBProperties.PASS;

        return DriverManager.getConnection(CONNECTION_STRING, USER, PASS);
    }

    public static boolean checkConnection(Connection connection) {
        String SQL = "SELECT 1 FROM DUAL";

        try (Statement statement  = getConnection().createStatement()){
            return statement.execute(SQL);
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return false;

    }
}
