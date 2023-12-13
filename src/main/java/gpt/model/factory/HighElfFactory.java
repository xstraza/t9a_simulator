package gpt.model.factory;

import gpt.armor.Armor;
import gpt.attack.AttackAttribute;
import gpt.attack.attribute.general.FightInExtraRank;
import gpt.attack.attribute.general.Harnessed;
import gpt.attack.attribute.general.LightningReflexes;
import gpt.attack.attribute.general.MultipleWounds;
import gpt.attack.attribute.hbe.SwordSworn;
import gpt.armor.ArmorFactory;
import gpt.weapon.WeaponFactory;
import gpt.model.*;

import java.util.ArrayList;
import java.util.List;

public class HighElfFactory extends ModelFactory {

    public static Model createHighbornLancer() {
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

        return createStandardCavalry()
                .name("Highborn Lancer")
                .offensiveProfiles(List.of(offensiveProfileElf, offensiveProfileHorse))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createEleinReaver() {
        List<AttackAttribute> attackAttributesElf = new ArrayList<>();
        attackAttributesElf.add(new LightningReflexes());
        OffensiveProfile offensiveProfileElf = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aLightLance(), attackAttributesElf);

        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new Harnessed());
        OffensiveProfile offensiveProfileHorse = new OffensiveProfile(1, 3, 3, 0, 4, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 1, armors);

        return createStandardCavalry()
                .name("Elein Reaver")
                .offensiveProfiles(List.of(offensiveProfileElf, offensiveProfileHorse))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSeaGuard() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 3, 0, 5, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return createStandardInfantry()
                .name("Sea Guard")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSpearman() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new FightInExtraRank());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aSpear(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return createStandardInfantry()
                .name("Spearman")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createArcher() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aHandWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0, armors);

        return createStandardInfantry()
                .name("Archer")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createLionGuard() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new MultipleWounds(model -> switch(model.getHeight()) {
            case STANDARD -> 1;
            case LARGE -> model.getType() == ModelType.CAVALRY || model.getType() == ModelType.BEAST
                    ? 2
                    : 1;
            case GIGANTIC -> 2;
        }));
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 4, 1, 5, WeaponFactory.aGreatWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        armors.add(ArmorFactory.lionsFur());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 5, 3, 0, armors);

        return createStandardInfantry()
                .name("Lion Guard")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSwordMaster() {
        List<AttackAttribute> attackAttributes = new ArrayList<>();
        attackAttributes.add(new LightningReflexes());
        attackAttributes.add(new SwordSworn());
        OffensiveProfile offensiveProfile = new OffensiveProfile(2, 6, 3, 0, 6, WeaponFactory.aGreatWeapon(), attackAttributes);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 6, 3, 0, armors);

        return createStandardInfantry()
                .name("Sword Master")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }
}

