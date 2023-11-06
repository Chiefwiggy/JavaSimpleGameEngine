package Engine.Managers;

import Engine.GameObjects.AlarmObject;
import Engine.Helpers.Multimap;
import Engine.Misc.ALARM_ID;

import java.util.ArrayList;

public class AlarmObjectManager {



    public class AlarmPair {
        public AlarmObject aRef;
        public ALARM_ID id;
        public Multimap.MultimapIterator it;

        public AlarmPair(AlarmObject a, ALARM_ID id) {
            this.aRef = a;
            this.id = id;
        }

        public void SetIterator(Multimap.MultimapIterator it) {
            this.it = it;
        }
    }

    public final static int ALARM_NUMBER = 4;

    public AlarmObjectManager() {
        timeline = new Multimap<>();
    }

    public void ProcessAlarms() {

        ArrayList<Multimap.MultimapIterator> objectsToRemove = new ArrayList<>();

        Multimap.MultimapLambda<Long, AlarmPair> lambda = (time, alarmPairList) -> {
            Long currentTime = System.nanoTime();

            if (time <= currentTime) {
                alarmPairList.forEach((ap) -> {
                    ap.aRef.TriggerAlarm(ap.id);
                    objectsToRemove.add(ap.it);
                });
            }
        };

        timeline.forEach(lambda);

        for (Multimap.MultimapIterator it : objectsToRemove) {
            Deregister(it);
        }
    }

    public Multimap.MultimapIterator Register(long timeInNs, ALARM_ID id, AlarmObject aRef) {
        long alarmTime = timeInNs + System.nanoTime();
        AlarmPair alarmPair = new AlarmPair(aRef, id);
        Multimap.MultimapIterator iter = timeline.Add(alarmTime, alarmPair);
        alarmPair.SetIterator(iter);
        return iter;
    }

    public void Deregister(Multimap.MultimapIterator iter) {
        timeline.Remove(iter);
    }

    private Multimap<Long, AlarmPair> timeline;



}
