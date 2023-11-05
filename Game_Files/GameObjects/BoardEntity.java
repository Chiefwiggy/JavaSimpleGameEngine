package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.Pair;
import Game_Files.Interfaces.DrawMethod;
import Game_Files.Managers.GameManager;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

import static Game_Files.GameObjects.Background.squareSize;

public abstract class BoardEntity extends GameObject implements Comparable<BoardEntity> {

    protected Pair<Integer> xy;
    protected double entitySizeDivisor;
    protected int comparisonValue;
    protected boolean canMoveDiagonally;
    protected Color color;
    protected DrawMethod drawMethod;

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

    public Pair<Integer> GetCoords() { return this.xy; }
    public void SetCoords(Pair<Integer> xy) { this.xy = xy; }

    public void Move() {
        ArrayList<Pair<Integer>> availableSpots = GridManager.GetAvailableAdjacentSpots(this.xy, this.canMoveDiagonally, false);
        if (!availableSpots.isEmpty()) {
            Pair<Integer> spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
            GridManager.Deregister(this);
            this.SetCoords(spot);
            GridManager.Register(this);
        }
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        int drawX = convertGridToWorldSpace(xy.get(0)) - (int) (squareSize / this.entitySizeDivisor / 2);
        int drawY = convertGridToWorldSpace(xy.get(1)) - (int) (squareSize / this.entitySizeDivisor / 2);
        g2.setColor(this.color);
        drawMethod.fillShape(drawX, drawY, (int) (squareSize / entitySizeDivisor), (int) (squareSize / entitySizeDivisor));
    }

    // Returns the center of the gridSpace
    private int convertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

    @Override
    public int compareTo(BoardEntity entity) {
        return this.comparisonValue - entity.comparisonValue;
    }

}
