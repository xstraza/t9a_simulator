package gpt.factory;

import gpt.factory.army.HighElfFactory;

public class ArmyFactory {

    public HighElfFactory HighbornElves() {
        return new HighElfFactory();
    }

    // Methods for other armies
    // public OrcFactory createOrcFactory() { ... }
    // public DwarfFactory createDwarfFactory() { ... }
    // ...

}

