package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.Fish;

import java.util.ArrayList;

class FishManager extends GameObject {

    protected ArrayList<Fish> liveFish;
    protected FishManager() {
        liveFish = new ArrayList<>();
    }

    protected void Spawn(Fish fish) { liveFish.add(fish); }

    protected void MoveAllFish() {
        for (Fish fish : liveFish) { fish.Move(); }
    }

}
