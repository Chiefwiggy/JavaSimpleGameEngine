package Simulation_EXAMPLE.BOARD_ENTITY;

import java.awt.*;

public class Ant extends BoardEntity {

    public Ant() {
        drawObject.SubmitDrawRegistration();
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(getWorldX(), getWorldY(), width, height);
    }
}
