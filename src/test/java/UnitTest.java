import gpt.Game;
import gpt.attackAttribute.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Model;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    @Test
    public void test15models5wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(15, spearman, 5);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(15, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test15models6wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(15, spearman, 6);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
       assertEquals(15, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test15models7wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(15, spearman, 7);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
       assertEquals(15, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test15models8wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(15, spearman, 8);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
       assertEquals(15, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test20models5wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(20, spearman, 5);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }
    @Test
    public void test20models6wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(20, spearman, 6);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }
    @Test
    public void test20models7wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(20, spearman, 7);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test20models8wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(20, spearman, 8);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test25models5wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(25, spearman, 5);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test25models6wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(25, spearman, 6);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(24, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test25models7wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(25, spearman, 7);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(25, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test25models8wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(25, spearman, 8);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(25, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test40models5wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(40, spearman, 5);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(20, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test40models6wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(40, spearman, 6);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(24, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test40models7wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(40, spearman, 7);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(28, new Game().getTotalAttacks(unit));
    }

    @Test
    public void test40models8wide() {
        Model spearman = new ArmyFactory().HighbornElves().createSpearman();
        Unit unit = new Unit(40, spearman, 8);
        new Game().triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, unit.getModel(), null);
        assertEquals(40, new Game().getTotalAttacks(unit));
    }
}
