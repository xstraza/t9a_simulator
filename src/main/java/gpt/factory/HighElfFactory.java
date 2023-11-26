package gpt.factory;

import gpt.DefensiveProfile;
import gpt.Model;
import gpt.OffensiveProfile;

public class HighElfFactory {

    public Model createSpearman() {
        return new Model(
                1,4,3,0,5,1,4,3,0
        );
    }

    public Model createArcher() {
        return new Model(
                1,4,3,0,5,1,4,3,0
        );
    }

    // Other High Elf model types
}

