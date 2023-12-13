package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class Harnessed implements SpecialRule {

    @Override
    public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
        if (event != Event.DETERMINE_ATTACKS) {
            return;
        }
        attack.setRank(attack.getRank() == 1 ? 1 : 100);
    }
}
