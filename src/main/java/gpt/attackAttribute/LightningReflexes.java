package gpt.attackAttribute;

import gpt.Attack;
import gpt.model.Model;

public class LightningReflexes implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.TO_HIT_MODIFIER) {
            return;
        }
        attack.setToHitModifier(attack.getToHitModifier() + 1);
    }
}
