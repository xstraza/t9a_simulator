package gpt.factory.army;

import gpt.attack_attribute.LightningReflexes;
import gpt.factory.ArmorFactory;
import gpt.factory.WeaponFactory;
import gpt.model.DefensiveProfile;
import gpt.model.Model;
import gpt.model.ModelBuilder;
import gpt.model.OffensiveProfile;

public class HighElfFactory {
    private static final OffensiveProfile baseElfOffensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5);
    private static final DefensiveProfile baseElfDefensiveProfile = new DefensiveProfile(1, 4, 3, 0);

    private ModelBuilder aBasicElf() {
        return new ModelBuilder()
                .setOffensiveProfile(baseElfOffensiveProfile)
                .setDefensiveProfile(baseElfDefensiveProfile)
                .setWeapon(WeaponFactory.aHandWeapon())
                .addArmor(ArmorFactory.lightArmor())
                .addAttackAttribute(new LightningReflexes());
    }

    public Model createSpearman() {
        return aBasicElf()
                .setName("Spearman")
                .setWeapon(WeaponFactory.aSpear())
                .addArmor(ArmorFactory.shield())
                .build();
    }

    public Model createArcher() {
        return aBasicElf()
                .setName("Archer")
                .build();
    }

}

