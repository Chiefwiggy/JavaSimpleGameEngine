package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.Background;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;

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

    public static void Initialize() {
        getInstance()._Initialize();
    }

    private void _Initialize() {
        background = new Background();
        stepManager = new StepManager();
        BoardEntityManager.Initialize();
    }

    public static void Step() {
        getInstance()._Step();
    }

    private void _Step() {
        // Do what it does every step: The following is temporary
        System.out.println("Here");
        Pair<Integer> xy = new Pair<>((int) (Math.random() * 9.9), (int) (Math.random() * 9.9));
        BoardEntityManager.Spawn(xy, BoardEntities.Crocodile);
    }

}
