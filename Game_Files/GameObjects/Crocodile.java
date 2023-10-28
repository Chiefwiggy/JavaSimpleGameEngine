package Game_Files.GameObjects;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public class Crocodile extends BoardEntity {
    public Crocodile(int x, int y) {
        super(x, y, "Croc");
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        super.GameDraw(g2);
        g2.setColor(new Color(0, 102, 51));
        g2.fillOval(drawX, drawY, (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

}
