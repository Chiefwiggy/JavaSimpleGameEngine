package Game_Files.GameObjects.EntityObjects;

import Game_Files.Enums.BoardEntities;
import Game_Files.Helpers.Coordinate;
import Game_Files.Managers.GridManager;

import java.awt.*;

public class Coral extends EntityObject {

    private final Color color = new Color(25, 0, 0);

    public Coral() {
        super();
        this.species = BoardEntities.CORAL;
    }

    @Override
    public void Move() {}

    @Override
    public void GameDraw(Graphics2D g2) {
        g2.setColor(this.color);
        Coordinate<Float> coords = currentGridSpace.GetWorldCoords();
        int squareSize = (int) GridManager.GetGridSpaceSize();
        g2.fillRect(coords.GetX().intValue(), coords.GetY().intValue(), squareSize, squareSize);
    }

    @Override
    public void GameAlarm0() {}

}