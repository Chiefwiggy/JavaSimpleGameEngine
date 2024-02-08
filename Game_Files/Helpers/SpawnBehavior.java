package Game_Files.Helpers;

import Game_Files.Enums.SpawnBehaviors;
import Game_Files.Interfaces.BehaviorParameter;

public class SpawnBehavior {

    public SpawnBehaviors behavior;

    public BehaviorParameter parameter;

    public SpawnBehavior(SpawnBehaviors behavior, BehaviorParameter parameter)
    {
        this.behavior = behavior;
        this.parameter = parameter;
    }

}
