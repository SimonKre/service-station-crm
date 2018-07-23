package pl.coderslab.carworkshop.model;

import java.time.LocalDate;

public class Vehicle {

    private int id;
    private String model;
    private String maker;
    private int productionYear;
    private String registrationNumber;
    private LocalDate nextOverhaul;
    private int customerId;

    public Vehicle(int id, String model, String maker, int productionYear, String registrationNumber, LocalDate nextOverhaul, int customerId) {
        this.id = id;
        this.model = model;
        this.maker = maker;
        this.productionYear = productionYear;
        this.registrationNumber = registrationNumber;
        this.nextOverhaul = nextOverhaul;
        this.customerId = customerId;
    }

    public Vehicle(String model, String maker, int productionYear, String registrationNumber, LocalDate nextOverhaul, int customerId) {
        this.id = 0;
        this.model = model;
        this.maker = maker;
        this.productionYear = productionYear;
        this.registrationNumber = registrationNumber;
        this.nextOverhaul = nextOverhaul;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getNextOverhaul() {
        return nextOverhaul;
    }

    public void setNextOverhaul(LocalDate nextOverhaul) {
        this.nextOverhaul = nextOverhaul;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
