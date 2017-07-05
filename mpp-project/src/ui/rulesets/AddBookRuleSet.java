package ui.rulesets;

import java.util.regex.Pattern;

import javafx.stage.Stage;
import ui.AddBookWindow;

final public class AddBookRuleSet implements RuleSet {
	AddBookRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		AddBookWindow addBookWindow = (AddBookWindow) ob;
		nonEmptyRule(addBookWindow.getISBN(), addBookWindow.getTitle(), addBookWindow.getMaxCheckoutLenght(),
				addBookWindow.getNumberOfCopies(), addBookWindow.hasSelectedAuthor());
		isbnRule(addBookWindow.getISBN());
		numbericRule(addBookWindow.getMaxCheckoutLenght(), addBookWindow.getNumberOfCopies());
	}

	private void numbericRule(String maxCheckoutLenght, String numberOfCopies) throws RuleException{
		try {
			Integer.parseInt(maxCheckoutLenght);
			Integer.parseInt(numberOfCopies);
		} catch (Exception e) {
			throw new RuleException("Max checkout lenght and Number of copies must be numberic.");
		}
	}
	
	private void isbnRule(String isbn) throws RuleException {
		if (!Pattern.matches("(\\d+)(-?)(\\d+)", isbn)) {
			throw new RuleException("ISBN number only include number and dash sign.");
		}
	}

	private void nonEmptyRule(String isbn, String title, String maxCheckoutLen, String numOfCopies,
			Boolean hasSelectedAuthor) throws RuleException {
		if (isbn.trim().isEmpty() || title.trim().isEmpty() || maxCheckoutLen.trim().isEmpty()
				|| numOfCopies.trim().isEmpty() || !hasSelectedAuthor) {
			throw new RuleException("All fields must be nonempty.");
		}
	}
}
