package gpt.attackAttribute;

import gpt.model.Model;

public class BattleFocus implements AttackAttribute {
    @Override
    public void onAttackEvent(AttackEvent event, Model attacker, Model defender) {
        if (event != AttackEvent.ROLLED_6_TO_HIT) {
            return;
        }
        attacker.setHits(2);
    }
}
