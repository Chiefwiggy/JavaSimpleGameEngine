package Simulation_EXAMPLE.GameObjects;

import Engine.GameObjects.GameObject;
import Engine.Helpers.CONSTANTS;
import Engine.Managers.AlarmObjectManager;
import Engine.Managers.EngineManager;
import Simulation_EXAMPLE.Interfaces.FactoryObject;
import Simulation_EXAMPLE.Managers.SimulationManager;

import java.awt.*;


public class Ant extends GameObject implements FactoryObject {

    float x = 0;
    float y = 0;

    boolean isAnimating;
    float goalX;
    float goalY;

    float speed = 90f;

    public Ant() {
        super();
        System.out.println("CREATED NEW ANT");

    }
    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect((int)x,(int)y, 50, 50);
    }

    @Override
    public void GameUpdate() {
        if ((int)goalX != (int)x) {
            x += Math.signum(goalX - x)*Math.min(speed* EngineManager.GetDeltaTime(), Math.abs(goalX - x));
        }
        if ((int)goalY != (int)y) {
            y += Math.signum(goalY - y)*Math.min(speed* EngineManager.GetDeltaTime(), Math.abs(goalY - y));
        }
    }

    public void Move() {
        goalX += 70*Math.signum(0.5 - Math.random());
        goalY += 70*Math.signum(0.5 - Math.random());
    }

    public void Initialize (int x, int y) {
        this.x = x;
        this.y = y;
        this.goalX = x;
        this.goalY = y;
        drawObject.SubmitDrawRegistration();
        updateObject.SubmitUpdateRegistration();
        alarmObject.SubmitAlarmRegistration(12*CONSTANTS.SECOND, AlarmObjectManager.ALARM_ID.ALARM_0);
    }

    public void Deinitialize() {
        updateObject.SubmitUpdateDeregistration();
        drawObject.SubmitDrawDeregistration();
    }

    @Override
    public void GameAlarm0() {
        SimulationManager.ReturnAnt(this);
    }
}
