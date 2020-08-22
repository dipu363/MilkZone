package com.dipu.milkzone;

public class DiscountCoponModel {
    private String discountStart, discountUpTo;
    private Double discountPersenteg;
    private boolean coponstatus;

    public DiscountCoponModel(String discountStart, String discountUpTo, Double discountPersenteg, boolean coponstatus) {
        this.discountStart = discountStart;
        this.discountUpTo = discountUpTo;
        this.discountPersenteg = discountPersenteg;
        this.coponstatus = coponstatus;
    }

    public String getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(String discountStart) {
        this.discountStart = discountStart;
    }

    public String getDiscountUpTo() {
        return discountUpTo;
    }

    public void setDiscountUpTo(String discountUpTo) {
        this.discountUpTo = discountUpTo;
    }

    public Double getDiscountPersenteg() {
        return discountPersenteg;
    }

    public void setDiscountPersenteg(Double discountPersenteg) {
        this.discountPersenteg = discountPersenteg;
    }

    public boolean isCoponstatus() {
        return coponstatus;
    }

    public void setCoponstatus(boolean coponstatus) {
        this.coponstatus = coponstatus;
    }
}
