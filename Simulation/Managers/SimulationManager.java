package Simulation.Managers;

import GameObjects.GameObject;
import Helpers.CONSTANTS;
import Helpers.Keyboard;
import Managers.AlarmObjectManager;
import Managers.GameManager;
import Simulation.GameObjects.Ant;
import Simulation.Factories.AntFactory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SimulationManager extends GameObject {

    private static SimulationManager instance;
    private static synchronized SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    private SimulationManager() {

    }

    public static void Initialize() {getInstance()._Initialize();}

    private boolean hasPressed;
    private ArrayList<Ant> ants;
    private AntFactory antFactory;

    private void _Initialize() {
        updateObject.SubmitUpdateRegistration();
        alarmObject.SubmitAlarmRegistration(5 * CONSTANTS.SECOND, AlarmObjectManager.ALARM_ID.ALARM_1);
        hasPressed = false;
        ants = new ArrayList<>();
        antFactory = new AntFactory();
    }



    public static Ant MakeAnt(int x, int y) {return getInstance()._MakeAnt(x, y);}
    public static void ReturnAnt(Ant deadAnt) {getInstance()._ReturnAnt(deadAnt);}

    private Ant _MakeAnt(int x, int y) {
        Ant newAnt = antFactory.GetAnt(x, y);
        _Register(newAnt);
        return newAnt;
    }

    private void _ReturnAnt(Ant deadAnt) {
        _Deregister(deadAnt);
        antFactory.ReturnAnt(deadAnt);
    }



    private void _Register(Ant ant) {
        ants.add(ant);
    }

    private void _Deregister(Ant ant) {
        ants.remove(ant);
    }

    @Override
    public void GameUpdate() {
        if (!hasPressed & Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            System.out.println("STEP");
            for (Ant ant : ants) {
                ant.Move();
            }
            alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/2, AlarmObjectManager.ALARM_ID.ALARM_0);
            hasPressed = true;
        }
    }

    @Override
    public void GameAlarm0() {
        hasPressed = false;
    }

    @Override
    public void GameAlarm1() {
        _MakeAnt((int)(Math.random() * GameManager.GetAntGame().getWidth()), (int)(Math.random() * GameManager.GetAntGame().getHeight()));
        alarmObject.SubmitAlarmRegistration(5 * CONSTANTS.SECOND, AlarmObjectManager.ALARM_ID.ALARM_1);
    }
}
