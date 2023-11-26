package gpt;
import java.util.Random;

public class Game {

    public void performAttack(Attack attack, Model attacker, Model defender) {
        // 2. Determine number of hits (simplified here)
        int hits = calculateHits(attack, attacker, defender);

        // 4. Attacker rolls to wound
        boolean wound = rollToWound(attack, defender);

        if (wound) {
            // 5. Defender makes Armour Save
            boolean armorSave = rollForArmorSave(defender, attack);

            if (!armorSave) {
                // 6. Defender makes Special Save
                boolean specialSave = rollForSpecialSave(defender);

                if (!specialSave) {
                    // 7 & 8. Defender suffers unsaved wounds and loses Health Points
                    defender.healthPoints -= 1; // Assuming 1 wound per attack for simplicity

                    // 9. Defender removes casualties (if health points reach 0)
                    if (defender.getHealthPoints() <= 0) {
                        removeCasualty(defender);
                    }

                    // 10. Panic Tests if necessary (not implemented here)
                }
            }
        }
    }

    private boolean rollToWound(Attack attack, Model defender) {
        int neededRoll = determineNeededRoll(attack.strength, defender.resilience);
        int roll = new Random().nextInt(6) + 1;
        return roll >= neededRoll;
    }

    private boolean rollForArmorSave(Model defender, Attack attack) {
        int neededRoll = 7 - defender.armor + attack.armorPenetration;
        int roll = new Random().nextInt(6) + 1;
        return roll >= neededRoll;
    }

    private boolean rollForSpecialSave(Model defender) {
        int neededRoll = defender.getSpecialSave();
        int roll = new Random().nextInt(6) + 1;
        return roll >= neededRoll;
    }

    private int determineNeededRoll(int attackStrength, int defenderResilience) {
        int difference = attackStrength - defenderResilience;
        if (difference >= 2) return 2;
        if (difference == 1) return 3;
        if (difference == 0) return 4;
        if (difference == -1) return 5;
        return 6; // difference <= -2
    }

    private int calculateHits(Attack attack, Model attacker, Model defender) {
        int hits = 0;
        Random random = new Random();
        int toHitDifference = attacker.offensiveSkill - defender.defensiveSkill;

        int neededRoll = determineNeededToHitRoll(toHitDifference);

        for (int i = 0; i < attack.attackValue; i++) {
            int roll = random.nextInt(6) + 1;
            if ((roll >= neededRoll && roll != 1) || roll == 6) {
                hits++;
            }
        }

        return hits;
    }

    private int determineNeededToHitRoll(int toHitDifference) {
        if (toHitDifference >= 4) return 2;
        if (toHitDifference >= 1) return 3;
        if (toHitDifference >= -3) return 4;
        if (toHitDifference >= -7) return 5;
        return 6; // toHitDifference < -7
    }

    private void removeCasualty(Model defender) {
        // Remove model from the game (not implemented here)
    }
}

