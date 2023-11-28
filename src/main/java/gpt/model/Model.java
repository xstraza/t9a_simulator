package gpt.model;


import gpt.Armor;
import gpt.Weapon;
import gpt.attack_attribute.AttackAttribute;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Model {
    OffensiveProfile offensiveProfile;
    DefensiveProfile defensiveProfile;
    List<Armor> armor;
    Weapon weapon;
    @Getter List<AttackAttribute> attackAttributes;


    @Getter @Setter
    int toHitModifier = 0;

    protected Model(OffensiveProfile offensiveProfile, DefensiveProfile defensiveProfile, List<Armor> armor, Weapon weapon, List<AttackAttribute> attackAttributes) {
        this.offensiveProfile = offensiveProfile;
        this.defensiveProfile = defensiveProfile;
        this.armor = armor;
        this.weapon = weapon;
        this.attackAttributes = attackAttributes;
    }

    public int getSpecialSave() {
        //todo;
        return 10;
    }

    public int getHealthPoints() {
        //todo
        return defensiveProfile.hp();
    }

    public int getResilience() {
        return defensiveProfile.resilience();
    }

    public int getArmor() {
        return defensiveProfile.armour();
    }

    public int getOffensiveSkill() {
        return offensiveProfile.offensiveSkill();
    }

    public int getDefensiveSkill() {
        return defensiveProfile.defensiveSkill();
    }

    public int getStrength() {
        return offensiveProfile.strength();
    }

    public int getAttacks() {
        return offensiveProfile.attacks();
    }

    public int getArmorPenetration() {
        return offensiveProfile.armourPenetration();
    }
}

