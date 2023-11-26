package gpt.factory;

import gpt.Weapon;

public class WeaponFactory {

    public static Weapon aHandWeapon() {
        return new Weapon(0,0);
    }

    public static Weapon aSpear() {
        return new Weapon(0, 1);
    }
}
