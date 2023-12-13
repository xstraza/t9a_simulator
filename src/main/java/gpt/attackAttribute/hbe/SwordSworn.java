package gpt.attackAttribute.hbe;

import gpt.Attack;
import gpt.weapon.WeaponType;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.model.Model;

public class SwordSworn implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.TO_HIT_MODIFIER) {
            return;
        }
        if (attack.getWeapon().getType().equals(WeaponType.GREAT_WEAPON)) {
            attack.setToHitModifier(attack.getToHitModifier() + 1);
        }
    }
}
