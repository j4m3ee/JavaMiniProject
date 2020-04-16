package miniproject;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MiniProject extends Application {

    String pathPic = "resource\\Pictures\\";
    static File f = new File("resource\\Data\\Accout.dat"); //Data
    int AccId = -1, tfToAcc = -1;
    double amount = 0.0;
    Scene login, option, tranfer, register, fixPassword, forgotPassword, makeTransaction, CFTransactionScene;
    ArrayList<Account> acDataList = new ArrayList<>();
    char Type = 'n', Gender = 'n';

    int r1 = 0, r2 = 0;

    final String bgColor = "-fx-background-color: rgb(181,234,215);";
    final String redBgColor = "-fx-background-color: linear-gradient(#ff5400, #ffdd00);\n";
    final String grnBgColor = "-fx-background-color: linear-gradient(#59ff00, #ffdd00);\n";
    final String yelBgColor = "-fx-background-color: linear-gradient(#00e699, #ffdd00);\n";
    final String purpBgColor = "-fx-background-color: linear-gradient(#00ff80, #ffdd00);\n";
    final String blueBgColor = "-fx-background-color: linear-gradient(#00ffff, #ffdd00);\n";
    final String HoverY = "-fx-background-color: linear-gradient(#ffdd00, #ffdd00);\n";
    final String bgRad = "    -fx-background-radius: 30;\n";
    final String bgIns = "    -fx-background-radius: 30;\n";
    final String whtTextFill = "    -fx-text-fill: black;";
    final String yelBigBtn = "-fx-background-color: \n"
            + "        linear-gradient(#ffd65b, #e68400),\n"
            + "        linear-gradient(#ffef84, #f2ba44),\n"
            
            + "        linear-gradient(#ffea6a, #efaa22),\n"
            + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
            + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 30;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #654b00;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-padding: 10 20 10 20;";
    
    final String yelBigBtnHover = "-fx-background-color: \n"
            + "        linear-gradient(#ffd65b, #e68400),\n"
            + "        linear-gradient(#ffef84, #f2ba44),\n"
            
            + "        linear-gradient(#ffea6a, #efaa22),\n"
            + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
            + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
            + "    -fx-background-radius: 40;\n"
            + "    -fx-background-insets: 0,1,2,3,0;\n"
            + "    -fx-text-fill: #654b00;\n"
            + "    -fx-font-weight: bold;\n"
            + "    -fx-font-size: 16px;\n"
            + "    -fx-padding: 10 20 10 20;";

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
        FileInputStream input = new FileInputStream(pathPic + "Background.jpg");
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
            acDataList = Data.readFile(f);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            acDataList = Data.readBackupData();
            acDataList = Data.updateFile(f, acDataList);
        }

        //All Pane Layout
        VBox LIbox = new VBox(15);//Login
        VBox OTbox = new VBox(15);//User
        VBox RGbox = new VBox(15);//Register
        VBox TFbox = new VBox(15);//Tranfer
        VBox FPbox = new VBox(15);//fix Passwordas
        VBox FGPbox = new VBox(15);//forgot password
        VBox TSbox = new VBox(15);//Make transaction (deposit/withdraw) 
        VBox CFTransactionbox = new VBox(15);//Before tranfer confirm transection
        

        LIbox.setBackground(background);
//        LIbox.setStyle(bgColor);
        RGbox.setBackground(background);
        TFbox.setBackground(background);
        FPbox.setBackground(background);
        FGPbox.setBackground(background);
        TSbox.setBackground(background);
        CFTransactionbox.setBackground(background);

        BorderPane INFO = new BorderPane();
        INFO.setBackground(background);

        //Layout Scene fixPassword
        Text oldPassText = new Text("Enter old password.");
        TextField oldPassTextField = new PasswordField();
        oldPassTextField.setMaxWidth(300);
        Text newPassText = new Text("Enter new password.");
        TextField newPassTextField = new PasswordField();
        newPassTextField.setMaxWidth(300);
        Text CFnewPassText = new Text("Re-new password.");
        TextField CFnewPassTextField = new PasswordField();
        CFnewPassTextField.setMaxWidth(300);
        Button SMFixPassBtn = new Button("Submit");
        SMFixPassBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        SMFixPassBtn.setOnMouseEntered((t) -> {SMFixPassBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        SMFixPassBtn.setOnMouseExited((t) -> {SMFixPassBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
        SMFixPassBtn.setOnAction((var t) -> {
            try {
                acDataList.get(AccId).setPassword(oldPassTextField.getText(),
                        newPassTextField.getText(), CFnewPassTextField.getText());
                acDataList = Data.updateFile(f, acDataList);
                stage.setScene(option);
                System.out.println("Submit Press.");
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }
        });
        Button CancelFixPassBtn = new Button("Cancel");
        CancelFixPassBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        CancelFixPassBtn.setOnMouseEntered((t) -> {CancelFixPassBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        CancelFixPassBtn.setOnMouseExited((t) -> {CancelFixPassBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
        CancelFixPassBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel Press.");
        });
        FPbox.getChildren().addAll(getImageView(logo), oldPassText, oldPassTextField, newPassText,
                newPassTextField, CFnewPassText, CFnewPassTextField, SMFixPassBtn,
                CancelFixPassBtn);
        FPbox.setAlignment(Pos.CENTER);
        //Layout Scene fixPassword

        //Layout Scene Option
        Button ConditionsBtn = new Button("Account Conditions");
        ConditionsBtn.setStyle(purpBgColor + bgRad + bgIns + whtTextFill);
        ConditionsBtn.setOnMouseEntered((t) -> {ConditionsBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill); });
        ConditionsBtn.setOnMouseExited((t) -> {ConditionsBtn.setStyle(purpBgColor + bgRad + bgIns + whtTextFill); });
        Button TranferBtn = new Button("Transfer", new ImageView(Tran));
        TranferBtn.setPrefWidth(200);
        TranferBtn.setPrefHeight(80);
        TranferBtn.setStyle(yelBigBtn);
        TranferBtn.setOnMouseEntered((t) -> {TranferBtn.setStyle(yelBigBtnHover);});
        TranferBtn.setOnMouseExited((t) -> {TranferBtn.setStyle(yelBigBtn);});
        Button DepositBtn = new Button("Deposit", new ImageView(Depo));
        DepositBtn.setPrefWidth(200);
        DepositBtn.setPrefHeight(80);
        DepositBtn.setStyle(yelBigBtn);
        DepositBtn.setOnMouseEntered((t) -> {DepositBtn.setStyle(yelBigBtnHover);});
        DepositBtn.setOnMouseExited((t) -> {DepositBtn.setStyle(yelBigBtn);});
        Button WidthdrawBtn = new Button("Widthdraw", new ImageView(With));
        WidthdrawBtn.setPrefWidth(200);
        WidthdrawBtn.setPrefHeight(80);
        WidthdrawBtn.setStyle(yelBigBtn);
        WidthdrawBtn.setOnMouseEntered((t) -> {WidthdrawBtn.setStyle(yelBigBtnHover);});
        WidthdrawBtn.setOnMouseExited((t) -> {WidthdrawBtn.setStyle(yelBigBtn);});
        Button TransactionBtn = new Button("Show Transaction", new ImageView(Hist));
        TransactionBtn.setPrefWidth(200);
        TransactionBtn.setPrefHeight(80);
        TransactionBtn.setStyle(yelBigBtn);
        TransactionBtn.setOnMouseEntered((t) -> {TransactionBtn.setStyle(yelBigBtnHover);});
        TransactionBtn.setOnMouseExited((t) -> {TransactionBtn.setStyle(yelBigBtn);});
        Button fixPassBtn = new Button("Change Password");
        fixPassBtn.setStyle(blueBgColor + bgRad + bgIns + whtTextFill);
        fixPassBtn.setOnMouseEntered((t) -> {fixPassBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        fixPassBtn.setOnMouseExited((t) -> {fixPassBtn.setStyle(blueBgColor + bgRad + bgIns + whtTextFill);});
        Button ExitBtn = new Button("Logout");
        ExitBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        ExitBtn.setOnMouseEntered((t) -> {ExitBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        ExitBtn.setOnMouseExited((t) -> {ExitBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
        TranferBtn.setOnAction((t) -> {
            Type = 't';
            stage.setScene(tranfer);
        });
        ExitBtn.setOnAction((t) -> {
            try {
                acDataList = Data.updateFile(f, acDataList);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
            INFO.getChildren().clear();
            AccId = -1;
            stage.setScene(login);
            System.out.println("Submit Press.");
        });
        ConditionsBtn.setOnAction((t) -> {
            informationBox.displayConditions(logo);
            System.out.println("Conditions Press.");
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
        //Layout Scene Option

        //Layout Scene forgot Password
        TextField ansField = new TextField();
        ansField.setMaxWidth(300);
        PasswordField FGpassField = new PasswordField();
        FGpassField.setMaxWidth(300);
        PasswordField cfFGpassField = new PasswordField();
        cfFGpassField.setMaxWidth(300);

        Button summitPassBtn = new Button("Submit");
        summitPassBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        summitPassBtn.setOnMouseEntered((t) -> {summitPassBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        summitPassBtn.setOnMouseExited((t) -> {summitPassBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
        summitPassBtn.setOnAction((t) -> {
            try {
                acDataList.get(AccId).setPassword(ansField.getText(),
                        FGpassField.getText(), cfFGpassField.getText(), 0);
                acDataList = Data.updateFile(f, acDataList);

                stage.setScene(login);
                AccId = -1;
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }
            System.out.println("Summit please.");
        });
        Button cancelPassBtn = new Button("Cancel");
        cancelPassBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        cancelPassBtn.setOnMouseEntered((t) -> {cancelPassBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        cancelPassBtn.setOnMouseExited((t) -> {cancelPassBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
        cancelPassBtn.setOnAction((t) -> {
            stage.setScene(login);
            System.out.println("Cancel please.");
        });
        //Layout Scene forgot Password

        //Layout Scene CFTransation
        CFTransactionbox.setAlignment(Pos.CENTER);
        Text TransactionText = new Text();
        TransactionText.setStyle("-fx-font-size:15px;");
        //TransactionText = setStyleElement.setStyleText(TransactionText, 15, Color.BLACK, Color.WHITE);
        Text RandomText = new Text();
        RandomText.setStyle("-fx-font-size:15px;");
        //RandomText = setStyleElement.setStyleText(RandomText, 15, Color.BLACK, Color.WHITE);
        TextField CFTextField = new TextField();
        CFTextField.setMaxWidth(300);
        HBox solve = new HBox(10);
        solve.setAlignment(Pos.CENTER);
        Button CFTranferBtn = new Button("Confirm");
        CFTranferBtn.setStyle(grnBgColor);
        CFTranferBtn.setOnMouseEntered((t) -> {CFTranferBtn.setStyle(HoverY);});
        CFTranferBtn.setOnMouseExited((t) -> {CFTranferBtn.setStyle(grnBgColor);});
        Button CancelTranferBtn = new Button("Cancel");
        CancelTranferBtn.setStyle(redBgColor);
        CancelTranferBtn.setOnMouseEntered((t) -> {CancelTranferBtn.setStyle(HoverY);});
        CancelTranferBtn.setOnMouseExited((t) -> {CancelTranferBtn.setStyle(redBgColor);});
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
                        acDataList = Data.updateFile(f, acDataList);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                    }
                    INFO.getChildren().clear();
                    Text userText = new Text("Username : " + acDataList.get(AccId).getName());
                    userText.setStyle("-fx-font-size:15px;");
                    userText.setFill(Color.WHITE);
                    userText.setStroke(Color.YELLOW);
                    Text balanceText = new Text("Balance : " + acDataList.get(AccId).getBalance() + "  " + "Baht");
                    balanceText.setStyle("-fx-font-size:15px;");
                    balanceText.setFill(Color.WHITE);
                    balanceText.setStroke(Color.YELLOW);
                    Text Fullname = new Text("Name : " + acDataList.get(AccId).getRealName() + "  " + acDataList.get(AccId).getSurname() + "  " +"Gender : " + "  " + acDataList.get(AccId).getGender());
                    Fullname.setStyle("-fx-font-size:15px;");
                    Fullname.setFill(Color.WHITE);
                    Fullname.setStroke(Color.YELLOW);

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

                    //FINANCE-CENTER
                    HBox DeWi = new HBox(15);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(15);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("       Welcome to system.\n Please choose your options.");
                    Options.setStyle("-fx-font-size:18px;");
                    Options.setTextFill(Color.BLACK);
                    VBox CENTER = new VBox(20);
                    CENTER.getChildren().addAll(Options, DeWi, Trans);
                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, fixPassBtn, ConditionsBtn);
                    decission.setTranslateX(135);
                    decission.setTranslateY(-10);

                    INFO.setTop(TOP);
                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);

                    Type = 'n';
//                            INFO.getChildren().addAll(userText, balanceText, TranferBtn, TransactionBtn, fixPassBtn, ExitBtn);
                    stage.setScene(option);
                } else {
                    throw new Exception("Your answer is wrong!");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input number.", logo);
            } catch (Exception ex) {
                System.out.println(ex);
                informationBox.displayAlertBox("Error", ex.getMessage(), logo);
            }

        });

        CancelTranferBtn.setOnAction((t) -> {
            stage.setScene(option);
            System.out.println("Cancel at confirm Transaction.");

        });
        //LayOut Scene CFTransation

        //Layout Scene Deposit/Withdraw
        Text TSamountText = new Text("Amount : ");
        TSamountText.setStyle("-fx-font-size:15px;");
        TextField TSamountField = new TextField();
        TSamountField.setMaxWidth(300);
//        Text TSaccountText = new Text("Tranfer to (name) : ");
//        TextField TSaccountField = new TextField();
        Button TSconfirmBtn = new Button("Confirm");
        TSconfirmBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        TSconfirmBtn.setOnMouseEntered((t) -> {TSconfirmBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        TSconfirmBtn.setOnMouseExited((t) -> {TSconfirmBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
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
                        TransactionText.setStyle("-fx-font-size:15px;");
                        break;
                    case 'w':
                        TransactionText.setText("You want to deposit amount : " + amount + "  " + "baht");
                        TransactionText.setStyle("-fx-font-size:15px;");
                        break;
                    default:
                        throw new Exception("input d : deposit\ninput w : withdraw");
                }
                Text TransactionText2 = new Text("Amount : ");
                TransactionText2.setStyle("-fx-font-size:15px;");

                Random random = new Random();
                r1 = random.nextInt(100);
                r2 = random.nextInt(100);
                RandomText.setText(r1 + " + " + r2 + " = (Please fill answer below.)");
                
                solve.getChildren().setAll(TransactionText2, RandomText);

                CFTransactionbox.getChildren().setAll(getImageView(logo),TransactionText,solve ,
                        CFTextField, CFTranferBtn, CancelTranferBtn);
                stage.setScene(CFTransactionScene);
                System.out.println("Confirm press.");
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Plese input amount be number.");
                System.out.println(numberFormatException);
                informationBox.displayAlertBox("Error", "Plese input amount be number.", logo);
            } catch (Exception e) {
                System.out.println(e);
                informationBox.displayAlertBox("Error", e.getMessage(), logo);
            }

        });
        Button TScancelBtn = new Button("Cancel");
        TScancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        TScancelBtn.setOnMouseEntered((t) -> {TScancelBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        TScancelBtn.setOnMouseExited((t) -> {TScancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
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
        WarningTS.setStyle("-fx-font-size:14px;");
        WarningTS.setFill(Color.WHITE);
        WarningTS.setStroke(Color.DARKRED);
        WarningTS.setStrokeWidth(0.85);
        TSbox.getChildren().addAll(Topfinance, WarningTS, TSamountText, TSamountField,
                TSconfirmBtn, TScancelBtn);
        //Layout Scene Deposit/Withdraw

        //Layout Scene Tranfer  
        Text amountText = new Text("Amount : ");
        TextField amountField = new TextField();
        amountField.setMaxWidth(300);
        Text accountText = new Text("Tranfer to (name) : ");
        TextField accountField = new TextField();
        accountField.setMaxWidth(300);
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        confirmBtn.setOnMouseEntered((t) -> {confirmBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        confirmBtn.setOnMouseExited((t) -> {confirmBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
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
                            RandomText.setStyle("-fx-font-size:15px;");
                            amount = Double.parseDouble(amountField.getText());
                            //Text
                            TransactionText.setText(acDataList.get(AccId).getName() + " tranfers to "
                                    + acDataList.get(tfToAcc).getName());
                            TransactionText.setStyle("-fx-font-size:15px;");
                            System.out.println("Acc : " + AccId);
                            System.out.println("tf : " + tfToAcc);
                            Text TransactionText2 = new Text("Amount : ");
                            TransactionText2.setStyle("-fx-font-size:15px;");
                            
                            solve.getChildren().setAll(TransactionText2, RandomText);

                            CFTransactionbox.getChildren().setAll(TransactionText, solve,
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
        cancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        cancelBtn.setOnMouseEntered((t) -> {cancelBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        cancelBtn.setOnMouseExited((t) -> {cancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});     
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
        warn.setStyle("-fx-font-size:14px;");
        warn.setFill(Color.WHITE);
        warn.setStroke(Color.DARKRED);
        warn.setStrokeWidth(0.85);
        TFbox.getChildren().addAll(transferTop, warn, accountText, accountField, amountText, amountField, confirmBtn, cancelBtn);
        TFbox.setAlignment(Pos.CENTER);
        //Layout Scene Tranfer  

        //Layout Scene Login
        Label labell3 = new Label("WELCOME TO O PLUS SERVICE\n             Please sign in.");
        labell3.setMinSize(50, 0);
        labell3.setScaleX(1.5);
        labell3.setScaleY(1.5);
        labell3.setStyle("-fx-font-size:16px;");
        labell3.setAlignment(Pos.TOP_CENTER);

        Label labell4 = new Label("Don't have an account. Try now?");
        labell4.setScaleX(1);
        labell4.setScaleY(1);
        labell4.setStyle("-fx-font-size:15px;");
        labell4.setTextFill(Color.RED);
        labell4.setAlignment(Pos.CENTER);

        Text idTopic = new Text("Username : ");
        idTopic = setStyleElement.setStyleText(idTopic, 20, Color.BLACK, Color.WHITE);
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        Text passTopic = new Text("Password : ");
        passTopic = setStyleElement.setStyleText(passTopic, 20, Color.BLACK, Color.WHITE);
        TextField passField = new PasswordField();
        passField.setMaxWidth(300);
        Button LIBtn = new Button("Login");
        LIBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        LIBtn.setOnMouseEntered((t) -> {LIBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        LIBtn.setOnMouseExited((t) -> {LIBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
        LIBtn.setOnAction((var t) -> {
            for (Account account1 : acDataList) {
                String thisUser = usernameField.getText(), thisPass = passField.getText();
                String chkUser = account1.getName(), chkPass = account1.getPassword();
                if (thisUser.equals(chkUser) && thisPass.equals(chkPass)) {
                    AccId = account1.getId() - 1;
                    System.out.println("Math! : " + account1.getId());
                    stage.setScene(option);
                    Text userText = new Text("Username : " + account1.getName());
                    userText.setStyle("-fx-font-size:15px;");
                    userText.setFill(Color.WHITE);
                    userText.setStroke(Color.YELLOW);
                    Text balanceText = new Text("Balance : " + account1.getBalance() + "  " + "Baht");
                    balanceText.setStyle("-fx-font-size:15px;");
                    balanceText.setFill(Color.WHITE);
                    balanceText.setStroke(Color.YELLOW);
                    Text Fullname = new Text("Name : " + account1.getRealName() + "  " + account1.getSurname() + "  " + "Gender : " + " " + account1.getGender());
                    Fullname.setStyle("-fx-font-size:15px;");
                    Fullname.setFill(Color.WHITE);
                    Fullname.setStroke(Color.YELLOW);

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

                    //FINANCE-CENTER
                    HBox DeWi = new HBox(15);
                    DeWi.getChildren().addAll(DepositBtn, WidthdrawBtn);
                    DeWi.setAlignment(Pos.CENTER);
                    HBox Trans = new HBox(15);
                    Trans.getChildren().addAll(TranferBtn, TransactionBtn);
                    Trans.setAlignment(Pos.CENTER);
                    Label Options = new Label("       Welcome to system.\n Please choose your options.");
                    Options.setStyle("-fx-font-size:18px;");
                    Options.setTextFill(Color.BLACK);
                    VBox CENTER = new VBox(20);
                    CENTER.getChildren().addAll(Options, DeWi, Trans);
                    CENTER.setAlignment(Pos.CENTER);

                    //DECISSION-BOTTOM
                    HBox decission = new HBox(25);
                    decission.getChildren().addAll(ExitBtn, fixPassBtn, ConditionsBtn);
                    decission.setTranslateX(135);
                    decission.setTranslateY(-10);

                    INFO.setTop(TOP);
                    INFO.setCenter(CENTER);
                    INFO.setBottom(decission);
//                    OTbox.getChildren().addAll(userText, balanceText, TranferBtn,
//                            TransactionBtn, fixPassBtn, ExitBtn);
                    System.out.println(account1.getGender());
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
        RGBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        RGBtn.setOnMouseEntered((t) -> {RGBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        RGBtn.setOnMouseExited((t) -> {RGBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
        RGBtn.setOnAction((ActionEvent t) -> {
            stage.setScene(register);
            System.out.println("Register Press.");
        });
        Button ChkListBtn = new Button("Check list");
        ChkListBtn.setOnAction((ActionEvent t) -> {
            showList(acDataList);
            System.out.println("Size : " + acDataList.size());
        });
        Button FGPBtn = new Button("Forgot Password.");
        FGPBtn.setStyle(yelBgColor + bgRad + bgIns + whtTextFill);
        FGPBtn.setOnMouseEntered((t) -> {FGPBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        FGPBtn.setOnMouseExited((t) -> {FGPBtn.setStyle(yelBgColor + bgRad + bgIns + whtTextFill);});
        FGPBtn.setOnAction((t) -> {
            AccId = Data.findData(usernameField.getText(), acDataList);
            if (AccId >= 0) {
                FGPbox.getChildren().clear();
                Text PassQThint = new Text("Question : " + acDataList.get(AccId).getQTPassHint());
                stage.setScene(forgotPassword);
                FGPbox.getChildren().addAll(PassQThint,
                        new Text("Answer :"), ansField,
                        new Text("New-Password : "), FGpassField,
                        new Text("Confirm New-Password : "), cfFGpassField,
                        summitPassBtn, cancelPassBtn);
                FGPbox.setAlignment(Pos.CENTER);
            } else {
                informationBox.displayAlertBox("Error", "Wrong username.", logo);
            }

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
        Text userDeal = new Text("(user must not be the same)");
        userDeal.setFill(Color.RED);
        HBox userR = new HBox(3);
        userR.getChildren().addAll(usernameR, userDeal);
        userR.setAlignment(Pos.CENTER);
        Text passwordR = new Text("Password : ");
        Text passDeal = new Text("(must be between 4-16 digits)");
        passDeal.setFill(Color.DARKRED);
        HBox passR = new HBox(3);
        passR.getChildren().addAll(passwordR, passDeal);
        passR.setAlignment(Pos.CENTER);

        TextField usernameField2 = new TextField();
        usernameField2.setMaxWidth(300);
        PasswordField passField2 = new PasswordField();
        passField2.setMaxWidth(300);
        TextField realnameTextField = new TextField();
        realnameTextField.setMaxWidth(300);
        TextField surnameField = new TextField();
        surnameField.setMaxWidth(300);
        TextField qtPassHintField = new TextField();
        qtPassHintField.setMaxWidth(300);
        TextField ansPassHintField = new TextField();
        ansPassHintField.setMaxWidth(300);

        CheckBox mGender = new CheckBox("Male");
        CheckBox fmGender = new CheckBox("Female");
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
        SMBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        SMBtn.setOnMouseEntered((t) -> {SMBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        SMBtn.setOnMouseExited((t) -> {SMBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);});
        Button CancelBtn = new Button("Cancel");
        CancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        CancelBtn.setOnMouseEntered((t) -> {CancelBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);});
        CancelBtn.setOnMouseExited((t) -> {CancelBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);});
        SMBtn.setOnAction((ActionEvent t) -> {

            ArrayList<Account> addDataList = new ArrayList<>();
            try {
                if (mGender.isSelected() == false && fmGender.isSelected() == false) {
                    throw new Exception("Please select gender.");
                }
                if (mGender.isSelected() == true) {
                    Gender = 'M';
                } else {
                    Gender = 'F';
                }
                addDataList = Data.readFile(f);
                addDataList.add(new Account(usernameField2.getText(), passField2.getText(),
                        addDataList.size() + 1,
                        realnameTextField.getText(),
                        surnameField.getText(),
                        Gender,
                        qtPassHintField.getText(),
                        ansPassHintField.getText()));
                Data.writeFile(f, addDataList);
                acDataList = Data.readFile(f);
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
        HBox RegisChoice = new HBox(15);
        RegisChoice.getChildren().addAll(SMBtn, CancelBtn);
        RegisChoice.setAlignment(Pos.CENTER);
        RGbox.getChildren().addAll(userR, usernameField2,
                passR, passField2,
                new Text("Name : "), realnameTextField,
                new Text("Surname : "), surnameField,
                gender,
                new Text("Question : "), qtPassHintField,
                new Text("Answer :"), ansPassHintField,
                RegisChoice);
        //Layout Scene Register 

        login = new Scene(LIbox, 650, 500);
        register = new Scene(RGbox, 600, 600);
        option = new Scene(INFO, 600, 450);
        tranfer = new Scene(TFbox, 600, 400);
        fixPassword = new Scene(FPbox, 600, 400);
        forgotPassword = new Scene(FGPbox, 600, 400);
        makeTransaction = new Scene(TSbox, 600, 400);
        CFTransactionScene = new Scene(CFTransactionbox, 600, 400);
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
