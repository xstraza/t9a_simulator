package gpt.model;

import gpt.armor.Armor;
import gpt.specialRules.SpecialRule;

import java.util.List;

public record DefensiveProfile(int hp,
                               int defensiveSkill,
                               int resilience,
                               int armour,
                               List<Armor> armors,
                               List<SpecialRule> protections) {
}
