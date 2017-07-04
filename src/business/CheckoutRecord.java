package business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bi on 7/4/17.
 */
public class CheckoutRecord {
    private List<CheckoutRecordEntry> checkoutRecordEntries;
    public String testdata = "Testdata";

    CheckoutRecord(){
        checkoutRecordEntries = new ArrayList<>();
    }

    public void addEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.lineSeparator();
        for (CheckoutRecordEntry entry: checkoutRecordEntries) {
            sb.append(entry.toString());
            sb.append(newLine);
        }
        return sb.toString();
    }
}
