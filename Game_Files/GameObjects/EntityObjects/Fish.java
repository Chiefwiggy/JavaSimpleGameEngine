package Game_Files.GameObjects.EntityObjects;

import Game_Files.Helpers.Sprite;
import Game_Files.Enums.BoardEntities;

public class Fish extends EntityObject
{

    public Fish()
    {
        super();
        this.species = BoardEntities.FISH;
        this.sprite = new Sprite("fish", 1.1);
    }

}
