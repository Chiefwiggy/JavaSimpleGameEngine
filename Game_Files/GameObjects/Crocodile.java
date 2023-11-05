package Game_Files.GameObjects;

import Game_Files.Helpers.Pair;
import Game_Files.Managers.EntityManager;
import Game_Files.Managers.GameManager;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

public class Crocodile extends BoardEntity {

    protected int fedMeter;
    public Crocodile(Pair<Integer> xy) {
        super(xy);
        this.entitySizeDivisor = 1.5;
        this.comparisonValue = 2;
        this.canMoveDiagonally = true;
        this.color = new Color(0, 102, 51);
        this.fedMeter = 0;
    }

    @Override
    public void Move() {
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, false, true);
        if (!availableSpots.isEmpty()) {
            Pair<Integer> spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
            GridManager.Deregister(this);
            this.Eat(spot);
            this.SetCoords(spot);
            GridManager.Register(this);
        } else { super.Move(); }
    }

    private void Eat(Pair<Integer> xy) {
        GridManager.ClearGridSpace(xy);
        this.fedMeter += 3;
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillOval;
        super.GameDraw(g2);
    }

}
