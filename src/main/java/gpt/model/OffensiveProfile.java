package gpt.model;

import gpt.Attack;
import gpt.Weapon;
import gpt.attackAttribute.AttackAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public record OffensiveProfile(int attacks,
                               int offensiveSkill,
                               int strength,
                               int armourPenetration,
                               int agility,
                               Weapon weapon,
                               List<AttackAttribute> attackAttributes) {
}
