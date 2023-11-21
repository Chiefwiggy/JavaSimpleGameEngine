package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.Crocodile;

import java.util.ArrayList;

class CrocodileManager extends GameObject {

    protected ArrayList<Crocodile> liveCrocs;
    protected CrocodileManager() {
        liveCrocs = new ArrayList<>();
    }

    protected void Spawn(Crocodile croc) { liveCrocs.add(croc); }

    protected void MoveAllCrocs() {
        for (Crocodile croc : liveCrocs) { croc.Move(); }
    }

}
