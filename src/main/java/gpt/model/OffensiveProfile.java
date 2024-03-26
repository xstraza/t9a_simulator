package gpt.model;

import gpt.specialRules.SpecialRule;
import gpt.specialRules.specialAttack.SpecialAttack;
import gpt.weapon.Weapon;

import java.util.List;

public record OffensiveProfile(int attacks,
                               int offensiveSkill,
                               int strength,
                               int armourPenetration,
                               int agility,
                               Weapon weapon,
                               List<SpecialRule> specialRules,
                               List<SpecialAttack> specialAttacks) {
}
