package gpt.weapon;

import gpt.attackAttribute.AttackAttribute;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Weapon {
    @Setter
    int strength;
    @Setter
    int armorPenetration;
    @Setter
    int agility;
    @Setter
    int agilityMultiplier;
    private final List<AttackAttribute> attackAttributes;
    private final WeaponType type;

    protected Weapon(int strength, int armorPenetration, int agility, int agilityMultiplier, List<AttackAttribute> attributes, WeaponType type) {
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.agility = agility;
        this.agilityMultiplier = agilityMultiplier;
        this.attackAttributes = new ArrayList<>(attributes);
        this.type = type;
    }

    public Weapon copy() {
        return new Weapon(
                this.strength,
                this.armorPenetration,
                this.agility,
                this.agilityMultiplier,
                new ArrayList<>(this.attackAttributes),
                this.type
        );
    }
}
