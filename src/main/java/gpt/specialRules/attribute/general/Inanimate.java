package gpt.specialRules.attribute.general;

import gpt.model.Model;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;

public class Inanimate implements SpecialRule {
    @Override
    public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
        if (event != Event.DETERMINE_ATTACKS) {
            return;
        }
        attack.setRank(100);
    }
}