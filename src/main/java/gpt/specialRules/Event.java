package gpt.specialRules;

public enum Event {
    ROLLED_1_TO_HIT,
    ROLLED_2_TO_HIT,
    ROLLED_3_TO_HIT,
    ROLLED_4_TO_HIT,
    ROLLED_5_TO_HIT,
    ROLLED_6_TO_HIT,
    TO_HIT_MODIFIER,
    DETERMINE_ATTACKS,
    CHARGE,
    AGILITY_MODIFIER,
    APPLY_MULTIPLE_WOUNDS,
    SPECIAL_SAVE;

    public static Event getEventForToHitRoll(int roll) {
        return switch (roll) {
            case 1 -> Event.ROLLED_1_TO_HIT;
            case 2 -> Event.ROLLED_2_TO_HIT;
            case 3 -> Event.ROLLED_3_TO_HIT;
            case 4 -> Event.ROLLED_4_TO_HIT;
            case 5 -> Event.ROLLED_5_TO_HIT;
            default -> Event.ROLLED_6_TO_HIT;
        };
    }
}
