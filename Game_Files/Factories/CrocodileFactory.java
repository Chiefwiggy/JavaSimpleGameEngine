package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Crocodile;

public class CrocodileFactory extends AbstractFactory {

    public CrocodileFactory() {}

    protected Crocodile CreateNew() {
        return new Crocodile();
    }

}
