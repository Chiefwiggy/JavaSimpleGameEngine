package Game_Files.GameObjects;

import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;

import java.awt.*;

@SuppressWarnings("unused")
public class Coral extends BoardEntity {
    public Coral(Pair<Integer> xy) {
        super(xy, BoardEntities.CORAL);
        this.entitySizeDivisor = 1.0;
        this.color = new Color(25, 0, 0);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillRect;
        super.GameDraw(g2);
    }

}