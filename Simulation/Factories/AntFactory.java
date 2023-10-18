package Simulation.Factories;

import Simulation.GameObjects.Ant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class AntFactory {

    private ArrayList<Ant> liveAnts;
    private Queue<Ant> antQueue;

    public AntFactory() {
        liveAnts = new ArrayList<>();
        antQueue = new ArrayDeque<>();
    }

    public Ant GetAnt(int x, int y) {
        Ant newAnt = antQueue.poll();
        if (newAnt == null) {
            newAnt = new Ant();
        }
        newAnt.Initialize(x, y);
        liveAnts.add(newAnt);
        return newAnt;
    }

    public void ReturnAnt(Ant deadAnt) {
        deadAnt.Deinitialize();
        liveAnts.remove(deadAnt);
        antQueue.add(deadAnt);
    }
}
