package dev.alerix.util.filereader;

import dev.alerix.model.Model;
import dev.alerix.util.validator.Validator;

import java.util.List;

public class SReader implements Reader {
    private final Validator validator;
    private final String filepath = "src/main/resources/data.txt";

    public SReader(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<Model> readModels() {
        return null;
    }
}
