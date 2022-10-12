package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewsPaper extends Literature
        implements Printable,Periodic{
    public Date getDate() {
        return date;
    }

    public NewsPaper setDate(Date date) {
        this.date = date;
        return this;
    }

    public NewsPaper setDate(String date)
            throws ParseException {
        this.date = sdfParser.parse(date);
        return this;
    }

    private Date date;
    static private final SimpleDateFormat sdfParser
            = new SimpleDateFormat("yyyy-MM-dd");
    static private final SimpleDateFormat sdfPrinter
            = new SimpleDateFormat("dd.MM.yyyy");
    static private final SimpleDateFormat sdfShortPrinter
            = new SimpleDateFormat("dd.MM");

   @Override
    public void print() {
        System.out.printf("Newspaper '%s' for %s%n",
                super.getTitle(),sdfPrinter.format( this.getDate()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.YEAR);
        Calendar now = Calendar.getInstance();
        SimpleDateFormat printer;

        printer = (now.get(Calendar.YEAR)==calendar.get(Calendar.YEAR))
            ? sdfShortPrinter
       : sdfPrinter;
        System.out.printf("Newspaper'%s' for %s%n",
                super.getTitle(), printer.format(this.getDate()));
    }

    @Override
    public NewsPaper setTitle(String title) {
        super.setTitle(title);
        return this;
    }

}
