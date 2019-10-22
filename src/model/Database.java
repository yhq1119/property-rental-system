package model;

import exceptions.InsertDBException;
import utilities.DateTime;
import utilities.PropertyStatus;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings({"SameParameterValue", "unused"})
public class Database {


    final private String DB_FLEXI_RENT = "FlexiRentDB";
    private final String TABLE_RENTAL_PROPERTY = "RENTAL_PROPERTY";
    private final String TABLE_RENTAL_RECORD = "RENTAL_RECORD";




    public void insertPropertyAndRecordsIntoTable (FlexiRentalSystem flexiRentalSystem)throws InsertDBException {
        for(RentalProperty P:flexiRentalSystem.getProperties()){
            insertRowToPropertyTable(P);
            for (RentalRecord R:P.getRentalRecords()){
                insertRowToRecordsTable(R,P.getPropertyId());
            }
        }
    }

    public void readFromPropertyAndRecordTable(FlexiRentalSystem flexiRentalSystem){
        flexiRentalSystem.setProperties(readPropertyFromTable());
        readRecordsFromTableToProperty(flexiRentalSystem.getProperties());
    }

    private PropertyStatus statusFromString(String status) {
        PropertyStatus propertyStatus;
        if (status.toUpperCase().equals("Available".toUpperCase())) {
            propertyStatus = PropertyStatus.AVAILABLE;
        } else if (status.toUpperCase().equals("Rented".toUpperCase())) {
            propertyStatus = PropertyStatus.RENTED;
        } else if (status.toUpperCase().equals("Under Maintenance".toUpperCase())) {
            propertyStatus = PropertyStatus.UNDER_MAINTENANCE;
        } else {
            propertyStatus = PropertyStatus.AVAILABLE;
        }
        return propertyStatus;
    }

    private DateTime dateFromString(String date) {
        DateTime temp = null;
        try {
            String[] a = date.split("/");
            //18/11/2018
            temp = new DateTime(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]));

        } catch (Exception e) {

            System.out.println("Failed to translate from string to datetime: "+date);

        }

        return temp;


    }

    private ArrayList<RentalProperty> readPropertyFromTable() {

        ArrayList<RentalProperty> temp = new ArrayList<>();
        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            String query = "SELECT * FROM " + TABLE_RENTAL_PROPERTY;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("PROPERTY_ID"));
                    String streetNum = resultSet.getString("STREET_NUM");
                    String streetName = resultSet.getString("STREET_NAME");
                    String suburb = resultSet.getString("SUBURB");
                    String type = resultSet.getString("PROPERTY_TYPE");
                    int numOfRooms = resultSet.getInt("BEDROOMS_NUM");
                    String status = resultSet.getString("PROPERTY_STATUS");
                    String maintenanceDate = resultSet.getString("MAINTENANCE_DATE");

                    String imageName = resultSet.getString("IMAGE_NAME");
                    String description = resultSet.getString("DESCRIPTION");

                    DateTime dateTime = dateFromString(maintenanceDate);


                    if (type.toUpperCase().equals("Apartment".toUpperCase())) {
                     Apartment a1= new Apartment(streetNum, streetName, suburb, numOfRooms);
                        a1.setStatus(statusFromString(status));
                        a1.setDescription(description);
                        a1.setImagePath(new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\images\\Apartment\\" + imageName));
                    temp.add(a1);
                    }
                    if (type.toUpperCase().equals("Premium Suite".toUpperCase())) {
                        PremiumSuite p1 =  new PremiumSuite(streetNum, streetName, suburb, dateTime);
                        p1.setStatus(statusFromString(status));
                        p1.setDescription(description);
                        p1.setImagePath(new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\images\\PremiumSuite\\" + imageName));
                  temp.add(p1);
                    }


                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }


    private void readRecordsFromTableToProperty(ArrayList<RentalProperty> properties) {


        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            String query = "SELECT * FROM " + TABLE_RENTAL_RECORD;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("RECORD_ID"));
                    String recordId = resultSet.getString("RECORD_ID");
                    String propertyId = resultSet.getString("PROPERTY_ID");
                    String rentdates = resultSet.getString("RENT_DATE");
                    String estidates = resultSet.getString("ESTIMATED_RETURN_DATE");
                    String actudates = resultSet.getString("ACTUAL_RETURN_DATE");
                    double rentFee = resultSet.getDouble("RENT_FEE");
                    double lateFee = resultSet.getDouble("LATE_FEE");


                    DateTime rentDate = dateFromString(rentdates);
                    DateTime estiDate = dateFromString(estidates);
                    DateTime actuDate = dateFromString(actudates);
                    RentalRecord record = new RentalRecord();
                    record.setRecordId(recordId);
                    record.setRentDate(rentDate);
                    record.setEstimatedReturnDate(estiDate);
                    record.setActulReturnDate(actuDate);
                    record.setRentalFee(rentFee);
                    record.setLateFee(lateFee);
                    for (RentalProperty Pp : properties) {
                        if (Pp.getPropertyId().toUpperCase().equals(propertyId.toUpperCase())) {
                            Pp.getRentalRecords().add(record);
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static Connection getConnection(String dbName) throws SQLException, ClassNotFoundException {
        //Registering the HSQLDB JDBC driver
        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        /* Database files will be created in the "database"
         * folder in the project. If no username or password is
         * specified, the default SA user and an empty password are used */
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:database/" + dbName, "SA", "");
        return con;
    }

    private void connetToDatabase() {

        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT)) {

            System.out.println("Connection to database " + DB_FLEXI_RENT + " created successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createPropertyTable() {

        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            int result = stmt.executeUpdate("CREATE TABLE " + TABLE_RENTAL_PROPERTY + " ("
                    + "PROPERTY_ID VARCHAR(100) NOT NULL,"
                    + "STREET_NUM VARCHAR(100) NOT NULL,"
                    + "STREET_NAME VARCHAR(100) NOT NULL,"
                    + "SUBURB VARCHAR(100) NOT NULL,"
                    + "PROPERTY_TYPE VARCHAR(100) NOT NULL,"
                    + "BEDROOMS_NUM INT NOT NULL,"
                    + "PROPERTY_STATUS VARCHAR(100) NOT NULL,"
                    + "MAINTENANCE_DATE VARCHAR(100) NOT NULL,"
                    + "RENT_RATE DOUBLE NOT NULL,"
                    + "LATE_RATE DOUBLE NOT NULL,"
                    + "IMAGE_NAME VARCHAR(100) NOT NULL,"
                    + "DESCRIPTION VARCHAR(4095) NOT NULL,"
                    + "PRIMARY KEY (PROPERTY_ID))");
            if (result == 0) {
                System.out.println("Table " + TABLE_RENTAL_PROPERTY + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_RENTAL_PROPERTY + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRecordTable() {

        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            int result = stmt.executeUpdate("CREATE TABLE " + TABLE_RENTAL_RECORD + " ("
                    + "RECORD_ID VARCHAR(100) NOT NULL,"
                    + "PROPERTY_ID VARCHAR(100) NOT NULL,"
                    + "RENT_DATE VARCHAR(100) NOT NULL,"
                    + "ESTIMATED_RETURN_DATE VARCHAR(100) NOT NULL,"
                    + "ACTUAL_RETURN_DATE VARCHAR(100) NOT NULL,"
                    + "RENT_FEE DOUBLE ,"
                    + "LATE_FEE DOUBLE ,"
                    + "PRIMARY KEY (RECORD_ID),"
                    + "FOREIGN KEY (PROPERTY_ID) REFERENCES RENTAL_PROPERTY(PROPERTY_ID))");
            if (result == 0) {
                System.out.println("Table " + TABLE_RENTAL_RECORD + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_RENTAL_RECORD + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRowToPropertyTable(RentalProperty Ps)throws InsertDBException {


        int result = 0;
        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {


            if (Ps instanceof Apartment) {
                String query = "INSERT INTO " + TABLE_RENTAL_PROPERTY +
                        " VALUES ("
                        + "'" + Ps.getPropertyId() + "'" + ","
                        + "'" + Ps.getStreetNum() + "'" + ","
                        + "'" + Ps.getStreetName() + "'" + ","
                        + "'" + Ps.getSuburb() + "'" + ","
                        + "'" + Ps.getStringType() + "'" + ","
                        + "'" + Ps.getNumOfRooms() + "'" + ","
                        + "'" + Ps.getStringStatus() + "'" + ","
                        + "'none'" + ","
                        + Ps.getRentRate() + ","
                        + Ps.getLateRate() + ","
                        + "'" + Ps.getImageName() + "'" + ","
                        + "'" + Ps.getDescription() + "'" + ")";

                result = stmt.executeUpdate(query);
            }
            if (Ps instanceof PremiumSuite) {
                String query = "INSERT INTO " + TABLE_RENTAL_PROPERTY +
                        " VALUES ("
                        + "'" + Ps.getPropertyId() + "'" + ","
                        + "'" + Ps.getStreetNum() + "'" + ","
                        + "'" + Ps.getStreetName() + "'" + ","
                        + "'" + Ps.getSuburb() + "'" + ","
                        + "'" + Ps.getStringType() + "'" + ","
                        + "'" + Ps.getNumOfRooms() + "'" + ","
                        + "'" + Ps.getStringStatus() + "'" + ","
                        + "'" + ((PremiumSuite) Ps).getLastMaintenanceDate().getFormattedDate() + "'" + ","
                        + Ps.getRentRate() + ","
                        + Ps.getLateRate() + ","
                        + "'" + Ps.getImageName() + "'" + ","
                        + "'" + Ps.getDescription() + "'" + ")";


                result = stmt.executeUpdate(query);
            }


            con.commit();

            System.out.println("Insert into table " + TABLE_RENTAL_PROPERTY + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertRowToRecordsTable(RentalProperty P)throws InsertDBException{

        insertRowToRecordsTable(P.getRentalRecords().get(0),P.getPropertyId());

    }

    private void insertRowToRecordsTable(RentalRecord rentalRecord, String propertyId) {

        int result;

        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {


            System.out.println("A1");

            System.out.println(rentalRecord.getActulReturnDate());

            String k;
            if (rentalRecord.getActulReturnDate() != null) {
                k = rentalRecord.getActulReturnDate().getFormattedDate();
            } else {
                k = "none";
            }
            System.out.println(k);
            System.out.println("B1");
            System.out.println(rentalRecord.toString());


            String query = "INSERT INTO " + TABLE_RENTAL_RECORD +
                    " VALUES (" +
                    "'" + rentalRecord.getRecordId() + "'" + "," +
                    "'" + propertyId + "'" + "," +
                    "'" + rentalRecord.getRentDate().getFormattedDate() + "'" + "," +
                    "'" + rentalRecord.getEstimatedReturnDate().getFormattedDate() + "'" + "," +
                    "'" + k + "'" + "," +
                    +rentalRecord.getRentalFee() + "," +
                    +rentalRecord.getLateFee() +
                    ")";

            System.out.println("B2");
            System.out.println(query);
            result = stmt.executeUpdate(query);
            System.out.println("A2");

            con.commit();

            System.out.println("Insert into table " + TABLE_RENTAL_RECORD + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println("C1");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void deleteRow(String table_name, String id) {


        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            String query = "DELETE FROM " + table_name +
                    " WHERE PROPERTY_ID LIKE " + id;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + table_name + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputQuery(String query) {


        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {


            int result = stmt.executeUpdate(query);

            System.out.println("Executed Query Statement: " + query);
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateProperty(RentalProperty P) {
        String status = P.getStringStatus();
        String description = P.getDescription();
        String maintainDate = "none";
        if (P instanceof PremiumSuite) {
            maintainDate = ((PremiumSuite) P).getLastMaintenanceDate().getFormattedDate();
        }

        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {


            String query = "UPDATE " + TABLE_RENTAL_PROPERTY +
                    " SET " +
                    " PROPERTY_STATUS = " + "'" + status + "'" + "," +
                    " DESCRIPTION = " + "'" + description + "'" + "," +
                    " MAINTENANCE_DATE = " + "'" + maintainDate + "'"  +
                    " WHERE PROPERTY_ID LIKE " + "'"+P.getPropertyId()+"'";

            int result = stmt.executeUpdate(query);



            System.out.println("Update table " + TABLE_RENTAL_PROPERTY + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deletePropertyAndRecordTables() {

        deleteTable("RENTAL_RECORD");
        deleteTable("RENTAL_PROPERTY");


    }

    public void createPropertyAndRecordTables() {
        createPropertyTable();
        createRecordTable();

    }

    public void updateRecord(RentalRecord R) {
        String recordId = R.getRecordId();
        String actuDate = R.getActulReturnDate().getFormattedDate();
        double rentFee = R.getRentalFee();
        double lateFee = R.getLateFee();

        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {


            String query = "UPDATE " + TABLE_RENTAL_RECORD +
                    " SET " +
                    " ACTUAL_RETURN_DATE = " + "'" + actuDate + "'" + "," +
                    " RENT_FEE = " + rentFee + "," +
                    " LATE_FEE = " + lateFee  +
                    " WHERE RECORD_ID LIKE " + "'"+recordId+"'";

            int result = stmt.executeUpdate(query);


            System.out.println("Update table " + TABLE_RENTAL_RECORD + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private void deleteTable(String table_name) {

        //use try-with-resources Statement
        try (Connection con = getConnection(DB_FLEXI_RENT);
             Statement stmt = con.createStatement()
        ) {
            int result = stmt.executeUpdate("DROP TABLE " + table_name);

            if (result == 0) {
                System.out.println("Table " + TABLE_RENTAL_PROPERTY + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_RENTAL_PROPERTY + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
