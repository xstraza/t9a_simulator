package gpt.model;

import lombok.Getter;
import lombok.Setter;

public class Unit {
    @Getter
    private int numberOfModels;
    @Getter
    private final Model model;
    @Getter @Setter
    private int frontage;

    public Unit(int numberOfModels, Model model, int frontage) {
        this.numberOfModels = numberOfModels;
        this.model = model;
        this.frontage = frontage;
    }

    public void reduceModels(int wounds) {
        numberOfModels -= wounds;
        if (numberOfModels < 0) {
            numberOfModels = 0;
        }
    }

    public boolean isLineFormation() {
        return this.frontage >= 8;
    }

    public int getFullRanksAfterFirst() {
        if (numberOfModels <= frontage) {
            return 0; // Not enough models for any full ranks after the first.
        } else {
            int remainingModels = numberOfModels - frontage; // Subtract the first rank.
            return remainingModels / frontage;
        }
    }

    public int getRankBonus() {
        if (isLineFormation()) {
            return 0;
        }
        return Math.min(getFullRanksAfterFirst(), 3);
    }

}