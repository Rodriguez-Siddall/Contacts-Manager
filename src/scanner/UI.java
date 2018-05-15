package scanner;

import java.util.Scanner;

public class UI {
    private Scanner scanner;

   public UI(){scanner = new Scanner(System.in).useDelimiter("\n");}

    public String getStringInput(String command) {
        System.out.println(command);
        return scanner.next();
    }

    public long getLonginput(String command) {
        System.out.println(command);
        return scanner.nextLong();
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

}
