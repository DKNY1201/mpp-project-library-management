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
		String isbn = addBookWindow.getISBN();
		String title = addBookWindow.getBookTitle();
		String numberOfCopies = addBookWindow.getNumberOfCopies();
		Boolean noAuthorSelected = addBookWindow.noAuthorSelected();

		nonEmptyRule(isbn, title, numberOfCopies, noAuthorSelected);
		isbnRule(isbn);
		numbericRule(numberOfCopies);
	}

	private void numbericRule(String numberOfCopies) throws RuleException {
		try {
			Integer.parseInt(numberOfCopies);
		} catch (Exception e) {
			throw new RuleException(" Number of copies must be numberic.");
		}
	}

	private void isbnRule(String isbn) throws RuleException {
		if (!Pattern.matches("(\\d+)(-?)(\\d+)", isbn)) {
			throw new RuleException("ISBN number only include number and dash sign.");
		}
	}

	private void nonEmptyRule(String isbn, String title, String numOfCopies, Boolean noAuthorSelected)
			throws RuleException {
		if (isbn.trim().isEmpty() || title.trim().isEmpty() || numOfCopies.trim().isEmpty() || noAuthorSelected) {
			throw new RuleException("All fields must be nonempty.");
		}
	}
}
