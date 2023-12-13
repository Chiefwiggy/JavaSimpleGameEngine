package Game_Files.GameObjects.EntityObjects;

import Game_Files.Helpers.Sprite;

import static Game_Files.Enums.BoardEntities.*;

public class Crocodile extends EntityObject
{

    public Crocodile()
    {
        super();
        this.species = CROCODILE;
        this.sprite = new Sprite("croc", 1.6);
    }

    @Override
    public void Move()
    {
        // This will contain a BFS for finding the closest fish
        // If no fish in adjacent spots (up, down, left, right),
        // it will pick a spot from all 8 available spaces.
    }

}
