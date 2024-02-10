package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.Fish;

public class FishFactory extends AbstractFactory {

    public FishFactory() {}

    protected Fish CreateNew() {
        return new Fish();
    }

}
