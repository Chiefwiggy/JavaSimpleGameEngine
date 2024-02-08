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
    }

    private void _Step()
    {
        System.out.println("HERE");
        GameEntityManager.SpawnEntities();
        GameEntityManager.MoveAll();
        // Fish move
        // Crocs check adjacent cells for fish
            // If yes, consume and move into spot
            // Else, move to non-occupied space
                // Note: can be any adjacent space (including diagonal)
        // Query fish count
    }

}
