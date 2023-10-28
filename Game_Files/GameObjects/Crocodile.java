package Game_Files.GameObjects;

import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public class Crocodile extends BoardEntity {
    public Crocodile(Pair<Integer> xy) {
        super(xy, BoardEntities.CROCODILE);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        super.GameDraw(g2);
        g2.setColor(new Color(0, 102, 51));
        g2.fillOval(drawXY.get(0), drawXY.get(1), (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

}
