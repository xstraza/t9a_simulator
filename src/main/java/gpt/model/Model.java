package gpt.model;


import gpt.Armor;
import gpt.Weapon;
import gpt.attack_attribute.AttackAttribute;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Model {
    String name;
    OffensiveProfile offensiveProfile;
    DefensiveProfile defensiveProfile;
    List<Armor> armor;
    Weapon weapon;
    List<AttackAttribute> attackAttributes;

    @Getter @Setter
    int toHitModifier = 0;

    @Getter @Setter
    int hits = 1;

    @Getter @Setter
    int fightInExtraRanks;

    public List<AttackAttribute> getAttackAttributes() {
        List<AttackAttribute> attributes = new ArrayList<>(attackAttributes);
        attributes.addAll(weapon.getAttackAttributes());
        return attributes;
    }

    protected Model(String name, OffensiveProfile offensiveProfile, DefensiveProfile defensiveProfile, List<Armor> armor, Weapon weapon, List<AttackAttribute> attackAttributes) {
        this.name = name;
        this.offensiveProfile = offensiveProfile;
        this.defensiveProfile = defensiveProfile;
        this.armor = armor;
        this.weapon = weapon;
        this.attackAttributes = attackAttributes;
    }

    public int getSpecialSave() {
        return 10;
    }

    public int getResilience() {
        return defensiveProfile.resilience();
    }

    public int getArmor() {
        return defensiveProfile.armour() + armor.stream().mapToInt(Armor::getArmorValue).sum();
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
        return offensiveProfile.armourPenetration() + weapon.getArmorPenetration();
    }

    @Override
    public String toString() {
        return name;
    }
}

