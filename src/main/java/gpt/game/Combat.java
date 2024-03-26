package gpt.game;

import gpt.model.Model;
import gpt.model.Unit;
import gpt.specialRules.Attack;
import gpt.specialRules.Event;
import gpt.specialRules.SpecialRule;
import gpt.util.Roll;
import gpt.util.Tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Combat {

    public static void combat(Unit unit1, Unit unit2) {
        startOfCombat(unit1, unit2);
        chooseWeapons(unit1, unit2);
        makeWay(unit1, unit2);
        for (int initiative = 10; initiative >= 0; initiative--) {
            rollMeleeAttacks(unit1, unit2, initiative);
        }
        calculateWinnerAndBreakTest(unit1, unit2);
    }

    private static void rollMeleeAttacks(Unit unit1, Unit unit2, int initiative) {
        int woundsToUnit2 = attack(unit1, unit2, initiative);
        int woundsToUnit1 = attack(unit2, unit1, initiative);
        unit1.reduceModels(woundsToUnit1);
        unit2.reduceModels(woundsToUnit2);
    }

    private static int attack(Unit attackers, Unit defenders, int initiative) {
        List<Attack> attacks = getAttacks(attackers, defenders, initiative);
        List<Attack> successfulAttacks = performAttacks(attacks, defenders.getModel());
        successfulAttacks.forEach(attack -> SpecialRule.trigger(() -> Event.APPLY_MULTIPLE_WOUNDS, attack, defenders.getModel()));
        return successfulAttacks.stream()
                .mapToInt(Attack::getWoundsCaused)
                .sum();
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
            SpecialRule.trigger(() -> Event.TO_HIT_MODIFIER, attack, defender);
            int toHitModifier = attack.getToHitModifier();
            int neededRoll = Tables.determineNeededToHitRoll(toHitDifference) - toHitModifier;

            int roll = Roll.D6();
            SpecialRule.trigger(() -> Event.getEventForToHitRoll(roll), attack, defender);
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
            int neededRoll = Tables.determineNeededRoll(attack.getStrength(), defender.getResilience());
            for (int i = 0; i < attack.getHits(); i++) {
                int roll = Roll.D6();
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
                int roll = Roll.D6();
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
        int savesMade = 0;
        for (Attack attack : attacks) {
            for (int i = 0; i < attack.getWounds(); i++) {
                SpecialRule.trigger(() -> Event.SPECIAL_SAVE, attack, defender);
                int neededRoll = defender.getSpecialSave();
                if (neededRoll > 6) {
                    attacksNotSpecialSaved.add(attack);
                    continue;
                }
                int roll = Roll.D6();
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

    public static List<Attack> getAttacks(Unit attackers, Unit defenders, int initiative) {
        List<Attack> totalAttacks = new ArrayList<>();
        for (int rank = 1; rank <= attackers.getMaxRanks(); rank++) {
            for (int file = 1; file <= attackers.getFrontage(); file++) {
                Optional<Model> modelAtPosition = attackers.getModelAtPosition(rank, file);
                if (modelAtPosition.isPresent()) {
                    Model model = modelAtPosition.get();
                    int fier = attackers.isLineFormation() ? 2 : 1;
                    List<Attack> modelAttacks = model.getAttacks(rank, fier, attackers.isCharging());
                    totalAttacks.addAll(modelAttacks);
                }
            }
        }
        totalAttacks.forEach(attack -> SpecialRule.trigger(() -> Event.AGILITY_MODIFIER, attack, defenders.getModel()));
        List<Attack> attacksForAgility = totalAttacks.stream()
                .filter(a -> a.getAgility() == initiative)
                .collect(Collectors.toList());
        attacksForAgility.forEach(attack -> SpecialRule.trigger(() -> Event.DETERMINE_ATTACKS, attack, defenders.getModel()));
        List<Attack> validAttacks =  attacksForAgility.stream()
                .filter(attack -> attack.getRank() <= 2 || attack.getRank() <= 1 + attack.getFier())
                .collect(Collectors.toList());
        System.out.println(attackers.getModel() + " unit has " + validAttacks.size() + " attacks at agility " + initiative);
        return validAttacks;
    }

    private static void calculateWinnerAndBreakTest(Unit unit1, Unit unit2) {

    }

    private static void makeWay(Unit unit1, Unit unit2) {

    }

    private static void chooseWeapons(Unit unit1, Unit unit2) {

    }

    private static void startOfCombat(Unit unit1, Unit unit2) {

    }
}
