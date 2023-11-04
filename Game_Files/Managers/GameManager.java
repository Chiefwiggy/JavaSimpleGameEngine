package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.Background;

import java.util.Random;

public class GameManager extends GameObject {

    private static GameManager instance;

    private synchronized static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public StepManager stepManager;
    public Background background;
    public static final Random random = new Random();

    public static void Initialize() {
        getInstance()._Initialize();
    }

    private void _Initialize() {
        background = new Background();
        stepManager = new StepManager();
        EntityManager.Initialize();
        GridManager.Initialize();
    }

    public static void Step() {
        getInstance()._Step();
    }

    private void _Step() {
        // Firstly, EntityManager should spawn fish if fish count meets requirements


        //EntityManager.GetQueue().forEach(entity -> System.out.println(entity.species));
        //EntityManager.GetQueue().forEach(entity -> entity.Move());
        // Instead we do
        EntityManager.MoveAll();
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
