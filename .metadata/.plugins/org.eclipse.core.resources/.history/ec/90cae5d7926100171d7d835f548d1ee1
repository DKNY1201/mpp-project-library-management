package ui;

import java.util.HashMap;

import javafx.stage.Stage;
import ui.*;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Stage>, RuleSet> map = new HashMap<>();
	static {
		map.put(NewMemberWindow.class, new NewMemberRuleSet());
		map.put(CheckoutBookWindow.class, new CheckoutBookRuleSet());
	}
	public static RuleSet getRuleSet(Stage c) {
		Class<? extends Stage> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Window");
		}
		return map.get(cl);
	}
}
