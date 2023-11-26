package gpt;

import java.util.Random;

public class Game {

    public void performAttack(Model attacker, Model defender) {
        int hits = calculateHits(attacker, defender);
        System.out.println(hits + " hit(s)!");
        for (int i = 0; i < hits; i++) {
            boolean wound = rollToWound(attacker, defender);
            if (!wound) {
                System.out.println("no wound!");
                continue;
            }
            System.out.println("wounded!");
            boolean armorSave = rollForArmorSave(defender, attacker);
            if (armorSave) {
                System.out.println("armour saved!");
                continue;
            }
            System.out.println("armour save failed!");
            boolean specialSave = rollForSpecialSave(defender);
            if (specialSave) {
                System.out.println("special saved!");
                continue;
            }
            System.out.println("special save failed!");
            // 7 & 8. Defender suffers unsaved wounds and loses Health Points
//                        defender.defensiveProfile.reduceHealthPoints(1); // Reduce health by 1 for each unsaved wound
            if (defender.getHealthPoints() <= 0) {
                System.out.println("defender dead!");
                removeCasualty(defender);
            } else {
                System.out.println("defender alive!");
            }
        }
    }

    private boolean rollToWound(Model attacker, Model defender) {
        int neededRoll = determineNeededRoll(attacker.getStrength(), defender.getResilience());
        int roll = new Random().nextInt(6) + 1;
        System.out.println("rolled " + roll + " to wound!");
        return roll >= neededRoll;
    }

    private boolean rollForArmorSave(Model defender, Model attacker) {
        int neededRoll = 7 - defender.getArmor() + attacker.getArmorPenetration();
        if (neededRoll > 6) {
            System.out.println("no armor save available!");
            return false;
        }
        int roll = new Random().nextInt(6) + 1;
        System.out.println("rolled " + roll + " to armor save!");
        return roll >= neededRoll;
    }

    private boolean rollForSpecialSave(Model defender) {
        int neededRoll = defender.getSpecialSave();
        if (neededRoll > 6) {
            System.out.println("no special save available!");
            return false;
        }
        int roll = new Random().nextInt(6) + 1;
        System.out.println("rolled " + roll + " to special save!");
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

    private int calculateHits(Model attacker, Model defender) {
        int hits = 0;
        Random random = new Random();
        int toHitDifference = attacker.getOffensiveSkill() - defender.getDefensiveSkill();

        int neededRoll = determineNeededToHitRoll(toHitDifference);

        for (int i = 0; i < attacker.getAttacks(); i++) {
            int roll = random.nextInt(6) + 1;
            System.out.println("rolled " + roll + " to hit!");
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

