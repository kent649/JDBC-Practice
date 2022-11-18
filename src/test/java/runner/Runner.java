package runner;

import connections.DBconnection;
import connections.QueryFactory;
import testData.CSVGenerator;
import testData.CsvToDbInserter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {
    private static String query = "select top 3 * from [BikeStores].[sales].[customers]";
    private static String updateQu = "update [BikeStores].[production].[products] SET list_price = '111.11' WHERE product_id = '1' ";
    private static String insertQu = "INSERT INTO sales.staffs VALUES('Test','Test','test@emai.bikes.shop','(111) 111-1111','1','1','3')";
    private static String deleteQu = "DELETE FROM production.stocks WHERE quantity = '0'";
    private static String CSVtoDbQuery = "INSERT INTO BikeStores.sales.stores ( store_id, store_name, phone, email, street, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static void main(String[] args) throws SQLException, IOException {
        DBconnection dBconnection = new DBconnection();
        Connection con = dBconnection.getConnection();

        QueryFactory queryFactory = new QueryFactory();
        queryFactory.arrayResult(con, query);
        queryFactory.sqlUpInsDelStatement(con, updateQu);
//        queryFactory.sqlUpInsDelStatement(con, insertQu);
//        queryFactory.sqlUpInsDelStatement(con, deleteQu);
        ResultSet resultSet = queryFactory.sqlStatement(con, query);
        CSVGenerator csvGenerator = new CSVGenerator();
        csvGenerator.csvExecutor(resultSet);
        CsvToDbInserter excelToDbInserter = new CsvToDbInserter();
        excelToDbInserter.insertFromCSVtoDB(con, CSVtoDbQuery);
        dBconnection.dBClose();

    }
}
