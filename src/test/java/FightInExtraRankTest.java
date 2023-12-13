import gpt.attack.Attack;
import gpt.Game;
import gpt.attack.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightInExtraRankTest {

    private Unit getSpearmanUnit(int size, int frontage) {
        return new Unit(size, new ArmyFactory().HighbornElves().createSpearman(), frontage, true);
    }

    private void testFier(int size, int frontage, int expected) {
        List<Attack> attacks = Game.getTotalAttacks(getSpearmanUnit(size, frontage));
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attack, null));
        attacks = Game.removeInvalidAttacks(attacks);
        assertEquals(expected, attacks.size());
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
