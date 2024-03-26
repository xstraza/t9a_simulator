package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;
import gpt.specialRules.SpecialRuleType;

public class BattleFocus implements SpecialRule {
    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.ROLLED_6_TO_HIT) {
            return;
        }
        attack.setHits(2);
    }

    @Override
    public SpecialRuleType getSpecialRuleType() {
        return SpecialRuleType.BATTLE_FOCUS;
    }
}
