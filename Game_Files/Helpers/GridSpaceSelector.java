package Game_Files.Helpers;

// One purpose, return a random GridSpace given some parameters

// If we send in our Grid, it will return any random spot on the board
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Managers.EntityManagers.AbstractEntityManager;
import Game_Files.Managers.GridManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridSpaceSelector {

    Random random = new Random();

    public GridSpaceSelector() {}

    @SuppressWarnings("unchecked")
    public GridSpace<EntityObject> Select(SpawnBehavior spawnBehavior)
    {
        switch (spawnBehavior.behavior) {
            case RANDOM -> { return GridManager.GetGridSpace(); }
            case ENTITY_BASED ->
            {
                AbstractEntityManager manager = (AbstractEntityManager) (spawnBehavior.parameter);
                List<EntityObject> entities = manager.GetEntities();
                AdjacencyMap<EntityObject, GridSpace<EntityObject>> adjacencyMap = new AdjacencyMap<>();
                for (EntityObject entity : entities)
                {
                    List<GridSpace<EntityObject>> availableSpaces = entity.GetAdjacentSpaces();
                    for (GridSpace<EntityObject> gridSpace : availableSpaces)
                    {
                        adjacencyMap.Add(entity, gridSpace);
                    }
                }
                return (GridSpace<EntityObject>) adjacencyMap.GetRandom()[1];
            }
            case FIXED ->
            {
                return GridManager.GetGridSpace((Coordinate<Integer>) spawnBehavior.parameter);
            }
            default -> { return null; }
        }
    }

}