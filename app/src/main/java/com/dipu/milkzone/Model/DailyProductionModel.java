package com.dipu.milkzone.Model;

public class DailyProductionModel {
    String pro_date, pro_cowtitle;
    int morning_Qty, evening_Qty;


    public DailyProductionModel(String pro_date, String pro_cowtitle, int morning_Qty, int evening_Qty) {
        this.pro_date = pro_date;
        this.pro_cowtitle = pro_cowtitle;
        this.morning_Qty = morning_Qty;
        this.evening_Qty = evening_Qty;
    }

    public String getPro_date() {
        return pro_date;
    }

    public void setPro_date(String pro_date) {
        this.pro_date = pro_date;
    }

    public String getPro_cowtitle() {
        return pro_cowtitle;
    }

    public void setPro_cowtitle(String pro_cowtitle) {
        this.pro_cowtitle = pro_cowtitle;
    }

    public int getMorning_Qty() {
        return morning_Qty;
    }

    public void setMorning_Qty(int morning_Qty) {
        this.morning_Qty = morning_Qty;
    }

    public int getEvening_Qty() {
        return evening_Qty;
    }

    public void setEvening_Qty(int evening_Qty) {
        this.evening_Qty = evening_Qty;
    }
}
