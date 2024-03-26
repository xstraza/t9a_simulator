package gpt.specialRules.specialAttack;

import gpt.specialRules.Attack;

import java.util.List;

public abstract class SpecialAttack {
    public abstract List<Attack> getAttacks(int strength, int ap);
}
