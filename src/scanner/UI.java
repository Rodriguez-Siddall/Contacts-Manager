package scanner;

import java.util.Scanner;

public class UI {
    private Scanner scanner;

   public UI(){scanner = new Scanner(System.in).useDelimiter("\n");}

    public String getStringInput(String command) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println(command);
        return sc.nextLine();
    }

    public float getFloatinput(String command) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println(command);
        return sc.nextFloat();
    }
}
