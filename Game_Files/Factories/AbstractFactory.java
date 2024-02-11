package Game_Files.Factories;

import Game_Files.Interfaces.FactoryObject;
import java.util.ArrayDeque;
import java.util.Queue;

public abstract class AbstractFactory {

    private final Queue<FactoryObject> recycledEntities;

    public AbstractFactory() { recycledEntities = new ArrayDeque<>(); }

    public FactoryObject GetEntity()
    {
        FactoryObject newEntity = recycledEntities.poll();
        if (newEntity == null)
        {
            newEntity = CreateNew();
            //System.out.println("Entity was created");
        }
        //else { System.out.println("Entity was recycled"); }
        newEntity.Initialize();
        return newEntity;
    }

    public void RecycleEntity(FactoryObject deadEntity)
    {
        recycledEntities.add(deadEntity);
        deadEntity.Deinitialize();
        //System.out.println("Entity was trashed");
    }

    protected abstract FactoryObject CreateNew();

}
