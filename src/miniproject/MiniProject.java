package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniProject extends Application {
    static File f = new File("Accout.dat");
    int AccId;
    Scene login, option, tranfer, register;
    ArrayList<Account> acDataList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception, FileNotFoundException, IOException, ClassNotFoundException {
        stage.setTitle("O-Bank");   
        
        try {
            acDataList = readFile(f);
        } catch (IOException | ClassNotFoundException iOException) {
            System.out.println(iOException);
        }
        
        //Layout
        VBox LIbox = new VBox(10);//Login
        VBox OTbox = new VBox(10);//User
        VBox RGbox = new VBox(10);//Register
        VBox TFbox = new VBox(10);//Tranfer
        
        //Layout Option
        Button ExitBtn = new Button("Exit");
        Button TranferBtn = new Button("Tranfer");
        TranferBtn.setOnAction((t) -> {
            stage.setScene(tranfer);
        });
        ExitBtn.setOnAction((t) -> {
            OTbox.getChildren().clear();
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        
        //Layout Tranfer
        Text amountText = new Text("Amount : ");
        TextField amountField = new TextField();
        Text accountText = new Text("Tranfer to (name) : ");
        TextField accountField = new TextField();
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction((t) -> {
            try {
                if (acDataList.get(AccId).getBalance() >= Integer.parseInt(amountField.getText())) {
                    for (Account account : acDataList) {
                        if(account.getName().equals(accountField.getText())){
                            acDataList.get(AccId).withdraw(Integer.parseInt(amountField.getText()));
                            account.deposit(Integer.parseInt(amountField.getText()));
                            acDataList = updateFile(f, acDataList);
                            break;
                        }
                    }
                }else System.out.println("Not money enough or Wrong account.");
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Plese input amount be number.");
                System.out.println(numberFormatException);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            OTbox.getChildren().clear();
            Text userText = new Text("Username : " + acDataList.get(AccId).getName());
            Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance());
            OTbox.getChildren().addAll(userText,balanceText,TranferBtn,ExitBtn);
            stage.setScene(option);
            System.out.println("Confirm press.");
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel press.");
        });
        TFbox.getChildren().addAll(accountText,accountField,amountText,amountField,confirmBtn,cancelBtn);
        
        //Layout Login
        Text idTopic = new Text("Username : ");
        TextField usernameField = new TextField();
        Text passTopic = new Text("Password : ");
        TextField passField = new TextField();
        Button LIBtn = new Button("Login");
        LIBtn.setOnAction((var t) -> {
            for (Account account1 : acDataList) {
                String thisUser = usernameField.getText(),thisPass = passField.getText();
                String chkUser = account1.getName(),chkPass = account1.getPassword();
                /*System.out.println("Username : " + thisUser);
                System.out.println(chkUser);
                System.out.println("Password : " + thisPass);
                System.out.println(chkPass);*/
                if(thisUser.equals(chkUser) && thisPass.equals(chkPass)){
                    AccId = account1.getId()-1;
                    System.out.println("Math! : " + account1.getId());
                    stage.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    Text balanceText = new Text("Balance : " + account1.getBalance());
                    
                    OTbox.getChildren().addAll(userText,balanceText,TranferBtn,ExitBtn);

                    break;
                }
            }
            System.out.println("Wrong id or password!");
            System.out.println("Login Press.\n");
        });
        Button RGBtn = new Button("Register");
        RGBtn.setOnAction((ActionEvent t) -> {
            stage.setScene(register);
            System.out.println("Register Press.");
        });
        Button CLBtn = new Button("Check list");
        CLBtn.setOnAction((ActionEvent t) -> {
            System.out.println(acDataList);
            System.out.println("Size : " + acDataList.size());
        });
        //vbox.setAlignment(Pos.CENTER);
        LIbox.getChildren().addAll(idTopic, usernameField, passTopic, 
                passField, LIBtn, RGBtn,CLBtn);

        //Layout Register
        
        TextField usernameField2 = new TextField();
        TextField passField2 = new TextField();
        Button SMBtn = new Button("Submit");
        
        SMBtn.setOnAction((ActionEvent t) -> {
            ArrayList<Account> addDataList = new ArrayList<>();
            try {
                addDataList = readFile(f);
                addDataList.add(new Account(usernameField2.getText(), passField2.getText(),addDataList.size()+1));
                writeFile(f, addDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            
            try {
                acDataList = readFile(f);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        RGbox.getChildren().addAll(new Text("Username : "), usernameField2,
                 new Text("Password : "), passField2,
                 SMBtn);

        login = new Scene(LIbox, 600, 400);
        register = new Scene(RGbox, 600, 400);
        option = new Scene(OTbox, 600, 400);
        tranfer = new Scene(TFbox, 600, 400);
        stage.setScene(login);
        stage.show();
    }
    
    public static ArrayList<Account> readFile(File f)throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        return (ArrayList<Account>) in.readObject();
    }
    
    public static void writeFile(File f,ArrayList<Account> acNew) throws FileNotFoundException, IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(acNew);
    }
    
    public static ArrayList<Account> updateFile(File f,ArrayList<Account> acNew)throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(acNew);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        return (ArrayList<Account>) in.readObject();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        /*File f = new File("Accout.dat");
        ArrayList<Account> ac = new ArrayList<>();
        Account a1 = new Account("Jame","Jame.011",1);
        ac.add(a1);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        
        out.writeObject(ac);
        
        System.out.println("Finish");*/
        
        launch(args);
        
        //fix from git
    }
}
