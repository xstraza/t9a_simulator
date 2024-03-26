package gpt.specialRules;

import gpt.model.OffensiveProfile;
import gpt.weapon.Weapon;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Attack {
    @Getter
    private int offensiveSkill;
    private int strength;
    private int armourPenetration;
    private int agility;

    public void setAgility(int agility) {
        this.agility = Math.min(agility, 10);
    }
    @Getter
    private Weapon weapon;
    private List<SpecialRule> specialRules;

    @Getter
    @Setter
    private int toHitModifier = 0;

    @Getter
    @Setter
    private boolean autoHit;

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
    @Setter
    private int woundMultiplier = 1;

    @Getter
    private boolean charging;
    @Getter
    private boolean charged;
    @Getter
    private boolean flaming = false;

    public Attack(OffensiveProfile offensiveProfile, int rank, int fier, boolean charging, boolean charged, boolean autoHit) {
        this.offensiveSkill = offensiveProfile.offensiveSkill();
        this.strength = offensiveProfile.strength();
        this.armourPenetration = offensiveProfile.armourPenetration();
        this.agility = offensiveProfile.agility();
        this.weapon = offensiveProfile.weapon().copy();
        this.rank = rank;
        this.specialRules = new ArrayList<>(offensiveProfile.specialRules());
        this.specialRules.add(SpecialRule.chargeAgilityBonus());
        this.fier = fier;
        this.charging = charging;
        this.charged = charged;
        this.autoHit = autoHit;
    }

    public int getWoundsCaused() {
        return woundMultiplier * wounds;
    }

    public int getArmorPenetration() {
        return armourPenetration + weapon.getArmorPenetration();
    }


    public List<SpecialRule> getSpecialRules() {
        return Stream.concat(
                        specialRules.stream(),
                        weapon.getSpecialRules().stream())
                .collect(Collectors.toList());
    }

    public int getAgility() {
        return (agility + weapon.getAgility()) * weapon.getAgilityMultiplier();
    }

    public int getStrength() {
        return strength + weapon.getStrength();
    }
}
