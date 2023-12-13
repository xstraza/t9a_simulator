package gpt.armor;

import gpt.specialRules.SpecialRule;
import lombok.Getter;

import java.util.List;

@Getter
public class Armor {
    int armorValue;
    private final List<SpecialRule> protections;

    protected Armor(int armorValue, List<SpecialRule> protections) {
        this.armorValue = armorValue;
        this.protections = protections;
    }
}
