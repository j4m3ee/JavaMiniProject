package GUI;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniproject.Account;
import miniproject.Data;
import miniproject.Transaction;

/**
 *
 * @author User
 */
public class informationBox {

    static int id = -1;

    static String redBgColor = "-fx-background-color: linear-gradient(#ff5400, #be1d00);\n";
    static String grnBgColor = "-fx-background-color: linear-gradient(#59ff00, #3cbe00);\n";
    static String bgRad = "    -fx-background-radius: 30;\n";
    static String bgIns = "    -fx-background-radius: 30;\n";
    static String whtTextFill = "    -fx-text-fill: white;";
    static String HoverY = "-fx-background-color: linear-gradient(#ffdd00, #ffdd00);\n";
    static String blueBgColor = "-fx-background-color: linear-gradient(#00ffff, #ffdd00);\n";

    public static void displayAlertBox(String title, String message, Image logo) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label = new Label(message);
        Label lebel2 = new Label("Please try again.");
        VBox layout = new VBox(10);

        Button closeBT = new Button("OK");
        closeBT.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
        closeBT.setOnAction(e -> window.close());

        layout.getChildren().addAll(label, lebel2, closeBT);
        layout.setStyle("-fx-background-color: rgb(255,154,162);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayTransactionBox(Account tr, Image logo) {
        Stage window = new Stage();
        String s = "";

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Transaction List.");

        Label label = new Label();
        VBox layout = new VBox(10);

        label.setText("Name : " + tr.getName() + " Id<" + tr.getId() + ">");

        Button closeBT = new Button("OK");
        closeBT.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
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
        layout.getChildren().addAll(label, trPane, closeBT);
        layout.setStyle("-fx-background-color: rgb(226,240,203);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayConditions(Image logo) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Account conditions");

        Label label = new Label();
        VBox layout = new VBox(10);

        label.setText("Account conditions (must read!!)");

        Button closeBT = new Button("Accept");
        closeBT.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
        closeBT.setOnAction(e -> window.close());

        ScrollPane cdPane = new ScrollPane();
        Label labelContent = new Label(""
                + "                                     WELCOME TO O-PLUS SERVICE. PLEASE READ.\n"
                + "1.When user starts O-PlUS sevice.User will achieve 500 baht.\n"
                + "2.In this platform.User can extremly deposit 20000 baht.\n"
                + "3.In this platform.User can extremly widthdraw 20000 baht.\n"
                + "4.If user want to delete and account.User should replace money to another account.\n"
                + "5.User should follow the rules strictly.\n"
                + "                                                     --THANK YOU--");
        labelContent.setAlignment(Pos.CENTER);
        cdPane.setContent(labelContent);
        cdPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        cdPane.setPannable(true);
        layout.getChildren().addAll(label, cdPane, closeBT);
        layout.setStyle("-fx-background-color: rgb(226,240,203);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 450, 350);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }
}
