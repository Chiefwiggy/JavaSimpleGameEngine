package GameObjects;

import java.awt.*;

public abstract class GameObject {

    public GameObject() {
        drawObject = new GameDrawObject();
        updateObject = new GameUpdateObject();
        alarmObject = new GameAlarmObject();
    }

    protected GameDrawObject drawObject;
    protected GameUpdateObject updateObject;
    protected GameAlarmObject alarmObject;

    protected class GameDrawObject extends DrawObject {

        @Override
        public void Draw(Graphics2D g2) {
            GameDraw(g2);
        }
    }

    protected class GameUpdateObject extends UpdateObject {

        @Override
        public void Update() {
            GameUpdate();
        }
    }

    protected class GameAlarmObject extends AlarmObject {
        @Override
        public void Alarm0() {
            GameAlarm0();
        }

        @Override
        public void Alarm1() {
            GameAlarm1();
        }

        @Override
        public void Alarm2() {
            GameAlarm2();
        }
    }

    public void GameDraw(Graphics2D g2) {}

    public void GameUpdate() {}

    public void GameAlarm0(){}
    public void GameAlarm1(){}
    public void GameAlarm2(){}

}
