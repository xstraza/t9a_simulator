import gpt.attack.Attack;
import gpt.Game;
import gpt.attack.AttackEvent;
import gpt.factory.ArmyFactory;
import gpt.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatWeaponTest {

    private Unit aLionGuardUnit() {
        return new Unit(10, new ArmyFactory().HighbornElves().createLionGuard(), 5, false);
    }

    private Unit aSwordMasterUnit() {
        return new Unit(5, new ArmyFactory().HighbornElves().createSwordMaster(), 5, false);
    }

    @Test
    public void testLionGuardInitiative() {
        List<Attack> attacks = Game.getTotalAttacks(aLionGuardUnit());
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.TO_HIT_MODIFIER, attack, null));
        long attacksAtInitiative6 = attacks.stream()
                .filter(a -> a.getAgility() == 5)
                .count();
        assertEquals(attacks.size(), attacksAtInitiative6);
    }

    @Test
    public void testLionGuardPlusToHit() {
        List<Attack> attacks = Game.getTotalAttacks(aLionGuardUnit());
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.TO_HIT_MODIFIER, attack, null));
        long attacksWithoutPlusToHit = attacks.stream()
                .filter(attack -> attack.getToHitModifier() == 0)
                .count();
        assertEquals(attacks.size(), attacksWithoutPlusToHit);
    }

    @Test
    public void testSwordMasterInitiative() {
        List<Attack> attacks = Game.getTotalAttacks(aSwordMasterUnit());
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.TO_HIT_MODIFIER, attack, null));
        long attacksAtInitiative6 = attacks.stream()
                .filter(a -> a.getAgility() == 6)
                .count();
        assertEquals(attacks.size(), attacksAtInitiative6);
    }

    @Test
    public void testSwordMasterPlusToHit() {
        List<Attack> attacks = Game.getTotalAttacks(aSwordMasterUnit());
        attacks.forEach(attack -> Game.triggerAttackAttribute(() -> AttackEvent.TO_HIT_MODIFIER, attack, null));
        long attacksWithoutPlusToHit = attacks.stream()
                .filter(attack -> attack.getToHitModifier() > 0)
                .count();
        assertEquals(attacks.size(), attacksWithoutPlusToHit);
    }
}
