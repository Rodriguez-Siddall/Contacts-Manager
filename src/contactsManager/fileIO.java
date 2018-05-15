package contactsManager;

import scanner.UI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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


    public static void main(String[] args) {
        UI ui = new UI();

        //the ui.getStringInput is from ui.java and is your run of the
        // mill sting scanner function.
        createFileIfNoExists(
                ui.getStringInput("Folder Name"),
                ui.getStringInput("File name")
        );

    }
}
