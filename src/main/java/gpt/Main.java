package gpt;

import gpt.factory.ArmyFactory;
import gpt.model.Unit;

public class Main {

    public static void main(String[] args) {
        Unit spearmenUnit = new Unit(15, new ArmyFactory().HighbornElves().createSpearman(), 5);
        Unit archerUnit = new Unit(10, new ArmyFactory().HighbornElves().createArcher(), 5);

        Game game = new Game();
        game.fightARoundOfCombat(spearmenUnit, archerUnit);

        System.out.println("Remaining spearmen: " + spearmenUnit.getNumberOfModels());
        System.out.println("Remaining archers: " + archerUnit.getNumberOfModels());
    }
}
