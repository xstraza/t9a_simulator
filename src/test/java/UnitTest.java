import gpt.Attack;
import gpt.Game;
import gpt.attackAttribute.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    private Unit getSpearmanUnit(int size, int frontage) {
        return new Unit(size, new ArmyFactory().HighbornElves().createSpearman(), frontage, true);
    }

    private Unit getLancerUnit(int size, int frontage) {
        return new Unit(size, new ArmyFactory().HighbornElves().createHighbornLancer(), frontage, true);
    }

    private void testFier(int size, int frontage, int expected) {
        List<Attack> attacks = Game.getTotalAttacks(getSpearmanUnit(size, frontage));
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attack, null));
        attacks = Game.removeInvalidAttacks(attacks);
        assertEquals(expected, attacks.size());
    }

    private void testHarnessed(int size, int frontage, int expected) {
        List<Attack> attacks = Game.getTotalAttacks(getLancerUnit(size, frontage));
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attack, null));
        attacks = Game.removeInvalidAttacks(attacks);
        assertEquals(expected, attacks.size());
    }

    @Test
    public void ltest5models5wide() {
        testHarnessed(5,5,10);
    }

    @Test
    public void ltest6models5wide() {
        testHarnessed(6,5,11);
    }

    @Test
    public void ltest7models5wide() {
        testHarnessed(7,5,12);
    }

    @Test
    public void ltest8models5wide() {
        testHarnessed(8,5,13);
    }

    @Test
    public void ltest9models5wide() {
        testHarnessed(9,5,14);
    }

    @Test
    public void ltest10models5wide() {
        testHarnessed(10,5,15);
    }

    @Test
    public void ltest11models5wide() {
        testHarnessed(11,5,15);
    }

    @Test
    public void test15models5wide() {
        testFier(15,5,15);
    }

    @Test
    public void test15models6wide() {
        testFier(15,6,15);
    }

    @Test
    public void test15models7wide() {
        testFier(15,7,15);
    }

    @Test
    public void test15models8wide() {
        testFier(15,8,15);
    }

    @Test
    public void test20models5wide() {
        testFier(20,5,20);
    }

    @Test
    public void test20models6wide() {
        testFier(20,6,20);
    }

    @Test
    public void test20models7wide() {
        testFier(20,7,20);
    }

    @Test
    public void test20models8wide() {
        testFier(20,8,20);
    }

    @Test
    public void test25models5wide() {
        testFier(25,5,20);
    }

    @Test
    public void test25models6wide() {
        testFier(25,6,24);
    }

    @Test
    public void test25models7wide() {
        testFier(25,7,25);
    }

    @Test
    public void test25models8wide() {
        testFier(25,8,25);
    }

    @Test
    public void test40models5wide() {
        testFier(40,5,20);
    }

    @Test
    public void test40models6wide() {
        testFier(40,6,24);
    }

    @Test
    public void test40models7wide() {
        testFier(40,7,28);
    }

    @Test
    public void test40models8wide() {
        testFier(40,8,40);
    }
}
