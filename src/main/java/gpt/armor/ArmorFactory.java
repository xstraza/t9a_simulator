package gpt.armor;

import gpt.specialRules.protection.general.Aegis;

import java.util.Collections;

public class ArmorFactory {

    public static Armor unarmored() {
        return new Armor(0, Collections.emptyList());
    }

    public static Armor lightArmor() {
        return new Armor(1, Collections.emptyList());
    }

    public static Armor heavyArmor() {
        return new Armor(2, Collections.emptyList());
    }

    public static Armor shield() {
        return new Armor(1, Collections.emptyList());
    }

    public static Armor lionsFur() {
        return new Armor(1, Collections.emptyList());
    }

    public static Armor dragonForgedArmor() {
        Armor heavyArmor = heavyArmor();
        heavyArmor.getProtections().add(new Aegis(attack -> {
            if (attack.isFlaming()) {
                return 3;
            } else {
                return 6;
            }
        }));
        return heavyArmor;
    }
}
