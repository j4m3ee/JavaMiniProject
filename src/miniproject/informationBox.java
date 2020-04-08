/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class informationBox {
    
    public static void displayAlertBox(String title, String message,Image logo){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);
        
        Label label = new Label(message);
        Label lebel2 = new Label("Please try again.");
        VBox layout = new VBox(10);
        
        Button closeBT = new Button("OK");
        closeBT.setOnAction(e -> window.close());
        
        layout.getChildren().addAll(label,lebel2, closeBT);
        layout.setStyle("-fx-background-color: rgb(255,154,162);");
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public static void displayTransactionBox(Account tr,Image logo){
        Stage window = new Stage();
        String s = "";
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Transaction List.");
        
        Label label = new Label();
        VBox layout = new VBox(10);
        
        label.setText("Name : " + tr.getName() + " Id<" + tr.getId() + ">");
        
        Button closeBT = new Button("OK");
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
        layout.getChildren().addAll(label,trPane,closeBT);
        layout.setStyle("-fx-background-color: rgb(226,240,203);");
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout,300,300);
        window.getIcons().add(logo);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
