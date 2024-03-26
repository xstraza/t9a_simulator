package gpt.model;


import gpt.armor.Armor;
import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class Model {
    String name;
    List<OffensiveProfile> offensiveProfiles;
    DefensiveProfile defensiveProfile;
    @Getter
    ModelType type;
    @Getter
    Height height;
    @Getter
    @Setter
    private int specialSave;

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

    public List<Attack> getAttacks(int rank, int fier, boolean charging, boolean charged, int agility) {
        List<Attack> attacks = new ArrayList<>();
        for (OffensiveProfile offensiveProfile : offensiveProfiles) {
            int numberOfAttacks = rank == 1 ? offensiveProfile.attacks() : height.getSupportingAttacks();
            for (int i = 0; i < numberOfAttacks; i++) {
                Attack attack = new Attack(offensiveProfile, rank, fier, charging, charged, false);
                attacks.add(attack);
            }
        }
        if (charging && agility == 10) {
            OffensiveProfile offensiveProfile = offensiveProfiles.size() == 1
                    ? offensiveProfiles.get(0)
                    : offensiveProfiles.stream()
                    .filter(os -> os.specialRules().contains(SpecialRule.inanimate()))
                    .findFirst().orElse(offensiveProfiles.stream()
                            .filter(os -> os.specialRules().contains(SpecialRule.harnessed()))
                            .findFirst().orElseThrow());
            List<Attack> specialAttacks = offensiveProfile.specialAttacks().stream()
                    .flatMap(specialAttack -> specialAttack.getAttacks(offensiveProfile.strength(), offensiveProfile.armourPenetration()).stream())
                    .collect(Collectors.toList());
            attacks.addAll(specialAttacks);
        }
        return attacks;
    }

    public List<SpecialRule> getProtections() {
        return defensiveProfile.protections();
    }

    public List<Integer> getAgilities() {
        return offensiveProfiles.stream()
                .map(OffensiveProfile::agility)
                .collect(Collectors.toList());

    }
}

