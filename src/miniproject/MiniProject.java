package miniproject;

import GUI.getElement;
import GUI.informationBox;
import GUI.setStyleElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniProject extends Application {

    public String pathPic = "resource\\Pictures\\";
    int AccId = -1, tfToAcc = -1;
    double amount = 0.0, stageWidth = 600, stageHeight = 650;
    Scene login, option, tranfer, register, fixPassword, forgotPassword,
            editProfile, makeTransaction, CFTransactionScene;
    ArrayList<Account> acDataList = new ArrayList<>();
    char Type = 'n';

    int r1 = 0, r2 = 0;
    

    //139,255,37 green
    //(#E2F0CB) 226,240,203 pastel yellow green
    //(#B5EAD7) 181,234,215 pastel green
    //(#FF9AA2) 255,154,162 pastel red
    @Override
    public void start(Stage stage)
            throws Exception, FileNotFoundException, IOException, ClassNotFoundException {
        stage.setTitle("O+ O PLUS");
        Image logo = new Image(new FileInputStream(pathPic + "Logo.png"));
        Image userimage = new Image(new FileInputStream(pathPic + "User1.png"));
        File imageFile1 = new File(pathPic + "Bucks.png");
        Image Buck = new Image(imageFile1.toURI().toString());
        File depositFile = new File(pathPic + "depo.png");
        Image Depo = new Image(depositFile.toURI().toString());
        File withdrawFile = new File(pathPic + "with.png");
        Image With = new Image(withdrawFile.toURI().toString());
        File transactionFile = new File(pathPic + "tran.png");
        Image Tran = new Image(transactionFile.toURI().toString());
        File historyFile = new File(pathPic + "hist.png");
        Image Hist = new Image(depositFile.toURI().toString());
        stage.getIcons().add(logo);
        Label tsLabel = new Label();
        tsLabel.setTextFill(Color.WHITE);
        tsLabel.setStyle("-fx-font-size:20px;");
//        tsLabel.setTranslateY(5);

        //Background
        FileInputStream input = new FileInputStream(pathPic + "moon.jpg");
        Image bg = new Image(input);
        BackgroundImage bgimage = new BackgroundImage(bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(bgimage);

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

        try {
            acDataList = Data.readFile(Data.f);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            acDataList = Data.readBackupData();
            acDataList = Data.updateFile(Data.f, acDataList);
        }

        //All Pane Layout
        VBox LIbox = new VBox(15);//Login
        VBox RGbox = new VBox(15);//Register
        VBox TFbox = new VBox(15);//Tranfer
        VBox FPbox = new VBox(15);//fix Passwordas
        VBox FGPbox = new VBox(15);//forgot password
        VBox TSbox = new VBox(15);//Make transaction (deposit/withdraw) 
        VBox CFTransactionbox = new VBox(15);//Before tranfer confirm transection
        VBox editProfilebox = new VBox(15);//Edit profile

        LIbox.setBackground(background);
        RGbox.setBackground(background);
        TFbox.setBackground(background);
        FPbox.setBackground(background);
        FGPbox.setBackground(background);
        TSbox.setBackground(background);
        CFTransactionbox.setBackground(background);
        editProfilebox.setBackground(background);

        BorderPane INFO = new BorderPane(); //User
        INFO.setBackground(background);

        //Layout Scene edit profile
        Text editUsernameR = new Text("Username : ");
        editUsernameR.setStyle(getElement.blueTxColor2);
        Text editUserDeal = new Text("(user must not be the same)");
        editUserDeal.setFill(Color.RED);
        HBox userR2 = new HBox(3);
        userR2.getChildren().addAll(editUsernameR, editUserDeal);
        userR2.setAlignment(Pos.CENTER);

        TextField usernameField3 = new TextField();
        usernameField3.setMaxWidth(300);
        usernameField3.setStyle(getElement.BorderText);
        PasswordField passField3 = new PasswordField();
        passField3.setMaxWidth(300);
        passField3.setStyle(getElement.BorderText);
        TextField realnameTextField2 = new TextField();
        realnameTextField2.setMaxWidth(300);
        realnameTextField2.setStyle(getElement.BorderText);
        TextField surnameField2 = new TextField();
        surnameField2.setMaxWidth(300);
        surnameField2.setStyle(getElement.BorderText);

        CheckBox mGender2 = new CheckBox("Male");
        mGender2.setStyle(getElement.blueTxColor);
        CheckBox fmGender2 = new CheckBox("Female");
        fmGender2.setStyle(getElement.blueTxColor);
        HBox gender2 = new HBox(20);
        gender2.getChildren().addAll(mGender2, fmGender2);
        gender2.setAlignment(Pos.CENTER);

        mGender2.setOnAction((t) -> {
            if (mGender2.isSelected()) {
                fmGender2.setSelected(false);
            }
        });

        fmGender2.setOnAction((t) -> {
            if (fmGender2.isSelected()) {
                mGender2.setSelected(false);
            }
        });
        Button fixPassBtn = new Button("Change Password");
        fixPassBtn.setStyle(getElement.getBlueStyleBtn());
        fixPassBtn.setOnMouseEntered((t) -> {
            fixPassBtn.setStyle(getElement.getStyleBtnHover());
        });
        fixPassBtn.setOnMouseExited((t) -> {
            fixPassBtn.setStyle(getElement.getBlueStyleBtn());
        });

        Button SMBtn2 = new Button("Submit");
        SMBtn2.setStyle(getElement.getGrnStyleBtn());
        SMBtn2.setOnMouseEntered((t) -> {
            SMBtn2.setStyle(getElement.getStyleBtnHover());
        });
        SMBtn2.setOnMouseExited((t) -> {
            SMBtn2.setStyle(getElement.getGrnStyleBtn());
        });
        Button CancelBtn2 = new Button("Cancel");
        CancelBtn2.setStyle(getElement.getRedStyleBtn());
        CancelBtn2.setOnMouseEntered((t) -> {
            CancelBtn2.setStyle(getElement.getStyleBtnHover());
        });
        CancelBtn2.setOnMouseExited((t) -> {
            CancelBtn2.setStyle(getElement.getRedStyleBtn());
        });
        Button deleteAcBtn = new Button("Delete");
        deleteAcBtn.setStyle(getElement.getRedStyleBtn());
        deleteAcBtn.setOnMouseEntered((t) -> {
            deleteAcBtn.setStyle(getElement.getStyleBtnHover());
        });
        deleteAcBtn.setOnMouseExited((t) -> {
            deleteAcBtn.setStyle(getElement.getRedStyleBtn());
        });
        SMBtn2.setOnAction((ActionEvent t) -> {
            char Gender = 'n';
            try {
                if (mGender2.isSelected() == false && fmGender2.isSelected() == false) {
                    throw new Exception("Please select gender.");
                }
                if (mGender2.isSelected() == true) {
                    Gender = 'm';
                } else {
                    Gender = 'f';
                }
                acDataList = Data.readFile(Data.f);
                for (Account account : acDataList) {
                    if (account.getName().equals(usernameField3.getText())) {
                        if (!acDataList.get(AccId).getName().equals(usernameField3.getText())) {
                            throw new Exception("This username is already.");
                        }
                    }
                }
                acDataList.get(AccId).setProfile(usernameField3.getText(),
                        realnameTextField2.getText(),
                        surnameField2.getText(), Gender);

                acDataList = Data.updateFile(Data.f, acDataList);

                Text userText = new Text("Username : " + acDataList.get(AccId).getName());
                userText.setStyle(getElement.nameTxColor1);
                Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance() + "  " + "Baht");
                balanceText.setStyle(getElement.nameTxColor1);
                Text Fullname = new Text("Name : " + acDataList.get(AccId).getRealName() + "  "
                        + acDataList.get(AccId).getSurname() + "  " + "Gender : " + "  "
                        + acDataList.get(AccId).getGender());
                Fullname.setStyle(getElement.nameTxColor1);

                //INFO-TOP
                HBox nameBalance = new HBox(20);
                nameBalance.getChildren().addAll(userText, balanceText);
                VBox userInfo = new VBox(12);
                userInfo.getChildren().addAll(nameBalance, Fullname);
                HBox doubleLogo = new HBox(15);
                doubleLogo.getChildren().addAll(getImageView(logo), getImageView(userimage));
                HBox TOP = new HBox(40);
                TOP.getChildren().addAll(doubleLogo, userInfo);
                TOP.setTranslateX(45);
                TOP.setTranslateY(10);

                INFO.setTop(TOP);

                stage.setScene(option);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Submit Press.");
        });
        fixPassBtn.setOnAction((t) -> {
            stage.setScene(fixPassword);
            System.out.println("Fix Password Press.");
        });
        CancelBtn2.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel Press.");
        });
        
        deleteAcBtn.setOnAction((t) -> {
            try {
                if (acDataList.get(AccId).getBalance() > 0.0) {
                    throw new Exception("Please clear your balance");
                }
                if (informationBox.confirmAlert("Confirm", "Are you sure?",logo,background)) {
                    acDataList.remove(AccId);
                    for (int i = 0; i < acDataList.size(); i++) {
                        acDataList.get(i).setId(i + 1);
                    }
                    acDataList = Data.updateFile(Data.f, acDataList);
                    AccId = -1;
                    stage.setScene(login);
                }

            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Del Prease.");
        });
        Text nameTag = new Text("Name : ");
        nameTag.setStyle(getElement.blueTxColor2);
        Text surnameTag = new Text("Surname : ");
        surnameTag.setStyle(getElement.blueTxColor2);
        editProfilebox.setAlignment(Pos.CENTER);
        HBox RegisChoice2 = new HBox(15);
        RegisChoice2.getChildren().addAll(SMBtn2, fixPassBtn, deleteAcBtn, CancelBtn2);
        RegisChoice2.setAlignment(Pos.CENTER);
        editProfilebox.getChildren().addAll(RegisChoice2);
        //Layout Scene edit profile

        //Layout Scene fixPassword
        Text oldPassText = new Text("Enter old password.");
        oldPassText.setStyle(getElement.blueTxColor2);
        TextField oldPassTextField = new PasswordField();
        oldPassTextField.setMaxWidth(300);
        oldPassTextField.setStyle(getElement.BorderText);
        Text newPassText = new Text("Enter new password.");
        newPassText.setStyle(getElement.blueTxColor2);
        TextField newPassTextField = new PasswordField();
        newPassTextField.setMaxWidth(300);
        newPassTextField.setStyle(getElement.BorderText);
        Text CFnewPassText = new Text("Re-new password.");
        CFnewPassText.setStyle(getElement.blueTxColor2);
        TextField CFnewPassTextField = new PasswordField();
        CFnewPassTextField.setMaxWidth(300);
        CFnewPassTextField.setStyle(getElement.BorderText);
        Button SMFixPassBtn = new Button("Submit");
        SMFixPassBtn.setStyle(getElement.grnBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        SMFixPassBtn.setOnMouseEntered((t) -> {
            SMFixPassBtn.setStyle(getElement.HoverY + getElement.bgRad + 
                    getElement.bgIns + getElement.whtTextFill);
        });
        SMFixPassBtn.setOnMouseExited((t) -> {
            SMFixPassBtn.setStyle(getElement.grnBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        });
        SMFixPassBtn.setOnAction((var t) -> {
            try {
                acDataList.get(AccId).setPassword(oldPassTextField.getText(),
                        newPassTextField.getText(), CFnewPassTextField.getText());
                acDataList = Data.updateFile(Data.f, acDataList);
                stage.setScene(option);
                System.out.println("Submit Press.");
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
        });
        Button CancelFixPassBtn = new Button("Cancel");
        CancelFixPassBtn.setStyle(getElement.redBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        CancelFixPassBtn.setOnMouseEntered((t) -> {
            CancelFixPassBtn.setStyle(getElement.HoverY + getElement.bgRad + 
                    getElement.bgIns + getElement.whtTextFill);
        });
        CancelFixPassBtn.setOnMouseExited((t) -> {
            CancelFixPassBtn.setStyle(getElement.redBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        });
        CancelFixPassBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel Press.");
        });
        HBox disiFixPassBtn = new HBox(15);
        disiFixPassBtn.getChildren().addAll(SMFixPassBtn,CancelFixPassBtn);
        disiFixPassBtn.setAlignment(Pos.CENTER);
        FPbox.getChildren().addAll(getImageView(logo), oldPassText, oldPassTextField, newPassText,
                newPassTextField, CFnewPassText, CFnewPassTextField, disiFixPassBtn);
        FPbox.setAlignment(Pos.CENTER);
        //Layout Scene fixPassword

        //Layout Scene Option
        Button ConditionsBtn = new Button("Account Conditions");
        ConditionsBtn.setStyle(getElement.purpBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        ConditionsBtn.setOnMouseEntered((t) -> {
            ConditionsBtn.setStyle(getElement.HoverY + getElement.bgRad + 
                    getElement.bgIns + getElement.whtTextFill);
        });
        ConditionsBtn.setOnMouseExited((t) -> {
            ConditionsBtn.setStyle(getElement.purpBgColor + getElement.bgRad + 
                getElement.bgIns + getElement.whtTextFill);
        });
        Button TranferBtn = new Button("Transfer", new ImageView(Tran));
        TranferBtn.setPrefWidth(200);
        TranferBtn.setPrefHeight(80);
        TranferBtn.setStyle(getElement.yelBigBtn);
        TranferBtn.setOnMouseEntered((t) -> {
            TranferBtn.setStyle(getElement.yelBigBtnHover);
        });
        TranferBtn.setOnMouseExited((t) -> {
            TranferBtn.setStyle(getElement.yelBigBtn);
        });
        Button DepositBtn = new Button("Deposit", new ImageView(Depo));
        DepositBtn.setPrefWidth(200);
        DepositBtn.setPrefHeight(80);
        DepositBtn.setStyle(getElement.yelBigBtn);
        DepositBtn.setOnMouseEntered((t) -> {
            DepositBtn.setStyle(getElement.yelBigBtnHover);
        });
        DepositBtn.setOnMouseExited((t) -> {
            DepositBtn.setStyle(getElement.yelBigBtn);
        });
        Button WidthdrawBtn = new Button("Widthdraw", new ImageView(With));
        WidthdrawBtn.setPrefWidth(200);
        WidthdrawBtn.setPrefHeight(80);
        WidthdrawBtn.setStyle(getElement.yelBigBtn);
        WidthdrawBtn.setOnMouseEntered((t) -> {
            WidthdrawBtn.setStyle(getElement.yelBigBtnHover);
        });
        WidthdrawBtn.setOnMouseExited((t) -> {
            WidthdrawBtn.setStyle(getElement.yelBigBtn);
        });
        Button TransactionBtn = new Button("Show Transaction", new ImageView(Hist));
        TransactionBtn.setPrefWidth(200);
        TransactionBtn.setPrefHeight(80);
        TransactionBtn.setStyle(getElement.yelBigBtn);
        TransactionBtn.setOnMouseEntered((t) -> {
            TransactionBtn.setStyle(getElement.yelBigBtnHover);
        });
        TransactionBtn.setOnMouseExited((t) -> {
            TransactionBtn.setStyle(getElement.yelBigBtn);
        });
        Button editProfileBtn = new Button("Edit Profile");
        editProfileBtn.setStyle(getElement.getBlueStyleBtn());
        editProfileBtn.setOnMouseEntered((t) -> {
            editProfileBtn.setStyle(getElement.getStyleBtnHover());
        });
        editProfileBtn.setOnMouseExited((t) -> {
            editProfileBtn.setStyle(getElement.getBlueStyleBtn());
        });
        Button ExitBtn = new Button("Logout");
        ExitBtn.setStyle(getElement.getRedStyleBtn());
        ExitBtn.setOnMouseEntered((t) -> {
            ExitBtn.setStyle(getElement.getStyleBtnHover());
        });
        ExitBtn.setOnMouseExited((t) -> {
            ExitBtn.setStyle(getElement.getRedStyleBtn());
        });
        TranferBtn.setOnAction((t) -> {
            Type = 't';
            stage.setScene(tranfer);
        });
        ExitBtn.setOnAction((t) -> {
            try {
                acDataList = Data.updateFile(Data.f, acDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            INFO.getChildren().clear();
            AccId = -1;
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        ConditionsBtn.setOnAction((t) -> {
            informationBox.displayConditions(logo, background);
            System.out.println("Conditions Press.");
        });
        TransactionBtn.setOnAction((t) -> {
            acDataList.get(AccId).showTransaction();
            informationBox.displayTransactionBox(acDataList.get(AccId), logo, background);
            System.out.println("TraTransaction Press.");
        });
        DepositBtn.setOnAction((t) -> {
            Type = 'd';
            tsLabel.setText("Deposit");
            stage.setScene(makeTransaction);
            System.out.println("Deposit Press.");
        });
        WidthdrawBtn.setOnAction((t) -> {
            Type = 'w';
            tsLabel.setText("Withdraw");
            stage.setScene(makeTransaction);
            System.out.println("Widthdraw Press.");
        });
        editProfileBtn.setOnAction((t) -> {
            switch (acDataList.get(AccId).getGender()) {
                case 'f':
                    fmGender2.setSelected(true);
                    break;
                case 'm':
                    mGender2.setSelected(true);
                    break;
                default:
                    fmGender2.setSelected(false);
                    mGender2.setSelected(false);
                    break;
            }
            usernameField3.setText(acDataList.get(AccId).getName());
            realnameTextField2.setText(acDataList.get(AccId).getRealName());
            surnameField2.setText(acDataList.get(AccId).getSurname());
            Type = acDataList.get(AccId).getGender();
            editProfilebox.getChildren().setAll(userR2, usernameField3,
                    nameTag, realnameTextField2,
                    surnameTag, surnameField2,
                    gender2,
                    RegisChoice2);
            stage.setScene(editProfile);
            System.out.println("Edit profile Press.");
        });
        //Layout Scene Option

        //Layout Scene forgot Password
        TextField ansField = new TextField();
        ansField.setMaxWidth(300);
        ansField.setStyle(getElement.BorderText);
        PasswordField FGpassField = new PasswordField();
        FGpassField.setMaxWidth(300);
        FGpassField.setStyle(getElement.BorderText);
        PasswordField cfFGpassField = new PasswordField();
        cfFGpassField.setMaxWidth(300);
        cfFGpassField.setStyle(getElement.BorderText);

        Button summitPassBtn = new Button("Submit");
        summitPassBtn.setStyle(getElement.getGrnStyleBtn());
        summitPassBtn.setOnMouseEntered((t) -> {
            summitPassBtn.setStyle(getElement.getStyleBtnHover());
        });
        summitPassBtn.setOnMouseExited((t) -> {
            summitPassBtn.setStyle(getElement.getGrnStyleBtn());
        });
        summitPassBtn.setOnAction((t) -> {
            try {
                acDataList.get(AccId).setPassword(ansField.getText(),
                        FGpassField.getText(), cfFGpassField.getText(), 0);
                acDataList = Data.updateFile(Data.f, acDataList);

                stage.setScene(login);
                AccId = -1;
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Summit please.");
        });
        Button cancelPassBtn = new Button("Cancel");
        cancelPassBtn.setStyle(getElement.getRedStyleBtn());
        cancelPassBtn.setOnMouseEntered((t) -> {
            cancelPassBtn.setStyle(getElement.getStyleBtnHover());
        });
        cancelPassBtn.setOnMouseExited((t) -> {
            cancelPassBtn.setStyle(getElement.getRedStyleBtn());
        });
        cancelPassBtn.setOnAction((t) -> {
            stage.setScene(login);
            System.out.println("Cancel please.");
        });
        HBox disistionFGPassBtn = new HBox(15);
        disistionFGPassBtn.getChildren().addAll(summitPassBtn,cancelPassBtn);
        disistionFGPassBtn.setAlignment(Pos.CENTER);
        //Layout Scene forgot Password

        //Layout Scene CFTransation
        CFTransactionbox.setAlignment(Pos.CENTER);
        Text TransactionText = new Text();
        TransactionText.setStyle(getElement.blueTxColor2);
        //TransactionText = setStyleElement.setStyleText(TransactionText, 15, Color.BLACK, Color.WHITE);
        Text RandomText = new Text();
        RandomText.setStyle(getElement.blueTxColor2);
        //RandomText = setStyleElement.setStyleText(RandomText, 15, Color.BLACK, Color.WHITE);
        TextField CFTextField = new TextField();
        CFTextField.setMaxWidth(300);
        CFTextField.setStyle(getElement.BorderText);
        HBox solve = new HBox(10);
        solve.setAlignment(Pos.CENTER);
        Button CFTranferBtn = new Button("Confirm");
        CFTranferBtn.setStyle(getElement.grnBgColor);
        CFTranferBtn.setOnMouseEntered((t) -> {
            CFTranferBtn.setStyle(getElement.HoverY);
        });
        CFTranferBtn.setOnMouseExited((t) -> {
            CFTranferBtn.setStyle(getElement.grnBgColor);
        });
        Button CancelTranferBtn = new Button("Cancel");
        CancelTranferBtn.setStyle(getElement.redBgColor);
        CancelTranferBtn.setOnMouseEntered((t) -> {
            CancelTranferBtn.setStyle(getElement.HoverY);
        });
        CancelTranferBtn.setOnMouseExited((t) -> {
            CancelTranferBtn.setStyle(getElement.redBgColor);
        });
        CFTranferBtn.setOnAction((t) -> {
            try {
                if (r1 + r2 == Integer.parseInt(CFTextField.getText())) {
                    if (Type == 't') {
                        acDataList.get(AccId).withdraw(amount);
                        acDataList.get(tfToAcc).deposit(amount);
                    } else if (Type == 'd' || Type == 'w') {
                        try {
                            acDataList.get(AccId).makeTransaction(Type, amount);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }

                    System.out.println("Amount : " + amount);
                    try {
                        acDataList = Data.updateFile(Data.f, acDataList);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                    }
                    INFO.getChildren().clear();
                    Text userText = new Text("Username : " + acDataList.get(AccId).getName());
                    userText.setStyle(getElement.nameTxColor1);
                    Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance() + "  " + "Baht");
                    balanceText.setStyle(getElement.nameTxColor2);
                    Text Fullname = new Text("Name : " + acDataList.get(AccId).getRealName() + "  " + acDataList.get(AccId).getSurname() + "  " + "Gender : " + "  " + acDataList.get(AccId).getGender());
                    Fullname.setStyle(getElement.nameTxColor1);

                    //INFO-TOP
                    HBox nameBalance = new HBox(20);
                    nameBalance.getChildren().addAll(userText);
                    VBox userInfo = new VBox(12);
                    userInfo.getChildren().addAll(nameBalance, Fullname);
                    HBox doubleLogo = new HBox(15);
                    doubleLogo.getChildren().addAll(getImageView(logo), getImageView(userimage));
                    HBox TOP = new HBox(40);
                    TOP.getChildren().addAll(doubleLogo, userInfo);
                    TOP.setTranslateX(45);
                    TOP.setTranslateY(10);

                    //FINANCE-CENTER
                    HBox DeWi = new HBox(15);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(15);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("       Welcome to system.\n Please choose your options.");
                    Options.setStyle(getElement.blueTxColor3);
                    Options.setTextFill(Color.BLACK);
                    VBox CENTER = new VBox(20);
                    CENTER.getChildren().addAll(balanceText, Options, DeWi, Trans);
                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, editProfileBtn, ConditionsBtn);
                    decission.setTranslateX(165);
                    decission.setTranslateY(-10);

                    INFO.setTop(TOP);
                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);

                    Type = 'n';
                    stage.setScene(option);
                } else {
                    throw new Exception("Your answer is wrong!");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input number.", logo, background);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }

        });

        CancelTranferBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel at confirm Transaction.");

        });
        //LayOut Scene CFTransation

        //Layout Scene Deposit/Withdraw
        Text TSamountText = new Text("Amount : ");
        TSamountText.setStyle(getElement.blueTxColor2);
        TextField TSamountField = new TextField();
        TSamountField.setStyle(getElement.BorderText);
        TSamountField.setMaxWidth(300);
//        Text TSaccountText = new Text("Tranfer to (name) : ");
//        TextField TSaccountField = new TextField();
        Button TSconfirmBtn = new Button("Confirm");
        TSconfirmBtn.setStyle(getElement.getGrnStyleBtn());
        TSconfirmBtn.setOnMouseEntered((t) -> {
            TSconfirmBtn.setStyle(getElement.getStyleBtnHover());
        });
        TSconfirmBtn.setOnMouseExited((t) -> {
            TSconfirmBtn.setStyle(getElement.getGrnStyleBtn());
        });
        TSconfirmBtn.setOnAction((t) -> {

            try {
                if (acDataList.get(AccId).getBalance() < Integer.parseInt(TSamountField.getText())
                        && Type == 'w') {
                    throw new Exception("Not money enough.");
                }

                if (Integer.parseInt(TSamountField.getText()) > acDataList.get(AccId).getMaxTransaction()
                        && Type == 'd') {
                    throw new Exception("You can't deposit more than " + acDataList.get(AccId).getMaxTransaction() + ".");
                }

                amount = Double.parseDouble(TSamountField.getText());
                //Text
                switch (Type) {
                    case 'd':
                        TransactionText.setText("You want to deposit amount : " + amount + "  " + "baht");
                        TransactionText.setStyle(getElement.blueTxColor2);
                        break;
                    case 'w':
                        TransactionText.setText("You want to widthdraw amount : " + amount + "  " + "baht");
                        TransactionText.setStyle(getElement.blueTxColor2);
                        break;
                    default:
                        throw new Exception("input d : deposit\ninput w : withdraw");
                }
                Text TransactionText2 = new Text("Amount : ");
                TransactionText2.setStyle(getElement.blueTxColor2);

                Random random = new Random();
                r1 = random.nextInt(100);
                r2 = random.nextInt(100);
                RandomText.setText(r1 + " + " + r2 + " = (Please fill answer below.)");

                solve.getChildren().setAll(TransactionText2, RandomText);

                CFTransactionbox.getChildren().setAll(getImageView(logo), TransactionText, solve,
                        CFTextField, CFTranferBtn, CancelTranferBtn);
                stage.setScene(CFTransactionScene);
                System.out.println("Confirm press.");
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Plese input amount be number.");
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input amount be number.", logo, background);
            } catch (Exception e) {
                System.out.println(e);
                informationBox.displayAlertBox("Error", e.getMessage(), logo, background);
            }

        });
        Button TScancelBtn = new Button("Cancel");
        TScancelBtn.setStyle(getElement.getRedStyleBtn());
        TScancelBtn.setOnMouseEntered((t) -> {
            TScancelBtn.setStyle(getElement.getStyleBtnHover());
        });
        TScancelBtn.setOnMouseExited((t) -> {
            TScancelBtn.setStyle(getElement.getRedStyleBtn());
        });
        TScancelBtn.setOnAction((t) -> {
            Type = 'n';
            stage.setScene(option);
            System.out.println("Cancel press.");
        });
        HBox Topfinance = new HBox(15);
        Topfinance.getChildren().addAll(getImageView(logo), tsLabel);
        Topfinance.setAlignment(Pos.CENTER);
        TSbox.setAlignment(Pos.CENTER);
        Topfinance.setTranslateY(-15);
        Text WarningTS = new Text("--- Maximum amount is 20,000 Baht. ---");
        WarningTS.setStyle(getElement.nameTxColor2);
        TSbox.getChildren().addAll(Topfinance, WarningTS, TSamountText, TSamountField,
                TSconfirmBtn, TScancelBtn);
        //Layout Scene Deposit/Withdraw

        //Layout Scene Tranfer  
        Text amountText = new Text("Amount : ");
        amountText.setStyle(getElement.blueTxColor2);
        TextField amountField = new TextField();
        amountField.setMaxWidth(300);
        amountField.setStyle(getElement.BorderText);
        Text accountText = new Text("Tranfer to (name) : ");
        accountText.setStyle(getElement.blueTxColor2);
        TextField accountField = new TextField();
        accountField.setMaxWidth(300);
        accountField.setStyle(getElement.BorderText);
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle(getElement.getGrnStyleBtn());
        confirmBtn.setOnMouseEntered((t) -> {
            confirmBtn.setStyle(getElement.getStyleBtnHover());
        });
        confirmBtn.setOnMouseExited((t) -> {
            confirmBtn.setStyle(getElement.getGrnStyleBtn());
        });
        confirmBtn.setOnAction((t) -> {
            try {
                if (acDataList.get(AccId).getBalance() >= Double.parseDouble(amountField.getText())) {
                    tfToAcc = -1;
                    for (Account account : acDataList) {
                        if (account.getName().equals(accountField.getText())) {
                            tfToAcc = account.getId() - 1;
                            if (AccId == tfToAcc) {
                                throw new Exception("You tranfer to your account.");
                            }

                            Random random = new Random();
                            r1 = random.nextInt(100);
                            r2 = random.nextInt(100);
                            RandomText.setText(r1 + " + " + r2 + " = (Please fill answer below.)");
                            RandomText.setStyle(getElement.blueTxColor2);
                            amount = Double.parseDouble(amountField.getText());
                            //Text
                            TransactionText.setText(acDataList.get(AccId).getName() + " tranfers to "
                                    + acDataList.get(tfToAcc).getName());
                            TransactionText.setStyle(getElement.blueTxColor2);
                            System.out.println("Acc : " + AccId);
                            System.out.println("tf : " + tfToAcc);
                            Text TransactionText2 = new Text("Amount : ");
                            TransactionText2.setStyle(getElement.blueTxColor2);

                            solve.getChildren().setAll(TransactionText2, RandomText);

                            CFTransactionbox.getChildren().setAll(getImageView(logo), TransactionText, solve,
                                    CFTextField, CFTranferBtn, CancelTranferBtn);

                            stage.setScene(CFTransactionScene);
                            break;
                        }
                    }
                    if (tfToAcc == -1) {
                        throw new Exception("Wrong account.");
                    }
                } else {
                    throw new Exception("Not money enough.");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input amount be number.", logo, background);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }

            System.out.println("Confirm press.");
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle(getElement.getRedStyleBtn());
        cancelBtn.setOnMouseEntered((t) -> {
            cancelBtn.setStyle(getElement.getStyleBtnHover());
        });
        cancelBtn.setOnMouseExited((t) -> {
            cancelBtn.setStyle(getElement.getRedStyleBtn());
        });
        cancelBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel press.");
        });
        Label tran = new Label("Transfer / Loan");
        tran.setStyle("-fx-font-size:20px;");
        tran.setTextFill(Color.WHITE);
        HBox transferTop = new HBox(15);
        transferTop.getChildren().addAll(getImageView(logo), tran);
        transferTop.setAlignment(Pos.CENTER);
        Text warn = new Text("Please make sure that you put a correct username.");
        warn.setStyle(getElement.nameTxColor2);
        TFbox.getChildren().addAll(transferTop, warn, accountText, accountField, amountText, amountField, confirmBtn, cancelBtn);
        TFbox.setAlignment(Pos.CENTER);
        //Layout Scene Tranfer  

        //Layout Scene Login
        Label labell3 = new Label("WELCOME TO O PLUS SERVICE\n             Please sign in.");
        labell3.setMinSize(50, 0);
        labell3.setScaleX(1.5);
        labell3.setScaleY(1.5);
        labell3.setStyle("-fx-font-size:16px;");

        labell3.setStyle(getElement.blueTxColor);
        labell3.setAlignment(Pos.TOP_CENTER);

        Label labell4 = new Label("Don't have an account. Try now?");
        labell4.setScaleX(1);
        labell4.setScaleY(1);
        labell4.setStyle("-fx-font-size:15px;");
        labell4.setStyle(getElement.redTexColor);
        labell4.setAlignment(Pos.CENTER);

        Text idTopic = new Text("Username : ");
        idTopic.setFill(Color.WHITE);
        idTopic.setStyle("-fx-font-size:15px;");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        usernameField.setAlignment(Pos.CENTER);
        usernameField.setStyle(getElement.BorderText);
        Text passTopic = new Text("Password : ");
        passTopic.setFill(Color.WHITE);
        passTopic.setStyle("-fx-font-size:15px;");
        TextField passField = new PasswordField();
        passField.setMaxWidth(300);
        passField.setAlignment(Pos.CENTER);
        passField.setStyle(getElement.BorderText);
        Button LIBtn = new Button("Login");
        LIBtn.setStyle(getElement.getGrnStyleBtn());
        LIBtn.setOnMouseEntered((t) -> {
            LIBtn.setStyle(getElement.getStyleBtnHover());
        });
        LIBtn.setOnMouseExited((t) -> {
            LIBtn.setStyle(getElement.getGrnStyleBtn());
        });
        LIBtn.setOnAction((var t) -> {
            for (Account account1 : acDataList) {
                String thisUser = usernameField.getText(), thisPass = passField.getText();
                String chkUser = account1.getName(), chkPass = account1.getPassword();
                if (thisUser.equals(chkUser) && thisPass.equals(chkPass)) {
                    AccId = account1.getId() - 1;
                    System.out.println("Math! : " + account1.getId());
                    stage.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    userText.setStyle(getElement.nameTxColor1);
                    Text balanceText = new Text("Balance : " + account1.getBalance() + "  " + "Baht");
                    balanceText.setStyle(getElement.nameTxColor2);

                    Text Fullname = new Text("Name : " + account1.getRealName() + "  " + account1.getSurname() + "  " + "Gender : " + " " + account1.getGender());
                    Fullname.setStyle(getElement.nameTxColor1);

                    //INFO-TOP
                    HBox nameBalance = new HBox(20);
                    nameBalance.getChildren().addAll(userText);
                    VBox userInfo = new VBox(12);
                    userInfo.getChildren().addAll(nameBalance, Fullname);
                    HBox doubleLogo = new HBox(15);
                    doubleLogo.getChildren().addAll(getImageView(logo), getImageView(userimage));
                    HBox TOP = new HBox(40);
                    TOP.getChildren().addAll(doubleLogo, userInfo);
                    TOP.setTranslateX(45);
                    TOP.setTranslateY(10);

                    //FINANCE-CENTER
                    HBox DeWi = new HBox(15);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(15);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("       Welcome to system.\n Please choose your options.");
                    Options.setStyle(getElement.blueTxColor3);
                    Options.setTextFill(Color.BLACK);
                    VBox CENTER = new VBox(20);
                    CENTER.getChildren().addAll(balanceText, Options, DeWi, Trans);
                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, editProfileBtn, ConditionsBtn);
                    decission.setTranslateX(165);
                    decission.setTranslateY(-10);

                    INFO.setTop(TOP);
                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);
//                    OTbox.getChildren().addAll(userText, balanceText, TranferBtn,
//                            TransactionBtn, fixPassBtn, ExitBtn);
                    break;
                }
            }
            if (AccId == -1) //If didn't have id in account list.
            {
                informationBox.displayAlertBox("O+ O PLUS", "Wrong unsername or password.", logo, background);
            }
            usernameField.clear();
            passField.clear();
            System.out.println("Login Press.\n");
        });
        Button RGBtn = new Button("Register");
        RGBtn.setStyle(getElement.getRedStyleBtn());
        RGBtn.setOnMouseEntered((t) -> {
            RGBtn.setStyle(getElement.getStyleBtnHover());
        });
        RGBtn.setOnMouseExited((t) -> {
            RGBtn.setStyle(getElement.getRedStyleBtn());
        });
        RGBtn.setOnAction((ActionEvent t) -> {
            stage.setScene(register);
            usernameField.clear();
            passField.clear();
            System.out.println("Register Press.");
        });
        Button ChkListBtn = new Button("Check list");
        ChkListBtn.setOnAction((ActionEvent t) -> {
            showList(acDataList);
            System.out.println("Size : " + acDataList.size());
        });
        Button FGPBtn = new Button("Forgot Password.");
        FGPBtn.setStyle(getElement.getYelStyleBtn());
        FGPBtn.setOnMouseEntered((t) -> {
            FGPBtn.setStyle(getElement.getStyleBtnHover());
        });
        FGPBtn.setOnMouseExited((t) -> {
            FGPBtn.setStyle(getElement.getYelStyleBtn());
        });
        FGPBtn.setOnAction((t) -> {
            AccId = Data.findData(usernameField.getText(), acDataList);
            if (AccId >= 0) {
                FGPbox.getChildren().clear();
                Text PassQThint = new Text("Question : " + acDataList.get(AccId).getQTPassHint());
                PassQThint.setStyle(getElement.blueTxColor2);
                Text answer2 = new Text("Answer :");
                answer2.setStyle(getElement.blueTxColor2);
                Text pass1 = new Text("New-Password : ");
                pass1.setStyle(getElement.blueTxColor2);
                Text cfpass1 = new Text("Confirm New-Password : ");
                cfpass1.setStyle(getElement.blueTxColor2);
                stage.setScene(forgotPassword);
                FGPbox.getChildren().addAll(PassQThint,
                        answer2, ansField,
                        pass1, FGpassField,
                        cfpass1, cfFGpassField,
                        disistionFGPassBtn);
                FGPbox.setAlignment(Pos.CENTER);
            } else {
                informationBox.displayAlertBox("Error", "Wrong username.", logo, background);
            }
            usernameField.clear();
            passField.clear();
        });
        LIbox.setAlignment(Pos.CENTER);
        HBox LIFGBtn = new HBox(15);
        LIFGBtn.getChildren().addAll(LIBtn, FGPBtn);
        LIFGBtn.setAlignment(Pos.CENTER);
        LIbox.getChildren().addAll(getImageView(logo), labell3, idTopic, usernameField, passTopic,
                passField, LIFGBtn, labell4, RGBtn);
        //Layout Scene Login

        //Layout Scene Register 
        Text usernameR = new Text("Username : ");
        usernameR.setStyle(getElement.blueTxColor2);
        Text userDeal = new Text("(user must not be the same)");
        userDeal.setFill(Color.RED);
        HBox userR = new HBox(3);
        userR.getChildren().addAll(usernameR, userDeal);
        userR.setAlignment(Pos.CENTER);
        Text passwordR = new Text("Password : ");
        passwordR.setStyle(getElement.blueTxColor2);
        Text passDeal = new Text("(must be between 4-16 digits)");
        passDeal.setFill(Color.RED);
        HBox passR = new HBox(3);
        passR.getChildren().addAll(passwordR, passDeal);
        passR.setAlignment(Pos.CENTER);
        Text name = new Text("Name : ");
        name.setStyle(getElement.blueTxColor2);
        Text surname = new Text("Surname : ");
        surname.setStyle(getElement.blueTxColor2);
        Text question = new Text("Question : ");
        question.setStyle(getElement.blueTxColor2);
        Text answer = new Text("Answer : ");
        answer.setStyle(getElement.blueTxColor2);

        TextField usernameField2 = new TextField();
        usernameField2.setMaxWidth(300);
        usernameField2.setStyle(getElement.BorderText);
        PasswordField passField2 = new PasswordField();
        passField2.setMaxWidth(300);
        passField2.setStyle(getElement.BorderText);
        TextField realnameTextField = new TextField();
        realnameTextField.setMaxWidth(300);
        realnameTextField.setStyle(getElement.BorderText);
        TextField surnameField = new TextField();
        surnameField.setMaxWidth(300);
        surnameField.setStyle(getElement.BorderText);
        TextField qtPassHintField = new TextField();
        qtPassHintField.setMaxWidth(300);
        qtPassHintField.setStyle(getElement.BorderText);
        TextField ansPassHintField = new TextField();
        ansPassHintField.setMaxWidth(300);
        ansPassHintField.setStyle(getElement.BorderText);

        CheckBox mGender = new CheckBox("Male");
        mGender.setStyle(getElement.blueTxColor);
        CheckBox fmGender = new CheckBox("Female");
        fmGender.setStyle(getElement.blueTxColor);
        HBox gender = new HBox(20);
        gender.getChildren().addAll(mGender, fmGender);
        gender.setAlignment(Pos.CENTER);

        mGender.setOnAction((t) -> {
            if (mGender.isSelected()) {
                fmGender.setSelected(false);
            }
        });

        fmGender.setOnAction((t) -> {
            if (fmGender.isSelected()) {
                mGender.setSelected(false);
            }
        });

        Button SMBtn = new Button("Submit");
        SMBtn.setStyle(getElement.getGrnStyleBtn());
        SMBtn.setOnMouseEntered((t) -> {
            SMBtn.setStyle(getElement.getStyleBtnHover());
        });
        SMBtn.setOnMouseExited((t) -> {
            SMBtn.setStyle(getElement.getGrnStyleBtn());
        });
        Button CancelBtn = new Button("Cancel");
        CancelBtn.setStyle(getElement.getRedStyleBtn());
        CancelBtn.setOnMouseEntered((t) -> {
            CancelBtn.setStyle(getElement.getStyleBtnHover());
        });
        CancelBtn.setOnMouseExited((t) -> {
            CancelBtn.setStyle(getElement.getRedStyleBtn());
        });
        SMBtn.setOnAction((ActionEvent t) -> {

            char Gender = 'n';
            try {
                acDataList = Data.readFile(Data.f);
                for (Account account : acDataList) {
                    if (usernameField2.getText().equals(account.getName())) {
                        throw new Exception("This username is already.");
                    }
                }

                if (mGender.isSelected() == false && fmGender.isSelected() == false) {
                    throw new Exception("Please select gender.");
                }
                if (mGender.isSelected() == true) {
                    Gender = 'M';
                } else {
                    Gender = 'F';
                }
                acDataList = Data.readFile(Data.f);
                acDataList.add(new Account(usernameField2.getText(), passField2.getText(),
                        acDataList.size() + 1,
                        realnameTextField.getText(),
                        surnameField.getText(),
                        Gender,
                        qtPassHintField.getText(),
                        ansPassHintField.getText()));
                Data.writeFile(Data.f, acDataList);
                acDataList = Data.readFile(Data.f);
                Gender = 'n';
                stage.setScene(login);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Submit Press.");
        });
        CancelBtn.setOnAction((t) -> {
            stage.setScene(login);
            System.out.println("Cancel Press.");
        });
        RGbox.setAlignment(Pos.CENTER);
        HBox RegisChoice = new HBox(15);
        RegisChoice.getChildren().addAll(SMBtn, CancelBtn);
        RegisChoice.setAlignment(Pos.CENTER);
        RGbox.getChildren().addAll(userR, usernameField2,
                passR, passField2,
                name, realnameTextField,
                surname, surnameField,
                gender,
                question, qtPassHintField,
                answer, ansPassHintField,
                RegisChoice);
        //Layout Scene Register 
        
        

        login = new Scene(LIbox, stageWidth, stageHeight);
        register = new Scene(RGbox, stageWidth, stageHeight);
        option = new Scene(INFO, stageWidth, stageHeight);
        tranfer = new Scene(TFbox, stageWidth, stageHeight);
        fixPassword = new Scene(FPbox, stageWidth, stageHeight);
        forgotPassword = new Scene(FGPbox, stageWidth, stageHeight);
        makeTransaction = new Scene(TSbox, stageWidth, stageHeight);
        CFTransactionScene = new Scene(CFTransactionbox, stageWidth, stageHeight);
        editProfile = new Scene(editProfilebox, stageWidth, stageHeight);
        stage.setScene(login);
        stage.setResizable(false);
        stage.show();
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

    public static ImageView getImageView(Image logo) {
        ImageView LOGO = new ImageView(logo);
        LOGO.setFitHeight(60);
        LOGO.setFitWidth(60);
        LOGO.setPreserveRatio(true);
        return LOGO;

        //Develop
    }

    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
//        File f = new File("Accout.dat");
//        ArrayList<Account> ac = new ArrayList<>();
//        Account a1 = new Account("Jame","Jame.011",1,"Surawit","Yosaeng","My Name","Jame");
//        ac.add(a1);
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
//        out.writeObject(ac);
//        System.out.println("Finish");
        launch(args);

    }
}
