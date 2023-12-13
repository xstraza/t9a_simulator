package gpt.specialRules;

import gpt.model.Model;

public interface SpecialRule {
    void onAttackAttributeEvent(Event event, Attack attack, Model defender);
}
