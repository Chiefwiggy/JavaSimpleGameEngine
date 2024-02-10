package Game_Files;

import Engine.Helpers.Scene;
import Game_Files.Managers.GameManager;

public class GameScene extends Scene {

    @Override
    public void Initialize() { GameManager.Initialize(); }

}
