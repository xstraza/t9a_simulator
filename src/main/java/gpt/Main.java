package gpt;

import gpt.factory.ModelFactory;

public class Main {

    public static void main(String[] args) {
        ModelFactory modelFactory = new ModelFactory();
        Model spearman = modelFactory.createHighElfFactory().createSpearman();
        Model archer = modelFactory.createHighElfFactory().createArcher();
        Game game = new Game();
        for (int i = 0; i < 100; i++) {
            game.performAttack(spearman, archer);
            System.out.println();
        }
    }
}
