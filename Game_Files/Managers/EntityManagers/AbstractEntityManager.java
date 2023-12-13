package Game_Files.Managers.EntityManagers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.AbstractFactory;
import Game_Files.GameObjects.EntityObjects.EntityObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityManager extends GameObject
{

    AbstractFactory factory;

    List<EntityObject> liveEntities;

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

    public void MoveAll() { for (EntityObject entity : liveEntities) { entity.Move(); } }

}
