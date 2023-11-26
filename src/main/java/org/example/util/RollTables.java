package org.example.util;

public class RollTables {

    private int calculateToHitRoll(int offensiveSkill, int defensiveSkill) {
        int difference = offensiveSkill - defensiveSkill;

        if (difference >= 4) {
            return 2;
        } else if (difference >= 1) {
            return 3;
        } else if (difference >= -3) {
            return 4;
        } else if (difference >= -7) {
            return 5;
        } else {
            return 6;
        }
    }
}
