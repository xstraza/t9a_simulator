import gpt.Attack;
import gpt.Game;
import gpt.attackAttribute.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Model;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    private Unit getUnit(int size, int frontage) {
        return new Unit(size, new ArmyFactory().HighbornElves().createSpearman(), frontage, true);
    }

    private void test(int size, int frontage, int expected) {
        List<Attack> attacks = Game.getTotalAttacks(getUnit(size, frontage));
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attack, null));
        attacks = Game.removeInvalidAttacks(attacks);
        assertEquals(expected, attacks.size());
    }

    @Test
    public void test15models5wide() {
        test(15,5,15);
    }

    @Test
    public void test15models6wide() {
        test(15,6,15);
    }

    @Test
    public void test15models7wide() {
        test(15,7,15);
    }

    @Test
    public void test15models8wide() {
        test(15,8,15);
    }

    @Test
    public void test20models5wide() {
        test(20,5,20);
    }

    @Test
    public void test20models6wide() {
        test(20,6,20);
    }

    @Test
    public void test20models7wide() {
        test(20,7,20);
    }

    @Test
    public void test20models8wide() {
        test(20,8,20);
    }

    @Test
    public void test25models5wide() {
        test(25,5,20);
    }

    @Test
    public void test25models6wide() {
        test(25,6,24);
    }

    @Test
    public void test25models7wide() {
        test(25,7,25);
    }

    @Test
    public void test25models8wide() {
        test(25,8,25);
    }

    @Test
    public void test40models5wide() {
        test(40,5,20);
    }

    @Test
    public void test40models6wide() {
        test(40,6,24);
    }

    @Test
    public void test40models7wide() {
        test(40,7,28);
    }

    @Test
    public void test40models8wide() {
        test(40,8,40);
    }
}
