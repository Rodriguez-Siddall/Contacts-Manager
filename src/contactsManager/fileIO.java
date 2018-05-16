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
import java.util.List;

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

    public static void findName(String dir,String dataFile, String search)throws IOException{
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item :list){
            if (item.contains(search)){
                System.out.println(item);
            }
        }
    }

    public static void addContactstoFile(ArrayList<Contacts> list, String dir, String filename) throws IOException {
        ArrayList<String> contactStrings = new ArrayList<>();
        for (Contacts contact : list){
            String contactString = contact.toString();
            contactStrings.add(contactString);
        }
        Path filepath = Paths.get(dir, filename);
        Files.write(filepath, contactStrings, StandardOpenOption.APPEND);
    }

    public static ArrayList<Contacts> makeContactList() {
        ArrayList<Contacts> contacts = new ArrayList<>();
        UI ui = new UI();
        Contacts contact;

        do {
            String name = ui.getStringInput("Please enter contact name");
            long phoneNumber = ui.getLonginput("Please enter contact's phone number");
            contact = new Contacts(name, phoneNumber);
            contacts.add(contact);
        } while (ui.yesNo("Would you like to enter another contact?"));

        return contacts;
    }


    public static void main(String[] args) {
        UI ui = new UI();
        String dir = "contacts";
        String filename = "contacts.txt";
        //the ui.getStringInput is from ui.java and is your run of the
        // mill sting scanner function.

        ArrayList<Contacts> contacts = makeContactList();
        System.out.println(contacts);

        try {
            addContactstoFile(contacts, dir, filename);
            findName(dir, filename, ui.getStringInput("Enter the name of the contact you want to find"));
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
