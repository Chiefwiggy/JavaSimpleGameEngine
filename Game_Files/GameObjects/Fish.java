package Game_Files.GameObjects;

import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public class Fish extends BoardEntity {
    public Fish(Pair<Integer> xy) {
        super(xy, BoardEntities.FISH);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        super.GameDraw(g2);
        g2.setColor(new Color(0, 102, 204));
        g2.fillRect(drawXY.get(0), drawXY.get(1), (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

}
