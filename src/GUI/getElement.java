package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class getElement {
    
    final static public String WiTxColor2 = "-fx-fill: linear-gradient(#f12711, #f5af19);\n" + "    -fx-font-size: 14px;\n";
    final static public String DeTxColor2 = "-fx-fill: linear-gradient(#FDFC47, #24FE41);\n" + "    -fx-font-size: 14px;\n";
    final static public String borderColor2 = "-fx-border-color: #ffdd00;\n" + "-fx-border-width : 2px;\n" + "-fx-background-color: linear-gradient(#41295a,#2F0743);\n";
    final static public String borderColor = "-fx-border-color: #00ffff;\n" + "-fx-border-width : 2px;\n";
    final static public String amountblueTxColor2 = "-fx-fill: linear-gradient(#00c3ff, #ffff1c);\n" + "    -fx-font-size: 17px;\n";
    final static public String nameTxColor4 = "-fx-fill: linear-gradient(#40e0d0, #ff8c00, #ff0080);\n" + "    -fx-font-size: 19px;\n";
    final static public String welcomeTx = "-fx-fill: linear-gradient(#00c3ff, #ffff1c);\n" + "    -fx-font-size: 23px;\n";
    final static public String nameTxColor2big = "-fx-fill: linear-gradient(#ee0979, #ff6a00);\n" + "    -fx-font-size: 18px;\n";
    final static public String nameTxColor2 = "-fx-fill: linear-gradient(#ee0979, #ff6a00);\n" + "    -fx-font-size: 14px;\n";
    final static public String nameTxColor1 = "-fx-fill: linear-gradient(#40e0d0, #ff8c00, #ff0080);\n" + "    -fx-font-size: 15px;\n";
    final static public String blueTxColor3 = "-fx-text-fill: linear-gradient(#00c3ff, #ffff1c);\n" + "    -fx-font-size: 19px;\n";
    final static public String blueTxColor2 = "-fx-fill: linear-gradient(#00c3ff, #ffff1c);\n" + "    -fx-font-size: 14px;\n";
    final static public String redTexColor = "-fx-text-fill: linear-gradient(#f12711, #f5af19);\n";
    final static public String blueTxColor = "-fx-text-fill: linear-gradient(#00c3ff, #ffff1c);\n";
    final static public String bgColor = "-fx-background-color: rgb(181,234,215);";
    final static public String redBgColor = "-fx-background-color: linear-gradient(#ff5400, #ffdd00);\n";
    final static public String grnBgColor = "-fx-background-color: linear-gradient(#59ff00, #ffdd00);\n";
    final static public String yelBgColor = "-fx-background-color: linear-gradient(#00e699, #ffdd00);\n";
    final static public String purpBgColor = "-fx-background-color: linear-gradient(#00ff80, #ffdd00);\n";
    final static public String blueBgColor = "-fx-background-color: linear-gradient(#00ffff, #ffdd00);\n";
    final static public String HoverY = "-fx-background-color: linear-gradient(#ffdd00, #ffdd00);\n";
    final static public String bgRad = "    -fx-background-radius: 30;\n";
    final static public String bgIns = "    -fx-background-radius: 30;\n";
    final static public String whtTextFill = "    -fx-text-fill: black;";
    final static public String yelBigBtn = "-fx-background-color: \n"
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

    final static public String yelBigBtnHover = "-fx-background-color: \n"
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

    final static public String BorderText = "-fx-border-color: linear-gradient(#00ffff, #ffdd00); \n"
            + "-fx-background-radius: 15;\n"
            + "-fx-border-radius: 15;\n"
            + "-fx-border-width:5; \n"
            + "-fx-background-color: rgba(255,255,255,0.5); \n";

    public static MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Edit");
        MenuItem menuItem1 = new MenuItem("Admin");
        //MenuItem menuItem2 = new MenuItem("Item 2");
        menu.getItems().add(menuItem1);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    public static Button getGreenButton(String name) {
        Button btn = new Button(name);
        return btn;
    }

    public static String getBlueStyleBtn() {
        return blueBgColor + bgRad + bgIns + whtTextFill;
    }

    public static String getRedStyleBtn() {
        return redBgColor + bgRad + bgIns + whtTextFill;
    }

    public static String getGrnStyleBtn() {
        return grnBgColor + bgRad + bgIns + whtTextFill;

    }

    public static String getYelStyleBtn() {
        return yelBgColor + bgRad + bgIns + whtTextFill;
    }

    public static String getPurpleStyleBtn() {
        return purpBgColor + bgRad + bgIns + whtTextFill;
    }

    public static String getStyleBtnHover() {
        return HoverY + bgRad + bgIns + whtTextFill;
    }

    public static ImageView getImageView(Image logo) {
        ImageView LOGO = new ImageView(logo);
        LOGO.setFitHeight(60);
        LOGO.setFitWidth(60);
        LOGO.setPreserveRatio(true);
        return LOGO;
    }

    public static ImageView getProfile(Image logo) {
        ImageView LOGO = new ImageView(logo);
        //logo.get
        LOGO.setFitWidth(80);
        LOGO.setFitHeight(80);
        LOGO.setPreserveRatio(true);
        return LOGO;
    }
    
    public static ImageView getLogoView1(Image KBANK) {
        ImageView kbank = new ImageView(KBANK);
        kbank.setFitHeight(60);
        kbank.setFitWidth(60);
        kbank.setPreserveRatio(true);
        return kbank;
    }    
    
    public static ImageView getLogoView2(Image SCB) {
        ImageView scb = new ImageView(SCB);
        scb.setFitHeight(60);
        scb.setFitWidth(60);
        scb.setPreserveRatio(true);
        return scb;
    }
    
    public static ImageView getLogoView3(Image UOB) {
        ImageView uob = new ImageView(UOB);
        uob.setFitHeight(60);
        uob.setFitWidth(60);
        uob.setPreserveRatio(true);
        return uob;
    }
    
    public static ImageView getLogoView4(Image KTB) {
        ImageView ktb = new ImageView(KTB);
        ktb.setFitHeight(60);
        ktb.setFitWidth(60);
        ktb.setPreserveRatio(true);
        return ktb;
    }
    
    public static ImageView getLogoView5(Image GSB) {
        ImageView gsb = new ImageView(GSB);
        gsb.setFitHeight(60);
        gsb.setFitWidth(60);
        gsb.setPreserveRatio(true);
        return gsb;
    }
    
    public static ImageView getLogoView6(Image TMB) {
        ImageView tmb = new ImageView(TMB);
        tmb.setFitHeight(60);
        tmb.setFitWidth(60);
        tmb.setPreserveRatio(true);
        return tmb;
    }
}
