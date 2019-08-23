/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author rh
 */
public class CustomerDTO {
    
private long customerID;
private String fullName; 
private String accountNumber;
private double balance;

    public CustomerDTO(BankCustomer b) {
        this.customerID = b.getId();
        this.fullName = b.getFirstName() +" "+ b.getLastName();
        this.accountNumber = b.getAccountNumber();
        this.balance = b.getBalance();
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    
}
