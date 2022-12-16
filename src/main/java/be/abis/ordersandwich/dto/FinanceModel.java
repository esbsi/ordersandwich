package be.abis.ordersandwich.dto;

import be.abis.ordersandwich.model.Shop;

import java.time.Month;

public class FinanceModel {

    private int shop;
    private int month;
    private int year;

    public FinanceModel() {
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
