package GUI;

import static GUI.informationBox.blueTxColor;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class setStyleElement {
    
    public static Text setStyleText(Text tx,int size,Color color,Color color2){
        tx.setStyle("-fx-font-size:"+size+"px;");
        tx.setFill(color);
        tx.setStroke(color2);
        tx.setStrokeWidth(0.8);
        tx.setFont(Font.font("Cascadia",FontWeight.BOLD,10));
        return tx;
    }
    
    public static Label setStyleLabel(Label label,String style){
        label.setMinSize(50, 0);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.setStyle("-fx-font-size:16px;");
        label.setStyle(style);
        return label;
    }
    
    
    
}
