package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.GameObjects.Crocodile;
import Game_Files.GameObjects.Fish;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;

import java.util.Iterator;
import java.util.PriorityQueue;

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
    public static BoardEntity Spawn(Pair<Integer> xy, BoardEntities species) {
        return getInstance()._Spawn(xy, species);
    }
    public static void Despawn(BoardEntity entity) { getInstance()._Despawn(entity); }
    public static void MoveAll() { getInstance()._MoveAll(); }

    public EntityFactory entityFactory;
    public PriorityQueue<BoardEntity> priorityQueue;

    public EntityManager() {}

    private void _Initialize() {
        entityFactory = new EntityFactory();
        priorityQueue = new PriorityQueue<>(BoardEntity::compareTo);
    }

    private BoardEntity _Spawn(Pair<Integer> xy, BoardEntities species) {
        BoardEntity entity = entityFactory.GetEntity(xy, species);
        if (species != BoardEntities.CORAL) { priorityQueue.add(entity); }
        return entity;
    }

    private void _Despawn(BoardEntity entity) {
        entityFactory.RecycleEntity(entity);
        priorityQueue.remove(entity);
    }

    private void _MoveAll() {
        // Crocs seem to be replacing fish in the main priorityQueue
        // Also, step is not activating every [ SPACEBAR ] click. This may just be lag.
        System.out.println("<Start of moving all entities in queue>");
        priorityQueue.forEach(System.out::println);
        PriorityQueue<BoardEntity> tempQueue = new PriorityQueue<>(priorityQueue);
        Iterator<BoardEntity> iterator = tempQueue.iterator();
        BoardEntity currentEntity = iterator.next();
        while(currentEntity.getClass().equals(Fish.class)) {
            currentEntity.Move();
            iterator.remove();
            currentEntity = iterator.next();
        }
        iterator = tempQueue.iterator();
        currentEntity = iterator.next();
        while(iterator.hasNext()) {
            currentEntity.Move();
            currentEntity = iterator.next();
        }
        priorityQueue.forEach(System.out::println);
    }

}
