package gpt.model;

import gpt.weapon.Weapon;
import gpt.attackAttribute.AttackAttribute;

import java.util.List;

public record OffensiveProfile(int attacks,
                               int offensiveSkill,
                               int strength,
                               int armourPenetration,
                               int agility,
                               Weapon weapon,
                               List<AttackAttribute> attackAttributes) {
}
