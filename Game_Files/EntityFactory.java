package Game_Files;

import java.util.ArrayDeque;
import java.util.Queue;

public class EntityFactory {

    private static Queue<BoardEntity> recycledEntities;

    public EntityFactory() {
        recycledEntities = new ArrayDeque<>();
    }

    public BoardEntity GetEntity(int x, int y, String t) {
        BoardEntity newEntity = recycledEntities.poll();
        if (newEntity == null) {
            if (t.equals("Fish")) { newEntity = new Fish(x, y); }
            else { newEntity = new Crocodile(x, y); }
            System.out.println("Entity was created");
        } else { System.out.println("Entity was recycled"); }
        newEntity.Initialize(x, y, t);
        return newEntity;
    }

    public void RecycleEntity(BoardEntity deadEntity) {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        System.out.println("Entity was trashed");
    }

}
