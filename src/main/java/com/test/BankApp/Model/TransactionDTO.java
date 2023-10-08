package com.test.BankApp.Model;

public class TransactionDTO {

    private int pin;
    private double amount;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionDTO(int pin, double amount){
        this.amount = amount;
        this.pin = pin;

    }
}
