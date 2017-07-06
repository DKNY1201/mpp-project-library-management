package ui.rulesets;

import javafx.stage.Stage;
import ui.*;
import java.util.regex.Pattern;

final public class NewAuthorRuleSet implements RuleSet {
	// package level access
	NewAuthorRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		NewAuthorWindow newAuthorWindow = (NewAuthorWindow) ob;

		String firstName = newAuthorWindow.getFirstName();
		String lastName = newAuthorWindow.getLastName();
		String phone = newAuthorWindow.getPhone();
		String street = newAuthorWindow.getStreet();
		String city = newAuthorWindow.getCity();
		String state = newAuthorWindow.getState();
		String zip = newAuthorWindow.getZip();
		String bio = newAuthorWindow.getBio();

		nonEmptyRule(firstName, lastName, street, city, state, zip, phone, bio);
		phoneRule(phone);
		zipRule(zip);
		stateRule(state);
	}

	private void phoneRule(String numericString) throws RuleException {
		try {
			Integer.parseInt(numericString);
		} catch (NumberFormatException e) {
			throw new RuleException("Phone field must be numeric.");
		}
	}

	private void nonEmptyRule(String firstName, String lastName, String phone, String street, String city, String state,
			String zip, String bio) throws RuleException {
		if (firstName.trim().equals("") || lastName.trim().equals("") || phone.trim().equals("")
				|| street.trim().equals("") || city.trim().equals("") || state.trim().equals("")
				|| zip.trim().equals("") || bio.trim().equals("")) {
			throw new RuleException("All fields must be nonempty.");
		}
	}

	private void zipRule(String zip) throws RuleException {
		try {
			Integer.parseInt(zip);
			if (zip.length() != 5) {
				throw new RuleException("Zip must be exactly 5 digits.");
			}
		} catch (NumberFormatException e) {
			throw new RuleException("Zip field must be numeric.");
		}
	}

	private void stateRule(String state) throws RuleException {
		if (!Pattern.matches("[A-Z]{2}", state)) {
			throw new RuleException("State must have exactly two characters in the range A-Z.");
		}
	}

}
