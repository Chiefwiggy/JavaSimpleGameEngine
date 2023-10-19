package zGame2;

import Helpers.Scene;

public class Game2Scene extends Scene {

    Player p;
    @Override
    public void Initialize() {
        p = new Player();
    }
}
