package com.acceptance.test.testDataModel;

import java.util.List;

public class TestDataContext {


    public  static  String actualResult;
    public static  boolean isThisHomePage= true;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<User> getUses() {
        return uses;
    }

    public void setUses(List<User> uses) {
        this.uses = uses;
    }

    private List<User> uses;
}
