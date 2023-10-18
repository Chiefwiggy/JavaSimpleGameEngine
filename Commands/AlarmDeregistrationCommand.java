package Commands;

import GameObjects.AlarmObject;
import Managers.AlarmObjectManager;

public class AlarmDeregistrationCommand extends RegistrationCommand {

    private AlarmObject pAlarm;
    private AlarmObjectManager.ALARM_ID al_id;
    public AlarmDeregistrationCommand(AlarmObject aRef, AlarmObjectManager.ALARM_ID al_id) {
        pAlarm = aRef;
        this.al_id = al_id;
    }
    @Override
    public void Execute() {
        pAlarm.AlarmDeregistration(al_id);
    }
}
