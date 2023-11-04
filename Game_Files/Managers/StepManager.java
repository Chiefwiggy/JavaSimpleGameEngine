package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Engine.Helpers.Keyboard;

import java.awt.event.KeyEvent;

public class StepManager extends GameObject {
    private boolean hasPressed = false;
    private int stepCounter;

    public StepManager() {
        updateObject.SubmitUpdateRegistration();
    }

    @Override
    public void GameUpdate() {
        if (!hasPressed && Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            hasPressed = true;
            GameManager.Step();
        }else if (hasPressed && !(Keyboard.isKeyPressed(KeyEvent.VK_SPACE))) {
            hasPressed = false;
        }
    }
}
