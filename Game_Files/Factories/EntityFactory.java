package Game_Files.Factories;

import java.lang.reflect.*;

import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
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
            // Possible using method refs??? I did this before looking into method references and
            // lambdas. Will look into later if possible. Might still need some reflection, however.
            try {
                String name = species.name().toLowerCase();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Class<?> entitySubClass = Class.forName("Game_Files.GameObjects." + name);
                Constructor<?> constructor = entitySubClass.getConstructor(Pair.class);
                newEntity = (BoardEntity) constructor.newInstance(xy);
            } catch (Exception ignored) {}
            System.out.println("Entity was created");
        } else { System.out.println("Entity was recycled"); }
        assert newEntity != null;
        newEntity.Initialize(xy, species);
        return newEntity;
    }

    public void RecycleEntity(BoardEntity deadEntity) {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        System.out.println("Entity was trashed");
    }

}
