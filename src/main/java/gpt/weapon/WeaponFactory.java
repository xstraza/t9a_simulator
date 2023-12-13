package gpt.weapon;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.specialRules.attribute.general.DevastatingCharge;
import gpt.specialRules.attribute.general.FightInExtraRank;
import gpt.model.Model;
import gpt.util.TriConsumer;

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
        SpecialRule spear = new SpecialRule() {
            @Override
            public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
                if (event == Event.CHARGE && !attack.isCharging()) {
                    attack.getWeapon().setArmorPenetration(attack.getWeapon().getArmorPenetration() + 1);
                    attack.getWeapon().setAgility(attack.getWeapon().getAgility() + 2);
                }
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
        TriConsumer<Event, Attack, Model> lance = (event, attack, defender) -> {
            if (event == Event.CHARGE && attack.isCharging()) {
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
        TriConsumer<Event, Attack, Model> lightLance = (event, attack, defender) -> {
            if (event == Event.CHARGE && attack.isCharging()) {
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

    public static Weapon aHalberd() {
        return new Weapon(
                1,
                1,
                0,
                1,
                Collections.emptyList(),
                WeaponType.HALBERD
        );
    }
}
