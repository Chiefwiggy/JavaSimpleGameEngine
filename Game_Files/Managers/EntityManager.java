package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;

// Solely in charge of spawning and despawning entities. Anything related
// to the grid will be handled by the GridManager class.
public class EntityManager extends GameObject {

    private static EntityManager instance;

    private synchronized static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public EntityFactory entityFactory;

    public static void Initialize() { getInstance()._Initialize(); }

    private void _Initialize() {
        entityFactory = new EntityFactory();
    }

    public static BoardEntity Spawn(Pair<Integer> xy, BoardEntities species) {
        return getInstance()._Spawn(xy, species);
    }

    private BoardEntity _Spawn(Pair<Integer> xy, BoardEntities species) {
        return entityFactory.GetEntity(xy, species);
    }

}
