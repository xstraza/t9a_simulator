package gpt.factory.army;

import gpt.attack_attribute.FightInExtraRank;
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
                .addAttackAttribute(new LightningReflexes())
                .addAttackAttribute(new FightInExtraRank());
    }

    public Model createSeaGuard() {
        return new ModelBuilder()
                .setName("Sea Guard")
                .setOffensiveProfile(baseElfOffensiveProfile)
                .setDefensiveProfile(new DefensiveProfile(1, 5, 3, 0))
                .setWeapon(WeaponFactory.aSpear())
                .addArmor(ArmorFactory.shield())
                .addArmor(ArmorFactory.lightArmor())
                .addAttackAttribute(new LightningReflexes())
                .build();
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

