package gpt.specialRules.protection.general;

import gpt.model.Model;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;

import java.util.List;

public class Aegis implements SpecialRule {

    private final int rollNeeded;

    public Aegis(int rollNeeded) {
        this.rollNeeded = rollNeeded;
    }
    @Override
    public void onPersonalProtectionEvent(Event event, List<Attack> attacks, Model defender) {
        if (event == Event.SPECIAL_SAVE) {
            defender.setSpecialSave(rollNeeded);
        }
    }
}
