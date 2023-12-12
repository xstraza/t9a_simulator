package gpt.model;


import gpt.Armor;
import gpt.Attack;
import gpt.Weapon;
import gpt.attackAttribute.AttackAttribute;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Model {
    String name;
    List<OffensiveProfile> offensiveProfiles;
    DefensiveProfile defensiveProfile;

    protected Model(String name, List<OffensiveProfile> offensiveProfiles, DefensiveProfile defensiveProfile) {
        this.name = name;
        this.offensiveProfiles = offensiveProfiles;
        this.defensiveProfile = defensiveProfile;
    }

    public int getSpecialSave() {
        return 10;
    }

    public int getResilience() {
        return defensiveProfile.resilience();
    }

    public int getArmor() {
        return defensiveProfile.armour() + defensiveProfile.armors().stream().mapToInt(Armor::getArmorValue).sum();
    }

    @Override
    public String toString() {
        return name;
    }

    public int getDefensiveSkill() {
        return defensiveProfile.defensiveSkill();
    }

    public List<Attack> getAttacks(int rank, int fier) {
        List<Attack> attacks = new ArrayList<>();
        for (OffensiveProfile offensiveProfile : offensiveProfiles) {
            for (int i = 0; i < offensiveProfile.attacks(); i++) {
                Attack attack = new Attack(offensiveProfile, rank, fier);
                attacks.add(attack);
            }
        }
        return attacks;
    }
}

