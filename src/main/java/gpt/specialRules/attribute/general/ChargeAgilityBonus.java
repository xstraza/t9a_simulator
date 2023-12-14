package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class ChargeAgilityBonus implements SpecialRule {

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.CHARGE) {
            return;
        }
        if (attack.isCharging()) {
            attack.setAgility(attack.getAgility() + 1);
        }
    }
}
