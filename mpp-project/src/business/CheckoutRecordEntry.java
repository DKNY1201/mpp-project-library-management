package business;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Bi on 7/4/17.
 */
public class CheckoutRecordEntry implements Serializable {

    private static final long serialVersionUID;

    static {
        serialVersionUID = 3665880920647848290L;
    }

    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;

    private CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
    }

    public static CheckoutRecordEntry createEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
        return new CheckoutRecordEntry(checkoutDate, dueDate, bookCopy);
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @Override
    public String toString() {
        return "Book copy: " + getBookCopy() + "\nCheckout date: " + getCheckoutDate() + " | Due date: " + getDueDate();
    }
}
