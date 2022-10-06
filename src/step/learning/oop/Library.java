package step.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class Library {
private List<Literature> funds;

    public Library() {
        funds = new ArrayList<>();
    }

    public void add(Literature literature){
        funds.add(literature);
    }
    private void printFunds(){
        for (Literature literature :
                funds) {
            literature.print();
        }
    }
    public void Run(){
        add(new Book()
                .setAuthor("Knuth")
                .setTitle("Art of programing"));
        printFunds();
        add(new Journal()
                .setPublication("Vogue")
                .setTitleJ("Something"));
        printFunds();
    }
}
