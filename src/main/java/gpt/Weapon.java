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
    @Getter
    private final WeaponType type;

    public Weapon(int strength, int armorPenetration, int agility, AttackAttribute attribute, WeaponType type) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.attackAttributes.add(attribute);
        this.type = type;
    }

    public Weapon(int strength, int armorPenetration, int agility, List<AttackAttribute> attributes, WeaponType type) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.attackAttributes.addAll(attributes);
        this.type = type;
    }

    public Weapon(int strength, int armorPenetration, int agility, WeaponType type) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.type = type;
    }

    public static Weapon clone(Weapon weapon) {
        return new Weapon(
                weapon.strength,
                weapon.armorPenetration,
                weapon.agility,
                new ArrayList<>(weapon.attackAttributes),
                weapon.type
        );
    }
}
