package GameObjects;

import Commands.RegistrationState;
import Commands.UpdateDeregistrationCommand;
import Commands.UpdateRegistrationCommand;
import Managers.GameManager;

public abstract class UpdateObject {

    private RegistrationState registrationState;
    private UpdateRegistrationCommand rCmd;
    private UpdateDeregistrationCommand drCmd;

    public UpdateObject() {
        registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        rCmd = new UpdateRegistrationCommand(this);
        drCmd = new UpdateDeregistrationCommand(this);
    }

    public abstract void Update();

    public void SubmitUpdateRegistration() {
        assert(registrationState == RegistrationState.CURRENTLY_DEREGISTERED);

        registrationState = RegistrationState.PENDING_REGISTRATION;
        GameManager.SubmitCommand(rCmd);

    }

    public void SubmitUpdateDeregistration() {
        assert(registrationState == RegistrationState.CURRENTLY_REGISTERED);

        registrationState = RegistrationState.PENDING_DEREGISTRATION;
        GameManager.SubmitCommand(drCmd);
    }

    public final void SceneRegistration() {
        assert(registrationState == RegistrationState.PENDING_REGISTRATION);

        registrationState = RegistrationState.CURRENTLY_REGISTERED;
        GameManager.Register(this);
    }

    public final void SceneDeregistration() {
        assert(registrationState == RegistrationState.PENDING_DEREGISTRATION);

        registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        GameManager.Deregister(this);
    }
}
