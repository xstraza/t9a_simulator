package gpt.factory.army;

import gpt.Armor;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.FightInExtraRank;
import gpt.attackAttribute.LightningReflexes;
import gpt.factory.ArmorFactory;
import gpt.factory.WeaponFactory;
import gpt.model.*;

import java.util.ArrayList;
import java.util.List;

public class HighElfFactory {

    public Model createSeaGuard() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 3, 0, 5, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return new ModelBuilder()
                .setName("Sea Guard")
                .addOffensiveProfile(offensiveProfile)
                .setDefensiveProfile(defensiveProfile)
                .build();
    }

    public Model createSpearman() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new FightInExtraRank());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aSpear(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return new ModelBuilder()
                .setName("Spearman")
                .addOffensiveProfile(offensiveProfile)
                .setDefensiveProfile(defensiveProfile)
                .build();
    }

    public Model createArcher() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return new ModelBuilder()
                .setName("Archer")
                .addOffensiveProfile(offensiveProfile)
                .setDefensiveProfile(defensiveProfile)
                .build();

    }

}

