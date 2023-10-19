package zGame2;

import GameObjects.GameObject;
import Helpers.CONSTANTS;
import Helpers.Keyboard;
import Managers.AlarmObjectManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    int x;
    int y;

    boolean isPrimed;

    public Player() {
        x = 0;
        y = 0;
        updateObject.SubmitUpdateRegistration();
        drawObject.SubmitDrawRegistration();
        isPrimed = false;
    }

    @Override
    public void GameUpdate() {
        if (!isPrimed && Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            isPrimed = true;
            alarmObject.SubmitAlarmRegistration(2 * CONSTANTS.SECOND, AlarmObjectManager.ALARM_ID.ALARM_0);
        }
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, 200, 200);
    }

    @Override
    public void GameAlarm0() {
        x += 30;
        isPrimed = false;
    }
}
