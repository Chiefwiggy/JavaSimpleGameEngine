package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;
import Game_Files.Interfaces.DrawMethod;
import Game_Files.Managers.GridManager;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public abstract class BoardEntity extends GameObject implements Comparable<BoardEntity> {

    public Pair<Integer> xy;
    protected BoardEntities species;
    protected double entitySizeDivisor;
    protected int comparisonValue;
    protected Color color;
    protected DrawMethod drawMethod;

    public BoardEntity(Pair<Integer> xy, BoardEntities species) {
        this.xy = xy;
        this.species = species;
    }

    public void Initialize(Pair<Integer> xy, BoardEntities species) {
        GridManager.Register(this);
        this.xy = xy;
        this.species = species;
        drawObject.SubmitDrawRegistration();
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        GridManager.Deregister(this);
    }

    public void Move() {}

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

    @Override
    public int compareTo(BoardEntity entity) {
        return this.comparisonValue - entity.comparisonValue;
    }

}
