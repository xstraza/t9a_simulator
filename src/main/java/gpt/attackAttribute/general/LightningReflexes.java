package gpt.attackAttribute.general;

import gpt.Attack;
import gpt.WeaponType;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.model.Model;

public class LightningReflexes implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.TO_HIT_MODIFIER || event != AttackEvent.AGILITY_MODIFIER) {
            return;
        }
        if (attack.getWeapon().getType().equals(WeaponType.GREAT_WEAPON)) {
            attack.getWeapon().setAgility(0);
        } else {
            attack.setToHitModifier(attack.getToHitModifier() + 1);
        }
    }
}
