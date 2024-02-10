package Game_Files.Helpers;

import Game_Files.Enums.SpawnBehaviors;

public class SpawnBehavior<P> {

    public SpawnBehaviors behavior;

    public P parameter;

    public SpawnBehavior(SpawnBehaviors behavior, P parameter)
    {
        this.behavior = behavior;
        this.parameter = parameter;
    }

}
