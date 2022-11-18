package testData;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class CSVGenerator {
    public void csvExecutor(ResultSet result) throws SQLException, IOException {

        ResultSetMetaData metadata = result.getMetaData();
        // create CSVWriter object filewriter object as parameter, //Instantiating the CSVWriter class
        CSVWriter writer = new CSVWriter(new FileWriter("src/test/java/testData/sample1.csv"));

        //Writing data to a csv file
        String csvHeader[] = {metadata.getColumnName(1), metadata.getColumnName(2), metadata.getColumnName(3), metadata.getColumnName(4), metadata.getColumnName(5), metadata.getColumnName(6), metadata.getColumnName(7), metadata.getColumnName(8), metadata.getColumnName(9)};
        writer.writeNext(csvHeader);
        String csvLine[] = new String[9];
        while (result.next()) {                                             //returns a boolean value specifying whether the ResultSet object contains more rows.
            csvLine[0] = result.getString(metadata.getColumnName(1));
            csvLine[1] = result.getString(metadata.getColumnName(2));
            csvLine[2] = result.getString(metadata.getColumnName(3));
            csvLine[3] = result.getString(metadata.getColumnName(4));
            csvLine[4] = result.getString(metadata.getColumnName(5));
            csvLine[5] = result.getString(metadata.getColumnName(6));
            csvLine[6] = result.getString(metadata.getColumnName(7));
            csvLine[7] = result.getString(metadata.getColumnName(8));
            csvLine[8] = result.getString(metadata.getColumnName(9));

            writer.writeNext(csvLine); //Writes the next line to the file.
        }
        //Flushing data from writer to file
        writer.flush(); // clear the writer of any element that may be or maybe not inside the writer
        System.out.println("Data has been written to csv file ");

    }
}


