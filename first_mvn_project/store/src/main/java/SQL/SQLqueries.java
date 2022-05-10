package SQL;
import by.issoft.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLqueries {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void createTables()
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            //System.out.println("Creating tables in given database...");
            stmt = conn.createStatement();
            String sql =  "DROP TABLE IF EXISTS CATEGORIES; " +
                    "CREATE TABLE CATEGORIES " +
                    "(CID INTEGER not NULL, " +
                    "CATEGORY_NAME VARCHAR(30)," +
                    " PRIMARY KEY ( CID ));" +
                    "INSERT INTO CATEGORIES VALUES(1, 'Food');" +
                    "INSERT INTO CATEGORIES VALUES(2, 'Book');";
            String sql2 =  "DROP TABLE IF EXISTS PRODUCTS; " +
                    "CREATE TABLE PRODUCTS " +
                    "(ID INTEGER not NULL, " +
                    "CAT_ID INTEGER, " +
                    "NAME VARCHAR(100)," +
                    "RATE INTEGER," +
                    "PRICE DOUBLE," +
                    " PRIMARY KEY ( ID ));";
            stmt.executeUpdate(sql);
            stmt.execute(sql2);
            //System.out.println("Created tables in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        //System.out.println("End");
    }

    public static void addProducts(List<Product> list)
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("Connected database successfully...");

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            StringBuilder values = new StringBuilder();
            for (int i = 0; i < list.size(); i++)
            {
                int cat_id = (list.get(i).getCategory().contains("Food") ? 1: 2);
                String val = "INSERT INTO PRODUCTS " + "VALUES (" +
                        (i+1) +  ", " +
                        cat_id + ", " + "'" +
                        list.get(i).getName() + "'" +  ", " +
                        list.get(i).getRate() + ", " +
                        list.get(i).getPrice() +  "); ";
                values.append(val);
            }

            stmt.executeUpdate(values.toString());
            //System.out.println("Inserted records into the table...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        //System.out.println("End");
    }

    public static List<String> getCategories()
    {
        Connection conn = null;
        Statement stmt = null;
        List<String> categoriesList = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT CATEGORY_NAME FROM CATEGORIES";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String categoryName = rs.getString("CATEGORY_NAME");
                categoriesList.add(rs.getString("CATEGORY_NAME"));
                // Display values
                //System.out.println("category name: " + categoryName);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        //System.out.println("End");
        return categoriesList;
    }

    public static void getProducts()
    {
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            //System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT CATEGORY_NAME, NAME, RATE, PRICE FROM PRODUCTS JOIN CATEGORIES ON CAT_ID = CID ORDER BY PRICE ASC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String categoryName = rs.getString("CATEGORY_NAME");
                String productName = rs.getString("NAME");
                Integer rate = rs.getInt("RATE");
                Double price = rs.getDouble("PRICE");
                // Display values
                System.out.println("Category: " + categoryName + ", product name: " + productName + ", rate: " + rate + ", price: " + price);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        //System.out.println("End");
    }
}
