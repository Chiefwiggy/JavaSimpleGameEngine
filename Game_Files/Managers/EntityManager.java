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
        // Need to fix issue where we add dead fish to new queue
        System.out.println("<Start of moving all entities in queue>");
        PriorityQueue<BoardEntity> newPriorityQueue = new PriorityQueue<>(BoardEntity::compareTo);
        Iterator<BoardEntity> iterator = priorityQueue.iterator();
        BoardEntity currentEntity = iterator.next();
        while(currentEntity.getClass().equals(Fish.class)) {
            newPriorityQueue.add(currentEntity);
            currentEntity.Move();
            iterator.remove();
            currentEntity = iterator.next();
        }
        System.out.println("<<After all fish have moved>>");
        priorityQueue.forEach(System.out::println);
        System.out.println("<<Before all crocs move>>");
        iterator = priorityQueue.iterator();
        currentEntity = iterator.next();
        newPriorityQueue.add(currentEntity);
        while(iterator.hasNext()) {
            System.out.println("HERE");
            currentEntity.Move();
            currentEntity = iterator.next();
            newPriorityQueue.add(currentEntity);
        }
        System.out.println("<<<Compare this>>>");
        priorityQueue.forEach(System.out::println);
        priorityQueue = newPriorityQueue;
        System.out.println("<<<To this>>>");
        priorityQueue.forEach(System.out::println);
        System.out.println("<End of moving all entities in queue>");
    }

}
