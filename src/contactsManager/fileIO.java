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

    public static void readAllNames(String dir,String dataFile)throws IOException{
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item :list){
                System.out.println(item);
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
        ArrayList<Contacts> contacts;

        //the ui.getStringInput is from ui.java and is your run of the
        // mill sting scanner function.
    do {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit");
        int userInput = ui.getInt("Enter an option (1, 2, 3, 4 or 5:\n");

            switch(userInput) {
                case 1:
                    try {
                        readAllNames(dir, filename);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    contacts = makeContactList();
                    try {
                        addContactstoFile(contacts, dir, filename);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        findName(dir, filename, ui.getStringInput("Enter the name of the contact you want to find"));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Need to make delete option");
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);
    }
}
