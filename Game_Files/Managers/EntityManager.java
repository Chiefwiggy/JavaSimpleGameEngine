package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;

import java.util.PriorityQueue;

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
    public PriorityQueue<BoardEntity> priorityQueue;

    public static void Initialize() { getInstance()._Initialize(); }

    private void _Initialize() {
        entityFactory = new EntityFactory();
        priorityQueue = new PriorityQueue<>();
    }

    public static BoardEntity Spawn(Pair<Integer> xy, BoardEntities species) {
        return getInstance()._Spawn(xy, species);
    }

    private BoardEntity _Spawn(Pair<Integer> xy, BoardEntities species) {
        BoardEntity entity = entityFactory.GetEntity(xy, species);
        priorityQueue.add(entity);
        return entity;
    }

    public static void Despawn(BoardEntity entity) {
        getInstance()._Despawn(entity);
    }

    private void _Despawn(BoardEntity entity) {
        entityFactory.RecycleEntity(entity);
        priorityQueue.remove(entity);
    }

}
