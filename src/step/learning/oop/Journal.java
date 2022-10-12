package step.learning.oop;

public class Journal extends Literature implements Printable,Periodic{

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




    //@Override
    public void print() {
        System.out.printf("Journal. Publication: %s. Title: %s%n",
                this.publication, this.titleJ);
    }
}
