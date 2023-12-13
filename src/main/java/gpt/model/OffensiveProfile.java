package gpt.model;

import gpt.weapon.Weapon;
import gpt.specialRules.SpecialRule;

import java.util.List;

public record OffensiveProfile(int attacks,
                               int offensiveSkill,
                               int strength,
                               int armourPenetration,
                               int agility,
                               Weapon weapon,
                               List<SpecialRule> specialRules) {
}
