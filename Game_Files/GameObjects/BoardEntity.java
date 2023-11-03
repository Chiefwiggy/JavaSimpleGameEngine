package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.Pair;
import Game_Files.Interfaces.DrawMethod;
import Game_Files.Managers.GridManager;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public abstract class BoardEntity extends GameObject {

    public Pair<Integer> xy;
    public double entitySizeDivisor;
    public Color color;
    public DrawMethod drawMethod;

    public BoardEntity(Pair<Integer> xy) {
        this.xy = xy;
    }

    public void Initialize(Pair<Integer> xy) {
        GridManager.Register(this);
        this.xy = xy;
        drawObject.SubmitDrawRegistration();
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        GridManager.Deregister(this);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        int drawX = convertGridToWorldSpace(xy.get(0)) - (int) (squareSize / this.entitySizeDivisor / 2);
        int drawY = convertGridToWorldSpace(xy.get(1)) - (int) (squareSize / this.entitySizeDivisor / 2);
        g2.setColor(this.color);
        drawMethod.fillShape(drawX, drawY, (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

    // Returns the center of the gridSpace
    protected int convertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

}
