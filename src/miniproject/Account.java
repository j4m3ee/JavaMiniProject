package miniproject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Account implements Encryption, Serializable {

    private String Username, password = "0000";
    private String QTPassHint, ASWPasshint, Surname, realName;
    private int id;
    private double balance, maxTransaction = 20000.0;
    private double annualInterestRate;
    private Date dateCreated;
    private ArrayList<Transaction> tr;
    private ArrayList<Bank> bank;
    private char gender;
    private File PictureFile = null;

    Account() {
        dateCreated = new Date();
        tr = new ArrayList<>();
        bank = new ArrayList<>();
        balance = 500;
    }

    Account(String name, String password, int id) throws Exception {
        this();
        this.Username = name;
        this.id = id;
        try {
            setPassword("0000", password, password);
        } catch (Exception ex) {
            System.out.println(ex);
            throw ex;
        }
    }

    /**
     *
     * @param name
     * @param password
     * @param id
     * @param QTPassHint
     * @param ASWPasshint
     * @param Surname
     * @param realName
     * @throws Exception
     */
    Account(String name, String password, int id, String realName, String Surname,
            char gender, String QTPassHint, String ASWPasshint) throws Exception {
        this(name, password, id);
        this.QTPassHint = QTPassHint;
        this.ASWPasshint = ASWPasshint;
        setSurname(Surname);
        setRealName(realName);
        this.gender = gender;
    }

    public void addTransaction(Transaction tr) {
        this.tr.add(tr);
    }

    public void addBank(Bank bank) {
        this.bank.add(bank);
    }

    public void showTransaction() {
        System.out.println("\nTransaction ID <" + id + "> Name : " + Username);
        for (Transaction transaction : tr) {
            System.out.println("Date : " + transaction.getDate());
            System.out.println("Type : " + transaction.getType());
            System.out.println("Amount : " + transaction.getAmount());
            System.out.println("Balance : " + transaction.getBalance());
            System.out.println("Description : " + transaction.getDescription() + "\n");
        }
    }

    public File getPictureFile() {
        return PictureFile;
    }

    public void setPictureFile(File PictureFile) {
        this.PictureFile = PictureFile;
    }

    public void setId(int newId) {
        id = newId;
    }

    public char getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setAnnualInterestRate(double newAIR) {
        if (newAIR >= 0 || newAIR <= 100) {
            annualInterestRate = newAIR / 100;
        }
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
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

    public String getName() {
        return Username;
    }

    public void setName(String name) {
        this.Username = name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getRealName() {
        return realName;
    }

    public String getQTPassHint() {
        return QTPassHint;
    }

    public String getASWPasshint() {
        return ASWPasshint;
    }

    public void setPassword(String oldPassword, String npassword, String cfPassword)
            throws Exception {
        if (this.password.equals(oldPassword)) {
            if (npassword.length() >= 4 && npassword.length() <= 16) {
                if (npassword.equals(cfPassword)) {
                    if (oldPassword.equals(npassword) == false) {
                        this.password = npassword;
                    } else {
                        throw new Exception("Your old and new password are same.");
                    }
                } else {
                    throw new Exception("Wrong confirm password.");
                }
            } else {
                throw new Exception("Please input between 4-16 character.");
            }
        } else {
            throw new Exception("Wrong old password.");
        }
    }

    public void setPassword(String ans, String npassword, String cfPassword, int i)
            throws Exception {
        if (ans.equals(ASWPasshint)) {
            setPassword(password, npassword, cfPassword);
        } else {
            throw new Exception("Wrong Answer.");
        }
    }

    public double getMaxTransaction() {
        return maxTransaction;
    }

    public void setMaxTransaction(double maxTransaction) {
        this.maxTransaction = maxTransaction;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String SMS(String sms) {
        return new String(encrypt(sms.getBytes()));
    }

    public ArrayList<Transaction> getTr() {
        return tr;
    }

    public ArrayList<Bank> getBank() {
        return bank;
    }

    public void setSurname(String Surname) {
        Surname = Surname.toLowerCase();
        Surname = Surname.substring(0, 1).toUpperCase() + Surname.substring(1);
        this.Surname = Surname;
    }

    public void setRealName(String realName) {
        realName = realName.toLowerCase();
        realName = realName.substring(0, 1).toUpperCase() + realName.substring(1);
        this.realName = realName;
    }

    public void setProfile(String username, String name, String surname, char gender) {
        setName(username);
        setRealName(name);
        setSurname(surname);
        setGender(gender);
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public static void menu() {
        System.out.println("");
        System.out.println("Main menu");
        System.out.println("1: check balance");
        System.out.println("2: withdraw");
        System.out.println("3: deposit");
        System.out.println("4: transaction");
        System.out.println("5: exit");
    }

    private static String Encrypt(String text) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = upperCase.toLowerCase();
        String numCase = "0123456789";
        String rot13 = "";
        int k, p;
        for (int i = 0; i < text.length(); i++) {
            String c = text.substring(i, i + 1);
            if ((k = upperCase.indexOf(c)) >= 0) {
                p = (k + 13) % upperCase.length();
                rot13 = rot13 + upperCase.substring(p, p + 1);
            } else if ((k = lowerCase.indexOf(c)) >= 0) {
                p = (k + 13) % upperCase.length();
                rot13 = rot13 + lowerCase.substring(p, p + 1);
            } else if ((k = numCase.indexOf(c)) >= 0) {
                p = (k + 5) % numCase.length();
                rot13 = rot13 + numCase.substring(p, p + 1);
            } else {
                rot13 = rot13 + c;
            }
        }
        return rot13;
    }

    @Override
    public String toString() {
        return "Account{" + "Username=" + Username + ", password=" + password + ", QTPassHint=" + QTPassHint + ", ASWPasshint=" + ASWPasshint + ", Surname=" + Surname + ", realName=" + realName + ", id=" + id + ", balance=" + balance + ", dateCreated=" + dateCreated + '}';
    }

    @Override
    public byte[] encrypt(byte[] data) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
        }

        return enc;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] dec = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            dec[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
        }

        return dec;
    }

}
