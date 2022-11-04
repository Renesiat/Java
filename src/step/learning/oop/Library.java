package step.learning.oop;

import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@DemoClass()
public class Library {
    private final String FUNDS_FILE = "funds.ser";
    private List<Literature> funds;

    public Library() {
        funds = new ArrayList<>();
    }

    public void add(Literature literature) {
        funds.add(literature);
    }

    private void printFunds() {
        for (Literature literature :
                funds) {
            if (literature instanceof Printable) {
                ((Printable) literature).print();
            } else {
                System.out.println(
                        "Unprintable: " +
                                literature.getTitle());
            }
        }

    }
    private void printPeriodic(){
        for(Literature literature:funds){
            if(literature instanceof Periodic && literature instanceof Printable)
                ((Printable)literature).print();
        }
    }
    private void printNonPeriodic(){
        for(Literature literature:funds){
            if(literature instanceof Periodic == false && literature instanceof Printable )
                ((Printable)literature).print();
        }
    }
    public void deserializeFunds(){
        try(FileInputStream file = new FileInputStream(FUNDS_FILE)){
            ObjectInputStream ois = new ObjectInputStream(file);
            @SuppressWarnings("unchecked")
            List<Literature> funds = (List<Literature>) ois.readObject();

            for (Literature data:funds){
                System.out.println(data);
            }

        }catch(Exception ex){
            System.out.println("Deserialize error: " + ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
    public void serializeFunds(){
        add(new Book()
                .setAuthor("Knuth")
                .setTitle("Art of programing"));
        add(new Book()
                .setAuthor("Knuth")
                .setTitle("Art of programing"));
        add(new Hologram().setTitle("Pectoral"));
        add(new Journal()
                .setPublication("Vogue")
                .setTitleJ("Something"));

        try {
            add(new NewsPaper()
                    .setTitle("Daily Planet")
                    .setDate("2022-10-07"));
            add(new NewsPaper()
                    .setTitle("New York Times")
                    .setDate("2021-10-07"));
        } catch (ParseException e) {
            System.out.printf("Newspaper creation failed: %s",
                    e.getMessage());
            return;
        }

        try(FileOutputStream file = new FileOutputStream(FUNDS_FILE))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(funds);
            oos.flush();
        }catch (IOException ex){
            System.out.println("Serialize error:" + ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
@EntryPoint
    public void run() {
        //serializeFunds();
        //deserializeFunds();

        //printFunds();
        //printPeriodic();
        //printNonPeriodic();
    }
}
