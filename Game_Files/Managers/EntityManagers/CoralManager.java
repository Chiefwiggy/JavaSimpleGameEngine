package Game_Files.Managers.EntityManagers;

import Game_Files.Factories.CoralFactory;

public class CoralManager extends AbstractEntityManager
{

    public CoralManager() { factory = new CoralFactory(); }

    @Override
    public void MoveAll() {}

}
