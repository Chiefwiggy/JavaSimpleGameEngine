package Game_Files.GameObjects.EntityObjects;

import Engine.ResourceManagement.SpriteSheet;
import Game_Files.Enums.BoardEntities;
import Game_Files.Helpers.Coordinate;
import Game_Files.Helpers.Sprite;

import java.awt.*;

import static Game_Files.Managers.GameManager.*;

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
        Coordinate<Integer> coords = currentGridSpace.GetGridCoords();
        g2.fillRect(coords.GetX(), coords.GetY(), (int) SQUARE_SIZE, (int) SQUARE_SIZE);
    }

    @Override
    public void GameAlarm0() {}

}