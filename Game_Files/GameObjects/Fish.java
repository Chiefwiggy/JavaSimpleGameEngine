package Game_Files.GameObjects;

import Game_Files.Helpers.Pair;
//import Game_Files.Managers.GameManager;
//import Game_Files.Managers.GridManager;

import java.awt.*;
//import java.util.ArrayList;

public class Fish extends BoardEntity {

    public Fish(Pair<Integer> xy) {
        super(xy);
        this.entitySizeDivisor = 3.0;
        this.comparisonValue = 1;
        this.canMoveDiagonally = false;
        this.color = new Color(0, 102, 204);
    }

    /*@Override
    public void Move() {
        // This stuff will eventually be in the BoardEntity class. We just need
        // to make sure we know what is specific to fish. We will sort this out
        // after we finish the croc move method and see commonalities.
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, false, false);
        Pair<Integer> spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
        GridManager.Deregister(this);
        this.SetCoords(spot);
        GridManager.Register(this);
        *//*System.out.println("Fish is moving");
        StringBuilder sb = new StringBuilder(String.format("Fish at (%s, %s) can move to spots: ", this.xy.get(0), this.xy.get(1)));
        availableSpots.forEach(pair -> sb.append(String.format("(%s, %s) ", pair.get(0), pair.get(1))));
        System.out.println(sb);*//*
    }*/


    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillRect;
        super.GameDraw(g2);
    }

}
