package pl.coderslab.carworkshop.db;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DbConnector {

    private static DataSource ds;

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if (ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource) ctx.lookup("java:comp/env/jdbc/car");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }

    public static boolean deleteById(String tableName, long id) {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";

        if (getRecordById(tableName, id) != null) {
            try (PreparedStatement statement = getConnection().prepareStatement(query)) {

                statement.setLong(1, id);
                statement.execute();
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Usunięcie rekordu nie powiodło się");
                return false;
            }
        }

        return false;
    }

    public static long saveToDb(String tableName, DbRecord record) {
        ArrayList<String> values = new ArrayList<>();
        String query;

        if (record.getValue("id") == null) {
            query = "INSERT INTO " + tableName + "(";


            for (String label : record.getAllColumns().keySet()) {
                query += label + ",";
                values.add(record.getValue(label));
            }

            query = query.substring(0, query.length() - 1) + ") VALUES (";
            for (int i = 0; i < values.size(); i++) {
                query += (values.get(i) != null ? "'" + values.get(i) + "'" : "NULL");
                if (i < values.size() - 1) query += ",";
            }
            query += ")";

            try (Statement statement = getConnection().createStatement()) {

                String generatedColumns[] = {"ID"};
                statement.executeUpdate(query, generatedColumns);

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        return resultSet.getLong(1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Cos się nie powiodło");
            }

        } else {

            query = "UPDATE " + tableName + " SET ";

            for (String label : record.getAllColumns().keySet()) {
                if (!label.equalsIgnoreCase("id")) {
                    query += label + "=" + (record.getValue(label) != null ? "'" + record.getValue(label) + "'" : "NULL") + ",";
                }
            }
            query = query.substring(0, query.length() - 1) + " WHERE id = " + record.getValue("id");

            System.out.println(query);

            try (Statement statement = getConnection().createStatement()) {

                statement.executeUpdate(query);
                return Long.parseLong(record.getValue("id"));

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Cos się nie powiodło");
            }
        }

        return 0;
    }

    public static DbRecord getRecordById(String tableName, long id) {

        ArrayList<DbRecord> records = getRecords(tableName, id, "id");

        if (!records.isEmpty() && id > 0) return records.get(0);
        else return null;
    }

    public static ArrayList<DbRecord> getRecordsBySpecified(String tableName, Long whereId, String whereColumnName) {

        ArrayList<DbRecord> records = getRecords(tableName, whereId, whereColumnName);

        if (!records.isEmpty()) return records;
        else return null;
    }

    public static ArrayList<DbRecord> getAllRecords(String tableName) {

        return getRecords(tableName, 0, "");
    }

    public static ArrayList<DbRecord> getAllRecords(String tableName, int limit) {

        if (limit < 1) return null;
        return getRecordsWithLimit(tableName, 0, limit, "");
    }


    private static ArrayList<DbRecord> getRecords(String tableName, long id, String whereParameter) {

        return getRecordsWithLimit(tableName, id, 0, whereParameter);
    }

    private static ArrayList<DbRecord> getRecordsWithLimit(String tableName, long id, int limit, String whereParameter) {

        ArrayList<DbRecord> records = new ArrayList<>();

        String query = "SELECT * FROM " + tableName;
        if (id > 0) query += " WHERE " + whereParameter + " = ?";
        if (limit > 0) query += " ORDER BY id DESC LIMIT ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            if (id > 0) {
                statement.setLong(1, id);
                if (limit > 0) statement.setLong(2, limit);
            } else {
                if (limit > 0) statement.setLong(1, limit);
            }

            System.out.println(query);
            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {

                    DbRecord record = new DbRecord();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        record.putValue(rs.getMetaData().getColumnName(i), rs.getString(i));
                    }
                    records.add(record);
                }

                return records;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos się nie powiodło");
            return records;
        }

    }

}
