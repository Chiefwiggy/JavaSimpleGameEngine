package Simulation.GameObjects;

import GameObjects.AlarmObject;
import GameObjects.GameObject;
import Helpers.CONSTANTS;
import Helpers.Keyboard;
import Managers.AlarmObjectManager;

import java.awt.event.KeyEvent;

public class Timer extends GameObject {

    Timer() {
        alarmObject.SubmitAlarmRegistration(1 * CONSTANTS.SECOND,AlarmObjectManager.ALARM_ID.ALARM_0);
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/2, AlarmObjectManager.ALARM_ID.ALARM_1);
        updateObject.SubmitUpdateRegistration();
    }

    @Override
    public void GameAlarm0() {
        System.out.println("GAMER");
    }

    @Override
    public void GameAlarm1() {
        System.out.println("GAMER 2");
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/2, AlarmObjectManager.ALARM_ID.ALARM_1);
    }

    @Override
    public void GameUpdate() {
        if (Keyboard.isKeyPressed(KeyEvent.VK_2)) {
            System.out.println("R");
        }
    }

}
