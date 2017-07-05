package ui.rulesets;
//package ui.rulesets;
//
//import java.awt.Component;
//import java.util.regex.*;
//import lab5.prob1.*;
//
//final public class AddressRuleSet implements RuleSet {
//	// package level access
//	AddressRuleSet() {
//	}
//
//	@Override
//	public void applyRules(Component ob) throws RuleException {
//		AddrWindow addr = (AddrWindow) ob;
//
//		String id = addr.getIdValue();
//		String street = addr.getStreetValue();
//		String city = addr.getCityValue();
//		String state = addr.getStateValue();
//		String zip = addr.getZipValue();
//
//		nonEmptyRule(id, street, city, state, zip);
//		numericRule(id);
//		zipRule(zip);
//		stateRule(state);
//		idZipRule(id, zip);
//	}
//
//	private void numericRule(String numericString) throws RuleException {
//		try {
//			Integer.parseInt(numericString);
//		} catch (NumberFormatException e) {
//			throw new RuleException("ID field must be numeric.");
//		}
//	}
//
//	private void nonEmptyRule(String id, String street, String city, String state, String zip) throws RuleException {
//		if (id.equals("") || street.equals("") || city.equals("") || state.equals("") || zip.equals("")) {
//			throw new RuleException("All fields must be nonempty.");
//		}
//	}
//
//	private void zipRule(String zip) throws RuleException {
//		try {
//			Integer.parseInt(zip);
//			if (zip.length() != 5) {
//				throw new RuleException("Zip must be exactly 5 digits.");
//			}
//		} catch (NumberFormatException e) {
//			throw new RuleException("Zip field must be numeric.");
//		}
//	}
//
//	private void stateRule(String state) throws RuleException {
//		if (!Pattern.matches("[A-Z]{2}", state)) {
//			throw new RuleException("State must have exactly two characters in the range A-Z.");
//		}
//	}
//
//	private void idZipRule(String id, String zip) throws RuleException {
//		if (id.equals(zip)) {
//			throw new RuleException("ID field may not equal zip field.");
//		}
//	}
//
//}
