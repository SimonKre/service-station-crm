package pl.coderslab.carworkshop.model;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private String adress;
    private String phone;
    private String note;
    private Double manHour;

    public Employee(int id, String name, String surname, String adress, String phone, String note, Double manHour) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.phone = phone;
        this.note = note;
        this.manHour = manHour;
    }

    public Employee(String name, String surname, String adress, String phone, String note, Double manHour) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.phone = phone;
        this.note = note;
        this.manHour = manHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getManHour() {
        return manHour;
    }

    public void setManHour(Double manHour) {
        this.manHour = manHour;
    }
}
