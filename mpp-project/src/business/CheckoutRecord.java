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
    
    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}

	public void setCheckoutRecordEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
		this.checkoutRecordEntries = checkoutRecordEntries;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.lineSeparator();
        int size = checkoutRecordEntries.size();
        for (int i = 0; i < size; i++) {
        	sb.append(checkoutRecordEntries.get(i));
            sb.append(newLine);
            if (i != size - 1) {
	            sb.append("----------------------------------------------------");
	            sb.append(newLine);
            }
        }
        return sb.toString();
    }
}
