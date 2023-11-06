package Engine.GameObjects;

import Engine.Commands.AlarmDeregistrationCommand;
import Engine.Commands.AlarmRegistrationCommand;
import Engine.Commands.RegistrationState;
import Engine.Helpers.Multimap;
import Engine.Managers.AlarmObjectManager;
import Engine.Managers.EngineManager;
import Engine.Misc.ALARM_ID;

import java.util.ArrayList;

public abstract class AlarmObject {

    public AlarmObject() {
        rData = new ArrayList<>(AlarmObjectManager.ALARM_NUMBER);
        for (int i = 0; i < AlarmObjectManager.ALARM_NUMBER; ++i) {
            rData.add(i, new RegistrationData());
            rData.get(i).rCmd = new AlarmRegistrationCommand(this, ALARM_ID.toAlarmId(i));
            rData.get(i).drCmd = new AlarmDeregistrationCommand(this, ALARM_ID.toAlarmId(i));
            rData.get(i).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        }
    }



    public RegistrationState GetAlarmState(ALARM_ID al_id) {
        return rData.get(al_id.al_id).registrationState;
    }

    public void TriggerAlarm(ALARM_ID id) {
        rData.get(id.al_id).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        switch(id) {
            case ALARM_0:
                Alarm0();
                break;
            case ALARM_1:
                Alarm1();
                break;
            case ALARM_2:
                Alarm2();
                break;
            case ALARM_3:
                Alarm3();
                break;
        }
    }

    public void Alarm0() {}
    public void Alarm1() {}
    public void Alarm2() {}
    public void Alarm3() {}

    public final void SubmitAlarmRegistration(long time, ALARM_ID al_id) {
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_DEREGISTERED);

        rData.get(al_id.al_id).registrationState = RegistrationState.PENDING_REGISTRATION;
        rData.get(al_id.al_id).rCmd.UpdateTime(time);
        EngineManager.SubmitCommand(rData.get(al_id.al_id).rCmd);

    }

    public final void SubmitAlarmDeregistration(ALARM_ID al_id) {
        if (rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_DEREGISTRATION) return;
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_REGISTERED);

        rData.get(al_id.al_id).registrationState = RegistrationState.PENDING_DEREGISTRATION;
        EngineManager.SubmitCommand(rData.get(al_id.al_id).drCmd);
    }

    public final void AlarmRegistration(long time, ALARM_ID al_id) {
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_REGISTRATION);

        rData.get(al_id.al_id).registrationState = RegistrationState.CURRENTLY_REGISTERED;
        rData.get(al_id.al_id).mRef = EngineManager.Register(time, al_id, this);
    }

    public final void AlarmDeregistration(ALARM_ID al_id) {
        if (rData.get(al_id.al_id).registrationState == RegistrationState.CURRENTLY_DEREGISTERED) return;
        assert(rData.get(al_id.al_id).registrationState == RegistrationState.PENDING_DEREGISTRATION);

        rData.get(al_id.al_id).registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        EngineManager.Deregister(rData.get(al_id.al_id).mRef);
    }

    private class RegistrationData {
        RegistrationState registrationState;
        AlarmRegistrationCommand rCmd;
        AlarmDeregistrationCommand drCmd;
        Multimap.MultimapIterator mRef;
    }

    private ArrayList<RegistrationData> rData;
}
