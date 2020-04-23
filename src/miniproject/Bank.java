/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Bank implements Serializable {

    private String BankId = "0000000000", nameBank = "", nameIdBank = "";
    public static String[] nameBankList = new String[]{"KBANK", "TMB", "SCB",
        "KTB", "UOB", "GSB"};
    private ArrayList<Transaction> tr;
    private double balance, maxTransaction = 20000.0;
    private Date dateCreated;
    private int id;

    Bank() {
        dateCreated = new Date();
        tr = new ArrayList<>();
        balance = 500;
    }

    Bank(String nameBank, String BankId, String nameIdBank, int id) throws Exception {
        this();
        setBankId(BankId);
        this.nameBank = nameBank;
        this.nameIdBank = nameIdBank;
        this.id = id;
    }

    public String getNameIdBank() {
        return nameIdBank;
    }

    public void setNameIdBank(String nameIdBank) {
        this.nameIdBank = nameIdBank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankId() {
        return BankId;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public ArrayList<Transaction> getTr() {
        return tr;
    }

    public void setBankId(String BankId) throws Exception {
        for (int i = 0; i < BankId.length(); i++) {
            if (BankId.charAt(i) < '0' || BankId.charAt(i) > '9') {
                throw new Exception("Please input number.");
            }
        }

        if (BankId.length() == 10) {
            this.BankId = BankId;
        } else {
            throw new Exception("Input 10 digit of Bank id");
        }

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction tr) {
        this.tr.add(tr);
    }

    public void withdraw(double value) {
        balance -= value;
        this.tr.add(new Transaction('W', value, balance));
    }

    public void deposit(double value) {
        balance += value;
        this.tr.add(new Transaction('D', value, balance));
    }

    public void makeTransaction(char type, double value) throws Exception {
        type = Character.toLowerCase(type);
        switch (type) {
            case 'd':
                deposit(value);
                break;
            case 'w':
                withdraw(value);
                break;
            default:
                throw new Exception("input d : deposit\ninput w : withdraw");
        }
    }

    public double getMaxTransaction() {
        return maxTransaction;
    }

    public void setMaxTransaction(double maxTransaction) {
        this.maxTransaction = maxTransaction;
    }
    
    

    @Override
    public String toString() {
        return id + ". " + getBankId() + " " + getNameBank() + " (" + getNameIdBank() + ")";
    }

    

}
