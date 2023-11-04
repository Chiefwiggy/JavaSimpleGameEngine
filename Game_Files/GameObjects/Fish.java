package Game_Files.GameObjects;

import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

public class Fish extends BoardEntity {

    public Fish(Pair<Integer> xy) {
        super(xy, BoardEntities.FISH);
        this.entitySizeDivisor = 3.0;
        this.comparisonValue = 1;
        this.color = new Color(0, 102, 204);
    }

    @Override
    public void Move() {
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, false);
        /*System.out.println("Fish is moving");
        StringBuilder sb = new StringBuilder(String.format("Fish at (%s, %s) can move to spots: ", this.xy.get(0), this.xy.get(1)));
        availableSpots.forEach(pair -> sb.append(String.format("(%s, %s) ", pair.get(0), pair.get(1))));
        System.out.println(sb);*/
    }


    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillRect;
        super.GameDraw(g2);
    }

}
