package gpt.armor;

import lombok.Getter;

@Getter
public class Armor {
    int armorValue;

    protected Armor(int armorValue) {
        this.armorValue = armorValue;
    }
}
