package GUI;

import static GUI.getElement.*;
import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniproject.Account;
import miniproject.Bank;
import miniproject.Transaction;

/**
 *
 * @author User
 */
public class informationBox {

    static boolean ans;
    final static public String pathPic = "resource\\Pictures\\";
    static int id = -1;

    public static void displayAlertBox(String title, String message, Image logo, Background background) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(200);

        Label label = new Label(message);
        Label label2 = new Label("Please try again.");
        VBox layout = new VBox(20);
        label = setStyleElement.setStyleLabel(label, redTexColor);
        label2 = setStyleElement.setStyleLabel(label2, redTexColor);

        Button closeBT = new Button("OK");
        closeBT.setStyle(getGrnStyleBtn());
        closeBT.setOnMouseEntered((t) -> {
            closeBT.setStyle(getStyleBtnHover());
        });
        closeBT.setOnMouseExited((t) -> {
            closeBT.setStyle(getGrnStyleBtn());
        });
        closeBT.setOnAction(e -> window.close());

        layout.getChildren().addAll(label, label2, closeBT);
        layout.setBackground(background);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayTransactionBox(Bank tr, Image logo, Background background) {
        Stage window = new Stage();
        String s = "";

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Transaction List.");

        Label label = new Label();
        VBox layout = new VBox(10);

        label.setText("Name : " + tr.getNameIdBank()+ " Id<" + tr.getId() + ">");
        label.setMinSize(50, 0);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.setStyle("-fx-font-size:16px;");
        label.setStyle(blueTxColor);

        Button closeBT = new Button("OK");
        closeBT.setStyle(getGrnStyleBtn());
        closeBT.setOnMouseEntered((t) -> {
            closeBT.setStyle(getStyleBtnHover());
        });
        closeBT.setOnMouseExited((t) -> {
            closeBT.setStyle(getGrnStyleBtn());
        });
        closeBT.setOnAction(e -> window.close());
        
        TilePane trPane2 = new TilePane();
        ScrollPane trPane = new ScrollPane();
        for (Transaction trList : tr.getTr()) {
            Text Date2 = new Text(" " + trList.getDate() + "\n");
            Date2.setStyle(blueTxColor2);
            Text Type2 = new Text(" " + trList.getType() + "\n");
            Text Amount2 = new Text(" " + trList.getAmount() + "\n");
            Amount2.setStyle(blueTxColor2);
            Text Balance2 = new Text(" " + trList.getBalance() + "\n");
            Balance2.setStyle(blueTxColor2);
            Text Des2 = new Text(" " + trList.getDescription() + "\n" + "---------------------------------------------------------------------" + "\n");
            Des2.setStyle(blueTxColor2);
            
            if(trList.getType() == "withdraw"){
                Type2.setStyle(WiTxColor2);
            }
            else if(trList.getType() == "deposit"){
                Type2.setStyle(DeTxColor2);
            }
            
            Text Date = new Text("Date :");
            Date.setStyle(blueTxColor2);
            Text Type = new Text("Type :");
            Type.setStyle(blueTxColor2);
            Text Amount = new Text("Amount :");
            Amount.setStyle(blueTxColor2);
            Text Balance = new Text("Current balance :");
            Balance.setStyle(blueTxColor2);
            Text Description = new Text("Description :");
            Description.setStyle(blueTxColor2);
        
            HBox DATE = new HBox();
            DATE.getChildren().addAll(Date, Date2);
            HBox TYPE = new HBox();
            TYPE.getChildren().addAll(Type, Type2);
            HBox AMOUNT = new HBox();
            AMOUNT.getChildren().addAll(Amount, Amount2);
            HBox BALANCE = new HBox();
            BALANCE.getChildren().addAll(Balance, Balance2);
            HBox DES = new HBox();
            DES.getChildren().addAll(Description, Des2);
            
            VBox TRAN = new VBox();
            TRAN.getChildren().addAll(DATE, TYPE, AMOUNT, BALANCE, DES);
//            s += "Date : " + trList.getDate() + "\n";
//            s += "Type : " + trList.getType() + "\n";
//            s += "Amount : " + trList.getAmount() + "\n";
//            s += "Balance : " + trList.getBalance() + "\n";
//            s += "Description : " + trList.getDescription() + "\n";
//            s += "---------------------------------------------------------------------";
//            s += "\n";
            trPane2.getChildren().addAll(TRAN);
            trPane2.setStyle(borderColor2);
        }
        
//        Label labelContent = new Label(s);
        trPane.setContent(trPane2);
        trPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        trPane.setPannable(true);
        trPane.setFitToWidth(true);
        trPane.setPrefSize(300, 500);
        trPane.setStyle(borderColor);
        layout.getChildren().addAll(getImageView(logo),label, trPane, closeBT);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        Scene scene = new Scene(layout, 350, 650);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayConditions(Image logo, Background background) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("O-PLUS Agreement");

        Label label = new Label();
        VBox layout = new VBox(10);
        label.setText("O-PLUS Agreement (Important!!)");
        label.setMinSize(50, 0);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.setStyle("-fx-font-size:16px;");
        label.setStyle(blueTxColor);

        Button closeBT = new Button("Close");
        closeBT.setStyle(getGrnStyleBtn());
        closeBT.setOnMouseEntered((t) -> {
            closeBT.setStyle(getStyleBtnHover());
        });
        closeBT.setOnMouseExited((t) -> {
            closeBT.setStyle(getGrnStyleBtn());
        });
        closeBT.setOnAction(e -> window.close());

        ScrollPane cdPane = new ScrollPane();
        Text labelContent = new Text(""
                + "                                    WELCOME TO O-PLUS SERVICE. PLEASE READ.\n"
                + "1.When user start O-PLUS sevice.User will achieve 500 baht.\n"
                + "2.Maximum amount you can deposit is 20,000 baht/time.\n"
                + "3.Maximum amount you can withdraw is 20,000 baht/time.\n"
                + "4.If user loan to the different banks. user will be charged for 10 baht.\n"
                + "5.Before delete an account, transfer money to another account first.\n"               
                + "6.Before delete a user, user must be clear a balance in every bank.\n"
                + "7.User should follow the rules strictly.\n "
                + "                                                     --THANK YOU--");
//        labelContent.setAlignment(Pos.CENTER);
        labelContent.setStyle(borderColor);
        cdPane.setContent(labelContent);
        cdPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        cdPane.setPannable(true);
        cdPane.setStyle(borderColor2);
        layout.getChildren().addAll(getImageView(logo), label, cdPane, closeBT);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        Scene scene = new Scene(layout, 450, 350);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static boolean confirmAlert(String title, String message, Image logo, Background background) {
        try {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setResizable(false);
            window.getIcons().add(logo);

            Label label1 = new Label();
            label1.setText(message);
            label1 = setStyleElement.setStyleLabel(label1, blueTxColor);

            Button yesBtn = new Button("Confirm");
            yesBtn.setStyle(getGrnStyleBtn());
            yesBtn.setOnMouseEntered((t) -> {
                yesBtn.setStyle(getStyleBtnHover());
            });
            yesBtn.setOnMouseExited((t) -> {
                yesBtn.setStyle(getGrnStyleBtn());
            });
            yesBtn.setOnAction(e -> {
                ans = true;
                window.close();
            });

            Button noBtn = new Button("Cancel");
            noBtn.setStyle(getRedStyleBtn());
            noBtn.setOnMouseEntered((t) -> {
                noBtn.setStyle(getStyleBtnHover());
            });
            noBtn.setOnMouseExited((t) -> {
                noBtn.setStyle(getRedStyleBtn());
            });
            noBtn.setOnAction(e -> {
                ans = false;
                window.close();
            });

            HBox row1 = new HBox(20);
            row1.getChildren().addAll(yesBtn, noBtn);
            row1.setAlignment(Pos.CENTER);

            VBox layout1 = new VBox(20);
            layout1.getChildren().addAll(label1, row1);
            layout1.setAlignment(Pos.CENTER);
            layout1.setBackground(background);

            Scene scene = new Scene(layout1, 350, 100);

            window.setScene(scene);
            window.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AlertBox : Exeption in confirmAlert");
        }

        return ans;
    }

}
