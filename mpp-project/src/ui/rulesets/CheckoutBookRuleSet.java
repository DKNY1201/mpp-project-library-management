package ui.rulesets;

import javafx.stage.Stage;
import ui.CheckoutBookWindow;

import java.util.regex.Pattern;

final public class CheckoutBookRuleSet implements RuleSet {
	// package level access
	CheckoutBookRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		CheckoutBookWindow checkoutBookWindow = (CheckoutBookWindow) ob;

		String memberID = checkoutBookWindow.getMemberIDValue();
		String isbn = checkoutBookWindow.getIsbnValue();

		nonEmptyRule(memberID, isbn);
        isbnRule(isbn);
	}

	private void isbnRule(String isbn) throws RuleException {
		if (!Pattern.matches("(\\d+)(-?)(\\d+)", isbn)) {
			throw new RuleException("ISBN number only include number and dash sign.");
		}
	}

	private void nonEmptyRule(String memberID, String isbn) throws RuleException {
		if (memberID.trim().equals("") || isbn.trim().equals("")) {
			throw new RuleException("All fields must be nonempty.");
		}
	}
}
