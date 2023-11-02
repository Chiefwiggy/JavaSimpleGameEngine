package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.Pair;
import Game_Files.Interfaces.DrawMethod;
import Game_Files.Managers.BoardEntityManager;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public abstract class BoardEntity extends GameObject {

    public Pair<Integer> xy;
    public Pair<Integer> drawXY;
    public double entitySizeDivisor;
    public Color color;
    public DrawMethod drawMethod;

    public BoardEntity(Pair<Integer> xy) {
        this.xy = xy;
    }

    public void Initialize(Pair<Integer> xy) {
        BoardEntityManager.Register(this);
        this.xy = xy;
        drawObject.SubmitDrawRegistration();
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        BoardEntityManager.Deregister(this);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        drawXY = new Pair<>(
        convertGridToWorldSpace(xy.get(0)) - (int) (squareSize / this.entitySizeDivisor / 2),
        convertGridToWorldSpace(xy.get(1)) - (int) (squareSize / this.entitySizeDivisor / 2)
        );
        g2.setColor(this.color);
        drawMethod.fillShape(drawXY.get(0), drawXY.get(1), (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

    // Returns the center of the gridSpace
    protected int convertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

}
