/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;

/**
 *
 * @author ASUS
 */
public class Data {

    public static File f = new File("resource\\Data\\Account.dat");

    public static ArrayList<Account> readFile(File f)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        return (ArrayList<Account>) in.readObject();
    }

    public static void writeFile(File f, ArrayList<Account> acNew)
            throws FileNotFoundException, IOException {
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))) {
            out.writeObject(acNew);
        }
    }

    public static ArrayList<Account> updateFile(File f, ArrayList<Account> acNew)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        writeFile(f, acNew);
        System.out.println("Backup data.");
        backupData(acNew);
        return readFile(f);
    }

    public static void backupData(ArrayList<Account> ac) {
        String fpath = new String("resource\\Data\\backupData.txt");
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");

        for (Account account : ac) {
            sb.append(account.getName() + ":"
                    + account.getPassword() + ":"
                    + account.getId() + ":"
                    + account.getRealName() + ":"
                    + account.getSurname() + ":"
                    + account.getQTPassHint() + ":"
                    + account.getASWPasshint() + ":"
                    + account.getBalance() + ":"
                    + account.getGender());
            sb.append(ls);
        }
        //System.out.println(sb.toString());

        File f = new File(System.getProperty("user.home"), fpath); //object home directory
        String s = sb.toString();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resource\\Data\\backupData.txt"));
            out.write(s);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error backup.");
            System.out.println(ex);
        }

    }

    public static ArrayList<Account> readBackupData() throws Exception {
        String f = new String("resource\\Data\\backupData.txt");
        ArrayList<Account> ac = new ArrayList<>();
        try {
            List<String> Lines = Files.readAllLines(Paths.get(f));
            for (String Line : Lines) {
                String s = Line;
                String[] data = s.split(":");
                Account account = new Account(data[0], data[1],
                        Integer.parseInt(data[2]), data[3], data[4], data[8].charAt(0), data[5], data[6]);
                account.setBalance(Double.parseDouble(data[7]));
                ac.add(account);
            }

        } catch (IOException ex) {
            System.out.println("Error read backup.");
            System.out.println(ex);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error read backup.");
            System.out.println(numberFormatException);
        }
        System.out.println(ac);
        return ac;
    }

    public static int findData(String name, ArrayList<Account> ac) {
        int id = -1;
        for (Account account : ac) {
            if (account.getName().equals(name)) {
                id = account.getId() - 1;
                break;
            }
        }
        return id;
    }

    public static File UploadPic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Picture");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Picture Files", "*.jpg","*.png","*.gif"));
        String defaultSaveName = "mySave";
        fileChooser.setInitialFileName(defaultSaveName); //set the default name for file to be saved
        File selectedFile = fileChooser.showOpenDialog(null);
        

        return selectedFile.getAbsoluteFile();

    }

}
