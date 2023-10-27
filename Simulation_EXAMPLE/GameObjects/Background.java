package Simulation_EXAMPLE.GameObjects;

import Engine.GameObjects.DrawObject;
import Engine.Managers.EngineManager;

import java.awt.*;

public class Background extends DrawObject {

    public Background() {
        SubmitDrawRegistration();
    }

    private int BOARD_SIZE = 8;

    @Override
    public void Draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        int height = EngineManager.GetHeight();
        int width = EngineManager.GetWidth();
        g2.fillRect(0, 0, width, height);
        float squareSize = width / BOARD_SIZE;
        g2.setColor(Color.GRAY);
        for (int j = 0; j < BOARD_SIZE; ++j) {
            for (int i = j % 2; i < BOARD_SIZE; i += 2) {
                g2.fillRect((int)(i*squareSize), (int)(j*squareSize),(int)squareSize,(int)squareSize);
            }
        }

    }
}
