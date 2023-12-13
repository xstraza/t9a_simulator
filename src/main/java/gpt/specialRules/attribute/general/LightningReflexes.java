package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.weapon.WeaponType;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class LightningReflexes implements SpecialRule {

    @Override
    public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
        if (event == Event.TO_HIT_MODIFIER || event == Event.AGILITY_MODIFIER) {
            if (attack.getWeapon().getType().equals(WeaponType.GREAT_WEAPON)) {
                attack.getWeapon().setAgilityMultiplier(1);
            } else {
                attack.setToHitModifier(attack.getToHitModifier() + 1);
            }
        }
    }
}
