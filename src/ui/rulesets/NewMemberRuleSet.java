package ui.rulesets;

import javafx.stage.Stage;
import ui.*;
import java.util.regex.Pattern;

final public class NewMemberRuleSet implements RuleSet {
	// package level access
	NewMemberRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		NewMemberWindow newMemberWindow = (NewMemberWindow) ob;

		String memberID = newMemberWindow.getMemberIDValue();
		String firstName = newMemberWindow.getFirstNameValue();
		String lastName = newMemberWindow.getLastNameValue();
		String street = newMemberWindow.getStreetValue();
		String city = newMemberWindow.getCityValue();
		String state = newMemberWindow.getStateValue();
		String zip = newMemberWindow.getZipValue();
		String phone = newMemberWindow.getPhoneValue();

		nonEmptyRule(memberID, firstName, lastName, street, city, state, zip, phone);
//		numericRule(id);
//		zipRule(zip);
//		stateRule(state);
//		idZipRule(id, zip);
	}

	private void numericRule(String numericString) throws RuleException {
		try {
			Integer.parseInt(numericString);
		} catch (NumberFormatException e) {
			throw new RuleException("ID field must be numeric.");
		}
	}

	private void nonEmptyRule(String memberID, String firstName, String lastName, String street,
							  String city, String state, String zip, String phone) throws RuleException {
		if (memberID.equals("") || firstName.equals("") || lastName.equals("") || street.equals("")
				|| city.equals("") || state.equals("") || zip.equals("") || phone.equals("")) {
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
	
	private void idZipRule(String id, String zip) throws RuleException {
		if (id.equals(zip)) {
			throw new RuleException("ID field may not equal zip field.");
		}
	}

}
