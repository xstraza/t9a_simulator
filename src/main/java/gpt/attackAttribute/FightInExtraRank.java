package gpt.attackAttribute;

import gpt.Attack;
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
