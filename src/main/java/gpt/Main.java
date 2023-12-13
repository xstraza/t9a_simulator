package gpt;

import gpt.model.Model;
import gpt.model.Unit;
import gpt.model.factory.HighElfFactory;

public class Main {

    public static void main(String[] args) {
        int count = 100;
        double sumRemainingUnit1 = 0;
        double sumRemainingUnit2 = 0;
        Model model1 = swordmaster();
        Model model2 = lionguard();
        for (int i = 0; i < count; i++) {
            Unit unit1 = new Unit(10, model1, 5, true);
            Unit unit2 = new Unit(10, model2, 5, false);
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
        System.out.println("Average remaining " + model1 + ": " + avg1);
        System.out.println("Average remaining " + model2 + ": " + avg2);
    }

    private static Model spearman() {
        return HighElfFactory.createSpearman();
    }

    private static Model lancer() {
        return HighElfFactory.createHighbornLancer();
    }

    private static Model swordmaster() {
        return HighElfFactory.createSwordMaster();
    }

    private static Model lionguard() {
        return HighElfFactory.createLionGuard();
    }
}
