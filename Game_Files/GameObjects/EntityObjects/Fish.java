package Game_Files.GameObjects.EntityObjects;

import Game_Files.Helpers.Sprite;
import Game_Files.Enums.EntityObjects;

public class Fish extends EntityObject
{

    public Fish()
    {
        super();
        this.species = EntityObjects.FISH;
        this.sprite = new Sprite("fish", 1.1);
    }

}
