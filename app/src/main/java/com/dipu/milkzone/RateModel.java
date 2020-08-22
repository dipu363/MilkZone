package com.dipu.milkzone;

public class RateModel {
    private String rate_id, rate, curent_date;

    public RateModel() {
    }

    public RateModel(String rate_id, String rate, String curent_date) {
        this.rate_id = rate_id;
        this.rate = rate;
        this.curent_date = curent_date;
    }

    public String getRate_id() {
        return rate_id;
    }

    public void setRate_id(String rate_id) {
        this.rate_id = rate_id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCurent_date() {
        return curent_date;
    }

    public void setCurent_date(String curent_date) {
        this.curent_date = curent_date;
    }
}
