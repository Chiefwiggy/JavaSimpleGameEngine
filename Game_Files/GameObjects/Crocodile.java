package Game_Files.GameObjects;

import Game_Files.Helpers.Pair;

import java.awt.*;

public class Crocodile extends BoardEntity {
    public Crocodile(Pair<Integer> xy) {
        super(xy);
        this.entitySizeDivisor = 1.5;
        this.color = new Color(0, 102, 51);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillOval;
        super.GameDraw(g2);
    }

}
