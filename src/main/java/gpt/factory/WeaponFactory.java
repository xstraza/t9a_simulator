package gpt.factory;

import gpt.Attack;
import gpt.Weapon;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.attackAttribute.DevastatingCharge;
import gpt.attackAttribute.FightInExtraRank;
import gpt.model.Model;

import java.util.ArrayList;
import java.util.List;

public class WeaponFactory {

    public static Weapon aHandWeapon() {
        return new Weapon(0,0,0);
    }

    public static Weapon aSpear() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new AttackAttribute() {
            @Override
            public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
                if (event != AttackEvent.CHARGE) {
                    return;
                }
                if (!attack.isCharging()) {
                    attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
                    attack.getWeapon().setAgility(attack.getAgility() + 2);
                }
            }
        });
        attackAttributes.add(new FightInExtraRank());
        return new Weapon(0, 1, 0, attackAttributes);
    }

    public static Weapon aLance() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new DevastatingCharge(WeaponFactory::lance));
        return new Weapon(0, 0, 0, new DevastatingCharge(WeaponFactory::lance));
    }

    private static void lance(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.CHARGE) {
            return;
        }
        if (attack.isCharging()) {
            attack.getWeapon().setStrength(attack.getWeapon().getStrength() + 2);
            attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 2);
        }
    }
}
