package Game_Files.Managers.EntityManagers;

import Game_Files.Enums.BoardEntities;
import Game_Files.Factories.FishFactory;
import Game_Files.Helpers.SpawnBehavior;
import Game_Files.Managers.GameEntityManager;
import Game_Files.Managers.StepManager;

import static Game_Files.Enums.SpawnBehaviors.*;

public class FishManager extends AbstractEntityManager
{

    public FishManager() { factory = new FishFactory(); }

    @Override
    public boolean ShouldSpawn()
    {
        if (StepManager.GetStepCount() == 0) { return liveEntities.size() < 12; }
        else
        {
            int mod = 4;
            if (liveEntities.size() < 6) { mod = 2; }
            if (StepManager.GetStepCount() % mod == 0 && !hasSpawned)
            {
                hasSpawned = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public SpawnBehavior SpawnHow()
    {
        if (StepManager.GetStepCount() > 0)
        {
            return new SpawnBehavior(ENTITY_BASED, GameEntityManager.GetManager(BoardEntities.CORAL));
        }
        else { return super.SpawnHow(); }
    }

}
