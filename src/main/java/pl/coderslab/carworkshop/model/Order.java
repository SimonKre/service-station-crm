package pl.coderslab.carworkshop.model;

import java.time.LocalDate;

public class Order {

    private int id;
    private LocalDate plannedStart;
    private LocalDate start;
    private Integer employeeId;
    private String problemDesc;
    private String repairDesc;
    private int statusId;
    private int vehicleId;
    private Double totalCost;
    private Double partsCost;
    private Float hoursSpent;

    public Order(int id, LocalDate plannedStart, LocalDate start, Integer employeeId, String problemDesc, String repairDesc, int statusId, int vehicleId, Double totalCost, Double partsCost, Float hoursSpent) {
        this.id = id;
        this.plannedStart = plannedStart;
        this.start = start;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.statusId = statusId;
        this.vehicleId = vehicleId;
        this.totalCost = totalCost;
        this.partsCost = partsCost;
        this.hoursSpent = hoursSpent;
    }

    public Order(LocalDate plannedStart, LocalDate start, Integer employeeId, String problemDesc, String repairDesc, int statusId, int vehicleId, Double totalCost, Double partsCost, Float hoursSpent) {
        this.id = 0;
        this.plannedStart = plannedStart;
        this.start = start;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.statusId = statusId;
        this.vehicleId = vehicleId;
        this.totalCost = totalCost;
        this.partsCost = partsCost;
        this.hoursSpent = hoursSpent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(LocalDate plannedStart) {
        this.plannedStart = plannedStart;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Double partsCost) {
        this.partsCost = partsCost;
    }

    public Float getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Float hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id;
    }

}
