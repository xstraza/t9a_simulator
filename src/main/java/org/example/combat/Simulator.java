package org.example.combat;

import org.example.attack.Attack;
import org.example.unit.Unit;

import java.util.List;

public class Simulator {

    private Unit unit1;
    private Unit unit2;
    private int initiative;

    public void simulateCombat() {
        for (initiative = 10; initiative >= 0; initiative--) {
            executePhase(Phase.ALLOCATE_ATTACKS);
            executePhase(Phase.ROLL_TO_HIT);
            executePhase(Phase.ROLL_TO_WOUND);
            executePhase(Phase.ROLL_ARMOUR_SAVES);
            executePhase(Phase.ROLL_SPECIAL_SAVES);
            executePhase(Phase.REMOVE_CASUALTIES);
        }
        executePhase(Phase.CALCULATE_WINNER);
        executePhase(Phase.BREAK_TEST);
    }


    private void executePhase(Phase phase) {
        switch (phase) {
            case ROLL_TO_HIT -> rollToHit();
            case ROLL_TO_WOUND -> rollToWound();
            case ROLL_ARMOUR_SAVES -> rollArmourSaves();
            case ROLL_SPECIAL_SAVES -> rollSpecialSaves();
            case REMOVE_CASUALTIES -> removeCasualties();
            case CALCULATE_WINNER -> calculateWinner();
            case BREAK_TEST -> breakTest();
            default -> throw new IllegalStateException("Unexpected phase: " + phase);
        }
    }
    private void rollToHit() {
        List<Attack> attacks = uni
    }

    private void breakTest() {

    }

    private void calculateWinner() {

    }

    private void removeCasualties() {

    }

    private void rollSpecialSaves() {

    }

    private void rollArmourSaves() {

    }

    private void rollToWound() {

    }


}
