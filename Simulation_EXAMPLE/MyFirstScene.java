package Simulation_EXAMPLE;

import Engine.Helpers.Scene;
import Simulation_EXAMPLE.GameObjects.Ant;
import Simulation_EXAMPLE.GameObjects.Background;
import Simulation_EXAMPLE.Managers.SimulationManager;

public class MyFirstScene extends Scene {

    Background b;
    Ant a1, a2;
    @Override
    public void Initialize() {
        SimulationManager.Initialize();
        b = new Background();


    }
}
