package Game_Files.GameObjects.EntityObjects;

import Game_Files.Enums.EntityObjects;
import Game_Files.Helpers.Coordinate;
import Game_Files.Managers.GridManager;

import java.awt.*;

public class Coral extends EntityObject {

    private final Color color = new Color(25, 0, 0);

    public Coral() {
        super();
        this.species = EntityObjects.CORAL;
    }

    @Override
    public void Move() {}

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(this.color);
        Coordinate<Float> coords = currentGridSpace.GetWorldCoords();
        int squareSize = (int) GridManager.GetGridSpaceSize();
        g2.fillRect(coords.GetRow().intValue(), coords.GetCol().intValue(), squareSize, squareSize);
    }

    @Override
    public void GameAlarm0() {}

}