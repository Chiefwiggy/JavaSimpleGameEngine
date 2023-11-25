package Game_Files.GameObjects;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public class Coral extends BoardEntity {
    public Coral(int x, int y) {
        super(x, y);
        this.color = new Color(25, 0, 0);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(this.color);
        int drawX = convertGridToWorldSpace(this.x) - (int) (squareSize / 2);
        int drawY = convertGridToWorldSpace(this.y) - (int) (squareSize / 2);
        g2.fillRect(drawX, drawY, (int) squareSize, (int) squareSize);
    }

    @Override
    public void GameAlarm0() {}

}