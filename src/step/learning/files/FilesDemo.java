package step.learning.files;

import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.io.*;
import java.nio.charset.StandardCharsets;
@DemoClass(priority = 2)
public class FilesDemo {
    @EntryPoint
    public void run() {
        fsDemo();
        //ioDemo();
    }

    private void fsDemo() {
        File file = new File("." + File.separator + "Basics.iml ");
        if (file.isDirectory()) {
            System.out.printf("'%s' - is directory%n",
                    file.getName());
            String[] list = file.list();
            if (list != null) {
                for (String filename : file.list()) {
                    System.out.println(filename);
                }
            }
            System.out.println("Do you want to delete DIR?(y/...)");
            try {
                int userInput = System.in.read();
                if (userInput == 'y') {
                    boolean res = file.delete();
                    if (res) System.out.println("Deleted");
                    else System.out.println("Delete error");
                }
            } catch (IOException ex) {
                System.out.println("System error" + ex.getMessage());
            }
        } else if (file.isFile()) {

            System.out.printf("'%s' - is file%n",
                    file.getName());

            if (file.canRead()) System.out.println("Readable");
            else System.out.println("Unreadable");

            if (file.canWrite()) System.out.println("Writable");
            else System.out.println("Un-writable");

            if (file.canExecute()) System.out.println("Executable");
            else System.out.println("Un-executable");

        } else {
            System.out.printf("'%s' - neither file," +
                            " nor directory%n",
                    file.getName());
            System.out.println("Creating subfolder ...");
            boolean res = file.mkdir();
            if (res) System.out.println("Created successfully");
            else System.out.println("Creation error");
        }
    }

    private void ioDemo() {
        String content = null;
        StringBuilder sb;
        try (InputStream reader = new FileInputStream("text.txt")){//Files.newInputStream(Paths.get("Basics.iml")))
            int symbol;
            sb = new StringBuilder();
            while ((symbol = reader.read()) != -1) {
                sb.append((char) symbol);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        content = new String(sb.toString().getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);
        System.out.println(content);

        try(FileWriter writer = new FileWriter("result.txt")){
            writer.write("Hello everybody!");
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Write finished");
    }
}
