package gpt.model;

import gpt.Armor;
import gpt.Weapon;
import gpt.attackAttribute.AttackAttribute;

import java.util.ArrayList;
import java.util.List;


public class ModelBuilder {
    private String name;
    private final List<OffensiveProfile> offensiveProfiles = new ArrayList<>();
    private DefensiveProfile defensiveProfile;

    public ModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ModelBuilder addOffensiveProfile(OffensiveProfile offensiveProfile) {
        this.offensiveProfiles.add(offensiveProfile);
        return this;
    }

    public ModelBuilder setDefensiveProfile(DefensiveProfile defensiveProfile) {
        this.defensiveProfile = defensiveProfile;
        return this;
    }

    public Model build() {
        return new Model(name, offensiveProfiles, defensiveProfile);
    }


}
