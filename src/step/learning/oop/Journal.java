package step.learning.oop;

import java.io.Serializable;

public class Journal extends Literature implements Printable,Periodic, Serializable {

    public String getPublication() {
        return publication;
    }

    public Journal setPublication(String publication) {
        this.publication = publication;
        return this;
    }

    private String publication;

    public String getTitleJ() {
        return titleJ;
    }

    public Journal setTitleJ(String titleJ) {
        this.titleJ = titleJ;
        return this;
    }

    private String titleJ;


    @Override
    public String toString() {
        return String.format("Journal. Publication: %s. Title: %s%n",
                this.publication, this.titleJ);
    }

    //@Override
    public void print() {
        System.out.printf("Journal. Publication: %s. Title: %s%n",
                this.publication, this.titleJ);
    }
}
