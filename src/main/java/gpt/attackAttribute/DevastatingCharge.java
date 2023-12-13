package gpt.attackAttribute;

import gpt.Attack;
import gpt.model.Model;
import gpt.util.TriConsumer;

public class DevastatingCharge implements AttackAttribute {

    private final TriConsumer<AttackEvent, Attack, Model> consumer;

    public DevastatingCharge(TriConsumer<AttackEvent,Attack,Model> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        consumer.accept(event, attack, defender);
    }
}
