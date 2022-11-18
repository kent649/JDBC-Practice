package connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {
    Connection connection;

    public Connection getConnection() throws IOException {
        //Reading the Properties File
        InputStream inputStream = new FileInputStream("src/test/java/connections/database.properties"); //read data from a source(read the contents of a file as a stream of bytes)
        Properties properties = new Properties(); //create a property instance in order to use Properties class(to read and write to a properties file on disk)
        properties.load(inputStream); //load a property file

        //get value by key
        String url = properties.getProperty("MYSQL_DB_URL");
        String user = properties.getProperty("MYSQL_DB_USER");
        String password = properties.getProperty("MYSQL_DB_USER_PASSWORD");

        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void dBClose() {
        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//public class DBconnection {
//    private static String url = "jdbc:sqlserver://localhost:1433;user=sa;password=X@procolector649;databaseName=BikeStores;encrypt=true;trustServerCertificate=true";
//    Connection connection ;
//
//    public Connection getConnection() {
//        try {
//            connection = DriverManager.getConnection(url);
//            return connection;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void dBClose() {
//        try {
//            connection.close();
//            System.out.println("Connection closed");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
