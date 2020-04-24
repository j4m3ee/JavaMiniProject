package miniproject;

import static GUI.getElement.*;
import GUI.informationBox;
import GUI.setStyleElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.InputMap;

public class MiniProject extends Application {

    public Stage window;
    public String pathPic = "resource\\Pictures\\";
    int AccId = -1, tfToAcc = -1, AcBankId = -1, tfToAccBank = -1;
    double amount = 0.0, stageWidth = 600, stageHeight = 650, regisHeight = 800;
    Scene login, option, tranfer, register, fixPassword, forgotPassword,
            editProfile, makeTransaction, CFTransactionScene, bankRegister;
    ArrayList<Account> acDataList = new ArrayList<>();
    char Type = 'n', TypeBank = 'n';
    ComboBox<Bank> cbBox;
    ComboBox<String> cbAllBox, cbTfBox;

    int r1 = 0, r2 = 0;
    ImageView profileImage;

    //139,255,37 green
    //(#E2F0CB) 226,240,203 pastel   yellow green
    //(#B5EAD7) 181,234,215 pastel green
    //(#FF9AA2) 255,154,162 pastel red
    @Override
    public void start(Stage stage)
            throws Exception, FileNotFoundException, IOException, ClassNotFoundException {
        window = stage;
        window.setTitle("O+ O PLUS");
        Image logo = new Image(new FileInputStream(pathPic + "Logo.png"));
        Image userimage = new Image(new FileInputStream(pathPic + "User1.png"));
        File depositFile = new File(pathPic + "depo.png");
        Image Depo = new Image(depositFile.toURI().toString());
        File withdrawFile = new File(pathPic + "with.png");
        Image With = new Image(withdrawFile.toURI().toString());
        File transactionFile = new File(pathPic + "tran.png");
        Image Tran = new Image(transactionFile.toURI().toString());
        File histFile = new File(pathPic + "hist.png");
        Image Hist = new Image(histFile.toURI().toString());

        // BANKS
        Image KBANK = new Image(new FileInputStream(pathPic + "KBANK.png"));
        Image KTB = new Image(new FileInputStream(pathPic + "KTB.png"));
        Image SCB = new Image(new FileInputStream(pathPic + "SCB.png"));
        Image TMB = new Image(new FileInputStream(pathPic + "TMB.png"));
        Image UOB = new Image(new FileInputStream(pathPic + "UOB.png"));
        Image GSB = new Image(new FileInputStream(pathPic + "GSB.png"));
        HBox Banks = new HBox(15);
        Banks.getChildren().addAll(getLogoView1(KBANK), getLogoView2(KTB), getLogoView3(SCB),
                getLogoView4(GSB), getLogoView5(UOB), getLogoView6(TMB));
        Banks.setAlignment(Pos.CENTER);
        Banks.setTranslateY(20);
        Banks.setOpacity(0.75);

        window.getIcons().add(logo);
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
        VBox bankRGbox = new VBox(15);

        LIbox.setBackground(background);
        RGbox.setBackground(background);
        TFbox.setBackground(background);
        FPbox.setBackground(background);
        FGPbox.setBackground(background);
        TSbox.setBackground(background);
        CFTransactionbox.setBackground(background);
        editProfilebox.setBackground(background);
        bankRGbox.setBackground(background);

        BorderPane INFO = new BorderPane(); //User
        INFO.setBackground(background);

        //combo box
        cbBox = new ComboBox<>();
        cbBox.setMaxWidth(250);
        cbBox.setMinWidth(250);
        cbBox.setStyle(getPurpleStyleBtn());
        cbBox.setOnMouseEntered((t) -> {
            cbBox.setStyle(getStyleBtnHover());
        });
        cbBox.setOnMouseExited((t) -> {
            cbBox.setStyle(getPurpleStyleBtn());
        });
        cbAllBox = new ComboBox<>();
        cbAllBox.setMaxWidth(250);
        cbAllBox.setStyle(getPurpleStyleBtn());
        cbAllBox.getItems().clear();
        for (String bank : Bank.nameBankList) {
            cbAllBox.getItems().add(bank);
        }
        cbTfBox = new ComboBox<>();
        cbTfBox.setMaxWidth(250);
        cbTfBox.setStyle(getPurpleStyleBtn());
        cbTfBox.getItems().clear();
        for (String bank : Bank.nameBankList) {
            cbTfBox.getItems().add(bank);
        }

        //Layout Scene edit profile
        Text editUsernameR = new Text("Username : ");
        editUsernameR.setStyle(blueTxColor2);
        Text editUserDeal = new Text("(user must not be the same)");
        editUserDeal.setFill(Color.RED);
        HBox userR2 = new HBox(3);
        userR2.getChildren().addAll(editUsernameR, editUserDeal);
        userR2.setAlignment(Pos.CENTER);

        TextField usernameField3 = new TextField();
        usernameField3.setMaxWidth(300);
        usernameField3.setStyle(BorderText);
        PasswordField passField3 = new PasswordField();
        passField3.setMaxWidth(300);
        passField3.setStyle(BorderText);
        TextField realnameTextField2 = new TextField();
        realnameTextField2.setMaxWidth(300);
        realnameTextField2.setStyle(BorderText);
        TextField surnameField2 = new TextField();
        surnameField2.setMaxWidth(300);
        surnameField2.setStyle(BorderText);

        CheckBox mGender2 = new CheckBox("Male");
        mGender2.setStyle(blueTxColor);
        CheckBox fmGender2 = new CheckBox("Female");
        fmGender2.setStyle(blueTxColor);
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
        fixPassBtn.setStyle(getBlueStyleBtn());
        fixPassBtn.setOnMouseEntered((t) -> {
            fixPassBtn.setStyle(getStyleBtnHover());
        });
        fixPassBtn.setOnMouseExited((t) -> {
            fixPassBtn.setStyle(getBlueStyleBtn());
        });

        Button SMBtn2 = new Button("Submit");
        SMBtn2.setStyle(getGrnStyleBtn());
        SMBtn2.setOnMouseEntered((t) -> {
            SMBtn2.setStyle(getStyleBtnHover());
        });
        SMBtn2.setOnMouseExited((t) -> {
            SMBtn2.setStyle(getGrnStyleBtn());
        });
        Button CancelBtn2 = new Button("Cancel");
        CancelBtn2.setStyle(getRedStyleBtn());
        CancelBtn2.setOnMouseEntered((t) -> {
            CancelBtn2.setStyle(getStyleBtnHover());
        });
        CancelBtn2.setOnMouseExited((t) -> {
            CancelBtn2.setStyle(getRedStyleBtn());
        });
        Button deleteAcBtn = new Button("Delete");
        deleteAcBtn.setStyle(getRedStyleBtn());
        deleteAcBtn.setOnMouseEntered((t) -> {
            deleteAcBtn.setStyle(getStyleBtnHover());
        });
        deleteAcBtn.setOnMouseExited((t) -> {
            deleteAcBtn.setStyle(getRedStyleBtn());
        });
        Button importPicbtn = new Button("Edit Picture");
        importPicbtn.setStyle(getGrnStyleBtn());
        importPicbtn.setOnMouseEntered((t) -> {
            importPicbtn.setStyle(getStyleBtnHover());
        });
        importPicbtn.setOnMouseExited((t) -> {
            importPicbtn.setStyle(getGrnStyleBtn());
        });
        importPicbtn.setOnAction((t) -> {
            acDataList.get(AccId).setPictureFile(Data.UploadPic());
            try {
                acDataList = Data.updateFile(Data.f, acDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
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
                userText.setStyle(nameTxColor4);
                Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance() + "  " + "Baht");
                balanceText.setStyle(nameTxColor4);
                Text Fullname = new Text("Name : " + acDataList.get(AccId).getRealName() + "  "
                        + acDataList.get(AccId).getSurname() + "  " + "Gender : " + "  "
                        + acDataList.get(AccId).getGender());
                Fullname.setStyle(nameTxColor4);

                //INFO-TOP
                VBox userInfo = new VBox(12);
                userInfo.getChildren().addAll(userText, Fullname);
                profileImage = getImageView(userimage);
                try {
                    if (!acDataList.get(AccId).getPictureFile().canRead()) {
                        profileImage = getImageView(userimage);
                    } else {
                        profileImage = getProfile(new Image(acDataList.get(AccId).getPictureFile().toURI().toString()));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                HBox TOP = new HBox(40);

                TOP.getChildren().addAll(profileImage, userInfo);
                TOP.setTranslateX(45);
                TOP.setTranslateY(10);

                INFO.setTop(TOP);

                window.setScene(option);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Submit Press.");
        });
        fixPassBtn.setOnAction((t) -> {
            window.setScene(fixPassword);
            System.out.println("Fix Password Press.");
        });
        CancelBtn2.setOnAction((t) -> {
            window.setScene(option);
            System.out.println("Cancel Press.");
        });

        deleteAcBtn.setOnAction((t) -> {
            try {
                for (Bank bk : acDataList.get(AccId).getBank()) {
                    if (bk.getBalance() > 0.0) {
                        throw new Exception("Please clear your balance in " + bk.getId() + " bank.");
                    }
                }

                if (informationBox.confirmAlert("Confirm", "Are you sure?", logo, background)) {
                    acDataList.remove(AccId);
                    for (int i = 0; i < acDataList.size(); i++) {
                        acDataList.get(i).setId(i + 1);
                    }
                    acDataList = Data.updateFile(Data.f, acDataList);
                    AccId = -1;
                    window.setScene(login);
                }

            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Del Prease.");
        });
        Text nameTag = new Text("Name : ");
        nameTag.setStyle(blueTxColor2);
        Text surnameTag = new Text("Surname : ");
        surnameTag.setStyle(blueTxColor2);
        editProfilebox.setAlignment(Pos.CENTER);
        HBox RegisChoice2 = new HBox(15);
        RegisChoice2.getChildren().addAll(SMBtn2, fixPassBtn, deleteAcBtn, CancelBtn2);
        RegisChoice2.setAlignment(Pos.CENTER);
        editProfilebox.getChildren().addAll(RegisChoice2);
        //Layout Scene edit profile

        //Layout Scene bank register
        Text bankSelecText = new Text("Select your bank : ");
        bankSelecText.setStyle(nameTxColor4);
        Text bankIdText = new Text("Bank ID : ");
        bankIdText.setStyle(nameTxColor4);
        Text bankNameIdText = new Text("Name of Bank ID : ");
        bankNameIdText.setStyle(nameTxColor4);
        TextField bankIdFiled = new TextField();
        bankIdFiled.setStyle(BorderText);
        bankIdFiled.setMaxWidth(300);
        TextField bankNameIdFiled = new TextField();
        bankNameIdFiled.setStyle(BorderText);
        bankNameIdFiled.setMaxWidth(300);
        Button smBgBtn = new Button("Submit");
        smBgBtn.setStyle(getGrnStyleBtn());
        smBgBtn.setOnMouseEntered((t) -> {
            smBgBtn.setStyle(getStyleBtnHover());
        });
        smBgBtn.setOnMouseExited((t) -> {
            smBgBtn.setStyle(getGrnStyleBtn());
        });
        smBgBtn.setOnAction((t) -> {
            try {
                if (cbAllBox.getValue() == null) {
                    throw new Exception("Choose the bank.");
                }

                if (bankNameIdFiled.getText().isBlank()) {
                    throw new Exception("Enter name id.");
                }

                if (bankIdFiled.getText().isBlank()) {
                    throw new Exception("Enter id.");
                }

                switch (TypeBank) {
                    case 'r':
                        for (Account ac : acDataList) {
                            for (Bank bank : ac.getBank()) {
                                if (bankIdFiled.getText().equals(bank.getBankId())
                                        && bank.getNameBank().equals((String) cbAllBox.getValue())) {
                                    throw new Exception("This id is already in this bank.");
                                }
                            }
                        }
                        Bank bk = new Bank((String) cbAllBox.getValue(),
                                bankIdFiled.getText(), bankNameIdFiled.getText(),
                                acDataList.get(AccId).getBank().size() + 1);
                        acDataList.get(AccId).addBank(bk);
                        acDataList = Data.updateFile(Data.f, acDataList);
                        cbBox.getItems().clear();
                        for (Bank bank : acDataList.get(AccId).getBank()) {
                            cbBox.getItems().add(bank);
                        }
                        TypeBank = 'n';
                        cbAllBox.getItems().clear();
                        for (String bank : Bank.nameBankList) {
                            cbAllBox.getItems().add(bank);
                        }
                        bankIdFiled.clear();
                        bankNameIdFiled.clear();
                        System.out.println("Regis bank success.");
                        break;
                    case 'e':
                        acDataList.get(AccId).getBank().get(AcBankId).setBankId(bankIdFiled.getText());
                        acDataList.get(AccId).getBank().get(AcBankId).setNameIdBank(bankNameIdFiled.getText());
                        acDataList.get(AccId).getBank().get(AcBankId).setNameBank((String) cbAllBox.getValue());

                        acDataList = Data.updateFile(Data.f, acDataList);
                        cbBox.getItems().clear();
                        for (Bank bank : acDataList.get(AccId).getBank()) {
                            cbBox.getItems().add(bank);
                        }
                        TypeBank = 'n';
                        cbAllBox.getItems().clear();
                        for (String bank : Bank.nameBankList) {
                            cbAllBox.getItems().add(bank);
                        }
                        bankIdFiled.clear();
                        bankNameIdFiled.clear();
                        System.out.println("Edit bank success.");
                        break;
                    default:
                        System.out.println("Type bank : " + TypeBank);
                        break;
                }

                window.setScene(option);
            } catch (Exception ex) {
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
                System.out.println(ex);
            }

            System.out.println("Submit prease.");
        });
        Button ccBgBtn = new Button("Cancel");
        ccBgBtn.setStyle(getRedStyleBtn());
        ccBgBtn.setOnMouseEntered((t) -> {
            ccBgBtn.setStyle(getStyleBtnHover());
        });
        ccBgBtn.setOnMouseExited((t) -> {
            ccBgBtn.setStyle(getRedStyleBtn());
        });
        ccBgBtn.setOnAction((t) -> {
            window.setScene(option);
            System.out.println("Cancel prease.");
        });
        HBox dicisBgBox = new HBox(15);
        dicisBgBox.setAlignment(Pos.CENTER);
        dicisBgBox.getChildren().addAll(smBgBtn, ccBgBtn);
        bankRGbox.setAlignment(Pos.CENTER);
        Text BankTx = new Text("Bank information");
        BankTx.setFill(Color.WHITE);
        BankTx.setStyle("-fx-font-size:20px;");
        HBox Banks2 = new HBox(15);
        Banks2.getChildren().addAll(getLogoView1(KBANK), getLogoView2(KTB), getLogoView3(SCB),
                getLogoView4(GSB), getLogoView5(UOB), getLogoView6(TMB));
        Banks2.setAlignment(Pos.CENTER);
        Banks2.setTranslateY(-10);
        Banks2.setOpacity(0.8);
        HBox TopBank = new HBox(15);
        TopBank.getChildren().addAll(getImageView(logo), BankTx);
        TopBank.setAlignment(Pos.CENTER);
        bankRGbox.getChildren().addAll(Banks2, TopBank, bankSelecText, cbAllBox, bankIdText,
                bankIdFiled, bankNameIdText, bankNameIdFiled, dicisBgBox);
        //Layout Scene bank register

        //Layout Scene fixPassword
        Text oldPassText = new Text("Enter old password.");
        oldPassText.setStyle(blueTxColor2);
        TextField oldPassTextField = new PasswordField();
        oldPassTextField.setMaxWidth(300);
        oldPassTextField.setStyle(BorderText);
        Text newPassText = new Text("Enter new password.");
        newPassText.setStyle(blueTxColor2);
        TextField newPassTextField = new PasswordField();
        newPassTextField.setMaxWidth(300);
        newPassTextField.setStyle(BorderText);
        Text CFnewPassText = new Text("Re-new password.");
        CFnewPassText.setStyle(blueTxColor2);
        TextField CFnewPassTextField = new PasswordField();
        CFnewPassTextField.setMaxWidth(300);
        CFnewPassTextField.setStyle(BorderText);
        Button SMFixPassBtn = new Button("Submit");
        SMFixPassBtn.setStyle(getGrnStyleBtn());
        SMFixPassBtn.setOnMouseEntered((t) -> {
            SMFixPassBtn.setStyle(getStyleBtnHover());
        });
        SMFixPassBtn.setOnMouseExited((t) -> {
            SMFixPassBtn.setStyle(getGrnStyleBtn());
        });
        SMFixPassBtn.setOnAction((var t) -> {
            try {
                acDataList.get(AccId).setPassword(oldPassTextField.getText(),
                        newPassTextField.getText(), CFnewPassTextField.getText());
                acDataList = Data.updateFile(Data.f, acDataList);
                window.setScene(option);
                System.out.println("Submit Press.");
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
        });
        Button CancelFixPassBtn = new Button("Cancel");
        CancelFixPassBtn.setStyle(getRedStyleBtn());
        CancelFixPassBtn.setOnMouseEntered((t) -> {
            CancelFixPassBtn.setStyle(getStyleBtnHover());
        });
        CancelFixPassBtn.setOnMouseExited((t) -> {
            CancelFixPassBtn.setStyle(getRedStyleBtn());
        });
        CancelFixPassBtn.setOnAction((t) -> {
            window.setScene(option);
            System.out.println("Cancel Press.");
        });
        HBox disiFixPassBtn = new HBox(15);
        disiFixPassBtn.getChildren().addAll(SMFixPassBtn, CancelFixPassBtn);
        disiFixPassBtn.setAlignment(Pos.CENTER);
        Text FixPassTx = new Text("Change password");
        FixPassTx.setFill(Color.WHITE);
        FixPassTx.setStyle("-fx-font-size: 20px;");
        HBox FixTop = new HBox(15);
        FixTop.getChildren().addAll(getImageView(logo), FixPassTx);
        FixTop.setAlignment(Pos.CENTER);
        FPbox.getChildren().addAll(FixTop, oldPassText, oldPassTextField, newPassText,
                newPassTextField, CFnewPassText, CFnewPassTextField, disiFixPassBtn);
        FPbox.setAlignment(Pos.CENTER);
        //Layout Scene fixPassword

        //Layout Scene Option
        Button ConditionsBtn = new Button("Account Conditions");
        ConditionsBtn.setStyle(getPurpleStyleBtn());
        ConditionsBtn.setOnMouseEntered((t) -> {
            ConditionsBtn.setStyle(getStyleBtnHover());
        });
        ConditionsBtn.setOnMouseExited((t) -> {
            ConditionsBtn.setStyle(getPurpleStyleBtn());
        });
        Button TranferBtn = new Button("Transfer", new ImageView(Tran));
        TranferBtn.setPrefWidth(200);
        TranferBtn.setPrefHeight(80);
        TranferBtn.setStyle(yelBigBtn);
        TranferBtn.setOnMouseEntered((t) -> {
            TranferBtn.setStyle(yelBigBtnHover);
        });
        TranferBtn.setOnMouseExited((t) -> {
            TranferBtn.setStyle(yelBigBtn);
        });
        Button DepositBtn = new Button("Deposit", new ImageView(Depo));
        DepositBtn.setPrefWidth(200);
        DepositBtn.setPrefHeight(80);
        DepositBtn.setStyle(yelBigBtn);
        DepositBtn.setOnMouseEntered((t) -> {
            DepositBtn.setStyle(yelBigBtnHover);
        });
        DepositBtn.setOnMouseExited((t) -> {
            DepositBtn.setStyle(yelBigBtn);
        });
        Button WidthdrawBtn = new Button("Widthdraw", new ImageView(With));
        WidthdrawBtn.setPrefWidth(200);
        WidthdrawBtn.setPrefHeight(80);
        WidthdrawBtn.setStyle(yelBigBtn);
        WidthdrawBtn.setOnMouseEntered((t) -> {
            WidthdrawBtn.setStyle(yelBigBtnHover);
        });
        WidthdrawBtn.setOnMouseExited((t) -> {
            WidthdrawBtn.setStyle(yelBigBtn);
        });
        Button TransactionBtn = new Button("Show Transaction", new ImageView(Hist));
        TransactionBtn.setPrefWidth(200);
        TransactionBtn.setPrefHeight(80);
        TransactionBtn.setStyle(yelBigBtn);
        TransactionBtn.setOnMouseEntered((t) -> {
            TransactionBtn.setStyle(yelBigBtnHover);
        });
        TransactionBtn.setOnMouseExited((t) -> {
            TransactionBtn.setStyle(yelBigBtn);
        });
        Button editProfileBtn = new Button("Edit Profile");
        editProfileBtn.setStyle(getBlueStyleBtn());
        editProfileBtn.setOnMouseEntered((t) -> {
            editProfileBtn.setStyle(getStyleBtnHover());
        });
        editProfileBtn.setOnMouseExited((t) -> {
            editProfileBtn.setStyle(getBlueStyleBtn());
        });
        Button bankRegisBtn = new Button("Add");
        bankRegisBtn.setStyle(getBlueStyleBtn());
        bankRegisBtn.setOnMouseEntered((t) -> {
            bankRegisBtn.setStyle(getStyleBtnHover());
        });
        bankRegisBtn.setOnMouseExited((t) -> {
            bankRegisBtn.setStyle(getBlueStyleBtn());
        });
        bankRegisBtn.setOnAction((t) -> {
            TypeBank = 'r';
            window.setScene(bankRegister);
            System.out.println("Bank register prease.");
        });

        Button bankEditBtn = new Button("Edit");
        bankEditBtn.setStyle(getBlueStyleBtn());
        bankEditBtn.setOnMouseEntered((t) -> {
            bankEditBtn.setStyle(getStyleBtnHover());
        });
        bankEditBtn.setOnMouseExited((t) -> {
            bankEditBtn.setStyle(getBlueStyleBtn());
        });
        bankEditBtn.setOnAction((t) -> {
            try {
                if (AcBankId == -1) {
                    throw new Exception("Prease selected bank.");
                }
                TypeBank = 'e';
                bankIdFiled.setText(acDataList.get(AccId).getBank().get(AcBankId).getBankId());
                bankNameIdFiled.setText(acDataList.get(AccId).getBank().get(AcBankId).getNameIdBank());
                window.setScene(bankRegister);
                System.out.println("Bank edit prease.");
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
        });

        Button ExitBtn = new Button("Logout");
        ExitBtn.setStyle(getRedStyleBtn());
        ExitBtn.setOnMouseEntered((t) -> {
            ExitBtn.setStyle(getStyleBtnHover());
        });
        ExitBtn.setOnMouseExited((t) -> {
            ExitBtn.setStyle(getRedStyleBtn());
        });
        Button deleteBankBtn = new Button("Delete");
        deleteBankBtn.setStyle(getRedStyleBtn());
        deleteBankBtn.setOnMouseEntered((t) -> {
            deleteBankBtn.setStyle(getStyleBtnHover());
        });
        deleteBankBtn.setOnMouseExited((t) -> {
            deleteBankBtn.setStyle(getRedStyleBtn());
        });
        deleteBankBtn.setOnAction((t) -> {

            try {
                if (AcBankId == -1) {
                    throw new Exception("Please select your bank.");
                }
                if (acDataList.get(AccId).getBank().get(AcBankId).getBalance() > 0.0) {
                    throw new Exception("Please clear your balance");
                }
                if (informationBox.confirmAlert("Confirm", "Are you sure?", logo, background)) {
                    acDataList.get(AccId).getBank().remove(AcBankId);
                    for (int i = 0; i < acDataList.get(AccId).getBank().size(); i++) {
                        acDataList.get(AccId).getBank().get(i).setId(i + 1);
                    }
                    acDataList = Data.updateFile(Data.f, acDataList);

                    cbBox.getItems().clear();
                    for (Bank bank : acDataList.get(AccId).getBank()) {
                        cbBox.getItems().add(bank);
                    }

                    //FINANCE-CENTER
                    Text WelcomeTx = new Text("WELCOME TO O+ SYSTEM.");
                    WelcomeTx.setStyle(welcomeTx);
                    Text balanceText = new Text("Please select your bank to show balance.");
                    balanceText.setStyle(nameTxColor2big);
                    HBox DeWi = new HBox(25);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(25);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("Please choose your options.");
                    Options.setStyle(blueTxColor3);
                    Options.setTextFill(Color.BLACK);
                    HBox bankOptionBar = new HBox(15);
                    bankOptionBar.setAlignment(Pos.CENTER);
                    bankOptionBar.getChildren().setAll(cbBox, bankRegisBtn, bankEditBtn, deleteBankBtn);
                    VBox CENTER = new VBox(25);
                    CENTER.getChildren().addAll(WelcomeTx, balanceText, bankOptionBar, Options, DeWi, Trans);
                    CENTER.setAlignment(Pos.CENTER);

                    AcBankId = -1;
                    INFO.setCenter(CENTER);
                    window.setScene(option);

                }

            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Del bank Prease.");

        });
        TranferBtn.setOnAction((t) -> {
            if (AcBankId == -1) {
                informationBox.displayAlertBox("Error", "Select your bank.", logo, background);
            } else {
                Type = 't';
                window.setScene(tranfer);
            }
        });
        ExitBtn.setOnAction((t) -> {
            try {
                acDataList = Data.updateFile(Data.f, acDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            INFO.getChildren().clear();
            AccId = -1;
            tfToAcc = -1;
            AcBankId = -1;
            tfToAccBank = -1;
            window.setScene(login);
            System.out.println("Submit Press.");
        });
        ConditionsBtn.setOnAction((t) -> {
            informationBox.displayConditions(logo, background);
            System.out.println("Conditions Press.");
        });
        TransactionBtn.setOnAction((t) -> {
            if (AcBankId == -1) {
                informationBox.displayAlertBox("Error", "Select your bank.", logo, background);
            } else {
                informationBox.displayTransactionBox(acDataList.get(AccId).getBank().get(AcBankId), logo, background);
            }
            System.out.println("TraTransaction Press.");
        });
        DepositBtn.setOnAction((t) -> {
            if (AcBankId == -1) {
                informationBox.displayAlertBox("Error", "Select your bank.", logo, background);
            } else {
                Type = 'd';
                tsLabel.setText("Deposit");
                window.setScene(makeTransaction);
                System.out.println(AcBankId + acDataList.get(AccId).getBank().get(AcBankId).getBalance());
                acDataList.get(AccId).getBank().get(AcBankId).getBalance();
            }
            System.out.println("Deposit Press.");
        });
        WidthdrawBtn.setOnAction((t) -> {
            if (AcBankId == -1) {
                informationBox.displayAlertBox("Error", "Select your bank.", logo, background);
            } else {
                Type = 'w';
                tsLabel.setText("Withdraw");
                window.setScene(makeTransaction);
            }
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
            Text EditTx = new Text("Edit profile");
            EditTx.setFill(Color.WHITE);
            EditTx.setStyle("-fx-font-size : 20px;");
            HBox EditTop = new HBox(15);
            EditTop.getChildren().addAll(getImageView(logo), EditTx);
            EditTop.setAlignment(Pos.CENTER);
            editProfilebox.getChildren().setAll(EditTop, importPicbtn, userR2, usernameField3,
                    nameTag, realnameTextField2,
                    surnameTag, surnameField2,
                    gender2,
                    RegisChoice2);
            window.setScene(editProfile);
            System.out.println("Edit profile Press.");
        });

        cbBox.setOnAction((t) -> {
            AcBankId = cbBox.getValue().getId() - 1;

            //FINANCE-CENTER
            Text WelcomeTx = new Text("WELCOME TO O+ SYSTEM.");
            WelcomeTx.setStyle(welcomeTx);
            Text balanceText = new Text("Your balance : " + acDataList.get(AccId).getBank().
                    get(AcBankId).getBalance() + "  " + "Baht");
            balanceText.setStyle(nameTxColor2big);
            HBox DeWi = new HBox(25);
            DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
            DeWi.setAlignment(Pos.CENTER);
            HBox Trans = new HBox(25);
            Trans.getChildren().addAll(TranferBtn, TransactionBtn);
            Trans.setAlignment(Pos.CENTER);
            Label Options = new Label("Please choose your options.");
            Options.setStyle(blueTxColor3);
            Options.setTextFill(Color.BLACK);
            HBox bankOptionBar = new HBox(15);
            bankOptionBar.setAlignment(Pos.CENTER);
            bankOptionBar.getChildren().setAll(cbBox, bankRegisBtn, bankEditBtn, deleteBankBtn);
            VBox CENTER = new VBox(25);
            CENTER.getChildren().addAll(WelcomeTx, balanceText, bankOptionBar, Options, DeWi, Trans);

            CENTER.setAlignment(Pos.CENTER);

            //DECISSION-BOTTOM
            HBox decission = new HBox(25);
            decission.getChildren().addAll(ExitBtn, editProfileBtn, ConditionsBtn);
            decission.setTranslateX(165);
            decission.setTranslateY(-25);

            INFO.setCenter(CENTER);
            INFO.setBottom(decission);

            window.setScene(option);

            System.out.println("Combo box Prease. " + cbBox.getValue());
            System.out.println(cbBox.getValue().getId() + " " + cbBox.getValue().getBalance());
        });
        //Layout Scene Option

        //Layout Scene forgot Password
        Text fgPass = new Text("Forgot password");
        fgPass.setFill(Color.WHITE);
        fgPass.setStyle("-fx-font-size: 20px;");
        TextField ansField = new TextField();
        ansField.setMaxWidth(300);
        ansField.setStyle(BorderText);
        PasswordField FGpassField = new PasswordField();
        FGpassField.setMaxWidth(300);
        FGpassField.setStyle(BorderText);
        PasswordField cfFGpassField = new PasswordField();
        cfFGpassField.setMaxWidth(300);
        cfFGpassField.setStyle(BorderText);

        Button summitPassBtn = new Button("Submit");
        summitPassBtn.setStyle(getGrnStyleBtn());
        summitPassBtn.setOnMouseEntered((t) -> {
            summitPassBtn.setStyle(getStyleBtnHover());
        });
        summitPassBtn.setOnMouseExited((t) -> {
            summitPassBtn.setStyle(getGrnStyleBtn());
        });
        summitPassBtn.setOnAction((t) -> {
            try {
                acDataList.get(AccId).setPassword(ansField.getText(),
                        FGpassField.getText(), cfFGpassField.getText(), 0);
                acDataList = Data.updateFile(Data.f, acDataList);

                window.setScene(login);
                AccId = -1;
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Summit please.");
        });
        Button cancelPassBtn = new Button("Cancel");
        cancelPassBtn.setStyle(getRedStyleBtn());
        cancelPassBtn.setOnMouseEntered((t) -> {
            cancelPassBtn.setStyle(getStyleBtnHover());
        });
        cancelPassBtn.setOnMouseExited((t) -> {
            cancelPassBtn.setStyle(getRedStyleBtn());
        });
        cancelPassBtn.setOnAction((t) -> {
            window.setScene(login);
            System.out.println("Cancel please.");
        });
        HBox disistionFGPassBtn = new HBox(15);
        disistionFGPassBtn.getChildren().addAll(summitPassBtn, cancelPassBtn);
        disistionFGPassBtn.setAlignment(Pos.CENTER);
        //Layout Scene forgot Password

        //Layout Scene CFTransation
        CFTransactionbox.setAlignment(Pos.CENTER);
        Text TransactionText = new Text();
        TransactionText.setStyle(blueTxColor2);
        Text RandomText = new Text();
        RandomText.setStyle(blueTxColor2);
        TextField CFTextField = new TextField();
        CFTextField.setMaxWidth(300);
        CFTextField.setStyle(BorderText);
        HBox solve = new HBox(10);
        solve.setAlignment(Pos.CENTER);
        Button CFTranferBtn = new Button("Confirm");
        CFTranferBtn.setStyle(grnBgColor);
        CFTranferBtn.setOnMouseEntered((t) -> {
            CFTranferBtn.setStyle(HoverY);
        });
        CFTranferBtn.setOnMouseExited((t) -> {
            CFTranferBtn.setStyle(grnBgColor);
        });
        Button CancelTranferBtn = new Button("Cancel");
        CancelTranferBtn.setStyle(redBgColor);
        CancelTranferBtn.setOnMouseEntered((t) -> {
            CancelTranferBtn.setStyle(HoverY);
        });
        CancelTranferBtn.setOnMouseExited((t) -> {
            CancelTranferBtn.setStyle(redBgColor);
        });
        CFTranferBtn.setOnAction((t) -> {
            try {
                if (r1 + r2 == Integer.parseInt(CFTextField.getText())) {
                    if (Type == 't') {
                        acDataList = Account.tranfer(AccId, AcBankId, tfToAcc, tfToAccBank, amount);
                    } else if (Type == 'd' || Type == 'w') {
                        acDataList.get(AccId).getBank().get(AcBankId).makeTransaction(Type, amount);

                    }
                    acDataList = Data.updateFile(Data.f, acDataList);
                    
                    System.out.println("Amount : " + amount);

                    //FINANCE-CENTER
                    Text WelcomeTx = new Text("WELCOME TO O+ SYSTEM.");
                    WelcomeTx.setStyle(welcomeTx);
                    Text balanceText = new Text("Your balance : " + acDataList.get(AccId).getBank().
                            get(AcBankId).getBalance() + "  " + "Baht");
                    balanceText.setStyle(nameTxColor2big);
                    HBox DeWi = new HBox(25);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(25);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("Please choose your options.");
                    Options.setStyle(blueTxColor3);
                    Options.setTextFill(Color.BLACK);
                    HBox bankOptionBar = new HBox(15);
                    bankOptionBar.setAlignment(Pos.CENTER);
                    bankOptionBar.getChildren().setAll(cbBox, bankRegisBtn, bankEditBtn, deleteBankBtn);
                    VBox CENTER = new VBox(25);
                    CENTER.getChildren().addAll(WelcomeTx, balanceText, bankOptionBar, Options, DeWi, Trans);

                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, editProfileBtn, ConditionsBtn);
                    decission.setTranslateX(165);
                    decission.setTranslateY(-25);

                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);

                    Type = 'n';
                    window.setScene(option);
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
            window.setScene(option);
            System.out.println("Cancel at confirm Transaction.");

        });
        //LayOut Scene CFTransation

        //Layout Scene Deposit/Withdraw
        Text DWtx = new Text();
        DWtx.setStyle("-fx-font-size: 20px;");
        DWtx.setFill(Color.WHITE);
        Text TSamountText = new Text("Amount : ");
        TSamountText.setStyle(blueTxColor2);
        TextField TSamountField = new TextField();
        TSamountField.setStyle(BorderText);
        TSamountField.setMaxWidth(300);
//        Text TSaccountText = new Text("Tranfer to (name) : ");
//        TextField TSaccountField = new TextField();
        Button TSconfirmBtn = new Button("Confirm");
        TSconfirmBtn.setStyle(getGrnStyleBtn());
        TSconfirmBtn.setOnMouseEntered((t) -> {
            TSconfirmBtn.setStyle(getStyleBtnHover());
        });
        TSconfirmBtn.setOnMouseExited((t) -> {
            TSconfirmBtn.setStyle(getGrnStyleBtn());
        });
        TSconfirmBtn.setOnAction((t) -> {

            try {
                if (acDataList.get(AccId).getBank().get(AcBankId).getBalance() < Integer.parseInt(TSamountField.getText())
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
                        TransactionText.setStyle(amountblueTxColor2);
                        DWtx.setText("Deposit");
                        break;
                    case 'w':
                        TransactionText.setText("You want to widthdraw amount : " + amount + "  " + "baht");
                        TransactionText.setStyle(amountblueTxColor2);
                        DWtx.setText("Widthdraw");
                        break;
                    default:
                        throw new Exception("input d : deposit\ninput w : withdraw");
                }
                Text TransactionText2 = new Text("Amount : ");
                TransactionText2.setStyle(amountblueTxColor2);

                Random random = new Random();
                r1 = random.nextInt(20);
                r2 = random.nextInt(20);
                RandomText.setText(r1 + " + " + r2 + " = (Please fill answer below.)");
                RandomText.setStyle(amountblueTxColor2);

                solve.getChildren().setAll(TransactionText2, RandomText);
                HBox DWTop = new HBox(15);
                DWTop.getChildren().addAll(getImageView(logo), DWtx);
                DWTop.setAlignment(Pos.CENTER);

                CFTransactionbox.getChildren().setAll(DWTop, TransactionText, solve,
                        CFTextField, CFTranferBtn, CancelTranferBtn);
                window.setScene(CFTransactionScene);
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
        TScancelBtn.setStyle(getRedStyleBtn());
        TScancelBtn.setOnMouseEntered((t) -> {
            TScancelBtn.setStyle(getStyleBtnHover());
        });
        TScancelBtn.setOnMouseExited((t) -> {
            TScancelBtn.setStyle(getRedStyleBtn());
        });
        TScancelBtn.setOnAction((t) -> {
            Type = 'n';
            window.setScene(option);
            System.out.println("Cancel press.");
        });
        HBox Topfinance = new HBox(15);
        Topfinance.getChildren().addAll(getImageView(logo), tsLabel);
        Topfinance.setAlignment(Pos.CENTER);
        TSbox.setAlignment(Pos.CENTER);
        Topfinance.setTranslateY(-15);
        Text WarningTS = new Text("--- Maximum amount is 20,000 Baht. ---");
        WarningTS.setStyle(nameTxColor4);
        TSbox.getChildren().addAll(Topfinance, WarningTS, TSamountText, TSamountField,
                TSconfirmBtn, TScancelBtn);
        //Layout Scene Deposit/Withdraw

        //Layout Scene Tranfer  
        Text amountText = new Text("Amount : ");
        amountText.setStyle(blueTxColor2);
        TextField amountField = new TextField();
        amountField.setMaxWidth(300);
        amountField.setStyle(BorderText);
        Text accountText = new Text("Tranfer to (Bank id) : ");
        accountText.setStyle(blueTxColor2);
        Text selecttfBank = new Text("Select bank : ");
        selecttfBank.setStyle(blueTxColor2);
        TextField accountField = new TextField();
        accountField.setMaxWidth(300);
        accountField.setStyle(BorderText);
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle(getGrnStyleBtn());
        confirmBtn.setOnMouseEntered((t) -> {
            confirmBtn.setStyle(getStyleBtnHover());
        });
        confirmBtn.setOnMouseExited((t) -> {
            confirmBtn.setStyle(getGrnStyleBtn());
        });
        confirmBtn.setOnAction((t) -> {
            try {
                if (acDataList.get(AccId).getBank().get(AcBankId).getBalance()
                        >= Double.parseDouble(amountField.getText())) {
                    tfToAcc = -1;
                    for (Account account : acDataList) {
                        for (Bank bank : account.getBank()) {
                            if (bank.getBankId().equals(accountField.getText())
                                    && bank.getNameBank().equals(cbTfBox.getValue())) {
                                tfToAcc = account.getId() - 1;
                                tfToAccBank = bank.getId() - 1;
                                if (AcBankId == tfToAccBank && AccId == AcBankId) {
                                    throw new Exception("You tranfer to same bank.");
                                }

                                Random random = new Random();
                                r1 = random.nextInt(20);
                                r2 = random.nextInt(20);
                                RandomText.setText(r1 + " + " + r2 + " = (Please fill answer below.)");
                                RandomText.setStyle(amountblueTxColor2);
                                amount = Double.parseDouble(amountField.getText());
                                //Text
                                TransactionText.setText(Data.getMessageTranfer(AccId, AcBankId, tfToAcc, tfToAccBank));
                                TransactionText.setStyle(amountblueTxColor2);
                                Text TransactionText2 = new Text("Amount : ");
                                TransactionText2.setStyle(amountblueTxColor2);

                                solve.getChildren().setAll(TransactionText2, RandomText);
                                Text TranferTx = new Text("Tranfer");
                                TranferTx.setFill(Color.WHITE);
                                TranferTx.setStyle("-fx-font-size:20px;");
                                HBox trantop = new HBox(15);
                                trantop.getChildren().addAll(getImageView(logo), TranferTx);
                                trantop.setAlignment(Pos.CENTER);
                                CFTransactionbox.getChildren().setAll(trantop, TransactionText, solve,
                                        CFTextField, CFTranferBtn, CancelTranferBtn);

                                window.setScene(CFTransactionScene);
                                break;
                            }
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
        cancelBtn.setStyle(getRedStyleBtn());
        cancelBtn.setOnMouseEntered((t) -> {
            cancelBtn.setStyle(getStyleBtnHover());
        });
        cancelBtn.setOnMouseExited((t) -> {
            cancelBtn.setStyle(getRedStyleBtn());
        });
        cancelBtn.setOnAction((t) -> {
            window.setScene(option);
            System.out.println("Cancel press.");
        });
        HBox tfToDicisBox = new HBox(15);
        tfToDicisBox.setAlignment(Pos.CENTER);
        Label tran = new Label("Transfer / Loan");
        tran.setStyle("-fx-font-size:20px;");
        tran.setTextFill(Color.WHITE);
        HBox transferTop = new HBox(15);
        transferTop.getChildren().addAll(getImageView(logo), tran);
        transferTop.setAlignment(Pos.CENTER);
        Text Charge = new Text("Loaning between a differnt banks will charge you at 10 baht.");
        Charge.setStyle(nameTxColor4);
        Text warn = new Text("Please make sure that you put a correct username.");
        warn.setStyle(nameTxColor4);

        tfToDicisBox.getChildren().addAll(confirmBtn, cancelBtn);
        TFbox.getChildren().addAll(transferTop, Charge, warn, selecttfBank, cbTfBox,
                accountText, accountField, amountText, amountField, tfToDicisBox);
        TFbox.setAlignment(Pos.CENTER);
        //Layout Scene Tranfer  

        //Layout Scene Login
        Label labell3 = new Label("WELCOME TO O PLUS SERVICE\n             Please sign in.");
        labell3.setMinSize(50, 0);
        labell3.setScaleX(1.5);
        labell3.setScaleY(1.5);
        labell3.setStyle("-fx-font-size:16px;");

        labell3.setStyle(blueTxColor);
        labell3.setAlignment(Pos.TOP_CENTER);

        Label labell4 = new Label("Don't have an account. Try now?");
        labell4.setScaleX(1);
        labell4.setScaleY(1);
        labell4.setStyle("-fx-font-size:14px;");
        labell4.setTextFill(Color.RED);
        labell4.setAlignment(Pos.CENTER);

        Text idTopic = new Text("Username : ");
        idTopic.setFill(Color.WHITE);
        idTopic.setStyle("-fx-font-size:15px;");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        usernameField.setAlignment(Pos.CENTER);
        usernameField.setStyle(BorderText);
        Text passTopic = new Text("Password : ");
        passTopic.setFill(Color.WHITE);
        passTopic.setStyle("-fx-font-size:15px;");
        TextField passField = new PasswordField();
        passField.setMaxWidth(300);
        passField.setAlignment(Pos.CENTER);
        passField.setStyle(BorderText);
        Button LIBtn = new Button("Login");
        LIBtn.setStyle(getGrnStyleBtn());
        LIBtn.setOnMouseEntered((t) -> {
            LIBtn.setStyle(getStyleBtnHover());
        });
        LIBtn.setOnMouseExited((t) -> {
            LIBtn.setStyle(getGrnStyleBtn());
        });
        LIBtn.setOnAction((var t) -> {
            for (Account account1 : acDataList) {
                String thisUser = usernameField.getText(), thisPass = passField.getText();
                String chkUser = account1.getName(), chkPass = account1.getPassword();
                if (thisUser.equals(chkUser) && thisPass.equals(chkPass)) {
                    AccId = account1.getId() - 1;
                    System.out.println("Math! : " + account1.getId());
                    window.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    userText.setStyle(nameTxColor4);

                    Text Fullname = new Text("Name : " + account1.getRealName() + "  " + account1.getSurname() + "  " + "Gender : " + " " + account1.getGender());
                    Fullname.setStyle(nameTxColor4);

                    cbBox.getItems().clear();
                    for (Bank bank : acDataList.get(AccId).getBank()) {
                        cbBox.getItems().add(bank);
                    }

                    //INFO-TOP
                    HBox nameBalance = new HBox(20);
                    nameBalance.getChildren().addAll(userText);
                    VBox userInfo = new VBox(12);
                    userInfo.getChildren().addAll(nameBalance, Fullname);
                    profileImage = getImageView(userimage);
                    try {
                        if (!acDataList.get(AccId).getPictureFile().canRead()) {
                            profileImage = getImageView(userimage);
                        } else {
                            profileImage = getProfile(new Image(acDataList.get(AccId).getPictureFile().toURI().toString()));
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    HBox TOP = new HBox(40);
                    TOP.getChildren().addAll(profileImage, userInfo);
                    TOP.setTranslateX(45);
                    TOP.setTranslateY(10);

                    //FINANCE-CENTER
                    Text WelcomeTx = new Text("WELCOME TO O+ SYSTEM.");
                    WelcomeTx.setStyle(welcomeTx);
                    Text balanceText = new Text("Please select your bank to show balance.");
                    balanceText.setStyle(nameTxColor2big);
                    HBox DeWi = new HBox(25);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(25);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("Please choose your options.");
                    Options.setStyle(blueTxColor3);
                    Options.setTextFill(Color.BLACK);
                    HBox bankOptionBar = new HBox(15);
                    bankOptionBar.setAlignment(Pos.CENTER);
                    bankOptionBar.getChildren().setAll(cbBox, bankRegisBtn, bankEditBtn, deleteBankBtn);
                    VBox CENTER = new VBox(25);
                    CENTER.getChildren().addAll(WelcomeTx, balanceText, bankOptionBar, Options, DeWi, Trans);

                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, editProfileBtn, ConditionsBtn);
                    decission.setTranslateX(165);
                    decission.setTranslateY(-25);

                    INFO.setTop(TOP);
                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);

                    break;
                }
            }
            if (AccId == -1) //If didn't have id in account list.
            {
                informationBox.displayAlertBox("O+ O PLUS", "Wrong username or password.", logo, background);
            }
            usernameField.clear();
            passField.clear();
            System.out.println("Login Press.\n");
        });
        Button RGBtn = new Button("Register");
        RGBtn.setStyle(getRedStyleBtn());
        RGBtn.setOnMouseEntered((t) -> {
            RGBtn.setStyle(getStyleBtnHover());
        });
        RGBtn.setOnMouseExited((t) -> {
            RGBtn.setStyle(getRedStyleBtn());
        });
        RGBtn.setOnAction((ActionEvent t) -> {
            window.setScene(register);
            usernameField.clear();
            passField.clear();
            System.out.println("Register Press.");
        });
        Button FGPBtn = new Button("Forgot Password.");
        FGPBtn.setStyle(getYelStyleBtn());
        FGPBtn.setOnMouseEntered((t) -> {
            FGPBtn.setStyle(getStyleBtnHover());
        });
        FGPBtn.setOnMouseExited((t) -> {
            FGPBtn.setStyle(getYelStyleBtn());
        });
        FGPBtn.setOnAction((t) -> {
            AccId = Data.findData(usernameField.getText(), acDataList);
            if (AccId >= 0) {
                FGPbox.getChildren().clear();
                Text PassQThint = new Text("Question : " + acDataList.get(AccId).getQTPassHint());
                PassQThint.setStyle(blueTxColor2);
                Text answer2 = new Text("Answer :");
                answer2.setStyle(blueTxColor2);
                Text pass1 = new Text("New-Password : ");
                pass1.setStyle(blueTxColor2);
                Text cfpass1 = new Text("Confirm New-Password : ");
                cfpass1.setStyle(blueTxColor2);
                window.setScene(forgotPassword);
                HBox Forgottop = new HBox(15);
                Forgottop.getChildren().addAll(getImageView(logo), fgPass);
                Forgottop.setAlignment(Pos.CENTER);
                FGPbox.getChildren().addAll(Forgottop, PassQThint,
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
                passField, LIFGBtn, labell4, RGBtn, Banks);
        //Layout Scene Login

        //Layout Scene Register 
        Text RegisTx = new Text("Register");
        RegisTx.setStyle("-fx-font-size: 20px;");
        RegisTx.setFill(Color.WHITE);
        Text usernameR = new Text("Username : ");
        usernameR.setStyle(blueTxColor2);
        Text userDeal = new Text("(user must not be the same)");
        userDeal.setFill(Color.RED);
        HBox userR = new HBox(3);
        userR.getChildren().addAll(usernameR, userDeal);
        userR.setAlignment(Pos.CENTER);
        Text passwordR = new Text("Password : ");
        passwordR.setStyle(blueTxColor2);
        Text passDeal = new Text("(must be between 4-16 digits)");
        passDeal.setFill(Color.RED);
        HBox passR = new HBox(3);
        passR.getChildren().addAll(passwordR, passDeal);
        passR.setAlignment(Pos.CENTER);
        Text name = new Text("Name : ");
        name.setStyle(blueTxColor2);
        Text surname = new Text("Surname : ");
        surname.setStyle(blueTxColor2);
        Text question = new Text("Question : ");
        question.setStyle(blueTxColor2);
        Text answer = new Text("Answer : ");
        answer.setStyle(blueTxColor2);

        TextField usernameField2 = new TextField();
        usernameField2.setMaxWidth(300);
        usernameField2.setStyle(BorderText);
        PasswordField passField2 = new PasswordField();
        passField2.setMaxWidth(300);
        passField2.setStyle(BorderText);
        TextField realnameTextField = new TextField();
        realnameTextField.setMaxWidth(300);
        realnameTextField.setStyle(BorderText);
        TextField surnameField = new TextField();
        surnameField.setMaxWidth(300);
        surnameField.setStyle(BorderText);
        TextField qtPassHintField = new TextField();
        qtPassHintField.setMaxWidth(300);
        qtPassHintField.setStyle(BorderText);
        TextField ansPassHintField = new TextField();
        ansPassHintField.setMaxWidth(300);
        ansPassHintField.setStyle(BorderText);

        CheckBox mGender = new CheckBox("Male");
        mGender.setStyle(blueTxColor);
        CheckBox fmGender = new CheckBox("Female");
        fmGender.setStyle(blueTxColor);
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
        SMBtn.setStyle(getGrnStyleBtn());
        SMBtn.setOnMouseEntered((t) -> {
            SMBtn.setStyle(getStyleBtnHover());
        });
        SMBtn.setOnMouseExited((t) -> {
            SMBtn.setStyle(getGrnStyleBtn());
        });
        Button CancelBtn = new Button("Cancel");
        CancelBtn.setStyle(getRedStyleBtn());
        CancelBtn.setOnMouseEntered((t) -> {
            CancelBtn.setStyle(getStyleBtnHover());
        });
        CancelBtn.setOnMouseExited((t) -> {
            CancelBtn.setStyle(getRedStyleBtn());
        });
        SMBtn.setOnAction((ActionEvent t) -> {

            char Gender = 'n';
            try {
                if (usernameField2.getText().isBlank()) {
                    throw new Exception("Please field username.");
                }
                if (passField2.getText().isBlank()) {
                    throw new Exception("Please field password.");
                }
                if (realnameTextField.getText().isBlank()) {
                    throw new Exception("Please field name.");
                }
                if (surnameField.getText().isBlank()) {
                    throw new Exception("Please field surname.");
                }
                if (mGender.isSelected() == false && fmGender.isSelected() == false) {
                    throw new Exception("Please select gender.");
                }
                if (qtPassHintField.getText().isBlank()) {
                    throw new Exception("Please field question.");
                }
                if (ansPassHintField.getText().isBlank()) {
                    throw new Exception("Please field answer.");
                }

                acDataList = Data.readFile(Data.f);
                for (Account account : acDataList) {
                    if (usernameField2.getText().equals(account.getName())) {
                        throw new Exception("This username is already.");
                    }
                }

                if (mGender.isSelected() == true) {
                    Gender = 'm';
                } else {
                    Gender = 'f';
                }
                acDataList.add(new Account(usernameField2.getText(), passField2.getText(),
                        acDataList.size() + 1,
                        realnameTextField.getText(),
                        surnameField.getText(),
                        Gender,
                        qtPassHintField.getText(),
                        ansPassHintField.getText()));
                acDataList = Data.updateFile(Data.f, acDataList);
                Gender = 'n';
                window.setScene(login);
                usernameField2.clear();
                passField2.clear();
                realnameTextField.clear();
                surnameField.clear();
                mGender.setSelected(false);
                fmGender.setSelected(false);
                qtPassHintField.clear();
                ansPassHintField.clear();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo, background);
            }
            System.out.println("Submit Press.");
        });
        CancelBtn.setOnAction((t) -> {
            window.setScene(login);
            System.out.println("Cancel Press.");
        });
        RGbox.setAlignment(Pos.CENTER);
        HBox RegisChoice = new HBox(15);
        RegisChoice.getChildren().addAll(SMBtn, CancelBtn);
        RegisChoice.setAlignment(Pos.CENTER);
        HBox Registop = new HBox(15);
        Registop.getChildren().addAll(getImageView(logo), RegisTx);
        Registop.setAlignment(Pos.CENTER);
        RGbox.getChildren().addAll(Registop, userR, usernameField2,
                passR, passField2,
                name, realnameTextField,
                surname, surnameField,
                gender,
                question, qtPassHintField,
                answer, ansPassHintField,
                RegisChoice);
        //Layout Scene Register 

        login = new Scene(LIbox, stageWidth, stageHeight);
        register = new Scene(RGbox, stageWidth, regisHeight);
        option = new Scene(INFO, stageWidth, stageHeight);
        tranfer = new Scene(TFbox, stageWidth, stageHeight);
        fixPassword = new Scene(FPbox, stageWidth, stageHeight);
        forgotPassword = new Scene(FGPbox, stageWidth, stageHeight);
        makeTransaction = new Scene(TSbox, stageWidth, stageHeight);
        CFTransactionScene = new Scene(CFTransactionbox, stageWidth, stageHeight);
        editProfile = new Scene(editProfilebox, stageWidth, stageHeight);
        bankRegister = new Scene(bankRGbox, stageWidth, stageHeight);

        window.setScene(login);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        //System.out.println(Data.readFile(Data.f).);
        launch(args);
    }
}
