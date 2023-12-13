package gpt.factory;

import gpt.Attack;
import gpt.Weapon;
import gpt.WeaponType;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.attackAttribute.general.DevastatingCharge;
import gpt.attackAttribute.general.FightInExtraRank;
import gpt.model.Model;

import java.util.ArrayList;
import java.util.List;

public class WeaponFactory {

    public static Weapon aHandWeapon() {
        return new Weapon(0,0,0, WeaponType.HAND_WEAPON);
    }

    public static Weapon aSpear() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add((event, attack, defender) -> {
            if (event != AttackEvent.CHARGE) {
                return;
            }
            if (!attack.isCharging()) {
                attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
                attack.getWeapon().setAgility(attack.getWeapon().getAgility() + 2);
            }
        });
        attackAttributes.add(new FightInExtraRank());
        return new Weapon(0, 1, 0, attackAttributes, WeaponType.SPEAR);
    }

    public static Weapon aLance() {
        DevastatingCharge attribute = new DevastatingCharge(WeaponFactory::lance);
        return new Weapon(0, 0, 0, attribute, WeaponType.LANCE);
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

    public static Weapon aLightLance() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new DevastatingCharge(WeaponFactory::lance));
        return new Weapon(0, 0, 0, new DevastatingCharge(WeaponFactory::lightLance), WeaponType.LIGHT_LANCE);
    }

    public static Weapon aGreatWeapon() {
        return new Weapon(2, 2, -20, WeaponType.GREAT_WEAPON);
    }

    private static void lightLance(AttackEvent event, Attack attack, Model model) {
        if (event != AttackEvent.CHARGE) {
            return;
        }
        if (attack.isCharging()) {
            attack.getWeapon().setStrength(attack.getWeapon().getStrength() + 1);
            attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
        }
    }
}
