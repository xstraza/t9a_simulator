package gpt;

import gpt.attack_attribute.AttackEvent;
import gpt.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    public int performAttack(int noAttackers, Model attacker, Model defender) {
        int hits = rollToHit(noAttackers, attacker, defender);
        int wounds = rollToWound(hits, attacker, defender);
        int notArmorSaved = rollForArmorSave(wounds, attacker, defender);
        return rollForSpecialSave(notArmorSaved, attacker, defender);
    }

    private int rollToWound(int noHits, Model attacker, Model defender) {
        int neededRoll = determineNeededRoll(attacker.getStrength(), defender.getResilience());
        int wounds = 0;
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < noHits; i++) {
            int roll = new Random().nextInt(6) + 1;
            rolls.add(roll);
            if ((roll >= neededRoll && roll != 1) || roll == 6) {
                wounds++;
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("to wound rolls:\n" + collect + "\n" + wounds + " wound(s)!\n");
        return wounds;
    }

    private int rollForArmorSave(int savesToMake, Model defender, Model attacker) {
        int neededRoll = 7 - defender.getArmor() + attacker.getArmorPenetration();
        if (neededRoll > 6) {
            return savesToMake;
        }
        int savesMade = 0;
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < savesToMake; i++) {
            int roll = new Random().nextInt(6) + 1;
            rolls.add(roll);
            if (roll >= neededRoll && roll != 1) {
                savesMade++;
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("armor save rolls:\n" + collect + "\n" + savesMade + " armor save(s) made!\n");
        return savesToMake - savesMade;
    }

    private int rollForSpecialSave(int savesToMake, Model attacker, Model defender) {
        int neededRoll = defender.getSpecialSave();
        if (neededRoll > 6) {
            return savesToMake;
        }
        int savesMade = 0;
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < savesToMake; i++) {
            int roll = new Random().nextInt(6) + 1;
            rolls.add(roll);
            if (roll >= neededRoll && roll != 1) {
                savesMade++;
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("special save rolls:\n" + collect + "\n" + savesMade + " special save(s) made!\n");
        return savesToMake - savesMade;
    }

    private int rollToHit(int noAttackers, Model attacker, Model defender) {
        int toHitDifference = attacker.getOffensiveSkill() - defender.getDefensiveSkill();

        attacker.getAttackAttributes()
                .forEach(attackAttribute
                        -> attackAttribute.onAttackEvent(AttackEvent.TO_HIT_MODIFIER, attacker, defender));
        int toHitModifier = attacker.getToHitModifier();
        int neededRoll = determineNeededToHitRoll(toHitDifference) - toHitModifier;

        List<Integer> rolls = new ArrayList<>();
        int hits = 0;
        for (int i = 0; i < attacker.getAttacks() * noAttackers; i++) {
            int roll = new Random().nextInt(6) + 1;
            rolls.add(roll);
            if ((roll >= neededRoll && roll != 1) || roll == 6) {
                hits++;
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("to hit rolls:\n" + collect + "\n" + hits + " hit(s)!\n");
        return hits;
    }

    private int determineNeededRoll(int attackStrength, int defenderResilience) {
        int difference = attackStrength - defenderResilience;
        if (difference >= 2) return 2;
        if (difference == 1) return 3;
        if (difference == 0) return 4;
        if (difference == -1) return 5;
        return 6; // difference <= -2
    }

    private int determineNeededToHitRoll(int toHitDifference) {
        if (toHitDifference >= 4) return 2;
        if (toHitDifference >= 1) return 3;
        if (toHitDifference >= -3) return 4;
        if (toHitDifference >= -7) return 5;
        return 6; // toHitDifference < -7
    }
}

