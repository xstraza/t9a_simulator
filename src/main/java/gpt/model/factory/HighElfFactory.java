package gpt.model.factory;

import gpt.armor.Armor;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.attribute.general.FightInExtraRank;
import gpt.specialRules.attribute.general.Harnessed;
import gpt.specialRules.attribute.general.LightningReflexes;
import gpt.specialRules.attribute.general.MultipleWounds;
import gpt.specialRules.attribute.hbe.SwordSworn;
import gpt.armor.ArmorFactory;
import gpt.specialRules.protection.general.Aegis;
import gpt.weapon.WeaponFactory;
import gpt.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighElfFactory extends ModelFactory {

    public static Model createHighbornLancer() {
        List<SpecialRule> attackAttributesElf = new ArrayList<>();
        attackAttributesElf.add(new LightningReflexes());
        OffensiveProfile offensiveProfileElf = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aLance(), attackAttributesElf);

        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new Harnessed());
        OffensiveProfile offensiveProfileHorse = new OffensiveProfile(1, 3, 3, 0, 4, WeaponFactory.aHandWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 2,armors, Collections.emptyList());

        return createStandardCavalry()
                .name("Highborn Lancer")
                .offensiveProfiles(List.of(offensiveProfileElf, offensiveProfileHorse))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createEleinReaver() {
        List<SpecialRule> attackAttributesElf = new ArrayList<>();
        attackAttributesElf.add(new LightningReflexes());
        OffensiveProfile offensiveProfileElf = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aLightLance(), attackAttributesElf);

        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new Harnessed());
        OffensiveProfile offensiveProfileHorse = new OffensiveProfile(1, 3, 3, 0, 4, WeaponFactory.aHandWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 1,armors, Collections.emptyList());

        return createStandardCavalry()
                .name("Elein Reaver")
                .offensiveProfiles(List.of(offensiveProfileElf, offensiveProfileHorse))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSeaGuard() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 3, 0, 5, WeaponFactory.aHandWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0,armors, Collections.emptyList());

        return createStandardInfantry()
                .name("Sea Guard")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSpearman() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        specialRules.add(new FightInExtraRank());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aSpear(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        armors.add(ArmorFactory.shield());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0,armors, Collections.emptyList());

        return createStandardInfantry()
                .name("Spearman")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createArcher() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 4, 3, 0, 5, WeaponFactory.aHandWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.lightArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 4, 3, 0,armors, Collections.emptyList());

        return createStandardInfantry()
                .name("Archer")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createLionGuard() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        specialRules.add(new MultipleWounds(model -> switch(model.getHeight()) {
            case STANDARD -> 1;
            case LARGE -> model.getType() == ModelType.CAVALRY || model.getType() == ModelType.BEAST
                    ? 2
                    : 1;
            case GIGANTIC -> 2;
        }));
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 4, 1, 5, WeaponFactory.aGreatWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        armors.add(ArmorFactory.lionsFur());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 5, 3, 0,armors, Collections.emptyList());

        return createStandardInfantry()
                .name("Lion Guard")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createSwordMaster() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        specialRules.add(new SwordSworn());
        OffensiveProfile offensiveProfile = new OffensiveProfile(2, 6, 3, 0, 6, WeaponFactory.aGreatWeapon(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 6, 3, 0,armors, Collections.emptyList());

        return createStandardInfantry()
                .name("Sword Master")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }

    public static Model createFlameWarden() {
        List<SpecialRule> specialRules = new ArrayList<>();
        specialRules.add(new LightningReflexes());
        specialRules.add(new FightInExtraRank());
        OffensiveProfile offensiveProfile = new OffensiveProfile(1, 5, 3, 0, 6, WeaponFactory.aHalberd(), specialRules);

        List<Armor> armors = new ArrayList<>();
        armors.add(ArmorFactory.heavyArmor());
        List<SpecialRule> protections = new ArrayList<>();
        protections.add(new Aegis(4));

        DefensiveProfile defensiveProfile = new DefensiveProfile(1, 5, 3, 0, armors, protections);

        return createStandardInfantry()
                .name("Flame Warden")
                .offensiveProfiles(List.of(offensiveProfile))
                .defensiveProfile(defensiveProfile)
                .build();
    }
}

