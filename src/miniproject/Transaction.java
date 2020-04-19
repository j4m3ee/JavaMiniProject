package miniproject;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable{
    private Date date;
    private char type;
    private double amount,balance;
    private String description;

    public Transaction() {
        date = new Date();
    }

    public Transaction(char type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        date = new Date();
    }
    
    public Transaction(char type, double amount, double balance, String description) {
        this(type,amount,balance);
        this.description = description;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        if(type == 'W') return "withdraw";
        else if(type == 'D') return "deposit";
        else return "null";
        
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" + "date=" + date + ", type=" + type + ", amount=" + amount + ", balance=" + balance + ", description=" + description + '}';
    }
    
    
}
