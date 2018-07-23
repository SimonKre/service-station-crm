package pl.coderslab.carworkshop.dao;

import pl.coderslab.carworkshop.db.DbConnector;
import pl.coderslab.carworkshop.db.DbRecord;
import pl.coderslab.carworkshop.model.Status;

import java.util.ArrayList;

public class StatusDao {
    private static final String TABLE_NAME = "car_workshop.status";

    public static Status loadById(long id) {
        DbRecord record = DbConnector.getRecordById(TABLE_NAME, id);
        return getCarFromDbRecord(record);
    }

    public static ArrayList<Status> loadAll() {

        ArrayList<DbRecord> records = DbConnector.getAllRecords(TABLE_NAME);
        ArrayList<Status> statuses = new ArrayList<>();

        for (DbRecord record : records) {
            statuses.add(getCarFromDbRecord(record));
        }

        return statuses;
    }

    private static Status getCarFromDbRecord(DbRecord record) {
        if (record != null) {
            return new Status(
                    Integer.parseInt(record.getValue("id")),
                    record.getValue("status")
            );
        } else {
            return null;
        }
    }

    public static long saveToDb(Status status) {
        DbRecord record = new DbRecord();
        if (status.getId() > 0) record.putValue("id", Long.toString(status.getId()));

        record.putValue("status", status.getStatus());

        return DbConnector.saveToDb(TABLE_NAME, record);
    }

    public static void delete(Status status) {
        if (status.getId() > 0) {
            if (DbConnector.deleteById(TABLE_NAME, status.getId())) status.setId(0);
        }
    }
}
