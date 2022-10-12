package step.learning.oop;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
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


    public void Run() {
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
        //printFunds();
        //printPeriodic();
        //printNonPeriodic();
    }
}
