package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;
import gpt.specialRules.SpecialRuleType;
import gpt.util.TriConsumer;

public class DevastatingCharge implements SpecialRule {

    private final TriConsumer<Event, Attack, Model> consumer;

    public DevastatingCharge(TriConsumer<Event,Attack,Model> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        consumer.accept(event, attack, defender);
    }

    @Override
    public SpecialRuleType getSpecialRuleType() {
        return SpecialRuleType.DEVASTATING_CHARGE;
    }
}
