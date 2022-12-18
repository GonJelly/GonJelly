package com.ssafy.board.model;

public class BuildDeal {

    protected String dealNo;
    protected String dealAmount;
    protected int dealYear;
    protected int dealMonth;
    protected int dealDay;
    protected String area;
    protected String floor;
    protected String cancelDealType;

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public int getDealYear() {
        return dealYear;
    }

    public void setDealYear(int dealYear) {
        this.dealYear = dealYear;
    }

    public int getDealMonth() {
        return dealMonth;
    }

    public void setDealMonth(int dealMonth) {
        this.dealMonth = dealMonth;
    }

    public int getDealDay() {
        return dealDay;
    }

    public void setDealDay(int dealDay) {
        this.dealDay = dealDay;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCancelDealType() {
        return cancelDealType;
    }

    public void setCancelDealType(String cancelDealType) {
        this.cancelDealType = cancelDealType;
    }
}
