package gpt.util;

import java.util.Random;

public class Roll {

    public static int D6() {
        return new Random().nextInt(6) + 1;
    }

    public static int D3() {
        return new Random().nextInt(3) + 1;
    }
}
