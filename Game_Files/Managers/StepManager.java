package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Engine.Helpers.Keyboard;

import java.awt.event.KeyEvent;

public class StepManager extends GameObject
{

    private static StepManager instance;

    private synchronized static StepManager getInstance() {
        if (instance == null) {
            instance = new StepManager();
        }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    public static int GetStepCount() { return getInstance()._GetStepCount(); }

    private boolean hasPressed = false;

    private int stepCount = 0;

    public StepManager() {}

    private void _Initialize() { updateObject.SubmitUpdateRegistration(); }

    private int _GetStepCount() { return stepCount; }

    @Override
    public void GameUpdate()
    {
        if (!getInstance().hasPressed && Keyboard.isKeyPressed(KeyEvent.VK_SPACE))
        {
            getInstance().hasPressed = true;
            getInstance().stepCount += 1;
            GameManager.Step();
        }
        else if (getInstance().hasPressed && !(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)))
        {
            getInstance().hasPressed = false;
        }
    }

}
