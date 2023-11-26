package gpt.factory;

public class ModelFactory {

    public HighElfFactory createHighElfFactory() {
        return new HighElfFactory();
    }

    // Methods for other armies
    // public OrcFactory createOrcFactory() { ... }
    // public DwarfFactory createDwarfFactory() { ... }
    // ...

}

