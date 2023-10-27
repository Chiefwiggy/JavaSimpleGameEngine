package Simulation_EXAMPLE.Factories;

import Simulation_EXAMPLE.GameObjects.Ant;

import java.util.ArrayDeque;
import java.util.Queue;

public class AntFactory {

    //private ArrayList<Ant> liveAnts;
    private Queue<Ant> antQueue;

    public AntFactory() {
        antQueue = new ArrayDeque<>();
    }

    public Ant GetAnt(int x, int y) {
        Ant newAnt = antQueue.poll();
        if (newAnt == null) {
            newAnt = new Ant();
        }
        newAnt.Initialize(x, y);
        return newAnt;
    }

    public void ReturnAnt(Ant deadAnt) {
        deadAnt.Deinitialize();
        antQueue.add(deadAnt);
    }
}
