package gpt;

import gpt.attackAttribute.AttackAttribute;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Weapon {
    @Getter @Setter
    int strength;
    @Getter @Setter
    int armorPenetration;
    @Getter @Setter
    int agility;
    @Getter
    List<AttackAttribute> attackAttributes = new ArrayList<>();

    public Weapon(int strength, int armorPenetration, int agility, AttackAttribute attribute) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.attackAttributes.add(attribute);
    }

    public Weapon(int strength, int armorPenetration, int agility, List<AttackAttribute> attributes) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.attackAttributes.addAll(attributes);
    }

    public Weapon(int strength, int armorPenetration, int agility) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
    }
}
