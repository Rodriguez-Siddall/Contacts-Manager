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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[46m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[47m";


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
        search = search.toLowerCase();
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);

        for (String item :list){
            String lowerCaseItem = item.toLowerCase();
            if (lowerCaseItem.contains(search)){
                System.out.println(ANSI_GREEN + item + ANSI_RESET);
                System.out.println();
                return true;
            }
        }
        System.out.println();
        System.out.println(ANSI_YELLOW + "Contact not found" + ANSI_RESET);
        System.out.println();
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
        System.out.println();
    }

    public static void readAllNames(String dir,String dataFile)throws IOException{
        Path filePath = Paths.get(dir, dataFile);
        List<String> list = Files.readAllLines(filePath);


        System.out.println();
        System.out.println(String.format(ANSI_BLUE + "%-20s | %-20s |" , "Name", "Phone Number"));
        System.out.println("--------------------------------------------|");
        for (String item :list){
                System.out.println(item);
        }
        System.out.println(ANSI_RESET);
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
                String name = ui.getStringInput(ANSI_CYAN + "Please enter contact name");
                boolean nameExists = findName(dir, filename, name);
                if (!nameExists) {
                    long phoneNumber = ui.getLonginput(ANSI_CYAN + "Please enter contact's phone number" + ANSI_RESET);
                    int length = String.valueOf(phoneNumber).length();
                    if (length != 10 || length != 7) {
                        System.out.println(ANSI_RED + "That is not a valid phone number");
                        phoneNumber = ui.getLonginput(ANSI_CYAN + "Please enter contact's phone number" + ANSI_RESET);
                    }
                    contact = new Contacts(name, phoneNumber);
                    contacts.add(contact);
                } else if (nameExists) {
                    System.out.println(ANSI_RED + "Already exists" + ANSI_RESET);
                    if (ui.yesNo(ANSI_YELLOW + "Would you like to overwrite?" + ANSI_RESET)){
                        removeContact(dir, filename, name);
                        long phoneNumber = ui.getLonginput(ANSI_CYAN + "Please enter contact's phone number" + ANSI_RESET);
                        int length = String.valueOf(phoneNumber).length();
                        if (length > 10 || length < 7) {
                            System.out.println(ANSI_RED + "That is not a valid phone number");
                            phoneNumber = ui.getLonginput(ANSI_CYAN + "Please enter contact's phone number" + ANSI_RESET);
                        }
                        contact = new Contacts(name, phoneNumber);
                        contacts.add(contact);
                    }
                }
            }catch (java.io.IOException e){
                System.out.println("wut");
            }

        } while (ui.yesNo(ANSI_CYAN + "Would you like to enter another contact?" + ANSI_RESET));

        return contacts;
    }


    public static void main(String[] args) {
        UI ui = new UI();
        ArrayList<Contacts> contacts;

        //the ui.getStringInput is from ui.java and is your run of the
        // mill sting scanner function.
    do {
        System.out.println(ANSI_BLUE + "1. View contacts." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "2. Add a new contact." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3. Search a contact by name." + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "4. Delete an existing contact." + ANSI_RESET);
        System.out.println(ANSI_RED + "5. Exit" + ANSI_RESET);
        int userInput = ui.getInt(ANSI_PURPLE + "Enter an option (1, 2, 3, 4 or 5:)\n" + ANSI_RESET);

            switch(userInput) {
                case 1:
                    try {
                        readAllNames(dir, filename);
                        System.out.println();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    contacts = makeContactList();
                    ArrayList<String> stringContacts = contactsToString(contacts);
                    try {
                        addContactstoFile(stringContacts, dir, filename);
                        System.out.println();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        findName(dir, filename, ui.getStringInput(ANSI_GREEN + "Enter the name of the contact you want to find" + ANSI_RESET));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        removeContact(dir, filename, ui.getStringInput(ANSI_YELLOW + "Enter the name of the contact you want to delete" + ANSI_RESET));
                        System.out.println(ANSI_RED + "Contact has been deleted" + ANSI_RESET);
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
