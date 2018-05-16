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
    public static int itemID;
    public static String dir = "contacts";
    public static String filename = "contacts.txt";



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

    public static boolean findName(String dir,String dataFile, String search)throws IOException{
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item :list){
            if (item.contains(search)){
                System.out.println(item);
                return true;
            }
        }
        return false;
    }

    public static void removeContact(String dir, String dataFile, String nameToRemove) throws IOException {
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item : list) {
            if (item.contains(nameToRemove)) {
                itemID = list.indexOf(item);
            }
        }

        list.remove(itemID);

        addContactstoFile(list, dir, filename);
    }

    public static void readAllNames(String dir,String dataFile)throws IOException{
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item :list){
                System.out.println(item);
        }
    }

    public static ArrayList<String> contactsToString(ArrayList<Contacts> list) {
        ArrayList<String> contactStrings = new ArrayList<>();
        for (Contacts contact : list){
            String contactString = contact.toString();
            contactStrings.add(contactString);
        }
        return contactStrings;
    }


    public static void addContactstoFile(ArrayList<String> list, String dir, String filename) throws IOException {
        Path filepath = Paths.get(dir, filename);
        Files.write(filepath, list, StandardOpenOption.APPEND);
    }

    public static void addContactstoFile(List<String> list, String dir, String filename) throws IOException {
        Path filepath = Paths.get(dir, filename);
        Files.write(filepath, list);
    }

    public static ArrayList<Contacts> makeContactList() {
        ArrayList<Contacts> contacts = new ArrayList<>();
        UI ui = new UI();
        Contacts contact;

        do {
            try {
                String name = ui.getStringInput("Please enter contact name");

                if (!findName(dir, filename, name)) {
                    long phoneNumber = ui.getLonginput("Please enter contact's phone number");
                    contact = new Contacts(name, phoneNumber);
                    contacts.add(contact);
                }
                else if (findName(dir, filename, name)){
                    System.out.println("Already exists");
                    if (ui.yesNo("Would you like to overwrite?")){
                        long phoneNumber = ui.getLonginput("Please enter contact's phone number");
                        contact = new Contacts(name, phoneNumber);
                        contacts.add(contact);
                    }
                }
            }catch (java.io.IOException e){
                System.out.println("wut");
            }

        } while (ui.yesNo("Would you like to enter another contact?"));

        return contacts;
    }


    public static void main(String[] args) {
        UI ui = new UI();
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
                    ArrayList<String> stringContacts = contactsToString(contacts);
                    try {
                        addContactstoFile(stringContacts, dir, filename);
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
                    try {
                        removeContact(dir, filename, ui.getStringInput("Enter the name of the contact you want to delete"));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);
    }
}
