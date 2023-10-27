package Engine.GameObjects;

import Engine.Commands.RegistrationState;
import Engine.Commands.UpdateDeregistrationCommand;
import Engine.Commands.UpdateRegistrationCommand;
import Engine.Managers.EngineManager;

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
        EngineManager.SubmitCommand(rCmd);

    }

    public void SubmitUpdateDeregistration() {
        assert(registrationState == RegistrationState.CURRENTLY_REGISTERED);

        registrationState = RegistrationState.PENDING_DEREGISTRATION;
        EngineManager.SubmitCommand(drCmd);
    }

    public final void SceneRegistration() {
        assert(registrationState == RegistrationState.PENDING_REGISTRATION);

        registrationState = RegistrationState.CURRENTLY_REGISTERED;
        EngineManager.Register(this);
    }

    public final void SceneDeregistration() {
        assert(registrationState == RegistrationState.PENDING_DEREGISTRATION);

        registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        EngineManager.Deregister(this);
    }
}
