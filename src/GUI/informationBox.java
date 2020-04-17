package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniproject.Account;
import miniproject.Transaction;

/**
 *
 * @author User
 */
public class informationBox {

    static boolean ans;
    static public String pathPic = "resource\\Pictures\\";
    static int id = -1;
    static String redBgColor = "-fx-background-color: linear-gradient(#ff5400, #be1d00);\n";
    static String grnBgColor = "-fx-background-color: linear-gradient(#59ff00, #3cbe00);\n";
    static String bgRad = "    -fx-background-radius: 30;\n";
    static String bgIns = "    -fx-background-radius: 30;\n";
    static String whtTextFill = "    -fx-text-fill: white;";
    static String redTexColor = "-fx-text-fill: linear-gradient(#f12711, #f5af19);\n";
    static String blueTxColor = "-fx-text-fill: linear-gradient(#00c3ff, #ffff1c);\n";
    static String HoverY = "-fx-background-color: linear-gradient(#ffdd00, #ffdd00);\n";

    public static void displayAlertBox(String title, String message, Image logo, Background background) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(200);

        Label label = new Label(message);
        Label label2 = new Label("Please try again.");
        VBox layout = new VBox(10);
        label = setStyleElement.setStyleLabel(label, redTexColor);
        label2 = setStyleElement.setStyleLabel(label2, redTexColor);

        Button closeBT = new Button("OK");
        closeBT.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
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

    public static void displayTransactionBox(Account tr, Image logo, Background background) {
        Stage window = new Stage();
        String s = "";

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Transaction List.");

        Label label = new Label();
        VBox layout = new VBox(10);

        label.setText("Name : " + tr.getName() + " Id<" + tr.getId() + ">");
        label.setMinSize(50, 0);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.setStyle("-fx-font-size:16px;");
        label.setStyle(blueTxColor);

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
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        Scene scene = new Scene(layout, 300, 300);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayConditions(Image logo, Background background) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Account conditions");

        Label label = new Label();
        VBox layout = new VBox(10);
        label.setText("Account conditions (must read!!)");
        label.setMinSize(50, 0);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.setStyle("-fx-font-size:16px;");
        label.setStyle(blueTxColor);

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
            yesBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
            yesBtn.setOnMouseEntered((t) -> {
                yesBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);
            });
            yesBtn.setOnMouseExited((t) -> {
                yesBtn.setStyle(grnBgColor + bgRad + bgIns + whtTextFill);
            });
            yesBtn.setOnAction(e -> {
                ans = true;
                window.close();
            });

            Button noBtn = new Button("Cancel");
            noBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
            noBtn.setOnMouseEntered((t) -> {
                noBtn.setStyle(HoverY + bgRad + bgIns + whtTextFill);
            });
            noBtn.setOnMouseExited((t) -> {
                noBtn.setStyle(redBgColor + bgRad + bgIns + whtTextFill);
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
