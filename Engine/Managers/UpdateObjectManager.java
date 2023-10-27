package Engine.Managers;

import Engine.GameObjects.UpdateObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateObjectManager {

    private List<UpdateObject> updateList;

    public UpdateObjectManager() {
        updateList = new ArrayList<>();
    }

    public void Register(UpdateObject uob) { updateList.add(uob);}
    public void Deregister(UpdateObject uob) {updateList.remove(uob);}

    public void ProcessElements() {
        for (UpdateObject uob : updateList) {
            uob.Update();
        }
    }
}
