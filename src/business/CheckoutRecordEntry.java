package business;

import java.time.LocalDate;

/**
 * Created by Bi on 7/4/17.
 */
public class CheckoutRecordEntry {
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
        return "Book copy: " + getBookCopy() + ", checkout date: " + getCheckoutDate() + ", due date" + getCheckoutDate();
    }
}
