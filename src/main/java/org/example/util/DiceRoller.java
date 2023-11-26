package org.example.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DiceRoller {
    private static final Random random = new Random();

    public static int rollD6() {
        return random.nextInt(6) + 1;
    }

    public static int rollD3() {
        return random.nextInt(3) + 1;
    }

    public static int roll2D6(int maximised, int minimised) {
        int diceToRoll = 2 + maximised + minimised;
        ArrayList<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < diceToRoll; i++) {
            rolls.add(rollD6());
        }
        Collections.sort(rolls);
        for (int i = 0; i < maximised; i++) {
            if (!rolls.isEmpty()) rolls.remove(0);
        }
        for (int i = 0; i < minimised; i++) {
            if (!rolls.isEmpty()) rolls.remove(rolls.size() - 1);
        }
        return rolls.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int roll2D6() {
        return rollD6() + rollD6();
    }


}

