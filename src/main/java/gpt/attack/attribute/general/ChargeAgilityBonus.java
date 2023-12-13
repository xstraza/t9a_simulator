package gpt.attack.attribute.general;

import gpt.attack.Attack;
import gpt.attack.AttackAttribute;
import gpt.attack.AttackEvent;
import gpt.model.Model;

public class ChargeAgilityBonus implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.CHARGE) {
            return;
        }
        if (attack.isCharging()) {
            attack.setAgility(attack.getAgility() + 1);
        }
    }
}
