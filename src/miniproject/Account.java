/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Account implements Encryption,Serializable{
    private String name,password;
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;
    private ArrayList<Transaction> tr;
    
    Account(){
        dateCreated = new Date();
        tr = new ArrayList<>();
        balance = 500;
    }
    
    Account(String name,String password,int id){
        this();
        this.name = name;
        this.id = id;
        this.password = password;
    }
    
    
    
    public void addTransaction(Transaction tr){
        this.tr.add(tr);
    }
    
    public void showTransaction(){
        System.out.println("\nTransaction ID : " + id);
        for (Transaction transaction : tr) {
            System.out.println("Date : " + transaction.getDate());
            System.out.println("Type : " + transaction.getType());
            System.out.println("Amount : " + transaction.getAmount());
            System.out.println("Balance : " + transaction.getBalance());
            System.out.println("Description : " + transaction.getDescription() + "\n");
        }
    }
    
    public void setId(int newId){
        id = newId;
    }
    
    public int getId(){
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    public void setBalance(double newBalance){
        balance = newBalance;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void setAnnualInterestRate(double newAIR){
        if(newAIR>=0||newAIR<=100)
        annualInterestRate = newAIR/100;
    }
    
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    
    public Date getDateCreated(){
        return dateCreated;
    }
    
    public double getMonthlyInterestRate(){
        return annualInterestRate/12;
    }
    
    public double getMonthlyInterest(){
        return balance * getMonthlyInterestRate();
    }
    
    public void withdraw(double value){
        balance -= value;
        this.tr.add(new Transaction('W',value,balance));
    }
    
    public void deposit(double value){
        balance += value;
        this.tr.add(new Transaction('D',value,balance)); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String oldPassword,String password) {
        if(this.password == oldPassword) this.password = password;
        else System.out.println("Wrong old password!");
    }
    
    public String SMS(String sms){
        return new String(encrypt(sms.getBytes()));
    }
    
    public static void menu(){
        System.out.println("");
        System.out.println("Main menu");
        System.out.println("1: check balance");
        System.out.println("2: withdraw");
        System.out.println("3: deposit");
        System.out.println("4: transaction");
        System.out.println("5: exit");
    }
    
    private static String Encrypt(String text){
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = upperCase.toLowerCase();
        String numCase = "0123456789";
        String rot13 = "";
        int k,p;
        for (int i = 0; i < text.length(); i++) {
            String c = text.substring(i, i+1);
            if((k = upperCase.indexOf(c))>=0){
                p = (k+13)%upperCase.length();
                rot13 = rot13 + upperCase.substring(p, p+1);
            }
            else if((k = lowerCase.indexOf(c))>=0){
                p = (k+13)%upperCase.length();
                rot13 = rot13 +lowerCase.substring(p, p+1);
            }
            else if((k = numCase.indexOf(c))>=0){
                p = (k+5)%numCase.length();
                rot13 = rot13 + numCase.substring(p, p+1);
            }else{
                rot13 = rot13 + c;
            }
        }
        return rot13;
    }

    @Override
    public String toString() {
        return "Account{" + "name=" + name + "id=" + id + ", balance=" + balance+ "$" + ", annualInterestRate=" + annualInterestRate + ", dateCreated=" + dateCreated + '}';
    }

    @Override
    public byte[] encrypt(byte[] data) {
        byte[] enc = new byte[data.length];
        
        for (int i = 0; i < data.length; i++) {
            enc[i] = (byte)((i%2==0)?data[i]+1:data[i]-1);
        }
        
        return enc;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] dec = new byte[data.length];
        
        for (int i = 0; i < data.length; i++) {
            dec[i] = (byte)((i%2==0)?data[i]-1:data[i]+1);
        }
        
        return dec;
    }
    
}
