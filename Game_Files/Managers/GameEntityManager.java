package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Managers.EntityManagers.*;

// Handles logic dealing with EntityManagers and getting things put on the grid
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

    public static void MoveAll() { getInstance()._MoveAll(); }

    private AbstractEntityManager[] entityManagers;

    public GameEntityManager() {}

    private void _Initialize()
    {
        entityManagers = new AbstractEntityManager[]
        {
            new FishManager(), new CrocodileManager(), new CoralManager()
        };
        // This is the initialize step (12 fish, 4 crocs, 4 coral)
        for (int i = 0; i < 12; i++) { _SpawnEntities(); }
    }

    private void _SpawnEntities()
    {
        for (AbstractEntityManager entityManager : entityManagers)
        {
            if (entityManager.ShouldSpawn())
            {
                GridManager.FillGridSpace(entityManager.Spawn());
            }
        }
    }

    private void _MoveAll()
    {
        for (AbstractEntityManager entityManager : entityManagers) { entityManager.MoveAll(); }
    }

}
