package gpt.specialRules.attribute.general;

import java.util.function.Supplier;

public class ImpactHits {

    private final Supplier<Integer> hitSupplier;

    public ImpactHits(Supplier<Integer> hitsSupplier) {
        this.hitSupplier = hitsSupplier;
    }

}
