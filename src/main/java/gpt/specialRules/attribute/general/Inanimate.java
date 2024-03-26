package gpt.specialRules.attribute.general;

import gpt.model.Model;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.SpecialRuleType;

public class Inanimate implements SpecialRule {
    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.DETERMINE_ATTACKS) {
            return;
        }
        attack.setRank(100);
    }

    @Override
    public SpecialRuleType getSpecialRuleType() {
        return SpecialRuleType.INANIMATE;
    }
}
