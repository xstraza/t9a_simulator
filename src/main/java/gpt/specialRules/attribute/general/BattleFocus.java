package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class BattleFocus implements SpecialRule {
    @Override
    public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
        if (event != Event.ROLLED_6_TO_HIT) {
            return;
        }
        attack.setHits(2);
    }
}
