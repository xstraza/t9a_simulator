package gpt.attackAttribute;

import gpt.model.Model;

public class LightningReflexes implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Model attacker, Model defender) {
        if (event != AttackEvent.TO_HIT_MODIFIER) {
            return;
        }
        attacker.setToHitModifier(attacker.getToHitModifier() + 1);
    }
}
