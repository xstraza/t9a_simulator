package gpt;

import gpt.factory.ArmyFactory;
import gpt.model.Unit;

public class Main {

    public static void main(String[] args) {
        ArmyFactory armyFactory = new ArmyFactory();
        // Create units of spearmen and archers
        Unit spearmenUnit = new Unit(15, armyFactory.HighbornElves().createSpearman());
        Unit archerUnit = new Unit(10, armyFactory.HighbornElves().createArcher());

        Game game = new Game();
        game.attackUnit(spearmenUnit, archerUnit);
        game.attackUnit(archerUnit, spearmenUnit);

        System.out.println("Remaining spearmen: " + spearmenUnit.getNumberOfModels());
        System.out.println("Remaining archers: " + archerUnit.getNumberOfModels());
    }
}
