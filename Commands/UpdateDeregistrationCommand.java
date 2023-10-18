package Commands;

import GameObjects.UpdateObject;

public class UpdateDeregistrationCommand extends RegistrationCommand {

    private UpdateObject pUpdate = null;

    public UpdateDeregistrationCommand(UpdateObject uobj) {
        pUpdate = uobj;
    }

    public void Execute() {
        pUpdate.SceneDeregistration();
    }
}
