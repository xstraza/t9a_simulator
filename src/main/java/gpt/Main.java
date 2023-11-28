package gpt;

import gpt.model.Model;
import gpt.factory.ArmyFactory;

public class Main {

    public static void main(String[] args) {
        ArmyFactory armyFactory = new ArmyFactory();
        Model spearman = armyFactory.createHighElfFactory().createSpearman();
        Model archer = armyFactory.createHighElfFactory().createArcher();
        Game game = new Game();
        int wounds = game.performAttack(15, spearman, archer);
        System.out.println(wounds + "\n");
    }
}
