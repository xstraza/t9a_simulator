import gpt.Attack;
import gpt.Game;
import gpt.attackAttribute.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HarnessedTest {

    private Unit getLancerUnit(int size, int frontage) {
        return new Unit(size, new ArmyFactory().HighbornElves().createHighbornLancer(), frontage, true);
    }

    private void testHarnessed(int size, int frontage, int expected) {
        List<Attack> attacks = Game.getTotalAttacks(getLancerUnit(size, frontage));
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attack, null));
        attacks = Game.removeInvalidAttacks(attacks);
        assertEquals(expected, attacks.size());
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
