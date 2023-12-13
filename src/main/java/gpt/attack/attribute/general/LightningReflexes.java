package gpt.attack.attribute.general;

import gpt.attack.Attack;
import gpt.weapon.WeaponType;
import gpt.attack.AttackAttribute;
import gpt.attack.AttackEvent;
import gpt.model.Model;

public class LightningReflexes implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event == AttackEvent.TO_HIT_MODIFIER || event == AttackEvent.AGILITY_MODIFIER) {
            if (attack.getWeapon().getType().equals(WeaponType.GREAT_WEAPON)) {
                attack.getWeapon().setAgilityMultiplier(1);
            } else {
                attack.setToHitModifier(attack.getToHitModifier() + 1);
            }
        }
    }
}
