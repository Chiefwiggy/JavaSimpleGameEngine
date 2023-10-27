package Engine.Commands;


import Engine.GameObjects.DrawObject;

public class DrawRegistrationCommand extends RegistrationCommand {



    private DrawObject pDraw = null;

    public DrawRegistrationCommand(DrawObject dobj) {

        pDraw = dobj;
    }

    public void Execute() {
        pDraw.SceneRegistration();
    }


}
