package Game_Files;

import java.awt.*;

import static Game_Files.Background.squareSize;

public class Fish extends BoardEntity {
    public Fish(int x, int y) {
        super(x, y, "Fish");
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        super.GameDraw(g2);
        g2.setColor(new Color(0, 102, 204));
        g2.fillRect(drawX, drawY, (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

}
