package Game_Files.GameObjects.EntityObjects;

import Game_Files.Enums.EntityObjects;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.Sprite;
import Game_Files.Managers.GameEntityManager;
import Game_Files.Managers.GridManager;

import java.util.List;

import static Game_Files.Enums.EntityObjects.*;

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
        // (In the future) This will contain a BFS for finding the closest fish
        List<GridSpace<EntityObject>> availableSpaces = currentGridSpace.GetAdjacentSpaces((space) ->
            space.Contains(EntityObjects.FISH), false);
        System.out.println("Available spaces size:" + availableSpaces.size());
        if (availableSpaces.isEmpty())
        {
            availableSpaces = currentGridSpace.GetAdjacentSpaces(GridSpace::IsEmpty, true);
            if (!availableSpaces.isEmpty())
            {
                GridSpace<EntityObject> space = availableSpaces.get(random.nextInt(availableSpaces.size()));
                GridManager.ClearGridSpace(currentGridSpace);
                SetCurrentGridSpace(null);
                GridManager.FillGridSpace(space, this);
            }
        }
        else
        {
            GridSpace<EntityObject> space = availableSpaces.get(random.nextInt(availableSpaces.size()));
            GridManager.ClearGridSpace(currentGridSpace);
            SetCurrentGridSpace(null);
            Consume(space);
            GridManager.FillGridSpace(space, this);
        }
    }

    private void Consume(GridSpace<EntityObject> spotToConsume)
    {
        GameEntityManager.GetManager(FISH).Despawn(spotToConsume.GetData());
    }

}
