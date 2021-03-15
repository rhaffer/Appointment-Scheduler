package report;

import java.time.LocalDate;

/**
 * This class holds the data required for the number of appointment by date report.
 */
public class ApptByDate {
    private final int number;
    private final LocalDate date;

    /**
     * Constructor to create new ApptByDate
     * @param newNumber Number to be inserted
     * @param newLocalDate LocalDate to be inserted
     */
    public ApptByDate(int newNumber, LocalDate newLocalDate){
        number = newNumber;
        date = newLocalDate;
    }

    /**
     * Returns the number associated with the ApptByDate
     * @return int number
     */
    public int getNumber(){return number;}

    /**
     * Returns the date associated with the ApptByDate
     * @return LocalDate date
     */
    public LocalDate getDate(){return date;}
}
