package gpt.specialRules;

import gpt.model.Model;

public interface SpecialRule {
    void onEvent(Event event, Attack attack, Model defender);
}
