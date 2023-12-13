package gpt.model;


import gpt.armor.Armor;
import gpt.attack.Attack;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Model {
    String name;
    List<OffensiveProfile> offensiveProfiles;
    DefensiveProfile defensiveProfile;
    @Getter
    ModelType type;
    @Getter
    Height height;

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

    public List<Attack> getAttacks(int rank, int fier, boolean charging) {
        List<Attack> attacks = new ArrayList<>();
        for (OffensiveProfile offensiveProfile : offensiveProfiles) {
            for (int i = 0; i < offensiveProfile.attacks(); i++) {
                Attack attack = new Attack(offensiveProfile, rank, fier, charging);
                attacks.add(attack);
            }
        }
        return attacks;
    }
}

