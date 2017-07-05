package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bi on 7/4/17.
 */
public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID;

    static {
        serialVersionUID = 5467032815847529663L;
    }

    private List<CheckoutRecordEntry> checkoutRecordEntries;

    public CheckoutRecord(){
        checkoutRecordEntries = new ArrayList<>();
    }

    public void addEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.lineSeparator();
        sb.append(newLine);
        sb.append("Checkout Record: ");
        sb.append(checkoutRecordEntries.size());
        for (CheckoutRecordEntry entry: checkoutRecordEntries) {
            sb.append(entry.toString());
            sb.append(newLine);
        }
        return sb.toString();
    }
}
