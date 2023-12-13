package gpt.attackAttribute.general;

import gpt.Attack;
import gpt.attackAttribute.AttackAttribute;
import gpt.attackAttribute.AttackEvent;
import gpt.model.Model;

import java.util.function.Function;
import java.util.function.Supplier;

public class MultipleWounds implements AttackAttribute {
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
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.APPLY_MULTIPLE_WOUNDS) {
            return;
        }
        int woundAmount = conditionalWoundFunction.apply(defender) > 0
                ? conditionalWoundFunction.apply(defender)
                : woundSupplier.get();
        attack.setWoundMultiplier(woundAmount);
    }
}
