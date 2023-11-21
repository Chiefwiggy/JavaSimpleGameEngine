package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.GameObjects.Crocodile;
import Game_Files.GameObjects.Fish;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;

// Solely in charge of spawning and despawning entities, as well as any actions
// done by entities as a whole. Anything related to the grid will be handled by
// the GridManager class.
public class EntityManager extends GameObject {

    private static EntityManager instance;

    private synchronized static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    @SuppressWarnings("UnusedReturnValue")
    public static BoardEntity Spawn(Pair<Integer> xy, BoardEntities species) {
        return getInstance()._Spawn(xy, species);
    }
    public static void Despawn(BoardEntity entity) { getInstance()._Despawn(entity); }
    public static void MoveAll() { getInstance()._MoveAll(); }

    public EntityFactory entityFactory;
    private FishManager fishManager;
    private CrocodileManager crocodileManager;

    public EntityManager() {}

    private void _Initialize() {
        entityFactory = new EntityFactory();
        fishManager = new FishManager();
        crocodileManager =  new CrocodileManager();
    }

    private BoardEntity _Spawn(Pair<Integer> xy, BoardEntities species) {
        // This would change so each factory spawns within its respective manager class
        BoardEntity entity = entityFactory.GetEntity(xy, species);
        if (species == BoardEntities.FISH) { fishManager.Spawn((Fish) entity); }
        else if (species == BoardEntities.CROCODILE) { crocodileManager.Spawn((Crocodile) entity); }
        return entity;
    }

    private void _Despawn(BoardEntity entity) {
        entityFactory.RecycleEntity(entity);
    }

    private void _MoveAll() {
        fishManager.MoveAllFish();
        crocodileManager.MoveAllCrocs();
    }

}
