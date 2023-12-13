package gpt.attack.attribute.general;

import gpt.attack.Attack;
import gpt.attack.AttackAttribute;
import gpt.attack.AttackEvent;
import gpt.model.Model;

public class BattleFocus implements AttackAttribute {
    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.ROLLED_6_TO_HIT) {
            return;
        }
        attack.setHits(2);
    }
}
