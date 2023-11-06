package Engine.GameObjects;

import Engine.Misc.ALARM_ID;
import Engine.Rendering.DrawSettings;

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

        @Override
        public void Alarm3() {
            GameAlarm3();
        }
    }

    protected final void SetRenderer(String key) {
        drawObject.SetRenderer(key);
    }

    protected final void SubmitAlarmRegistration(long time, ALARM_ID id) {
        alarmObject.SubmitAlarmRegistration(time, id);
    }

    protected final void SubmitAlarmDeregistration(ALARM_ID id) {
        alarmObject.SubmitAlarmDeregistration(id);
    }

    protected final void SubmitDrawRegistration() {
        drawObject.SubmitDrawRegistration();
    }

    protected final void SubmitDrawDeregistration() {
        drawObject.SubmitDrawDeregistration();
    }

    protected final void SubmitUpdateRegistration() {
        updateObject.SubmitUpdateRegistration();
    }

    protected final void SubmitUpdateDeregistration() {
        updateObject.SubmitUpdateDeregistration();
    }

    public void GameDraw(Graphics2D g2) {}

    public void GameUpdate() {}

    public void GameAlarm0(){}
    public void GameAlarm1(){}
    public void GameAlarm2(){}
    public void GameAlarm3(){}

}
