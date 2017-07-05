package ui.rulesets;

import javafx.stage.Stage;
import ui.SearchLibraryMemberWindow;

final public class SearchLibraryMemberRuleSet implements RuleSet {
	SearchLibraryMemberRuleSet() {
	}

	@Override
	public void applyRules(Stage ob) throws RuleException {
		SearchLibraryMemberWindow searchLibraryMemberWindow = (SearchLibraryMemberWindow) ob;
		nonEmptyRule(searchLibraryMemberWindow.getMemberId());
	}

	private void nonEmptyRule(String memberID) throws RuleException {
		if (memberID.trim().isEmpty()) {
			throw new RuleException("Member id must be nonempty.");
		}
	}
}
