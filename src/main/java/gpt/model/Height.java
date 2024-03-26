package gpt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Height {
    STANDARD(5, 1),
    LARGE(3, 3),
    GIGANTIC(1, 5);

    private final int fullRanks;
    private final int supportingAttacks;
}
