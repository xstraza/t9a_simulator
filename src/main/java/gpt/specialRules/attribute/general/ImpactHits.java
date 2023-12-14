package gpt.specialRules.attribute.general;

import gpt.model.Model;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;

import java.util.function.Supplier;

public class ImpactHits implements SpecialRule {

    private final Supplier<Integer> hitSupplier;

    public ImpactHits(Supplier<Integer> hitsSupplier) {
        this.hitSupplier = hitsSupplier;
    }

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        //todo
    }
}
