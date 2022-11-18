package testData;

import java.io.*;
import java.sql.*;

public class CsvToDbInserter {
    public void insertFromCSVtoDB(Connection connection, String CSVtoDbQuery) {

        String csvFilePath = "src/test/java/testData/data.csv";
        try {
//            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SET IDENTITY_INSERT BikeStores.sales.stores ON; " + CSVtoDbQuery + "SET IDENTITY_INSERT BikeStores.sales.stores OFF;");//interface to execute the parameterized query
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath)); //read the csv file

            String lineText = null;
            lineReader.readLine(); // skip header line

            //BufferedReader is used to read the text in the CSV file, line by line:
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String store_id = data[0]; //store the first data. First index is stored in id
                String store_name = data[1];
                String phone = data[2];
                String email = data[3];
                String street = data[4];
                String city = data[5];
                String state = data[6];
                String zip_code = data[7];

                //statement.setInt(1, parseInt(store_id));
                statement.setString(1, store_id); //set the parameter to the array
                statement.setString(2, store_name);
                statement.setString(3, phone);
                statement.setString(4, email);
                statement.setString(5, street);
                statement.setString(6, city);
                statement.setString(7, state);
                statement.setString(8, zip_code);

                statement.addBatch();
            }
            lineReader.close();
            // execute the remaining queries
            statement.executeBatch();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

