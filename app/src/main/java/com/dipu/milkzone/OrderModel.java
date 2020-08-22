package com.dipu.milkzone;

public class OrderModel {
    private String order_id;
    private String userid;
    private String name;
    private String address;
    private String mobile;
    private String orderdate;
    private String ordertype;
    private String scheduleDay;
    private int totaldays;
    private Double dailyqnty;
    private Double rate;
    private Double discountrate;
    private Double totalquntity;
    private Double totalprice;
    private Double totaldiscount;

    private Double afterdistotalprice;
    private String orderStatus;

    public OrderModel() {
    }

    public OrderModel(String order_id, String userid, String name, String address, String mobile, String orderdate, String ordertype, String scheduleDay, int totaldays, Double dailyqnty, Double rate, Double discountrate, Double totalquntity, Double totalprice, Double totaldiscount, Double afterdistotalprice, String orderStatus) {
        this.order_id = order_id;
        this.userid = userid;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.orderdate = orderdate;
        this.ordertype = ordertype;
        this.scheduleDay = scheduleDay;
        this.totaldays = totaldays;
        this.dailyqnty = dailyqnty;
        this.rate = rate;
        this.discountrate = discountrate;
        this.totalquntity = totalquntity;
        this.totalprice = totalprice;
        this.totaldiscount = totaldiscount;
        this.afterdistotalprice = afterdistotalprice;
        this.orderStatus = orderStatus;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public int getTotaldays() {
        return totaldays;
    }

    public void setTotaldays(int totaldays) {
        this.totaldays = totaldays;
    }

    public Double getDailyqnty() {
        return dailyqnty;
    }

    public void setDailyqnty(Double dailyqnty) {
        this.dailyqnty = dailyqnty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(Double discountrate) {
        this.discountrate = discountrate;
    }

    public Double getTotalquntity() {
        return totalquntity;
    }

    public void setTotalquntity(Double totalquntity) {
        this.totalquntity = totalquntity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getTotaldiscount() {
        return totaldiscount;
    }

    public void setTotaldiscount(Double totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public Double getAfterdistotalprice() {
        return afterdistotalprice;
    }

    public void setAfterdistotalprice(Double afterdistotalprice) {
        this.afterdistotalprice = afterdistotalprice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
