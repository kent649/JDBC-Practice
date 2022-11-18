package connections;

import java.sql.*;
import java.util.*;

public class QueryFactory {
    public String[] arrayResult(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Creates a Statement object that will generate ResultSet objects (sending and sql query to database)
        ResultSet rs = statement.executeQuery(query); // Get your ResultSet from Database(Executes a supplied SQL statement. Returns a single ResultSet)
        ResultSetMetaData metadata = rs.getMetaData();//This method will return the value of the given column as a Java object.
        int columnCount = metadata.getColumnCount(); //Returns the number of columns in this ResultSet object

        List<String> list = new ArrayList<>(); //Creating a List of type String using ArrayList
        while (rs.next()) {                                   //moves the pointer of the current (ResultSet) object to the next row, from the current position.
            for (int i = 1; i <= columnCount; i++) {
                String str = rs.getString(i);            //Retrieves the value of the designated column in the current row of this ResultSet object as a String
                list.add(str);
                System.out.print(str + " ");
            }
            System.out.println();
        }
        String[] arr = list.toArray(new String[0]); //returns an array containing all the elements present in the list in proper order.
        return arr;
    }

    public ResultSet sqlStatement(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void sqlUpInsDelStatement(Connection connection, String updateQuery) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        int rowsUpdated = statement.executeUpdate(updateQuery);
        if (rowsUpdated > 0) {
            System.out.println(updateQuery + "Statement was executed successfully!");
        }
    }


//    public static List<HashMap> testHashMap(Connection connection, String query) throws SQLException {
//        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet resultSet = statement.executeQuery(query);
//        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        int columns = resultSetMetaData.getColumnCount();
//        ArrayList list = new ArrayList<>();
//        while (resultSet.next()) {
//            HashMap row = new HashMap(columns);
//            for (int i = 1; i <= columns; i++) {
//                row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
//            }
//            list.add(row);
//        }
//        System.out.println(list.toString());
//        return list;
//
//    }
}
