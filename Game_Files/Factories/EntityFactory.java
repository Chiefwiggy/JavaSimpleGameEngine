package Game_Files.Factories;

import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;

import java.util.ArrayDeque;
import java.util.Queue;

public class EntityFactory {

    private static Queue<BoardEntity> recycledEntities;

    public EntityFactory() {
        recycledEntities = new ArrayDeque<>();
    }

    public BoardEntity GetEntity(int x, int y, BoardEntities species) {
        BoardEntity newEntity = recycledEntities.poll();
        if (newEntity == null) {
            newEntity = species.constructor.construct(x, y);
            System.out.println("Entity was created");
        } else { System.out.println("Entity was recycled"); }
        newEntity.Initialize(x, y);
        return newEntity;
    }

    public void RecycleEntity(BoardEntity deadEntity) {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        System.out.println("Entity was trashed");
    }

}
