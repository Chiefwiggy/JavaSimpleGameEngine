package Game_Files.Managers.EntityManagers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.AbstractFactory;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.Helpers.SpawnBehavior;

import java.util.ArrayList;
import java.util.List;

import static Game_Files.Enums.SpawnBehaviors.*;

public abstract class AbstractEntityManager extends GameObject {

    protected AbstractFactory factory;

    protected List<EntityObject> liveEntities;

    protected boolean hasSpawned = false;

    public AbstractEntityManager() { liveEntities = new ArrayList<>(); }

    public EntityObject Spawn()
    {
        EntityObject newEntity = (EntityObject) factory.GetEntity();
        liveEntities.add(newEntity);
        return newEntity;
    }

    public void Despawn(EntityObject entity)
    {
        liveEntities.remove(entity);
        factory.RecycleEntity(entity);
    }

    // This is our default, otherwise we override
    public boolean ShouldSpawn() { return liveEntities.size() < 4; }

    public SpawnBehavior<?> SpawnHow() { return new SpawnBehavior<>(RANDOM, 0); }

    public void ResetHasSpawned() { hasSpawned = false; }

    public List<EntityObject> GetEntities() { return liveEntities; }

    public void MoveAll() { for (EntityObject entity : liveEntities) { entity.Move(); } }

}
