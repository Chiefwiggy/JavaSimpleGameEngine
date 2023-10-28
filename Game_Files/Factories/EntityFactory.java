package Game_Files.Factories;

import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.GameObjects.Fish;
import Game_Files.GameObjects.Crocodile;
import Game_Files.Helpers.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class EntityFactory {

    private static Queue<BoardEntity> recycledEntities;

    public EntityFactory() {
        recycledEntities = new ArrayDeque<>();
    }

    public BoardEntity GetEntity(Pair<Integer> xy, BoardEntities species) {
        BoardEntity newEntity = recycledEntities.poll();
        if (newEntity == null) {
            // Bad code below. If Else should be unnecessary.
            if (species == BoardEntities.FISH) { newEntity = new Fish(xy); }
            else { newEntity = new Crocodile(xy); }
            System.out.println("Entity was created");
        } else { System.out.println("Entity was recycled"); }
        newEntity.Initialize(xy, species);
        return newEntity;
    }

    public void RecycleEntity(BoardEntity deadEntity) {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        System.out.println("Entity was trashed");
    }

}
