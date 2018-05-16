package scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private Scanner scanner;

   public UI(){scanner = new Scanner(System.in).useDelimiter("\n");}

    public String getStringInput(String command) {
        System.out.println(command);
        return scanner.next();
    }

    public String getStringInput() {
        return scanner.next();
    }

    public long getLonginput(String command) {
        System.out.println(command);
        try {
            return scanner.nextLong();

        } catch (InputMismatchException e){
            System.out.println("You must enter a number\n");
            return -1;
        }
    }

    public boolean yesNo(String prompt) {
        System.out.print(prompt);
        String userInput = scanner.next();
        if ("y".equalsIgnoreCase(userInput) || "yes".equalsIgnoreCase(userInput)){
            return true;
        } else {
            return false;
        }
    }

    public int getInt() {
        String ui = getStringInput();
        try {
            return Integer.valueOf(ui);
        } catch (NumberFormatException e){
            System.out.println(e);
            System.out.println("Your input is not an integer");
            return getInt("Please enter an integer");
        }
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        return getInt();
    }

}
