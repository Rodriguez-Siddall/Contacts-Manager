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

    public String convertNumber(long phNum){
       String data = Long.toString(phNum);

    if (data.length() == 9) {
        String areaNum =data.substring(0,3);
        String exchangeNum = data.substring(3, 6);
        String num = data.substring(5, 9);
        return areaNum + "-" + exchangeNum + "-" + num;
    }else if (data.length() == 7){
        String areaNum =data.substring(0, 3);
        String num = data.substring(3, 7);
        return areaNum + "-" + num;
    }
    return "this number was incorrect.";
}

    @Override
    public String toString() {
        return String.format("%-20s  |  %-12s", name, convertNumber(phoneNumber));
    }
}
