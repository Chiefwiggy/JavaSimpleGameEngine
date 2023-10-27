package Engine.Commands;

import Engine.GameObjects.AlarmObject;
import Engine.Managers.AlarmObjectManager;

public class AlarmRegistrationCommand extends RegistrationCommand {

    private AlarmObject aObjRef;
    private AlarmObjectManager.ALARM_ID al_id;
    long time;

    public void UpdateTime(long t) {time = t;}

    public AlarmRegistrationCommand(AlarmObject aRef, AlarmObjectManager.ALARM_ID al_id) {
        this.aObjRef = aRef;
        this.al_id = al_id;
    }
    @Override
    public void Execute() {
        aObjRef.AlarmRegistration(time, al_id);
    }
}
