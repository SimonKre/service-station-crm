package pl.coderslab.carworkshop.dao;

import pl.coderslab.carworkshop.db.DbConnector;
import pl.coderslab.carworkshop.db.DbRecord;
import pl.coderslab.carworkshop.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDao {
    private static final String TABLE_NAME = "car_workshop.order";

    public static Order loadById(long id) {
        DbRecord record = DbConnector.getRecordById(TABLE_NAME, id);

        return record != null ? getOrderFromDbRecord(record) : null;
    }

    public static ArrayList<Order> loadAll() {

        ArrayList<DbRecord> records = DbConnector.getAllRecords(TABLE_NAME);
        ArrayList<Order> orders = new ArrayList<>();

        if (records != null) {
            for (DbRecord record : records) {
                orders.add(getOrderFromDbRecord(record));
            }
        }

        return orders;
    }

    public static ArrayList<Order> loadAllByEmployeeId(long employeeId) {

        ArrayList<DbRecord> records = DbConnector.getRecordsBySpecified(TABLE_NAME, employeeId, "employee_id");
        ArrayList<Order> orders = new ArrayList<>();

        if (records != null) {
            for (DbRecord record : records) {
                orders.add(getOrderFromDbRecord(record));
            }
        }

        return orders;
    }

    public static ArrayList<Order> loadAllByVehicleId(long vehicleId) {

        ArrayList<DbRecord> records = DbConnector.getRecordsBySpecified(TABLE_NAME, vehicleId, "vehicle_id");
        ArrayList<Order> orders = new ArrayList<>();

        if (records != null) {
            for (DbRecord record : records) {
                orders.add(getOrderFromDbRecord(record));
            }
        }

        return orders;
    }

    public static ArrayList<Order> loadAllByCustomerId(long customerId) {

        ArrayList<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " INNER JOIN vehicle ON order.vehicle_id = vehicle.id WHERE customer_id = ?";

        try (PreparedStatement statement = DbConnector.getConnection().prepareStatement(query)) {

            statement.setLong(1, customerId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    DbRecord record = new DbRecord();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        record.putValue(rs.getMetaData().getColumnName(i), rs.getString(i));
                    }
                    orders.add(getOrderFromDbRecord(record));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos się nie powiodło");
        }

        return orders;
    }

    public static long saveToDb(Order order) {
        DbRecord record = new DbRecord();
        if (order.getId() > 0) record.putValue("id", Long.toString(order.getId()));

        record.putValue("planned_start", order.getPlannedStart() != null ? order.getPlannedStart().toString() : null);
        record.putValue("start", order.getStart() != null ? order.getStart().toString() : null);
        record.putValue("employee_id", order.getEmployeeId() != null ? Integer.toString(order.getEmployeeId()) : null);
        record.putValue("problem_desc", order.getProblemDesc());
        record.putValue("repair_desc", order.getRepairDesc());
        record.putValue("status_id", Integer.toString(order.getStatusId()));
        record.putValue("vehicle_id", Integer.toString(order.getVehicleId()));
        record.putValue("total_cost", order.getTotalCost() != null ? Double.toString(order.getTotalCost()) : null);
        record.putValue("parts_cost", order.getPartsCost() != null ? Double.toString(order.getPartsCost()) : null);
        record.putValue("hours_spent", order.getHoursSpent() != null ? Float.toString(order.getHoursSpent()) : null);

        return DbConnector.saveToDb(TABLE_NAME, record);
    }

    public static void delete(Order order) {
        if (order.getId() > 0) {
            if (DbConnector.deleteById(TABLE_NAME, order.getId())) order.setId(0);
        }
    }

    public static void deleteById(int id) {
        if (id > 0) {
            if (DbConnector.deleteById(TABLE_NAME, id));
        }
    }

    private static Order getOrderFromDbRecord(DbRecord record) {
        if (record != null) {
            return new Order(
                    (int) Long.parseLong(record.getValue("id")),
                    record.getValue("planned_start") != null ? LocalDate.parse(record.getValue("planned_start")) : null,
                    record.getValue("start") != null ? LocalDate.parse(record.getValue("start")) : null,
                    record.getValue("employee_id") != null ? Integer.parseInt(record.getValue("employee_id")) : null,
                    record.getValue("problem_desc"),
                    record.getValue("repair_desc"),
                    Integer.parseInt(record.getValue("status_id")),
                    Integer.parseInt(record.getValue("vehicle_id")),
                    record.getValue("total_cost") != null ? Double.parseDouble(record.getValue("total_cost")) : null,
                    record.getValue("parts_cost") != null ? Double.parseDouble(record.getValue("parts_cost")) : null,
                    record.getValue("hours_spent") != null ? Float.parseFloat(record.getValue("hours_spent")) : null
            );
        } else {
            return null;
        }
    }
}
