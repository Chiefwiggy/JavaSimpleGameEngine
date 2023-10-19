package Simulation;

import Helpers.Scene;
import Simulation.GameObjects.Ant;
import Simulation.GameObjects.Background;
import Simulation.Managers.SimulationManager;

public class MyFirstScene extends Scene {

    Background b;
    Ant a1, a2;
    @Override
    public void Initialize() {
        SimulationManager.Initialize();
        b = new Background();


    }
}
