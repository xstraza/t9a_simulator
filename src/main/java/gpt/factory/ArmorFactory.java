package gpt.factory;

import gpt.Armor;

public class ArmorFactory {

    public static Armor unarmored() {
        return new Armor(0);
    }

    public static Armor lightArmor() {
        return new Armor(1);
    }

    public static Armor heavyArmor() {
        return new Armor(2);
    }

    public static Armor shield() {
        return new Armor(1);
    }

    public static Armor lionsFur() {
        return new Armor(1);
    }
}
