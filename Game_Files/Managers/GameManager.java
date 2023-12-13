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

    public StepManager stepManager;

    private void _Initialize()
    {
        stepManager = new StepManager();
        GridManager.Initialize();
        GameEntityManager.Initialize();
    }

    private void _Step()
    {
        GameEntityManager.SpawnEntities();
        // Firstly, EntityManager should spawn fish if fish count meets requirements


        //EntityManager.GetQueue().forEach(entity -> System.out.println(entity.species));
        //EntityManager.GetQueue().forEach(entity -> entity.Move());
        // Instead we do
        GameEntityManager.MoveAll();
        // This should just call move for every object in the queue




        // Fish move
        // Crocs check adjacent cells for fish
            // If yes, consume and move into spot
            // Else, move to non-occupied space
                // Note: can be any adjacent space (including diagonal)
        // Query fish count




        // Do what it does every step: The following is temporary
        //System.out.println("Here");
        //GridManager.FillGridSpace((int) (Math.random() * 9.9), (int) (Math.random() * 9.9), BoardEntities.CROCODILE);
    }

}
