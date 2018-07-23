package pl.coderslab.carworkshop.db;

import java.util.HashMap;

public class DbRecord {

    private HashMap<String, String> record;

    public DbRecord(String label, String value) {
        record = new HashMap<String, String>();
        record.put(label, value);
    }

    public DbRecord() {
        record = new HashMap<String, String>();
    }

    public void putValue(String label, String value) {
        record.put(label, value);
    }

    public String getValue(String label) {

        if (record.containsKey(label)) {
            return record.get(label);
        } else {
            return null;
        }
    }

    public int getColumnCount() {
        return record.size();
    }

    public HashMap<String, String> getAllColumns() {
        return this.record;
    }

}
