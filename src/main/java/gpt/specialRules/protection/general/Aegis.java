package gpt.specialRules.protection.general;

import gpt.model.Model;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;

import java.util.function.Function;

public class Aegis implements SpecialRule {

    private final int rollNeeded;
    private final Function<Attack, Integer> conditionalRollNeeded;

    public Aegis(int rollNeeded) {
        this.rollNeeded = rollNeeded;
        this.conditionalRollNeeded = attack -> 10;
    }

    public Aegis(Function<Attack, Integer> conditionalRollNeeded) {
        this.rollNeeded = 10;
        this.conditionalRollNeeded = conditionalRollNeeded;
    }

    @Override
    public void onAttackAttributeEvent(Event event, Attack attack, Model defender) {
        if (event == Event.SPECIAL_SAVE) {
            Integer apply = conditionalRollNeeded.apply(attack);
            int min = Math.min(rollNeeded, apply);
            int roll = Math.min(defender.getSpecialSave(), min);
            defender.setSpecialSave(roll);
        }
    }
}
