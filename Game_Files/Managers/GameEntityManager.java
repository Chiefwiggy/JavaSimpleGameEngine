package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Enums.BoardEntities;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.GridSpaceSelector;
import Game_Files.Managers.EntityManagers.*;

// Handles logic dealing with EntityObject managers and getting things put on the grid
public class GameEntityManager extends GameObject {

    private static GameEntityManager instance;

    private synchronized static GameEntityManager getInstance() {
        if (instance == null) {
            instance = new GameEntityManager();
        }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    public static void SpawnEntities() { getInstance()._SpawnEntities(); }

    public static AbstractEntityManager GetManager(BoardEntities entityType)
    {
        return getInstance()._GetManager(entityType);
    }

    public static void MoveAll() { getInstance()._MoveAll(); }

    private AbstractEntityManager[] entityManagers;

    private GridSpaceSelector gridSpaceSelector;

    public GameEntityManager() {}

    private void _Initialize()
    {
        entityManagers = new AbstractEntityManager[]
        {
            new CoralManager(), new FishManager(), new CrocodileManager()
        };
        gridSpaceSelector = new GridSpaceSelector();
        // This is the initialize step (4 coral, 12 fish, 4 crocs)
        SpawnEntities();
    }

    private void _SpawnEntities()
    {
        for (AbstractEntityManager entityManager : entityManagers) {
            while (entityManager.ShouldSpawn()) {
                GridSpace<EntityObject> gridSpace = gridSpaceSelector.Select(entityManager.SpawnHow());
                if (gridSpace != null)
                {
                    GridManager.FillGridSpace(gridSpace, entityManager.Spawn());
                }
            }
            entityManager.ResetHasSpawned();
        }
    }

    private AbstractEntityManager _GetManager(BoardEntities entityType)
    {
        return entityManagers[entityType.ordinal()];
    }

    private void _MoveAll()
    {
        for (AbstractEntityManager entityManager : entityManagers) { entityManager.MoveAll(); }
    }

}
