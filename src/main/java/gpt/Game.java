package gpt;

import gpt.attack_attribute.AttackEvent;
import gpt.model.Model;
import gpt.model.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Game {

    public void fightARoundOfCombat(Unit unit1, Unit unit2) {
        System.out.println(unit1.getModel() + " attacking");
        attackUnit(unit1, unit2);
        System.out.println(unit2.getModel() + " attacking");
        attackUnit(unit2, unit1);
    }

    public void attackUnit(Unit attackers, Unit defenders) {
        triggerAttackAttribute(() -> AttackEvent.DETERMINE_ATTACKS, attackers.getModel(), defenders.getModel());
        int noAttacks = getTotalAttacks(attackers);
        System.out.println(attackers.getModel() + " unit has " + noAttacks + " attacks");
        int wounds = performAttack(noAttacks, attackers.getModel(), defenders.getModel());
        defenders.reduceModels(wounds);
    }

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

    private int rollForArmorSave(int savesToMake, Model attacker, Model defender) {
        int armor = defender.getArmor();
        int armorPenetration = attacker.getArmorPenetration();
        int neededRoll = 7 - armor + armorPenetration;
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

        triggerAttackAttribute(() -> AttackEvent.TO_HIT_MODIFIER, attacker, defender);
        int toHitModifier = attacker.getToHitModifier();
        int neededRoll = determineNeededToHitRoll(toHitDifference) - toHitModifier;

        List<Integer> rolls = new ArrayList<>();
        int hits = 0;
        for (int i = 0; i < attacker.getAttacks() * noAttackers; i++) {
            int roll = new Random().nextInt(6) + 1;
            triggerAttackAttribute(() -> getEventForToHitRoll(roll), attacker, defender);
            rolls.add(roll);
            if ((roll >= neededRoll && roll != 1) || roll == 6) {
                hits += attacker.getHits();
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

    private void triggerAttackAttribute(Supplier<AttackEvent> eventSupplier, Model attacker, Model defender) {
        AttackEvent event = eventSupplier.get();
        attacker.getAttackAttributes()
                .forEach(attribute ->
                        attribute.onAttackEvent(event, attacker, defender));
    }

    private AttackEvent getEventForToHitRoll(int roll) {
        return switch (roll) {
            case 1 -> AttackEvent.ROLLED_1_TO_HIT;
            case 2 -> AttackEvent.ROLLED_2_TO_HIT;
            case 3 -> AttackEvent.ROLLED_3_TO_HIT;
            case 4 -> AttackEvent.ROLLED_4_TO_HIT;
            case 5 -> AttackEvent.ROLLED_5_TO_HIT;
            default -> AttackEvent.ROLLED_6_TO_HIT;
        };
    }

    public int getTotalAttacks(Unit unit) {
        int totalAttacks = 0;

        // Calculate attacks for the first rank if enough models are alive.
        if (unit.getNumberOfModels() >= unit.getFrontage()) {
            totalAttacks += unit.getFrontage() * unit.getModel().getAttacks(); // All models in the first rank get model.attacks() attacks.
        } else if (unit.getNumberOfModels() > 0) {
            totalAttacks += unit.getNumberOfModels() * unit.getModel().getAttacks(); // If not enough for a full rank, use available models.
        }

        // Calculate attacks for the second rank if there are enough models.
        if (unit.getNumberOfModels() > unit.getFrontage()) {
            int remainingModels = unit.getNumberOfModels() - unit.getFrontage();
            totalAttacks += Math.min(remainingModels, unit.getFrontage()); // All models in the second rank get 1 attack.
        }

        // Calculate attacks for the third rank in line formation if there are enough models.
        if (unit.isLineFormation() && unit.getNumberOfModels() > (2 * unit.getFrontage())) {
            int remainingModels = unit.getNumberOfModels() - (2 * unit.getFrontage());
            totalAttacks += Math.min(remainingModels, unit.getFrontage()); // All models in the third rank get 1 attack.
        }

        return totalAttacks;
    }
}

