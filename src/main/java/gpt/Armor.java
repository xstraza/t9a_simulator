package gpt;

import lombok.Getter;

public class Armor {
    @Getter
    int armorValue;

    public Armor(int armorValue) {
        this.armorValue = armorValue;
    }
}
