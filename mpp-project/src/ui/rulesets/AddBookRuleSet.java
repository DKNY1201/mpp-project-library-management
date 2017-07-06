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
		String maxCheckoutLenght = addBookWindow.getMaxCheckoutLenght();
		String numberOfCopies = addBookWindow.getNumberOfCopies();
		Boolean noAuthorSelected = addBookWindow.noAuthorSelected();
		
		nonEmptyRule(isbn, title, maxCheckoutLenght, numberOfCopies, noAuthorSelected);
		isbnRule(isbn);
		numbericRule(maxCheckoutLenght, numberOfCopies);
		maxCheckoutLengthRule(maxCheckoutLenght);
	}
	
	private void maxCheckoutLengthRule(String maxCheckoutLenght) throws RuleException{
		if (Integer.parseInt(maxCheckoutLenght) != 21 && Integer.parseInt(maxCheckoutLenght) != 7)
			throw new RuleException("Max checkout lenght value must be 21 or 7.");
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
			Boolean noAuthorSelected) throws RuleException {
		if (isbn.trim().isEmpty() || title.trim().isEmpty() || maxCheckoutLen.trim().isEmpty()
				|| numOfCopies.trim().isEmpty() || noAuthorSelected) {
			throw new RuleException("All fields must be nonempty.");
		}
	}
}
