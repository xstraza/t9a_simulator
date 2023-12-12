package gpt.model;

import lombok.Getter;

public class Unit {
    @Getter
    private int numberOfModels;
    @Getter
    private Model model;

    public Unit(int numberOfModels, Model model) {
        this.numberOfModels = numberOfModels;
        this.model = model;
    }

    public void reduceModels(int wounds) {
        numberOfModels -= wounds;
        if (numberOfModels < 0) {
            numberOfModels = 0;
        }
    }
}
