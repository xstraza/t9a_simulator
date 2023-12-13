package gpt;

import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.model.Model;
import gpt.model.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Game {

    public static void fightARoundOfCombat(Unit unit1, Unit unit2) {
        for (int agility = 10; agility >= 0; agility--) {
            int woundsToUnit2 = attackUnit(unit1, unit2, agility);
            int woundsToUnit1 = attackUnit(unit2, unit1, agility);
            unit1.reduceModels(woundsToUnit1);
            unit2.reduceModels(woundsToUnit2);
        }
    }

    public static int attackUnit(Unit attackers, Unit defenders, int agility) {
        List<Attack> attacks = getTotalAttacks(attackers);
        attacks.forEach(attack -> triggerAttackAttribute(() -> Event.AGILITY_MODIFIER, attack, defenders.getModel()));
        attacks.forEach(attack -> triggerAttackAttribute(() -> Event.CHARGE, attack, defenders.getModel()));
        List<Attack> attacksForAgility = attacks.stream()
                .filter(a -> a.getAgility() == agility)
                .collect(Collectors.toList());
        attacksForAgility.forEach(attack -> triggerAttackAttribute(() -> Event.DETERMINE_ATTACKS, attack, defenders.getModel()));
        List<Attack> validAttacks = removeInvalidAttacks(attacksForAgility);
        if (validAttacks.isEmpty()) {
            return 0;
        }
        System.out.println(attackers.getModel() + " unit has " + validAttacks.size() + " attacks at agility " + agility);
        List<Attack> successfulAttacks = performAttacks(validAttacks, defenders.getModel());
        successfulAttacks.forEach(attack -> triggerAttackAttribute(() -> Event.APPLY_MULTIPLE_WOUNDS, attack, defenders.getModel()));
        return successfulAttacks.stream()
                .mapToInt(Attack::getWoundsCaused)
                .sum();
    }

    public static List<Attack> removeInvalidAttacks(List<Attack> attacks) {
        return attacks.stream()
                .filter(attack -> attack.getRank() <= 2 || attack.getRank() <= 1 + attack.getFier())
                .collect(Collectors.toList());
    }

    public static List<Attack> performAttacks(List<Attack> attacks, Model defender) {
        List<Attack> attacksThatHit = rollToHit(attacks, defender);
        List<Attack> attacksThatWounded = rollToWound(attacksThatHit, defender);
        List<Attack> attacksThatWereNotArmorSaved = rollForArmorSave(attacksThatWounded, defender);
        return rollForSpecialSave(attacksThatWereNotArmorSaved, defender);
    }

    private static List<Attack> rollToHit(List<Attack> attacks, Model defender) {
        List<Attack> attacksThatHit = new ArrayList<>();
        List<Integer> rolls = new ArrayList<>();
        int hits = 0;
        for (Attack attack : attacks) {
            int toHitDifference = attack.getOffensiveSkill() - defender.getDefensiveSkill();
            triggerAttackAttribute(() -> Event.TO_HIT_MODIFIER, attack, defender);
            int toHitModifier = attack.getToHitModifier();
            int neededRoll = determineNeededToHitRoll(toHitDifference) - toHitModifier;

            int roll = new Random().nextInt(6) + 1;
            triggerAttackAttribute(() -> getEventForToHitRoll(roll), attack, defender);
            rolls.add(roll);
            if ((roll >= neededRoll && roll != 1) || roll == 6) {
                hits++;
                attacksThatHit.add(attack);
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("to hit rolls:\n" + collect + "\n" + hits + " hit(s)!\n");
        return attacksThatHit;
    }

    private static List<Attack> rollToWound(List<Attack> attacks, Model defender) {
        List<Attack> attacksThatWounded = new ArrayList<>();
        List<Integer> rolls = new ArrayList<>();
        int wounds = 0;
        for (Attack attack : attacks) {
            int neededRoll = determineNeededRoll(attack.getStrength(), defender.getResilience());
            for (int i = 0; i < attack.getHits(); i++) {
                int roll = new Random().nextInt(6) + 1;
                rolls.add(roll);
                if ((roll >= neededRoll && roll != 1) || roll == 6) {
                    wounds++;
                    attacksThatWounded.add(attack);
                }
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("to wound rolls:\n" + collect + "\n" + wounds + " wound(s)!\n");
        return attacksThatWounded;
    }

    private static List<Attack> rollForArmorSave(List<Attack> attacks, Model defender) {
        List<Attack> attacksNotArmorSaved = new ArrayList<>();
        List<Integer> rolls = new ArrayList<>();
        int savesMade = 0;
        int armor = defender.getArmor();
        for (Attack attack : attacks) {
            int armorPenetration = attack.getArmorPenetration();
            int neededRoll = 7 - armor + armorPenetration;
            if (neededRoll > 6) {
                attacksNotArmorSaved.add(attack);
                continue;
            }
            for (int i = 0; i < attack.getWounds(); i++) {
                int roll = new Random().nextInt(6) + 1;
                rolls.add(roll);
                if (roll >= neededRoll && roll != 1) {
                    savesMade++;
                    attack.setWounds(attack.getWounds() - 1);
                } else {
                    attacksNotArmorSaved.add(attack);
                }
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("armor save rolls:\n" + collect + "\n" + savesMade + " armor save(s) made!\n");
        return attacksNotArmorSaved;
    }

    private static List<Attack> rollForSpecialSave(List<Attack> attacks, Model defender) {
        List<Attack> attacksNotSpecialSaved = new ArrayList<>();
        List<Integer> rolls = new ArrayList<>();
        triggerPersonalProtection(() -> Event.SPECIAL_SAVE, attacks, defender);
        int neededRoll = defender.getSpecialSave();
        if (neededRoll > 6) {
            return attacks;
        }
        int savesMade = 0;
        for (Attack attack : attacks) {
            for (int i = 0; i < attack.getWounds(); i++) {
                int roll = new Random().nextInt(6) + 1;
                rolls.add(roll);
                if (roll >= neededRoll && roll != 1) {
                    savesMade++;
                    attack.setWounds(attack.getWounds() - 1);
                } else {
                    attacksNotSpecialSaved.add(attack);
                }
            }
        }
        String collect = rolls.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("special save rolls:\n" + collect + "\n" + savesMade + " special save(s) made!\n");
        return attacksNotSpecialSaved;
    }

    private static int determineNeededRoll(int attackStrength, int defenderResilience) {
        int difference = attackStrength - defenderResilience;
        if (difference >= 2) return 2;
        if (difference == 1) return 3;
        if (difference == 0) return 4;
        if (difference == -1) return 5;
        return 6; // difference <= -2
    }

    private static int determineNeededToHitRoll(int toHitDifference) {
        if (toHitDifference >= 4) return 2;
        if (toHitDifference >= 1) return 3;
        if (toHitDifference >= -3) return 4;
        if (toHitDifference >= -7) return 5;
        return 6; // toHitDifference < -7
    }

    public static void triggerAttackAttribute(Supplier<Event> eventSupplier, Attack attack, Model defender) {
        Event event = eventSupplier.get();
        attack.getSpecialRules()
                .forEach(attribute ->
                        attribute.onAttackAttributeEvent(event, attack, defender));
    }

    public static void triggerPersonalProtection(Supplier<Event> eventSupplier, List<Attack> attacks, Model defender) {
        Event event = eventSupplier.get();
        defender.getProtections()
                .forEach(protection ->
                        protection.onPersonalProtectionEvent(event, attacks, defender));
    }

    private static Event getEventForToHitRoll(int roll) {
        return switch (roll) {
            case 1 -> Event.ROLLED_1_TO_HIT;
            case 2 -> Event.ROLLED_2_TO_HIT;
            case 3 -> Event.ROLLED_3_TO_HIT;
            case 4 -> Event.ROLLED_4_TO_HIT;
            case 5 -> Event.ROLLED_5_TO_HIT;
            default -> Event.ROLLED_6_TO_HIT;
        };
    }

    public static List<Attack> getTotalAttacks(Unit unit) {
        List<Attack> attacks = new ArrayList<>();
        int maxModelsInUnit = (unit.getNumberOfModels() + unit.getFrontage() - 1) / unit.getFrontage() * unit.getFrontage();
        for (int rank = 1; rank <= maxModelsInUnit / unit.getFrontage(); rank++) {
            for (int file = 1; file <= unit.getFrontage(); file++) {
                Optional<Model> modelAtPosition = unit.getModelAtPosition(rank, file);
                if (modelAtPosition.isPresent()) {
                    Model model = modelAtPosition.get();
                    int fier = unit.isLineFormation() ? 2 : 1;
                    attacks.addAll(model.getAttacks(rank, fier, unit.isCharging()));
                }
            }
        }
        return attacks;
    }
}

