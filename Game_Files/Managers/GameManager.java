package Game_Files.Managers;

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
        GridManager.PrintGrid();
    }

    private void _Step()
    {
        System.out.println("Step: " + StepManager.GetStepCount());
        GameEntityManager.SpawnEntities();
        GameEntityManager.MoveAll();
    }

}
