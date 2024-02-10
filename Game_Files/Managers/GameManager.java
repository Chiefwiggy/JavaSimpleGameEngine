package Game_Files.Managers;

import Game_Files.Helpers.Coordinate;

public class GameManager
{

    private static GameManager instance;

    private synchronized static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    public static void Step() { getInstance()._Step(); }

    private void _Initialize()
    {
        StepManager.Initialize();
        GridManager.Initialize();
        GameEntityManager.Initialize();
        //GridManager.PrintGrid();
    }

    private void _Step()
    {
        System.out.println("Step: " + StepManager.GetStepCount());
        //System.out.println(GridManager.GetGridSpace(new Coordinate<>(0, 0)).toString());
        GameEntityManager.SpawnEntities();
        GameEntityManager.MoveAll();
    }

}
