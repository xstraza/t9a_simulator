package gpt;

import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.ChargeAgilityBonus;
import gpt.model.OffensiveProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Attack {
    @Getter
    private int offensiveSkill;
    @Getter
    private int strength;
    private int armourPenetration;
    @Getter @Setter
    private int agility;
    @Getter
    private Weapon weapon;
    private List<AttackAttribute> attackAttributes;

    @Getter
    @Setter
    private int toHitModifier = 0;

    @Getter
    @Setter
    private int hits = 1;

    @Getter
    @Setter
    private int wounds = 1;

    @Getter
    @Setter
    private int fier;

    @Getter
    @Setter
    private int rank;
    private int woundMultiplier = 1;

    @Getter
    private boolean charging;

    public Attack(OffensiveProfile offensiveProfile, int rank, int fier, boolean charging) {
        this.offensiveSkill = offensiveProfile.offensiveSkill();
        this.strength = offensiveProfile.strength();
        this.armourPenetration = offensiveProfile.armourPenetration();
        this.agility = offensiveProfile.agility();
        this.weapon = offensiveProfile.weapon();
        this.rank = rank;
        this.attackAttributes = new ArrayList<>(offensiveProfile.attackAttributes());
        this.attackAttributes.add(new ChargeAgilityBonus());
        this.fier = fier;
        this.charging = charging;
    }

    public int getWoundsCaused() {
        return woundMultiplier * wounds;
    }

    public int getArmorPenetration() {
        return armourPenetration + weapon.getArmorPenetration();
    }


    public List<AttackAttribute> getAttackAttributes() {
        return Stream.concat(
                        attackAttributes.stream(),
                        weapon.getAttackAttributes().stream())
                .collect(Collectors.toList());
    }
}
