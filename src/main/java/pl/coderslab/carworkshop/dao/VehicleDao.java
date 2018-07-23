package pl.coderslab.carworkshop.dao;

import pl.coderslab.carworkshop.db.DbConnector;
import pl.coderslab.carworkshop.db.DbRecord;
import pl.coderslab.carworkshop.model.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

public class VehicleDao {
    private static final String TABLE_NAME = "car_workshop.vehicle";

    public static Vehicle loadById(long id) {
        DbRecord record = DbConnector.getRecordById(TABLE_NAME, id);
        return getVehicleFromDbRecord(record);
    }

    public static ArrayList<Vehicle> loadAll() {

        ArrayList<DbRecord> records = DbConnector.getAllRecords(TABLE_NAME);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (DbRecord record : records) {
            vehicles.add(getVehicleFromDbRecord(record));
        }

        return vehicles;
    }

    public static ArrayList<Vehicle> loadAllByCustomerId(long customerId) {

        ArrayList<DbRecord> records = DbConnector.getRecordsBySpecified(TABLE_NAME, customerId, "customer_id");
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        if (records != null) {
            for (DbRecord record : records) {
                vehicles.add(getVehicleFromDbRecord(record));
            }
        }

        return vehicles;
    }

    private static Vehicle getVehicleFromDbRecord(DbRecord record) {
        if (record != null) {
            return new Vehicle(
                    Integer.parseInt(record.getValue("id")),
                    record.getValue("model"),
                    record.getValue("maker"),
                    Integer.parseInt(record.getValue("production_year")),
                    record.getValue("registration_number"),
                    LocalDate.parse(record.getValue("next_overhaul")),
                    Integer.parseInt(record.getValue("customer_id"))
            );
        } else {
            return null;
        }
    }

    public static long saveToDb(Vehicle vehicle) {
        DbRecord record = new DbRecord();
        if (vehicle.getId() > 0) record.putValue("id", Long.toString(vehicle.getId()));

        record.putValue("model", vehicle.getModel());
        record.putValue("maker", vehicle.getMaker());
        record.putValue("production_year", Integer.toString(vehicle.getProductionYear()));
        record.putValue("registration_number", vehicle.getRegistrationNumber());
        record.putValue("next_overhaul", vehicle.getNextOverhaul().toString());
        record.putValue("customer_id", Integer.toString(vehicle.getCustomerId()));

        return DbConnector.saveToDb(TABLE_NAME, record);
    }

    public static void delete(Vehicle vehicle) {
        if (vehicle.getId() > 0) {
            if (DbConnector.deleteById(TABLE_NAME, vehicle.getId())) vehicle.setId(0);
        }
    }

    public static void deleteById(int id) {
        if (id > 0) {
            if (DbConnector.deleteById(TABLE_NAME, id));
        }
    }
}
