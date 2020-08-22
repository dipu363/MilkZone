package com.dipu.milkzone.Model;

public class CowDetailsModel {

    private String name, weight, remarks;
    private boolean cow_Status;
    private double avg_production;

    public CowDetailsModel() {


    }

    public CowDetailsModel(String name, String weight, String remarks, boolean cow_Status, double avg_production) {
        this.name = name;
        this.weight = weight;
        this.remarks = remarks;
        this.cow_Status = cow_Status;
        this.avg_production = avg_production;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isCow_Status() {
        return cow_Status;
    }

    public void setCow_Status(boolean cow_Status) {
        this.cow_Status = cow_Status;
    }

    public double getAvg_production() {
        return avg_production;
    }

    public void setAvg_production(double avg_production) {
        this.avg_production = avg_production;
    }
}
