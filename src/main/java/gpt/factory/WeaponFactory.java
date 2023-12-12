package gpt.factory;

import gpt.Weapon;
import gpt.attackAttribute.FightInExtraRank;

public class WeaponFactory {

    public static Weapon aHandWeapon() {
        return new Weapon(0,0);
    }

    public static Weapon aSpear() {
        return new Weapon(0, 1, new FightInExtraRank());
    }
}
