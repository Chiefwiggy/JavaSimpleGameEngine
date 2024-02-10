package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Coral;

public class CoralFactory extends AbstractFactory {

    public CoralFactory() {}

    protected Coral CreateNew() {
        return new Coral();
    }

}
