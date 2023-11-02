package Game_Files.GameObjects;

import Game_Files.Helpers.Pair;

import java.awt.*;

public class Fish extends BoardEntity {

    public Fish(Pair<Integer> xy) {
        super(xy);
        this.entitySizeDivisor = 3.0;
        this.color = new Color(0, 102, 204);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillRect;
        super.GameDraw(g2);
    }

}
