package ui.rulesets;

import java.util.HashMap;

import javafx.stage.Stage;
import ui.*;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Stage>, RuleSet> map = new HashMap<>();
	static {
		map.put(NewMemberWindow.class, new NewMemberRuleSet());
		map.put(CheckoutBookWindow.class, new CheckoutBookRuleSet());
		map.put(AddBookWindow.class, new AddBookRuleSet());
		map.put(PrintMemberCheckoutReport.class, new PrintMemberCheckoutReportRuleSet());
		map.put(CheckOverdueBookWindow.class, new CheckOverdueBookRuleSet());
		map.put(NewAuthorWindow.class, new NewAuthorRuleSet());
		map.put(EditMemberWindow.class, new EditMemberRuleSet());
	}
	public static RuleSet getRuleSet(Stage c) {
		Class<? extends Stage> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Window");
		}
		return map.get(cl);
	}
}
