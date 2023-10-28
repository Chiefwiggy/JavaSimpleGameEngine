package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.Background;

public class GameManager extends GameObject {

    private static GameManager instance;

    private synchronized static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    //public BoardEntity[][] gridSpaces;
    public StepManager stepManager;
    public Background background;

    public static void Initialize() {
        getInstance()._Initialize();
    }

    private void _Initialize() {
        //gridSpaces = new BoardEntity[BOARD_SIZE][BOARD_SIZE];
        background = new Background();
        BoardEntityManager.Initialize();
        stepManager = new StepManager();

        // Potentially create an BoardEntityManager. Responsible for spawning, despawning,
        // registering, & deregistering entities. Will also contain our 2D array.
        // Essentially it will deal with all array operations for our 2D array.
    }

    public static void Step() {
        getInstance()._Step();
    }

    private void _Step() {
        // Do what it does every step: The following is temporary
        System.out.println("Here");
        int x = (int) (Math.random() * 9.9);
        int y = (int) (Math.random() * 9.9);
        BoardEntityManager.Spawn(x, y, "Croc");
    }

}
