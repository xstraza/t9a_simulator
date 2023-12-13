package gpt.weapon;

import gpt.Attack;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.attackAttribute.general.DevastatingCharge;
import gpt.attackAttribute.general.FightInExtraRank;
import gpt.model.Model;
import gpt.util.TriConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeaponFactory {

    public static Weapon aHandWeapon() {
        return new Weapon(
                0,
                0,
                0,
                1,
                Collections.emptyList(),
                WeaponType.HAND_WEAPON
        );
    }

    public static Weapon aSpear() {
        AttackAttribute spear = (event, attack, defender) -> {
            if (event == AttackEvent.CHARGE && !attack.isCharging()) {
                attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
                attack.getWeapon().setAgility(attack.getWeapon().getAgility() + 2);
            }
        };
        return new Weapon(
                0,
                1,
                0,
                1,
                List.of(spear, new FightInExtraRank()),
                WeaponType.SPEAR);
    }

    public static Weapon aLance() {
        TriConsumer<AttackEvent, Attack, Model> lance = (event, attack, defender) -> {
            if (event == AttackEvent.CHARGE && attack.isCharging()) {
                attack.getWeapon().setStrength(attack.getWeapon().getStrength() + 2);
                attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 2);
            }
        };
        return new Weapon(
                0,
                0,
                0,
                1,
                List.of(new DevastatingCharge(lance)),
                WeaponType.LANCE
        );
    }

    public static Weapon aLightLance() {
        TriConsumer<AttackEvent, Attack, Model> lightLance = (event, attack, defender) -> {
            if (event == AttackEvent.CHARGE && attack.isCharging()) {
                attack.getWeapon().setStrength(attack.getWeapon().getStrength() + 1);
                attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
            }
        };
        return new Weapon(
                0,
                0,
                0,
                1,
                List.of(new DevastatingCharge(lightLance)),
                WeaponType.LIGHT_LANCE
        );
    }

    public static Weapon aGreatWeapon() {
        return new Weapon(
                2,
                2,
                0,
                0,
                Collections.emptyList(),
                WeaponType.GREAT_WEAPON
        );
    }
}
