package Engine.GameObjects;

import Engine.Commands.DrawDeregistrationCommand;
import Engine.Commands.DrawRegistrationCommand;
import Engine.Commands.RegistrationState;
import Engine.Managers.EngineManager;
import Engine.Rendering.DrawSettings;
import Engine.Rendering.GraphicsOptions;

import java.awt.*;

public abstract class DrawObject extends GraphicsObject {

    private RegistrationState registrationState;
    private DrawRegistrationCommand rCmd;
    private DrawDeregistrationCommand drCmd;
    private String currentRendererKey = null;


    public DrawObject() {
        registrationState = RegistrationState.CURRENTLY_DEREGISTERED;
        rCmd = new DrawRegistrationCommand(this);
        drCmd = new DrawDeregistrationCommand(this);
        image = null;
    }


    public final void Draw(DrawSettings ds) {
        this.Draw(ds.GetRenderer(currentRendererKey).g2);
    }

    public abstract void Draw(Graphics2D g2);

    public void SetRenderer(String key) {
        currentRendererKey = key;
    }

    public void SubmitDrawRegistration() {
        assert(registrationState == RegistrationState.CURRENTLY_DEREGISTERED);

        registrationState = RegistrationState.PENDING_REGISTRATION;
        EngineManager.SubmitCommand(rCmd);

    }

    public void SubmitDrawDeregistration() {
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
