package Game_Files.GameObjects;

import Game_Files.Helpers.Pair;
import Game_Files.Managers.GameManager;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

public class Crocodile extends BoardEntity {

    private int fedMeter;
    public Crocodile(Pair<Integer> xy) {
        super(xy);
        this.entitySizeDivisor = 1.5;
        this.comparisonValue = 2;
        this.color = new Color(0, 102, 51);
    }

    @Override
    public void Move() {
        // First need to check if we can move to a spot with a fish
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, false, true);
        Pair<Integer> spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
        if (availableSpots.isEmpty()) {
            //Make sure we set diagonal to true somehow before the super call
            super.Move();
        }
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillOval;
        super.GameDraw(g2);
    }

}
