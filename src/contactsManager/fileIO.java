package contactsManager;

import scanner.UI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class fileIO {


    // checks to see if a file and dir has been created. if not it makes it
    public static void createFileIfNoExists(String dir, String fileName){
        Path dataDirectory = Paths.get(dir);
        Path dataFile = Paths.get(dir, fileName);

        try {
            if (Files.notExists(dataDirectory) && Files.notExists(dataFile)){
                Files.createDirectories(dataDirectory);
                Files.createFile(dataFile);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addContactstoFile(HashMap<String, Long> list, String dir, String filename) throws IOException {
        Path filepath = Paths.get(dir, filename);
        Files.write(filepath, list, StandardOpenOption.APPEND);
    }

    public static HashMap<String, Long> makeContactList() {
        HashMap<String, Long> contacts = new HashMap<>();
        UI ui = new UI();


        do {
            String name = ui.getStringInput("Please enter contact name");
            Long phoneNumber = ui.getLonginput("Please enter contact's phone number");
            contacts.put(name, phoneNumber);
        } while (ui.yesNo("Would you like to enter another contact?"));

        return contacts;
    }


    public static void main(String[] args) {
        UI ui = new UI();
        String dir = "contacts";
        String filename = "contacts.txt";
        //the ui.getStringInput is from ui.java and is your run of the
        // mill sting scanner function.

        HashMap<String, Long> contacts = makeContactList();
        System.out.println(contacts);

        try {
            addContactstoFile(contacts, dir, filename);
        }catch(Exception e){
            System.out.println("thing");
        }

    }
}
