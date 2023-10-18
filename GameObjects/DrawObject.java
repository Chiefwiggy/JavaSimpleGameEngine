package GameObjects;

import Commands.DrawDeregistrationCommand;
import Commands.DrawRegistrationCommand;
import Commands.RegistrationCommand;
import Commands.RegistrationState;
import Managers.GameManager;

import java.awt.*;

public abstract class DrawObject extends GraphicsObject {

    private RegistrationState registrationState;
    private DrawRegistrationCommand rCmd;
    private DrawDeregistrationCommand drCmd;

    public DrawObject() {
        registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        rCmd = new DrawRegistrationCommand(this);
        drCmd = new DrawDeregistrationCommand(this);
    }


    public abstract void Draw(Graphics2D g2);

    public void SubmitDrawRegistration() {
        assert(registrationState == RegistrationState.CURRENTLY_DEREGISTERED);

        registrationState = RegistrationState.PENDING_REGISTRATION;
        GameManager.SubmitCommand(rCmd);

    }

    public void SubmitDrawDeregistration() {
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
