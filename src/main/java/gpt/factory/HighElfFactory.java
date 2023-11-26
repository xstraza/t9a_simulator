package gpt.factory;

import gpt.*;

import java.util.List;

public class HighElfFactory {

    public Model createSpearman() {
        Weapon spear = WeaponFactory.aSpear();
        Armor lightArmor = ArmorFactory.lightArmor();
        Armor shield = ArmorFactory.shield();
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5);
        DefensiveProfile defensiveProfile = new DefensiveProfile(1,4,3,0);
        return new Model(offensiveProfile, defensiveProfile, List.of(lightArmor, shield), spear);
    }

    public Model createArcher() {
        Armor lightArmor = ArmorFactory.lightArmor();
        Weapon handWeapon = WeaponFactory.aHandWeapon();
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5);
        DefensiveProfile defensiveProfile = new DefensiveProfile(1,4,3,0);
        return new Model(offensiveProfile, defensiveProfile, List.of(lightArmor), handWeapon);
    }

}

