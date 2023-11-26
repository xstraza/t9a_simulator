package org.example.combat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Phase {
//    START_OF_THE_ROUND_OF_COMBAT,
//    CHOOSE_A_WEAPON,
//    MAKE_WAY,
//    ISSUE_AND_ACCEPT_DUELS,
    ALLOCATE_ATTACKS,
    ROLL_TO_HIT,
    ROLL_TO_WOUND,
    ROLL_ARMOUR_SAVES,
    ROLL_SPECIAL_SAVES,
    REMOVE_CASUALTIES,
    CALCULATE_WINNER,
    BREAK_TEST;

    public Phase getNextPhase(Integer initiative) {
        if (this.equals(BREAK_TEST)) {
            return null;
        }
        return this.equals(REMOVE_CASUALTIES)
                ? initiative <= 0
                    ? CALCULATE_WINNER
                    : ALLOCATE_ATTACKS
                : Phase.values()[this.ordinal() + 1];
    }
}
