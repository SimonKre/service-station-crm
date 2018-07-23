package pl.coderslab.carworkshop.dao;

import pl.coderslab.carworkshop.db.DbConnector;
import pl.coderslab.carworkshop.db.DbRecord;
import pl.coderslab.carworkshop.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerDao {
    private static final String TABLE_NAME = "car_workshop.customer";

    public static Customer loadById(long id) {
        DbRecord record = DbConnector.getRecordById(TABLE_NAME, id);
        return getCustomerFromDbRecord(record);
    }

    public static ArrayList<Customer> loadAll() {

        ArrayList<DbRecord> records = DbConnector.getAllRecords(TABLE_NAME);
        ArrayList<Customer> customers = new ArrayList<>();

        for (DbRecord record : records) {
            customers.add(getCustomerFromDbRecord(record));
        }

        return customers;
    }

    private static Customer getCustomerFromDbRecord(DbRecord record) {
        if (record != null) {
            return new Customer(
                    Integer.parseInt(record.getValue("id")),
                    record.getValue("name"),
                    record.getValue("surname"),
                    record.getValue("birthdate") != null ? LocalDate.parse(record.getValue("birthdate")) : null
            );
        } else {
            return null;
        }
    }

    public static long saveToDb(Customer customer) {
        DbRecord record = new DbRecord();
        if (customer.getId() > 0) record.putValue("id", Long.toString(customer.getId()));

        record.putValue("name", customer.getName());
        record.putValue("surname", customer.getSurname());
        record.putValue("birthdate", customer.getBirthdate() != null ? customer.getBirthdate().toString() : null);

        return DbConnector.saveToDb(TABLE_NAME, record);
    }

    public static void delete(Customer customer) {
        if (customer.getId() > 0) {
            if (DbConnector.deleteById(TABLE_NAME, customer.getId())) customer.setId(0);
        }
    }

    public static void deleteById(int id) {
        if (id > 0) {
            if (DbConnector.deleteById(TABLE_NAME, id));
        }
    }
}
