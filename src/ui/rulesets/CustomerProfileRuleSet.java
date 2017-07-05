//package ui.rulesets;
//
//import java.awt.Component;
//import java.util.regex.Pattern;
//
//import lab5.prob1.*;
//
//final public class CustomerProfileRuleSet implements RuleSet {
//	//package level access
//	CustomerProfileRuleSet() {}
//	@Override
//	public void applyRules(Component ob) throws RuleException {
//		ProfileWindow custProf = (ProfileWindow)ob;
//
//		String id = custProf.getIdValue();
//		String fName = custProf.getFirstNameValue();
//		String lName = custProf.getLastNameValue();
//		String favMovie = custProf.getFavoriteMovieValue();
//		String favRestaurant = custProf.getFavoriteRestaurantValue();
//
//		nonEmptyRule(id, fName, lName, favMovie, favRestaurant);
//		numericRule(id);
//		movieRestaurantRule(favMovie, favRestaurant);
//		fNamelNameRule(fName, lName);
//	}
//
//	private void movieRestaurantRule(String favMovie, String favRestaurant) throws RuleException {
//		if (favMovie.equals(favRestaurant)) {
//			throw new RuleException("Favorite restaurant cannot equal favorite movie.");
//		}
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
//	private void nonEmptyRule(String id, String fName, String lName, String favMovie, String favRestaurant) throws RuleException {
//		if (id.equals("") || fName.equals("") || lName.equals("") || favMovie.equals("") || favRestaurant.equals("")) {
//			throw new RuleException("All fields must be nonempty.");
//		}
//	}
//
//	private void fNamelNameRule(String fName, String lName) throws RuleException {
//		System.out.println(Pattern.matches("[a-zA-Z]+", fName));
//		System.out.println(Pattern.matches("[a-zA-Z]+", lName));
//		if (!Pattern.matches("[a-zA-Z]+", fName) || !Pattern.matches("[a-zA-Z]+", lName)) {
//			throw new RuleException("firstname and lastname fields may not contain spaces or characters other than a-z, A-Z.");
//		}
//	}
//
//}
