package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;
import gpt.specialRules.SpecialRuleType;

public class ChargeAgilityBonus implements SpecialRule {

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.AGILITY_MODIFIER) {
            return;
        }
        if (attack.isCharging()) {
            attack.setAgility(attack.getAgility() + 1);
        }
    }

    @Override
    public SpecialRuleType getSpecialRuleType() {
        return SpecialRuleType.CHARGE_AGILITY_BONUS;
    }
}
