import gpt.game.Combat;
import gpt.model.Unit;
import gpt.model.factory.HighElfFactory;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HarnessedTest {

    private Unit getLancerUnit(int size, int frontage) {
        return new Unit(size, HighElfFactory.createHighbornLancer(), frontage, true, false);
    }

    private void testHarnessed(int size, int frontage, int expected) {
        Unit lancerUnit = getLancerUnit(size, frontage);
        List<Attack> attacks = Combat.getAttacks(lancerUnit, lancerUnit, lancerUnit.getModel().getAgilities().get(0) + 1);
        List<Attack> attacksHorses = Combat.getAttacks(lancerUnit, lancerUnit, lancerUnit.getModel().getAgilities().get(1) + 1);
        attacks.forEach(attack -> SpecialRule.trigger(Event.DETERMINE_ATTACKS, attack, HighElfFactory.createArcher()));
        attacksHorses.forEach(attack -> SpecialRule.trigger(Event.DETERMINE_ATTACKS, attack, HighElfFactory.createArcher()));
        assertEquals(expected, attacksHorses.size() + attacks.size());
    }

    @Test
    public void test5models5wide() {
        testHarnessed(5,5,10);
    }

    @Test
    public void test6models5wide() {
        testHarnessed(6,5,11);
    }

    @Test
    public void test7models5wide() {
        testHarnessed(7,5,12);
    }

    @Test
    public void test8models5wide() {
        testHarnessed(8,5,13);
    }

    @Test
    public void test9models5wide() {
        testHarnessed(9,5,14);
    }

    @Test
    public void test10models5wide() {
        testHarnessed(10,5,15);
    }

    @Test
    public void test11models5wide() {
        testHarnessed(11,5,15);
    }
}
