package step.learning.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class FileNavigation {
    public void Run() {
        ShowDir();
        Base();
    }

    File file = new File("C:\\Users\\Lin\\IdeaProjects\\Java_project");

    public void ShowDir() {
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String filename : Objects.requireNonNull(file.list())) {
                    System.out.println(filename);
                }
            }
        }else{
            System.out.println("Resource not found");
        }
    }
public void ShowFile(String file){
    String content;
    StringBuilder sb;

    try (InputStream reader = new FileInputStream(file)){
        int symbol;
        sb = new StringBuilder();
        while ((symbol = reader.read()) != -1) {
            sb.append((char) symbol);
        }
    } catch (IOException ex) {
        System.out.println("Resource not found");
        return;
    }
    content = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1),
            StandardCharsets.UTF_8);
    System.out.println(content);
}

    public void Base() {

        System.out.println("Enter command: ");
        try {
            Scanner kbScanner = new Scanner(System.in);
            String userInput = kbScanner.nextLine();

            if (userInput.startsWith("cd")) {
                userInput = userInput.substring(userInput.indexOf(' ') + 1);
                file = new File(userInput);
                ShowDir();
            } else if (userInput.startsWith("cat")) {
                userInput = userInput.substring(userInput.indexOf(' ') + 1);
                ShowFile(userInput);
            } else {
                System.out.println("Command unknown: " + userInput);
            }
        } catch (Exception ex) {
            System.out.println("System error" + ex.getMessage());
        }
        Base();
    }


}



