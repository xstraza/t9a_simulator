package gpt.model.factory;

import gpt.model.Height;
import gpt.model.Model;
import gpt.model.ModelType;

public abstract class ModelFactory {

    public static Model.ModelBuilder createStandardInfantry() {
        return Model.builder()
                .specialSave(10)
                .type(ModelType.INFANTRY)
                .height(Height.STANDARD);
    }

    public static Model.ModelBuilder createStandardCavalry() {
        return Model.builder()
                .specialSave(10)
                .type(ModelType.CAVALRY)
                .height(Height.STANDARD);
    }
}
