package gpt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class Unit {
    @Getter
    private int numberOfModels;
    @Getter
    private final Model model;
    @Getter @Setter
    private int frontage;
    @Getter
    private boolean charging;

    public Unit(int numberOfModels, Model model, int frontage, boolean charging) {
        this.numberOfModels = numberOfModels;
        this.model = model;
        this.frontage = frontage;
        this.charging = charging;
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

    public Optional<Model> getModelAtPosition(int rank, int file) {
        int totalModels = frontage * (rank - 1) + file;
        if (totalModels <= numberOfModels) {
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }

    public int getMaxRanks() {
        return ((numberOfModels + frontage - 1) / frontage * frontage) / frontage;
    }
}
