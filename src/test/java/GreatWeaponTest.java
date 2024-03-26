import gpt.game.Combat;
import gpt.model.Unit;
import gpt.model.factory.HighElfFactory;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatWeaponTest {

    private Unit aLionGuardUnit() {
        return new Unit(10, HighElfFactory.createLionGuard(), 5, false, false);
    }

    private Unit aSwordMasterUnit() {
        return new Unit(5, HighElfFactory.createSwordMaster(), 5, false, false);
    }

    @Test
    public void testLionGuardInitiative() {
        Unit lionGuardUnit = aLionGuardUnit();
        List<Attack> attacks = Combat.getAttacks(lionGuardUnit, lionGuardUnit, lionGuardUnit.getModel().getAgilities().get(0));
        attacks.forEach(attack -> SpecialRule.trigger(Event.TO_HIT_MODIFIER, attack, HighElfFactory.createArcher()));
        long attacksAtInitiative6 = attacks.stream()
                .filter(a -> a.getAgility() == 5)
                .count();
        assertEquals(attacks.size(), attacksAtInitiative6);
    }

    @Test
    public void testLionGuardPlusToHit() {
        Unit lionGuardUnit = aLionGuardUnit();
        List<Attack> attacks = Combat.getAttacks(lionGuardUnit, lionGuardUnit, lionGuardUnit.getModel().getAgilities().get(0));
        attacks.forEach(attack -> SpecialRule.trigger(Event.TO_HIT_MODIFIER, attack, HighElfFactory.createArcher()));
        long attacksWithoutPlusToHit = attacks.stream()
                .filter(attack -> attack.getToHitModifier() == 0)
                .count();
        assertEquals(attacks.size(), attacksWithoutPlusToHit);
    }

    @Test
    public void testSwordMasterInitiative() {
        Unit swordMasterUnit = aSwordMasterUnit();
        List<Attack> attacks = Combat.getAttacks(swordMasterUnit, swordMasterUnit, swordMasterUnit.getModel().getAgilities().get(0));
        attacks.forEach(attack -> SpecialRule.trigger(Event.TO_HIT_MODIFIER, attack, HighElfFactory.createArcher()));
        long attacksAtInitiative6 = attacks.stream()
                .filter(a -> a.getAgility() == 6)
                .count();
        assertEquals(attacks.size(), attacksAtInitiative6);
    }

    @Test
    public void testSwordMasterPlusToHit() {
        Unit swordMasterUnit = aSwordMasterUnit();
        List<Attack> attacks = Combat.getAttacks(swordMasterUnit, swordMasterUnit, swordMasterUnit.getModel().getAgilities().get(0));
        attacks.forEach(attack -> SpecialRule.trigger(Event.TO_HIT_MODIFIER, attack, HighElfFactory.createArcher()));
        long attacksWithoutPlusToHit = attacks.stream()
                .filter(attack -> attack.getToHitModifier() > 0)
                .count();
        assertEquals(attacks.size(), attacksWithoutPlusToHit);
    }
}
