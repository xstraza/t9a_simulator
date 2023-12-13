package gpt.attack.attribute.general;

import gpt.attack.Attack;
import gpt.attack.AttackAttribute;
import gpt.attack.AttackEvent;
import gpt.model.Model;

public class FightInExtraRank implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.DETERMINE_ATTACKS) {
            return;
        }
        attack.setFier(attack.getFier()+1);
    }
}
