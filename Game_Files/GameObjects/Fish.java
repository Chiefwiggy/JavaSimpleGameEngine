package Game_Files.GameObjects;

import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Fish extends BoardEntity {

    public Fish(Pair<Integer> xy) {
        super(xy, BoardEntities.FISH);
        this.entitySizeDivisor = 3.0;
        this.color = new Color(0, 102, 204);
    }

    @Override
    public void Move() {
        // Pick random spot around that is not occupied
        // Otherwise, stay in current spot

        // I'm thinking we gather available spots, and then choose randomly from there.
        // For spots Up (x, y-1), Right (x+1, y), Down (x, y+1), Left(x-1, y)

        // We will have our GridManager tell us available spots based on our
        // x, y position. Something like the following method declaration:
        // public ArrayList<Pair<Integer>> GetAvailableAdjacentSpots(Pair<Integer> xy)
        // Then we just randomly choose one of the returned spots.
        System.out.println("Fish is moving");
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, false);
        availableSpots.forEach(System.out::println);
    }


    @Override
    public void GameDraw(Graphics2D g2) {
        this.drawMethod = g2::fillRect;
        super.GameDraw(g2);
    }

}
