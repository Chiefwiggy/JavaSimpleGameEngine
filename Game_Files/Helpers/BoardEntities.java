package Game_Files.Helpers;

import Game_Files.GameObjects.Coral;
import Game_Files.GameObjects.Fish;
import Game_Files.GameObjects.Crocodile;
import Game_Files.Interfaces.EntityConstructor;

public enum BoardEntities {
    CORAL(Coral::new),
    FISH(Fish::new),
    CROCODILE(Crocodile::new);

    public final EntityConstructor constructor;
    BoardEntities(EntityConstructor constructor) { this.constructor = constructor; }

}
