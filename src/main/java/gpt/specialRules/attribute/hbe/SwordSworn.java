package gpt.specialRules.attribute.hbe;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRuleType;
import gpt.weapon.WeaponType;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

public class SwordSworn implements SpecialRule {

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.TO_HIT_MODIFIER) {
            return;
        }
        if (attack.getWeapon().getType().equals(WeaponType.GREAT_WEAPON)) {
            attack.setToHitModifier(attack.getToHitModifier() + 1);
        }
    }

    @Override
    public SpecialRuleType getSpecialRuleType() {
        return SpecialRuleType.SWORD_SWORN;
    }
}
