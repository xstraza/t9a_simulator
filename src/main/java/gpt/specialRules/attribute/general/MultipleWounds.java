package gpt.specialRules.attribute.general;

import gpt.specialRules.Attack;
import gpt.specialRules.SpecialRule;
import gpt.specialRules.Event;
import gpt.model.Model;

import java.util.function.Function;
import java.util.function.Supplier;

public class MultipleWounds implements SpecialRule {
    private final Supplier<Integer> woundSupplier;
    private final Function<Model, Integer> conditionalWoundFunction;

    public MultipleWounds(Supplier<Integer> woundSupplier) {
        this.woundSupplier = woundSupplier;
        this.conditionalWoundFunction = model -> 0;
    }

    public MultipleWounds(Function<Model, Integer> conditionalWoundFunction) {
        this.woundSupplier = () -> 0;
        this.conditionalWoundFunction = conditionalWoundFunction;
    }

    @Override
    public void onEvent(Event event, Attack attack, Model defender) {
        if (event != Event.APPLY_MULTIPLE_WOUNDS) {
            return;
        }
        int woundAmount = conditionalWoundFunction.apply(defender) > 0
                ? conditionalWoundFunction.apply(defender)
                : woundSupplier.get();
        attack.setWoundMultiplier(woundAmount);
    }
}
