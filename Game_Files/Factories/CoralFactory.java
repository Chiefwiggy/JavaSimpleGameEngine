package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Coral;

public class CoralFactory extends AbstractFactory {

    public CoralFactory() {}

    protected Coral build() {
        return new Coral();
    }

}
