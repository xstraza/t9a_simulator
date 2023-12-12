package gpt.model;

import gpt.Armor;
import gpt.Weapon;
import gpt.attackAttribute.AttackAttribute;

import java.util.ArrayList;
import java.util.List;


public class ModelBuilder {
    private String name;
    private OffensiveProfile offensiveProfile;
    private DefensiveProfile defensiveProfile;
    private final List<Armor> armor = new ArrayList<>();
    private Weapon weapon;
    private final List<AttackAttribute> attackAttributes = new ArrayList<>();

    public ModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ModelBuilder setOffensiveProfile(OffensiveProfile offensiveProfile) {
        this.offensiveProfile = offensiveProfile;
        return this;
    }

    public ModelBuilder setDefensiveProfile(DefensiveProfile defensiveProfile) {
        this.defensiveProfile = defensiveProfile;
        return this;
    }

    public ModelBuilder addArmor(Armor armorPiece) {
        this.armor.add(armorPiece);
        return this;
    }

    public ModelBuilder setWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public ModelBuilder addAttackAttribute(AttackAttribute attribute) {
        this.attackAttributes.add(attribute);
        return this;
    }

    public Model build() {
        return new Model(name, offensiveProfile, defensiveProfile, armor, weapon, attackAttributes);
    }


}
