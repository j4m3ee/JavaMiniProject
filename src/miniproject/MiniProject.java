package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniProject extends Application {
    static File f = new File("Accout.dat");
    int AccId;
    Scene login, option, tranfer, register,fixPassword;
    ArrayList<Account> acDataList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception, FileNotFoundException, IOException, ClassNotFoundException {
        stage.setTitle("O+ O PLUS");   
        
        try {
            acDataList = readFile(f);
        } catch (IOException | ClassNotFoundException iOException) {
            System.out.println(iOException);
        }
        
        //All Pane Layout
        VBox LIbox = new VBox(15);//Login
        VBox OTbox = new VBox(15);//User
        VBox RGbox = new VBox(15);//Register
        VBox TFbox = new VBox(15);//Tranfer
        VBox FPbox = new VBox(15);//fix Password
        LIbox.setStyle("-fx-background-color: rgb(139,255,37);");
        OTbox.setStyle("-fx-background-color: rgb(139,255,37);");
        RGbox.setStyle("-fx-background-color: rgb(139,255,37);");
        TFbox.setStyle("-fx-background-color: rgb(139,255,37);");
        FPbox.setStyle("-fx-background-color: rgb(139,255,37);");
        
        //Layout Scene fixPassword
        Image logo5 = new Image(new FileInputStream("Logo.png"));
        ImageView LOGO = new ImageView(logo5);
        LOGO.setFitHeight(60);
        LOGO.setFitWidth(60);
        LOGO.setPreserveRatio(true); 
        
        Text oldPassText = new Text("Enter old password.");
        TextField oldPassTextField = new PasswordField();
        Text newPassText = new Text("Enter new password.");
        TextField newPassTextField = new PasswordField();    
        Text CFnewPassText = new Text("Re-new password.");
        TextField CFnewPassTextField = new PasswordField();  
        Button SMFixPassBtn = new Button("Submit");
        SMFixPassBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Submit Press.");
        });
        Button CancelFixPassBtn = new Button("Cancel");
        CancelFixPassBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel Press.");
        });
        FPbox.getChildren().addAll(LOGO,oldPassText,oldPassTextField,newPassText,
            newPassTextField,CFnewPassText,CFnewPassTextField,SMFixPassBtn,
            CancelFixPassBtn);
        
        //Layout Scene Option
        Image logo2 = new Image(new FileInputStream("Logo.png"));
        ImageView LOGO2 = new ImageView(logo2);
        LOGO2.setFitHeight(60);
        LOGO2.setFitWidth(60);
        LOGO2.setPreserveRatio(true); 
        Button ExitBtn = new Button("Logout");
        Button TranferBtn = new Button("Transfer");
        Button TransactionBtn = new Button("Show Transaction");
        Button fixPassBtn = new Button("Change Password");
        TranferBtn.setOnAction((t) -> {
            stage.setScene(tranfer);
        });
        ExitBtn.setOnAction((t) -> {
            OTbox.getChildren().clear();
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        TransactionBtn.setOnAction((t) -> {
            acDataList.get(AccId).showTransaction();
            System.out.println("TraTransaction Press.");
        });
        fixPassBtn.setOnAction((t) -> {
            stage.setScene(fixPassword);
            System.out.println("Fix Password Press.");
        });
        
        //Layout Scene Tranfer  
        Image logo3 = new Image(new FileInputStream("Logo.png"));
        ImageView LOGO3 = new ImageView(logo3);
        LOGO3.setFitHeight(60);
        LOGO3.setFitWidth(60);
        LOGO3.setPreserveRatio(true); 
        
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
        TFbox.getChildren().addAll(LOGO3,accountText,accountField,amountText,amountField,confirmBtn,cancelBtn);
        
        //Layout Scene Login
        Label labell3 = new Label("WELCOME TO O PLUS SERVICE\n             Please sign in.");
        labell3.setMinSize(50, 0);
        labell3.setScaleX(1.5);  labell3.setScaleY(1.5);
        labell3.setStyle("-fx-font-size:16px;");
        labell3.setAlignment(Pos.TOP_CENTER); 
        
        Label labell4 = new Label("Don't have an account?");
        labell4.setScaleX(1);  labell4.setScaleY(1);
        labell4.setTextFill(Color.RED);
        labell4.setAlignment(Pos.TOP_CENTER); 
        
        Text idTopic = new Text("Username : ");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        Text passTopic = new Text("Password : ");
        TextField passField = new PasswordField();
        passField.setMaxWidth(300);
        Button LIBtn = new Button("Login");
        LIBtn.setOnAction((var t) -> {
            for (Account account1 : acDataList) {
                String thisUser = usernameField.getText(),thisPass = passField.getText();
                String chkUser = account1.getName(),chkPass = account1.getPassword();
                if(thisUser.equals(chkUser) && thisPass.equals(chkPass)){
                    AccId = account1.getId()-1;
                    System.out.println("Math! : " + account1.getId());
                    stage.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    Text balanceText = new Text("Balance : " + account1.getBalance());
                    
                    OTbox.getChildren().addAll(LOGO,userText,balanceText,TranferBtn,
                            TransactionBtn,fixPassBtn,ExitBtn);

                    break;
                }
            }
//            AlertBox.display("O+ O PLUS","Wrong unsername or password.\n             Please try again.");
//            System.out.println("Wrong id or password!");
//            System.out.println("Login Press.\n");
        });
        Button RGBtn = new Button("Register");
        RGBtn.setOnAction((ActionEvent t) -> {
            stage.setScene(register);
            System.out.println("Register Press.");
        });
        Button ChkListBtn = new Button("Check list");
        ChkListBtn.setOnAction((ActionEvent t) -> {
            showList(acDataList);
            System.out.println("Size : " + acDataList.size());
        });
        LIbox.setAlignment(Pos.CENTER);
        LIbox.getChildren().addAll(LOGO2,labell3,idTopic, usernameField, passTopic, 
                passField, LIBtn, labell4, RGBtn);

        //Layout Scene Register 
        Image logo4 = new Image(new FileInputStream("Logo.png"));
        ImageView LOGO4 = new ImageView(logo4);
        LOGO4.setFitHeight(60);
        LOGO4.setFitWidth(60);
        LOGO4.setPreserveRatio(true); 
        TextField usernameField2 = new TextField();
        usernameField2.setMaxWidth(300);
        PasswordField passField2 = new PasswordField();
        passField2.setMaxWidth(300);
        Button SMBtn = new Button("Submit");
        Button CancelBtn = new Button("Cancel");
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
        CancelBtn.setOnAction((t) -> {
            stage.setScene(login);
            System.out.println("Cancel Press.");
        });
        RGbox.setAlignment(Pos.CENTER);
        RGbox.getChildren().addAll(LOGO4,new Text("Username : "), usernameField2,
                 new Text("Password : "), passField2,
                 SMBtn,CancelBtn);

        login = new Scene(LIbox, 600, 400);
        register = new Scene(RGbox, 600, 400);
        option = new Scene(OTbox, 600, 400);
        tranfer = new Scene(TFbox, 600, 400);
        fixPassword = new Scene(FPbox,600,400);
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
    
    public static void showList(ArrayList<Account> ac){
        for (Account acc : ac) {
            System.out.println("Id <" + acc.getId() + ">");
            System.out.println("Username : " + acc.getName());
            System.out.println("Password : " + acc.getPassword());
            System.out.println("Balance : " + acc.getBalance());
            System.out.println("Date created : " + acc.getDateCreated());
            System.out.println("");
        }
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
    }
}
