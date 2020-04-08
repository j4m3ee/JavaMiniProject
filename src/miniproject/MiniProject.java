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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniProject extends Application {

    static File f = new File("Accout.dat"); //Data
    static File fbu = new File("backup.dat"); //Backup file
    int AccId = -1;
    Scene login, option, tranfer, register, fixPassword;
    ArrayList<Account> acDataList = new ArrayList<>();

    String bgColor = "-fx-background-color: rgb(181,234,215);";
    //139,255,37 green
    //(#E2F0CB) 226,240,203 pastel yellow green
    //(#B5EAD7) 181,234,215 pastel green
    //(#FF9AA2) 255,154,162 pastel red

    @Override
    public void start(Stage stage) throws Exception, FileNotFoundException, IOException, ClassNotFoundException {
        stage.setTitle("O+ O PLUS");
        Image logo = new Image(new FileInputStream("Logo.png"));
        stage.getIcons().add(logo);
        /*
        //File menu
        Menu fileMenu = new Menu("Tools");
        //Menu items
        MenuItem adminMenu = new MenuItem("Admin");
        adminMenu.setOnAction((t) -> {
            System.out.println("Admin pless.");
        });
        fileMenu.getItems().add(adminMenu);
        //Main menu bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(fileMenu);
         */
        try {
            acDataList = readFile(f);
            updateFile(fbu, acDataList);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            acDataList = readFile(fbu);
        }

        //All Pane Layout
        VBox LIbox = new VBox(15);//Login
        VBox OTbox = new VBox(15);//User
        VBox RGbox = new VBox(15);//Register
        VBox TFbox = new VBox(15);//Tranfer
        VBox FPbox = new VBox(15);//fix Password

        LIbox.setStyle(bgColor);
        OTbox.setStyle(bgColor);
        RGbox.setStyle(bgColor);
        TFbox.setStyle(bgColor);
        FPbox.setStyle(bgColor);

        //Layout Scene fixPassword
        Text oldPassText = new Text("Enter old password.");
        TextField oldPassTextField = new PasswordField();
        Text newPassText = new Text("Enter new password.");
        TextField newPassTextField = new PasswordField();
        Text CFnewPassText = new Text("Re-new password.");
        TextField CFnewPassTextField = new PasswordField();
        Button SMFixPassBtn = new Button("Submit");
        SMFixPassBtn.setOnAction((var t) -> {
            try {
                acDataList.get(AccId).setPassword(oldPassTextField.getText(),
                        newPassTextField.getText(), CFnewPassTextField.getText());
                acDataList = updateFile(f, acDataList);
                stage.setScene(option);
                System.out.println("Submit Press.");
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }
        });
        Button CancelFixPassBtn = new Button("Cancel");
        CancelFixPassBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel Press.");
        });
        FPbox.getChildren().addAll(getLogoImage(logo), oldPassText, oldPassTextField, newPassText,
                newPassTextField, CFnewPassText, CFnewPassTextField, SMFixPassBtn,
                CancelFixPassBtn);

        //Layout Scene Option
        Button ExitBtn = new Button("Logout");
        Button TranferBtn = new Button("Transfer");
        Button TransactionBtn = new Button("Show Transaction");
        Button fixPassBtn = new Button("Change Password");
        TranferBtn.setOnAction((t) -> {
            stage.setScene(tranfer);
        });
        ExitBtn.setOnAction((t) -> {
            try {
                backupFile(fbu, acDataList);
                acDataList = updateFile(f, acDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            OTbox.getChildren().clear();
            AccId = -1;
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        TransactionBtn.setOnAction((t) -> {
            acDataList.get(AccId).showTransaction();
            informationBox.displayTransactionBox(acDataList.get(AccId), logo);
            System.out.println("TraTransaction Press.");
        });
        fixPassBtn.setOnAction((t) -> {
            stage.setScene(fixPassword);
            System.out.println("Fix Password Press.");
        });

        //Layout Scene Tranfer  
        Text amountText = new Text("Amount : ");
        TextField amountField = new TextField();
        Text accountText = new Text("Tranfer to (name) : ");
        TextField accountField = new TextField();
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction((t) -> {
            try {
                if (acDataList.get(AccId).getBalance() >= Integer.parseInt(amountField.getText())) {
                    for (Account account : acDataList) {
                        if (account.getName().equals(accountField.getText())) {
                            acDataList.get(AccId).withdraw(Integer.parseInt(amountField.getText()));
                            account.deposit(Integer.parseInt(amountField.getText()));
                            acDataList = updateFile(f, acDataList);
                            OTbox.getChildren().clear();
                            Text userText = new Text("Username : " + acDataList.get(AccId).getName());
                            Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance());
                            OTbox.getChildren().addAll(userText, balanceText, TranferBtn, TransactionBtn, fixPassBtn, ExitBtn);
                            stage.setScene(option);
                            break;
                        }
                    }
                } else {
                    throw new Exception("Not money enough or Wrong account.");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Plese input amount be number.");
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input amount be number.", logo);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }
            
            System.out.println("Confirm press.");
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel press.");
        });
        TFbox.getChildren().addAll(getLogoImage(logo), accountText, accountField, amountText, amountField, confirmBtn, cancelBtn);

        //Layout Scene Login
        Label labell3 = new Label("WELCOME TO O PLUS SERVICE\n             Please sign in.");
        labell3.setMinSize(50, 0);
        labell3.setScaleX(1.5);
        labell3.setScaleY(1.5);
        labell3.setStyle("-fx-font-size:16px;");
        labell3.setAlignment(Pos.TOP_CENTER);

        Label labell4 = new Label("Please logout after make transaction!");
        labell4.setScaleX(1);
        labell4.setScaleY(1);
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
                String thisUser = usernameField.getText(), thisPass = passField.getText();
                String chkUser = account1.getName(), chkPass = account1.getPassword();
                if (thisUser.equals(chkUser) && thisPass.equals(chkPass)) {
                    AccId = account1.getId() - 1;
                    System.out.println("Math! : " + account1.getId());
                    stage.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    Text balanceText = new Text("Balance : " + account1.getBalance());

                    OTbox.getChildren().addAll(userText, balanceText, TranferBtn,
                            TransactionBtn, fixPassBtn, ExitBtn);
                    break;
                }
            }
            if (AccId == -1) //If didn't have id in account list.
            {
                informationBox.displayAlertBox("O+ O PLUS", "Wrong unsername or password.", logo);
            }
            System.out.println("Login Press.\n");
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

        LIbox.getChildren().addAll(getLogoImage(logo), labell3, idTopic, usernameField, passTopic,
                passField, LIBtn, labell4, RGBtn);

        //Layout Scene Register 
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
                addDataList.add(new Account(usernameField2.getText(), passField2.getText(),
                        addDataList.get(addDataList.size()-1).getId() + 1));
                writeFile(f, addDataList);
                acDataList = readFile(f);
                stage.setScene(login);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }

            
            System.out.println("Submit Press.");
        });
        CancelBtn.setOnAction((t) -> {
            stage.setScene(login);
            System.out.println("Cancel Press.");
        });
        RGbox.setAlignment(Pos.CENTER);
        RGbox.getChildren().addAll(getLogoImage(logo), new Text("Username : "), usernameField2,
                new Text("Password : "), passField2,
                SMBtn, CancelBtn);

        /*LIbox.setMaxWidth(600);
        LIbox.setMaxHeight(400);
        BorderPane BdPane = new BorderPane();
        BdPane.setTop(menubar);
        BdPane.getChildren().add(LIbox);*/
        login = new Scene(LIbox, 600, 400);
        register = new Scene(RGbox, 600, 400);
        option = new Scene(OTbox, 600, 400);
        tranfer = new Scene(TFbox, 600, 400);
        fixPassword = new Scene(FPbox, 600, 400);
        stage.setScene(login);
        stage.show();
    }

    public static ArrayList<Account> readFile(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        return (ArrayList<Account>) in.readObject();
    }

    public static void writeFile(File f, ArrayList<Account> acNew) throws FileNotFoundException, IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))) {
            out.writeObject(acNew);
        }
    }

    public static ArrayList<Account> updateFile(File f, ArrayList<Account> acNew) throws FileNotFoundException, IOException, ClassNotFoundException {
        writeFile(f, acNew);
        return readFile(f);
    }

    public static void backupFile(File f, ArrayList<Account> acNew) throws FileNotFoundException, IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))) {
            out.writeObject(acNew);
        }
    }
    
    public static int findData(ArrayList<Account> ac){
        int id = -1;
        for (Account account : ac) {
            id = account.getId();
        }
        return id;
    }

    public static void showList(ArrayList<Account> ac) {
        for (Account acc : ac) {
            System.out.println("Id <" + acc.getId() + ">");
            System.out.println("Username : " + acc.getName());
            System.out.println("Password : " + acc.getPassword());
            System.out.println("Balance : " + acc.getBalance());
            System.out.println("Date created : " + acc.getDateCreated());
            System.out.println("");
        }
    }

    public static ImageView getLogoImage(Image logo) throws FileNotFoundException {
        ImageView LOGO = new ImageView(logo);
        LOGO.setFitHeight(60);
        LOGO.setFitWidth(60);
        LOGO.setPreserveRatio(true);
        return LOGO;
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
