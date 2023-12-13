package gpt.specialRules;

import gpt.model.Model;

import java.util.List;

public interface SpecialRule {
    default void onAttackAttributeEvent(Event event, Attack attack, Model defender) {}
    default void onPersonalProtectionEvent(Event event, List<Attack> attacks, Model defender) {}
}
