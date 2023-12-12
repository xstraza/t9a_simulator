package gpt;

import gpt.attackAttribute.AttackAttribute;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Weapon {
    int strengthBonus;
    @Getter
    int armorPenetration;
    @Getter
    List<AttackAttribute> attackAttributes = new ArrayList<>();

    public Weapon(int strengthBonus, int armorPenetration, AttackAttribute attribute) {
        this.strengthBonus = strengthBonus;
        this.armorPenetration = armorPenetration;
        this.attackAttributes.add(attribute);
    }

    public Weapon(int strengthBonus, int armorPenetration) {
        this.strengthBonus = strengthBonus;
        this.armorPenetration = armorPenetration;
    }
}
