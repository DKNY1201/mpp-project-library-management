package ui.rulesets;

import javafx.stage.Stage;
import ui.*;
import java.util.regex.Pattern;

final public class EditMemberRuleSet implements RuleSet {
	// package level access
	EditMemberRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		EditMemberWindow editMemberWindow = (EditMemberWindow) ob;

		String memberID = editMemberWindow.getMemberIDValue();
		String firstName = editMemberWindow.getFirstNameValue();
		String lastName = editMemberWindow.getLastNameValue();
		String street = editMemberWindow.getStreetValue();
		String city = editMemberWindow.getCityValue();
		String state = editMemberWindow.getStateValue();
		String zip = editMemberWindow.getZipValue();
		String phone = editMemberWindow.getPhoneValue();

		nonEmptyRule(memberID, firstName, lastName, street, city, state, zip, phone);
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

	private void nonEmptyRule(String memberID, String firstName, String lastName, String street,
							  String city, String state, String zip, String phone) throws RuleException {
		if (memberID.trim().equals("") || firstName.trim().equals("") || lastName.trim().equals("") || street.trim().equals("")
				|| city.trim().equals("") || state.trim().equals("") || zip.trim().equals("") || phone.trim().equals("")) {
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
