package com.test.BankApp.Model;

public class BankAccountOpenInfoDTO {
    private String name;

    private double amount;

    public BankAccountOpenInfoDTO(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public BankAccountOpenInfoDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
