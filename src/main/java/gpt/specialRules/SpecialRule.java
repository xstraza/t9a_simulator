package gpt.specialRules;

import gpt.model.Model;

import java.util.function.Supplier;

public interface SpecialRule {
    void onEvent(Event event, Attack attack, Model defender);

    static void trigger(Supplier<Event> eventSupplier, Attack attack, Model defender) {
        Event event = eventSupplier.get();
        attack.getSpecialRules()
                .forEach(attribute ->
                        attribute.onEvent(event, attack, defender));
        defender.getProtections()
                .forEach(protection ->
                        protection.onEvent(event, attack, defender));
    }
}
