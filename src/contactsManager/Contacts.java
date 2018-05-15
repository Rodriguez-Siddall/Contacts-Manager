package contactsManager;

import java.util.Scanner;

public class Contacts {


    String name;
    long phoneNumber;

    public Contacts(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }



}
