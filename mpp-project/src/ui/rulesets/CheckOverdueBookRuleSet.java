package ui.rulesets;

import java.util.regex.Pattern;

import javafx.stage.Stage;
import ui.CheckOverdueBookWindow;

final public class CheckOverdueBookRuleSet implements RuleSet {
	CheckOverdueBookRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		CheckOverdueBookWindow checkOverdueBookWindow = (CheckOverdueBookWindow) ob;
		nonEmptyRule(checkOverdueBookWindow.getISBN());
		isbnRule(checkOverdueBookWindow.getISBN());
	}

	private void isbnRule(String isbn) throws RuleException {
		if (!Pattern.matches("(\\d+)(-?)(\\d+)", isbn)) {
			throw new RuleException("ISBN number only include number and dash sign.");
		}
	}
	
	private void nonEmptyRule(String memberID) throws RuleException {
		if (memberID.trim().isEmpty()) {
			throw new RuleException("ISBN must be nonempty.");
		}
	}
}
