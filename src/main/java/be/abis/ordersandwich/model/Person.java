package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {

    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    // business



    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
