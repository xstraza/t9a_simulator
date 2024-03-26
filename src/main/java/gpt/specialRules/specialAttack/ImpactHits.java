package gpt.specialRules.specialAttack;

import gpt.model.OffensiveProfile;
import gpt.specialRules.Attack;
import gpt.weapon.WeaponFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ImpactHits extends SpecialAttack {

    private final Supplier<Integer> hitSupplier;

    public ImpactHits(Supplier<Integer> hitSupplier) {
        this.hitSupplier = hitSupplier;
    }

    @Override
    public List<Attack> getAttacks(int strength, int ap) {
        List<Attack> attacks = new ArrayList<>();
        Integer hits = hitSupplier.get();
        System.out.println("Rolled " + hits + " impact hit(s)!");
        for (int i = 0; i < hits; i++) {
            Attack attack = new Attack(
                    new OffensiveProfile(1, 0, strength, ap, 10, WeaponFactory.aHandWeapon(), Collections.emptyList(), Collections.emptyList()),
                    1, 0, true, false, true);
            attacks.add(attack);
        }
        return attacks;
    }
}
