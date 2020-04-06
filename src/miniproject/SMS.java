package miniproject;

import java.util.Date;

public interface SMS {
    Date date = new Date();
    char[] type = new char[2];
    String text = new String();
    String showSMS(String SMS);
    String reciveSMS(String SMS);
    
    
}
