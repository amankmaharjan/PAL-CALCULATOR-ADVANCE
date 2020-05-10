
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aman
 */
public class DatabaseUtility {

    // Database constants
    private final String USER_NAME = "root";
    private final String PASSWORD = "";
    private final String DB_NAME = "Assignment2";
    private final String TBL_NAME = "UserEnergy";
    // File contants
    private final String ID_SUFFIX = "S10";
    private final String DELIMETER = ",";
    private final String FILE_NAME = "COIT20256Ass2Datatest.csv";
    // Variable declarations for  mysql db
    private final String MYSQL_URL;
    private Statement statement;
    private final String dbCreateSQL;
    private final String TABLE_USER_ENERGYU_QRY;
    private final String SELECT_USER_ENERGYU_QRY;
    private ResultSet rS = null;
    private DatabaseMetaData dbmd;
    private final String DB_URL;
    private Connection sqlConnection, dbConnection;
// Constructor

    public DatabaseUtility() throws ClassNotFoundException {
        //Mysql url
        MYSQL_URL = "jdbc:mysql://localhost:3306";
        DB_URL = MYSQL_URL + "/" + DB_NAME;
        //initialise MySql usename and password 
        statement = null;
        //sql query to create database.
        dbCreateSQL = "CREATE DATABASE" + " " + DB_NAME;
        //sql queries to create Tables
        TABLE_USER_ENERGYU_QRY = "CREATE TABLE" + " " + TBL_NAME + " "
                + "( id int NOT NULL AUTO_INCREMENT,"
                + " Name varchar(255),"
                + " Height double,"
                + " Gender varchar(255),"
                + " ageGroup varchar(255),"
                + " palDescription varchar(255), "
                + " palValue varchar(255),"
                + " energyValue double,"
                + " expectedWeight double,"
                + " PRIMARY KEY (ID) )";

        SELECT_USER_ENERGYU_QRY = "SELECT * FROM " + " " + TBL_NAME;
        createDBtables();

    }
// Method for Database connection

    public Connection getConnection(String URL) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
// Method for creating tables

    public boolean createDBtables() throws ClassNotFoundException {
        boolean dbExists = false;
        boolean tblUserExist = false;
        boolean dbCreated = false;
        String databaseName = "";
        //connect to MySql ;
        try {
            sqlConnection = getConnection(MYSQL_URL);
            statement = sqlConnection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
        //check whether the databse exists.
        try {
            //get the list of databases
            ResultSet dbData = sqlConnection.getMetaData().getCatalogs();

            //iterate each catalog in the ResultSet 
            while (dbData.next()) {
                // Get the database name, which is at position 1
                databaseName = dbData.getString(1);
                // System.out.printf("%s ",databaseName);  
                if (databaseName.equalsIgnoreCase("Assignment2")) {
                    dbExists = true;
                }
            }
            if (!dbExists) //if database doesn't exist create database executing the query.
            {
                statement.executeUpdate(dbCreateSQL);
            }
            if (sqlConnection != null) {
                sqlConnection.close();  //close the existing connection to connect to MySql
            }             //connect to Assignment2 database
            dbConnection = getConnection(DB_URL);
            statement = dbConnection.createStatement();
            dbmd = dbConnection.getMetaData();
            // loop through the list of tables if the tables are already created
            ResultSet rs = dbmd.getTables(null, null, "%", null);
            while (rs.next()) {
                if ((rs.getString(3).equalsIgnoreCase(TBL_NAME))) {
                    tblUserExist = true;
                }
            }
            //if any of the tables doesn't exist create table executing the query
            if (!tblUserExist) {
                statement.executeUpdate(TABLE_USER_ENERGYU_QRY);
            }

            if (tblUserExist) {
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
            return false;
        }
        return true;
    }
// Method for inserting user energy

    public boolean insertUserEnergy(UserEnergyNeeds userEnergyNeeds) {
        boolean insertSuccess = true;
        PreparedStatement addUserEnergyStmt;
        try {
            if (dbConnection == null) {
                dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            }
            addUserEnergyStmt = dbConnection.prepareStatement("INSERT INTO " + TBL_NAME
                    + "(Name, Height,Gender,ageGroup,"
                    + "palDescription, palValue,"
                    + "energyValue,expectedWeight)"
                    + "VALUES (?,?,?,?,?,?,?,?)");
            addUserEnergyStmt.setString(1, userEnergyNeeds.getName());
            addUserEnergyStmt.setDouble(2, userEnergyNeeds.getHeight());
            addUserEnergyStmt.setString(3, userEnergyNeeds.getGender());
            addUserEnergyStmt.setString(4, userEnergyNeeds.getAgeGroup());
            addUserEnergyStmt.setString(5, userEnergyNeeds.getPaldata().getPal().getDescription());
            addUserEnergyStmt.setString(6, userEnergyNeeds.getPaldata().getPal().getValue());
            addUserEnergyStmt.setDouble(7, userEnergyNeeds.getPaldata().getEnergy());
            addUserEnergyStmt.setDouble(8, userEnergyNeeds.getWeight());
            if (addUserEnergyStmt.executeUpdate() == 0) {
                insertSuccess = false;
            }
            System.out.println("User energy data added");
            addUserEnergyStmt.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
            return insertSuccess;
        }
        return insertSuccess;
    }
// Method for displaying user energy

    public List<UserEnergyNeeds> displayUserEnergyNeeds() {
        List<UserEnergyNeeds> listofUserEnergyNeeds = new LinkedList<>();
        try {
            if (dbConnection == null) {
                dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            }

            try {
                statement = dbConnection.createStatement();
                rS = statement.executeQuery(SELECT_USER_ENERGYU_QRY);
                System.out.println(rS);
                while (rS.next()) {
                    UserEnergyNeeds userEnergyNeed = new UserEnergyNeeds();
                    userEnergyNeed.setName(rS.getString("name"));
                    userEnergyNeed.setHeight(rS.getDouble("height"));
                    userEnergyNeed.setAgeGroup(rS.getString("ageGroup"));
                    userEnergyNeed.setGender(rS.getString("gender"));
                    userEnergyNeed.setPaldata(new PALdata(rS.getString("palDescription"), rS.getFloat("energyValue")));
                    userEnergyNeed.setWeight(rS.getDouble("expectedWeight"));
                    listofUserEnergyNeeds.add(userEnergyNeed);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            rS.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
        return listofUserEnergyNeeds;
    }
// Method for generating name Id file for part b

    public void generateNameIDFile() {
        // variables for name and ids
        List<String> nameList = new LinkedList<>();
        List<String> idList = new LinkedList<>();

        try {
            // Establishing database connection
            if (dbConnection == null) {
                dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            }

            try {
                // get statement object
                statement = dbConnection.createStatement();
                // excute select query
                rS = statement.executeQuery(SELECT_USER_ENERGYU_QRY);
                System.out.println(rS);
                while (rS.next()) {
                    nameList.add(rS.getString("name"));
                    idList.add(ID_SUFFIX + rS.getString("id"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            rS.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }

        try {
            // File writer object
            FileWriter fw = new FileWriter(FILE_NAME);
            String header = "ID" + DELIMETER + "Name";
            fw.append(header);

            for (int i = 0; i < nameList.size(); i++) {
                fw.append("\n");
                fw.append(idList.get(i) + DELIMETER + nameList.get(i));
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
