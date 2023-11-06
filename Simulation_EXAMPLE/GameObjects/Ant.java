package Simulation_EXAMPLE.GameObjects;

import Engine.GameObjects.GameObject;
import Engine.Helpers.CONSTANTS;
import Engine.Helpers.Keyboard;
import Engine.Managers.AlarmObjectManager;
import Engine.Managers.EngineManager;
import Engine.Misc.ALARM_ID;
import Engine.ResourceManagement.ImageManager;
import Engine.ResourceManagement.SpriteSheet;
import Engine.ResourceManagement.SpriteSheetManager;
import Simulation_EXAMPLE.Interfaces.FactoryObject;
import Simulation_EXAMPLE.Managers.SimulationManager;

import javax.imageio.ImageIO;
import java.awt.*;


public class Ant extends GameObject implements FactoryObject {

    float x = 0;
    float y = 0;

    boolean isAnimating;
    float goalX;
    float goalY;

    float speed = 90f;

    int currentAnimation = 0;
    long lastTime;
    SpriteSheet sp_idle;
    SpriteSheet sp_jump;

    Boolean isIdle;

    SpriteSheet current_sp;

    public Ant() {
        super();
        System.out.println("CREATED NEW ANT");
        SetRenderer("pixel");
    }
    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect((int)x,(int)y, 50, 50);

        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.setColor(Color.RED);

        g2.drawString("Hello gamer", 200, 50);

        g2.drawImage(current_sp.GetSprite(currentAnimation), (int)x, (int)y, 150, 150, null);


    }

    @Override
    public void GameUpdate() {
        if ((int)goalX != (int)x) {
            x += Math.signum(goalX - x)*Math.min(speed*EngineManager.GetDeltaTime(), Math.abs(goalX - x));
        }
        if ((int)goalY != (int)y) {
            y += Math.signum(goalY - y)*Math.min(speed*EngineManager.GetDeltaTime(), Math.abs(goalY - y));
        }


    }

    public void JumpingForJoy() {
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
        //alarmObject.SubmitAlarmRegistration(12*CONSTANTS.SECOND, ALARM_ID.ALARM_0);
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_3);

        lastTime = System.nanoTime();
        sp_idle = SpriteSheetManager.Get("adventurer_idle");
        sp_jump = SpriteSheetManager.Get("adventurer_jump");
        current_sp = sp_idle;
        isIdle = true;
    }

    public void Deinitialize() {
        updateObject.SubmitUpdateDeregistration();
        drawObject.SubmitDrawDeregistration();
    }

    public void Jump() {
        current_sp = sp_jump;
        currentAnimation = 0;
        isIdle = false;
    }

    public void GoIdle() {
        current_sp = sp_idle;
        currentAnimation = 0;
        isIdle = true;
    }

    @Override
    public void GameAlarm0() {
        SimulationManager.ReturnAnt(this);
    }

    @Override
    public void GameAlarm3() {
        //System.out.println("Time since last trigger: " + ((double)(System.nanoTime() - lastTime)/(double)CONSTANTS.SECOND));
        //lastTime = System.nanoTime();
        currentAnimation++;
        if (currentAnimation > current_sp.GetSpriteCount()-1) {
            if (!isIdle) {
                GoIdle();
            } else {
                currentAnimation = 0;
            }
        }
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/15, ALARM_ID.ALARM_3);
    }



}
