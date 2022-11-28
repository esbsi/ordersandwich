package be.abis.ordersandwich.dto;

import be.abis.ordersandwich.model.Shop;

import java.time.Month;

public class FinanceModel {

    private Shop shop;
    private Month month;
    private int year;

    public FinanceModel() {
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
