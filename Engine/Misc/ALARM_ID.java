package Engine.Misc;

public enum ALARM_ID {
    ALARM_0(0),
    ALARM_1(1),
    ALARM_2(2),
    ALARM_3(3);
    public final int al_id;

    private ALARM_ID(int id) {
        this.al_id = id;
    }

    public static ALARM_ID toAlarmId(int id) {
        switch(id) {
            case 0:
                return ALARM_0;
            case 1:
                return ALARM_1;
            case 2:
                return ALARM_2;
            case 3:
                return ALARM_3;
        }
        return null;
    }
}