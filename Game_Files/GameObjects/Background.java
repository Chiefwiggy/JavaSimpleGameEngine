package Game_Files.GameObjects;

import java.awt.*;
import Engine.GameObjects.DrawObject;
import Engine.Managers.EngineManager;

public class Background extends DrawObject {

    public Background() {
        SubmitDrawRegistration();
    }

    public static final int BOARD_SIZE = 10;
    public static double squareSize;
    @Override
    public void Draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        int height = EngineManager.GetHeight();
        int width = EngineManager.GetWidth();
        g2.fillRect(0, 0, width, height);
        squareSize = (double) width / BOARD_SIZE;
        g2.setColor(Color.GRAY);
        for (int j = 0; j < BOARD_SIZE; j++) {
            for (int i = j % 2; i < BOARD_SIZE; i += 2) {
                g2.fillRect((int)(i*squareSize), (int)(j*squareSize),(int)squareSize,(int)squareSize);
            }
        }
        g2.setColor(new Color(25, 0, 0));
        g2.fillRect((int) (width/2 - squareSize), (int) (height/2 - squareSize), (int) (squareSize*2), (int) (squareSize*2));
    }

}
