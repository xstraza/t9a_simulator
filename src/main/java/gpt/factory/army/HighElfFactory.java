package gpt.factory.army;

import gpt.Armor;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.general.FightInExtraRank;
import gpt.attackAttribute.general.Harnessed;
import gpt.attackAttribute.general.LightningReflexes;
import gpt.attackAttribute.general.MultipleWounds;
import gpt.attackAttribute.hbe.SwordSworn;
import gpt.factory.ArmorFactory;
import gpt.factory.WeaponFactory;
import gpt.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HighElfFactory {

    public Model createHighbornLancer() {
        List<AttackAttribute> attackAttributesElf = new ArrayList<>();
        attackAttributesElf.add(new LightningReflexes());
        OffensiveProfile offensiveProfileElf = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aLance(), attackAttributesElf);

        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new Harnessed());
        OffensiveProfile offensiveProfileHorse = new OffensiveProfile(1, 3, 3, 0, 4, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 2, armors);

        return new ModelBuilder()
                .setName("Highborn Lancer")
                .addOffensiveProfile(offensiveProfileElf)
                .addOffensiveProfile(offensiveProfileHorse)
                .setDefensiveProfile(defensiveProfile)
                .setType(ModelType.CAVALRY)
                .setHeight(Height.STANDARD)
                .build();
    }

    public Model createEleinReaver() {
        List<AttackAttribute> attackAttributesElf = new ArrayList<>();
        attackAttributesElf.add(new LightningReflexes());
        OffensiveProfile offensiveProfileElf = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aLightLance(), attackAttributesElf);

        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new Harnessed());
        OffensiveProfile offensiveProfileHorse = new OffensiveProfile(1, 3, 3, 0, 4, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 1, armors);

        return new ModelBuilder()
                .setName("Elein Reaver")
                .addOffensiveProfile(offensiveProfileElf)
                .addOffensiveProfile(offensiveProfileHorse)
                .setDefensiveProfile(defensiveProfile)
                .setType(ModelType.CAVALRY)
                .setHeight(Height.STANDARD)
                .build();
    }

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
                .setType(ModelType.INFANTRY)
                .setHeight(Height.STANDARD)
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
                .setType(ModelType.INFANTRY)
                .setHeight(Height.STANDARD)
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
                .setType(ModelType.INFANTRY)
                .setHeight(Height.STANDARD)
                .build();

    }

    public Model createLionGuard() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new MultipleWounds(model -> {
            if (model.getHeight() == Height.LARGE) {
                if (model.getType() == ModelType.BEAST || model.getType() == ModelType.CAVALRY) {
                    return 2;
                }
            } else if (model.getHeight() == Height.GIGANTIC) {
                return 2;
            }
            return 1;
        }));
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 4, 1, 5, WeaponFactory.aGreatWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        armors.add(ArmorFactory.lionsFur());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 5, 3, 0, armors);

        return new ModelBuilder()
                .setName("Lion Guard")
                .addOffensiveProfile(offensiveProfile)
                .setDefensiveProfile(defensiveProfile)
                .setType(ModelType.INFANTRY)
                .setHeight(Height.STANDARD)
                .build();
    }

    public Model createSwordMaster() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new SwordSworn());
        OffensiveProfile offensiveProfile = new OffensiveProfile(2, 6, 3, 0, 6, WeaponFactory.aGreatWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 6, 3, 0, armors);

        return new ModelBuilder()
                .setName("Sword Master")
                .addOffensiveProfile(offensiveProfile)
                .setDefensiveProfile(defensiveProfile)
                .setType(ModelType.INFANTRY)
                .setHeight(Height.STANDARD)
                .build();
    }
}

