package pl.coderslab.carworkshop.dao;

import pl.coderslab.carworkshop.db.DbConnector;
import pl.coderslab.carworkshop.db.DbRecord;
import pl.coderslab.carworkshop.model.Employee;
import java.util.ArrayList;

public class EmployeeDao {
    private static final String TABLE_NAME = "car_workshop.employee";

    public static Employee loadById(long id) {
        DbRecord record = DbConnector.getRecordById(TABLE_NAME, id);
        return getEmployeeFromDbRecord(record);
    }

    //TODO add null protection for records like in OrderDao
    public static ArrayList<Employee> loadAll() {

        ArrayList<DbRecord> records = DbConnector.getAllRecords(TABLE_NAME);
        ArrayList<Employee> employees = new ArrayList<>();

        for (DbRecord record : records) {
            employees.add(getEmployeeFromDbRecord(record));
        }

        return employees;
    }

    private static Employee getEmployeeFromDbRecord(DbRecord record) {
        if (record != null) {
            return new Employee(
                    Integer.parseInt(record.getValue("id")),
                    record.getValue("name"),
                    record.getValue("surname"),
                    record.getValue("adress"),
                    record.getValue("phone"),
                    record.getValue("note"),
                    Double.parseDouble(record.getValue("man_hour"))
            );
        } else {
            return null;
        }
    }

    public static long saveToDb(Employee employee) {
        DbRecord record = new DbRecord();
        if (employee.getId() > 0) record.putValue("id", Long.toString(employee.getId()));

        record.putValue("name", employee.getName());
        record.putValue("surname", employee.getSurname());
        record.putValue("adress", employee.getAdress());
        record.putValue("phone", employee.getPhone());
        record.putValue("note", employee.getNote());
        record.putValue("man_hour", Double.toString(employee.getManHour()));

        return DbConnector.saveToDb(TABLE_NAME, record);
    }

    public static void delete(Employee employee) {
        if (employee.getId() > 0) {
            if (DbConnector.deleteById(TABLE_NAME, employee.getId())) employee.setId(0);
        }
    }

    public static void deleteById(int id) {
        if (id > 0) {
            if (DbConnector.deleteById(TABLE_NAME, id));
        }
    }
}
