package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class FightInExtraRank implements SpecialRule {

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.DETERMINE_ATTACKS) {
            return;
        }
        attack.setFier(attack.getFier()+1);
    }
}
