package GUI;

import static GUI.getElement.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
        
        ScrollPane trPane = new ScrollPane();
        for (Transaction trList : tr.getTr()) {
            s += "Date : " + trList.getDate() + "\n";
            s += "Type : " + trList.getType() + "\n";
            s += "Amount : " + trList.getAmount() + "\n";
            s += "Balance : " + trList.getBalance() + "\n";
            s += "Description : " + trList.getDescription() + "\n";
            s += "\n";
        }
        
        Label labelContent = new Label(s);
        trPane.setContent(labelContent);
        trPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        trPane.setPannable(true);
        trPane.setPrefSize(300, 500);
        layout.getChildren().addAll(getImageView(logo),label, trPane, closeBT);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        Scene scene = new Scene(layout, 400, 650);
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
        Label labelContent = new Label(""
                + "                                    WELCOME TO O-PLUS SERVICE. PLEASE READ.\n"
                + "1.When user start O-PLUS sevice.User will achieve 500 baht.\n"
                + "2.Maximum amount you can deposit is 20,000 baht/time.\n"
                + "3.Maximum amount you can withdraw is 20,000 baht/time.\n"
                + "4.If user loan to the different banks. user will be charged for 10 baht.\n"
                + "5.Before delete an account, transfer money to another account first.\n"               
                + "6.Before delete a user, user must be clear a balance in every bank.\n"
                + "7.User should follow the rules strictly.\n "
                + "                                                     --THANK YOU--");
        labelContent.setAlignment(Pos.CENTER);
        cdPane.setContent(labelContent);
        cdPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        cdPane.setPannable(true);
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
