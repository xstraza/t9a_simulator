package gpt;

import gpt.factory.ArmyFactory;
import gpt.model.Model;
import gpt.model.Unit;

public class Main {

    public static void main(String[] args) {
        int count = 100;
        double sumRemainingUnit1 = 0;
        double sumRemainingUnit2 = 0;
        for (int i = 0; i < count; i++) {
            Unit unit1 = new Unit(15, spearman(), 5, true);
            Unit unit2 = new Unit(10, lancer(), 5, false);
            Game game = new Game();
            game.fightARoundOfCombat(unit1, unit2);

            System.out.println("Remaining " + unit1.getModel() + ": " + unit1.getNumberOfModels());
            System.out.println("Remaining " + unit2.getModel() + ": " + unit2.getNumberOfModels());
            sumRemainingUnit1 += unit1.getNumberOfModels();
            sumRemainingUnit2 += unit2.getNumberOfModels();
        }
        System.out.println();
        double avg1 = sumRemainingUnit1 / count;
        double avg2 = sumRemainingUnit2 / count;
        System.out.println("Average remaining " + spearman() + ": " + avg1);
        System.out.println("Average remaining " + lancer() + ": " + avg2);
    }

    private static Model spearman() {
        return new ArmyFactory().HighbornElves().createSpearman();
    }

    private static Model lancer() {
        return new ArmyFactory().HighbornElves().createHighbornLancer();
    }
}
